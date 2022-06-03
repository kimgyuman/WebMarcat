var mainStartNum = 7;
var subStartNum = 7;
var viewStatus = '';
var mainResult = true;
var subResult = true;
var mainTab = true;
var subTab = false;
const home2 = document.getElementById('main-tab');

$('#main-tab').on("click", function () {
    if (mainTab != true) {
        mainTab = true;
        subTab = false;
    }
});

$('#sub-tab').on('click', function () {
    if (subTab != true) {
        subTab = true;
        mainTab = false;
    }
});


// $(window).scroll(function () {
//     if (homeTab == true) {
//         if ($(window).scrollTop() >= ($(document).height() - 1200) && mainResult == true) {
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
//                         mainStartNum += 6;
//                     } else {
//                         mainResult = false;
//                     }
//                 },
//                 error: function () {
//                     alert("Error");
//                 }
//             });
//         }
//     }
// });

function deleteBoard(data){
    var id = data;
    var cardElement = document.getElementById("boardCard");

    console.log("board seq :", id);
    console.log("cardElement :", cardElement);

    $.ajax({
        url:'/user/deleteMyBoard', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id': id}, //보낼 데이터
        success: function(data)   {
            alert("게시글 삭제 완료");
            cardElement.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });
}


