const spaceValidation = RegExp(/\s/g);
const engValidation = RegExp(/[a-zA-Z]/);
const numValidation = RegExp(/[0-9]/);
const specialValidation = RegExp(/[`~!@#$%^&*|\\\'\";:\/?]/gi);

$(document).keypress(function(e) { // 엔터키 막기
    if (e.keyCode == 13) e.preventDefault();
});

function setAddress(obj) { // 부모창의 특정 부분에 text를 추가해주기 위한 메서드
    var inputJuso = opener.document.getElementById("textJuso");
    var pkJuso = opener.document.getElementById("jusoCode");
    var src = $(obj).attr('value');
    var jusoValue = $(obj).text();
    inputJuso.value = jusoValue;
    pkJuso.value = src;
    window.close(); //추가 해준 후 바로 닫음
}


$(document).ready(function () {
    $('#jusoSubmitBtn').click(function action(){ // 발리데이션 및 컨트롤러로 주소 데이터 보내기
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
            var juso = {"juso" : jusoVal};
            $.ajax({
                type: "POST",
                url: "/market/searchJuso",
                data: JSON.stringify(juso),
                dataType: "json",
                contentType: "application/json",
                success: function (data){
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
                            '<a href="javascript:void(0);" onclick="setAddress(this)" class="setJuso" value="' +
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