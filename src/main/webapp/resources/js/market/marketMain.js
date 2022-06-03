var startNum;
var searchTypeParam = 'tw';
var categories = '';
var koreaAre = '';
var keyword = '';
var sellStatus = '';
var viewStatus = '';
var goodsResult = true;
const jusoCode = document.getElementById('jusoCode');
const searchText = document.getElementById('searchText');


$(document).ready(function () { // 시작시 페이징을 통해 게시글 불러오기
    startNum = 1;
    var searchType = {
        "startNum": startNum,
        "searchType": searchTypeParam,
        "categories": categories,
        "koreaArea": koreaAre,
        "keyword": keyword,
        "sellStatus": sellStatus,
        "viewStatus": viewStatus
    }
    $.ajax({
        type: "POST",
        url: "/market/searchGoods",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(searchType),
        dataType: "json",
        success: function (data) {
            if ($.isEmptyObject(data) === false) {
            var appendItems = "";
            $.each(data, function (index, item) {// 되돌아온 데이터를 받아서 게시글 (카드) 생성

                appendItems += '<div class="card">';
                appendItems += '<a href="/market/marketGoods?id=' + item.goodsId + '">';
                appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                appendItems += '</a>';
                appendItems += '<div class="card-block">';
                appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods?id=' + item.goodsId + '">' + item.title + '</a></h2>';
                appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                appendItems += '<div class="wrapfooter">';
                appendItems += '<span class="meta-footer-thumb">';
                appendItems += '<a href="/user/userLink?id=' + item.nickId + '"><img class="author-thumb" src="' + item.nickPicture + '" alt=""></a>';
                appendItems += '</span>';
                appendItems += '<span class="author-meta">';
                appendItems += '<span class="post-name"><a href="/user/userLink?id=' + item.nickId + '">' + item.nick + '</a></span><br/>';
                appendItems += '<span class="post-date">' + item.createTime + '</span>';
                appendItems += '</span>';
                appendItems += '<span class="mainHeart">';
                if(item.wishList === true){// 사용자의 위시리스트에 해당 게시글이 있을 시
                    appendItems += '<button type="button" class="wish_btn readyWish" id="wishPlus'+item.goodsId+'" onclick="mainHeart('+item.wishList+','+item.goodsId+')">';
                    appendItems += '<i class="fa-regular fa-heart"></i></button>';
                }else {// 사용자의 위시리스트에 해당 게시글이 없을 시
                    appendItems += '<button type="button" class="wish_btn noneWish" id="wishPlus'+item.goodsId+'" onclick="mainHeart('+item.wishList+','+item.goodsId+')">';
                    appendItems += '<i class="fa-regular fa-heart"></i></button>';
                }
                appendItems += '</span>'
                appendItems += '</div>';
                appendItems += '</div>';
                appendItems += '</div>';

            });
            $("#contentsWrap").append(appendItems);// 화면에 추가

            startNum += 12;
            } else {
                goodsResult = false;
            }
        },
        error: function () {
            console.log("Error");
        }
    });
});

function mainHeart(wish, goodsId) {
    if (wish === false) {
        let result = plusWish(goodsId);
        if (result === true) {
            let param = 'mainHeart(true,' + goodsId + ')';
            document.getElementById("wishPlus" + goodsId).className = "wish_btn readyWish";
            document.getElementById("wishPlus" + goodsId).setAttribute("onClick", param);
        }
    } else if (wish === true) {
        let result = cancleWish(goodsId);
        if (result === true) {
            let param = 'mainHeart(false,' + goodsId + ')';
            document.getElementById("wishPlus" + goodsId).className = "wish_btn noneWish";
            document.getElementById("wishPlus" + goodsId).setAttribute("onClick", param);
        }
    }
}

function plusWish(id) {// 위시리스트에 보내기
    let returnResult = false;
    $.ajax({
        type: "POST",
        url: "/user/market/wish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에 추가하였습니다");
                returnResult = true;
            } else if (data.result === "300"){
                alert("정상적인 방법으로 접근해주세요!")
            }else if(data.result === "400") {
                alert("정지된 게시글입니다.");
            } else if (data.result === "500") {
                alert("이미 존재하는 위시리스트입니다.");
                let param = 'mainHeart(true,' + id + ')';
                document.getElementById("wishPlus" + id).className = "wish_btn readyWish";
                document.getElementById("wishPlus" + id).setAttribute("onClick", param);
            }else{
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            console.log("error")
        }
    });
    return returnResult;
}

function cancleWish(id) {// 위시리스트에서 제거하기
    let returnResult = false;
    $.ajax({
        type: "POST",
        url: "/user/market/cancelWish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에서 제거하였습니다");
                returnResult = true;
            } else if (data.result === "300"){
                alert("정상적인 방법으로 접근해주세요!")
            }else if(data.result === "400") {
                alert("정지된 게시글입니다.");
            }else if (data.result === "500") {
                alert("이미 지워진 위시리스트 입니다.");
                let param = 'mainHeart(false,' + id + ')';
                document.getElementById("wishPlus" + id).className = "wish_btn noneWish";
                document.getElementById("wishPlus" + id).setAttribute("onClick", param);
            } else{
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            console.log("error")
        }
    });
    return returnResult;
}

$(window).scroll(function () { // 스크롤을 통한 페이징 처리

    if ($(window).scrollTop() >= ($(document).height() - 1200) && goodsResult === true) { // 스크롤값
        var searchType = {
            "startNum": startNum,
            "searchType": searchTypeParam,
            "categories": categories,
            "koreaArea": koreaAre,
            "keyword": keyword,
            "sellStatus": sellStatus,
            "viewStatus": viewStatus
        }
        $.ajax({
            type: "POST",
            url: "/market/searchGoods",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(searchType),
            dataType: "json",
            async: false,
            success: function (data) {
                if ($.isEmptyObject(data) === false) {
                    var appendItems = "";
                    $.each(data, function (index, item) {
                        appendItems += '<div class="card">';
                        appendItems += '<a href="/market/marketGoods?id=' + item.goodsId + '">';
                        appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                        appendItems += '</a>';
                        appendItems += '<div class="card-block">';
                        appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods?id=' + item.goodsId + '">' + item.title + '</a></h2>';
                        appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                        appendItems += '<div class="wrapfooter">';
                        appendItems += '<span class="meta-footer-thumb">';
                        appendItems += '<a href="/user/userLink?id=' + item.nickId + '"><img class="author-thumb" src="' + item.nickPicture + '" alt=""></a>';
                        appendItems += '</span>';
                        appendItems += '<span class="author-meta">';
                        appendItems += '<span class="post-name"><a href="/user/userLink?id=' + item.nickId + '">' + item.nick + '</a></span><br/>';
                        appendItems += '<span class="post-date">' + item.createTime + '</span>';
                        appendItems += '</span>';
                        appendItems += '<span class="mainHeart">';
                        if(item.wishList === true){
                            appendItems += '<button type="button" class="wish_btn readyWish" id="wishPlus'+item.goodsId+'" onclick="mainHeart('+item.wishList+','+item.goodsId+')">';
                            appendItems += '<i class="fa-regular fa-heart"></i></button>';
                        }else {
                            appendItems += '<button type="button" class="wish_btn noneWish" id="wishPlus'+item.goodsId+'" onclick="mainHeart('+item.wishList+','+item.goodsId+')">';
                            appendItems += '<i class="fa-regular fa-heart"></i></button>';
                        }
                        appendItems += '</span>'
                        appendItems += '</div>';
                        appendItems += '</div>';
                        appendItems += '</div>';
                    });
                    $("#contentsWrap").append(appendItems);
                    startNum += 12;// 지속적으로 추가하여 끝까지 나오도록
                } else {
                    goodsResult = false;
                }
            },
            error: function () {
                console.log("Error");
            }
        });
    }
});

function search() { //검색시 실행
    goodsResult = true;

    startNum = 1;
    var tempSearchType = $(".default_option").text();
    if (tempSearchType === "All") {
        searchTypeParam = 'tw';
    } else if (tempSearchType === "제목") {
        searchTypeParam = 't';
    } else if (tempSearchType === "작성자") {
        searchTypeParam = 'w';
    }// 해당되는 검색의 파라미터를 잡아서 추가해줄 부분
    categories = firstCate;
    koreaAre = jusoCode.value;
    keyword = searchText.value;

    var searchType = {
        "startNum": startNum,
        "searchType": searchTypeParam,
        "categories": categories,
        "koreaArea": koreaAre,
        "keyword": keyword,
        "sellStatus": sellStatus,
        "viewStatus": viewStatus
    }

    $.ajax({
        type: "POST",
        url: "/market/searchGoods",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(searchType),
        dataType: "json",
        async: false,
        success: function (data) {
            $("#contentsWrap").empty();
            if ($.isEmptyObject(data) === false) {
                var appendItems = "";
                $.each(data, function (index, item) { //검색 후 값을 받아와서 화면을 지우고 뿌려줌
                    appendItems += '<div class="card">';
                    appendItems += '<a href="/market/marketGoods?id=' + item.goodsId + '">';
                    appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                    appendItems += '</a>';
                    appendItems += '<div class="card-block">';
                    appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods?id=' + item.goodsId + '">' + item.title + '</a></h2>';
                    appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                    appendItems += '<div class="wrapfooter">';
                    appendItems += '<span class="meta-footer-thumb">';
                    appendItems += '<a href="/user/userLink?id=' + item.nickId + '"><img class="author-thumb" src="' + item.nickPicture + '" alt=""></a>';
                    appendItems += '</span>';
                    appendItems += '<span class="author-meta">';
                    appendItems += '<span class="post-name"><a href="/user/userLink?id=' + item.nickId + '">' + item.nick + '</a></span><br/>';
                    appendItems += '<span class="post-date">' + item.createTime + '</span>';
                    appendItems += '</span>';
                    appendItems += '</div>';
                    appendItems += '</div>';
                    appendItems += '</div>';
                });
                $("#contentsWrap").append(appendItems);
                startNum += 12;
            } else {
                goodsResult = false;
            }
        },
        error: function () {
            console.log("Error");
        }
    });
}

$(document).ready(function () {// 검색부 All 에 잡혀있는 dropdown
    var dropdownActive = false;
    $(".default_option").click(function () {
        if (dropdownActive === false) {
            $(".dropdown ul").addClass("active");
            dropdownActive = true;
        } else {
            $(".dropdown ul").removeClass("active");
            dropdownActive = false;
        }
    });
});

$(".dropdown ul li").click(function () { // 각 all에 잡혀있는 dropdown 설정값과 텍스트부 값
    var text = $(this).text();
    $(".default_option").text(text);
    $(".dropdown ul").removeClass("active");
});

// 카테고리 더보기
let cateDiv = document.querySelector('#main_cate_area');
let areaDiv = document.querySelector('#area_select');
let searchCate = document.querySelector('#searchCate');

function OpenClose() {
    if (cateDiv.style.display === 'none') {
        cateDiv.style.display = 'block';
    } else {
        cateDiv.style.display = 'none';
    }
}

let count = 0;
let firstCate = '';
let reCate = '';

function CateSearch(cateVal, cateId) { // 카테고리 값 설정
    if(count === 0){
        let selectedCate = document.getElementById(cateId);
        count++;
        searchCate.value = cateId;
        selectedCate.style.backgroundColor = '#378de5';
        return firstCate = cateId;
    }else {
        let returnCate = document.getElementById(firstCate);
        returnCate.style.backgroundColor = '#9fa3b1';
        searchCate.value = "";
        count--;
        return firstCate = '';
    }
}

// 주소 search area
function openJuso() {// 주소검색시 주소창 오픈
    var url = "/market/searchJuso";
    window.open(url, "주소찾기", 'width=500,height=400, scrollbars=no, resizable=no');
}

var inputJuso = document.getElementById("textJuso");
function jusoReset() {// 주소 x버튼 클릭시 value 초기화
    inputJuso.value = '';
    jusoCode.value = '';
}