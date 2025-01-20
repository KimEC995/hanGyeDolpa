// Google Charts 로드
google.charts.load('current', {packages: ['corechart', 'bar']});
document.addEventListener('DOMContentLoaded', function () {
    loadYearCalendar();
    loadDifficultyChart();
});

// 1년 달력 생성
function loadYearCalendar() {
    const yearCalendarDiv = document.getElementById('year-calendar');
    const today = new Date();
    
    // 예시 데이터: 운동 기록 (날짜는 실제 데이터로 대체)
    const exerciseData = [
        {date: '2023-01-01', calories: 300, time: 30},
        {date: '2023-02-14', calories: 200, time: 20},
        {date: '2023-03-15', calories: 400, time: 50},
        {date: '2023-04-05', calories: 150, time: 15},
        {date: '2023-05-10', calories: 250, time: 25},
        {date: '2023-06-20', calories: 100, time: 10},
        {date: '2023-07-25', calories: 350, time: 40},
        {date: '2023-08-30', calories: 300, time: 35},
        {date: '2023-09-15', calories: 200, time: 20},
        {date: '2023-10-10', calories: 400, time: 60},
        {date: '2023-11-05', calories: 450, time: 55},
        {date: '2023-12-25', calories: 600, time: 75},
    ];

    let totalCalories = 0;
    let totalTime = 0;

    for (let month = 0; month < 12; month++) {
        const monthDiv = document.createElement('div');
        monthDiv.className = 'month';
        monthDiv.innerText = `${month + 1}월`;

        // 해당 월의 운동 기록 체크
        exerciseData.forEach(record => {
            const recordDate = new Date(record.date);
            if (recordDate.getFullYear() === today.getFullYear() && recordDate.getMonth() === month) {
                monthDiv.classList.add('filled'); // 운동 기록이 있는 경우 색칠
                totalCalories += record.calories;
                totalTime += record.time;
            }
        });

        yearCalendarDiv.appendChild(monthDiv);
    }

    // 총 소모 칼로리 및 시간 업데이트
    document.getElementById('total-calories').innerText = totalCalories;
    document.getElementById('total-time').innerText = totalTime;
}

// 누적 난이도별 그래프 생성
function loadDifficultyChart() {
    const data = google.visualization.arrayToDataTable([
        ['난이도', '횟수'],
        ['낮음', 5],
        ['중간', 10],
        ['높음', 15]
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

// 운동 기록 조회 버튼 클릭 이벤트
document.getElementById('view-records-btn').onclick = function() {
    location.href = '/exercise'; // 운동 기록 조회 페이지로 이동
};

// GitHub 스타일 히트맵 로드 함수
function loadHeatmap() {
    const heatmapContainer = document.getElementById('commit-heatmap');

    // 예시 데이터: true(출석), false(결석) 배열
    const attendanceData = generateAttendanceData();

    // 히트맵 스타일로 7열(요일 기준) x 52행(52주) 생성
    attendanceData.forEach(isPresent => {
        const dayDiv = document.createElement('div');
        dayDiv.classList.add('heatmap-day');
        if (isPresent) {
            dayDiv.classList.add('present'); // 출석한 경우 색칠
        }
        heatmapContainer.appendChild(dayDiv);
    });
}

// 출석 데이터를 생성 (랜덤 예제)
function generateAttendanceData() {
    const data = [];
    for (let i = 0; i < 365; i++) {
        data.push(Math.random() > 0.3); // 약 70% 확률로 출석
    }
    return data;
}




