// Google Charts 로드
google.charts.load('current', {packages: ['corechart', 'bar']});
document.addEventListener('DOMContentLoaded', function () {
    loadYearCalendar();
    loadDifficultyChart();
	loadMonthlyStats();
	loadHeatmap();
});

// 1년 달력 생성
async function loadYearCalendar() {
    const yearCalendarDiv = document.getElementById('year-calendar');
    const today = new Date();

    // 서버에서 운동 기록 데이터 가져오기
    let exerciseData = [];
    try {
        const response = await fetch(`/api/exercise/records?year=${today.getFullYear()}`);
        if (response.ok) {
            exerciseData = await response.json(); // 서버에서 받은 데이터
        } else {
            console.error('운동 기록을 가져오는 데 실패했습니다.');
        }
    } catch (error) {
        console.error('오류 발생:', error);
    }

    

    for (let month = 0; month < 12; month++) {
        const monthDiv = document.createElement('div');
        monthDiv.className = 'month';
        monthDiv.innerText = `${month + 1}월`;

        // 해당 월의 운동 기록 체크
        exerciseData.forEach(record => {
            const recordDate = new Date(record.exerciseDate);
            if (recordDate.getFullYear() === today.getFullYear() && recordDate.getMonth() === month) {
                monthDiv.classList.add('filled'); // 운동 기록이 있는 경우 색칠
                totalCalories += record.calories;
                totalTime += record.timeSpent;
            }
        });

        yearCalendarDiv.appendChild(monthDiv);
    }

    // 총 소모 칼로리 및 시간 업데이트
    document.getElementById('total-calories').innerText = totalCalories;
    document.getElementById('total-time').innerText = totalTime;
}

// 월별 통계 로드
async function loadMonthlyStats() {
    const currentDate = new Date(2025, 0); // 2025년 1월 기준
    const currentMonth = `${currentDate.getFullYear()}-${String(currentDate.getMonth() + 1).padStart(2, '0')}`;

    try {
        const response = await fetch(`/api/exercise/records/month?month=${currentMonth}`);
        if (!response.ok) throw new Error('통계 불러오기 실패');

        const stats = await response.json();
        console.log('월별 통계 데이터:', stats); // 응답 데이터 확인
        document.getElementById("total-calories").innerText = stats.calories; // 총 소모 칼로리
        document.getElementById("total-time").innerText = stats.time; // 총 운동 시간
    } catch (error) {
        console.error(error);
    }
}
// 페이지 로드 시 통계 데이터 불러오기
window.onload = () => {
    loadMonthlyStats();
    loadHeatmap(); // 히트맵 로드
};

// 누적 난이도별 그래프 생성
async function loadDifficultyChart() {
    const response = await fetch('/api/exercise/difficulty-stats'); // 난이도 통계 API 호출
    const difficultyData = await response.json();

    const data = google.visualization.arrayToDataTable([
        ['난이도', '횟수'],
        ['낮음', difficultyData.low],
        ['중간', difficultyData.medium],
        ['높음', difficultyData.high]
    ]);

    const options = {
        title: '누적 난이도별 운동 횟수',
        width: '100%',
        height: 300,
        isStacked: true,
        colors: ['#6a5acd', '#ffeb3b', '#f44336']
    };

    const chart = new google.visualization.BarChart(document.getElementById('attendance-chart'));
    chart.draw(data, options);
}

// GitHub 스타일 히트맵 로드 함수
async function loadHeatmap() {
    const heatmapContainer = document.getElementById('commit-heatmap');
    heatmapContainer.innerHTML = ''; // 기존 내용을 지웁니다.

    // 서버에서 운동 기록 데이터 가져오기
    let attendanceData = new Array(365).fill(false); // 365일의 출석 데이터 초기화
    try {
        const response = await fetch(`/api/exercise/records?year=2025`);
        if (response.ok) {
            const exerciseRecords = await response.json(); // 서버에서 받은 운동 기록 데이터
            
            // 운동 기록을 기반으로 출석 데이터 생성
            exerciseRecords.forEach(record => {
                const recordDate = new Date(record.exerciseDate);
                const dayOfYear = Math.floor((recordDate - new Date(recordDate.getFullYear(), 0, 0)) / 1000 / 60 / 60 / 24);
                if (dayOfYear >= 0 && dayOfYear < 365) {
                    attendanceData[dayOfYear] = true; // 해당 날짜에 운동 기록이 있는 경우 출석 체크
                }
            });
        } else {
            console.error('운동 기록을 가져오는 데 실패했습니다.');
        }
    } catch (error) {
        console.error('오류 발생:', error);
    }

    // 요일 및 월 표시
    const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
    const monthNames = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];

    // 월 헤더 추가
    const headerRow = document.createElement('div');
    headerRow.className = 'week-label';
    headerRow.innerHTML = '<span></span>' + monthNames.map(month => `<span>${month}</span>`).join('');
    heatmapContainer.appendChild(headerRow);

    // 요일 레이블 추가
    daysOfWeek.forEach(day => {
        const dayLabel = document.createElement('div');
        dayLabel.className = 'week-label';
        dayLabel.innerText = day;
        heatmapContainer.appendChild(dayLabel);
    });

    // 히트맵 셀 생성
    for (let week = 0; week < 52; week++) {
        for (let day = 0; day < 7; day++) {
            const index = week * 7 + day;
            const dayDiv = document.createElement('div');
            dayDiv.classList.add('heatmap-day');

            if (index < attendanceData.length && attendanceData[index]) {
                dayDiv.classList.add('present'); // 운동한 경우 색칠
            } else {
                dayDiv.classList.add('absent'); // 운동하지 않은 경우 색칠 X
            }

            // 날짜 툴팁 표시
            const dateLabel = new Date(2025, 0, index + 1); // 2025년 기준
            dayDiv.title = `${dateLabel.getFullYear()}-${String(dateLabel.getMonth() + 1).padStart(2, '0')}-${String(dateLabel.getDate()).padStart(2, '0')} - ${attendanceData[index] ? '운동' : '운동 안 함'}`;
            heatmapContainer.appendChild(dayDiv);
        }
    }
}







