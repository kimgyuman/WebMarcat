const spaceValidation = RegExp(/\s/g);
const numValidation = RegExp(/[0-9]/);
const specialValidation = RegExp(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
const cellphoneValidation = RegExp(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/); //'-'없이 핸드폰번호인지에 대한 형식검사.
const pswValidation = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/)
const idElement = document.getElementById('userId');
const pswElement = document.getElementById('userPw');
const pswCheckElement = document.getElementById('userPwCheck');
const sendPswCheckElement = document.getElementById('checkNum');
const nameElement = document.getElementById('userName');
const nickElement = document.getElementById('nickName');
const phoneElement = document.getElementById('userPhone');
const jusoElement = document.getElementById('inputJuso');
var checkNickResult = false;
var checkPswResult = false;
var checkPswChkResult = false;
var checkPhoneResult = false;
var timerResult = false;
var timerval;

$(document).keypress(function (e) {
    if (e.keyCode == 13) e.preventDefault();
});

// url param이 있을 경우 작동
var signUpError = new URL(window.location.href).searchParams.get('status');
window.onload = function () {

    // 회원가입 에러 처리
    if (signUpError != null && signUpError === "100") {
        let elementById = document.getElementById("error_msg");
        elementById.style.display = "inline-block";
        elementById.style.visibility = 'visible';
        elementById.innerHTML = "다시 입력해 주시기 바랍니다.";
        elementById.style.color = "red";
    }
}

function address() {
    var url = "/findJuso";
    window.open(url, "findJuso", 'width=500,height=400, scrollbars=no, resizable=no');
}

function checkIdMain() {
    var url = "/add/checkIdMain";
    window.open(url, "checkId", 'width=400,height=300, scrollbars=no, resizable=no');
}

$('#userPw').on('keyup', function () {
    var userPw = pswElement.value;
    // var userPwCheck = pswCheckElement.value;
    var psw_msg = document.querySelector("#psw_msg");
    // var pswCheck_msg = document.querySelector("#pswCheck_msg");
    if (pswValidation.test(userPw)) {
        psw_msg.style.display = "inline-block";
        psw_msg.style.visibility = 'visible';
        psw_msg.innerHTML = "사용 가능합니다.";
        psw_msg.style.color = "green";
        // pswCheckMethod();
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
    var userPw = pswElement.value;
    var userPwCheck = pswCheckElement.value;
    var pswCheck_msg = document.querySelector("#pswCheck_msg");
    if (userPw !== userPwCheck) {
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

$('#nickName').on('keyup', function () {
    var nick = nickElement.value;
    var nickData = {"nickName": nick}
    var nick_msg = document.querySelector("#nick_msg");
    if (nickNameValidation(nick)) {
        $.ajax({
            type: "POST",
            url: "/nick",
            data: JSON.stringify(nickData),
            dataType: "json",
            async: "false",
            contentType: "application/json",
            success: function (data) {
                if (data === true) {
                    nick_msg.style.display = "inline-block";
                    nick_msg.style.visibility = 'visible';
                    nick_msg.innerHTML = "공백이 있거나 닉네임이 중복됩니다.";
                    nick_msg.style.color = "red";
                    checkNickResult = false;
                } else {
                    if (nickNameValidation(nick)) {
                        nick_msg.style.display = "inline-block";
                        nick_msg.style.visibility = 'visible';
                        nick_msg.innerHTML = "사용 가능합니다.";
                        nick_msg.style.color = "green";
                        checkNickResult = true;
                    } else {
                        nick_msg.innerHTML = "특수문자를 제외한 2~8자리 닉네임을 입력하세요.";
                        nick_msg.style.color = "red";
                        checkNickResult = false;
                    }
                }
            },
            fail: function () {
                console.log("error");
            }
        });
    } else {
        nick_msg.innerHTML = "특수문자를 제외한 2~8자리 닉네임을 입력하세요.";
        nick_msg.style.color = "red";
        checkNickResult = false;
    }
});

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

function nickNameValidation(nickName) {
    if (nickName === "") {
        return false;
    } else if (spaceValidation.test(nickName)) {
        return false;
    } else if (specialValidation.test(nickName) || nickName.length < 2 || nickName.length > 8) {
        return false;
    } else {
        return true;
    }
}

$(document).ready(function () {
    $('#checkPhone').click(function () {
        var phoneNumber = phoneElement.value;
        var recipientPhoneNumber = phoneNumber.replace(/\-/g, '');
        var request = {"recipientPhoneNumber": recipientPhoneNumber};
        var phoneNum = document.querySelector("#userPhone");
        var sendCkPhone = document.querySelector("#sendCkPhone");
        if (recipientPhoneNumber === "") {
            alert('공백 없이 입력하세요.')

        } else if (spaceValidation.test(recipientPhoneNumber)) {
            alert('공백 없이 입력하세요.')

        } else if (cellphoneValidation.test(recipientPhoneNumber)) {
            $.ajax({
                type: "POST",
                url: "/sms",
                data: JSON.stringify(request),
                dataType: "json",
                contentType: "application/json",
                async: "false",
                success: function (data) {
                    if (data === true) {
                        clearInterval(timerval);
                        timer();
                        timerResult = true;
                        checkPhoneResult = false;
                        phoneNum.readOnly = false;
                        sendCkPhone.disabled = false;
                        document.getElementById("timer").style.color = "red"
                    } else {
                        document.getElementById("timer").innerHTML = "정보를 다시 확인하세요";
                    }
                },
                error: function () {
                    console.log("error");
                }
            });
        } else {
            alert('맞는 번호를 입력하세요.')
        }
    });
});

function timer() {
    var time = 60;
    var min = "";
    var sec = "";

    var x = setInterval(function () {
        min = parseInt(time / 60);
        sec = time % 60;

        document.getElementById("timer").innerHTML = min + "분" + sec + "초";
        time--;

        if (time < 0) {
            clearInterval(x);
            document.getElementById("timer").innerHTML = "시간초과";
            timerResult = false;
        }
    }, 1000);
    timerval = x;
}

$('#sendCkPhone').click(function () {
    var phone = phoneElement.value;
    var num = sendPswCheckElement.value;
    var userPhone = phone.replace(/\-/g, '');
    var checkNum = num.replace(/\-/g, '');
    var phoneNum = document.querySelector("#userPhone");
    var sendCkPhone = document.querySelector("#sendCkPhone");
    var request = {"recipientPhoneNumber": userPhone, "authCode": checkNum}
    if (checkNum === "") {
        alert('공백 없이 입력하세요.')

    } else if (spaceValidation.test(checkNum)) {
        alert('공백 없이 입력하세요.')

    } else if (numValidation.test(checkNum) && timerResult === true) {
        $.ajax({
            type: "POST",
            url: "/smscheck",
            data: JSON.stringify(request),
            dataType: "json",
            async: "false",
            contentType: "application/json",
            success: function (data) {
                if (data.result === "200") {
                    phoneNum.readOnly = true;
                    sendCkPhone.disabled = true;
                    checkPhoneResult = true;
                    clearInterval(timerval);
                    document.getElementById("timer").style.color = "green"
                    document.getElementById("timer").innerHTML = "인증 완료되었습니다.";
                } else {
                    alert('인증번호가 틀립니다.')
                }
            },
            error: function () {

            }
        })
    } else {
        alert('정확하게 입력하시기 바랍니다.')
        checkPswResult = false;
    }
});

function validation() {
    var id = idElement.value;
    var psw = pswElement.value;
    var pswCh = pswCheckElement.value;
    var name = nameElement.value;
    var nick = nickElement.value;
    var phone = phoneElement.value;
    var juso = jusoElement.value;

    if (id === "" || psw === "" || pswCh === "" || name === "" || nick === "" || phone === "" || juso === "") {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (spaceValidation.test(id) || spaceValidation.test(psw) || spaceValidation.test(pswCh) ||
        spaceValidation.test(name) || spaceValidation.test(nick) || spaceValidation.test(phone)) {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (!pswValidation.test(psw)) {
        alert('비밀번호는 숫자+영문+특수문자 조합으로 8~15자리를 사용해야 합니다.')
        return false;
    } else if (psw !== pswCh) {
        alert('비밀번호가 동일하지 않습니다.')
        return false;
    } else if (numValidation.test(name) || specialValidation.test(name) || name.length < 2 || name.length > 10) {
        alert('정확한 이름을 입력하세요.')
        return false;
    } else if (specialValidation.test(nick) || nick.length < 2 || nick.length > 8) {
        alert('특수문자를 제외한 2~8자리 닉네임을 입력하세요.')
        return false;
    } else if (checkNickResult === false) {
        alert('닉네임을 입력하세요.')
        return false;
    } else if (checkPswResult === false) {
        alert('비밀번호를 입력하세요.')
        return false;
    } else if (checkPswChkResult === false) {
        alert('비밀번호 재확인을 입력하세요.')
        return false;
    } else if (checkPhoneResult === false) {
        alert('문자 인증을 하세요.')
        return false;
    } else {
        return true;
    }
}