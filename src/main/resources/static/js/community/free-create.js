$(document).ready(function() {

// // 초기 등록 버튼 색상 설정
// $('#regist_btn').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");

// // textarea 입력시 등록 버튼 색상 변경
// $('#content_textarea, #title_textarea').on('input', function() {
//   var contentLength = $('#content_textarea').val().length;
//   var titleLength = $('#title_textarea').val().length;
//   if (contentLength > 0 && titleLength > 0) {
//     $('#regist_btn').css("color", "white").css("background-color", "blue");
//   } else {
//     $('#regist_btn').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242, 244, 247)");
//   }
// });

// });
    
    
const file = document.querySelector('input[type=file]');
const imgDiv = document.querySelector('.file-name');
const closeSpan = document.querySelector('#btn_x');
const input = document.querySelector('#file_input');
const plusPicture = document.querySelector('#plus_picture');

// x 버튼을 누르면, 기본 이미지로 변경!
closeSpan.addEventListener('click', function (e) {
    e.preventDefault();
    input.value = "";
    this.style.display = 'none';
    imgDiv.style.backgroundImage = `url('')`;
    $("#plus_picture").show();
});

file.addEventListener('change', function (e) {
    closeSpan.style.display = "inline-block";
    this.style.display = 'none';
    let reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);
    $("#plus_picture").hide();
    reader.onload = function (e) {
        let result = e.target.result;
        if (result.includes('image')) {
            imgDiv.style.backgroundImage = `url('${result}')`;
        } else {
            imgDiv.style.backgroundImage = `url('no_image.png')`;
        }
    };
});