const spaceValidation = RegExp(/\s/g);
const engValidation = RegExp(/[a-zA-Z]/);
const numValidation = RegExp(/[0-9]/);
const engNumValidation = RegExp(/^[a-zA-Z0-9]*$/);
const specialValidation = RegExp(/[`~!@#$%^&*|\\\'\";:\/?]/gi);
const krValidation = RegExp(/^[ㄱ-ㅎ가-힣]*$/);
const krNumValidation= RegExp(/^[ㄱ-ㅎ가-힣0-9]*$/);
const emailValidation= RegExp(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i); //이메일 형식인지에 대한 검사. 아이디@도메인의 형식을 충족해야 한다.
const cellphoneValidation = RegExp(/^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/); //'-'없이 핸드폰번호인지에 대한 형식검사.
const phoneValidation = RegExp(/^\d{2,3}\d{3,4}\d{4}$/); //'-'없이 전화번호인지에 대한 형식검사. 각 부분에 대한 자리수도 충족시켜야 한다.
const personalNumValidation = RegExp(/^\d{6}[1-4]\d{6}/); //'-'없이 주민번호에 대한 글자수 및 뒷자리 첫글자가 1~4의 범위에 있는지에 대한 검사

$(document).ready(function () {

    $('#idSubmit').click(function() {
        var userId = $("#id").val();
        var checkId = {"userId" : userId};
        const element = document.getElementById('result_msg');

        if(userId === "") {
            alert('공백 없이 입력하세요.')

        } else if (spaceValidation.test(userId)) {
            alert('공백 없이 입력하세요.')

        } else if (specialValidation.test(userId)) {
            alert('특수문자 없이 입력하세요.')

        } else if (krValidation.test(userId)) {
            alert('영문, 숫자 4~20자로 입력하세요.')

        } else if (userId.length<4 || userId.length>20) {
            alert('영문, 숫자 4~20자로 입력하세요.')

        } else {
            $.ajax({
                type: "POST",
                url: "/add/checkId",
                data: JSON.stringify(checkId),
                dataType: "json",
                async: "false",
                contentType: "application/json",
                success: function (data){
                    if(data===false) {
                        setUid(userId);
                    } else {
                        // element.innerText = '존재하는 아이디입니다.'
                        alert('공백이 있거나 존재하는 아이디입니다.')
                    }
                },
                error: function () {
                    alert('error발생')
                }
            });
        }
    });

    $('#jusoSubmit').click(function action(){
        const jusoVal = document.querySelector("#juso").value;
        if(jusoVal === "") {
            alert('공백 없이 입력하세요.')

        } else if (spaceValidation.test(jusoVal)) {
            alert('공백 없이 입력하세요.')

        } else if (specialValidation.test(jusoVal)) {
            alert('특수문자 없이 입력하세요.')

        } else if (engValidation.test(jusoVal)) {
            alert('한글,숫자로 입력하세요.')

        } else if (jusoVal.length<2 || jusoVal.length>10) {
            alert('한글 2~10자로 입력하세요.')

        } else {
            // var getJuso = document.querySelector("#juso").value.replace(/(^\s*)|(\s*$)/g, "")
            var juso = {"juso" : jusoVal};
            $.ajax({
                type: "POST",
                url: "/findJuso",
                data: JSON.stringify(juso),
                dataType: "json",
                contentType: "application/json",
                success: function (data){
                    // alert('success');
                    resultData = data;
                    var tr = '<thead>'+
                        '<tr>' +
                        '<th>번호</th>'+
                        '<th>주소</th>'+
                        '</tr>'+
                        '</thead>';
                    $("#result tr").remove();
                    $.each(data, function (index,item){
                        tr += '<tr><td>' + (index+1) + '</td><td>' +
                            '<a href="javascript:void(0);" onclick="setAddr(this)" class="setJuso" value="' +
                            item.admCd8 + '">' + item.admNm + '</a></td></tr>'

                    });
                    $("#result").append(tr);
                },
                error: function () {
                    alert("주소가 없습니다. 한글 2글자 이상 입력하세요.")
                }
            });
        }
    });
});

$(document).keypress(function(e) {
    if (e.keyCode == 13) e.preventDefault();
});

function setUid(uId){
    alert('사용 가능한 아이디입니다.')
    var inputId = document.getElementById("userId");
    opener.document.getElementById("userId").value=uId;
    window.close();
}

function setAddr(obj){
    var inputJuso = document.getElementById("inputJuso");
    var pkJuso = document.getElementById("pkJuso");
    var src = $(obj).attr('value');
    var jusoValue = $(obj).text();
    opener.inputJuso.value = jusoValue;
    opener.pkJuso.value = src;
    // alert(opener.pkJuso.value)
    window.close();
}