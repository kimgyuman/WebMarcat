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
                    <h1 class="h1">비밀번호 변경</h1>
                </div>

                <div class="card">
                    <div class="card-body">
                        <div class="m-sm-5">
                            <form:form modelAttribute="signUpDTO" method="post" action="/findPw"
                                       onsubmit="return validation();">
                                <div class="form-group">
                                    <label>아이디</label>
                                    <form:input path="userId" class="form-control form-control-lg"
                                                type="text" id="userId" name="userId" placeholder="아이디 입력"/>
                                    <form:errors path="userId" cssClass="error_msg"/>
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
                                        <form:errors path="userPhone" cssClass="error_msg"/>
                                    </div>
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
                                    <span id="findPw_msg" class="error_msg"></span>
                                </div>
                                <div class="form-group pwdWrap">
                                    <label>새로운 비밀번호</label>
                                    <form:input path="userPw" class="form-control form-control-lg" type="password"
                                                id="userPw" name="userPw" placeholder="비밀번호 입력"/>
                                    <form:errors path="userPw" cssClass="error_msg"/>
                                    <span id="psw_msg" class="error_msg"></span>
                                </div>
                                <div class="form-group pwdCheckWrap">
                                    <label>비밀번호 확인</label>
                                    <input class="form-control form-control-lg" type="password" id="userPwCheck">
                                    <span id="pswCheck_msg" class="error_msg"></span>
                                </div>
                                <div class="text-center mt-5">
                                    <div class="form-check-inline">
                                        <button type="submit" class="btn btn-lg btn-primary" id="login_btn">비밀번호 변경
                                        </button>
                                        <button type="button" class="btn btn-lg btn-primary btn-danger ml-4"
                                                onclick="location.href='/'"
                                                id="cancel_btn">취소
                                        </button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../../fragments/footer.jsp" %>
<script src="/js/member/findPw.js"></script>
<script src="https://kit.fontawesome.com/5b0cfc680a.js" crossorigin="anonymous"></script>
</script>
</body>
</html>
