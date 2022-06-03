<%@ page import="marcat.myPage.dto.user.MessageDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>쪽지관리</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet" type="text/css">

    <%--    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">--%>
    <!-- Templete Stylesheet -->
    <link rel="stylesheet" href="/resources/css/user/userPageMessage.css" type="text/css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

</head>

<body>
<%@ include file="../../fragments/header.jsp" %>
<div class="container-fluid py-5">
    <div class="container py-5">

        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/user">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>

        <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">쪽지 관리</h3>
            </div>


        <div class="p-5 bg-white rounded shadow mb-5">
            <!-- Lined tabs-->
            <ul id="myTab2" role="tablist"
                class="nav nav-tabs nav-pills with-arrow lined flex-column flex-sm-row text-center">
                <li class="nav-item flex-sm-fill">
                    <a id="home2-tab" data-toggle="tab" href="#home2" role="tab" aria-controls="home2"
                       aria-selected="true"
                       class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0 active">보낸 쪽지함</a>
                </li>
                <li class="nav-item flex-sm-fill">
                    <a id="profile1-tab" data-toggle="tab" href="#profile1" role="tab" aria-controls="profile1"
                       aria-selected="false" class="nav-link text-uppercase font-weight-bold mr-sm-3 rounded-0">받은 쪽지함</a>
                </li>
            </ul>

            <div id="myTab2Content" class="tab-content">
                <div id="home2" role="tabpanel" aria-labelledby="home-tab"
                     class="tab-pane fade px-4 py-5 show active">
                    <div id="contentsWrap" class="columns listrecent innerWrap">
                        <c:if test="${empty receiveMessageList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">보낸 메세지가 없습니다.</button>
                            </div>
                        </c:if>
                        <c:forEach var="receiveMessages" items="${receiveMessageList}">
                            <div class="card col-md-12" id="messageCard">
                                <input id="smsId" type="hidden" name="smsId" value="${receiveMessages.msId}"/>
                                <div class="card-body" id="messageBody">
                                    <div class="row">
                                        <div class="d-flex flex-column align-items-center col-md-4 mt-3">
                                            <div class="box">
                                                <img class="profile" src="${receiveMessages.targetMemberImages}">
                                            </div>
                                            <a class="mt-3" href="/user/userLink?id=${receiveMessages.senderId}">
                                                <strong>${receiveMessages.targetNick}</strong></a>
                                        </div>
                                        <div class="col-md-8">
                                            <h2 class="card-title txt_post">보낸 메세지 내용</h2>
                                            <div class="card">
                                                <p class="ml-2 mt-2">${receiveMessages.msMessage}</p>
                                                <span class="ml-2 mt-2">${receiveMessages.msCreateTime}</span>
                                            </div>
                                            <div class="btnGroup mt-3">
                                                <button class="sendDel float-right btn btn-danger ml-2"
                                                        id="delete_btn" onclick="deleteA(${receiveMessages.msId})">삭제하기</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </c:forEach>
                    </div>
                </div>
                <div id="profile1" role="tabpanel" aria-labelledby="profile-tab" class="tab-pane fade px-4 py-5">
                    <div id="contentsWrap1" class="columns listrecent">
                        <c:if test="${empty sendMessageList}">
                            <div class="empty">
                                <button type="button" class="btn btn-primary w-30 py-3"
                                        onClick="location.href='/market'">받은 메세지가 없습니다.</button>
                            </div>
                        </c:if>
                        <c:forEach var="sendMessages" items="${sendMessageList}">
                        <div class="card col-md-12" id="mainCard">
                            <input id="msId" type="hidden" name="msId" value="${sendMessages.msId}"/>
                            <div class="card-body" id="mainBody">
                                <div class="row">
                                    <div class="d-flex flex-column align-items-center col-md-4 mt-3">
                                        <div class="box">
                                            <img class="profile" src="${sendMessages.targetMemberImages}">
                                        </div>
                                        <a class="mt-3" href="/user/userLink?id=${sendMessages.senderId}">
                                            <strong>${sendMessages.senderNick}</strong></a>
                                    </div>

                                    <div class="col-md-8">
                                        <h2 class="card-title txt_post">받은 메세지 내용</h2>
                                        <div class="card">
                                            <p class="ml-2 mt-2">${sendMessages.msMessage}</p>
                                            <span class="ml-2 mt-2">${sendMessages.msCreateTime}</span>
                                        </div>
                                        <div class="btnGroup mt-3">
                                            <button class="replyDel float-right btn btn-danger ml-2"
                                                    id="delete1_btn" onclick="deleteB(${sendMessages.msId})">삭제하기</button>
                                            <button class="replyBtn float-right btn btn-primary ml-2"
                                                    id="reply_btn" onclick="replyPopUp(${sendMessages.msId})">
                                                <i class="fa fa-reply" aria-hidden="true"></i> 답장하기</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>  <!-- end card -->

                    </c:forEach>
                </div>
            </div> <!-- end tab -->


              </div>
          </div>
        </div>
    </div>
</div>



<%@ include file="../../fragments/footer.jsp" %>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="/resources/js/user/userPageMessage.js"></script>

</body>
</html>
