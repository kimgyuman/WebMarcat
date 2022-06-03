<%@ page import="marcat.myPage.dto.user.UserActivityMarketDTO" %>
<%@ page import="marcat.myPage.dto.user.UserActivityBoardDTO" %>
<%@ page import="marcat.myPage.dto.user.UserLinkDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Fonts -->

    <link href="/resources/css/user/userPageUserLink.css" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/5363aed2f0.js" crossorigin="anonymous"></script>

<%--    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>
<%--    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>--%>
    <title>유저 정보</title>
</head>
<body>
<%@ include file="../../fragments/header.jsp" %>
<div class="container">
    <div class="col-md-9 col-xs-12 mb-3">
    <div class="mainheading">

        <!-- Begin Profile -->
        <div class="row post-top-meta">
            <div class="col-md-2 ml-5">
                <a><img class="author-thumb"
                        src="${userLinkDTO.savedFileName}"
                        alt="Sal">
                </a>
            </div>
            <div class="col-md-9">
                <a class="link-dark">${userLinkDTO.nickName}</a><a href="#" class="btn follow" onclick="return message()">쪽지보내기</a>
                <div>
                    <span class="author-description">${userLinkDTO.addr}</span>
                </div>
            </div>
        </div>

        <!-- End Profile -->
         </div>
    </div>

            <div class="col-md-12 p-5 bg-white rounded shadow mb-5">
                <!-- Lined tabs-->
                <ul id="myTab2" role="tablist"
                    class="nav nav-tabs nav-pills with-arrow lined flex-column flex-sm-row text-center">
                    <li class="nav-item flex-sm-fill">
                        <a id="home2-tab" data-toggle="tab" href="#home2" role="tab" aria-controls="home2"
                           aria-selected="true"
                           class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0 active">마켓 게시글</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile1-tab" data-toggle="tab" href="#profile1" role="tab" aria-controls="profile1"
                           aria-selected="false"
                           class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">게시글</a>
                    </li>
                </ul>

                <div id="myTab2Content" class="tab-content">
                    <div id="home2" role="tabpanel" aria-labelledby="home-tab"
                         class="tab-pane fade px-4 py-5 show active">
                        <div id="contentsWrap" class="columns listrecent innerWrap">

                            <div class="col-md-12">
                                <table class="table table-hover">

                                    <thead>
                                    <tr>
                                        <th class="border-0 text-uppercase small font-weight-bold">번호</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">제목</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">날짜</th>
                                    </tr>
                                    </thead>

                                    <tbody class="marketWrap">
                                    <c:forEach var="goods" items="${marketActivityList}">
                                        <tr style=  cursor:pointer;"
                                            onClick="location.href='/market/marketGoods?id=${goods.goodsId}'">
                                            <td>${goods.goodsId}</td>
                                            <td>${goods.title}</td>
                                            <td>${goods.createTime}</td>
                                        </tr>

                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>

                    <div id="profile1" role="tabpanel" aria-labelledby="profile-tab" class="tab-pane fade px-4 py-5">
                        <div id="contentsWrap1" class="columns listrecent">

                            <div class="col-md-12">
                                <table class="table table-hover">

                                    <thead>
                                    <tr >
                                        <th class="border-0 text-uppercase small font-weight-bold">번호</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">제목</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">날짜</th>
                                    </tr>
                                    </thead>

                                    <tbody class="boardWrap">
                                    <c:forEach var="boards" items="${boardActivityList}">
                                        <tr style=cursor:pointer;"
                                            onClick="location.href='/board/content?id=${boards.id}'">
                                            <td>${boards.id}</td>
                                            <td>${boards.title}</td>
                                            <td>${boards.createTime}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>

                </div>
            </div>


                <!-- End lined tabs -->
</div>

<%@ include file="../../fragments/footer.jsp" %>
<script src="/resources/js/user/userPageUserLink.js"></script>
</body>
</html>
