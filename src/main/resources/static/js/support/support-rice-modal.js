const $open = $('.donate');
const $layout = $('.support-layout');
const $errorLayout = $('.support-layout-error');

// 버튼 클릭시 모달창 띄우기
$open.click(function(){
    if (memberDTO == null || memberDTO.memberRice == null){
        $('.support-layout-error').html(
            `
        <div class="support-rice-layout">
                <!-- <form name=""> -->
                    <div class="suppport-rice-container-error">
                        <div class="support-rice-close-btn-layout-error" style="visibility: hidden">
                            <button type="button" onclick="close()">X</button>
                        </div>
                            <div class="">
                                <p>로그인 후 <em>이용</em>가능합니다.</p>
                            </div>

                        </div>
                        <div class="support-pay-layout-error">
                            <button type="button" onclick="confirmClose()" class="btn-rice">
                                <span>확인</span>
                            </button>
                        </div>
                    <!-- </form> -->
            </div>
        `);
        $errorLayout.css('visibility', 'visible');
    }else {
        $('.support-layout').html(
            `
            <div class="support-rice-layout">
             <form name="goForm" method="post" action="/support/support">
            <div class="suppport-rice-container">
                <div class="support-rice-close-btn-layout">
                    <button type="button" onclick="modalClose()">X</button>
                </div>
                <div class="">
                    <input type="text" name="supportAmount" placeholder="공양미 입력"  class="rice-payment-input riceInput" value="">
                     <p class="error" style="font-size: 13px;"></p>
                    <p style="margin: 8px -16px 2px 3px;">후원 가능한 공양미: <em>${memberDTO.memberRice}</em>석</p>
                </div>
            </div>
            <div class="support-pay-layout">
                <button type="button" onclick="confirm()" class="btn-rice">
                <input type="hidden" name="supportRequestId" value="${supportRequestDTO.id}">
                    <span>확인</span>
                </button>
            </div>
             </form>
        </div>
        `
        )
        $layout.show();
    }

})

function confirmClose() {
    $errorLayout.css('visibility', 'hidden');
}
function modalClose(){
    $layout.hide();
}

const numberRegex = /^[0-9]+$/;

function confirm(){

    // input값이 비어있으면 에러 메시지 출력
    if(!$('.riceInput').val()){
        $('.error').text('비워둘 수 없습니다.');
    }else{
        $('.error').hide();
    }

    if(!numberRegex.test($('.riceInput').val()) && $('.riceInput').val()) {
        $('.error').text('숫자만 입력하세요.');
        $('.error').show();
    }

    if($('.error').css('display') == 'block') {
        return;
    }
    // 다 통과했다면 submit
    document.goForm.submit();
};

// 환전할 수 있는 공양미 보다 큰 값을 입력했을 경우 환전할 수 있는 최대 공양미 수로 변경
$('.riceInput').on('blur', function() {
    if (parseInt($(this).val()) > parseInt($('.rice-payment-input').text())) {
        $(this).val($('.rice-payment-input').text());
    }
})





