const spaceValidation = RegExp(/\s/g);
const numValidation = RegExp(/[0-9]/);
const cellphoneValidation = RegExp(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/); //'-'없이 핸드폰번호인지에 대한 형식검사.
const phoneElement = document.getElementById('userPhone');
var timerResult = false;
var timerval;

// 문자 전송
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
                        phoneNum.readOnly = false;
                        sendCkPhone.disabled = false;
                        document.getElementById("timer").style.color = "red"
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

// 인증번호 전송 및 인증번호 검증 완료 시 아이디 검색 ajax 전송
$('#sendCkPhone').click(function () {
    var phone = $('#userPhone').val();
    var num = $('#checkNum').val();
    var userPhone = phone.replace(/\-/g, '');
    var checkNum = num.replace(/\-/g, '');
    var phoneJson = {"userPhone": userPhone};
    var phoneNum = document.querySelector("#userPhone");
    var sendCkPhone = document.querySelector("#sendCkPhone");
    var findId_msg = document.querySelector("#findId_msg");
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
                    clearInterval(timerval);
                    document.getElementById("timer").style.color = "green"
                    document.getElementById("timer").innerHTML = "인증 완료되었습니다.";
                    $.ajax({
                        type: "POST",
                        url: "/findId",
                        data: JSON.stringify(phoneJson),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result) {
                            findId_msg.style.display = "inline-block";
                            findId_msg.style.visibility = 'visible';
                            if (result.result === "100") {
                                findId_msg.innerHTML = "입력된 전화번호가 없습니다.";
                                findId_msg.style.color = "red";
                            } else if (result.result === "300") {
                                findId_msg.innerHTML = "잘못된 전화번호 입니다.";
                                findId_msg.style.color = "red";
                            } else if (result.result === "400") {
                                findId_msg.innerHTML = "존재하지 않는 회원입니다.";
                                findId_msg.style.color = "red";
                            } else if (result.result === "210") {
                                clearInterval(timerval);
                                findId_msg.innerHTML = "카카오 로그인유저입니다. <br>카카오로 문의하여 주시기 바랍니다.";
                                findId_msg.style.color = "red";
                            } else {
                                findId_msg.innerHTML = "고객님의 아이디는 : " + result.result + " 입니다.";
                                findId_msg.style.color = "black";
                            }
                        }, error: function () {
                            alert("에러발생")
                        }
                    })
                } else {
                    alert('인증번호가 틀립니다.')
                }
            },
            error: function () {
                alert('에러발생')
            }
        })
    } else {
        alert('정확하게 입력하시기 바랍니다.')
        checkPswResult = false;
    }
});