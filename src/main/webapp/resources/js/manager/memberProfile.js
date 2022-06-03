function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(".sleepUser").on("click",function() {

    var sid = getParameter("id");

    if(confirm('해당 회원을 정지하시겠습니까?')) {
        $.ajax({
            url:'/admin/sleepUser',
            type:'get',
            data:{'id':sid},
            success: function(data) {
                alert("정지가 완료되었습니다");
                document.location.reload();
            },
            error: function(err) {
                alert("실패")
            }
        });
    }

});

$(".deleteUser").on("click",function() {
    var did = getParameter("id");

    if(confirm('해당 회원을 삭제하시겠습니까?')) {
        $.ajax({
            url:'/admin/deleteUser',
            type:'get',
            data:{'id':did},
            success: function(data) {
                alert("삭제가 완료되었습니다");

                location.href="/admin/memberManagement";
            },
            error: function(err) {
                alert("실패")
            }
        });
    }


});

