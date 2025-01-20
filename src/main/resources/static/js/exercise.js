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
        };

        calendarDays.appendChild(dayDiv);
    }
}

function selectDate(year, month, date) {
    selectedDate = new Date(year, month, date); // 선택된 날짜 저장
}

function loadExerciseRecords() {
    if (!selectedDate) {
        alert("날짜를 선택하세요.");
        return;
    }

    const recordsDiv = document.getElementById("exercise-records");
    recordsDiv.innerHTML = ''; // 이전 기록 삭제

    // 선택된 날짜의 운동 기록을 서버에서 가져오는 코드 작성
    fetch(`/api/exercise/records?date=${selectedDate.toISOString().split('T')[0]}`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                recordsDiv.innerHTML = '<p>운동 기록이 없습니다. 운동 좀 하세요!</p>';
            } else {
                data.forEach(record => {
                    const recordElement = document.createElement('p');
                    recordElement.textContent = `${record.exerciseType} - ${record.timeSpent} 분`;
                    recordsDiv.appendChild(recordElement);
                });
            }
        })
        .catch(error => {
            console.error('Error fetching records:', error);
            recordsDiv.innerHTML = '<p>운동 기록을 불러오는 데 오류가 발생했습니다.</p>';
        });
}

document.getElementById("prev-month-btn").onclick = () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    loadCalendar();
};

document.getElementById("next-month-btn").onclick = () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    loadCalendar();
};

// 초기 달력 로드
loadCalendar();




