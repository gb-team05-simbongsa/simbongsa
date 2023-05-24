/* 목록 */
myFreeBoards.forEach((myFreeBoard, i) => {
    let text;

    text += `
        <div>
            <div class="review-header">
                <div class="review-title">
                    <div class="review-title-text">
                        ${myFreeBoard.boardTitle}
                    </div>
                    <div>
                        <a href="my-free-board-detail/${myFreeBoard.id}">
                            <span class="review-modify">수정</span>
                        </a>
                        <span class="review-delete">삭제</span>

                    </div>
                </div>
            </div>
            <div class="review-main">
                <div class="review-main-img-wrap">
                `
    if(myFreeBoard.fileDTOS == null || myFreeBoard.fileDTOS == undefined) {
        text += `
            <img src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> `;
    }
    else {
        for(let j = 0; j < myFreeBoard.fileDTOS.length; j++) {
            text +=
                ` <div class="review-main-img">
                        <div class="review-img">
                            <img src="/file/display?fileName=${myFreeBoard.fileDTOS[j].filePath + '/' + myFreeBoard.fileDTOS[j].fileUuid + '_' + myFreeBoard.fileDTOS[j].fileName}">
                        </div>
                   </div>
                    `;
        }
    }

    text += `        
            </div>
                <div class="review-content">
                    ${myFreeBoard.boardContent}
                </div>
            </div>
            <div class="review-main-footer">
                <div class="review-write-date">
                    <span>${myFreeBoard.updatedDate.substring(0,10)} ${myFreeBoard.updatedDate.substring(11,19)}</span>
                    <span>자유게시판</span>
                </div>
            </div>
        </div>
    `;

    $('.my-review-list').prepend(text);
});


const $modal = $('.modal-wrap');

// 사진을 누르면 모달형태로 사진 보여주기(스크롤 막음)
$('.review-main-img').on('click', function() {
    $('.modal-img img').attr('src', $(this).children().children().attr('src'));

    $('.full-screen').on('scroll touchmove mousewheel', function(e) {
        e.preventDefault();
        e.stopPropagation();
    });

    $modal.show();
});

// X를 누르면 모달 숨김(스크롤 품)
$('.modal-close').on('click', () => {
    $modal.hide();

    $('.full-screen').off('scroll touchmove mousewheel');
});

/* 수정버튼 누르면 작성페이지로 이동 */
$('.review-modify').on('click', function () {
    console.log("수정버튼눌림");
    location.href = "/community/board-modify";
})

/* 삭제 모달 */
const $deleteModal = $('.delete-modal-wrap');

// 모달에서 삭제 버튼을 눌렀을 때 진짜 삭제할건지 모달 띄우기
$('.review-delete').on('click', () => {
    $('.delete-modal-wrap').show();
});

// 삭제할건지 묻는 모달에서 취소 버튼 눌렀을 때 모달 닫기
$('.modal-cancel').on('click', () => {
    $('.delete-modal-wrap').hide();
    $deleteModal.hide();
});
