<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="marcat.goods.vo.GoodsImages" %><%--
  Created by IntelliJ IDEA.
  User: junu
  Date: 2022/03/29
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <link href="../../resources/css/market/marketGoods_style.css" rel="stylesheet" type="text/css">
    <!-- Fonts -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Gothic+A1&family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <link href="/resources/css/mediumish.css" rel="stylesheet" type="text/css">
    <title>market-new</title>
    <script>
        let oneGoodsId = ${oneGoods.id};
        let wish = ${oneGoods.wishList};
        let goodsMemberId = ${oneGoods.memberId};
        let reportResult = ${oneGoods.reportOne};
        let requestOne = ${oneGoods.requestBuy};
        let requestResult = "${oneGoods.requestResult}";
        let loginedId = ${member};
        let loginedIdString = ${memberName};
        let active = "${oneGoods.status}";

    </script>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<!-- Begin Article
================================================== -->
<div class="container">
    <div class="row">
        <div class="col-md-2 menu">
            <div class="share">
                <ul>
                    <li>
                        <p>
                            <a href="#top" class="goods_menu"><i class="ri-arrow-up-s-line"></i></a>
                        </p>
                    </li>
                    <li>
                        <a href="#target" class="goods_menu">
                            <i class="ri-chat-3-line"></i>
                        </a>
                    </li>
                </ul>
                <div class="sep">
                </div>
                <ul>
                    <li class="each_menu">
                        <button type="button" class="report_btn report" id="send_report_btn">
                            <i class="ri-alarm-warning-line"></i>
                        </button>
                    </li>
                    <li class="each_menu">
                        <button type="button" id="wish_btn wish" class="wish_btn wish">
                            <i class="fa-regular fa-heart"></i>
                        </button>
                    </li>
                </ul>
                <div class="sep">
                </div>
                <ul>
                    <li class="each_menu">
                        <a onclick="updateQuestion(${oneGoods.id})"  class="goods_menu">
                            <i class="ri-edit-line"></i>
                        </a>
                    </li>
                    <li class="each_menu">
                        <a onclick="deleteQuestion(${oneGoods.id})" class="goods_menu">
                            <i class="ri-delete-bin-line"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>


        <!-- Begin Post -->
        <div class="innerRow" id="#top">
            <div class="mainHeading">
                <!-- Begin Top Meta -->
                <div class="hed">
                    <div class="hed_1">
                        <a href="/user/userLink?id=${oneGoods.memberId}" class="hed_1"><img class="author-thumb"
                                                                 src="${oneGoods.memberImages}"
                                                                 alt="${oneGoods.nickName}"></a>
                    </div>
                    <div class="hed_2">
                        <a class="link-dark" id="hedName" href="/user/userLink?id=${oneGoods.memberId}">${oneGoods.nickName}</a><a onclick="sendMessageModal(${oneGoods.memberId})" class="btn follow sendMessageInMarket">쪽지보내기</a>
                        <div class="author-area">"${oneGoods.koreaArea}"</div>
                        <div class="post-date">${oneGoods.createTime}</div>
                    </div>
                </div>
                <!-- End Top Menta -->
            </div>
            <!-- Begin Tags -->
            <div class="tag_divs">
                <ul class="tags">
                    <li><a># ${oneGoods.categoriesName}</a></li>
                    <li><a># 네고:${oneGoods.negoStatus}</a></li>
                    <li><a># 좋아요:${oneGoods.wishCount}</a></li>
                    <li><a># 조회수:${oneGoods.viewCount}</a></li>
                    <li><a># 판매상태:${oneGoods.sellStatus}</a></li>
                </ul>
            </div>
            <!-- End Tags -->
            <!-- Begin Featured Image 이미지 부분 -->
            <div class="slider">
                <c:forEach var="radios" begin="1" end="${fn:length(oneGoods.goodsImages)}">
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
                    <c:forEach var="images" items="${oneGoods.goodsImages}">
                        <c:if test="${images != null}">
                            <li><img class="featured-image img-fluid" src="${images}"></li>
                        </c:if>
                    </c:forEach>
                </ul>
                <div class="bullets">
                    <c:forEach var="radios" begin="1" end="${fn:length(oneGoods.goodsImages)}">
                        <label for="slide${radios}">&nbsp;</label>
                    </c:forEach>
                </div>
            </div>
            <!-- End Featured Image -->

            <div class="goods_card">
                <div class="card-title">제목 : ${oneGoods.title}</div>
                <hr/>
                <div class="card-price">가격 : <fmt:formatNumber value="${oneGoods.price}" pattern="##,###,###,###"/> 원</div>

                <hr/>
                <!-- Begin Post Content -->
                <div class="card-content">
                        ${oneGoods.contents}
                </div>
                <!-- End Post Content -->
                <div class="toggle_menu"><!--disabled-->
                    <button type="button" class="toggle_report report" id="toggle_report_btn">
                        <i class="ri-alarm-warning-line"></i>
                    </button>
                    <button type="button" id="toggle_wish_btn wish" class="toggle_like wish">
                        <i class="fa-regular fa-heart"></i>
                    </button>
                    <a href="/user/market/marketGoods/DeleteGoods?id=${oneGoods.id}" class="toggle_delete">
                        <i class="ri-delete-bin-line"></i>
                    </a>
                    <a href="/user/market/GoodsRemake?id=${oneGoods.id}" class="toggle_update">
                        <i class="ri-edit-line"></i>
                    </a>
                </div>
                <sec:authorize access="isAnonymous()">
                    <div class="buy_btn_area">
                        <input type="button" class="marketComment_btn" onclick="location.href='/login'"
                               value="구매 요청하기">
                    </div>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <div class="buy_btn_area">
                        <input type="button" class="buy_btn" id="buy_btn"/>
                    </div>
                </sec:authorize>


                <div id="target"></div>
                <%@ include file="marketComment.jsp" %>

            </div>
        </div>
        <!-- End Post -->
    </div>
</div>
<%@ include file="../fragments/footer.jsp" %>
<script src="/resources/js/market/GoodsDetail.js"></script>
</body>
</html>
