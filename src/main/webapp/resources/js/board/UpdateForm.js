

let title = document.querySelector('#title');
let content = document.querySelector('#contents');


let comeLocation = 0;//들어온 파일들의 화면 위치

$(document).ready(function () {
    console.log(realName);
    for (let i = 0; i < realName.length; i++) { // 기존의 파일들
        fileCount++;
        fileCome(realName[i], imgsName[i]);// 화면에 뿌려주기
    }
    title.value = oneBoardTitle;
    content.innerText = oneBoardContents;
});


function firstFilesDelete(index) { //기존 파일리스트에서 삭제해주기

    fileCount--;
    $('#fileL_'+index).remove();

    realName.splice((index / 100) - 1, 1);
    imgsName.splice((index / 100) - 1, 1);

    let value = $('#saved'+index).val();

    originFiles.push(value);

    for(var i =0; i<originFiles.length; i++) {
        // fileCome(realName[i]);
    }


    }


function fileCome(real, saved) { // 화면에 뿌려주는 메서드

    comeLocation += 100; // 추가로 들어오는 파일과 겹치지 않도록 위치값을 100단위로 줌
    let fileDOM = document.createElement('div');


    fileDOM.className = 'file';
    fileDOM.id = 'fileL_' + comeLocation;
    var setImageHtml = '';
    var savedImageHtml = '';
    savedImageHtml += '<input type="hidden" id="saved' + comeLocation + '" value= "'+saved+'" >'
    setImageHtml += '<img src = "' + saved + '" alt="파일타입 이미지" class="image">';
    fileDOM.innerHTML = `
        <div class="thumbnail">
          ${setImageHtml}
        </div>
        <div class="details">
            <div class="btn_removeImg">
                   <i class="fa-solid fa-xmark" id="deleteFile" onclick="firstFilesDelete(${comeLocation})"></i>
            </div>
          <header class="header">
            <span class="name">${real}</span>
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
    $('#files').append(savedImageHtml);
}
//---------------------------------------------------------기존파일 댕겨와서 array 에 넣기

const spaceValidation = RegExp(/\s/g);
const engValidation = RegExp(/[a-zA-Z]/);
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

function fileCheck(e) {

    var files = e.target.files;
    let fileCheck = CheckImagefiles(files);
    if (fileCheck === false) {
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
    for (var i=0; i<realName.length; i++){
    }

}

const dropFile = new DropFile('drop-file');

function DropFile(dropAreaId, fileListId) { // file drag drop 설정 메서드

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
// ----------------------------------------------- 일반 파일부


function registerAction() { // form data 를 생성하여 controller 로 보내줌
    var form = $("form")[0];
    var formData = new FormData(form);
    for (var i = 0; i < content_files.length; i++) {
        // 삭제 안한것만 담아 준다.
        if (!content_files[i].is_delete) {
            formData.append("files", content_files[i]);
        }
    }
    for (var i=0; i<realName.length; i++){
        formData.append("exist", imgsName[i]);
    }
    for (var i=0; i<originFiles.length; i++){
        formData.append("saved", originFiles[i]);
    }
        if (fileCount === 0 ) {
        alert("이미지를 선택하지 않았습니다!");
        return false;
    } else if (title.value === "" || content.value === "" ) {
        alert("작성하지 않은 정보가 있습니다!");
        return false;
    } else {
        formData.append("id",oneBoardId)
        formData.append("title", JSON.stringify(title.value));
        formData.append("contents", JSON.stringify(content.value));
        /*
         * 파일업로드 multiple ajax처리
         */
        $.ajax({
            type: "POST",
            enctype: "multipart/form-data",
            url: "/user/board/update?id="+oneBoardId,
            data: formData,
            processData: false,
            contentType: false,
            async: false,
            success: function (data) {
                if (data.result === "200") {
                    window.location.replace("/board/content?id="+oneBoardId);
                    alert("게시글 수정이 완료 되었습니다.");
                } else if (data.result === "100") {
                    alert("서버내 오류로 처리가 지연되고있습니다. 잠시 후 다시 시도해주세요");
                } else {
                    alert("올바른 방법으로 접근해주세요");
                }
            },
            errors: function () {
                alert("error")
            }
        });
        return false; //무조건 false를 줘서 jsp form이 실행되지 않도록 한다.
    }
}




