const spaceValidation = RegExp(/\s/g);
const pswValidation = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/)
const currentPswElement = document.getElementById('userPw');
const pswElement = document.getElementById('userPwUpdate');
const pswCheckElement = document.getElementById('userPwCheck');
var checkCurrentPswResult = false;
var checkPswResult = false;
var checkPswChkResult = false;

$('#userPw').on('keyup', function (){
   var userPw = currentPswElement.value;
   var current_psw_msg = document.querySelector("#current_psw_msg");
    if (pswValidation.test(userPw)) {
        current_psw_msg.style.display = "inline-block";
        current_psw_msg.style.visibility = 'visible';
        current_psw_msg.innerHTML = "사용 가능합니다.";
        current_psw_msg.style.color = "green";
        checkPswResult = false;
    } else {
        current_psw_msg.innerHTML = "숫자+영문+특수문자 조합으로 8~15자리";
        current_psw_msg.style.color = "red";
        checkPswResult = false;
    }
});

$('#userPwUpdate').on('keyup', function () {
    var userPwUpdate = pswElement.value;
    var userPwCheck = pswCheckElement.value;
    var psw_msg = document.querySelector("#psw_msg");
    var pswCheck_msg = document.querySelector("#pswCheck_msg");
    if (pswValidation.test(userPwUpdate)) {
        psw_msg.style.display = "inline-block";
        psw_msg.style.visibility = 'visible';
        psw_msg.innerHTML = "사용 가능합니다.";
        psw_msg.style.color = "green";
        pswCheckMethod();
        checkPswResult = true;
    } else {
        psw_msg.innerHTML = "숫자+영문+특수문자 조합으로 8~15자리";
        psw_msg.style.color = "red";
        checkPswResult = false;
    }
});

$('#userPwCheck').on('keyup', function () {
    pswCheckMethod();
});

function pswCheckMethod() {
    var userPwUpdate = pswElement.value;
    var userPwCheck = pswCheckElement.value;
    var pswCheck_msg = document.querySelector("#pswCheck_msg");
    if (userPwUpdate !== userPwCheck) {
        if (pswCheckValidation(userPwCheck)) {
            pswCheck_msg.innerHTML = "비밀번호가 일치하지 않습니다.";
            pswCheck_msg.style.color = "red";
            checkPswChkResult = false;
        } else {
            pswCheck_msg.innerHTML = "";
            pswCheck_msg.style.color = "red";
            checkPswChkResult = false;
        }
    } else if (checkPswResult === true) {
        pswCheck_msg.style.display = "inline-block";
        pswCheck_msg.style.visibility = 'visible';
        pswCheck_msg.innerHTML = "비밀번호가 일치합니다.";
        pswCheck_msg.style.color = "green";
        checkPswChkResult = true;
    }
}

function pswCheckValidation(pswCheck) {
    if (pswCheck === "") {
        return false;
    } else if (spaceValidation.test(pswCheck)) {
        return false;
    } else if (pswCheck.length < 8 || pswCheck.length > 15) {
        return false;
    } else {
        return true;
    }
}

function validation() {
    var currentPsw = currentPswElement.value;
    var psw = pswElement.value;
    var pswCh = pswCheckElement.value;

    if (currentPsw == "" || psw === "" || pswCh === "") {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (spaceValidation.test(currentPsw) || spaceValidation.test(psw) || spaceValidation.test(pswCh)) {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (!pswValidation.test(psw)) {
        alert('비밀번호는 숫자+영문+특수문자 조합으로 8~15자리를 사용해야 합니다.')
        return false;
    } else if (psw !== pswCh) {
        alert('비밀번호가 동일하지 않습니다.')
        return false;
    } else if (currentPsw === psw) {
        alert('현재 사용중인 비밀번호와 다른 비밀번호를 사용해주세요.')
        return false;
    } else if (checkCurrentPswResult === true) {
        alert('현재 비밀번호를 입력하세요.')
        return false;
    } else if (checkPswResult === false) {
        alert('변경할 비밀번호를 입력하세요.')
        return false;
    } else if (checkPswChkResult === false) {
        alert('변경할 비밀번호를 다시 한번 입력하세요.')
        return false;
    } else {
        alert('비밀번호를 수정하였습니다.')
        return true;
    }
}
