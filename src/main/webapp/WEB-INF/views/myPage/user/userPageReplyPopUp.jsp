<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>답장 보내기</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <link href="/resources/css/user/userPageReplyPopUp.css" rel="stylesheet">
</head>
<body>
<form name="receiveMessageForm" id="receiveMessageForm" method="post" onsubmit="return validation();">
    <div class="container">
        <!-- seq Input -->
        <input  class="form-control bg-white border-0"  type="hidden" id="msId" name="msId"
                readonly="true" value="${msId}"/>


        <div class="contents">
            <h2>쪽지 보내기</h2><hr>

            <!-- Name Input -->
        <div class="message">
            <h4>보내는 사람</h4>
            <input class="form-control"  type="text" id="sendId" name="sendId"
                   readonly="true" value="${reSendId}"/>
        </div>

        <!-- Name Input -->
            <div class="message">
            <h4>받는 사람</h4>
            <input  class="form-control"  type="text" id="recvId" name="recvId"
                     readonly="true" value="${reRecvId}"/>
        </div>

        <!-- Message Input -->
        <div class="message">
            <h4>메세지</h4>
            <textarea class="noresize"
                      id="message" type="text" maxlength='93'
                      placeholder="메세지 입력"></textarea>
        </div>

        <!-- Submit button -->
        <div class="btn_area">
            <input type="button" class="click_btn" type="button" form="receiveMessageForm" id="receiveByMessage"
                   value="쪽지 전송">
        </div>

    </div>
</form>
<!-- End of contact form -->


<script src="/resources/js/user/userPageReplyPopUp.js"></script>
</body>
</html>
