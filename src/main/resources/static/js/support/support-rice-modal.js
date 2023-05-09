const $open = $('.donate');
    const $layout = $('.support-layout');
    const $submit = $('.support-pay-layout');
    const $close = $('.support-rice-close-btn-layout');
    const $input = $('rice-payment-input');
    const $errorMessage = $('.error-message');
    const numberRegex = /^[0-9]+$/;

    $(document).ready(function(){
    
    // 버튼 클릭시 모달창 띄우기 
    $open.click(function(){
        $layout.show();
    });
    
    // x버튼 클릭시 모달창 닫기
    $close.click(function(){
        $layout.hide();
    })
    
    // 전송
    // $submit.click(function(){
    //     document.$submit();
    // });

  });
const $resultModal = $('.support-layout-error');
const $modalClose = $('.support-rice-close-btn-layout-error');
const $closeButton = $('.support-pay-layout-error');
    $submit.on('click',function(e){
        $resultModal.show();
        $layout.hide()
    })
    $modalClose.click(function (){
        $resultModal.hide();
        $layout.show()
    })
    $closeButton.click(function (){
        $resultModal.hide();
        $layout.show()
    })






// const $input = $('rice-payment-input');
// const $errorMessage = $('.error-message');
// const numberRegex = /^[0-9]+$/;

// $('.change-modal-ok-btn').on('click', () => {
//     // input값이 비어있으면 에러 메시지 출력
//     $input.each((i, e) => {
//         if(!$(e).val()) {
//             $errorMessage.eq(i).text('비워둘 수 없습니다.');
//             $errorMessage.eq(i).show();
//         } else {
//             $errorMessage.eq(i).hide();
//         }
//     });

  $('.btn-rice').on('click', () => {
      // input값이 비어있으면 에러 메시지 출력
      $input.each((i, e) => {
          if(!$(e).val()) {
            console.log(i);
            console.log(e);
              $errorMessage.eq(i).text('비워둘 수 없습니다.');
              $errorMessage.eq(i).show();
          } else {
              $errorMessage.eq(i).hide();
          }
      });

    // 계좌번호와 환전할 수 있는 공양미는 숫자만 입력받게 정규식 테스트
        for(let i = 1; i < $errorMessage.length; i++) {
            if(!numberRegex.test($input.eq(i).val()) && $input.eq(i).val()) {
                $errorMessage.eq(i).text('숫자만 입력하세요.');
                $errorMessage.eq(i).show();
            }
        }

    });

//     // 에러 메시지가 떠있는게 있으면 빠져나가기
//     for(let i = 0; i < $errorMessage.length; i++) {
//         if($errorMessage.eq(i).css('display') == 'block') {
//             return;
//         }
//     }

//     // 다 통과했다면 submit
//     document.ricePayed.submit();
// });

// // 환전할 수 있는 공양미 보다 큰 값을 입력했을 경우 환전할 수 있는 최대 공양미 수로 변경
// $input.eq(2).on('blur', function() {
//     if(parseInt($(this).val()) > parseInt($('rice-payment-input').text())) {
//         $(this).val($('rice-payment-input').text(300));
//     }
// });