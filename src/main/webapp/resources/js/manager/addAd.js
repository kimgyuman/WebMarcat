const spaceValidation = RegExp(/\s/g);
const numValidation = RegExp(/[0-9]/);
const urlValidation = RegExp(/(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/);
const imageValidation = RegExp(/(.*?)\.(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$/);
const expiryTimeElement = document.getElementById('expiryTime');
const titleElement = document.getElementById('title');
const urlElement = document.getElementById("url");
const imageElement = document.getElementById("input-image");



function adValidation() {
    var expiry = expiryTimeElement.value;
    var title = titleElement.value;
    var url = urlElement.value;
    var image = imageElement.value;

    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var today = year+'-'+month+'-'+day;
    var todayDate = new Date(today);
    var expiryDate = new Date(expiry);
    var maxSize = 52428800;
    var minSize = 1048576;



    if (image === "") {
        alert("파일은 필수입니다.")
        return false;
    } else if (!imageValidation.test(image)) {
        alert("이미지 파일만 업로드 가능합니다")
        return false;
    } else if (document.getElementById('input-image').files[0].size > maxSize && document.getElementById('input-image').files[0].size < minSize) {
        alert("파일은 1MB 이상 50MB 미만으로 등록 가능합니다.")
        return false;
    } else if (expiry === "") {
        alert("종료 시기는 필수입니다.")
        return false;
    } else if (title === "") {
        alert("제목은 필수입니다.")
        return false;
    } else if (url === "") {
        alert("url을 입력해주세요.")
        return false;
    } else if (expiryDate <= todayDate) {
        alert('종료시기는 오늘 이후부터 가능합니다.')
        return false;
    } else if (title.length < 5 || title.length > 60) {
        alert('제목은 5자 이상 60자 미만으로 입력하세요.')
        return false;
    } else if (!urlValidation.test(url)) {
        alert('url을 정확히 입력하세요.')
        return false;
    } else {
        return true;
    }
}

function readImage(input) {
    if(input.files && input.files[0]) {

        const reader = new FileReader()
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        reader.readAsDataURL(input.files[0])
    }
}

imageElement.addEventListener("change", e => {
    readImage(e.target)
})
