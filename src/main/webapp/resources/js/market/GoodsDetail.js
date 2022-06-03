let buyBtn = document.getElementById("buy_btn");
window.onload = function () {
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        window.location.replace("/market");
    }
    if (wish === true) {// 위시가 존재하면 위시 버튼들을 붉게 바꿔줌
        document.querySelector('.wish_btn').style.color = 'red';
        document.querySelector('.toggle_like').style.color = 'red';
        wishCount++;
    }
    if (loginedId === true) {
        if (requestOne === true) {// 구매요청 validation
            if (requestResult === '요청중') {
                buyBtn.style.backgroundColor = 'gray';
                buyBtn.style.cursor = 'default';
                buyBtn.value = '구매 요청됨';
                forBuyCount = 1;
            } else if (requestResult === '거절됨') {
                buyBtn.style.backgroundColor = 'red';
                buyBtn.style.cursor = 'default';
                buyBtn.value = '요청 거절된 게시글';
                forBuyCount = 2;
            } else if (requestResult === '수락됨') {
                buyBtn.style.backgroundColor = 'green';
                buyBtn.style.cursor = 'default';
                buyBtn.value = '요청 수락!';
                forBuyCount = 3;
            }
        } else {// 없으면 처음 진입시 구매요청하기 사라지니까 지우지 말것
            buyBtn.value = "구매 요청하기";
        }
    }
    marketCommentsInsert();

};

function deleteQuestion(id) {
    if (loginedId === false) {
        alert("로그인 후 이용해주세요");
        return false;
    }

    if(loginedIdString === goodsMemberId){
        let deleteUrl = "/user/market/marketGoods/DeleteGoods?id=" + id;
        if (confirm("해당 게시글을 삭제 하시겠습니까?")) {
            alert("게시글이 삭제 되었습니다.");
            location.href = deleteUrl;
        }
    }else {
        alert("본인의 게시글만 삭제할 수 있습니다.");
    }
}

function updateQuestion(id) {
    if (loginedId === false) {
        alert("로그인 후 이용해주세요");
        return false;
    }

    if(loginedIdString === goodsMemberId){
        let updateUrl = "/user/market/GoodsRemake?id="+id;
        if (confirm("해당 게시글을 수정 하시겠습니까?")) {
            location.href = updateUrl;
        }
    }else {
        alert("본인의 게시글만 수정할 수 있습니다.");
    }
}

let wishCount = 0;
$(".wish").click(function () { //위시 버튼 액션
    if (loginedId === false) {
        alert("로그인 후 이용해주세요!");
        return;
    }
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        return;
    }
    if (wishCount === 0) {
        let returned = plusWish(oneGoodsId);
        if (returned === true) {
            document.querySelector('.wish_btn').style.color = 'red';
            document.querySelector('.toggle_like').style.color = 'red';
            wishCount++;
        }
    } else {
        let returned = cancleWish(oneGoodsId);
        if (returned === true) {
            document.querySelector('.wish_btn').style.color = 'rgba(0, 0, 0, 0.44)';
            document.querySelector('.toggle_like').style.color = 'rgba(0, 0, 0, 0.44)';
            wishCount = 0;
        }
    }
});

function plusWish(id) { // 본인의 위시리스트에 추가
    let wishReturn = false;
    $.ajax({
        type: "POST",
        url: "/user/market/wish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에 추가하였습니다");
                wishReturn = true;
            } else if (data.result === "300") {
                alert("정상적인 방법으로 접근해주세요!")
            } else if (data.result === "400") {
                alert("게시글이 존재하지 않습니다.")
            } else if (data.result === "100") {
                alert("정지된 게시글입니다.")
            } else if (data.result === "500") {
                alert("이미 존재하는 위시리스트입니다.");
                document.querySelector('.wish_btn').style.color = 'red';
                document.querySelector('.toggle_like').style.color = 'red';
                wishCount++;
            } else {
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            console.log("error")
        }
    });
    return wishReturn;
}

function cancleWish(id) {// 위시리스트에서 제거
    let wishReturn = false;
    $.ajax({
        type: "POST",
        url: "/user/market/cancelWish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에서 제거하였습니다");
                wishReturn = true;
            } else if (data.result === "300") {
                alert("정상적인 방법으로 접근해주세요!")
            } else if (data.result === "400") {
                alert("게시글이 존재하지 않습니다.")
            } else if (data.result === "100") {
                alert("정지된 게시글 입니다.")
            } else if (data.result === "500") {
                alert("이미 지워진 위시리스트 입니다.");
                document.querySelector('.wish_btn').style.color = 'rgba(0, 0, 0, 0.44)';
                document.querySelector('.toggle_like').style.color = 'rgba(0, 0, 0, 0.44)';
                wishCount = 0;
            } else {
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            console.log("error")
        }
    });
    return wishReturn;
}

$('.report').on('click', function () { //신고 버튼
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        return;
    }

    if (loginedId === false) {
        alert("로그인 후 이용해주세요!");
        return;
    }

    if (reportResult === false) { //신고가 없을시 신고 모달창으로 넘기기
        reportPageOpen(); //모달창 오픈
    } else {
        alert("이미 신고한 게시글입니다."); //신고가 있다면 알려줌
    }

});

function reportPageOpen() {// 모달창
    var url = "/user/market/marketGoods/report?id=" + oneGoodsId;
    window.open(url, "신고하기", 'width=500,height=600, scrollbars=no, resizable=no');
    reportReult = true;
}


let forBuyCount = 0;
$(".buy_btn").click(function () { // 구매요청 버튼 누를시 구매요청
    //내부 validation
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        return;
    }

    if (forBuyCount === 0) {
        let buyResult = buy();
        if (buyResult === true) {
            forBuyCount++;
        }
    } else if (forBuyCount === 1) {
        alert("이미 요청된 게시글입니다!");
        forBuyCount = 1;
    } else if (forBuyCount === 2) {
        alert("거절된 게시글입니다!");
        forBuyCount = 2;
    } else if (forBuyCount === 3) {
        alert("수락된 게시글입니다!");
        forBuyCount = 3;
    }
})

function buy() {// 구매요청 메서드
    let buyResult = false;
    $.ajax({
        type: "POST",
        url: "/user/market/sendRequest",
        data:
            {
                "id": oneGoodsId,
                "sendRequest": "요청중"
            },
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("구매 요청이 완료되었습니다");
                document.querySelector('.buy_btn').style.backgroundColor = 'GRAY';
                document.querySelector('.buy_btn').style.disabled = true;
                document.querySelector('.buy_btn').style.cursor = 'default';
                buyResult = true;
            } else if (data.result === "300") {
                alert("구매요청이 거절된 게시물입니다.");
                document.querySelector('.buy_btn').style.backgroundColor = 'GRAY';
                document.querySelector('.buy_btn').style.disabled = true;
                document.querySelector('.buy_btn').style.cursor = 'default';
                buyResult = false;

            } else if (data.result === "100") {
                alert("이미 요청한 게시물입니다!");
                document.querySelector('.buy_btn').style.backgroundColor = 'GRAY';
                document.querySelector('.buy_btn').style.disabled = true;
                document.querySelector('.buy_btn').style.cursor = 'default';
                buyResult = false;
            } else if (data.result === "400") {
                alert("본인의 게시물입니다.");
                buyResult = false;
            }
        },
        errors: function () {
            alert("error")
        }
    });
    return buyResult;
}


$(document).keypress(function (e) {//엔터키 막기
    if (e.keyCode === 13) e.preventDefault();
});


let startNum = 1;

function marketCommentsInsert() {// 화면 로드시 댓글 불러오기
    var goodsId = {
        "id": oneGoodsId,
        "startNum": startNum
    }
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/market/getMarketComment",
        data: JSON.stringify(goodsId),
        dataType: "json",
        async: false,
        success: function (data) {
            $.each(data, function (index, item) {
                dataAppend(item);
            });
        },
        error: function () {
            alert("Error");
        }
    });
}

function dataAppend(item) { //댓글 화면 구성
    var htmls = "";

    htmls += '<div class="allCommentWrap" id="rid' + item.id + '">';
    htmls += '<div class="media text-muted pt-3" id="com">';
    htmls += '<div class="d-flex">';
    htmls += '<div class="flex-shrink-0"><a href="/user/userLink?id=' + item.memberId + '"><img class="author-thumb" id="comment_img' + item.id + '" src="' + item.memberPicture + '" alt="..."/></a></div>'
    htmls += '<div class="for_append_textarea" id="for_append_textarea' + item.id + '">';
    htmls += '<div class="test112">';
    htmls += '<div class="nametack" id="nametack">';
    htmls += '<div class="comment_Name" id="comment_Name' + item.id + '"><a class="link-dark" href="/user/userLink?id=' + item.memberId + '">' + item.memberNickname + '</a></div>';
    htmls += '<div class="comment_address" id="comment_Time' + item.id + '">' + item.createTime + '</div>';
    htmls += '</div>';
    htmls += '</div>';
    htmls += '<div class="comment_content" id="comment_detail' + item.id + '">' + item.contents + '</div>';
    htmls += '</div>';
    htmls += '</div>';
    htmls += '</div>';
    htmls += '<div class="comment_button_area">';
    htmls += '<div id="updateBtnArea"><button type="button" class="updateComment" onclick="updateComment(' + item.id + ',' + item.memberId + ',' + item.nowUserId + ')"><i class="ri-edit-line"></i></input></div>';
    htmls += '<div id="deleteBtnArea"><button type="button" class="deleteComment" onclick="deleteComment(' + item.id + ',' + item.memberId + ',' + item.nowUserId + ')"><i class="ri-delete-bin-line"></i></input></div>';
    htmls += '</div>';
    htmls += '<hr>';
    htmls += '</div>';
    $("#marketComment_comments").append(htmls);
}

/////comment Write
//댓글 저장 버튼 클릭 이벤트
$('#marketComment_btn').on('click', function () {

    let content = document.getElementById('content').value;
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        $('#content').val("");
        return;
    }

    if (fn_checkByte(content) === false) {
        return;
    }
    var replyContent = $('#content').val();
    var repldata = {
        "id": oneGoodsId,
        "content": replyContent
    }
    $('#content').val('');
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/user/market/createMarketComment",
        data: JSON.stringify(repldata),
        dataType: "json",
        async: false,
        success: function (data) {
            if (data == null) {
                alert("정지된 게시글이거나 게시글이 존재하지 않습니다.");
                return;
            }
            $("#marketComment_comments div").remove();
            $.each(data, function (index, item) {
                dataAppend(item);
            });
        }
        , error: function (error) {
            console.log("error");
        }
    });
});


// 각 댓글의 삭제 버튼 클릭
function deleteComment(id, writeUser, nowUser) {
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        return;
    }
    if (writeUser !== nowUser) {
        alert("사용자의 아이디와 다릅니다!");
        return;
    }else {
        if (confirm("해당 댓글을 삭제 하시겠습니까?")) {
            let data = {"id": id}
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/user/market/deleteMarketComment",
                data: JSON.stringify(data),
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.result === "200") {
                        alert("삭제되었습니다!")
                        $('#rid' + id).remove();
                    } else if (data.result === "100") {
                        alert("해당 댓글의 작성자가 아닙니다!");
                    } else if (data.result === "300") {
                        alert("로그인 후 이용해주세요!");
                    } else if (data.result === "400") {
                        alert("해당 댓글이 더이상 존재하지 않습니다.")
                    }
                },
                error: function (error) {
                    console.log("에러");
                }
            });
        }
    }


}


let count = 0;

// 각 댓글의 수정 버튼 클릭
function updateComment(id, writeUser, nowUser) {
    if (active === "INACTIVE") {
        alert("정지된 게시글입니다!");
        return;
    }
    if (writeUser !== nowUser) {
        alert("사용자의 아이디와 다릅니다!");
        return;
    }// 사용자 아이디 validation

    if (count === 0) {// 사용자 아이디가 같을 시 카운트로 수정버튼을 컨트롤
        count++;

        let parentNode = $("rid" + id);

        let comment = document.getElementById("comment_detail" + id);
        let commentText = comment.innerText;


        let newTextArea = document.createElement("input");
        newTextArea.id = "new_detail" + id;
        newTextArea.class = "marketComment_textarea";
        newTextArea.value = commentText;

        comment.remove();
        $("#for_append_textarea" + id).append(newTextArea);

    } else if (count === 1) {
        if (confirm("해당 댓글을 수정 하시겠습니까?")) {
            count++;
            let content = document.getElementById("new_detail" + id)
            let newContent = content.value;
            if (fn_checkByte(newContent) === false) {
                return;
            }

            var updateData = {
                "id": id,
                "content": newContent
            }
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/user/market/updateMarketComment",
                data: JSON.stringify(updateData),
                dataType: "json",
                async: false,
                success: function (data) {
                    let newObject = null;
                    if (data.result === "200") {
                        alert("수정되었습니다!");
                        newObject = data.updateComment;

                        let htmls = "";
                        htmls += '<div class="comment_content" id="comment_detail' + newObject.id + '\">' + newObject.contents + '</div>'

                        $("#for_append_textarea" + newObject.id + " input").remove();
                        $("#for_append_textarea" + newObject.id).append(htmls);
                    } else if (data.result === "100") {
                        alert("수정에 실패하였습니다.");
                        newObject = data.updateComment;

                        let htmls = "";
                        htmls += '<div class="comment_content" id="comment_detail' + newObject.id + '\">' + newObject.contents + '</div>'

                        $("#for_append_textarea" + newObject.id + " input").remove();
                        $("#for_append_textarea" + newObject.id).append(htmls);
                    } else if (data.result === "300") {
                        alert("로그인 후 이용해주세요!");
                    } else if (data.result === "400") {
                        alert("해당 댓글이 더이상 존재하지 않습니다.")
                    }
                }
                , error: function (error) {
                    console.log("error");
                }
            });
        }
    } else {
        count = 0;
    }
}

//textarea 바이트 수 체크하는 함수
function fn_checkByte(text_val) {
    const maxByte = 1000; //최대 100바이트
    // const text_val = obj.value; //입력한 문자
    const text_len = text_val.length; //입력한 문자수

    let totalByte = 0;
    for (let i = 0; i < text_len; i++) {
        const each_char = text_val.charAt(i);
        const uni_char = escape(each_char); //유니코드 형식으로 변환
        if (uni_char.length > 4) {
            // 한글 : 2Byte
            totalByte += 2;
        } else {
            // 영문,숫자,특수문자 : 1Byte
            totalByte += 1;
        }
    }

    if (totalByte > maxByte) {
        alert('최대 1000Byte까지만 입력가능합니다.');
        document.getElementById("content").value = "";
        return false;
    }
}


function sendMessageModal(memberId) {
    if (loginedId === false) {
        alert("로그인 후 이용해주세요!");
        return false;
    }

    var url = "/user/userLink/sendMessage?id=" + memberId;
    window.open(url, "쪽지보내기", 'width=500,height=600, scrollbars=no, resizable=no');
}
