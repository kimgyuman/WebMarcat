<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/5363aed2f0.js" crossorigin="anonymous"></script>
    <link href="/css/board/content.css" rel="stylesheet">
    <link href="/resources/css/mediumish.css" rel="stylesheet" type="text/css">


    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <script>
<%--        <% PageContext.setAttribute("replaceChar", "\n"); %>--%>
        let oneBoardId = ${contents.id};
        let wish = ${contents.wishList};
        let reportReult = ${contents.report};
        let id = ${contents.id};
        let loginedId = ${loginedId};
        let boardMemberId = ${contents.memberId};
        let nowUser = ${contents.memberId};
        if (loginedId === true) {
            var loginedIdString = ${loginedIdString};
        } else {
            let loginedIdString = "";
        }

    </script>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<!-- Begin Article
================================================== -->
<div class="container">
    <div class="row">

        <!-- Begin Fixed Left Share -->
        <div class="col-md-2 col-xs-12">
            <div class="share">

                <ul>
                    <li>
                        <p>
                            <a href="#" class="top_btn" style="text-decoration: none"><i class="ri-arrow-up-s-line"></i></a>
                        </p>
                    </li>
                    <li>
                        <a href="#target" class="moveComment_btn" style="text-decoration: none">
                            <i class="ri-chat-3-line"></i>
                        </a>
                    </li>
                </ul>
                <div class="sep">
                </div>
                <ul>
                    <li>
                        <button type="button" class="report_btn report" id="send_report_btn">
                            <i class="ri-alarm-warning-line"></i>
                        </button>
                    </li>
                    <li>
                        <button type="button" id="wish_btn" class="wish_btn wish_btn">
                            <i class="fa-regular fa-heart"></i>
                        </button>
                    </li>
                </ul>
                <div class="sep">
                </div>
                <ul>
                    <li>
                        <a onclick="moveToUpdate(${contents.id})">
                            <i class="ri-edit-line side_update_btn" id="main_save"></i>
                        </a>
                    </li>
                </ul>
                <ul>
                    <li>
<%--                        <a onclick="moveToDelete(${contents.id})">--%>
                        <a onclick="moveToDelete(${contents.id})">
                            <i class="ri-delete-bin-line side_delete_btn" id="main_delete"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- End Fixed Left Share -->

        <!-- Begin Post -->
        <div class="col-md-8 col-md-offset-2 col-xs-12" id="mainTag">
            <div class="mainheading">

                <!-- Begin Profile -->
                <div class="row post-top-meta">
                    <div class="col-md-2">
                        <a href="/user/userLink?id=${contents.memberId}"><img class="author-thumb"
                                                   src="${contents.memberImages.savedFileName}"
                                                   alt="Sal">
                        </a>
                    </div>
                    <div class="col-md-10">
                        <a href="/user/userLink?id=${contents.memberId}" class="link-dark" style="text-decoration: none; color:black;">${contents.nickname}</a><a onclick="sendMessageModal(${contents.memberId})"  class="btn follow">쪽지보내기</a>

<%--                        <a href="#" class="btn follow">Follow</a>--%>
                        <div>
                        <span class="author-description">${contents.koreaArea.admNm}</span>
<%--                        <span class="post-date">22 July 2017</span><span class="dot"></span><span class="post-read">6 min read</span>--%>
                        </div>
                    </div>
                </div>
                <!-- End Profile -->


            </div>
            <hr>

            <!-- Image Area-->
<%--            <img class="featured-image img-fluid" src="assets/img/demopic/10.jpg" alt="">--%>
            <div class="slider">
                <c:forEach var="radios" begin="1"  end="${fn:length(contents.boardImages)}">
                    <c:choose>
                        <c:when test="${radios == 1}">
                            <input type="radio" name="slide" id="slide${radios}" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="slide" id="slide${radios}">
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <ul id="imgholder" class="imgs">
                    <c:forEach var="images" items="${contents.boardImages}">
                        <c:if test="${images != null}">
                            <li><img class="featured-image img-fluid" src="${images}"></li>
                        </c:if>
                    </c:forEach>
                </ul>
                <div class="bullets">
                    <c:forEach var="radios" begin="1"  end="${fn:length(contents.boardImages)}">
                        <label for="slide${radios}">&nbsp;</label>
                    </c:forEach>
                </div>
            </div>
            <!-- End Image Area -->
            <div class="main_title">
                <h1 class="posttitle">${contents.title}</h1>
            </div>
            <!-- content Area -->
            <div class="article-post">
                <% pageContext.setAttribute("newLineChar", "\\n"); %>
                <blockquote class="contentsStyle">


                    ${fn:replace(contents.contents, newLineChar, "<br/>")}
                </blockquote>

            </div>
            <!-- End Content Area -->

            <!-- Begin Tags -->
            <div class="after-post-tags">
                <ul class="tags">
                    <li class="createTimeCount"><a>
                        ${contents.createTime} </a></li>
                    <li class="viewCount"><a>
                        조회수 &nbsp; ${contents.viewCount}</a></li>
                    <li class="wishCount"><a>좋아요 ${contents.wishCount}</a></li>
                </ul>
            </div>
            <!-- End Tags -->

            <%--  Begin Comment  --%>
            <a id="target"></a>
            <div class="marketCommemt_container">
                <form class="marketCommnet_form" id="marketCommnet_form">
                    <sec:authorize access="isAnonymous()">
                        <input class="marketComment_textarea" placeholder="로그인하시기 바랍니다." id="content" type="text">
                        <input type="button" class="marketComment_btn ml-3" onclick="location.href='/login'" value="로그인">
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <input class="marketComment_textarea" placeholder="COMMNETS" id="content" type="text">
                        <input type="button" class="marketComment_btn ml-3" id="btnCommentSave" value="등록">
                    </sec:authorize>
                </form>
            </div>

                    <div class="toggle_menu"><!--disabled-->
                        <button type="button" id="toggle_wish_btn wish_btn" class="mr-5 toggle_like wish_btn">
                            <i class="fa-regular fa-heart"></i>
                        </button>
                        <button type="button" class="mr-5 toggle_report report" id="toggle_report_btn">
                            <i class="ri-alarm-warning-line"></i>
                        </button>
                        <a onclick="moveToUpdate(${contents.id})" class="mr-5 toggle_update">
                            <i class="ri-edit-line side_update_btn" id="main_save a"></i>
<%--                            <i class="fa-regular fa-pen-to-square"></i>--%>

                        </a>
                        <a onclick="moveToDelete(${contents.id})" class="mr-5 toggle_delete">
                            <i class="ri-delete-bin-line side_delete_btn" id="main_delete a" ></i>
                        </a>
                    </div>



            <div id="contentsWrap"></div>
            <%--  End Comment  --%>
        </div>
        <!-- End Post -->

    </div>
</div>
<!-- End Article
================================================== -->
<div class="hideshare"></div>
    <%@ include file="../fragments/footer.jsp" %>
    <script src="/resources/js/board/boardContent.js"></script>
</body>
</html>