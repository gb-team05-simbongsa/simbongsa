boards.forEach(board => {
    let text;

    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                    <input type="checkbox" name="check"/>
                </label>
            </td>
            <td class="content__id">${board.id}</td>
            <td class="notice-content">${board.boardTitle}</td>
            <td>` + adminService.dateFormat(`${board.createdDate}`) + `</td>
            <td>` + adminService.dateFormat(`${board.updatedDate}`) + `</td>
            <td>${board.memberDTO.memberEmail}</td>
            <td>${board.memberDTO.memberName}</td>
            <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
            </td>
        </tr>
    `;

    $('.table').append(text);
});

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

$('#confirm-delete').on('click', function() {
    adminService.deleteOrUpdate("/admins/free-boards-delete", $checkArr, function() {
        document.location.reload(true);
    });
});

$('.search').on('click', () => {
    location.href = "/admin/volunteer-recruitment-board?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});

$('.content__detail__btn').on('click', function () {
    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    adminService.getDetail("/admins/freeboard-details", contentId, function(result) {
        let text;

        text = `
            <section class="modal">
                <div class="modal__header">
                    <h3 class="modal__title">게시판 상세보기</h3>
                    <a class="modal-close" onclick="modalClose()">
                        <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                            <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                        </svg>
                    </a>
                </div>
                <form class="storage_form" action="">
                    <main class="modal__main">
                        <div class="modal__content">
                            <div class="content__title">
                                <h4>게시판 제목</h4>
                                <div class="content__intput notice__title">
                                    <input type="text" name="" value="${result.boardTitle}" style="width: 100%" readonly/>
                                </div>
                            </div>
                            <div class="content__title" style="display: block">
                                <h5>작성 날짜</h5>
                                <div class="content__intput notice__title">
                                    <input type="text" name="" value="` + adminService.dateFormat(`${result.createdDate}`) + `" readonly/>
                                </div>
                                <h5>수정 날짜</h5>
                                <div class="content__intput notice__title">
                                    <input type="text" name="" value="` + adminService.dateFormat(`${result.updatedDate}`) + `" readonly/>
                                </div>
                            </div>
                            <div class="content__img__wrap">
                                <!-- 임시로 name='file' 해둠 -->`;
        let fileDTOs = result.fileDTOS;
        if(fileDTOs.length == 0) {
            text += `
                    <label>
                        <div class="content__img">
                            <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" style="width: 200px"> 
                        </div>
                    </label>
                    `;
        } else {
            fileDTOs.forEach((fileDTO) => {
                console.log(fileDTO); // 각 요소 출력 예시
                text += `
                        <label>
                            <div class="content__img">
                                <img src="/file/display?fileName=` + fileDTO.filePath + '/' + fileDTO.fileUuid + '_' + fileDTO.fileName + `" style="width: 200px">
                            </div>
                        </label>
                        `;
                    });
                 }
        text += `
                            </div>
                            <div class="content__intput input_box_shadow" style="margin-left: 6px;">
                                <textarea readonly>${result.boardContent}</textarea>
                            </div>
                        </div>
                        <div class="user__profile__button">
                            <button id="completeBtn" class="button__type_2 button__color__green" onclick="modalClose()">
                                닫기
                            </button>
                        </div>
                    </main>
                </form>
            </section>
        `;

        $('.modal-stage').html(text);

    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();
});

function modalClose() {
    $modalStage.fadeOut(500);
    $('.modal-stage').empty();
}