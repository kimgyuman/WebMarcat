function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(".notifyContents-btn").on("click",function() {
    var cnt1 = 0;
    var cnt2 = 0;
    var cnt3 = 0;
    var cnt4 = 0;
    var cnt5 = 0;
    var cnt6 = 0;
    var row = $(this).closest('tr');

    var id = row.find('td:eq(3)').text();

    $.ajax({
        url:'/admin/boardNotifyContentsView',
        type:'get',
        data: {'id': id},
        success: function(data) {
            $.each(data, function (index, item) {
                if (item.contents == "스팸홍보/도배글입니다") {
                    cnt1 = item.cnt;
                }else if (item.contents == "음란물입니다") {
                    cnt2 = item.cnt;
                }else if (item.contents == "욕설/혐오/차별적 표현이 포함되어 있습니다") {
                    cnt3 = item.cnt;
                }else if (item.contents == "개인정보 노출 게시물입니다") {
                    cnt4 = item.cnt;
                }else if (item.contents == "청소년에게 유해한 내용입니다") {
                    cnt5 = item.cnt;
                }else if (item.contents == "저작권침해 게시물입니다") {
                    cnt6 = item.cnt;
                }
            });
            document.getElementById('bNotify1').innerText = cnt1;
            document.getElementById('bNotify2').innerText = cnt2;
            document.getElementById('bNotify3').innerText = cnt3;
            document.getElementById('bNotify4').innerText = cnt4;
            document.getElementById('bNotify5').innerText = cnt5;
            document.getElementById('bNotify6').innerText = cnt6;

            $(".notify-popup").fadeIn();
        },
        error: function(err) {
            alert("실패")
        }
    });
});

function notifyPopClose() {
    $(".notify-popup").fadeOut();
}


$(".status-btn").on("click",function() {

    var row = $(this).closest('tr');

    var td = row.find('td:eq(3)');
    var td1 = row.find('td:eq(6)');

    var id = td.text();
    var status = td1.text();

    console.log(status);


    location.href="/admin/notifyBoardStatus?id="+id+"&status="+status;
});

function boardNotifySearch() {
    var search = document.getElementById("bn-select").value;
    var keyword = document.getElementById("bn-input").value;

    location.href="/admin/boardNotifyHistory?page="+1+"&search="+search+"&keyword="+keyword;
}

$("#bn-input").keyup(function(event) {
    if (event.keyCode === 13) {
        var search = document.getElementById("bn-select").value;
        var keyword = document.getElementById("bn-input").value;

        location.href="/admin/boardNotifyHistory?page="+1+"&search="+search+"&keyword="+keyword;
    }
});

function bnAmountChange() {

    var search = getParameter('search');
    var keyword = getParameter('keyword');

    var selectBox = document.getElementById('bn-amountSelect');

    var value = selectBox.options[selectBox.selectedIndex].value;

    if (keyword != '') {
        location.href="/admin/boardNotifyHistory?page="+1+"&real="+value+"&amount="+value+"&search="+search+"&keyword="+keyword;
    }else {
        location.href="/admin/boardNotifyHistory?page="+1+"&real="+value+"&amount="+value;
    }

}

window.onload = function () {
    var real = getParameter("real");

    if (real == '') {
        $("#bn-amountSelect").val(10).prop("selected", true);
    }else {
        $("#bn-amountSelect").val(real).prop("selected", true);
    }

}