// 공지사항 목록 출력
notices.forEach(notice => {
    let text;

    text = `
    <tr class="table__content">
        <td>
            <label class="check-label">
               <input type="checkbox" name="check" />
            </label>
        </td>
        <td class="content__id">${notice.id}</td>
        <td class="notice-content">${notice.noticeTitle}</td>
        <td>${notice.createdDate}</td>
        <td>${notice.updatedDate}</td>
        <td>관리자</td>
        <td>
            <button class="content__detail__btn button__type_2 button__color__green show-detail">
                상세보기
            </button>
        </td>
    </tr>
`;

    $('.table').append(text);
});

$('.content__detail__btn').on('click', function () {
    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    adminService.getDetail("/admins/notice-details", contentId, function(result) {
        $('input[name=id]').val(contentId);
        $('input[name=noticeTitle]').val(result.noticeTitle);
        $('.notice-detail-content').val(result.noticeContent);
    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});

$('#confirm-delete').on('click', function() {
    adminService.deleteAllById("/admins/notices-delete", $checkArr, function() {
        document.location.reload(true);
    });
});



$('#completeBtn').on('click', () => {
    $('input[name=noticeContent]').val($('.notice-detail-content').val());
    document.updateForm.submit();
});

/* 공지 사항 작성 버튼 */
$('#create-button').on('click', function () {
    $(".modal-stage").html(
        `
       <section class="modal" id="modal" th:fragment="notice-modal">
          <div class="modal__header">
            <h3 class="modal__title">공지 작성하기</h3>
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
          <form class="storage_form" name="noticeForm">
            <main class="modal__main">
              <div class="modal__content">
                <div class="content__title">
                  <h4>공지사항 제목</h4>
                  <div class="content__intput notice__title" style="margin-left: 11px;">
                    <input type="text" name="noticeTitle" id="noticeTitle" placeholder="공지사항 제목">
                  </div>
<!--                     <h4 style="margin-left: 37px;">작성자</h4>-->
<!--                  <div class="content__intput notice__title" style="margin-right: 17px; margin-left: -33px;">-->
<!--                    <input type="text" name="noticeWriter" placeholder="작성자">-->
<!--                  </div>-->
<!--                <div class="content__intput date" style="flex: 0">-->
<!--                    <input type="date" name="noticeRegist">-->
<!--                  </div>-->
                  </div>
                <div class="content__intput input_box_shadow" style="margin-left: 6px;">
                    <textarea id="noticeContent"></textarea>
                </div>
              </div>
              <div id="#completeBtn" class="user__profile__button">
                <button class="button__type_2 button__color__green" type="button" id="saveButton" onclick="save()">작성완료</button>
              </div>
            </main>
          </form>
        </section>
        `
    );
    // $('#summernote').summernote({
    //     placeholder: '공지사항 내용 작성',
    //     tabsize: 2,
    //     height: 300,
    //     width: '100%',
    //     toolbar: [
    //         ['style', ['style']],
    //         ['font', ['bold', 'underline', 'clear']],
    //         ['color', ['color']],
    //         ['para', ['ul', 'ol', 'paragraph']],
    //         ['table', ['table']],
    //         ['insert', ['link']],
    //     ],
    // });

    $modalStage.show();
    $('.modal-close').on('click', function () {
        $modalStage.fadeOut(500);
    });

    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
})

function save() {
    let noticeDTO = {
        id : 0,
        noticeTitle : $('#noticeTitle').val(),
        noticeContent : $('#noticeContent').val(),
        createdDate : null,
        updatedDate : null
    };

    adminService.saveNotice("/admins/notice-save", noticeDTO, function() {
        document.location.reload(true);
    })
}

$('.search').on('click', () => {
    location.href = "/admin/notice?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});