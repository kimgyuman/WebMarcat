var homeStartNum = 13;
var profile1TabStartNum = 13;
var sellStatus = 'SELL';
var viewStatus = '';
var homeResult = true;
var profile1Result = true;
var homeTab = true;
var profile1Tab = false;
const home2 = document.getElementById('home2-tab');

$('#home2-tab').on("click", function () {
    if (homeTab != true) {
        homeTab = true;
        profile1Tab = false;
    }
});
$('#profile1-tab').on('click', function () {
    if (profile1Tab != true) {
        profile1Tab = true;
        homeTab = false;
    }
});

/* 답장하기버튼클릭시 답장 PopUp */
function replyPopUp(data) {
    var url="/user/message/replyMessage?msId="+data;
    var option="width=500, height=500, top=200, left=400, scrollbars=no, resizable=no"
    window.open(url, "", option);
}


/* 보낸메세지함 삭제버튼 */
function deleteA(data) {
    var msId = data;
    var card = document.getElementById("messageBody")
    var deleteElement = card.parentNode;


    $.ajax({
        url:'/user/message/sendDelMessage',
        type:'post',
        data:{'msId':msId},
        success: function(data) {
            alert("메세지 삭제 성공");
            deleteElement.remove();

        },
        error: function(err) {
            alert("메세지 삭제 실패")
        }
    });
}

/* 받은메세지함 삭제버튼 */
function deleteB(data) {
    var msId = data;
    var card = document.getElementById("mainBody")
    var deleteElement = card.parentNode;

    $.ajax({
        url:'/user/message/delMessage',
        type:'post',
        data:{'msId':msId},
        success: function(data) {
            alert("메세지 삭제 성공");
            deleteElement.remove();
        },
        error: function(err) {
            alert("메세지 삭제 실패")
        }
    });
}


$(window).scroll(function () {
    if (homeTab == true) {
        if ($(window).scrollTop() >= ($(document).height() - 1200) && homeResult == true) {
            var targetSearchType = {
                "startNum": profile1TabStartNum,
                "viewStatus": viewStatus
            }

            $.ajax({
                type: "POST",
                url: "/user/message/receiveMessageList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(targetSearchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    console.log("보낸편지함");
                    console.log(data);
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<div class="card col-md-12" id="messageCard">';
                            appendItems += '<input id="smsId" type="hidden" name="smsId" value="' + item.msId + '"/>';
                            appendItems += '<div class="card-body" id="messageBody">';
                            appendItems += '<div class="row">';
                            appendItems += '<div class="d-flex flex-column align-items-center col-md-4 mt-3">';
                            appendItems += '<div class="box">';
                            appendItems += '<img class="profile" src="'+ item.targetMemberImages + '">';
                            appendItems += '</div>';
                            appendItems += '<a class="mt-3" href="/user/userLink?id=' + item.senderId + '">';
                            appendItems += '<strong>' + item.targetNick + '</strong></a>';
                            appendItems += '</div>';
                            appendItems += '<div class="col-md-8">';
                            appendItems += '<h2 class="card-title txt_post">보낸 메세지 내용</h2>';
                            appendItems += '<div class="card">';
                            appendItems += '<p class="ml-2 mt-2">' + item.msMessage + '</p>';
                            appendItems += '<span class="ml-2 mt-2">' + item.msCreateTime + '</span>';
                            appendItems += '</div>';
                            appendItems += '<div class="btnGroup mt-3">';
                            appendItems += '<button class="sendDel float-right btn btn-danger ml-2" id="delete_btn" onClick="deleteA(' + item.msId + ')">삭제하기</button>';
                            appendItems += '</div>';
                            appendItems += '</div>';
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
                "startNum": homeStartNum,
                "sellStatus": sellStatus,
                "viewStatus": viewStatus
            }
            $.ajax({
                type: "POST",
                url: "/user/message/sendMessageList",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(searchType),
                dataType: "json",
                async: false,
                success: function (data) {
                    console.log("받은편지함");
                    console.log(data);
                    if ($.isEmptyObject(data) == false) {
                        var appendItems = "";
                        $.each(data, function (index, item) {
                            appendItems += '<div class="card col-md-12" id="mainCard">';
                            appendItems += '<input id="msId" type="hidden" name="msId" value="' + item.msId + '"/>';
                            appendItems += '<div class="card-body" id="mainBody">';
                            appendItems += '<div class="row">';
                            appendItems += '<div class="d-flex flex-column align-items-center col-md-4 mt-3">';
                            appendItems += '<div class="box">';
                            appendItems += '<img class="profile" src="'+ item.targetMemberImages + '">';
                            appendItems += '</div>';
                            appendItems += '<a class="mt-3" href="/user/userLink?id=' + item.senderId + '">';
                            appendItems += '<strong>' + item.senderNick + '</strong></a>';
                            appendItems += '</div>';
                            appendItems += '<div class="col-md-8">';
                            appendItems += ' <h2 class="card-title txt_post">받은 메세지 내용</h2>';
                            appendItems += '<div class="card">';
                            appendItems += '<p class="ml-2 mt-2">' + item.msMessage + '</p>';
                            appendItems += '<span class="ml-2 mt-2">' + item.msCreateTime + '</span>';
                            appendItems += '</div>';
                            appendItems += '<div class="btnGroup mt-3">';
                            appendItems += ' <button class="replyDel float-right btn btn-danger ml-2" id="delete1_btn" onclick="deleteB(' + item.msId + ')">삭제하기</button>';
                            appendItems += '<button class="replyBtn float-right btn btn-primary ml-2" id="reply_btn" onclick="replyPopUp('+ item.msId +')">답장하기</button>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
                            appendItems += '</div>';
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
    }

});




