const spaceValidation = RegExp(/\s/g);
const numValidation = RegExp(/[0-9]/);
const specialValidation = RegExp(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
const cellphoneValidation = RegExp(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/); //'-'없이 핸드폰번호인지에 대한 형식검사.
const phoneValidation = RegExp(/^\d{2,3}\d{3,4}\d{4}$/); //'-'없이 전화번호인지에 대한 형식검사. 각 부분에 대한 자리수도 충족시켜야 한다.
const sendPswCheckElement = document.getElementById('checkNum');
const preNickElement = document.getElementById('preNickName');
const nickElement = document.getElementById('nickName');
const phoneElement = document.getElementById('userPhone');
const prejusoElement = document.getElementById('preinputJuso');
const jusoElement = document.getElementById('inputJuso');
var checkNickResult = true;
var checkPhoneResult = true;
var timerResult = false;
var jusoResult = true;
var timerval;
var submitResult =false;

$(document).keypress(function (e) {
    if (e.keyCode == 13) e.preventDefault();
});

function address() {
    var url = "/findJuso";
    window.open(url, "findJuso", 'width=500,height=400, scrollbars=no, resizable=no');
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
                        submitResult = true;
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

function nickNameValidation(nickName) {
    var preNick = preNickElement.value;
    if (nickName === "") {
        return false;
    } else if (spaceValidation.test(nickName)) {
        return false;
    } else if (specialValidation.test(nickName) || nickName.length < 2 || nickName.length > 8) {
        return false;
    } else if (specialValidation.test(nickName) || preNick === nickName) {
        return false;
    } else {
        return true;
        submitResult = true;
    }
}


function validation() {
    var preNick = preNickElement.value;
    var nick = nickElement.value;
    var juso = jusoElement.value;
    var preJuso = prejusoElement.value;
    if (nick === "" || juso === "") {
        alert("공백 없이 입력하세요.")
        return false;
    // } else if (spaceValidation.test(nick) || spaceValidation.test(juso)) {
    //     alert("공백 없이 입력하세요.")
    //     return false;
    } else if (specialValidation.test(nick) || nick.length < 2 || nick.length > 8) {
        alert('특수문자를 제외한 2~8자리 닉네임을 입력하세요.')
        return false;
    } else if (nick === preNick && juso === preJuso) {
        alert('하나 이상의 정보를 수정해야 합니다.')
        return false;
    } else if (checkNickResult === false) {
        alert('현재 닉네임과 다른 닉네임을 입력하세요.')
        return false;
    } else if (jusoResult === false) {
    alert('정확한 주소를 입력하세요.')
    return false;
    }else {
        alert('정보를 수정하였습니다.')
        return true;
        submitResult = true;
    }
}
