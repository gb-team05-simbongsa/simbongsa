const $inputs = $('.input-write');
const $errorMessage = $('.email-error')
const emailRegex = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@#$%^&*_-]).{8,}$/;
const $checkboxes = $('.agree').find('input');
const $all = $('.agree-all');
const $checks = $('.agree');
let emailCheck = false;

$('.submit-button').on('click', function(event) {
    if(kakaoinfo != null) {
        emailCheck = true;
    }

    // 비어있는 input 태그가 있을 시 에러 메시지 출력
    $inputs.each((i, e) => {
        if(!$(e).val()) {
            $errorMessage.eq(i).text('비워놓을 수 없습니다.');
            $errorMessage.eq(i).show();
        } else {
            $errorMessage.eq(i).hide();
        }
    });
    
    // 이메일이 정규식에 맞지 않으면 에러 메시지 출력
    if($inputs.eq(1).val() && !emailRegex.test($inputs.eq(1).val())) {
        $errorMessage.eq(1).text('이메일 형식에 맞게 입력하세요.');
        $errorMessage.eq(1).show();
    }
    
/*    // 이메일 주소 확인 시 위에와 일치하지 않으면 에러 메시지 출력
    if($inputs.eq(2).val()) {
        if($inputs.eq(1).val() != $inputs.eq(2).val()) {
            $errorMessage.eq(2).text('이메일이 일치하지 않습니다.');
            $errorMessage.eq(2).show();
        }
    }*/

    // 비밀번호가 정규식에 맞지 않으면 에러 메시지 출력
    if($inputs.eq(2).val() && !passwordRegex.test($inputs.eq(3).val())) {
        $errorMessage.eq(2).text('영문 대소문자, 숫자, 특수문자 포함 8자 이상으로 설정해주세요.');
        $errorMessage.eq(2).show();
    }

    // 비밀번호 확인 시 위에와 일치하지 않으면 에러 메시지 출력
    if($inputs.eq(3).val()) {
        if($inputs.eq(2).val() != $inputs.eq(3).val()) {
            $errorMessage.eq(3).text('비밀번호가 일치하지 않습니다.');
            $errorMessage.eq(3).show();
        }
    }

    // 
    if($checkboxes.eq(0).prop('checked') && $checkboxes.eq(1).prop('checked') && $checkboxes.eq(2).prop('checked')) {
        $('.check-error').hide();
    } else {
        $('.check-error').show();
    }

    // 에러 메시지가 떠있는게 있으면 빠져나가기
    for(let i = 0; i < $errorMessage.length; i++) {
        if($errorMessage.eq(i).css('display') == 'block' || $('.check-error').css('display') == 'block') {
            return;
        }
    }

    if(!emailCheck) {
        $('.modal-content').text('이메일 중복을 확인해주세요.');
        $('.modal-wrap').show();
        return;

    }

    // 다 통과했다면 submit
    document.joinForm.submit();

});


//이메일 중복
$('.email-check').on("click", function(){
    $.ajax({
        url:"/members/check-email" ,
        data: { memberEmail: $('.input-email').val() },
        type: "post",
        success: function(result){
            if(result >= 1) {
                $errorMessage.eq(1).text('사용 불가능한 이메일입니다.');
                $errorMessage.eq(1).show();
            } else {
                emailCheck = true;
                $('.modal-content').text('사용 가능한 이메일입니다.');
                $('.modal-wrap').show();
                $errorMessage.eq(1).hide();

            }
        }
    });
});

// 모달에 확인 버튼 누르면 모달 끄기
$('.modal-ok').on('click', () => {
    $('.modal-wrap').hide();
});


// 관심봉사활동을 선택하는 인풋쪽을 클릭했을 때 드롭다운 보여주거나 숨기기
$('label[for=drop-span]').on('click', function() {
    if($('.drop-content').css('display') == 'none') {
        $('.drop-content').show();
    } else {
        $('.drop-content').hide();
    }
});

// 드롭다운에서 하나를 선택했을 때 input태그에 값 입력이 들어가게 하기
$('.drop-content p').on('click', function() {
    $('.drop-content').hide();
    $('#interest-volunteer').val($(this).text());
});

// 주소 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            document.querySelector('#address').value = data.address; // 주소 넣기
        },
    }).open();
}

// 동의 버튼 효과
$checkboxes.each((i,e)=>{
    $(e).parent().on('click', function(){
        var $ischecked = $(e).is(':checked');
        if($ischecked){
            $(e).prev().css('display', 'none');
            $(e).prop('checked', false);
        }else{
            $(e).prev().css('display', 'flex');
            $(e).prop('checked', true);
        }
    });
});

// 전체동의 버튼 효과
$all.on("click", function(){
    var $checked = $('#agree-all').is(':checked');
    if($checked) {
        $all.children().css('display', 'none');
        $all.children().prop('checked', false);
        $checkboxes.prev().css('display', 'none');
        $checkboxes.prop('checked', false);
    } else {
        $all.children().css('display', 'flex');
        $all.children().prop('checked', true);
        $checkboxes.prev().css('display', 'flex');
        $checkboxes.prop('checked', true);
    }
});

// 동의 버튼 전체 확인 시 전체동의도 확인 효과, 필수사항 동의 시 submit 버튼 활성화
$checks.on('click', function(){
    if($checkboxes.filter(":checked").length == 5) {
        $all.children().eq(0).css('display', 'flex');
        $all.children().prop('checked', true);
    } else {
        $all.children().eq(0).css('display', 'none');
        $all.children().prop('checked', false);
    }
});
