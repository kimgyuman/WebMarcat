<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet" type="text/css">

    <!-- Template Stylesheet -->
    <link rel="stylesheet" type="text/css" href="/resources/css/user/userPageActivityLog.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>활동내역</title>
</head>

<body>
<%@ include file="../../fragments/header.jsp" %>
<!-- 필요한 데이터 작성게시물 썸네일 표시, 게시물 url,게시물 제목 표시 ,게시물 작성 시간-->
<!-- 댓글 ) 썸네일 표시, 게시물 url, 게시물 작성 시간, 댓글 내용 표시 -->

<!-- Service Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/user">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>

        <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">활동 내역</h3>
            </div>
        </div>

        <div class="p-5 bg-white rounded shadow mb-5">
            <!-- Lined tabs-->
            <ul id="myTab3" role="tablist"
                class="nav nav-tabs nav-pills with-arrow lined flex-column flex-sm-row text-center">
                <li class="nav-item flex-sm-fill">
                    <a id="main-tab" data-toggle="tab" href="#main" role="tab" aria-controls="main"
                       aria-selected="true"
                       class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0 active">작성한 게시물</a>
                </li>
                <li class="nav-item flex-sm-fill">
                    <a id="sub-tab" data-toggle="tab" href="#sub" role="tab" aria-controls="sub"
                       aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">작성한
                        댓글</a>
                </li>


            </ul>
            <div id="myTab2Content" class="tab-content">
                <div id="main" role="tabpanel" aria-labelledby="main-tab"
                     class="tab-pane fade px-4 py-5 show active">
                    <c:if test="${empty myActivityBoardList}">
                        <div class="empty">
                            <button type="button" class="btn btn-primary w-30 py-3"
                                    onClick="location.href='/board'">활동 게시물이 없습니다.</button>
                        </div>
                    </c:if>
                    <div id="contentsWrap" class="boardColumns listfeaturedtag">
                        <c:forEach var="board" items="${myActivityBoardList}">
                            <!-- begin post -->
                            <div class="card" id="boardCard">
                                <div class="row">
                                    <div class="col-md-5 wrapthumbnail">
                                        <a href="/board/content?id=${board.id}">
                                            <div class="thumbnail" style="background-image:url(${board.picture})"></div>
                                        </a>
                                    </div>
                                    <div class="col-md-7">
                                        <div class="card-block">
                                            <h2 class="card-title"><a href="/board/content?id=${board.id}">${board.title}</a></h2>
                                            <h4 class="card-text btxt_post">${board.contents}</h4>
                                            <div class="wrapfooter">
                                                <span class="post-date">${board.createTime}</span>
                                                <div class="row">
                                                    <a href="/user/board/update?id=${board.id}">
                                                        <button type="button" class="btn btn-sm btn-primary mt-3 ml-5"
                                                                name="sendCkPhone">수정하기
                                                        </button>
                                                    </a>
                                                    <button type="button" class="deleteMyBoard btn btn-sm btn-primary btn-danger ml-4 mt-3"
                                                            name="sendCkPhone" onclick="deleteBoard(${board.id})">삭제하기
                                                        <input type="hidden" value="${board.id}"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div id="sub" role="tabpanel" aria-labelledby="sub-tab" class="tab-pane fade px-4 py-5">
                    <c:if test="${empty myActivityBoardCommentList}">
                        <div class="empty">
                            <button type="button" class="btn btn-primary w-30 py-3"
                                    onClick="location.href='/board'">활동 댓글이 없습니다.</button>
                        </div>
                    </c:if>
                    <div id="contentsWrap3" class="columnsComment listrecent">
                        <c:forEach var="board" items="${myActivityBoardCommentList}">

                            <!-- begin post -->
                            <div class="card">
                                <div class="card-block">
                                    <h2 class="card-title txt_post"><a
                                            href="/board/content?id=${board.boardId}">
                                            Re : ${board.title}</a>
                                    </h2>
                                </div>
                                <div class="card-block">
                                    <div class="mr-4">
                                        <a><img class="rounded-circle" src="${board.memberPicture}"alt="..."/></a>
                                    </div>
                                    <h4 class="card-text btxt_post">${board.contents}</h4><hr>
                                    <div class="wrapfooter">
                                        <span class="post-date">${board.createTime}</span>
                                    </div>
                                </div>

                            </div>
                            <!-- end post -->


                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- end wrap -->
</div>
<%@ include file="../../fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="/resources/js/user/userPageActivityLog.js"></script>
</body>
</html>
