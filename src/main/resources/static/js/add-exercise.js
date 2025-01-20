// add-exercise.js
async function submitExerciseRecord(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries()); // FormData를 일반 객체로 변환

    const payload = {
        exerciseType: data.exerciseType,
        exerciseDate: data.exerciseDate,
        location: data.location,
        difficulty: data.difficulty,
        calories: data.calories,
        timeSpent: data.timeSpent,
    };

    // 디버깅: payload 출력
    console.log("전송할 데이터:", payload); // 디버깅을 위한 출력

    try {
        const response = await fetch('/api/exercise/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload), // 객체를 JSON 문자열로 변환
        });

        if (response.ok) {
            const responseData = await response.json(); // JSON으로 파싱
            alert(responseData.message); // 성공 메시지 표시
            window.location.href = '/exercise'; // 운동 기록 목록 페이지로 이동
        } else {
			//response.then(data => data.text()).then(str => console.log(str));
            const errorData = await response.json(); // JSON으로 파싱
            alert('운동 기록 등록에 실패했습니다123: ' + errorData.message); // 오류 메시지 표시
        }
    } catch (error) {
        alert('네트워크 오류: ' + error.message);
    }
}






