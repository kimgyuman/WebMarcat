<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>탈퇴하기</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <!-- Template Stylesheet -->
    <link href="/resources/css/user/userPageResign.css" rel="stylesheet">

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

            <form:form modelAttribute="userPwUpdateDTO" method="post"  class="pwUpdateForm"
                       action="/user/userResign" onsubmit="return resignValidation();">
            <div class="formDiv">
                 <div class="form-group">
                     <label>탈퇴하려면 비밀번호를 입력하세요.</label>
                     <form:input  path="userPw" type="password" class="form-control form-control-lg"
                                  id="userPw" name="userPw" placeholder="비밀번호 입력" />
                     <span id="psw_msg" class="error_msg"></span>
                     <form:errors path="userPw" cssClass="error_msg"/>
                 </div>

                <div class="btn_writeCancel">
                    <button type="submit" class="btn btn-default" id="btnSave">회원탈퇴</button>
                </div>
                 </form:form>
             </div>

        </div>
    </div>

<%@ include file="../../fragments/footer.jsp" %>
    <script src="/resources/js/user/userPageUserResign.js"></script>
</body>
</html>
