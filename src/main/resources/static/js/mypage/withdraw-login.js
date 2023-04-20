const $password = $('.input-write').eq(1);

$('.submit-button').on('click', () => {
    if(!$password.val()) {
        $('.email-error').text('비밀번호를 입력해주세요');
        $('.email-error').css('display', 'block');
        return;
    }

    if($password.val().length < 8) {
        $('.email-error').text('비밀번호는 8자 이상이어야 합니다');
        $('.email-error').css('display', 'block');
        return;
    }

    $('.modal-wrap').show();
});

$('.modal-ok').on('click', () => {
    document.withdrawForm.submit();
});