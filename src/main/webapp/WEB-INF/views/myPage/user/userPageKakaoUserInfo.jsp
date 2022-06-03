<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>카카오유저 정보수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >

    <!-- Icon Font Stylesheet -->
<%--    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">--%>

<%--    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">--%>

    <!-- Template Stylesheet -->
    <link href="/resources/css/user/userPageUserInfo.css" rel="stylesheet">


    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../../fragments/header.jsp" %>

<!-- Service Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="section-title text-center position-relative pb-3 mb-5 mx-auto" style="max-width: 600px;">
            <h5 class="fw-bold text-primary text-uppercase"><a href="/myPage">Go Home</a></h5>
            <h3 class="mb-0">My Room</h3>
        </div>

        <div class="bg-light rounded p-5">
            <div class="section-title section-title-sm position-relative pb-3 mb-4">
                <h3 class="mb-0">프로필 수정</h3>
            </div>

            <form:form modelAttribute="signUpDTO" method="post" action="/user/userInfo"
                       class="profileForm" onsubmit="return validation();" >
            <div class="formDiv">

                <div class="form-group">
<%--                    <h4>아이디</h4>--%>
    <input class="form-control bg-white border-0" type="hidden"
                path="userId" id="pkUser" name="userId" value="${signUpDTO.userId}" readonly="true" />

<%--    <input type="hidden" id="pkUser" name="id" path="id" value="#{member.id}"/>--%>
                </div>

                <div class="form-group">
                    <h4>닉네임</h4>
                    <input path="nickName" class="form-control bg-white border-0"  type="hidden" id="preNickName"
                           name="nickName" disabled="disabled" value="${signUpDTO.nickName}"/>
                    <form:input path="nickName" class="form-control bg-white border-0"  type="text" id="nickName"
                                name="nickName" placeholder="특수문자를 제외한 2~8자리" value="${signUpDTO.nickName}"/>
                    <form:errors path="nickName" cssClass="error_msg"/>
                    <span id="nick_msg" class="error_msg"></span>
                </div>

                <div class="form-group">
                    <h4>주소</h4>
                    <input class="form-control bg-white border-0" type="hidden"
                           id="preinputJuso" name="inputJuso" readonly="true" disabled="disabled" value="${jusoName}"/>
                    <input class="form-control bg-white border-0" type="text"
                           id="inputJuso" name="inputJuso" readonly="true" value="${jusoName}"/>
                    <div class="form-line">
                        <button type="button" class="juso btn-default mt-1 mb-3"
                                onclick="address()">주소찾기</button>

                        <form:input type="hidden" id="pkJuso" name="juso" value="${signUpDTO.juso}"
                                    path="juso"/>
                        <form:errors path="juso" cssClass="error_msg"/>
                    </div>

                </div>

                <div class="form-group">
                    <h4>전화번호</h4>
                    <form:input path="userPhone" class="form-control bg-white border-0" type="text" id="userPhone"
                                placeholder="PhoneNum"  readonly="true" value="${member.phoneNum}"/>
<%--                      <div class="form-line">--%>
<%--                          <button type="button" class="menu btn-default mt-1 mb-3" id="checkPhone"--%>
<%--                                    name="checkPhone">문자전송--%>
<%--                         </button>--%>
<%--                      </div>--%>
                </div>

<%--                <div class="form-group">--%>
<%--                    <label>인증번호</label>--%>
<%--                    <span id="timer" class="timer ml-sm-3"/></label>--%>
<%--                    <input class="form-control" type="text"--%>
<%--                           id="checkNum" placeholder="인증번호 입력">--%>
<%--                    <div class="form-line">--%>
<%--                        <button type="button" class="menu btn-default mt-1 mb-3" id="sendCkPhone"--%>
<%--                                name="sendCkPhone">인증하기--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </div>--%>

                <div class="btn_writeCancel">
                    <button type="submit" class="btn btn-default" id="btnSave">수정</button>
                    <button type="button"  onclick="location.href='/user'" class="btn btn-default" id="btnCancel">취소</button>
                </div>

                <div class="menuList">
                    <button type="button" class="menu btn-default"
                            onclick="location.href='/user/userProfileUpdate'">프로필사진 수정</button>
                    <button type="button" class="menu btn-default"
                            onclick="resignBtn()">회원 탈퇴</button>
                </div>

<%--            <form:form modelAttribute="signUpDTO" method="post" action="/user/userInfo" class="profileForm" onsubmit="return validation();" >--%>
<%--            <div class="row  d-flex justify-content-center text-center form-row">--%>

<%--                <div class="form-group">--%>
<%--                    <label>닉네임</label>--%>
<%--                    <form:input path="nickName" class="form-control bg-white border-0"  type="text" id="nickName"--%>
<%--                                placeholder="Nick Name" style="height: 55px;" value="${member.nickName}"/>--%>
<%--                    <form:errors path="nickName" cssClass="error_msg"/>--%>
<%--                    <span id="nick_msg" class="error_msg"></span>--%>
<%--                    <input path="userName" class="form-control bg-white border-0"  type="hidden" id="userName"--%>
<%--                           placeholder="Name" style="height: 55px;" readonly="true" value="${member.name}"/>--%>
<%--                </div>--%>

<%--                <div class="col-12 col-sm-7 form-group">--%>
<%--                    <label>주소</label>--%>
<%--                    <input class="form-control bg-white border-0" type="text"--%>
<%--                           id="inputJuso" name="inputJuso" readonly="true" placeholder="주소찾기" value="${member.koreaArea.admNm}"/>--%>
<%--                    <div class="form-line">--%>
<%--                        <button type="button" class="btn btn-primary w-30 py-3" onclick="address()"--%>
<%--                                id="findJuso">주소찾기--%>
<%--                        </button>--%>
<%--                        <form:input type="hidden" id="pkJuso" name="juso" value="#{signUpDTO.juso}"--%>
<%--                                    path="juso"/>--%>
<%--                        <form:errors path="juso" cssClass="error_msg"/>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col-12 col-sm-7 form-group">--%>
<%--                    <label>전화번호</label>--%>
<%--                    <form:input path="userPhone" class="form-control bg-white border-0" type="text" id="userPhone"--%>
<%--                                placeholder="Phone Num" style="height: 55px;"  readonly="true" value="${member.phoneNum}"/>--%>
<%--                        &lt;%&ndash;                        <div class="form-line">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        <button type="button" class="btn btn-primary w-30 py-3" id="checkPhone"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                                name="checkPhone">문자전송&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        </button>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        </div>&ndash;%&gt;--%>
<%--                </div>--%>

<%--                    &lt;%&ndash;                        <div class="col-12 col-sm-7 py-3 form-group">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            <label>인증번호</label>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            <span id="timer" class="timer ml-sm-3"/></label>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                <input class="form-control bg-white border-0 " type="text"&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                       id="checkNum" placeholder="인증번호 입력">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            <div class="form-line">&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                <button type="button" class="btn btn-primary w-30 py-3" id="sendCkPhone"&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                        name="sendCkPhone">인증하기&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                                </button>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                        </div>&ndash;%&gt;--%>


<%--                <div class="col-12 col-sm-7 form-button">--%>
<%--                    <button class="btn btn-primary w-50 py-3" type="submit" id="modify_btn">수정하기</button>--%>
<%--                </div>--%>
<%--                <div class="col-12 col-sm-7 form-button">--%>
<%--                    <button class="btn btn-secondary w-50 py-3" type="button" onclick="location.href='/user'">취소</button>--%>
<%--                </div>--%>
<%--                <div class="col-12 col-sm-7 form-button">--%>
<%--                    <button class="btn btn-primary w-50 py-3" type="button" onclick="location.href='/user/userProfileUpdate'">프로필사진 수정</button>--%>
<%--                </div>--%>
<%--                <div class="col-12 col-sm-7 form-button">--%>
<%--                    <button class="btn btn-primary w-50 py-3" type="button" onclick="location.href='/user/kakaoUserResign'">회원탈퇴</button>--%>
<%--                </div>--%>

            </div>
            </form:form>
        </div>
    </div>
</div>
    <%@ include file="../../fragments/footer.jsp" %>

<script src="/resources/js/user/userInfo.js"></script>
<script src="/resources/js/user/userPageKakaoResign.js"></script>

</body>
</html>


