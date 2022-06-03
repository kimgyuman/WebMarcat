const spaceValidation = RegExp(/\s/g);
const specialValidation = RegExp(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
const krValidation = RegExp(/^[ㄱ-ㅎ가-힣]*$/);
const pswValidation = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/)
const idElement = document.getElementById('input_text');
const pswElement = document.getElementById('input_password');
var REST_API_KEY = '';
var REDIRECT_URI = 'http://localhost:8080/login';
const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

// url param이 있을 경우 작동
var code = new URL(window.location.href).searchParams.get('code');
var failLogin = new URL(window.location.href).searchParams.get('login');
var kakaoLoginError = new URL(window.location.href).searchParams.get('status');
window.onload = function () {

    // 카카오 인가 코드 받았을 경우 작동
    if (code !== null) {
        location.href = "/login/kakao?code=" + code;
    }

    // 정지된 아이디일 경우 작동
    if (failLogin !== null) {
        let elementById = document.getElementById("loginResult");
        elementById.style.display = "inline-block";
        elementById.style.visibility = 'visible';
        elementById.innerHTML = "탈퇴 또는 정지된 아이디입니다.";
        elementById.style.color = "red";
    }

    // 카카오로그인 에러 처리
    if (kakaoLoginError !== null && kakaoLoginError === "110") {
        let elementById = document.getElementById("loginResult");
        elementById.style.display = "inline-block";
        elementById.style.visibility = 'visible';
        elementById.innerHTML = "다시 입력해 주시기 바랍니다.";
        elementById.style.color = "red";
    }
}

// 글자 검증
function validation() {
    var id = idElement.value;
    var psw = pswElement.value;

    if (id === "" || psw === "") {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (spaceValidation.test(id) || spaceValidation.test(psw)) {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (specialValidation.test(id) || krValidation.test(id) || id.length<4 || id.length>20) {
        alert('아이디는 영문, 숫자 4~20자로 입력하세요.')
        return false;
    } else if (!pswValidation.test(psw)) {
        alert('비밀번호는 숫자+영문+특수문자 조합으로 8~15자리를 사용해야 합니다.')
        return false;
    } else {
        return true;
    }
}