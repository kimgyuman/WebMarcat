<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2022/03/17
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="css/market/market_boot_main.css" rel="stylesheet" type="text/css">
    <!-- Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
    <title>market</title>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<div class="container">
    <%--    <div class="mainheading" id="top">--%>
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
                <input type="hidden" id="searchCate">
                <i class="fas fa-search" id="searchButton" onclick="search()"></i>
            </div>
        </div>
        <button id="mainCate" class="mainCateBtn" onclick="OpenClose()"><i class="ri-file-copy-2-line"></i></button>
    </div>
    <div class="area_select" id="area_select">
        <p class="area_title"> 지역 :</p>
        <input class="text_juso" type="text" id="textJuso" readonly="true" placeholder="지도 클릭하여 검색"/>
        <input type="hidden" id="jusoCode" class="jusoCode"/>
        <i class="fa-solid fa-xmark" onclick="jusoReset()"></i>
        <button id="mainArea" value="Area" class="area_btn" onclick="openJuso()"><i class="fa-solid fa-map-location"></i></button>
    </div>
    <div class="main_cate_area" id="main_cate_area">
        <input class="main_cates" type="button" id="100" value="중고차" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="200" value="디지털기기" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="300" value="생활가전" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="400" value="가구,인테리어" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="500" value="유아동" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="600" value="생활,가공식품" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="700" value="유아도서" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="800" value="스포츠,레저" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="900" value="여성잡화" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1000" value="여성의류" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1100" value="남성패션,잡화" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1200" value="게임,취미" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1300" value="뷰티,미용" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1400" value="반려동물용품" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1600" value="식물" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1500" value="도서/티켓/음반" onclick="CateSearch(this.value,this.id)">
        <input class="main_cates" type="button" id="1700" value="기타 중고물품" onclick="CateSearch(this.value,this.id)">
    </div>
    <!-- End Site Title
    ================================================== -->

    <!-- Begin List Posts
    ================================================== -->
    <section class="recent-posts mt-4">
        <div class="section-title">
            <h2><span>Items</span><a class="new_input" href="/user/market/createGoods"><i class="fa-solid fa-plus"></i></a>
            </h2>
        </div>
        <div id="contentsWrap" class="columns listrecent">

        </div>
    </section>
    <!-- End Featured
    ================================================== -->
</div>
<a class="btn_top" href="#top">top</a>
<%@ include file="../fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="/resources/js/market/marketMain.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>
</html>
