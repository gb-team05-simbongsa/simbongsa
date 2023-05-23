$("form[name='form']").on("submit", function (e) {
    e.preventDefault();

    let formData = new FormData(); // Create FormData object
    formData.append('file', $('#file_input')[0].files[0]); // Append the file
    formData.append('boardTitle', $("input[name='boardTitle']").val()); // Append the title
    formData.append('boardContent', $("textarea[name='boardContent']").val()); // Append the content

    $.ajax({
        url: '/community/review-create',
        data: formData,
        processData: false,
        contentType: false,
        method: 'post',
        success: function () {
            // redirect 경로
            location.href = "/community/review-board/new";
        }
    });
});


$(document).ready(function() {
  // textarea 입력시 등록 버튼 색상 변경
  $('.title-input, .contents-text').on('input', function() {
      var contentLength = $('.contents-text').val().length;
      var titleLength = $('.title-input').val().length;

      if (contentLength > 0 && titleLength > 0) {
        $('.register-btn').attr('disabled', false);
      } else {
        $('.register-btn').attr('disabled', true);
      }
    
    });
});

const modal = document.querySelector('.modal');
const modalBack = document.querySelector('.modal-back');
const xBtn = document.querySelector('.register-btn');
const cancelBtn = document.querySelector('.choce1-btn');

function showModal() {
  modal.style.display = 'flex';
  modal.style.top = '50%';
  modal.style.left = '50%';
  modal.style.transform = 'translate(-50%, -50%)';
  modalBack.style.display = 'block';
  modalBack.style.position = 'fixed';
}

function hideModal() {
  modal.style.display = 'none';
  modalBack.style.display = 'none';
}

xBtn.addEventListener('click', showModal);
cancelBtn.addEventListener('click', hideModal);
modalBack.addEventListener('click', hideModal);




/* 파일 썸네일 */
/* 파일인풋 */
const file1 = document.querySelector('input[type=file]');
function handleFiles(files) {
    /* 썸네일 담을 div의 부모 */
    const thumbnailList = document.getElementById("photo-picker2");
    for (let i = 0; i < files.length; i++) {
        /* 파일절대경로얻기 */  
        const file1 = files[i];
        const reader = new FileReader();
        /* reader가 onload 할때 */
        reader.onload = function(event) {
            /* 썸네일 담을 div와 그 자식의 span 선언 */   
            const thumbnail = document.createElement("div");
            const thumbnailSpan = document.createElement("span");
            let result = event.target.result;
            /* 썸네일 담을 div와 그 자식의 span에 썸네일 css와 x버튼 css 추가*/
            thumbnail.classList.add("imageThumbnail");
            thumbnailSpan.classList.add("closeImgButton");
            /* 썸네일 담을 div에 절대경로 넣어주기 */
            thumbnail.style.backgroundImage = `url('${result}')`;
            /* 썸네일 담을 div와 그 자식의 span 추가해주기 */
        /* div는 위에 선언해 준 부모 div에 prepend 해주고 */
            /* span은 div에 appendChild 해주기 */
            thumbnailList.prepend(thumbnail);
            thumbnail.appendChild(thumbnailSpan);
            /* x버튼 선언 */
            const closeButton = document.querySelector(".closeImgButton");
            /* x버튼 누를 시 x버튼과 backgroundImage 지워주기 */
            closeButton.addEventListener('click', function (e) {
                e.preventDefault();
                file1.value = "";
                this.style.display = 'none';
                thumbnail.style.backgroundImage = `url('')`;
                thumbnail.remove(thumbnail);
            });
            
        };
        /* result 속성(attribute)에 담기 */
        reader.readAsDataURL(file1);
           
    }
}
/* 버튼을 감싸고있는 label객체 들고오기 */
const fileInput = document.getElementById("photo-picker");
/* 버튼을 감싸고있는 label객체 클릭하면 위에 function handleFiles 실행 */
fileInput.addEventListener("change", function(event) {
    handleFiles(event.target.files);
});

/*============================================================================================================*/
