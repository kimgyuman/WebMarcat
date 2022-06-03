function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function goodsListSearch() {
    var search = document.getElementById("gl-select").value;
    var keyword = document.getElementById("gl-input").value;

    location.href="/admin/goodsList?page="+1+"&search="+search+"&keyword="+keyword;
}

$("#gl-input").keyup(function(event) {
    if (event.keyCode === 13) {
        var search = document.getElementById("gl-select").value;
        var keyword = document.getElementById("gl-input").value;

        location.href="/admin/goodsList?page="+1+"&search="+search+"&keyword="+keyword;
    }
});

function glAmountChange() {
    var search = getParameter('search');
    var keyword = getParameter('keyword');

    var selectBox = document.getElementById('gl-amountSelect');

    var value = selectBox.options[selectBox.selectedIndex].value;

    if (keyword != '') {
        location.href="/admin/goodsList?page="+1+"&real="+value+"&amount="+value+"&search="+search+"&keyword="+keyword;
    }else {
        location.href="/admin/goodsList?page="+1+"&real="+value+"&amount="+value;
    }

}

window.onload = function () {
    var real = getParameter("real");


    if (real == '') {
        $("#gl-amountSelect").val(10).prop("selected", true);
    }else {
        $("#gl-amountSelect").val(real).prop("selected", true);
    }

}

$(".goods-window").on("click",function() {

    window.open("/market",'market',"_blank")
});



