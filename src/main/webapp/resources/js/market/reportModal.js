
let reportBtnCount = 0;
let firstid = '';
let finalVal = '';
function reportOne(value, id) { // 모달창 리포트 버튼 액션
    //신고시 사용자의 심적 문제로 연속적 버튼 클릭이 이루어 질 수 있어 우선적으로 카운트를 사용하여 발리데이션함.
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

function report() {// 신고 사유가 없다면 신고 할 수 없도록 악용방지
    if(reportBtnCount === 0) {
        alert("신고사유를 꼭 하나는 선택해주세요");
        return false;
    }else {
        let report = {
            "content": finalVal
        }
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/user/market/report?id="+oneGoodsId,
            data: JSON.stringify(report),
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.result === "200") {
                    window.close();
                    opener.document.location.reload(); //신고시 부모 페이지를 재로딩시켜 신고 버튼을 비활성화
                    alert("게시글 신고가 완료되었습니다.");
                } else if (data.result === "100") {
                    alert("신고하기 기능은 로그인 후 이용할 수 있습니다!");
                } else if(data.result === "300") {
                    alert("이미 정지된 게시글입니다");
                } else if(data.result === "400") {
                    alert("게시글이 존재하지 않습니다.");
                } else if (data.result === "500") {
                    alert("이미 신고한 게시글입니다.");
                }else{
                    alert("올바른 방법으로 접근해주세요");
                }
            },
            errors: function () {
                console.log("error")
            }
        });
        return false;
    }
}