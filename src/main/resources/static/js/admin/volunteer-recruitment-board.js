/* 게시판 작성 버튼 */
$('#create-button').on('click', function(){
    $(".modal-stage").html(
        `
       <section class="modal" id="modal" th:fragment="notice-modal">
          <div class="modal__header">
            <h3 class="modal__title">게시판 작성하기</h3>
            <a class="modal-close" id="modal-close">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                data-name="Capa 1"
                id="Capa_1"
                viewBox="0 0 20 19.84">
                <path
                  d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z" />
              </svg>
            </a>
          </div>
          <form class="storage_form" name="noticeForm" method="" action="">
            <main class="modal__main">
              <div class="modal__content">
                <div class="content__title">
                  <h4>게시판 제목</h4>
                  <div class="content__intput notice__title" style="margin-left: 11px;">
                    <input type="text" name="noticeTitle" placeholder="게시판 제목">
                  </div>
                     <h4 style="margin-left: 37px;">작성자</h4>
                  <div class="content__intput notice__title" style="margin-right: 17px; margin-left: -33px;">
                    <input type="text" name="noticeWriter" placeholder="작성자">
                  </div>
                <div class="content__intput date" style="flex: 0">
                    <input type="date" name="noticeRegist">
                  </div></div>
                <div class="notice__content">
                  <textarea id="summernote" name="noticeContent"></textarea>
                </div>
              </div>
              <div id="#completeBtn" class="user__profile__button">
                <button class="button__type_2 button__color__green">작성완료</button>
              </div>
            </main>
          </form>
        </section>
        `
        
    );
  $('#summernote').summernote({
  placeholder: '게시판 내용 작성',
  tabsize: 2,
  height: 300,
  width: '100%',
  toolbar: [
      ['style', ['style']],
      ['font', ['bold', 'underline', 'clear']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link', 'picture', 'video']],
  ],
});

$modalStage.show();
$('.modal-close').on('click', function () {
    $modalStage.fadeOut(500);
    });

$('.modal-close').on('click', function (e) {
  $modalStage.fadeOut(500); 
});    
})