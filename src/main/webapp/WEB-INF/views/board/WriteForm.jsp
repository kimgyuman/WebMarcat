
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>동네생활</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/board/WriteForm.css" rel="stylesheet"/>
<body>
<%@ include file="../fragments/header.jsp" %>
<div class="container">
    <div class="top_title"><h2>동네 생활 글쓰기</h2></div>
    <form class="page-section" id="contact" onsubmit="return registerAction();">
        <div class="form-group">
            <label for="title"><h4>Title</h4></label>

            <input type="text" class="form-control" id="title"
                        placeholder="제목을 입력해주세요" name="title"
                        maxlength="100"/>
        </div>
        <!-- insert file upload -->
        <div id="all_file_container">
            <hr>
            <div class="divider-custom">
                <div class="divider-custom-line"></div>
                <h4>FILES</h4>
                <div class="divider-custom-line"></div>
            </div>
            <div class="inner_file_container">
                <div class="upload-box">
                    <div id="drop-file" class="drag-file">
                        <p class="message">여기에 파일을 드래그 해 주세요 <br/> 파일은 4개까지 올릴 수 있습니다!</p>
                    </div>
                    <label class="file-label" for="chooseFile">파일 선택</label>
                    <input class="file" id="chooseFile" type="file" name="file" multiple="multiple" accept="image/*">
                </div>
                <div id="files" class="files">

                </div>
            </div>
        </div>
        <!-- end file upload -->
        <div class="form-group">
            <hr>

            <label for="contents"><h4>내용</h4></label>

            <textarea class="form-control" rows="5" id="contents"
                           name="contents" placeholder="내용을 작성해주세요"></textarea>
        </div>

        <div class="btn_writeCancel">
            <button type="submit" class="btn btn-default" id="btnSave">등록</button>
            <button type="button"  onclick="location.href='/board'" class="btn btn-default" id="btnCancel">취소</button>
        </div>
    </form>


<%--  </form>--%>
</div>
<script src="/resources/js/board/WriteForm.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ include file="../fragments/footer.jsp" %>
</body>
</html>
