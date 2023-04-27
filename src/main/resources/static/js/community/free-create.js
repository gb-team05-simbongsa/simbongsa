$(document).ready(function() {

// 초기 등록 버튼 색상 설정
$('.register-btn').css({
    "color": "#fff",
    "background-color": "#c5c5c5"
  });
  
  // textarea 입력시 등록 버튼 색상 변경
  $('.title-input, .contents-text').on('input', function() {
    var contentLength = $('.contents-text').val().length;
    var titleLength = $('.title-input').val().length;
    
    if (contentLength > 0 && titleLength > 0) {
      $('.register-btn').css({
        "color": "#fff",
        "background-color": "rgba(229, 104, 89, 0.83)"
      });
    } else {
      $('.register-btn').css({
        "color": "#fff",
        "background-color": "#c5c5c5"
      });
    }
  });
  
});
    
    
const file = document.querySelector('#photo');
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