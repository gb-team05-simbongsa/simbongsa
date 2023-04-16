const $email = $('.input-write');
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
const $error = $('.email-error');

// 이메일에 값이 변경될때마다 오류 메세지 감추기
$email.on('keyup', () => {
    $error.css('display', 'none');
});

// 비밀번호 설정 링크 버튼 눌렀을때
$('.submit-button').on('click', () => {

    // 빈 값일 경우 오류 메세지 출력
    if(!$email.val()) {
        $error.text('이메일 주소를 입력해주세요');
        $error.css('display', 'block');
        return;
    }
    
    // 이메일 형식이 맞지 않을 경우 오류 메세지 출력
    if(!emailRegex.test($email.val())) {
        $error.text('이메일 형식을 확인해주세요');
        $error.css('display', 'block');
        return;
    }

    // 위 검사를 성공적으로 완료하였을 때 submit
    document.findPasswordForm.submit();
});