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