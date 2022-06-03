window.onload = function () {
    if (wish === true) {
        document.getElementById("wish_btn").style.color = 'red';
        wishCount++;
    }

    commentList();
};
//////////////////////////////////
/*
    댓글 기본 양식
*/
function commentAppend(comments) {
    let htmls = "";
    htmls += '<div class="media text-muted pt-3" id="rid' + comments.id + '">';
    htmls += '<div class="d-flex">';
    htmls += '<div class="ms-3" id="">';
    htmls += '<div class="flex-shrink-0"><a href="/user/userLink?id=' + comments.memberId + '"><img class="rounded-circle" src="' + comments.memberPicture + '"alt="..."/></a></div>'
    htmls += '<div id="for_append_textarea' + comments.id + '">';
    htmls += '<div class="test112">';
    htmls += '<div class="fw-bold" id="nametack">';
    htmls += '<div class="comment_Name"><a href="/user/userLink?id=' + comments.memberId + '" style="text-decoration: none; color:black;">' + comments.nickname + '</a></div>';
    htmls += '<div class="comment_address">' + comments.admNm + '</div>';
    htmls += '</div>';
    htmls += '<div id="updateBtnArea"><button onclick="commentsUpdateVal(' + comments.id + ',' +comments.memberId + ',' + comments.nowUser +')" class="updateComment"><i class="ri-edit-line"></i></button></div>';
    htmls += '<div id="deleteBtnArea"><button onclick="commentsDeleteVal(' + comments.id + ',' +comments.memberId + ',' + comments.nowUser +')" id="' + comments.id + '" class="deleteComment" ><i class="ri-delete-bin-line"></i></button>';
    htmls += '</div>';
    htmls += '</div>';
    htmls += '<div class="comment_content" id="comment_detail' + comments.id + '">' + comments.contents + '</div>';
    htmls += '</div>';
    htmls += '<div class="comment_createTime">' + timeForToday(comments.createTime) + '</div>';
    htmls += '</div>';
    htmls += '</div>';
    htmls += '</div>';
    htmls += '<hr class="bottom_line">';
    $("#contentsWrap").append(htmls);
}

function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

/*
    댓글 리스트
* */
function commentList() {


    $.ajax({
        type: "POST",
        // contentType: "application/json; charset=utf-8",
        url: "/board/content",
        data: {"id": oneBoardId},
        // dataType: "json",
        // data: JSON.stringify(comment),
        // async: false,
        success: function (data) {
            $.each(data, function (index, comments) {
                commentAppend(comments);
            });
        },
        error: function () {
            alert("Error");
        },
    })
}

/*
    댓글 저장
*/
$('#btnCommentSave').on('click', function () {
    // let content = document.getElementById(#).value();
    if (fn_checkByte(content) === false) {
        return;
    }
    let replyContent = $('#content').val();
    let repleData = {
        "id": oneBoardId,
        "content": replyContent
    }
    $('#content').val('');
    $.ajax({
        url: "/user/board/content/new",
        data: {'content': replyContent, 'id': oneBoardId},
        type: 'POST',
        success: function (data) {
            if (data == null) {
                alert("게시글이 존재하지 않습니다.");
                return;
            }
            commentAppend(data);
            alert("댓글 등록 완료");
        },
        error: function (error) {
            alert("error");
        }
    })
});

/*
    댓글 삭제
*/
function commentDelete(id) {
    $.ajax({
        url: "/user/board/content/delete",
        type: 'POST',
        data: {'id': id},
        success: function (data) {
            if (data.result === "200") {
                $('#rid' + id).remove();
                alert("댓글이 삭제 되었습니다.")
            } else if (data.result === "100") {
                alert("해당 댓글의 작성자가 아닙니다!");
            } else if (data.result === "300") {
                alert("로그인 후 이용해주세요!");
            } else if (data.result === "400") {
                alert("해당 댓글이 더이상 존재하지 않습니다.")
            }
        },
        error: function (error) {
            alert("error");
        },

    });
}

/*
    댓글 수정
*/
let count = 0;

function commentupdate(id, writeUser, nowUser) {

    if (count === 0) {
        count++;
        let comment = document.getElementById("comment_detail" + id);
        let commentText = comment.innerText;

        let newTextArea = document.createElement("input");
        newTextArea.id = "new_detail" + id;
        newTextArea.class = "boardComment_textarea";
        newTextArea.value = commentText;

        comment.remove();
        $("#for_append_textarea" + id).append(newTextArea);

    } else if (count === 1) {
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
            // type: "POST",
            // contentType: "application/json; charset=utf-8",
            url: "/user/board/content/update",
            // data: {
            //     "id": id,
            //     "content": newContent
            // },
            data: {'content': newContent, 'id': id},
            // data : updateData,
            type: 'POST',
            // dataType: "json",
            // async: false,
            success: function (data) {

                let newObject = null;
                if (data.result === "200") {
                    newObject = data.updateComment;
                    let htmls = "";
                    htmls += '<div class="comment_content" id="comment_detail' + newObject.id + '\">' + newObject.contents + '</div>'
                    $("#for_append_textarea" + newObject.id + " input").remove();
                    $("#for_append_textarea" + newObject.id).append(htmls);
                    alert("댓글이 수정 되었습니다.");

                } else if (data.result === "100") {
                    newObject = data.updateComment;
                    let htmls = "";
                    htmls += '<div class="comment_content" id="comment_detail' + newObject.id + '\">' + newObject.contents + '</div>'
                    $("#for_append_textarea" + newObject.id + " input").remove();
                    $("#for_append_textarea" + newObject.id).append(htmls);
                    alert("댓글이 수정 되었습니다.");
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
    } else {
        count = 0;
    }
}


/////////////////////////////////

let wishCount = 0;
$(".wish_btn").click(function () {
    if (loginedId == false) {
        alert("로그인 후 이용해주세요!")
        return;
    }

    if (wishCount === 0) {
        let result = plusWish(oneBoardId);
        if(result === true){
            document.getElementById("wish_btn").style.color = 'red';
            wishCount++;
        }
    } else {
        let result = cancelWish(oneBoardId);
        if (result === true) {
            document.getElementById("wish_btn").style.color = 'rgba(0, 0, 0, 0.44)';
            wishCount = 0;
        }
    }
});

function plusWish(id) {
    let result = false;
    $.ajax({
        type: "POST",
        url: "/user/board/wish?id="+oneBoardId,
        async: false,
        data: {'id': id},
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에 추가하였습니다");
                result = true;
            } else if(data.result === "300") {
                alert("로그인 후 이용해주세요!");
            } else{
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            alert("error")
        }
    });
    return result;
}
function cancelWish(id) {
    let result = false

    $.ajax({
        type: "POST",
        url: "/user/board/cancelWish?id="+oneBoardId,
        async: false,
        data: {'id': id},
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에서 제거하였습니다");
                result = true;
            } else {
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            alert("error")
        }
    });
    return result;
}

$('.report').on('click', function () {
    if(reportReult === true){
        alert("이미 신고한 게시물입니다.");
        return;
    }
    reportPageOpen();
});

function reportPageOpen() {
    var url = "/board/content/report?id="+oneBoardId;
    window.open(url, "신고하기", 'width=500,height=600, scrollbars=no, resizable=no');
}


//////////////////////////////////


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
        // document.getElementById("content").value = "";
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

function moveToDelete(memberId) {
    if (loginedId === false) {
        alert("로그인 후 이용해주세요");
        return false;
    } else {
        if (boardMemberId === loginedIdString) {
            if (confirm("해당 게시글을 삭제 하시겠습니까?")) {
                alert("게시글이 삭제 되었습니다.");
                location.href = "/user/board/delete?id=" + memberId;

            } else{
            }
        } else {
            alert("본인이 작성한 글만 삭제 할 수 있습니다.");
            return false;
        }
    }
}

function moveToUpdate(memberId) {
    if (loginedId === false) {
        alert("로그인 후 이용해주세요");
        return false;
    } else {
        if (boardMemberId === loginedIdString) {
            location.href = "/user/board/update?id=" + memberId;

        } else {
            alert("본인이 작성한 글만 수정 할 수 있습니다.");
            return false;
        }
    }
}

function commentsUpdateVal(id, writeUser, nowUser) {

    if (loginedId === false) {
        alert("로그인이 필요합니다.");
        return;
    } else if (writeUser !== nowUser) {
        alert("본인이 작성한 댓글만 수정할 수 있습니다.");
        return;
    }
        commentupdate(id, writeUser, nowUser);

}

function commentsDeleteVal(id, writeUser, nowUser) {

    if (nowUser === undefined) {
        alert("로그인이 필요합니다.");

    } else if (writeUser !== nowUser) {
        alert("본인이 작성한 댓글만 삭제할 수 있습니다.");

    } else if (confirm("해당 댓글을 삭제 하시겠습니까?")) {
        commentDelete(id, writeUser, nowUser);
    }
}



function timeForToday(value) {
    const today = new Date();
    const timeValue = new Date(value);
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
    if (betweenTime < 1) return '방금전';
    if (betweenTime < 60) {
        return `${betweenTime}분전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if (betweenTimeDay >= 2) {
        return `${timeValue.toLocaleDateString()+" "+timeValue.toLocaleTimeString()}`;
    } else {
        return `${betweenTimeDay}일전`;
    }
}

