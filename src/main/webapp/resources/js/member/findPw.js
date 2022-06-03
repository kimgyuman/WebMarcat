const spaceValidation = RegExp(/\s/g);
const numValidation = RegExp(/[0-9]/);
const cellphoneValidation = RegExp(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/); //'-'없이 핸드폰번호인지에 대한 형식검사.
const pswValidation = RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/)
const idElement = document.getElementById('userId');
const pswElement = document.getElementById('userPw');
const pswCheckElement = document.getElementById('userPwCheck');
const sendPswCheckElement = document.getElementById('checkNum');
const phoneElement = document.getElementById('userPhone');
var checkPswResult = false;
var checkPswChkResult = false;
var checkPhoneResult = false;
var timerResult = false;
var timerval;

// 비밀번호 입력 시 사용 가능 여부 검증
$('#userPw').on('keyup', function () {
    var userPw = pswElement.value;
    var psw_msg = document.querySelector("#psw_msg");
    if (pswValidation.test(userPw)) {
        psw_msg.style.display = "inline-block";
        psw_msg.style.visibility = 'visible';
        psw_msg.innerHTML = "사용 가능합니다.";
        psw_msg.style.color = "green";
        checkPswResult = true;
    } else {
        psw_msg.innerHTML = "숫자+영문+특수문자 조합으로 8~15자리";
        psw_msg.style.color = "red";
        checkPswResult = false;
    }
});

// 비밀번호 확인 입력 시 동일한 비밀번호를 작성하였는지 검증
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

// 문자 전송
$(document).ready(function () {
    $('#checkPhone').click(function () {
        var phoneNumber = phoneElement.value;
        var recipientPhoneNumber = phoneNumber.replace(/\-/g, '');
        var request = {"recipientPhoneNumber": recipientPhoneNumber};
        var userId = document.querySelector("#userId");
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
                        userId.readOnly = true;
                        phoneNum.readOnly = false;
                        sendCkPhone.disabled = false;
                        document.getElementById("timer").style.color = "red";
                        var findPw_msg = document.querySelector("#findPw_msg");
                        findPw_msg.style.display = "block";
                        findPw_msg.style.visibility = 'hidden';
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

// 타이머 메서드
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

// 인증번호 전송 및 인증번호 검증 후 해당 아이디가 있는지 확인하고 카카오 유저인지 확인
$('#sendCkPhone').click(function () {
    var uId = idElement.value;
    var phone = phoneElement.value;
    var num = sendPswCheckElement.value;
    var userPhone = phone.replace(/\-/g, '');
    var checkNum = num.replace(/\-/g, '');
    var phoneNum = document.querySelector("#userPhone");
    var sendCkPhone = document.querySelector("#sendCkPhone");
    var pwdWrap = document.querySelector(".pwdWrap");
    var pwdCheckWrap = document.querySelector(".pwdCheckWrap");
    var request = {"recipientPhoneNumber": userPhone, "authCode": checkNum, "uId": uId}
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
                    pwdWrap.style.visibility = "visible";
                    pwdWrap.style.display = "block";
                    pwdCheckWrap.style.visibility = "visible";
                    pwdCheckWrap.style.display = "block";
                    document.getElementById("timer").style.color = "green";
                    document.getElementById("timer").innerHTML = "인증 완료되었습니다.";
                } else if (data.result === "100") {
                    findPw_msgVisible("입력된 전화번호가 없습니다.");
                } else if (data.result === "300") {
                    findPw_msgVisible("잘못된 전화번호 입니다.");
                } else if (data.result === "310") {
                    findPw_msgVisible("인증번호를 정확하게 입력하세요.");
                } else if (data.result === "320") {
                    findPw_msgVisible("인증번호가 틀립니다.");
                } else if (data.result === "400") {
                    findPw_msgVisible("아이디가 존재하지 않습니다.");
                } else if (data.result === "210") {
                    clearInterval(timerval);
                    document.getElementById("timer").style.color = "green";
                    document.getElementById("timer").innerHTML = "인증 완료되었습니다.";
                    findPw_msgVisible("카카오 로그인유저입니다. <br>카카오로 문의하여 주시기 바랍니다.");
                } else {
                    alert('다시 한번 더 확인하여 주시기 바랍니다.')
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

function findPw_msgVisible(text) {
    var findPw_msg = document.querySelector("#findPw_msg");
    findPw_msg.style.display = "inline-block";
    findPw_msg.style.visibility = 'visible';
    findPw_msg.style.color = "red";
    findPw_msg.innerHTML = text;
}

// 값 검증
function validation() {
    var id = idElement.value;
    var psw = pswElement.value;
    var pswCh = pswCheckElement.value;
    var phone = phoneElement.value;

    if (id === "" || psw === "" || pswCh === "" || phone === "") {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (spaceValidation.test(id) || spaceValidation.test(psw) || spaceValidation.test(pswCh) ||
        spaceValidation.test(phone)) {
        alert("공백 없이 입력하세요.")
        return false;
    } else if (!pswValidation.test(psw)) {
        alert('비밀번호는 숫자+영문+특수문자 조합으로 8~15자리를 사용해야 합니다.')
        return false;
    } else if (psw !== pswCh) {
        alert('비밀번호가 동일하지 않습니다.')
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