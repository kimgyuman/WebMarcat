$(document).ready(function () {

    (function ($) {
        // Header slider
        $('.header-slider').slick({
            autoplay: true,
            autoplaySpeed: 2000,
            dots: true,
            infinite: true,
            slidesToShow: 1,
            slidesToScroll: 1
        });
    })(jQuery);

    var navHeight = $(".recent-posts").height();
    //navHeight 의 높이를 구하기

    $(".header").hide();
    //스크롤시 나타날 객체 미리 숨기기

    $(window).scroll(function () {  // 윈도우 스크롤 기능 작동
        var rollIt = $(this).scrollTop() >= navHeight - 300;
        // 스크롤의 정도가 navHeight 의 값을 넘었을 때 == 윈도우 스크롤의 값이 navHeight 의 높이와 같거나 크다

        /*
        scrollTop 은 윈도우에서 스크롤의 위치가 가장 상위에 있다는 의미로 해석
        스크롤의 위치가 화면 아래일수록 == scrollTop 의 값이 커짐
        */

        if (rollIt) {
            //윈도우 스크롤 기능의 값이 navHeight 의 높이와 같거나 크면
            $(".header").show();
            (function ($) {
                // Header slider
                $('.header-slider').slick({
                    autoplay: true,
                    autoplaySpeed: 2000,
                    dots: true,
                    infinite: true,
                    slidesToShow: 1,
                    slidesToScroll: 1
                });
            })(jQuery);
        } else {
            $(".header").hide();
        }
    });

});

// Wish용 JS
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

function plusWish(id) {
    let resultBoolean = false;
    $.ajax({
        type: "POST",
        url: "/user/market/wish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에 추가하였습니다");
                resultBoolean = true;
            }else if (data.result === "300"){
                alert("정상적인 방법으로 접근해주세요!");
            } else if (data.result === "400") {
                alert("게시글이 존재하지 않습니다.");
            } else if (data.result === "100") {
                alert("정지된 게시글입니다.");
            } else if (data.result === "500") {
                alert("이미 존재하는 위시리스트입니다.");
                let param = 'mainHeart(true,' + id + ')';
                document.getElementById("wishPlus" + id).className = "wish_btn readyWish";
                document.getElementById("wishPlus" + id).setAttribute("onClick", param);
            }else {
                console.log(data.result);
                alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
            }
        },
        errors: function () {
            alert("error")
        }
    });
    return resultBoolean;
}

function cancleWish(id) {
    let resultBoolean = false;
    $.ajax({
        type: "POST",
        url: "/user/market/cancelWish?id=" + id,
        data: {"id": id},
        async: false,
        success: function (data) {
            if (data.result === "200") {
                alert("위시 리스트에서 제거하였습니다");
                resultBoolean = true;
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
            alert("error")
        }
    });
    return resultBoolean;
}

//boardWish
function boardMainHeart(wish, boardid) {
    // if (loginedId === false) {
    //     alert("로그인 후 이용해주세요!")
    // }

    if (wish === false) {
        let result = boardPlusWish(boardid);
        if (result === true) {
            let param = 'boardMainHeart(true,' + boardid + ')';
            document.getElementById("boardWishPlus" + boardid).className = "wish_btn readyWish";
            document.getElementById("boardWishPlus" + boardid).setAttribute("onClick", param);
        }
    } else {
        let result = boardCancelWish(boardid);
        if (result === true) {
            let param = 'boardMainHeart(false,' + boardid + ')';
            document.getElementById("boardWishPlus" + boardid).className = "wish_btn noneWish";
            document.getElementById("boardWishPlus" + boardid).setAttribute("onClick", param);
        }
    }
}

function boardPlusWish(id) {// 위시리스트에 보내기
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

function boardCancelWish(id) {// 위시리스트에서 제거하기
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