document.addEventListener("DOMContentLoaded", function() {
    const authLink = document.getElementById('auth-link');
    const token = localStorage.getItem('Authorization'); // 저장하신 키 이름 확인 (예: 'token', 'jwt')

    if (token) {
        // 1. 토큰이 있다면 '로그아웃' 모드로 변경
        authLink.innerText = 'LOGOUT';
        authLink.href = '#'; // 실제 로그아웃은 JS 함수로 처리
        authLink.classList.add('logout-mode');

        // 2. 로그아웃 클릭 이벤트
        authLink.addEventListener('click', function(e) {
            e.preventDefault();
            if (confirm("로그아웃 하시겠습니까?")) {
                localStorage.removeItem('Authorization'); // 토큰 삭제
                location.href = '/'; // 홈으로 리다이렉트
            }
        });
    }
});