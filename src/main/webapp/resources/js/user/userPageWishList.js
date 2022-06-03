var homeStartNum = 13;
var profile1TabStartNum = 13;
var profile2TabStartNum = 13;
var viewStatus = '';
var homeResult = true;
var profile1Result = true;
var homeTab = true;
var profile1Tab = false;
var profile2Tab = false;
const home2 = document.getElementById('home2-tab');

$('#home2-tab').on("click", function () {
    if (homeTab != true) {
        homeTab = true;
        profile1Tab = false;
        profile2Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
});
$('#profile1-tab').on('click', function () {
    if (profile1Tab != true) {
        profile1Tab = true;
        homeTab = false;
        profile2Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
});
$('#profile2-tab').on('click', function () {
    if (profile2Tab != true) {
        profile2Tab = true;
        homeTab = false;
        profile1Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
});



$(window).scroll(function () {
    if (homeTab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 1200) && homeResult == true) {
            var searchType = {
                "startNum": homeStartNum,
                "viewStatus": viewStatus
            }
            $.ajax({
                type: "POST",
                url: "/user/wishList/myWantedList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<div class="card">';
                            appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
                            appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                            appendItems += '</a>';
                            appendItems += '<div class="card-block innerWrap">';
                            appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                            appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                            appendItems += '<div class="wrapfooter dayBottom">';
                            appendItems += '<div class="row justify-content-center">';
                            appendItems += '<button type="button" class="deleteWish btn btn-sm btn-primary btn-danger" name="sendCkPhone">찜 취소하기</button>';
                            appendItems += '</div>';
                            appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>'
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                        });
                        $("#contentsWrap").append(appendItems);
                        homeStartNum += 12;
                    } else {
                        homeResult = false;
                    }
                },
                error: function () {
                    alert("Error");
                }
            });
          }
       }else if (profile1Tab == true) {
            if ($(window).scrollTop() >= ($(document).height() - 1200) && profile1TabStartNum == true) {
                var searchType = {
                    "startNum": profile1TabStartNum,
                    "viewStatus": viewStatus
                }
                $.ajax({
                    type: "POST",
                    url: "/user/wishList/myWantedList",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(searchType),
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        if ($.isEmptyObject(data) == false) {
                            var appendItems = "";
                            $.each(data, function (index, item) {
                                appendItems += '<div class="card">';
                                appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
                                appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                                appendItems += '</a>';
                                appendItems += '<div class="card-block innerWrap">';
                                appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                                appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                                appendItems += '<div class="wrapfooter dayBottom">';
                                appendItems += '<div class="row justify-content-center">';
                                appendItems += '<button type="button" class="deleteWish btn btn-sm btn-primary btn-danger" name="sendCkPhone">찜 취소하기</button>';
                                appendItems += '</div>';
                                appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>'
                                appendItems += '</div>';
                                appendItems += '</div>';
                                appendItems += '</div>';
                            });
                            $("#contentsWrap").append(appendItems);
                            homeStartNum += 12;
                        } else {
                            homeResult = false;
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
       } else if (profile2Tab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 1200) && profile2Result == true) {
            var searchType = {
                "startNum": profile2TabStartNum,
                "sellStatus": sellStatus,
                "viewStatus": viewStatus,
            }
            $.ajax({
                type: "POST",
                url: "/user/wishList/myRequestList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<div class="card">';
                            appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
                            appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                            appendItems += '</a>';
                            appendItems += '<div class="card-block innerWrap">';
                            appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                            appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                            appendItems += '<div class="wrapfooter dayBottom">';
                            appendItems += '<div class="row justify-content-center">';
                            appendItems += '<button type="button" class="deleteRequest btn btn-sm btn-primary btn-danger" name="sendCkPhone">요청 취소하기</button>';
                            appendItems += '</div>';
                            appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>'
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                        });
                        $("#contentsWrap1").append(appendItems);
                        profile2TabStartNum += 12;
                    } else {
                        profile1Result = false;
                    }
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    }
});

$(".deleteWish").on("click",function() {
    var id = this.parentNode.childNodes[3].value;

    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(parentNode);

    console.log(id);

    $.ajax({
        url:'/user/deleteWish', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id':id}, //보낼 데이터
        success: function(data) {
            alert("완료");
            parentNode.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });
});

function deleteBoard(data){
    var id = data;
    var card = document.getElementById("boardWishCard");

    $.ajax({
        url:'/user/deleteBoardWish', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id':id}, //보낼 데이터
        success: function(data) {
            alert("완료");
            card.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });

}


$(".deleteRequest").on("click",function() {
    var id = this.parentNode.childNodes[3].value;

    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(parentNode);

    console.log(id);

    $.ajax({
        url:'/user/deleteRequest', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id':id}, //보낼 데이터
        success: function(data) {
            alert("완료");
            parentNode.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });
});
