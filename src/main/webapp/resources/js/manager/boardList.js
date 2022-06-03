function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(".board-window").on("click",function() {
    window.open("/board",'market',"_blank")
});

function boardSearch() {
    var search = document.getElementById("bl-select").value;
    var keyword = document.getElementById("bl-input").value;

    location.href="/admin/boardList?page="+1+"&search="+search+"&keyword="+keyword;
}

$("#bl-input").keyup(function(event) {
    if (event.keyCode === 13) {
        var search = document.getElementById("bl-select").value;
        var keyword = document.getElementById("bl-input").value;

        location.href="/admin/boardList?page="+1+"&search="+search+"&keyword="+keyword;
    }
});

function blAmountChange() {

    var search = getParameter('search');
    var keyword = getParameter('keyword');

    var selectBox = document.getElementById('bl-amountSelect');

    var value = selectBox.options[selectBox.selectedIndex].value;

    if (keyword != '') {
        location.href="/admin/boardList?page="+1+"&real="+value+"&amount="+value+"&search="+search+"&keyword="+keyword;
    }else {
        location.href="/admin/boardList?page="+1+"&real="+value+"&amount="+value;
    }


}

window.onload = function () {
    var real = getParameter("real");

    if (real == '') {
        $("#bl-amountSelect").val(10).prop("selected", true);
    }else {
        $("#bl-amountSelect").val(real).prop("selected", true);
    }

}

