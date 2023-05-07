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
    location.href = "/community/free-create";
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