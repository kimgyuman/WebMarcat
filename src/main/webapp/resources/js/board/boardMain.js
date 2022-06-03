var startNum;
var searchTypeParam = 'tw';
var categories = '';
var koreaAre = '';
var keyword = '';
var sellStatus = '';
var viewStatus = '';
var boardResult = true;
const jusoCode = document.getElementById('jusoCode');
const searchText = document.getElementById('searchText');
//
window.onload = function () {
    if (wish === true) {
        document.getElementById("wish_btn").style.color = 'red';
        wishCount++;
    }
    commentList();
};

$(document).ready(function () {
    startNum = 1;
    var searchType = {
        "startNum": startNum,
        "searchType": searchTypeParam,
        "koreaArea": koreaAre,
        "keyword": keyword,
        "viewStatus": viewStatus
    }
    $.ajax({
        type: "POST",
        url: "/board/searchBoards",
        data: JSON.stringify(searchType),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var appendItems = "";
            // $.each(data, function (index, item) {
            $.each(data, function (index, board) {
                appendItems += '<div class="card">';
                appendItems += '<div class="row">';
                appendItems += '<div class="col-md-5 wrapthumbnail">';
                appendItems += '<a href="/board/content?id=' + board.id + '">';
                appendItems += '<div  class="thumbnail" style="background-image:url(' + board.picture + ')">';
                appendItems += '</div>';
                appendItems += '</a>';
                appendItems += '</div>';
                appendItems += '<div class="col-md-7">';
                appendItems += '<div class="card-block">';
                appendItems += '<h2 class="card-title"><a href="/board/content?id=' + board.id + '">' + board.title + '</a></h2>';
                appendItems += '<h4 class="card-text"><a href="/board/content?id=' + board.id + '" style="text-decoration: none;">' + board.contents + '</a></h4>';
                appendItems += '<div class="wrapfooter">';
                appendItems += '<span class="meta-footer-thumb">';
                appendItems += '<a href="/user/userLink?id='+board.memberId+'"><img class="author-thumb" src="' + board.memberImages + '" alt="Sal"></a>';
                appendItems += '</span>';
                appendItems += '<span class="author-meta">';
                appendItems += '<span class="post-name" ><a href="/user/userLink?id='+board.memberId+'" style="text-decoration: none; color:black;">' + board.nickname + '</a></span><br/>';
                appendItems += '<span class="post-date">' + board.admNm + '</span><span class="dot"></span><span class="post-read">조회수  ' + board.viewCount + '</span>';
                appendItems += '</span>';
                appendItems += '<span class="mainHeart">';
                if (board.boardWishList === true) {// 사용자의 위시리스트에 해당 게시글이 있을 시
                    appendItems += '<button type="button" class="wish_btn readyWish" id="boardWish' + board.id + '" onclick="boardMainHeart(' + board.boardWishList + ',' + board.id + ')">';
                    appendItems += '<i class="fa-regular fa-heart"></i></button>';
                } else { // 사용자의 위시리스트에 해당 게시글이 없을 시
                    appendItems += '<button type="button" class="wish_btn noneWish" id="boardWish' + board.id + '" onclick="boardMainHeart(' + board.boardWishList + ',' + board.id + ')">';
                    appendItems += '<i class="fa-regular fa-heart"></i></button>';
                }
                appendItems += '</span>';
                // appendItems += '<span class="post-read-more"><a href="post.html" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewBox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>';
                appendItems += '</div>';
                appendItems += '</div>';
                appendItems += '</div>';
                appendItems += '</div>';
                appendItems += '</div>';
                appendItems += '</div>';
            });

            $("#contentsWrap").append(appendItems);
            startNum += 6;

        },
        error: function () {
            alert("Error");
        }
    });
});

function boardMainHeart(wish, boardid) {

    if (wish === false) {
        let result = plusWish(boardid);
        if (result === true) {
            document.getElementById("boardWish" + boardid).className = "wish_btn readyWish";
            document.getElementById("boardWish" + boardid).setAttribute("onClick", "boardMainHeart(" + true + "," + boardid + ")");
        }
    } else {
        let result = cancelWish(boardid);
        if (result === true) {
            document.getElementById("boardWish" + boardid).className = "wish_btn noneWish";
            document.getElementById("boardWish" + boardid).setAttribute("onClick", "boardMainHeart(" + false + "," + boardid + ")");
        }
    }
}

function plusWish(id) {// 위시리스트에 보내기
    let returnResult = false;
    $.ajax({
        type: "POST",
        url: "/user/board/wish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에 추가하였습니다");
                returnResult = true;
            } else {
                alert("로그인 후 이용해주세요");

            }
        },
        errors: function () {
            alert("error")
        }
    });
    return returnResult;
}

function cancelWish(id) {// 위시리스트에서 제거하기
    let returnResult = false;
    $.ajax({
        type: "POST",
        url: "/user/board/cancelWish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에서 제거하였습니다");
                returnResult = true;
            } else {
                alert("로그인 후 이용해주세요");

            }
        },
        errors: function () {
            alert("error")
        }
    });
    return returnResult;
}

$(window).scroll(function () {

    if ($(window).scrollTop() >= ($(document).height() - 1200) && boardResult === true) {
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
            url: "/board/searchBoards",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(searchType),
            dataType: "json",
            async: false,
            success: function (data) {
                if ($.isEmptyObject(data) === false) {
                    var appendItems = "";
                    $.each(data, function (index, board) {
                        // appendIt
                        appendItems += '<div class="card">';
                        appendItems += '<div class="row">';
                        appendItems += '<div class="col-md-5 wrapthumbnail">';
                        appendItems += '<a href="/board/content?id=' + board.id + '">';
                        appendItems += '<div class="thumbnail" style="background-image:url(' + board.picture + ')">';
                        appendItems += '</div>';
                        appendItems += '</a>';
                        appendItems += '</div>';
                        appendItems += '<div class="col-md-7">';
                        appendItems += '<div class="card-block">';
                        // appendItems += '<h2 className="card-title"><a href=\'/board/content?id=${board.id}\'>'+board.title+'</a>';
                        appendItems += '<h2 class="card-title"><a href="/board/content?id=' + board.id + '">' + board.title + '</a></h2>';
                        appendItems += '<h4 class="card-text"><a href="/board/content?id=' + board.id + '">' + board.contents + '</a></h4>';
                        appendItems += '<div class="wrapfooter">';
                        appendItems += '<span class="meta-footer-thumb">';
                        appendItems += '<a href="/user/userLink?id='+board.memberId+'"><img class="author-thumb" src="' + board.memberImages + '" alt="Sal"></a>';
                        appendItems += '</span>';
                        appendItems += '<span class="author-meta">';
                        appendItems += '<span class="post-name"><a href="/user/userLink?id='+board.memberId+'">' + board.nickname + '</a></span><br/>';
                        appendItems += '<span class="post-date">' + board.admNm + '</span><span class="dot"></span><span class="post-read">조회수  ' + board.viewCount + '</span>';
                        appendItems += '</span>';
                        appendItems += '<span class="mainHeart">';
                        if (board.boardWishList === true) {// 사용자의 위시리스트에 해당 게시글이 있을 시
                            appendItems += '<button type="button" class="wish_btn readyWish" id="boardWish' + board.id + '" onclick="boardMainHeart(' + board.boardWishList + ',' + board.id + ')">';
                            appendItems += '<i class="fa-regular fa-heart"></i></button>';
                        } else { // 사용자의 위시리스트에 해당 게시글이 없을 시
                            appendItems += '<button type="button" class="wish_btn noneWish" id="boardWish' + board.id + '" onclick="boardMainHeart(' + board.boardWishList + ',' + board.id + ')">';
                            appendItems += '<i class="fa-regular fa-heart"></i></button>';
                        }
                        appendItems += '</span>'
                        // appendItems += '<span class="post-read-more"><a href="post.html" title="Read Story"><svg class="svgIcon-use" width="25" height="25" viewBox="0 0 25 25"><path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path></svg></a></span>';
                        appendItems += '</div>';
                        appendItems += '</div>';
                        appendItems += '</div>';
                        appendItems += '</div>';
                        appendItems += '</div>';
                    });
                    $("#contentsWrap").append(appendItems);
                    startNum += 6;
                } else {
                    boardResult = false;
                }
            },
            error: function () {
                alert("Error!!");
            }
        });
    }
});

function search() {
    boardResult = true;

    startNum = 1;
    var tempSearchType = $(".default_option").text();
    if (tempSearchType === "All") {
        searchTypeParam = 'tw';
    } else if (tempSearchType === "제목") {
        searchTypeParam = 't';
    } else if (tempSearchType === "작성자") {
        searchTypeParam = 'w';
    }
    // categories = firstCate;
    // koreaAre = jusoCode.value;
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
    // alert(JSON.stringify(searchType))
    $.ajax({
        type: "POST",
        url: "/board/searchBoards",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(searchType),
        dataType: "json",
        async: false,
        success: function (data) {
            $("#contentsWrap").empty();
            if ($.isEmptyObject(data) === false) {
                var appendItems = "";
                $.each(data, function (index, board) {
                    // appendIt
                    appendItems += '<div class="card">';
                    appendItems += '<div class="row">';
                    appendItems += '<div class="col-md-5 wrapthumbnail">';
                    appendItems += '<a href="/board/content?id=' + board.id + '">';
                    appendItems += '<div class="thumbnail" style="background-image:url(' + board.picture + ')">';
                    appendItems += '</div>';
                    appendItems += '</a>';
                    appendItems += '</div>';
                    appendItems += '<div class="col-md-7">';
                    appendItems += '<div class="card-block">';
                    appendItems += '<h2 class="card-title"><a href="/board/content?id=' + board.id + '">' + board.title + '</a></h2>';
                    appendItems += '<h4 class="card-text"><a href="/board/content?id=' + board.id + '">' + board.contents + '</a></h4>';
                    appendItems += '<div class="wrapfooter">';
                    appendItems += '<span class="meta-footer-thumb">';
                    appendItems += '<a href="/user/userLink?id='+board.memberId+'"><img class="author-thumb" src="' + board.memberImages + '" alt="Sal"></a>';
                    appendItems += '</span>';
                    appendItems += '<span class="author-meta">';
                    appendItems += '<span class="post-name"><a href="/user/userLink?id='+board.memberId+'">' + board.nickname + '<a/></span><br/>';
                    appendItems += '<span class="post-date">' + board.admNm + '</span><span class="dot"></span><span class="post-read">조회수  ' + board.viewCount + '</span>';
                    appendItems += '</span>';
                    appendItems += '<span class="mainHeart">';
                    if (board.boardWishList === true) {// 사용자의 위시리스트에 해당 게시글이 있을 시
                        appendItems += '<button type="button" class="wish_btn readyWish" id="boardWish' + board.id + '" onclick="boardMainHeart(' + board.boardWishList + ',' + board.id + ')">';
                        appendItems += '<i class="fa-regular fa-heart"></i></button>';
                    } else { // 사용자의 위시리스트에 해당 게시글이 없을 시
                        appendItems += '<button type="button" class="wish_btn noneWish" id="boardWish' + board.id + '" onclick="boardMainHeart(' + board.boardWishList + ',' + board.id + ')">';
                        appendItems += '<i class="fa-regular fa-heart"></i></button>';
                    }
                    appendItems += '</span>'
                    appendItems += '</div>';
                    appendItems += '</div>';
                    appendItems += '</div>';
                    appendItems += '</div>';
                    appendItems += '</div>';
                });
                $("#contentsWrap").append(appendItems);
                startNum += 5;
            } else {
                boardResult = false;
            }
        },
        error: function () {
            alert("Error");
        }
    });
}

$(document).ready(function () {
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

$(".dropdown ul li").click(function () {
    var text = $(this).text();
    $(".default_option").text(text);
    $(".dropdown ul").removeClass("active");
});


// 주소 search area
function openJuso() {
    var url = "market/searchJuso";
    window.open(url, "주소찾기", 'width=500,height=400, scrollbars=no, resizable=no',);
}

var inputJuso = document.getElementById("textJuso");

function jusoReset() {
    inputJuso.value = '';
    jusoCode.value = '';

}
