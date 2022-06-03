<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="css/member/addMember.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../../fragments/header.jsp" %>
<div class="container register-container">
    <div class="row">
        <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
            <div class="d-table-cell align-middle">

                <div class="text-center mt-4">
                    <h1 class="h1">SIGN UP</h1>
                </div>

                <div class="card">
                    <div class="card-body">
                        <div class="m-sm-5">
                            <form:form modelAttribute="signUpDTO" method="post" action="/add"
                                       onsubmit="return validation();">
                                <div class="form-group">
                                    <label>아이디</label>
                                    <div class="form-check-inline">
                                        <form:input path="userId" class="form-control form-control-lg form-check-input"
                                                    type="text" id="userId" name="userId" readonly="true"
                                                    placeholder="버튼 클릭"/>
                                        <button type="button" class="btn btn-sm btn-primary ml-sm-5"
                                                onclick="checkIdMain()" id="checkId" name="checkId">중복확인
                                        </button>
                                    </div>
                                    <form:errors path="userId" cssClass="error_msg"/>
                                </div>
                                <div class="form-group">
                                    <label>비밀번호</label>
                                    <form:input path="userPw" class="form-control form-control-lg" type="password"
                                                id="userPw" name="userPw" placeholder="비밀번호 입력"/>
                                    <form:errors path="userPw" cssClass="error_msg"/>
                                    <span id="psw_msg" class="error_msg"></span>
                                </div>
                                <div class="form-group">
                                    <label>비밀번호 확인</label>
                                    <input class="form-control form-control-lg" type="password" id="userPwCheck">
                                    <span id="pswCheck_msg" class="error_msg"></span>
                                </div>
                                <div class="form-group">
                                    <label>이름</label>
                                    <form:input path="userName" class="form-control form-control-lg" type="text"
                                                id="userName" name="userName"/>
                                    <form:errors path="userName" cssClass="error_msg"/>
                                </div>
                                <div class="form-group">
                                    <label>닉네임</label>
                                    <form:input path="nickName" class="form-control form-control-lg" type="text"
                                                id="nickName" name="nickName" placeholder="특수문자 제외 2~8자리"/>
                                    <form:errors path="nickName" cssClass="error_msg"/>
                                    <span id="nick_msg" class="error_msg"></span>
                                </div>
                                <div class="form-group">
                                    <label>전화번호</label>
                                    <div class="form-check-inline">
                                        <form:input path="userPhone"
                                                    class="form-control form-control-lg form-check-input" type="text"
                                                    id="userPhone" name="userPhone" placeholder="- 없이 입력"/>
                                        <button type="button" class="btn btn-sm btn-primary ml-sm-5" id="checkPhone"
                                                name="checkPhone">문자전송
                                        </button>
                                    </div>
                                    <form:errors path="userPhone" cssClass="error_msg"/>
                                </div>
                                <div class="form-group">
                                    <label>인증번호<span id="timer" class="timer ml-sm-3"/></label>
                                    <div class="form-check-inline">
                                        <input class="form-control form-control-lg form-check-input" type="text"
                                               id="checkNum" placeholder="인증번호 입력">
                                        <button type="button" class="btn btn-sm btn-primary ml-sm-5" id="sendCkPhone"
                                                name="sendCkPhone">인증하기
                                        </button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>주소</label>
                                    <div class="form-check-inline">
                                        <input class="form-control form-control-lg form-check-input"
                                               type="text"
                                               id="inputJuso" readonly="true" placeholder="버튼 클릭"/>
                                        <button type="button" class="btn btn-sm btn-primary ml-sm-5" onclick="address()"
                                                id="findJuso">주소찾기
                                        </button>
                                        <form:input type="hidden" id="pkJuso" name="juso" value="#{signUpDTO.juso}"
                                                    path="juso"/>
                                    </div>
                                    <form:errors path="juso" cssClass="error_msg"/>
                                </div>
                                <div class="text-center mt-5">
                                    <div class="form-check-inline">
                                        <button type="submit" class="btn btn-lg btn-primary" id="login_btn">회원가입
                                        </button>
                                        <button type="button" class="btn btn-lg btn-primary btn-danger ml-4"
                                                onclick="location.href='/'"
                                                id="cancel_btn">취소
                                        </button>
                                    </div>
                                </div>
                                <span id="error_msg" class="error_msg"></span>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../fragments/footer.jsp" %>
<script src="/js/member/memberForm.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>
</script>
</body>
</html>
