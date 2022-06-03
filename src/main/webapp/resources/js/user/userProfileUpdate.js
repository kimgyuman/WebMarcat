const fileValueElement = document.getElementById("inputFileCheck");

function readImage(input) {
    if(input.files && input.files[0]) {  // 인풋 태그에 파일이 있는 경우
        const reader = new FileReader()  // FileReader 인스턴스 생성
        reader.onload = e => {    // 이미지 로드
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        reader.readAsDataURL(input.files[0])    // reader가 이미지 읽음
    }
}

//이미지가 들어오면 썸네일 변경 이벤트
const inputImage = document.getElementById("input-image")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})

$(document).ready(function(){
    // input file 파일 첨부시 fileCheck 함수 실행

    $("#input-image").on("change", fileCheck);

    var fileTarget = $('.filebox .upload-hidden');

    fileTarget.on('change', function(){
        if(window.FileReader){
            var filename = $(this)[0].files[0].name;
        } else {
            var filename = $(this).val().split('/').pop().split('\\').pop();
        }
        $(this).siblings('.upload-name').val(filename);
    });

});

$(function () {
    $('#input-btn').click(function (e){
        e.preventDefault();
        $('#input-image').click();
    });
});

function fileCheck(e) { // 파일 첨부 버튼 클릭

    var files = e.target.files;
    let fileCheck = CheckImagefiles(files);
    if (fileCheck === false) {
        alert("파일의 확장자를 확인해주세요!");

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


function validation() {
    var uploadFile =  fileValueElement.value;
    if (uploadFile === "파일명") {
        alert("업로드할 이미지를 선택하세요.");
        return false;
    } else {
        alert("프로필 이미지가 적용되었습니다.");
        return true;
    }
}