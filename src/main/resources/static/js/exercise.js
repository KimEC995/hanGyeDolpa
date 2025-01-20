// exercise.js
let currentDate = new Date();
const calendarTitle = document.getElementById("calendar-title");
const calendarDays = document.getElementById("calendar-days");
let selectedDate = null; // 선택된 날짜를 저장할 변수

function loadCalendar() {
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth();

    // 달력 제목 설정
    calendarTitle.innerText = `${year}년 ${month + 1}월`;

    // 날짜 초기화
    calendarDays.innerHTML = '';

    // 오늘 날짜 계산
    const today = new Date();
    const todayYear = today.getFullYear();
    const todayMonth = today.getMonth();
    
    // 첫 번째 날의 요일 찾기
    const firstDay = new Date(year, month, 1).getDay();
    // 마지막 날 찾기
    const lastDate = new Date(year, month + 1, 0).getDate();

    // 빈 공간 추가
    for (let i = 0; i < firstDay; i++) {
        const emptyDiv = document.createElement('div');
        calendarDays.appendChild(emptyDiv);
    }

    // 날짜 추가
    for (let date = 1; date <= lastDate; date++) {
        const dayDiv = document.createElement('div');
        dayDiv.className = 'day';
        dayDiv.innerText = date;

        // 오늘 날짜와 비교하여 클래스 추가
        if (date === today.getDate() && year === todayYear && month === todayMonth) {
            dayDiv.classList.add('today'); // 오늘 날짜에 스타일 적용
        }

        // 날짜 선택 시 호출
        dayDiv.onclick = () => {
            selectDate(year, month, date); // 날짜 선택 시 호출
            loadExerciseRecords(); // 선택한 날짜의 운동 기록 로드
            updateRecordButton(); // 운동 기록 버튼 활성화
        };

        calendarDays.appendChild(dayDiv);
    }
}

function selectDate(year, month, date) {
    selectedDate = new Date(year, month, date); // 선택된 날짜 저장
    updateCalendarHighlight(); // 선택된 날짜 색칠
}

function updateCalendarHighlight() {
    const days = document.querySelectorAll('.day');
    days.forEach((dayDiv, index) => {
        const day = index + 1;
        dayDiv.classList.remove('selected'); // 이전 선택 제거
        if (selectedDate && day === selectedDate.getDate()) {
            dayDiv.classList.add('selected'); // 선택된 날짜 색칠
        } else if (day === new Date().getDate() && new Date().getMonth() === selectedDate.getMonth()) {
            dayDiv.classList.add('today'); // 오늘 날짜 색칠
        }
    });
}

function updateRecordButton() {
    const recordButton = document.getElementById("record-button");
    if (selectedDate) {
        recordButton.disabled = false; // 버튼 활성화
        recordButton.onclick = () => {
            const formattedDate = selectedDate.toISOString().split('T')[0]; // YYYY-MM-DD 형식
            location.href = `/add-exercise?date=${formattedDate}`; // 날짜를 쿼리 파라미터로 전달
        };
    }
}

function getDateFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('date'); // URL에서 날짜 가져오기
}

async function loadExerciseRecords() {
    const recordsDiv = document.getElementById("records-container");
    recordsDiv.innerHTML = ''; // 이전 기록 삭제

    if (!selectedDate) {
        alert("날짜를 선택하세요.");
        return;
    }

    const date = selectedDate.toISOString().split('T')[0]; // YYYY-MM-DD 형식
    const userId = 1; // 실제 사용자 ID로 변경

    // 선택된 날짜의 운동 기록을 서버에서 가져오는 코드
    try {
        const response = await fetch(`/api/exercise/records?date=${date}&userId=${userId}`);
        if (!response.ok) {
            throw new Error('네트워크 응답이 좋지 않습니다.');
        }

        const data = await response.json();
        if (data.length === 0) {
            recordsDiv.innerHTML = '<p>운동 기록이 없습니다. 운동 좀 하세요!</p>';
        } else {
            data.forEach(record => {
                const recordElement = document.createElement('p');
                recordElement.innerHTML = `
                    <strong>운동 종류:</strong> ${record.exerciseType}<br>
                    <strong>운동 장소:</strong> ${record.location}<br>
                    <strong>운동 난이도:</strong> ${record.difficulty}<br>
                    <strong>시도 횟수:</strong> ${record.attempts}<br>
                    <strong>소모 칼로리:</strong> ${record.calories} kcal<br>
                    <strong>운동 시간:</strong> ${Math.floor(record.timeSpent / 60)}시간 ${record.timeSpent % 60}분
                `;
                recordsDiv.appendChild(recordElement);
            });
        }
    } catch (error) {
        console.error('Error fetching records:', error);
        recordsDiv.innerHTML = '<p>운동 기록을 불러오는 데 오류가 발생했습니다.</p>';
    }
}


// 이전 및 다음 달 버튼 클릭 시 달력 업데이트
document.getElementById("prev-month-btn").onclick = () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    loadCalendar();
};

document.getElementById("next-month-btn").onclick = () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    loadCalendar();
};

// 초기 달력 로드 및 운동 기록 가져오기
window.onload = () => {
    loadCalendar();
    loadExerciseRecords(); // 운동 기록 로드
};



