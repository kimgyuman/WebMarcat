
let reportBtnCount = 0;
let firstid = '';
let finalVal = '';
function reportOne(value, id) {
    if(reportBtnCount === 0){
        let btn = document.getElementById(id);//하나
        btn.style.backgroundColor = '#0b5ed7';
        finalVal = value;
        firstid = id;
        reportBtnCount++;
    }else{
        let btn = document.getElementById(firstid);//하나
        btn.style.backgroundColor = '#1fa5fd';
        finalVal = '';
        firstid = '';
        reportBtnCount = 0;
    }
}

function report() {
    if(reportBtnCount === 0) {
        alert("신고사유가 선택되지 않았습니다.");
        return false;
    }else {
        let report = {"content": finalVal}
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/user/board/report?id="+oneBoardId,
            data: JSON.stringify(report),
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.result === "200") {
                    alert("게시글 신고가 완료되었습니다.");
                    opener.document.location.reload();
                    window.close();
                } else if (data.result === "100") {
                    alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
                    return reportReult = true;

                } else if(data.result === "300") {
                    alert("신고하기 기능은 로그인 후 이용할 수 있습니다!");
                    return reportReult = true;

                } else{
                    alert("올바른 방법으로 접근해주세요");
                    return reportReult = true;

                }
            },
            errors: function () {
                alert("error")
                return false;

            }
        });
        return false;
    }
}