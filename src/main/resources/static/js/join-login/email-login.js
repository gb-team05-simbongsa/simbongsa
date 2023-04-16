const $email = $('.input-write').eq(0);
const $password = $('.input-write').eq(1);
const $emailError = $('.email-error').eq(0);
const $passwordError = $('.email-error').eq(1);
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

$email.on('keyup', () => {
    $emailError.css('display', 'none');
    $passwordError.css('display', 'none');
});

$('.submit-button').on('click', () => {
    if(!$email.val()) {
        $emailError.text('이메일 주소를 입력해주세요');
        $emailError.css('display', 'block');
    }

    if(!$password.val()) {
        $passwordError.text('비밀번호를 입력해주세요');
        $passwordError.css('display', 'block');
        return;
    } else {
        $passwordError.css('display', 'none');
    }

    if($password.val().length < 8) {
        $passwordError.text('비밀번호는 8자 이상이어야 합니다');
        $passwordError.css('display', 'block');
        return;
    } else {
        $passwordError.css('display', 'none');
    }

    if(!emailRegex.test($email.val())) {
        $emailError.text('이메일 형식에 맞게 입력하세요');
        $emailError.css('display', 'block');
        return;
    }

    document.loginForm.submit();
});