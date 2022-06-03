<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>프로필사진수정</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <link rel="stylesheet" href="/resources/css/user/userPageProfileUpdate.css">
    <!-- Template Stylesheet -->


    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>



</head>
<body>
<%@ include file="../../fragments/header.jsp" %>


<!-- Service Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/myPage">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>

        <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">프로필 수정</h3>
            </div>

            <form method="post" action="/user/userProfileUpdate"
                  enctype="multipart/form-data" onsubmit="return validation();">
                <div class="row  d-flex justify-content-center text-center form-row">
                    <div class="col-12 col-sm-7 form-group">
                        <h2 class="file_title">프로필 사진 업로드</h2>
                        <hr>
                    </div>
                    <div class="col-12 col-sm-7 form-group">
                        <div class="profile mt-5 mb-5">
                                <img id="preview-image" src="${memberImages.savedFileName}">
                            <div class="filebox bs3-primary">
                                <input class="upload-name" id="inputFileCheck" value="파일명">
                                <label id="input-btn" for="input-image">업로드</label>
                                <input type="file" name="file" value="file" id="input-image" class="upload-hidden">
                            </div>
                            <div id="files" class="files">
                            </div>

                        </div>

                        <div class="btn_writeCancel">
                            <button type="submit" class="btn btn-default" id="btnSave">등록</button>
                            <button type="button"  onclick="location.href='/user/userInfo'" class="btn btn-default" id="btnCancel">취소</button>
                        </div>

                       </div>
                    </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="../../fragments/footer.jsp" %>

<script src="/resources/js/user/userProfileUpdate.js"></script>
</body>
</html>