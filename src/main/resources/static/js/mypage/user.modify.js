const $modifySubmit = $('.modify-submit');
const $errorMessage = $('.error-message');

// 각 요소 오른쪽 위 변경 버튼을 눌렀을 때
// 그 요소는 숨기고 그 요소에 맞는 변경 입력 요소 보여주기
// 단, 탈퇴 버튼을 눌렀을땐 해당되지 않음
$('.modify-button').on('click', function() {
    if($(this).text() == '탈퇴') {
        return;
    }
    $(this).parent().parent().hide();
    $(this).parent().parent().next().show();
});

// 각 요소 오른쪽 위 취소 버튼을 눌렀을 때
// 그 요소는 숨기고 그 요소에 맞는 원래 요소 보여주기
$('.modify-button-cancel').on('click', function() {
    $(this).parent().parent().hide();
    $(this).parent().parent().find('.error-message').hide();
    $(this).parent().parent().find('.drop-content').hide();
    $(this).parent().parent().prev().show();
});

// 각 요소의 최종 submit 버튼을 눌렀을 때
// input에 값이 없을 경우 에러 메시지 출력
// 비밀번호 변경일 경우 따로 처리
$modifySubmit.on('click', function() {
    if(this == $modifySubmit[0]) {
        $(this).parent().prev().find('input').each((i, e) => {
            if(!$(e).val()) {
                $(this).parent().prev().find('.error-message').eq(i).show();
            } else {
                $(this).parent().prev().find('.error-message').eq(i).hide();
            }
        });
        return; 
    }

    if(!$(this).parent().prev().find('input').val()) {
        $(this).parent().prev().find('.error-message').show();
    } else {
        $(this).parent().prev().find('.error-message').hide();
    }
});

// 관심봉사활동을 선택하는 인풋쪽을 클릭했을 때 드롭다운 보여주거나 숨기기
$('label[for=drop-span]').on('click', function() {
    if($('.drop-content').css('display') == 'none') {
        $('.drop-content').show();
    } else {
        $('.drop-content').hide();
    }
});

// 드롭다운에서 하나를 선택했을 때 input태그에 값 입력이 들어가고 에러 메시지 숨기기
$('.drop-content p').on('click', function() {
    $('.drop-content').hide();
    $('#interest-volunteer').val($(this).text());
    $errorMessage.eq(6).hide();
});