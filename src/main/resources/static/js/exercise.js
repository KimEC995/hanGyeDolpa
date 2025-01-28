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
        if (date === today.getDate() && year === todayYear && month === todayMonth && !selectedDate) {
            dayDiv.classList.add('today'); // 오늘 날짜 강조
        }

        // 선택된 날짜 강조
        if (selectedDate && date === selectedDate.getDate() && year === selectedDate.getFullYear() && month === selectedDate.getMonth()) {
            dayDiv.classList.add('selected');
        }

        // 날짜 선택 시 호출
        dayDiv.onclick = () => {
            selectDate(year, month, date); // 날짜 선택
            loadExerciseRecords(); // 기록 로드
        };

        calendarDays.appendChild(dayDiv);
    }
}

function selectDate(year, month, date) {
    selectedDate = new Date(year, month, date); // 로컬 시간대로 설정
    loadCalendar(); // 달력 다시 로드하여 선택된 날짜 강조
}

async function loadExerciseRecords() {
    const recordsDiv = document.getElementById("records-container");
    recordsDiv.innerHTML = ''; // 이전 기록 삭제

    if (!selectedDate) {
        recordsDiv.innerHTML = '<p>날짜를 선택하세요.</p>';
        return;
    }

    const year = selectedDate.getFullYear();
    const month = String(selectedDate.getMonth() + 1).padStart(2, '0');
    const day = String(selectedDate.getDate()).padStart(2, '0');
    const date = `${year}-${month}-${day}`;

    try {
        const response = await fetch(`/api/exercise/records?date=${date}`);
        if (!response.ok) throw new Error('기록 불러오기 실패');

        const records = await response.json();

        if (records.length === 0) {
            recordsDiv.innerHTML = '<p>운동 기록이 없습니다.</p>';
        } else {
            records.forEach(record => {
                const recordElement = document.createElement('div');
                recordElement.className = 'record-item'; // 클래스 추가
                recordElement.innerHTML = `
                    <strong>운동 장소:</strong> ${record.location}<br>
                    <strong>난이도:</strong> ${record.difficulty}<br>
                    <strong>시도 횟수:</strong> ${record.count}<br>
                    <strong>소모 칼로리:</strong> ${record.calories} kcal<br>
                    <strong>운동 시간:</strong> ${Math.floor(record.timeSpent / 60)}시간 ${record.timeSpent % 60}분<br>
                `;
                recordsDiv.appendChild(recordElement); // 각 기록을 화면에 추가
            });
        }
    } catch (error) {
        recordsDiv.innerHTML = '<p>운동 기록을 불러오는 중 오류가 발생했습니다.</p>';
    }
}



// 이전/다음 월 버튼
document.getElementById("prev-month-btn").onclick = () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    loadCalendar();
};

document.getElementById("next-month-btn").onclick = () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    loadCalendar();
};

// "운동 기록하기" 버튼 클릭 시 선택한 날짜 전달
document.querySelector(".record-button").onclick = () => {
    if (!selectedDate) {
        alert("날짜를 선택해주세요!");
        return;
    }

    // 로컬 시간대 기준으로 YYYY-MM-DD 형식 변환
    const year = selectedDate.getFullYear();
    const month = String(selectedDate.getMonth() + 1).padStart(2, '0');
    const day = String(selectedDate.getDate()).padStart(2, '0');
    const date = `${year}-${month}-${day}`;

    location.href = `/exercise/add?date=${date}`;
};

// 초기 로드
window.onload = () => {
    loadCalendar();
    loadExerciseRecords();
};

