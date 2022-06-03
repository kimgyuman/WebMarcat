var homeStartNum = 13;
var profile1TabStartNum = 13;
var profile2TabStartNum = 13;
var profile3TabStartNum = 13;
var sellStatus = 'SELL';
var viewStatus = '';
var homeResult = true;
var profile1Result = true;
var profile2Result = true;
var profile3Result = true;
var homeTab = true;
var profile1Tab = false;
var profile2Tab = false;
var profile3Tab = false;
const home2 = document.getElementById('home2-tab');

$('#home2-tab').on("click", function () {
    if (homeTab != true) {
        homeTab = true;
        profile1Tab = false;
        profile2Tab = false;
        profile3Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
    console.log(profile3Tab);
});
$('#profile1-tab').on('click', function () {
    if (profile1Tab != true) {
        profile1Tab = true;
        homeTab = false;
        profile2Tab = false;
        profile3Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
    console.log(profile3Tab);
});
$('#profile2-tab').on('click', function () {
    if (profile2Tab != true) {
        profile2Tab = true;
        homeTab = false;
        profile1Tab = false;
        profile3Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
    console.log(profile3Tab);
});
$('#profile3-tab').on('click', function () {
    if (profile3Tab != true) {
        profile3Tab = true;
        homeTab = false;
        profile1Tab = false;
        profile2Tab = false;
    }
    console.log(homeTab);
    console.log(profile1Tab);
    console.log(profile2Tab);
    console.log(profile3Tab);
});


$(window).scroll(function () {
    if (homeTab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 1200) && homeResult == true) {
            var searchType = {
                "startNum": homeStartNum,
                "sellStatus": sellStatus,
                "viewStatus": viewStatus
            }
            $.ajax({
                type: "POST",
                url: "/user/goodsHistory/sellingList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<div class="card" id="sellingCard">';
                            appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
                            appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                            appendItems += '</a>';
                            appendItems += '<div class="card-block innerWrap">';
                            appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                            appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                            appendItems += '<div class="wrapfooter dayBottom">';
                            appendItems += '<div class="row justify-content-center">';
                            appendItems += '<a href="/user/market/GoodsRemake/?id=' +  item.goodsId + '"><button type="button" class="btn btn-sm btn-primary" name="sendCkPhone">수정하기</button></a>';
                            appendItems += '<a href="/"><button type="button" class="deleteMyGoods btn btn-sm btn-primary btn-danger ml-4" name="sendCkPhone">삭제하기</button></a>';
                            appendItems += '</div>';
                            appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>'
                            appendItems += '</span>';
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
    } else if (profile1Tab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 1200) && profile1Result == true) {
            var searchType = {
                "startNum": profile1TabStartNum,
                "viewStatus": viewStatus
            }
            $.ajax({
                type: "POST",
                url: "/user/goodsHistory/requestList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {

                            if (item.requestStatus == '수락됨') {
                                appendItems += '<div class="card" id="reCard">';
                                appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
                                appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                                appendItems += '</a>';
                                appendItems += '<div class="card-block innerRequestWrap">';
                                appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                                appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                                appendItems += '<div class="wrapfooter dayBottom">';
                                appendItems += '<div class="authorSize row justify-content-center">거래 상대 :&nbsp <a href="/user/' + item.requestMemberId + '">' + item.nick + '</a></div>';
                                appendItems += '<div class="row justify-content-center mt-3">';
                                appendItems += '<button type="button" class="soldAccept btn btn-sm btn-primary mr-4" name="sendCkPhone">판매완료</button>';
                                appendItems += '<button type="button" class="deleteAccept btn btn-sm btn-primary btn-danger mr-4" name="sendCkPhone">취소하기</button>';
                                appendItems += '<input type="hidden" name="code" value="' + item.requestId + '">';
                                appendItems += '<input type="hidden" name="code2" value="' + item.goodsId + '">';
                                appendItems += '</div>';
                                appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>';
                                appendItems += '</div>';
                                appendItems += '</div>';
                                appendItems += '</div>';
                            }

                            if (item.requestStatus == '요청중') {
                            appendItems += '<div class="card">';
                            appendItems += '<a href="/market/marketGoods/' + item.goodsId + '">';
                            appendItems += '<img class="img-fluid" src="' + item.picture + '" alt="">';
                            appendItems += '</a>';
                            appendItems += '<div class="card-block innerRequestWrap">';
                            appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                            appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                            appendItems += '<div class="wrapfooter dayBottom">';
                            appendItems += '<div class="authorSize row justify-content-center">요청자 :&nbsp <a href="/user/' + item.requestMemberId + '">' + item.nick + '</a></div>';
                            appendItems += '<div class="row justify-content-center mt-1">';
                            appendItems += '<button type="button" class="requestAccept btn btn-sm btn-primary btn-danger mr-4" name="sendCkPhone">수락하기</button>';
                            appendItems += '<button type="button" class="requestRefuse btn btn-sm btn-primary btn-danger mr-4" name="sendCkPhone">거절하기</button>';
                            appendItems += '<input type="hidden" name="code" value="' + item.requestId + '">';
                            appendItems += '</div>';
                            appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                            }

                        });
                        $("#contentsWrap1").append(appendItems);
                        profile1TabStartNum += 12;
                    } else {
                        profile1Result = false;
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
                "sellStatus": 'SOLD',
                "viewStatus": viewStatus
            }
            $.ajax({
                type: "POST",
                url: "/user/goodsHistory/sellingList",
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
                            appendItems += '<div class="card-block innerSoldWrap">';
                            appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                            appendItems += '<h4 class="card-text">' + item.juso + '</h4>';
                            appendItems += '<div class="wrapfooter dayBottom">';
                            appendItems += '<span class="post-date mt-2">' + item.createTime + '</span>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                        });
                        $("#contentsWrap2").append(appendItems);
                        profile2TabStartNum += 12;
                    } else {
                        profile2Result = false;
                    }
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    } else if (profile3Tab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 1200) && profile3Result == true) {
            var searchType = {
                "startNum": profile3TabStartNum,
                "viewStatus": viewStatus
            }
            $.ajax({
                type: "POST",
                url: "/user/goodsHistory/commentsList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<div class="card">';
                            appendItems += '<div class="card-block">';
                            appendItems += '<h2 class="card-title txt_post">(' + item.sellStatus + ') <a href="/market/marketGoods/' + item.goodsId + '">' + item.title + '</a></h2>';
                            appendItems += '</div>';
                            appendItems += '<div class="card-block">';
                            appendItems += '<h4 class="card-text">' + item.contents + '</h4>';
                            appendItems += '<h4 class="card-text">' + item.createTime + '</h4>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                        });
                        $("#contentsWrap3").append(appendItems);
                        profile3TabStartNum += 12;
                    } else {
                        profile3Result = false;
                    }
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    }
});

// 마켓글 삭제 버튼
$(".deleteMyGoods").on("click",function() {
    var id = this.parentNode.childNodes[5].value;

    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(parentNode);

    console.log(id);

    $.ajax({
        url:'/user/deleteMyGoods', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id':id}, //보낼 데이터
        success: function(data) {
            alert("마켓글 삭제 완료");
            parentNode.remove();
        },
        error: function(err) {
            alert("실패")
        }
    });
});



// 거래 수락 요청 버튼
$(".requestAccept").on("click",function() {
    var requestId = this.parentNode.childNodes[5].value;
    var goodsId = this.parentNode.childNodes[7].value;
    var parentNode = this.parentNode;
    var btn1 = this.parentNode.childNodes[1];
    var btn2 = this.parentNode.childNodes[3];


    console.log(requestId);
    console.log(goodsId);

    $.ajax({
        url:'/user/accept', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'requestId': requestId,
        'goodsId': goodsId}, //보낼 데이터
        success: function(data) {
            alert("거래 요청을 수락하였습니다.");
            console.log(data);
            // document.getElementById("h2Value").innerHTML = "SELLING";
            parentNode.removeChild(btn1);
            parentNode.removeChild(btn2);
            $(parentNode).prepend($("<button type=\"button\" class=\"soldSelect btn btn-sm btn-primary mr-4\"\n" +
                "name=\"sendCkPhone\">판매완료</button>"));
            $(parentNode).append($("<button type=\"button\" class=\"deleteAccept btn btn-sm btn-primary btn-danger mr-4\"\n" + "name=\"sendCkPhone\">취소하기</button>"));
            location.href ='/user/goodsHistory'
            }
        ,
        error: function(err) {
            alert("실패")
        }
    });
});

//거래 요청 취소 버튼
$(".requestRefuse").on("click",function() {
    var id = this.parentNode.childNodes[5].value;

    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(parentNode);

    console.log(id);

    $.ajax({
        url:'/user/refuse', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'id':id}, //보낼 데이터
        success: function(data) {
            alert("거래 요청을 거절하였습니다.");
            parentNode.remove();
            location.href ='/user/goodsHistory'
        },
        error: function(err) {
            alert("실패")
        }
    });
});

// 판매완료 버튼
$(".soldAccept").on("click",function() {
    var requestId = this.parentNode.childNodes[5].value;
    var goodsId = this.parentNode.childNodes[7].value;
    var deleteSellGoods = document.getElementById("sellingCard");
    var deleteSellingGoods = document.getElementById("reCard");

    // console.log(requestId);
    // console.log(goodsId);
    // console.log(deleteSellGoods);
    // console.log(deleteSellingGoods);

    $.ajax({
        url:'/user/soldAccept', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'requestId': requestId,
            'goodsId': goodsId}, //보낼 데이터
        success: function(data) {
            alert("판매 완료");
            // console.log(data);
            deleteSellingGoods.remove();
            deleteSellGoods.remove();
            location.href ='/user/goodsHistory'
        },
        error: function(err) {
            alert("실패")
        }
    });
});

// Selling 거래 취소
$(".deleteAccept").on("click",function() {

    var requestId = this.parentNode.childNodes[5].value;
    var goodsId = this.parentNode.childNodes[7].value;
    var parentNode = this.parentNode.parentNode.parentNode.parentNode;

    console.log(requestId);
    console.log(goodsId);

    $.ajax({
        url:'/user/deleteAccept', //request 보낼 서버의 경로
        type:'post', // 메소드(get, post, put 등)
        data:{'requestId': requestId,
            'goodsId': goodsId}, //보낼 데이터
        success: function(data) {
            alert("거래 취소");
            parentNode.remove();
            location.href ='/user/goodsHistory'
        },
        error: function(err) {
            alert("실패")
        }
    });
});