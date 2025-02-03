// 현재 연도와 월을 전역 변수로 관리 [수정됨]
let currentYear = new Date().getFullYear(); // [수정됨]
let currentMonth = new Date().getMonth(); // 0부터 시작 (0: 1월, 11: 12월) [수정됨]

// 달력 생성 함수
function loadCalendar() {
    const calendarTitle = document.getElementById("calendar-title");
    const calendarDays = document.getElementById("calendar-days");
    // onst currentDate = new Date();
    // const year = currentDate.getFullYear();
    // const month = currentDate.getMonth();

    // 달력 제목 설정
    calendarTitle.innerText = `${currentYear}년 ${currentMonth + 1}월`; // [수정됨]

    // 달력 초기화
    calendarDays.innerHTML = '';

    // 월의 첫 번째 날과 마지막 날 계산
	const firstDay = new Date(currentYear, currentMonth, 1).getDay(); // [수정됨]
    const lastDate = new Date(currentYear, currentMonth + 1, 0).getDate(); // [수정됨]

    // 빈 칸 추가 (첫 주의 시작 요일까지)
    for (let i = 0; i < firstDay; i++) {
        const emptyDiv = document.createElement("div");
        calendarDays.appendChild(emptyDiv);
    }

    // 날짜 추가
    for (let date = 1; date <= lastDate; date++) {
        const dayDiv = document.createElement("div");
        dayDiv.className = "day";
        dayDiv.innerText = date;

        // 선택된 날짜 강조
        const selectedDate = sessionStorage.getItem("selectedDate");
        //const formattedDate = new Date(year, month, date).toISOString().split('T')[0];
		const formattedDate = new Date(Date.UTC(currentYear, currentMonth, date)).toISOString().split('T')[0];
        if (selectedDate === formattedDate) {
            dayDiv.classList.add("selected");
        }

        // 날짜 클릭 이벤트
        dayDiv.onclick = () => {
            selectDate(formattedDate);
			console.log(formattedDate);
			
        };

        calendarDays.appendChild(dayDiv);
    }
}

// 이전 달로 이동 [수정됨]
function goToPreviousMonth() { // [수정됨]
    if (currentMonth === 0) { // 1월에서 이전 달로 이동하면 전년도 12월 [수정됨]
        currentYear -= 1; // [수정됨]
        currentMonth = 11; // [수정됨]
    } else {
        currentMonth -= 1; // [수정됨]
    }
    loadCalendar(); // 달력 다시 로드 [수정됨]
}

// 다음 달로 이동 [수정됨]
function goToNextMonth() { // [수정됨]
    if (currentMonth === 11) { // 12월에서 다음 달로 이동하면 다음 년도 1월 [수정됨]
        currentYear += 1; // [수정됨]
        currentMonth = 0; // [수정됨]
    } else {
        currentMonth += 1; // [수정됨]
    }
    loadCalendar(); // 달력 다시 로드 [수정됨]
}


// 달력에서 날짜를 선택하고, sessionStorage에 저장
function selectDate(formattedDate) {
    // 선택된 날짜를 sessionStorage에 저장
    sessionStorage.setItem("selectedDate", formattedDate);
    // alert(`운동 날짜 저장 완료: ${formattedDate}`);

    loadCalendar(); // 달력 다시 로드하여 선택된 날짜 강조
    loadExerciseRecords(); // 기록 로드
}



// 운동 기록 불러오기
async function loadExerciseRecords() {
    const recordsDiv = document.getElementById("records-container");
    recordsDiv.innerHTML = ''; // 기존 기록 초기화

    // sessionStorage에서 사용자 ID와 선택된 날짜 가져오기
    const exerciseDate = sessionStorage.getItem("selectedDate");
	//const exerciseDate = new Date(sessionStorage.getItem("selectedDate")).toISOString().split('T')[0];
    const userId = sessionStorage.getItem("userId");
	
	
	console.log("----------------------------");
	console.log(""+exerciseDate);
	console.log("----------------------------");
	
    // 날짜와 사용자 ID가 없는 경우 처리
    if (!exerciseDate || !userId) {
        recordsDiv.innerHTML = '<p>운동 날짜 또는 사용자 정보가 없습니다.</p>';
        return;
    }
	console.log(sessionStorage);
    try {
        // 서버 요청
        const response = await fetch(`/api/exercise/records`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ exerciseDate, userId })
        });
		console.log(response);
        if (!response.ok) throw new Error("운동 기록을 불러오는 중 오류 발생");

        const records = await response.json();
		console.log(records);
        if (records.length === 0) {
            recordsDiv.innerHTML = '<p>운동 기록이 없습니다.</p>';
        } else {
            records.forEach(record => {
                const recordElement = document.createElement("div");
                recordElement.innerHTML = `
                    <strong>운동 장소:</strong> ${record.climbPlace}<br>
                    <strong>난이도:</strong> ${record.climbStage}<br>
                    <strong>시도 횟수:</strong> ${record.climbCount}<br>
                    <strong>운동 시간:</strong> ${record.climbTime}분<br>
                `;
                recordsDiv.appendChild(recordElement);
            });
        }
    } catch (error) {
        recordsDiv.innerHTML = '<p>운동 기록을 불러오는 중 오류가 발생했습니다.</p>';
    }
}

document.getElementById("record-button").onclick = () => {
    const date = sessionStorage.getItem("selectedDate");
    const userId = sessionStorage.getItem("userId");

    if (!date || !userId) {
        alert("운동 날짜 또는 사용자 정보가 없습니다!");
        return;
    }

    alert(`운동 기록 페이지로 이동합니다: 날짜=${date}, 사용자 ID=${userId}`);
    window.location.href = '/exercise-add'; 
};

// 버튼 클릭 이벤트 추가 [수정됨]
document.getElementById("prev-month-btn").onclick = goToPreviousMonth; // [수정됨]
document.getElementById("next-month-btn").onclick = goToNextMonth; // [수정됨]

// 초기
window.onload = () => {
    loadCalendar();
    loadExerciseRecords();
};
