<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Marcat</title>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/js/header.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="/css/header.css">
</head>
<body>
<header>
    <div>
        <div class="header-blue">
            <nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
                <div class="container">
                    <a class="navbar-brand" href="/">Marcat</a>
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                            class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav mr-auto">
                            <li class="nav-item" role="presentation"><a class="nav-link active" href="/">HOME</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link active"
                                                                        href="/market">MARKET</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link active" href="/board">BOARD</a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link active"
                                                                        href="/myPage">MYPAGE</a></li>
                        </ul>
                        <sec:authorize access="isAnonymous()">
                            <span class="navbar-text"> <a href="/login" class="login">로그인</a></span>
                            <a class="btn btn-light action-button" role="button" href="/add">회원가입</a>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <span class="navbar-text"> <a href="/logout" class="login">로그아웃</a></span>
                        </sec:authorize>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</header>
</body>
</html>
