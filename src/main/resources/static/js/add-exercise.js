async function submitExerciseRecord(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries()); // FormData를 일반 객체로 변환

    const payload = {
        exerciseType: data.exerciseType, // 운동 종류
        exerciseDate: data.exerciseDate, // 운동 날짜
        location: data.location,          // 운동 장소
        difficulty: parseInt(data.difficulty), // 운동 난이도
        count: parseInt(data.count),      // 시도 횟수
        calories: parseInt(data.calories), // 소모 칼로리
        timeSpent: parseInt(data.timeSpent), // 운동 시간
        userId: parseInt(data.userId),    // 사용자 ID
    };

    // 디버깅: payload 출력
    console.log("전송할 데이터:", payload);

    try {
        const response = await fetch('/api/exercise/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),
        });

        if (response.ok) {
            const responseData = await response.json();
            alert(responseData.message); // 성공 메시지 표시
            
            // 운동 기록 목록 페이지로 이동하면서 날짜 전달
            window.location.href = `/exercise?date=${payload.exerciseDate}`; // 선택한 날짜로 이동
            
        } else {
            const errorData = await response.json();
            alert('운동 기록 등록에 실패했습니다: ' + errorData.message);
        }
    } catch (error) {
        alert('네트워크 오류: ' + error.message);
    }
}







