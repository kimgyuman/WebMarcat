// var homeStartNum = 13;
// var profile1TabStartNum = 13;
// var viewStatus = '';
// var homeResult = true;
// var profile1Result = true;
// var profile2Result = true;
// var profile3Result = true;
// var homeTab = true;
// var profile1Tab = false;
// var profile2Tab = false;
// var profile3Tab = false;
// const home2 = document.getElementById('home2-tab');
//
// $('#home2-tab').on("click", function () {
//     if (homeTab != true) {
//         homeTab = true;
//         profile1Tab = false;
//         profile2Tab = false;
//         profile3Tab = false;
//     }
//     console.log(homeTab);
//     console.log(profile1Tab);
//     console.log(profile2Tab);
//     console.log(profile3Tab);
// });
// $('#profile1-tab').on('click', function () {
//     if (profile1Tab != true) {
//         profile1Tab = true;
//         homeTab = false;
//         profile2Tab = false;
//         profile3Tab = false;
//     }
//     console.log(homeTab);
//     console.log(profile1Tab);
//     console.log(profile2Tab);
//     console.log(profile3Tab);
// });
// $('#profile2-tab').on('click', function () {
//     if (profile2Tab != true) {
//         profile2Tab = true;
//         homeTab = false;
//         profile1Tab = false;
//         profile3Tab = false;
//     }
//     console.log(homeTab);
//     console.log(profile1Tab);
//     console.log(profile2Tab);
//     console.log(profile3Tab);
// });
// $('#profile3-tab').on('click', function () {
//     if (profile3Tab != true) {
//         profile3Tab = true;
//         homeTab = false;
//         profile1Tab = false;
//         profile2Tab = false;
//     }
//     console.log(homeTab);
//     console.log(profile1Tab);
//     console.log(profile2Tab);
//     console.log(profile3Tab);
// });
//
//
// $(window).scroll(function () {
//     if (homeTab == true) {
//         if ($(window).scrollTop() >= ($(document).height() - 1200) && homeResult == true) {
//             var searchType = {
//                 "startNum": homeStartNum,
//                 "sellStatus": sellStatus,
//                 "viewStatus": viewStatus
//             }
//             $.ajax({
//                 type: "POST",
//                 url: "/user/goodsHistory/sellingList",
//                 contentType: "application/json; charset=utf-8",
//                 data: JSON.stringify(searchType),
//                 dataType: "json",
//                 async: false,
//                 success: function (data) {
//                     if ($.isEmptyObject(data) == false) {
//                         var appendItems = "";
//                         $.each(data, function (index, item) {
//                             appendItems += '<div class="card">';
//                             appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
//                             appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
//                             appendItems += '</a>';
//                             appendItems += '<div class="card-block innerWrap">';
//                             appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
//                             appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
//                             appendItems += '<div class="wrapfooter dayBottom">';
//                             appendItems += '<div class="row justify-content-center">';
//                             appendItems += '<a href="/"><button type="button" class="btn btn-sm btn-primary" name="sendCkPhone">수정하기</button></a>';
//                             appendItems += '<a href="/"><button type="button" class="deleteMyGoods btn btn-sm btn-primary btn-danger ml-4" name="sendCkPhone">삭제하기</button></a>';
//                             appendItems += '</div>';
//                             appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>'
//                             appendItems += '</span>';
//                             appendItems += '</div>';
//                             appendItems += '</div>';
//                             appendItems += '</div>';
//                         });
//                         $("#contentsWrap").append(appendItems);
//                         homeStartNum += 12;
//                     } else {
//                         homeResult = false;
//                     }
//                 },
//                 error: function () {
//                     alert("Error");
//                 }
//             });
//         }
//     } else if (profile1Tab == true) {
//         if ($(window).scrollTop() >= ($(document).height() - 1200) && profile1Result == true) {
//             var searchType = {
//                 "startNum": profile1TabStartNum,
//                 "viewStatus": viewStatus
//             }
//             $.ajax({
//                 type: "POST",
//                 url: "/user/goodsHistory/notifyHistory",
//                 contentType: "application/json; charset=utf-8",
//                 data: JSON.stringify(searchType),
//                 dataType: "json",
//                 async: false,
//                 success: function (data) {
//                     if ($.isEmptyObject(data) == false) {
//                         var appendItems = "";
//                         $.each(data, function (index, item) {
//
//                             if (item.requestStatus == '수락됨') {
//                                 appendItems += '<div class="card">';
//                                 appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
//                                 appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
//                                 appendItems += '</a>';
//                                 appendItems += '<div class="card-block innerRequestWrap">';
//                                 appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
//                                 appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
//                                 appendItems += '<div class="wrapfooter dayBottom">';
//                                 appendItems += '<div class="authorSize row justify-content-center">요청자 :&nbsp <a href="/user/' + item.requestMemberId + '">' + item.nick + '</a></div>';
//                                 appendItems += '<div class="row justify-content-center mt-1">';
//                                 appendItems += '<h3>'+ item.requestStatus +'</h3>';
//                                 appendItems += '<button type="button" class="deleteAccept btn btn-sm btn-primary btn-danger ml-4" name="sendCkPhone">취소하기</button>'
//                                 appendItems += '<input type="hidden" name="code" value="' + item.requestId + '">';
//                                 appendItems += '</div>';
//                                 appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>';
//                                 appendItems += '</div>';
//                                 appendItems += '</div>';
//                                 appendItems += '</div>';
//                             }
//
//                             if (item.requestStatus == '요청중') {
//                                 appendItems += '<div class="card">';
//                                 appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
//                                 appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
//                                 appendItems += '</a>';
//                                 appendItems += '<div class="card-block innerRequestWrap">';
//                                 appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
//                                 appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
//                                 appendItems += '<div class="wrapfooter dayBottom">';
//                                 appendItems += '<div class="authorSize row justify-content-center">요청자 :&nbsp <a href="/user/' + item.requestMemberId + '">' + item.nick + '</a></div>';
//                                 appendItems += '<div class="row justify-content-center mt-1">';
//                                 appendItems += '<button type="button" class="requestAccept btn btn-sm btn-primary btn-danger ml-4" name="sendCkPhone">수락하기</button>';
//                                 appendItems += '<button type="button" class="requestRefuse btn btn-sm btn-primary btn-danger ml-4" name="sendCkPhone">거절하기</button>';
//                                 appendItems += '<input type="hidden" name="code" value="' + item.requestId + '">';
//                                 appendItems += '</div>';
//                                 appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>';
//                                 appendItems += '</div>';
//                                 appendItems += '</div>';
//                                 appendItems += '</div>';
//                             }
//
//                         });
//                         $("#contentsWrap1").append(appendItems);
//                         profile1TabStartNum += 12;
//                     } else {
//                         profile1Result = false;
//                     }
//                 },
//                 error: function () {
//                     alert("Error");
//                 }
//             });
//         }
//     }
// });
var element = document.querySelector("#notifyWrap");

var element2 = document.querySelector("#notifyWrap2");

$(".notifyView").on("click",function() {
    while (element.hasChildNodes()) {	// 부모노드가 자식이 있는지 여부를 알아낸다
        element.removeChild(
            element.firstChild
        );
    }
    var goodsId = this.parentNode.childNodes[5].value;

    console.log(goodsId);

    $.ajax({
        url:'/user/findNotifyCount', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'goodsId': goodsId}, //보낼 데이터
        success: function(data) {
                var appendItems = "";
                appendItems += '<table class="pop-table">';
                appendItems += '<tr>';
                appendItems += '<th>' + '신고 사유' + '</th>';
                appendItems += '<th>' + '신고 횟수' + '</th>';
                appendItems += '<tr>';
                $.each(data, function (index, item) {
                    appendItems += '<tr>';
                    appendItems += '<td>' + item.contents + '</td>';
                    appendItems += '<td>' + item.cnt + '</td>';
                    appendItems += '<tr>';
                });
                appendItems += '</table>';
                $("#notifyWrap").append(appendItems);

                $("#popup").fadeIn();

        },
        error: function(err) {
            // alert("실패")
        }
    });
});

$(".boardNotifyView").on("click",function() {
    while (element2.hasChildNodes()) {	// 부모노드가 자식이 있는지 여부를 알아낸다
        element2.removeChild(
            element2.firstChild
        );
    }
    var boardId = this.parentNode.childNodes[5].value;

    console.log(boardId);

    $.ajax({
        url:'/user/findBoardNotifyCount', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'boardId': boardId}, //보낼 데이터
        success: function(data) {
            var appendItems = "";
            appendItems += '<table class="pop-table">';
            appendItems += '<tr>';
            appendItems += '<th>' + '신고 사유' + '</th>';
            appendItems += '<th>' + '신고 횟수' + '</th>';
            appendItems += '<tr>';
            $.each(data, function (index, item) {
                appendItems += '<tr>';
                appendItems += '<td>' + item.contents + '</td>';
                appendItems += '<td>' + item.cnt + '</td>';
                appendItems += '<tr>';
            });
            appendItems += '</table>';
            $("#notifyWrap2").append(appendItems);

            $("#popup2").fadeIn();

        },
        error: function(err) {
            // alert("실패")
        }
    });
});

function popClose() {
    $("#popup").fadeOut();
}

function popClose2() {
    $("#popup2").fadeOut();
}

$(".deleteStopGoods").on("click",function() {
    var goodsId = this.parentNode.childNodes[5].value;

    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(goodsId);
    console.log(parentNode);

    $.ajax({
        url:'/user/deleteMyGoods', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id': goodsId}, //보낼 데이터
        success: function(data) {
            alert("마켓글 삭제 완료")
            parentNode.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });
});

$(".deleteStopBoard").on("click",function() {
    var boardId = this.parentNode.childNodes[5].value;

    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(boardId);
    console.log(parentNode);

    $.ajax({
        url:'/user/deleteMyBoard', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'boardId': boardId}, //보낼 데이터
        success: function(data) {
            alert("게시글 삭제 완료")
            parentNode.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });
});