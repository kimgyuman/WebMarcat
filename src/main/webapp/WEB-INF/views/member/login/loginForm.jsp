<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/member/loginForm.css" type="text/css">
</head>
<body>
<%@ include file="../../fragments/header.jsp" %>
<div class="login-container animated fadeInDown bootstrap snippets bootdeys">
    <div class="loginbox bg-white">
        <div class="loginbox-title">SIGN IN</div>
        <form:form modelAttribute="loginForm" method="post" action="/login" onsubmit="return validation();">
        <div class="loginbox-textbox">
            <form:input path="loginId" type="text" class="form-control" id="input_text" placeholder="아이디"/>
            <form:errors path="loginId" cssClass="error_msg"/>
            <span id="loginResult" class="error_msg"></span>
        </div>
        <div class="loginbox-textbox">
            <form:input path="loginPw" type="password" id="input_password"  name="loginPw" class="form-control" placeholder="비밀번호"/>
            <form:errors path="loginPw" cssClass="error_msg"/>
        </div>
        <div class="loginbox-forgot">
            <a href="/findId">아이디 찾기</a><span>&nbsp;&nbsp;|&nbsp;&nbsp;</span><a href="/findPw">비밀번호 찾기</a>
        </div>
        <div class="loginbox-submit">
            <input type="submit" id="login_btn" class="btn btn-primary btn-block" value="Login">
        </div>
        </form:form>
        <div class="loginbox-submit" onclick="location.href = KAKAO_AUTH_URL">
            <input type="button" class="btn btn-link btn-block kakao">
        </div>
        <div class="loginbox-signup">
            <a href="/add" id="signUp_btn">회원가입</a>
        </div>
    </div>
</div>
<%@include file="../../fragments/footer.jsp" %>
<script src="/js/member/loginForm.js"></script>
</body>
</html>
