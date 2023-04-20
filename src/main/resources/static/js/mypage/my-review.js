const $modal = $('.modal-wrap');

$('.review-main-img').on('click', function() {
    $('.modal-img img').attr('src', $(this).children().children().attr('src'));

    $('.full-screen').on('scroll touchmove mousewheel', function(e) {
        e.preventDefault();
        e.stopPropagation();
    });

    $modal.show();
});

$('.modal-close').on('click', () => {
    $modal.hide();

    $('.full-screen').off('scroll touchmove mousewheel');
});