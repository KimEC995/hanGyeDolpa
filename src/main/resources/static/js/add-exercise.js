// 운동 기록 등록 함수
document.addEventListener("DOMContentLoaded", () => {
    const dateField = document.getElementById("exercise-date");
    const urlParams = new URLSearchParams(window.location.search);
    const selectedDate = urlParams.get("date");

    if (selectedDate) {
        dateField.value = selectedDate; // URL의 날짜를 설정
    }
});

async function submitExerciseRecord(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());

    const payload = {
        exerciseType: data.exerciseType,
        exerciseDate: data.exerciseDate,
        location: data.location,
        difficulty: parseInt(data.difficulty),
        count: parseInt(data.count),
        calories: parseInt(data.calories),
        timeSpent: parseInt(data.timeSpent),
        userId: parseInt(data.userId),
    };

    try {
        const response = await fetch('/api/exercise/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload),
        });

        if (response.ok) {
            const responseData = await response.json();
            alert(responseData.message);
            window.location.href = `/exercise?date=${payload.exerciseDate}`;
        } else {
            const errorData = await response.json();
            alert('등록 실패: ' + errorData.message);
        }
    } catch (error) {
        alert('네트워크 오류: ' + error.message);
    }
}





