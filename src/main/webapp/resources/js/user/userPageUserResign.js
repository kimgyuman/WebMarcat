const spaceValidation = RegExp(/\s/g);
const pswValidation = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/)
const pswElement = document.getElementById('userPw');


$('#userPw').on('keyup', function (){
    var userPw = pswElement.value;
    var psw_msg = document.querySelector("#current_psw_msg");
    if (pswValidation.test(userPw)) {
        psw_msg.style.display = "inline-block";
        psw_msg.style.visibility = 'visible';
        psw_msg.innerHTML = "사용 가능합니다.";
        psw_msg.style.color = "green";
    } else {
        psw_msg.innerHTML = "숫자+영문+특수문자 조합으로 8~15자리";
        psw_msg.style.color = "red";
    }
});

function resignValidation() {
    var psw = pswElement.value;

    if (psw === "") {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (spaceValidation.test(psw)) {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (!pswValidation.test(psw)) {
        alert('비밀번호는 숫자+영문+특수문자 조합으로 8~15자리를 사용해야 합니다.')
        return false;
    }else {
        alert('회원 탈퇴가 완료되었습니다.')
        return true;
    }
}




