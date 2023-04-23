const $modal = $('.change-modal');

// 상세보기 버튼을 클릭했을 때 수정 모달 띄우기
$('.go-support-request').on('click', function() {
    $('.full-screen').on('scroll touchmove mousewheel', function(e) {
        e.preventDefault();
        e.stopPropagation();
    });

    $modal.show();
});

// 수정 모달이 띄워졌을 때 X버튼 누르면 모달 닫기
$('.modal-close').on('click', () => {
    $modal.hide();

    $('.full-screen').off('scroll touchmove mousewheel');
});

// 답변확인 버튼 눌렀을 때 답변이 있을 경우
$('.show-answer').on('click', function() {
    if($(this).parent().parent().parent().next().css('display') == 'none') {
        $(this).parent().parent().parent().next().show();
    } else {
        $(this).parent().parent().parent().next().hide();
    }
});

// 답변확인 버튼 눌렀을 때 답변이 없을 때 모달 띄우기
// $('.show-answer').on('click', () => {
//     $('.full-screen').on('scroll touchmove mousewheel', function(e) {
//         e.preventDefault();
//         e.stopPropagation();
//     });

//     $('.modal-wrap').show();
// });

// $('.modal-ok').on('click', () => {
//     $('.modal-wrap').hide();
//     $('.full-screen').off('scroll touchmove mousewheel');
// });