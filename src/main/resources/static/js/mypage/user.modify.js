const $modifySubmit = $('.modify-submit');
const $errorMessage = $('.error-message');
const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;


// 각 요소의 최종 submit 버튼을 눌렀을 때
// input에 값이 없을 경우 에러 메시지 출력
$modifySubmit.on('click', function() {
    // 비밀번호 변경일 경우 따로 처리
    if(this == $modifySubmit[0]) {
        let check = [false, false, false];
        let $inputs = $(this).parent().prev().find('input');
        for(let i = 0; i < $inputs.length; i++) {
            // input 태그의 값이 비어있거나 정규식에 맞지 않을 경우 에러 메시지 출력
            if(!$inputs.eq(i).val()) {
                $(this).parent().prev().find('.error-message').eq(i).show();
                check[i] = false;
            } else {
                $(this).parent().prev().find('.error-message').eq(i).hide();

                if(!passwordRegex.test($inputs.eq(i).val())) {
                    $(this).parent().prev().find('.error-message').eq(i).text('영문 대소문자, 숫자, 특수문자 포함 8자 이상으로 설정해주세요.');
                    $(this).parent().prev().find('.error-message').eq(i).show();
                    check[i] = false;
                } else {
                    check[i] = true;
                }
            }
        }

        // 바꿀 비밀번호와 비밀번호 확인 값이 다르면 에러 메시지 출력
        if($inputs.eq(1).val() != $inputs.eq(2).val()) {
            $(this).parent().prev().find('.error-message').eq(2).text('바꿀 비밀번호와 똑같이 적어주세요.');
            $(this).parent().prev().find('.error-message').eq(2).show();
            return;
        }

        // 모든걸 통과했을 경우 submit
        if(check[0] && check[1] && check[2]) {
            $(this).parent().parent().submit();
        }
    }

    // 비밀번호 변경 제외 나머지
    // 값이 비어있으면 에러 메시지 출력
    if(!$(this).parent().prev().find('input').val()) {
        $(this).parent().prev().find('.error-message').show();
        return;
    } else {
        $(this).parent().prev().find('.error-message').hide();
    }

    // 다 통과했을 경우 submit
    $(this).parent().parent().submit();
});




// 각 요소 오른쪽 위 변경 버튼을 눌렀을 때
// 그 요소는 숨기고 그 요소에 맞는 변경 입력 요소 보여주기
// 단, 탈퇴 버튼을 눌렀을땐 해당되지 않음
// 그리고 기존 정보가 있다면 input 태그 안에 띄움
$('.modify-button').on('click', function() {
    if($(this).text() == '탈퇴') {
        return;
    }

    $(this).parent().parent().hide();
    $(this).parent().parent().next().show();
    $(this).parent().parent().next().find('input').val($(this).parent().next().text());
});

// 각 요소 오른쪽 위 취소 버튼을 눌렀을 때
// 그 요소는 숨기고 그 요소에 맞는 원래 요소 보여주기
$('.modify-button-cancel').on('click', function() {
    $(this).parent().parent().parent().hide();
    $(this).parent().parent().find('.error-message').hide();
    $(this).parent().parent().find('.drop-content').hide();
    $(this).parent().parent().find('input').val('');
    $(this).parent().parent().parent().prev().show();
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

// 주소 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.querySelector('#address').value = data.address; // 주소 넣기
        },
    }).open();
}