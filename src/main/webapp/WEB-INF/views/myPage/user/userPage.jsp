<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>마이페이지</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">


    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <!-- userPage -->
    <link href="/resources/css/user/userPage.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css">

</head>
<body>
<%@ include file="../../fragments/header.jsp" %>

<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/user">MY PAGE</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>
        <div class="row g-5">
            <div class="col-lg-4 col-md-6 menuCol">
                <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                    <div class="service-icon">
                        <i class="fa fa-user fa-2x text-black"></i>
                    </div>
                    <h4 class="mb-3">프로필 수정</h4>
                    <p class="m-0">정보 수정</p>
                    <p class="m-0">회원 탈퇴</p>
                    <a class="btn btn-lg btn-primary rounded" href="/user/userInfo">
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 menuCol">
                <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                    <div class="service-icon">
                        <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                    </div>
                    <h4 class="mb-3">활동 내역</h4>
                    <p class="m-0">게시판 게시물</p>
                    <p class="m-0">게시판 댓글</p>
                    <a class="btn btn-lg btn-primary rounded" href="/user/activityLog">
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 menuCol">
                <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                    <div class="service-icon">
                        <i class="fa fa-cubes fa-2x text-black"></i>
                    </div>
                    <h4 class="mb-3">나의 거래</h4>
                    <p class="m-0">거래중 상품</p>
                    <p class="m-0">거래완료 상품</p>
                    <a class="btn btn-lg btn-primary rounded" href="/user/goodsHistory">
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 menuCol">
                <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                    <div class="service-icon">
                        <i class="fa fa-cart-plus fa-2x" aria-hidden="true"></i>
<%--                        <i class="fa fa-commenting" aria-hidden="true"></i>--%>
                    </div>
                    <h4 class="mb-3">위시리스트</h4>
                    <p class="m-0">마켓 위시리스트</p>
                    <p class="m-0">게시판 위시리스트</p>
                    <p class="m-0">거래 요청한 상품</p>
                    <a class="btn btn-lg btn-primary rounded" href="/user/wishList">
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 menuCol">
                <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                    <div class="service-icon">
                        <i class="fa fa-user-times fa-2x" aria-hidden="true"></i>
                    </div>
                    <h4 class="mb-3">신고 당한 내역</h4>
                    <p class="m-0">신고 당한 마켓글</p>
                    <p class="m-0">신고 당한 게시글</p>
                    <a class="btn btn-lg btn-primary rounded" href="/user/report">
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 menuCol">
                <div class="service-item bg-light rounded d-flex flex-column align-items-center justify-content-center text-center">
                    <div class="service-icon">
                        <i class="fa fa-envelope-open fa-2x text-black"></i>
                    </div>
                    <h4 class="mb-3">나의 쪽지함</h4>
                    <p class="m-0">보낸 쪽지함</p>
                    <p class="m-0">받은 쪽지함</p>
                    <a class="btn btn-lg btn-primary rounded" href="/user/message">
                        <i class="bi bi-arrow-right"></i>
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- MyPage Menu End -->


<!-- 최근본 상품 slide -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-4 mx-auto">
            <h5 class="fw-bold text-primary text-uppercase">거래 요청온 리스트</h5>
        </div>
        <c:if test="${empty requiredGoodsList}">
        <div class="empty">
            <button type="button" class="btn btn-primary w-30 py-3"
                    onClick="location.href='/market'">거래 요청온 상품이 없습니다.</button>
        </div>
        </c:if>
            <section class="blog_section">
                <div class="container">
                    <div class="blog_content">
                        <div class="owl-carousel owl-theme">

                        <c:forEach var="goods" items="${requiredGoodsList}">
                            <div class="blog_item">
                                <div class="blog_image">
                                    <a href="/market/marketGoods?id=${goods.goodsId}">
                                        <img class="img-fluid" src="${goods.picture}" alt="">
                                    <span><i class="icon ion-md-create"></i></span></a>
                                </div>

                                <div class="blog_details">
                                    <div class="blog_title">
                                        <h5><a href="/market/marketGoods?id=${goods.goodsId}"></a>${goods.title}</h5>
                                    </div>
                                    <ul>
                                        <li>${goods.sellStatus}</li>
                                    </ul>
                                    <div class="requestUser d-flex flex-column text-center">
                                         <h5>거래 요청 시간</h5>
                                         <a href="javascript:void(0);">${goods.createTime}</a>
                                         <h5>거래 요청인</h5>
                                         <a href="/user/userLink?id=${goods.requestMemberId}">${goods.nick}</a>
                                    </div>

                                    <p><a href="/user/goodsHistory">
                                        ALL RequiredGoods<i class="icofont-long-arrow-right"></i></a></p>
                                </div>

                            </div>
                        </c:forEach>


                        </div>
                    </div>
                </div>
            </section>

        <!-- 위시 리스트 -->
        <div class="container-fluid py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="section-title text-center position-relative pb-3 mb-4 mx-auto" >
                    <h5 class="fw-bold text-primary text-uppercase">위시리스트</h5>
                </div>
                <c:if test="${empty wishGoodsList}">
                    <div class="empty">
                        <button type="button" class="btn btn-primary w-30 py-3"
                        onClick="location.href='/market'">
                            위시리스트를 추가하세요!</button>
                    </div>
                </c:if>
                <section class="blog_section">
                    <div class="container">
                        <div class="blog_content">
                            <div class="owl-carousel owl-theme">
                                <c:forEach var="goods" items="${wishGoodsList}">
                                <div class="blog_item">
                                    <div class="blog_image">
                                    <a href="/market/marketGoods?id=${goods.goodsId}">
                                    <img class="img-fluid" src="${goods.picture}" >
                                    <span><i class="icon ion-md-create"></i></span></a>
                                    </div>
                                    <div class="blog_details">
                                        <div class="blog_title">
                                            <h5><a href="/market/marketGoods?id=${goods.goodsId}"></a>${goods.title}</h5>
                                        </div>
                                        <ul>
                                            <li>${goods.sellStatus}</li>
                                            <li>${goods.createTime}</li>
                                        </ul>
                                        <p><a href="/user/wishList">
                                        All WishList<i class="icofont-long-arrow-right"></i></a></p>
                                    </div>
                                </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </section>

        </div>
    </div>
    <!-- Testimonial End -->



<%@ include file="../../fragments/footer.jsp" %>


<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- bootstrap -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- carousel -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.js"></script>

<script type="text/javascript" src="/resources/js/user/userPage.js"></script>

</body>

</html>


