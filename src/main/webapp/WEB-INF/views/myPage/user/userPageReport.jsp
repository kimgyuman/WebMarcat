<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>신고내역</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <!-- Templete Stylesheet -->
    <link rel="stylesheet" type="text/css" href="/resources/css/user/userPageReport.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<%@ include file="../../fragments/header.jsp" %>

<!-- Service Start -->
<div id="popup" class="modal">
    <div id="popmenu">
        <div class="d-flex justify-content-center mt-4">
            <div id="notifyWrap"></div>
        </div>
        <button class="mt-2" id="popButton" onclick="popClose()">닫기</button>
    </div>
</div>

<div id="popup2" class="modal">
    <div id="popmenu2">
        <div class="d-flex justify-content-center mt-4">
            <div id="notifyWrap2"></div>
        </div>
        <button class="mt-2" id="popButton" onclick="popClose2()">닫기</button>
    </div>
</div>


<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/user">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>
        <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">신고당한 내역 </h3>
            </div>
        </div>

            <div class="p-5 bg-white rounded shadow mb-5">
                <!-- Lined tabs-->
                <ul id="myTab2" role="tablist"
                    class="nav nav-tabs nav-pills with-arrow lined flex-column flex-sm-row text-center">
                    <li class="nav-item flex-sm-fill">
                        <a id="home2-tab" data-toggle="tab" href="#home2" role="tab" aria-controls="home2"
                           aria-selected="true"
                           class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0 active">신고된 마켓글</a>
                    </li>
                    <li class="nav-item flex-sm-fill">
                        <a id="profile1-tab" data-toggle="tab" href="#profile1" role="tab" aria-controls="profile1"
                           aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">
                            신고된 게시글</a>
                    </li>
                </ul>
                <div id="myTab2Content" class="tab-content">
                    <div id="home2" role="tabpanel" aria-labelledby="home-tab"
                         class="tab-pane fade px-4 py-5 show active">
                        <c:if test="${empty goodsReportList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">신고된 마켓글이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap" class="columns listrecent innerWrap">

                            <c:forEach var="goods" items="${goodsReportList}">
                                <!-- begin post -->
                                <div class="card">
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
                                                <button type="button" class="notifyView btn btn-sm btn-primary"
                                                        name="sendCkPhone">신고 사유 상세 확인
                                                </button>
                                                <button type="button" class="deleteStopGoods btn btn-sm btn-primary btn-danger ml-4"
                                                        name="sendCkPhone">삭제하기
                                                </button>
                                                <input type="hidden" name="code2" value="${goods.goodsId}">
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
                        <c:if test="${empty boardReportList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/board'">신고된 게시물이 없습니다.</button>
                            </div>
                        </c:if>
                        <div id="contentsWrap1" class="boardColumns listfeaturedtag">
                            <c:forEach var="board" items="${boardReportList}">
                                <div class="card">
                                    <div class="row">
                                        <div class="col-md-5 wrapthumbnail">
                                            <a href="/board/content?id=${board.id}">
                                                <div class="thumbnail" style="background-image:url(${board.boardImages})"></div>
                                            </a>
                                        </div>
                                        <div class="col-md-7">
                                            <div class="card-block">
                                                <h2 class="card-title"><a href="/board/content?id=${board.id}">${board.title}</a></h2>
                                                <h4 class="card-text btxt_post">${board.contents}</h4>
                                                <div class="wrapfooter">
                                                    <span class="author-meta">
                                                        <span class="post-date">${board.createTime}</span>
                                                    </span>
                                                    <div class="row">
                                                        <button type="button" class="boardNotifyView btn btn-sm btn-primary mt-3"
                                                                name="sendCkPhone">신고 사유 상세 확인
                                                        </button>
                                                        <button type="button" class="deleteStopBoard btn btn-sm btn-primary btn-danger ml-4 mt-3"
                                                                name="sendCkPhone">삭제하기
                                                        </button>
                                                        <input type="hidden" name="code2" value="${board.id}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
<%--                                <!-- begin post -->--%>
<%--                                <div class="card">--%>
<%--                                    <a href="/board/content?id=${board.id}">--%>
<%--                                        <img class="img-fluid" src="${board.picture}" alt="">--%>
<%--                                    </a>--%>
<%--                                    <div class="card-block innerWrap">--%>
<%--                                        <h2 class="card-title txt_post"><a--%>
<%--                                                href="/board/content?id=${board.id}">${board.title}</a>--%>
<%--                                        </h2>--%>
<%--                                        <h4 class="card-text">${board.admNm}</h4>--%>
<%--                                        <div class="wrapfooter dayBottom">--%>
<%--                                            <div class="row justify-content-center">--%>
<%--                                                <button type="button" class="boardNotifyView btn btn-sm btn-primary"--%>
<%--                                                        name="sendCkPhone">신고 사유 상세 확인--%>
<%--                                                </button>--%>
<%--                                                <button type="button" class="deleteStopBoard btn btn-sm btn-primary btn-danger ml-4"--%>
<%--                                                        name="sendCkPhone">삭제하기--%>
<%--                                                </button>--%>
<%--                                                <input type="hidden" name="code2" value="${board.id}">--%>
<%--                                            </div>--%>
<%--                                            <span class="post-date mt-2">${board.createTime}</span>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <!-- end post -->--%>
                            </c:forEach>
                        </div>
                </div>
            </div>
    </div> <!-- end wrap -->
</div>

<%@ include file="../../fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="/resources/js/user/userPageReport.js"></script>
</body>
</html>