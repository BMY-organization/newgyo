document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();  // 기본 폼 제출 막기

    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    //const remember = document.getElementById('remember').checked;

    // 간단한 클라이언트 측 검증 (선택)
    if (!email || !password) {
        showError('이메일과 비밀번호를 모두 입력해주세요.');
        return;
    }

    // FormData로 urlencoded 형식 만들기 (Spring Security 기본 로그인과 호환)
    const formData = {
        username: document.getElementById('email').value,
        password: document.getElementById('password').value,
    };
    // formData.append('remember', remember ? 'on' : 'off');  // Spring Security가 기대하는 값

    try {
        const response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });
        let data = await response.json()

        if (response.ok) {
            // 로그인 성공 시 토큰을 local에 저장
            // -> 헤더에 토큰 포함해서 리다이렉트
            localStorage.setItem('Authorization', data.token);

            // JS 에서도 메서드 방식으로 간략하게 바꾸자
            // 로컬에 저장하고 헤더에 토큰을 주고 리다이렉트를 해줘야 한다
            fetch('/', {
                method: 'GET',
                headers: {
                    'Authorization': data.token,   // ← 여기서 헤더 포함!
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('홈 페이지 로드 실패: ' + response.status);
                    }
                    return response.text();  // HTML 텍스트로 받음
                })
                .then(html => {
                    // 현재 문서의 내용을 /home의 HTML로 완전히 교체 → 리다이렉트 효과
                    document.open();
                    document.write(html);
                    document.close();

                    // URL도 /home으로 변경 (뒤로가기 버튼 정상 동작)
                    window.history.pushState({}, 'Home', '/');

                    console.log('Authorization 헤더 포함해서 /home으로 이동 완료');
                })
                .catch(err => {
                    console.error('홈 페이지 로드 실패:', err);
                    alert('홈 페이지로 이동할 수 없습니다.');
                    // 실패 시 일반 리다이렉트로 fallback
                    window.location.href = '/login';
                });

            //window.location.href = '/home';         // 또는 '/dashboard', '/home' 등
        } else {
            // 로그인 실패 (401, 403 등)
            const errorText = await response.text();

            if (response.status === 401) {
                showError('이메일 또는 비밀번호가 올바르지 않습니다.');
            } else if (response.status === 403) {
                showError('접근 권한이 없습니다.');
            } else {
                showError('로그인 중 오류가 발생했습니다.');
            }

            console.log('로그인 실패:', errorText);
        }
    } catch (error) {
        console.error('로그인 요청 오류:', error);
        showError('서버와의 연결에 문제가 있습니다.');
    }
});

// 에러 메시지 표시 함수
function showError(message) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
}