<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>카카오탈퇴하기</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <!-- Template Stylesheet -->
    <link href="/resources/css/user/userPageUserInfo.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../../fragments/header.jsp" %>
<!-- Service Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/user">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>

        <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">회원 탈퇴</h3>
            </div>

            <div class="row  d-flex justify-content-center text-center form-row">

                <div class="col-12 col-sm-7 form-button">
                    <button class="btn btn-primary w-50 py-3" onclick="location.href = KAKAO_AUTH_URL">회원탈퇴</button>
                </div>

            </div>

        </div>
    </div>

    <%@ include file="../../fragments/footer.jsp" %>
    <script src="/resources/js/user/userPageKakaoResign.js"></script>
</body>
</html>
