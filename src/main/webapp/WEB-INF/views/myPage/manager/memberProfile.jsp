<%--
  Created by IntelliJ IDEA.
  User: 이정호
  Date: 2022-03-31
  Time: 오후 4:49
  To change this template use File | Settings | File Templates.
--%>
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
    <link href="../../../../resources/css/manager/memberProfile.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../../../../resources/css/manager/style.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid position-relative bg-white d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
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
                <a href="/admin/memberManagement" class="nav-item nav-link active"><i class="fa-solid fa-address-book me-2"></i>회원관리</a>
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

        <!-- Recent Sales Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">
                <div class="col-sm-12 col-xl-12 bg-white">
                    <div class="m-mp-content">
                        <div class="mp-container mt-4 mb-4">
                            <h1>회원정보</h1>
                            <div class="mp-content-card-item1 mt-4">
                                <div class="mp-card-title">프로필</div>
                                <div class="mp-text">
                                    <p>회원번호&emsp;: ${profile.id}</p>
                                    <p>이름&emsp;&emsp;&emsp;: ${profile.name}</p>
                                    <p>아이디&emsp;&emsp;: ${profile.UId}</p>
                                    <p>닉네임&emsp;&emsp;: ${profile.nickname}</p>
                                    <p>핸드폰&emsp;&emsp;: ${profile.phoneNum}</p>
                                    <p>주소&emsp;&emsp;&emsp;: ${profile.addr}</p>
                                    <p>가입일&emsp;&emsp;: ${profile.createTime}</p>
                                    <p>활동&emsp;&emsp;&emsp;: ${profile.activated}</p>
                                    <p>권한&emsp;&emsp;&emsp;: ${profile.roleStatus}</p>
                                </div>
                            </div>
                            <div class="mp-content-card-item2-layout mt-4">
                                <div class="mp-content-card-item2">
                                    <div class="mp-card-title">활동 정보</div>
                                    <table>
                                        <tr>
                                            <td class="mp-table-left">상품 게시글 수</td>
                                            <td class="mp-table-center">:</td>
                                            <td class="mp-table-right">${activity.goodsSum}</td>
                                        </tr>
                                        <tr>
                                            <td class="mp-table-left">상품 게시글 댓글 수</td>
                                            <td class="mp-table-center">:</td>
                                            <td class="mp-table-right">${activity.goodsCommentSum}</td>
                                        </tr>
                                        <tr>
                                            <td class="mp-table-left">게시글 수</td>
                                            <td class="mp-table-center">:</td>
                                            <td class="mp-table-right">${activity.boardSum}</td>
                                        </tr>
                                        <tr>
                                            <td class="mp-table-left">게시글 댓글 수</td>
                                            <td class="mp-table-center">:</td>
                                            <td class="mp-table-right">${activity.boardCommentSum}</td>
                                        </tr>
                                        <tr>
                                            <td class="mp-table-left">찜 수</td>
                                            <td class="mp-table-center">:</td>
                                            <td class="mp-table-right">${activity.wishSum}</td>
                                        </tr>
                                        <tr>
                                            <td class="mp-table-left">신고 수</td>
                                            <td class="mp-table-center">:</td>
                                            <td class="mp-table-right">${activity.reportSum}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="mp-btn mt-5">
                                    <button class="sleepUser">정지</button>
                                    <button class="deleteUser">삭제</button>
                                </div>
                            </div>
                            <!-- item2 end -->
                        </div>
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

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>

<!-- Template Javascript -->
<script src="../../../../resources/js/manager/managerMain.js"></script>
<script src="../../../../resources/js/manager/memberProfile.js"></script>

</body>

</html>