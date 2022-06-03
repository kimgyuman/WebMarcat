<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<form name="jusoForm" id="jusoForm" method="post">
    <input type="text" id="juso" name="juso" placeholder="ex) 서울특별시, 강서구" />
    <button id="jusoSubmit" type="button" form="jusoForm" >검색</button>
</form>
<table id="result">

</table>
<script src="/js/member/popup.js"></script>
</body>
</html>
