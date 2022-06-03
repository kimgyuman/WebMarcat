<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Marcat</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../../../../resources/css/manager/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../../../../resources/css/manager/style.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid position-relative bg-white d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner"
         class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <!-- Sidebar Start -->
    <div class="sidebar pe-4 pb-3">
        <nav class="navbar">
            <a href="/admin" class="navbar-brand mx-4 mb-3">
                <h3 class="text-primary">&nbsp;&nbsp;<i class="fa fa-hashtag me-2"></i>ADMIN</h3>
            </a>

            <div class="navbar-nav w-100">
                <a href="/admin" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>대시보드</a>
                <a href="/admin/memberManagement" class="nav-item nav-link"><i class="fa-solid fa-address-book me-2"></i>회원관리</a>
                <a href="/admin/managerChart" class="nav-item nav-link"><i class="fa-solid fa-chart-column me-2"></i>차트</a>
                <a href="/admin/notifyHistory" class="nav-item nav-link"><i class="fa-solid fa-list-ul me-2"></i>상품 게시글 신고내역</a>
                <a href="/admin/boardNotifyHistory" class="nav-item nav-link"><i class="fa-solid fa-list-ul me-2"></i>게시글 신고내역</a>
                <a href="/admin/goodsList" class="nav-item nav-link"><i class="fa-solid fa-border-all me-2"></i>전체 상품 게시글</a>
                <a href="/admin/boardList" class="nav-item nav-link"><i class="fa-solid fa-border-all me-2"></i>전체 게시글</a>
                <a href="/admin/adManagement" class="nav-item nav-link"><i class="fa-solid fa-rectangle-ad me-2"></i>광고 관리</a>
            </div>
        </nav>
    </div>
    <!-- Sidebar End -->


    <!-- Content Start -->
    <div class="content bg-light">

        <!-- Navbar Start -->
        <div class="nav-header">
            <nav class="navbar navbar-expand navbar-light sticky-top px-4 py-0">
                <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                <a href="#" class="sidebar-toggler flex-shrink-0">
                    <i class="fa fa-bars"></i>
                </a>


                <div class="navbar-nav align-items-center ms-auto">
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <i class="fa-solid fa-user-large"></i>
                            <span class="d-none d-lg-inline-flex">admin</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="/" class="dropdown-item">홈으로</a>
                            <a href="/logout" class="dropdown-item">로그아웃</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->

        <input id="today" type="hidden" value="${goodsCnt.today}">
        <input id="oneAgo" type="hidden" value="${goodsCnt.oneAgo}">
        <input id="twoAgo" type="hidden" value="${goodsCnt.twoAgo}">
        <input id="threeAgo" type="hidden" value="${goodsCnt.threeAgo}">
        <input id="fourAgo" type="hidden" value="${goodsCnt.fourAgo}">
        <input id="fiveAgo" type="hidden" value="${goodsCnt.fiveAgo}">
        <input id="sixAgo" type="hidden" value="${goodsCnt.sixAgo}">

        <input id="bdToday" type="hidden" value="${boardCnt.today}">
        <input id="bdOneAgo" type="hidden" value="${boardCnt.oneAgo}">
        <input id="bdTwoAgo" type="hidden" value="${boardCnt.twoAgo}">
        <input id="bdThreeAgo" type="hidden" value="${boardCnt.threeAgo}">
        <input id="bdFourAgo" type="hidden" value="${boardCnt.fourAgo}">
        <input id="bdFiveAgo" type="hidden" value="${boardCnt.fiveAgo}">
        <input id="bdSixAgo" type="hidden" value="${boardCnt.sixAgo}">

        <!-- Sales Chart Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">주간 상품 게시글 수</h6>
                        </div>
                        <canvas id="myChart"></canvas>
                    </div>
                </div>
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">주간 게시글 수</h6>
                        </div>
                        <canvas id="myChart1"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Sales Chart End -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">최근 상품 게시글</h6>
                            <a href="/admin/goodsList">Show All</a>
                        </div>
                        <table class="d-table">
                            <tr>
                                <th>닉네임</th>
                                <th>카테고리</th>
                                <th>제목</th>
                                <th>등록일자</th>
                                <th>가격</th>
                            </tr>
                            <c:forEach var="goods" items="${latelyGoods}">
                                <tr>
                                    <td style="width: 15%;">${goods.nickname}</td>
                                    <td style="width: 20%;">${goods.name}</td>
                                    <td style="width: 32%;"><a href="/market/marketGoods?id=${goods.goodsId}">${goods.title}</a></td>
                                    <td style="width: 18%;">${goods.createTime}</td>
                                    <td style="width: 15%;"><fmt:formatNumber value="${goods.price}" pattern="##,###,###,###"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">최근 게시글</h6>
                            <a href="/admin/boardList">Show All</a>
                        </div>
                        <table class="d-table">
                            <tr>
                                <th>닉네임</th>
                                <th>제목</th>
                                <th>조회수</th>
                                <th>등록일자</th>
                            </tr>
                            <c:forEach var="board" items="${latelyBoard}">
                                <tr>
                                    <td style="width: 20%;">${board.nickname}</td>
                                    <td style="width: 45%;"><a href="/board/content?id=${board.boardId}"> ${board.title}</a></td>
                                    <td style="width: 15%;">${board.viewCount}</td>
                                    <td style="width: 20%;">${board.createTime}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>


        <!-- Recent Sales Start -->

        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">상품 게시글 신고 내역</h6>
                            <a href="/admin/notifyHistory">Show All</a>
                        </div>
                        <table class="d-table">
                            <tr>
                                <th>신고자</th>
                                <th>피신고자</th>
                                <th>신고 사유</th>
                            </tr>
                            <c:forEach var="notifyHistory" items="${latelyGoodsNotify}">
                                <tr>
                                    <td style="width: 20%;">${notifyHistory.reporter}</td>
                                    <td style="width: 20%;">${notifyHistory.nickname}</td>
                                    <td style="width: 60%;">${notifyHistory.contents}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div class="col-sm-12 col-xl-6">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">게시글 신고 내역</h6>
                            <a href="/admin/boardNotifyHistory">Show All</a>
                        </div>
                        <table class="d-table">
                            <tr>
                                <th>신고자</th>
                                <th>피신고자</th>
                                <th>신고 사유</th>
                            </tr>
                            <c:forEach var="notifyHistory" items="${latelyBoardNotify}">
                                <tr>
                                    <td style="width: 20%;">${notifyHistory.reporter}</td>
                                    <td style="width: 20%;">${notifyHistory.nickname}</td>
                                    <td style="width: 60%;">${notifyHistory.contents}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>



        <!-- Footer Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="bg-light rounded-top p-4">
                <div class="row">
                    <div class="col-12 col-sm-6 text-center text-sm-start">
                        &copy; <a href="/">Marcat</a>, All Right Reserved.
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->
    </div>
</div>
<!-- Content End -->


</div>

<!-- JavaScript Libraries -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.7.1/dist/chart.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>

<!-- Template Javascript -->
<script src="../../../../resources/js/chart/dashBoardChart.js"></script>
<script src="../../../../resources/js/manager/managerMain.js"></script>
</body>
</html>
