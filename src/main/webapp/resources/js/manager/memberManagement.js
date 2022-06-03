function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

$(".m-btn4").on("click",function() {

    var row = $(this).closest('tr');

    var td = row.find('td:eq(0)');

    var id = td.text();
    console.log(id);

    location.href="/admin/memberProfile?id="+id;
});

function mSearch() {
    var search = document.getElementById("mm-select").value;
    var keyword = document.getElementById("mm-input").value;

    location.href="/admin/memberManagement?page="+1+"&search="+search+"&keyword="+keyword;
}

$("#mm-input").keyup(function(event) {
    if (event.keyCode === 13) {
        var search = document.getElementById("mm-select").value;
        var keyword = document.getElementById("mm-input").value;

        location.href="/admin/memberManagement?page="+1+"&search="+search+"&keyword="+keyword;
    }
});

function mmAmountChange() {

    var search = getParameter('search');
    var keyword = getParameter('keyword');

    var selectBox = document.getElementById('mm-amountSelect');

    var value = selectBox.options[selectBox.selectedIndex].value;

    if (keyword != '') {
        location.href="/admin/memberManagement?page="+1+"&real="+value+"&amount="+value+"&search="+search+"&keyword="+keyword;
    }else {
        location.href="/admin/memberManagement?page="+1+"&real="+value+"&amount="+value;
    }

}

window.onload = function () {
    var real = getParameter("real");

    if (real == '') {
        $("#mm-amountSelect").val(10).prop("selected", true);
    }else {
        $("#mm-amountSelect").val(real).prop("selected", true);
    }

}

