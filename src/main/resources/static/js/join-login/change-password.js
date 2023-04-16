const $password = $('.input-write').eq(0);
const $passwordCheck = $('.input-write').eq(1);
const $errorPassword = $('.email-error').eq(0);
const $errorPasswordCheck = $('.email-error').eq(1);
const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;

// 비밀번호 변경 칸에 값이 바뀔때마다 실행
$password.on('keyup', () => {
    // 비밀번호 정규식으로 비교하여 맞으면 에러 사라짐
    if(!passwordRegex.test($password.val())) {
        $errorPassword.text('영문 대소문자, 숫자, 특수문자 포함 8자 이상으로 설정해주세요');
        $errorPassword.css('display', 'block');
    } else {
        $errorPassword.css('display', 'none');
    }

    // 비밀번호와 비밀번호 확인이 동일하지 않으면 에러 출력
    if($passwordCheck.val() != $password.val()) {
        $errorPasswordCheck.text('비밀번호가 일치하지 않습니다');
        $errorPasswordCheck.css('display', 'block');
    } else {
        $errorPasswordCheck.css('display', 'none');
    }
});

// 비밀번호 확인에 값이 바뀔때마다 실행
$passwordCheck.on('keyup', () => {
    // 비밀번호와 비밀번호 확인이 동일하지 않으면 에러 출력
    if($passwordCheck.val() != $password.val()) {
        $errorPasswordCheck.text('비밀번호가 일치하지 않습니다');
        $errorPasswordCheck.css('display', 'block');
    } else {
        $errorPasswordCheck.css('display', 'none');
    }
});

$('.submit-button').on('click', () => {
    // 비밀번호의 값이 없을 때 에러 출력
    if(!$password.val()) {
        $errorPassword.text('변경할 비밀번호를 입력해주세요');
        $errorPassword.css('display', 'block');
    }

    // 비밀번호 확인의 값이 없을 때 에러 출력
    if(!$passwordCheck.val()) {
        $errorPasswordCheck.text('변경할 비밀번호를 한번더 입력해주세요');
        $errorPasswordCheck.css('display', 'block');
    }

    // 두개의 에러가 모두 없을 때 submit
    if($errorPassword.css('display') == 'none' && $errorPasswordCheck.css('display') == 'none') {
        document.changePasswordForm.submit();
    }
});