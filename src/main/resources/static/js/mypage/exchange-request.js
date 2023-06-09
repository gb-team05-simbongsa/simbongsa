const $inputs = $('.change-modal-form-title-input');
const $errorMessage = $('.error-message');
const numberRegex = /^[0-9]+$/;

$('.change-modal-ok-btn').on('click', () => {
    // input값이 비어있으면 에러 메시지 출력
    $inputs.each((i, e) => {
        if(!$(e).val()) {
            $errorMessage.eq(i).text('비워둘 수 없습니다.');
            $errorMessage.eq(i).show();
        } else {
            $errorMessage.eq(i).hide();
        }
    });

    // 계좌번호와 환전할 수 있는 공양미는 숫자만 입력받게 정규식 테스트
    for(let i = 1; i < $errorMessage.length; i++) {
        if(!numberRegex.test($inputs.eq(i).val()) && $inputs.eq(i).val()) {
            $errorMessage.eq(i).text('숫자만 입력하세요.');
            $errorMessage.eq(i).show();
        }
    }

    // 에러 메시지가 떠있는게 있으면 빠져나가기
    for(let i = 0; i < $errorMessage.length; i++) {
        if($errorMessage.eq(i).css('display') == 'block') {
            return;
        }
    }

    // 다 통과했다면 submit
    document.exchangeForm.submit();
});

// 환전할 수 있는 공양미 보다 큰 값을 입력했을 경우 환전할 수 있는 최대 공양미 수로 변경
$inputs.eq(2).on('blur', function() {
    if(parseInt($(this).val()) > parseInt($('.able-exchange-rice').text())) {
        $(this).val($('.able-exchange-rice').text());
    }
});