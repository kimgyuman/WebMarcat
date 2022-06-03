var REST_API_KEY = '';
var REDIRECT_URI = 'http://localhost:8080/user/userInfo';
const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

var code = new URL(window.location.href).searchParams.get('code');
function resignBtn() {
        var resignCheck = confirm("정말 탈퇴하시겠습니까?");
        if (resignCheck == true) {
            location.href = KAKAO_AUTH_URL;
        } else if (resignCheck == false) {
            alert("회원 탈퇴를 취소합니다.");
            location.href = "/user/userInfo"
        }
}

var code = new URL(window.location.href).searchParams.get('code');
window.onload = function () {
    if (code != null) {
        var authCode = {"code": code};
        // alert(JSON.stringify(code))
        location.href = "/user/kakaoUserResign?code=" + code;
        alert("회원 탈퇴를 완료되었습니다.");
    }
}

