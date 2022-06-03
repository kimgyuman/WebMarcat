<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>비밀번호변경</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">

    <link rel="stylesheet" href="/resources/css/user/userPageUserPwChange.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>


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
                <h3 class="mb-0">비밀번호 변경</h3>
            </div>
            <form:form modelAttribute="userPwUpdateDTO" method="post" action="/user/userPwChange"
                       class="pwUpdateForm" onsubmit="return validation();">
                <div class="formDiv">
                    <div class="form-group">
                        <h4>현재 비밀번호</h4>
                        <form:input path="userPw" class="form-control bg-white border-0" type="password"
                                    id="userPw" name="userPw" style="height: 55px;" placeholder="현재 사용중인 비밀번호를 입력하세요."/>
                        <form:errors path="userPw" cssClass="error_msg"/>
                        <span id="current_psw_msg" class="error_msg"></span>
                        <form:input type="hidden" id="pkUser" name="userId" path="userId"/>
                    </div>

                    <div class="form-group">
                        <h4>변경할 비밀번호</h4>
                        <form:input path="userPwUpdate" class="form-control bg-white border-0" type="password"
                                    id="userPwUpdate" name="userPwUpdate" style="height: 55px;"
                                    placeholder="변경할 비밀번호를 입력하세요."/>
                        <form:errors path="userPwUpdate" cssClass="error_msg"/>
                        <span id="psw_msg" class="error_msg"></span>
                    </div>

                    <div class="form-group">
                        <h4>변경 비밀번호 확인</h4>
                        <input type="password" class="form-control bg-white border-0"
                               placeholder="변경할 비밀번호를 한번 더 입력하세요." style="height: 55px;" id="userPwCheck">
                        <span id="pswCheck_msg" class="error_msg"></span>
                    </div>


                    <div class="btn_writeCancel">
                        <button type="submit" class="btn btn-default" id="btnSave">수정</button>
                        <button type="button" class="btn btn-default" onclick="location.href='/user'"  id="btnCancel">취소</button>
                    </div>
<%--                    <div class="col-12 col-sm-7 form-button">--%>
<%--                        <button class="modifyBtn btn-primary w-50 py-3" type="submit" id="modify_btn" >수정하기</button>--%>
<%--                    </div>--%>
<%--                    <div class="col-12 col-sm-7 form-button">--%>
<%--                        <button class="cancleBtn btn-secondary w-50 py-3" type="button" id="cancel_btn">취소</button>--%>
<%--                    </div>--%>
                </div>
            </form:form>

        </div>
    </div>
</div>
    <%@ include file="../../fragments/footer.jsp" %>
    <script src="/resources/js/user/userPwChange.js"></script>
    <%--  <script src="/resources/js/member/memberForm.js"></script>--%>
</body>
</html>


