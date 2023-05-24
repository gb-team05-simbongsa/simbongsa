const $open = $('.donate');
const $layout = $('.support-layout');
    // const $submit = $('.support-pay-layout');
    // const $close = $('.support-rice-close-btn-layout');
    // const $input = $('rice-payment-input');


// 버튼 클릭시 모달창 띄우기
$open.click(function(){
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
                    <p>후원 가능한 공양미: <em>${memberDTO.memberRice}</em>석</p>
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

    // 전송
    // $submit.click(function(){
    //     document.$submit();
    // });

})

// $open.click(function() {
//     $('.support-layout').html(
//         `
//             <div class="support-rice-layout">
//                 <form id="supportForm">
//                     <div class="suppport-rice-container">
//                         <div class="support-rice-close-btn-layout">
//                             <button type="button" onclick="modalClose()">X</button>
//                         </div>
//                         <div class="">
//                             <input type="text" name="supportAmount" placeholder="공양미 입력"  class="rice-payment-input riceInput" value="">
//                             <p class="error" style="font-size: 13px;"></p>
//                             <p>후원 가능한 공양미: <em>${memberDTO.memberRice}</em>석</p>
//                         </div>
//                     </div>
//                     <div class="support-pay-layout">
//                         <button type="button" onclick="confirmSupport()" class="btn-rice">
//                             <input type="hidden" name="supportRequestId" value="${supportRequestDTO.supportRequestId}">
//                             <input type="hidden" name="memberId" value="${memberDTO.memberId}">
//                             <span>확인</span>
//                         </button>
//                     </div>
//                 </form>
//             </div>
//         `
//     )
//     $layout.show();
// });
//
// function confirmSupport() {
//     var supportAmount = $('input[name="supportAmount"]').val();
//     var supportRequestId = $('input[name="supportRequestId"]').val();
//     var memberId = $('input[name="memberId"]').val();
//
//     $.ajax({
//         type: "GET",
//         url: "/update-gongyangmi",
//         data: {
//             supportAmount: supportAmount,
//             supportRequestId: supportRequestId,
//             memberId: memberId
//         },
//         success: function(response) {
//             location.reload();
//         },
//         error: function(error) {
//         }
//     });
// }



function modalClose(){
    console.log("들어옴");
    $layout.hide();
}

const $inputs = $('.rice-payment-input');
const $errorMessage = $('.error-message');
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
    console.log("들어옴?");
    // 다 통과했다면 submit
    document.goForm.submit();
};

// 환전할 수 있는 공양미 보다 큰 값을 입력했을 경우 환전할 수 있는 최대 공양미 수로 변경
$('.riceInput').on('blur', function() {
    if (parseInt($(this).val()) > parseInt($('.rice-payment-input').text())) {
        $(this).val($('.rice-payment-input').text());
    }

})


// x버튼 클릭시 모달창 닫기
//
// const $resultModal = $('.support-layout-error');
// const $modalClose = $('.support-rice-close-btn-layout-error');
// const $closeButton = $('.support-pay-layout-error');
//     $submit.on('click',function(e){
//         $resultModal.show();
//         $layout.hide()
//     })
//     $modalClose.click(function (){
//         $resultModal.hide();
//         $layout.show()
//     })
//     $closeButton.click(function (){
//         $resultModal.hide();
//         $layout.show()
//     })





