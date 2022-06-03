function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(".notifyContents-btn").on("click",function() {
    console.log('gd1');
    var cnt1 = 0;
    var cnt2 = 0;
    var cnt3 = 0;
    var cnt4 = 0;
    var cnt5 = 0;
    var cnt6 = 0;
    var row = $(this).closest('tr');

    var id = row.find('td:eq(3)').text();

    $.ajax({
        url:'/admin/notifyContentsView',
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
            document.getElementById('notify1').innerText = cnt1;
            document.getElementById('notify2').innerText = cnt2;
            document.getElementById('notify3').innerText = cnt3;
            document.getElementById('notify4').innerText = cnt4;
            document.getElementById('notify5').innerText = cnt5;
            document.getElementById('notify6').innerText = cnt6;

            console.log('gd2');
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


    location.href="/admin/notifyTableStatus?id="+id+"&status="+status;
});

function goodsNotifySearch() {
    var search = document.getElementById("gn-select").value;
    var keyword = document.getElementById("gn-input").value;

    location.href="/admin/notifyHistory?page="+1+"&search="+search+"&keyword="+keyword;
}

$("#gn-input").keyup(function(event) {
    if (event.keyCode === 13) {
        var search = document.getElementById("gn-select").value;
        var keyword = document.getElementById("gn-input").value;

        location.href="/admin/notifyHistory?page="+1+"&search="+search+"&keyword="+keyword;
    }
});

function gnAmountChange() {

    var search = getParameter('search');
    var keyword = getParameter('keyword');

    var selectBox = document.getElementById('gn-amountSelect');

    var value = selectBox.options[selectBox.selectedIndex].value;

    if (keyword != '') {
        location.href="/admin/notifyHistory?page="+1+"&real="+value+"&amount="+value+"&search="+search+"&keyword="+keyword;
    }else {
        location.href="/admin/notifyHistory?page="+1+"&real="+value+"&amount="+value;
    }

}

window.onload = function () {
    var real = getParameter("real");

    if (real == '') {
        $("#gn-amountSelect").val(10).prop("selected", true);
    }else {
        $("#gn-amountSelect").val(real).prop("selected", true);
    }

}
