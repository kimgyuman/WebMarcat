<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="notify-popup modal">
    <div class="notify-popMenu">
        <div class="d-flex justify-content-center mt-4">
            <table class="notifyTable">
                <tr>
                    <th>신고 사유</th>
                    <th>신고 횟수</th>
                </tr>
                <tr>
                    <td>스팸홍보/도배글입니다</td>
                    <td id="notify1">0</td>
                </tr>
                <tr>
                    <td>음란물입니다</td>
                    <td id="notify2">0</td>
                </tr>
                <tr>
                    <td>욕설/혐오/차별적 표현이 포함되어 있습니다</td>
                    <td id="notify3">0</td>
                </tr>
                <tr>
                    <td>개인정보 노출 게시물입니다</td>
                    <td id="notify4">0</td>
                </tr>
                <tr>
                    <td>청소년에게 유해한 내용입니다</td>
                    <td id="notify5">0</td>
                </tr>
                <tr>
                    <td>저작권침해 게시물입니다</td>
                    <td id="notify6">0</td>
                </tr>
            </table>
        </div>
        <button class="mt-2" onclick="notifyPopClose()">닫기</button>
    </div>
</div>
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
                <a href="/admin" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>대시보드</a>
                <a href="/admin/memberManagement" class="nav-item nav-link"><i class="fa-solid fa-address-book me-2"></i>회원관리</a>
                <a href="/admin/managerChart" class="nav-item nav-link"><i class="fa-solid fa-chart-column me-2"></i>차트</a>
                <a href="/admin/notifyHistory" class="nav-item nav-link active"><i class="fa-solid fa-list-ul me-2"></i>상품 게시글 신고내역</a>
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


        <!-- Recent Sales Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-sm-12 col-xl-12">
                    <div class="bg-white text-center rounded p-4">
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <h6 class="mb-0">상품 게시글 신고 내역</h6>
                            <a href="/admin/notifyHistory?page=1&search=status&keyword=INACTIVE">정지된 글만 보기</a>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <div>
                                <select id="gn-amountSelect" onchange="gnAmountChange()">
                                    <option value="10">10개씩 보기</option>
                                    <option value="20">20개씩 보기</option>
                                    <option value="30">30개씩 보기</option>
                                </select>
                            </div>
                            <div class="search-layout d-flex justify-content-end">
                                <select id="gn-select">
                                    <option value="nickname">피신고자</option>
                                    <option value="title">게시글 제목</option>
                                </select>
                                <input id="gn-input" type="text" placeholder="Search">
                                <i class="search-icon fa fa-search" onclick="goodsNotifySearch()"></i>
                            </div>
                        </div>

                        <table class="am-table">
                            <tr>
                                <th rowspan="2">피신고자</th>
                                <th rowspan="2">신고 수</th>
                                <th rowspan="2">신고 사유</th>
                                <th colspan="5">신고 게시글</th>
                            </tr>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>등록일자</th>
                                <th>상태</th>
                                <th>활성/비활성</th>
                            </tr>
                            <c:forEach var="notify" items="${allGoodsNotify}">
                                <tr>
                                    <td style="width: 15%;">${notify.nickname}</td>
                                    <td style="width: 8%;">${notify.cnt}</td>
                                    <td style="width: 12%;"><button style="width: 65%;" class="notifyContents-btn">view</button></td>
                                    <td style="width: 8%;">${notify.id}</td>
                                    <td style="width: 25%;"><a href="/market/marketGoods?id=${notify.id}">${notify.title}</a></td>
                                    <td style="width: 10%;">${notify.createTime}</td>
                                    <td style="width: 10%;">${notify.status}</td>
                                    <td style="width: 12%;"><button style="width: 65%;" class="status-btn">button</button></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <nav aria-label="Page navigation example" class="d-flex justify-content-center mt-4">
                            <ul class="pagination">
                                <c:if test="${page.prev }">
                                    <li class="page-item"><a
                                            href="${contextPath}/admin/notifyHistory?real=${page.count}&amount=${page.count}&page=${page.pageParam.page-1}&search=${page.pageParam.search}&keyword=${page.pageParam.keyword}"
                                            class="page-link">이전</a></li>
                                </c:if>

                                <c:forEach var="pageNum" begin="${page.startPage}" end="${page.endPage }" step="1">
                                    <li class="page-item ${pageNum == page.pageParam.page? "active" : "" }"><a
                                            class="page-link" href="?real=${page.count}&amount=${page.count}&page=${pageNum}&search=${page.pageParam.search}&keyword=${page.pageParam.keyword}">${pageNum}</a></li>
                                </c:forEach>

                                <c:if test="${page.next }">
                                    <c:if test ="${page.endPage<page.realEnd}">
                                        <li class="page-item"><a
                                                href="${contextPath}/admin/notifyHistory?real=${page.count}&amount=${page.count}&page=${page.endPage+1}&search=${page.pageParam.search}&keyword=${page.pageParam.keyword}"
                                                class="page-link">다음</a></li>
                                    </c:if>
                                    <c:if test ="${page.endPage>=page.realEnd}">
                                        <li class="page-item"><a
                                                href="${contextPath}/admin/notifyHistory?real=${page.count}&amount=${page.count}&page=${page.endPage}&search=${page.pageParam.search}&keyword=${page.pageParam.keyword}"
                                                class="page-link">다음</a></li>
                                    </c:if>
                                </c:if>
                            </ul>
                        </nav>
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
    <!-- Content End -->


</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>

<!-- Template Javascript -->
<script src="../../../../resources/js/manager/managerMain.js"></script>
<script src="../../../../resources/js/manager/notifyHistory.js"></script>
</body>
</html>