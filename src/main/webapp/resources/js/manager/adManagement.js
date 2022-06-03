function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function ativationBtn() {

    let ck = $("input[name=adCk]");
    var idarr = [];

    ck.each(function () {
        if (this.checked == true) {
            let tr = this.parentElement.parentElement;

            let id = tr.childNodes[3].textContent;

            idarr.push(id);

        }
    })

    $.ajax({
        url:'/admin/adActivation',
        type:'post',
        data:{'id':idarr},
        success: function(data) {
            alert("완료");
            location.href="/admin/adManagement";
        },
        error: function(err) {
            alert("실패")
        }
    });
}


function inactiveBtn() {

    let ck = $("input[name=adCk]");
    var idarr = [];

    ck.each(function () {
        if (this.checked == true) {
            let tr = this.parentElement.parentElement;

            let id = tr.childNodes[3].textContent;

            idarr.push(id);

        }
    })

    $.ajax({
        url:'/admin/adInactive',
        type:'post',
        data:{'id':idarr},
        success: function(data) {
            alert("완료");
            location.href="/admin/adManagement";
        },
        error: function(err) {
            alert("실패")
        }
    });
}

function deleteAd() {

    let ck = $("input[name=adCk]");

    var idarr = [];

    ck.each(function () {
        if (this.checked == true) {
            let tr = this.parentElement.parentElement;

            let id = tr.childNodes[3].textContent;

            idarr.push(id);

        }
    })

    if(confirm('선택한 광고를 삭제하시겠습니까?')) {
        $.ajax({
            url:'/admin/deleteAd',
            type:'post',
            data:{'id':idarr},
            success: function(data) {
                alert("광고가 삭제 되었습니다");
                location.href="/admin/adManagement";
            },
            error: function(err) {
                alert("실패")
            }
        });
    }


}

function AdSearch() {
    var search = document.getElementById("am-select").value;
    var keyword = document.getElementById("am-input").value;

    location.href="/admin/adManagement?page="+1+"&search="+search+"&keyword="+keyword;
}

$("#am-input").keyup(function(event) {
    if (event.keyCode === 13) {
        var search = document.getElementById("am-select").value;
        var keyword = document.getElementById("am-input").value;

        location.href="/admin/adManagement?page="+1+"&search="+search+"&keyword="+keyword;
    }
});

function amAmountChange() {

    var search = getParameter('search');
    var keyword = getParameter('keyword');

    var selectBox = document.getElementById('am-amountSelect');

    var value = selectBox.options[selectBox.selectedIndex].value;

    if (keyword != '') {
        location.href="/admin/adManagement?page="+1+"&real="+value+"&amount="+value+"&search="+search+"&keyword="+keyword;
    }else {
        location.href="/admin/adManagement?page="+1+"&real="+value+"&amount="+value;
    }

}

window.onload = function () {
    var real = getParameter("real");

    if (real == '') {
        $("#am-amountSelect").val(10).prop("selected", true);
    }else {
        $("#am-amountSelect").val(real).prop("selected", true);
    }

}
