<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="/resources/css/market/goods_input_style.css" rel="stylesheet" type="text/css">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Fonts -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Righteous" rel="stylesheet">
    <title>Goods</title>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
<form class="page-section" id="contact" onsubmit="return registerAction();">
    <div class="container">
        <!-- Contact Section Heading-->
        <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">NEW GOODS</h2>

        <!-- insert file upload -->
        <div id="all_file_container">
            <hr>
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <h2>FILES</h2>
                <div class="divider-custom-line"></div>
            </div>
            <div class="inner_file_container">
                <div class="upload-box">
                    <div id="drop-file" class="drag-file">
                        <p class="message">여기에 파일을 드레그 해 주세요 <br/> 파일은 4개까지 올릴 수 있습니다!</p>
                    </div>
                    <button class="inserted_btn" id="btn-upload">파일 선택</button>
                    <input class="file" id="chooseFile" type="file" name="file" multiple="multiple" accept="image/*">
                </div>
                <div id="files" class="files">

                </div>
            </div>
        </div>
        <!-- end file upload -->

        <div class="cates_area" id="cates_area">
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <h2>CATEGORIES</h2>
                <div class="divider-custom-line"></div>
            </div>
            <input class="cate_btn" type="button" id="100" value="중고차" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="200" value="디지털기기" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="300" value="생활가전" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="400" value="가구,인테리어" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="500" value="유아동" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="600" value="생활,가공식품" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="700" value="유아도서" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="800" value="스포츠,레저" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="900" value="여성잡화" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1000" value="여성의류" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1100" value="남성패션,잡화" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1200" value="게임,취미" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1300" value="뷰티,미용" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1400" value="반려동물용품" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1500" value="도서/티켓/음반" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1600" value="식물" onclick="inputCateVal(this.value,this.id)">
            <input class="cate_btn" type="button" id="1700" value="기타 중고물품" onclick="inputCateVal(this.value,this.id)">
            <input type="hidden" id="hidden_data" path="cateId"/>
        </div>

        <!-- Contact Section Form-->
        <div class="row justify-content-center">
            <div class="col-lg-8 col-xl-7">
                <div id="contactForm" data-sb-form-api-token="API_TOKEN">
                    <div class="divider-custom">
                        <div class="divider-custom-line"></div>
                        <h2>CONTENTS</h2>
                        <div class="divider-custom-line"></div>
                    </div>
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" name="title" id="title" type="text"
                                    data-sb-validations="required" path="title">
                        <label for="title">제목</label>
                        <div class="invalid-feedback" data-sb-feedback="title:required">제목은 필수입니다.</div>
                    </div>
                    <!-- Phone number input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" name="price" id="price" type="text"
                                    data-sb-validations="required" path="price">
                        <label for="price">가격</label>
                        <div class="invalid-feedback" data-sb-feedback="price:required">가격은 필수입니다.</div>
                    </div>
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" name="detail" id="detail" type="text" style="height: 10rem"
                                    data-sb-validations="required" path="contents">
                        <label for="detail">상세설명</label>
                        <div class="invalid-feedback" data-sb-feedback="detail:required">설명을 기입해주세요!</div>
                    </div>
                    <!-- Submit success message-->
                    <!---->
                    <!-- This is what your users will see when the form-->
                    <input class="cate_btn" type="button" id="nego" value="NEGO:OFF"/>
                    <input type="hidden" id="nego_state" value="NO" path="negoStatus"/>
                    <button id="submit_button" type="submit">상품 등록하기</button>
                </div>
            </div>
        </div>
    </div>
</form>




<%@ include file="../fragments/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/resources/js/market/input_boot.js"></script>
</body>
</html>
