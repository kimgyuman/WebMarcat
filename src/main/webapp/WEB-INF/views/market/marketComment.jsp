<%--
  Created by IntelliJ IDEA.
  User: junu
  Date: 2022/05/11
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link href="../../resources/css/market/marketComment.css" rel="stylesheet" type="text/css">
    <title>Title</title>
</head>
<body>
<!-- Begin Author Posts
================================================== -->
<div class="marketComment_wrap">
    <!-- begin post -->
    <div class="marketCommemt_container">
        <form class="marketCommnet_form" id="marketCommnet_form">
            <sec:authorize access="isAnonymous()">
                <input class="marketComment_textarea" placeholder="로그인하시기 바랍니다." id="content" type="text">
                <input type="button" class="marketComment_btn ml-3" onclick="location.href='/login'" value="로그인">
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <input class="marketComment_textarea" placeholder="COMMNETS" id="content" type="text">
                <input type="button" class="marketComment_btn ml-3" id="marketComment_btn" value="등록">
            </sec:authorize>
        </form>
    </div>
    <div class="divider-custom">
        <div class="divider-custom-line"></div>
        <h2>Comments</h2>
        <div class="divider-custom-line"></div>
    </div>
    <div id="marketComment_comments" class="marketComment_comments">

    </div>

</div>
</body>
</html>
