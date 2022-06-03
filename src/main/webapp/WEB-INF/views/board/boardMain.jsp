<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2022/03/17
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/board/board.css" type="text/css"/>
<%--    <link href="../../resources/css/blog.css" rel="stylesheet" type="text/css">--%>
<%--    <link href="../../resources/css/home.css" rel="stylesheet" type="text/css">--%>
    <title>Document</title>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>

<div class="container">
    <div class="wrapper mt-3">
        <div class="search_box">
            <div class="dropdown">
                <div class="default_option">All</div>
                <ul>
                    <li>All</li>
                    <li>제목</li>
                    <li>작성자</li>
                </ul>
            </div>
            <div class="search_field">
                <input type="text" id="searchText" class="input" placeholder="Search">
                <i class="fas fa-search" id="searchButton" onclick="search()"></i>
            </div>
        </div>
    </div>

    <section class="featured-posts">
        <div class="section-title">
            <div>
                <h2>
                    <span>Story</span>
                </h2>
            </div>
            <div>
                <a class="new_input" href="/user/board/write">
                    <i class="fa-solid fa-plus"></i>
                </a>
            </div>
        </div>

        <div class="columns listfeaturedtag" id="contentsWrap">
        </div>
    </section>
    <!-- End Featured
    ================================================== -->
</div>
<a class="btn_top" href="#top">top</a>
<%@ include file="../fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="/resources/js/board/boardMain.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>
</html>
