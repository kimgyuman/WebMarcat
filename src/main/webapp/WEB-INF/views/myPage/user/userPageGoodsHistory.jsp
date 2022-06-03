<%@ page import="marcat.goods.dto.GoodsDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>상품 거래 내역</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet" type="text/css">

    <%--    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">--%>
    <!-- Templete Stylesheet -->
    <link rel="stylesheet" href="/css/user/userPageGoodsHistory.css" type="text/css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../../fragments/header.jsp" %>

<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/user">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>

            <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">상품 내역</h3>
            </div>

            <div class="p-5 bg-white rounded shadow mb-5">
                <!-- Lined tabs-->
                <ul id="myTab2" role="tablist"
                    class="nav nav-tabs nav-pills with-arrow lined flex-column flex-sm-row text-center">
                    <li class="nav-item flex-sm-fill">
                        <a id="home2-tab" data-toggle="tab" href="#home2" role="tab" aria-controls="home2"
                           aria-selected="true"
                           class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0 active">판매중인 상품</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile1-tab" data-toggle="tab" href="#profile1" role="tab" aria-controls="profile1"
                           aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">거래
                            요청 온 상품</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile2-tab" data-toggle="tab" href="#profile2" role="tab" aria-controls="profile2"
                           aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">판매
                            완료 상품</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile3-tab" data-toggle="tab" href="#profile3" role="tab" aria-controls="profile3"
                           aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">마켓 상품
                            댓글</a>
                    </li>
                </ul>
                <div id="myTab2Content" class="tab-content">
                    <div id="home2" role="tabpanel" aria-labelledby="home-tab"
                         class="tab-pane fade px-4 py-5 show active">
                        <c:if test="${empty sellingGoodsList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">판매 중인 상품이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap" class="columns listrecent innerWrap">
                            <c:forEach var="goods" items="${sellingGoodsList}">
                                <!-- begin post -->
                                <div class="card" id="sellingCard">
                                    <a href="/market/marketGoods?id=${goods.goodsId}">
                                        <img class="img-fluid" src="${goods.picture}" alt="">
                                    </a>
                                    <div class="card-block innerWrap">
                                        <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                                href="/market/marketGoods/${goods.goodsId}">${goods.title}</a>
                                        </h2>
                                        <h4 class="card-text">${goods.juso}</h4>
                                        <div class="wrapfooter dayBottom">
                                            <div class="row justify-content-center">
                                                <a href="/user/market/GoodsRemake/?id=${goods.goodsId}">
                                                    <button type="button" class="btn btn-sm btn-primary mr-4"
                                                            name="sendCkPhone">수정하기
                                                    </button>
                                                </a>
                                                    <button type="button" class="deleteMyGoods btn btn-sm btn-primary btn-danger mr-4"
                                                            name="sendCkPhone">삭제하기
                                                    </button>
                                                <input type="hidden" value="${goods.goodsId}"/>
                                            </div>
                                            <span class="post-date mt-2">${goods.createTime}</span>
                                        </div>
                                    </div>
                                </div>
                                <!-- end post -->
                            </c:forEach>
                        </div>
                    </div>
                    <div id="profile1" role="tabpanel" aria-labelledby="profile-tab" class="tab-pane fade px-4 py-5">
                        <c:if test="${empty requiredGoodsList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">거래 요청 온 상품이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap1" class="columns listrecent">
                            <c:forEach var="goods" items="${requiredGoodsList}">
                                <c:if test="${goods.requestStatus == '수락됨'}">
                                    <div class="card" id="reCard">
                                        <a href="/market/marketGoods?id=${goods.goodsId}">
                                            <img class="img-fluid" src="${goods.picture}" alt="">
                                        </a>
                                        <div class="card-block innerRequestWrap">
                                            <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                                    href="/market/marketGoods/${goods.goodsId}">${goods.title}</a>
                                            </h2>
                                            <h4 class="card-text">${goods.juso}</h4>
                                            <div class="wrapfooter dayBottom">
                                                <div class="authorSize row justify-content-center mr-2">거래 상대 :&nbsp <a
                                                        href="/user/${goods.requestMemberId}">${goods.nick}</a>
                                                </div>
                                                <div class="row justify-content-center">
                                                    <button type="button" class="soldAccept btn btn-sm btn-primary mr-4"
                                                            name="sendCkPhone">판매완료</button>
                                                    <button type="button" class="deleteAccept btn btn-sm btn-danger mr-4"
                                                            name="sendCkPhone">취소하기</button>
                                                    <input type="hidden" name="code" value="${goods.requestId}">
                                                    <input type="hidden" name="code2" value="${goods.goodsId}">
                                                </div>
                                                <span class="post-date mt-2">${goods.createTime}</span>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${goods.requestStatus == '요청중'}">
                                    <div class="card">
                                        <a href="/market/marketGoods?id=${goods.goodsId}">
                                            <img class="img-fluid" src="${goods.picture}" alt="">
                                        </a>
                                        <div class="card-block innerRequestWrap">
                                            <h2 class="card-title txt_post" id="h2Value">(${goods.sellStatus})<a
                                                    href="/market/marketGoods/${goods.goodsId}">${goods.title}</a>
                                            </h2>
                                            <h4 class="card-text">${goods.juso}</h4>
                                            <div class="wrapfooter dayBottom">
                                                <div class="authorSize row justify-content-center mr-2">요청자 :&nbsp <a
                                                        href="/user/${goods.requestMemberId}">${goods.nick}</a>
                                                </div>
                                                <div class="row justify-content-center">
                                                        <button type="button" class="requestAccept btn btn-sm btn-primary mr-4"
                                                                name="sendCkPhone">수락하기
                                                        </button>
                                                        <button type="button" class="requestRefuse btn btn-sm btn-primary btn-danger mr-4"
                                                                name="sendCkPhone">거절하기
                                                        </button>
                                                    <input type="hidden" name="code" value="${goods.requestId}">
                                                    <input type="hidden" name="code2" value="${goods.goodsId}">
                                                </div>
                                                <span class="post-date mt-2">${goods.createTime}</span>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>

                            </c:forEach>
                        </div>
                    </div>
                    <div id="profile2" role="tabpanel" aria-labelledby="profile-tab" class="tab-pane fade px-4 py-5">
                        <c:if test="${empty soldGoodsList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">거래 완료된 상품이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap2" class="columns listrecent">
                            <c:forEach var="goods" items="${soldGoodsList}">
                                <!-- begin post -->
                                <div class="card">
                                    <a href="/market/marketGoods?id=${goods.goodsId}">
                                        <img class="img-fluid" src="${goods.picture}" alt="">
                                    </a>
                                    <div class="card-block innerSoldWrap">
                                        <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                                href="/market/marketGoods/${goods.goodsId}">${goods.title}</a>
                                        </h2>
                                        <h4 class="card-text">${goods.juso}</h4>
                                        <div class="wrapfooter dayBottom">
                                            <span class="post-date mt-2">${goods.createTime}</span>
                                        </div>
                                    </div>
                                </div>
                                <!-- end post -->
                            </c:forEach>
                        </div>
                    </div>
                    <div id="profile3" role="tabpanel" aria-labelledby="profile-tab" class="tab-pane fade px-4 py-5">
                        <c:if test="${empty commentList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">작성한 댓글이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap3" class="columnsComment listrecent">
                            <c:forEach var="goods" items="${commentList}">
                                <!-- begin post -->
                                <div class="card">
                                    <div class="card-block">
                                        <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                                href="/market/marketGoods?id=${goods.goodsId}">${goods.title}</a>
                                        </h2>
                                    </div>
                                    <div class="card-block">
                                        <div class="mr-4">
                                            <a><img class="rounded-circle" src="${goods.memberPicture}"alt="..."/></a>
                                        </div>
                                        <h4 class="card-text">${goods.contents}</h4><hr>
                                        <h4 class="card-text">${goods.createTime}</h4>
                                    </div>
                                </div>
                                <!-- end post -->
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!-- End lined tabs -->
            </div>
        </div>

    </div>
</div>
<%@ include file="../../fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="/js/user/userPageGoodsReport.js"></script>
</body>
</html>
