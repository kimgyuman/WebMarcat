<%--
  Created by IntelliJ IDEA.
  User: kimgyuman
  Date: 2022/04/18
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>동네생활</title>
<%--    <meta charset="UTF-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--%>
    <link href="/resources/css/board/WriteForm.css" rel="stylesheet"/>
    <script>
        let imgsName = [];
        let realName = [];
        let originFiles = [];
        <c:forEach var="item" items="${boardUpdateDTO.images}">
        imgsName.push("${item.savedFileName}");
        realName.push("${item.originFileName}");
        </c:forEach>
        let oneBoardId = '${boardUpdateDTO.id}';
        let oneBoardContents = '${boardUpdateDTO.contents}';
        let oneBoardTitle = '${boardUpdateDTO.title}';



    </script>
<body>
<%@ include file="../fragments/header.jsp" %>


<div class="container">
    <div class="top_title"><h2>동네 생활 글쓰기</h2></div>
    <form class="page-section" id="contact" onsubmit="return registerAction()">
    <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title"
                   placeholder="제목 입력(4-100)" name="title"
                   maxlength="100"/>
        </div>
        <div id="all_file_container">
            <hr>
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <h2>FILES</h2>
                <div class="divider-custom-line"></div>
            </div>
            <div class="inner_file_container">
                <div class="upload-box">
                    <div id="drop-file" class="drag-file">
                        <p class="message">여기에 파일을 드레그 해 주세요 <br/> 파일은 4개까지 올릴 수 있습니다!</p>
                    </div>
                    <button class="btn-sm btn-primary mt-3" id="btn-upload">파일 선택</button>
                    <input class="file" id="chooseFile" type="file" name="file" multiple="multiple" accept="image/*">
                </div>
                <div id="files" class="files">

                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="contents">내용</label>

            <textarea class="form-control" rows="5" id="contents"
                      name="contents" placeholder="내용 작성"></textarea>
        </div>

        <div class="btn_writeCancel">
            <button type="submit" class="" id="btnSave">수정</button>
            <button type="button" class="btn btn-default" id="btnCancel"  onclick="location.href='/board/content?id=${boardUpdateDTO.id}'">취소</button>
        </div>
    </form>
</div>
<script src="/resources/js/board/UpdateForm.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ include file="../fragments/footer.jsp" %>
</body>
</html>
