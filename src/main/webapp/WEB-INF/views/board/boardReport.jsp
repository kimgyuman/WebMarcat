<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>report</title>
    <link href="/resources/css/market/reportModal.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script>
        let oneBoardId = ${oneBoardId};
    </script>
</head>
<body>
<div class="modal">
    <div class="divider-custom">
        <div class="divider-custom-line"></div>
        <h2>REPORT</h2>
        <div class="divider-custom-line"></div>
    </div>
    <form onsubmit="return report()">

        <input type="button" class="report_reason" id="papering" value="스팸홍보/도배글입니다" onclick="reportOne(this.value,this.id)"/>
        <input type="button" class="report_reason" id="porn" value="음란물입니다" onclick="reportOne(this.value,this.id)"/>
        <input type="button" class="report_reason" id="mannerIssue" value="욕설/혐오/차별적 표현이 포함되어 있습니다" onclick="reportOne(this.value,this.id)"/>
        <input type="button" class="report_reason" id="Privacy" value="개인정보 노출 게시물입니다" onclick="reportOne(this.value,this.id)"/>
        <input type="button" class="report_reason" id="Harmful" value="청소년에게 유해한 내용입니다" onclick="reportOne(this.value,this.id)"/>
        <input type="button" class="report_reason" id="copyright" value="저작권침해 게시물입니다" onclick="reportOne(this.value,this.id)"/>

        <div class="submit_div">
            <input type="submit" value="REPORT" class="report_submit">
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/resources/js/board/boardReport.js"></script>
</body>
</html>
