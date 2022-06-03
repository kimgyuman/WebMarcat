<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>위시리스트</title>

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet" type="text/css">
    <link href="../../resources/css/blog.css" rel="stylesheet" type="text/css">
    <!-- Template Stylesheet -->
    <link rel="stylesheet" type="text/css" href="/resources/css/user/userPageWishList.css">

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
                <h3 class="mb-0">위시리스트</h3>
            </div>
            <div class="p-5 bg-white rounded shadow mb-5">
                <!-- Lined tabs-->
                <ul id="myTab2" role="tablist"
                    class="nav nav-tabs nav-pills with-arrow lined flex-column flex-sm-row text-center">
                    <li class="nav-item flex-sm-fill">
                        <a id="home2-tab" data-toggle="tab" href="#home2" role="tab" aria-controls="home2"
                           aria-selected="true"
                           class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0 active">마켓 위시리스트</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile1-tab" data-toggle="tab" href="#profile1" role="tab" aria-controls="profile1"
                           aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">
                            게시글 위시리스트</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile2-tab" data-toggle="tab" href="#profile2" role="tab" aria-controls="profile2"
                           aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">거래
                            요청한 상품</a>
                    </li>
                </ul>
                <div id="myTab2Content" class="tab-content">
                    <div id="home2" role="tabpanel" aria-labelledby="home-tab"
                         class="tab-pane fade px-4 py-5 show active">
                        <c:if test="${empty wishGoodsList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">위시리스트가 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap" class="columns listrecent innerWrap">
                            <c:forEach var="goods" items="${wishGoodsList}">
                                <!-- begin post -->
                                <div class="card">
                                    <a href="/market/marketGoods/${goods.goodsId}">
                                        <img class="img-fluid" src="${goods.picture}" alt="">
                                    </a>
                                    <div class="card-block innerWrap">
                                        <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                                href="/market/marketGoods?id=${goods.goodsId}">${goods.title}</a>
                                        </h2>
                                        <h4 class="card-text">${goods.juso}</h4>
                                        <div class="wrapfooter dayBottom">
                                            <div class="row justify-content-center">
                                                    <button type="button" class="deleteWish btn btn-sm btn-danger mr-3"
                                                            name="sendCkPhone">찜 취소하기
                                                    </button>
                                                <input type="hidden" value="${goods.id}"/>
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
                        <c:if test="${empty boardWishList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">게시판 위시리스트가 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap2" class="boardColumns listfeaturedtag">
                            <c:forEach var="board" items="${boardWishList}">
                                <div class="card" id="boardWishCard">
                                    <div class="row">
                                        <div class="col-md-5 wrapthumbnail">
                                            <a href="/board/content?id=${board.id}">
                                                <div class="thumbnail" style="background-image:url(${board.picture})"></div>
                                            </a>
<%--                                            <a href="/board/content?id=${board.id}">--%>
<%--                                                <img class="img-fluid" src="${board.picture}" alt="">--%>
<%--                                            </a>--%>
                                        </div>
                                        <div class="col-md-7">
                                            <div class="card-block">
                                                <h2 class="card-title txt_post"><a href="/board/content?id=${board.boardId}">${board.title}</a></h2>
                                            <div class="card-block innerWrap">
                                                <h5 class="card-text">${board.juso}</h5>
                                                    <div class="wrapfooter dayBottom">
                                                        <div class="row justify-content-center">
                                                            <button type="button" class="deleteBoardWish btn btn-sm btn-danger mr-3"
                                                             name="sendCkPhone" onclick="deleteBoard(${board.id})">찜 취소하기
                                                            </button>
                                                            <input type="hidden" value="${board.id}"/>
                                                        </div>
                                                        <span class="post-date mt-2">${board.createTime}</span>
                                                    </div>
                                               </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

<%--                                <div class="card">--%>
<%--                                    <a href="/market/marketGoods/${board.boardId}">--%>
<%--                                        <img class="img-fluid" src="${board.picture}" alt="">--%>
<%--                                    </a>--%>
<%--                                    <div class="card-block innerWrap">--%>
<%--                                        <h2 class="card-title txt_post"><a--%>
<%--                                                href="/market/marketGoods?id=${board.boardId}">${board.title}</a>--%>
<%--                                        </h2>--%>
<%--                                        <h4 class="card-text">${board.juso}</h4>--%>
<%--                                        <div class="wrapfooter dayBottom">--%>
<%--                                            <div class="row justify-content-center">--%>
<%--                                                <button type="button" class="deleteWish btn btn-sm btn-danger"--%>
<%--                                                        name="sendCkPhone">찜 취소하기--%>
<%--                                                </button>--%>
<%--                                                <input type="hidden" value="${board.id}"/>--%>
<%--                                            </div>--%>
<%--                                            <span class="post-date mt-2">${board.createTime}</span>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
                                <!-- end post -->


                    <div id="profile2" role="tabpanel" aria-labelledby="profile-tab" class="tab-pane fade px-4 py-5">
                        <c:if test="${empty requiredGoodsList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">거래 요청한 상품이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap1" class="columns listrecent">
                            <c:forEach var="goods" items="${requiredGoodsList}">
                                <!-- begin post -->
                                <div class="card">
                                    <a href="/market/marketGoods/${goods.goodsId}">
                                        <img class="img-fluid" src="${goods.picture}" alt="">
                                    </a>
                                    <div class="card-block innerWrap">
                                        <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                                href="/market/marketGoods/${goods.goodsId}">${goods.title}</a>
                                        </h2>
                                        <h4 class="card-text">${goods.juso}</h4>
                                        <div class="wrapfooter dayBottom">
                                            <div class="row justify-content-center">
                                                    <button type="button" class="deleteRequest btn btn-sm btn-danger"
                                                            name="sendCkPhone">요청 취소하기
                                                    </button>
                                                <input type="hidden" value="${goods.requestId}"/>
                                            </div>
                                            <span class="post-date mt-2">${goods.createTime}</span>
                                        </div>
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
<script src="/js/user/userPageWishList.js"></script>
</body>
</html>
