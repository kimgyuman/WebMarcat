<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="../../resources/css/blog.css" rel="stylesheet" type="text/css">
    <link href="../../resources/css/home.css" rel="stylesheet" type="text/css">
    <!-- Fonts -->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">
    <link href="../../resources/js/slick/slick.css" rel="stylesheet">
    <link href="../../resources/js/slick/slick-theme.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap"
          rel="stylesheet">
</head>
<body>
<%@ include file="fragments/header.jsp" %>
<div class="container">
    <div class="mainheading">
        <h1 class="sitetitle">Marcat</h1>
        <p class="lead">
            Good Day Good Items!
        </p>
    </div>
    <!-- End Site Title
    ================================================== -->

    <!-- Begin List Posts
    ================================================== -->
    <section class="recent-posts">
        <div class="section-title row justify-content-between mx-1">
            <h2><span>Items</span></h2><a href="/market">더보기</a>
        </div>
        <div class="columns listrecent">
            <c:forEach var="goods" items="${sellingGoodsList}">
                <!-- begin post -->
                <div class="card">
                    <a href="/market/marketGoods?id=${goods.goodsId}">
                        <img class="img-fluid" src="${goods.picture}" alt="">
                    </a>
                    <div class="card-block innerWrap">
                        <h2 class="card-title txt_post">(${goods.sellStatus})<a
                                href="/market/marketGoods?id=${goods.goodsId}">${goods.title}</a>
                        </h2>
                        <h4 class="card-text">${goods.juso}</h4>
                        <div class="wrapfooter">
                            <span class="meta-footer-thumb">
                                <a href="/user/userLink?id=${goods.nickId}"><img class="author-thumb"
                                                                                 src="${goods.nickPicture}" alt=""></a>
                            </span>
                            <span class="author-meta">
                                <span class="post-name"><a
                                        href="/user/userLink?id=${goods.nickId}">${goods.nick}</a></span><br/>
                                <span class="post-date mt-2">${goods.createTime}</span>
                            </span>
                            <span class="mainHeart">
                            <sec:authorize access="isAnonymous()">
                                <button type="button" class="wish_btn noneWish" onclick="alert('로그인이 필요합니다')">
                                    <i class="fa-regular fa-heart"></i></button>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${goods.wishList eq 'false'}">
                                <button type="button" class="wish_btn noneWish" id="wishPlus${goods.goodsId}"
                                        onclick="mainHeart(${goods.wishList},${goods.goodsId})">
                                    <i class="fa-regular fa-heart"></i></button>
                                </c:if>
                                <c:if test="${goods.wishList eq 'true'}">
                                <button type="button" class="wish_btn readyWish" id="wishPlus${goods.goodsId}"
                                        onclick="mainHeart(${goods.wishList},${goods.goodsId})">
                                    <i class="fa-regular fa-heart"></i></button>
                                </c:if>
                            </sec:authorize>
                            </span>
                        </div>
                    </div>
                </div>
                <!-- end post -->
            </c:forEach>
        </div>
    </section>
    <!-- End List Posts
    ================================================== -->

    <!-- Main Slider Start -->
    <div class="header scrollNav">
        <div class="container-fluid ">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="header-slider normal-slider">
                        <div class="header-slider-item">
                            <img src="${adList[0].savedFileName}" class="imgWide" alt="Slider Image"/>
                            <div class="header-slider-caption">
                                <p>${adList[0].title}</p>
                                <a class="btn" href="${adList[0].url}"><i class="fa fa-shopping-cart"></i>Shop Now</a>
                            </div>
                        </div>
                        <div class="header-slider-item">
                            <img src="${adList[1].savedFileName}" class="imgWide" alt="Slider Image"/>
                            <div class="header-slider-caption">
                                <p>${adList[1].title}</p>
                                <a class="btn" href="${adList[1].url}"><i class="fa fa-shopping-cart"></i>Shop Now</a>
                            </div>
                        </div>
                        <div class="header-slider-item">
                            <img src="${adList[2].savedFileName}" class="imgWide" alt="Slider Image"/>
                            <div class="header-slider-caption">
                                <p>${adList[2].title}</p>
                                <a class="btn" href="${adList[2].url}"><i class="fa fa-shopping-cart"></i>Shop Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Main Slider End -->

    <!-- Begin Featured
    ================================================== -->
    <section class="featured-posts">
        <div class="section-title row justify-content-between mx-1">
            <h2><span>Stories</span></h2><a href="/board">더보기</a>
        </div>
        <div class="boardColumns listfeaturedtag">
            <c:forEach var="boards" items="${boardList}">
                <!-- begin post -->
                <div class="card">
                    <div class="row">
                        <div class="col-md-5 wrapthumbnail">
                            <a href="/board/content?id=${boards.id}">
                                <div class="thumbnail" style="background-image:url(${boards.boardImages})"></div>
                            </a>
                        </div>
                        <div class="col-md-7">
                            <div class="card-block">
                                <h2 class="card-title txt_post"><a href="/board/content?id=${boards.id}">${boards.title}</a></h2>
                                <h4 class="card-text btxt_post">${boards.contents}</h4>
                                <div class="wrapfooter">
								<span class="meta-footer-thumb">
                                    <a href="/user/userLink?id=${boards.memberId}">
                                        <img class="author-thumb" src="${boards.memberImages}" alt="Sal">
                                    </a>
								</span>
                                    <span class="author-meta">
								<span class="post-name"><a
                                        href="/user/userLink?id=${boards.memberId}">${boards.nickname}</a></span><br/>
								<span class="post-date">${boards.createTime}</span>
								</span>
                                    <span class="mainHeart">
                            <sec:authorize access="isAnonymous()">
                                <button type="button" class="wish_btn noneWish" onclick="alert('로그인이 필요합니다')">
                                    <i class="fa-regular fa-heart"></i></button>
                            </sec:authorize>
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${boards.boardWishList eq 'false'}">
                                <button type="button" class="wish_btn noneWish" id="boardWishPlus${boards.id}"
                                        onclick="boardMainHeart(${boards.boardWishList},${boards.id})">
                                    <i class="fa-regular fa-heart"></i></button>
                                </c:if>
                                <c:if test="${boards.boardWishList eq 'true'}">
                                <button type="button" class="wish_btn readyWish" id="boardWishPlus${boards.id}"
                                        onclick="boardMainHeart(${boards.boardWishList},${boards.id})">
                                    <i class="fa-regular fa-heart"></i></button>
                                </c:if>
                            </sec:authorize>
                            </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end post -->
            </c:forEach>
        </div>
    </section>
    <!-- End Featured
    ================================================== -->
</div>
<%@ include file="fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="js/easing.min.js"></script>
<script src="js/slick/slick.min.js"></script>
<script src="js/home.js"></script>
</body>
</html>