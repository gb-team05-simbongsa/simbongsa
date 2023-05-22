const $password = $('.input-write').eq(1);

// 입력 비밀번호 글자 수 검사 및 비어있지 않은지 검사
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

    let loginCheck = false;
    $.ajax({
        url: "/mypages/check-member",
        type: "post",
        data: { memberEmail : $('#memberEmail').val(), memberPassword : $('#memberPassword').val() },
        async: false,
        success: function(result) {
            if(!result) {
                console.log(result);
                loginCheck = false;
            } else {
                console.log(result);
                loginCheck = true;
            }
        }
    });
    console.log(loginCheck);

    if(!loginCheck) {
        $('.email-error').text('비밀번호가 맞지 않습니다');
        $('.email-error').css('display', 'block');
        return;
    }

    $('.modal-wrap').show();
});



// 모달에서 확인 버튼을 누르면 submit
$('.modal-ok').on('click', () => {
    document.withdrawForm.submit();
});