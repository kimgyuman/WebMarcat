const spaceValidation = RegExp(/\s/g);
const numValidation = RegExp(/[0-9]/);

$(document).ready(function ()
    // input file 파일 첨부시 fileCheck 함수 실행
{
    $("#chooseFile").on("change", fileCheck);
});

/**
 * 첨부파일로직
 */
$(function () {
    $('#btn-upload').click(function (e) {
        e.preventDefault();
        $('#chooseFile').click();
    });
});

// 파일 현재 필드 숫자 totalCount랑 비교값
var fileCount = 0;
// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
var totalCount = 4;
// 파일 고유넘버
var fileNum = 0;
// 첨부파일 배열
var content_files = [];



function fileCheck(e) { // 파일 첨부 버튼 클릭

    var files = e.target.files;
    let fileCheck = CheckImagefiles(files);
    if (fileCheck === false) {
        alert("파일의 확장자를 확인해주세요!");
        return;
    }

    // 파일 배열 담기
    var filesArr = Array.prototype.slice.call(files);



    // 파일 개수 확인 및 제한
    if (fileCount + filesArr.length > totalCount) {
        alert('파일은 최대 ' + totalCount + '개까지 업로드 할 수 있습니다.');
        return;
    } else {
        fileCount = fileCount + filesArr.length;
    }

    // 각각의 파일 배열담기 및 기타
    filesArr.forEach(function (f) {
        var reader = new FileReader();
        reader.onload = function (e) {
            content_files.push(f);
            let fileDOM = document.createElement('div');
            fileDOM.className = 'file';
            fileDOM.id = 'fileL_' + fileNum;
            var setImageHtml = '';
            setImageHtml += '<img src = "' + URL.createObjectURL(f) + '" alt="파일타입 이미지" class="image">';
            fileDOM.innerHTML = `
        <div class="thumbnail">
          ${setImageHtml}
        </div>
        <div class="details">
            <div class="btn_removeImg">
                   <i class="fa-solid fa-xmark" id="deleteFile" onclick="deleteFile(${fileNum})"></i>
            </div>
          <header class="header">
            <span class="name">${f.name}</span>
            <span class="size">${f.size}/MB</span>
          </header>
          <div class="progress">
            <div class="bar"></div>
          </div>
          <div class="status">
            <span class="percent">100% done</span>
            <span class="speed">90KB/sec</span>
          </div>
        </div>
      `;
            $('#files').append(fileDOM);
            fileNum++;
        };
        reader.readAsDataURL(f);
    });

    //초기화 한다.
    $("#chooseFile").val("");
}

const dropFile = new DropFile('drop-file');

function DropFile(dropAreaId, fileListId) {// 파일 드레그엔 드롭

    let dropArea = document.getElementById(dropAreaId);

    function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
    }

    function highlight(e) {
        preventDefaults(e);
        dropArea.classList.add('highlight');
    }

    function unhighlight(e) {
        preventDefaults(e);
        dropArea.classList.remove('highlight');
    }

    dropArea.addEventListener('dragenter', highlight, false);
    dropArea.addEventListener('dragover', highlight, false);
    dropArea.addEventListener('dragleave', unhighlight, false);
    dropArea.addEventListener('drop', handleDrop, false);

    function handleDrop(e) {
        unhighlight(e);
        let dt = e.dataTransfer;
        let files = [...dt.files];

        let fileCheck = CheckImagefiles(files);
        if (fileCheck === false) {
            return;
        }


        var filesArr = Array.prototype.slice.call(files);


        // 파일 개수 확인 및 제한
        if (fileCount + filesArr.length > totalCount) {
            alert('파일은 최대 ' + totalCount + '개까지 업로드 할 수 있습니다.');
            return;
        } else {
            fileCount = fileCount + filesArr.length;
        }

        // 각각의 파일 배열담기 및 기타
        filesArr.forEach(function (f) {
            var reader = new FileReader();
            reader.onload = function (e) {
                content_files.push(f);
                let fileDOM = document.createElement('div');
                fileDOM.className = 'file';
                fileDOM.id = 'fileL_' + fileNum;
                var setImageHtml = '';
                setImageHtml += '<img src = "' + URL.createObjectURL(f) + '" alt="파일타입 이미지" class="image">';
                fileDOM.innerHTML = `
        <div class="thumbnail">
          ${setImageHtml}
        </div>
        <div class="details">
            <div class="btn_removeImg">
                   <i class="fa-solid fa-xmark" id="deleteFile" onclick="deleteFile(${fileNum})"></i>
            </div>
          <header class="header">
            <span class="name">${f.name}</span>
            <span class="size">${f.size}/MB</span>
          </header>
          <div class="progress">
            <div class="bar"></div>
          </div>
          <div class="status">
            <span class="percent">100% done</span>
            <span class="speed">90KB/sec</span>
          </div>
        </div>
      `;
                $('#files').append(fileDOM);
                fileNum++;
            };
            reader.readAsDataURL(f);
        });

        //초기화 한다.
        $("#chooseFile").val("");
    }
}

//파일 확장자 체크 함수
function CheckImagefiles(files) {
    let filesArr = [...files];
    var result = false;
    filesArr.forEach(function (f) {

        let fileName = f.name;
        var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (!ext) {
            alert("파일의 확장자가 존재하지 않는 파일입니다!");
            return result;
        }
        var imgs = ['gif', 'jpg', 'jpeg', 'png', 'bmp', 'ico', 'apng'];
        ext = ext.toLocaleLowerCase();
        imgs.forEach(function (element) {
            if (ext === element) {
                result = true;
            }
        });

    });

    return result;
}


// 파일 부분 삭제 함수
function deleteFile(fileNum) {
    content_files[fileNum].is_delete = true;
    $('#fileL_' + fileNum).remove();
    fileCount--;

}


/*
 * 폼 submit 로직
 */
function registerAction() {
    var form = $("form")[0];
    var formData = new FormData(form);
    for (var i = 0; i < content_files.length; i++) {
        // 삭제 안한것만 담아 준다.
        if (!content_files[i].is_delete) {
            formData.append("file", content_files[i]);
        }
    }

    var ftitle = document.querySelector("#title");
    var detail = document.querySelector("#contents");
    if (ftitle.value === ""|| detail.value === "" || fileCount == 0 ) {
        alert("작성하지 않은 정보가 있습니다!");
        return false;
    } else {
        var jsonData = {
            "title": ftitle.value,
            "contents": detail.value,
        }
        formData.append("jsonData", JSON.stringify(jsonData));

        /*
         * 파일업로드 multiple ajax처리
         */
        $.ajax({
            type: "POST",
            enctype: "multipart/form-data",
            url: "/user/board/write",
            data: formData,
            processData: false,
            contentType: false,
            async: false,
            success: function (data) {
                if (data.result === "200") {
                    // window.location.href = redirectUrl;
                    window.location.replace("/board");
                    alert("게시글 등록이 완료 되었습니다.");
                } else if (data.result === "100") {
                    alert("로그인 후 이용해주세요!");
                } else if (data.result === "400"){
                    alert("파일 형식이 올바르지 않습니다!");
                } else {
                    alert("올바른 방법으로 접근해주세요");
                }
            },
            errors: function () {
                alert("error")
            }
        });
        return false;
    }
}
