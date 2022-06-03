<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<form name="findJusoForm" id="findJusoForm" method="post">
    <input type="text" id="juso" name="juso" placeholder="ex) 삼전동, 홍제동" />
    <button id="jusoSubmitBtn" type="button" form="findJusoForm">검색</button>
</form>
<table id="result">

</table>
<script src="/resources/js/market/marketJuso.js"></script>
</body>
</html>
