const $close = $('.inquiry-close-btn-wrap');
const $main = $('.main');
const $open = $('#open');


$(document).ready(function(){
    
    // 버튼 클릭시 모달창 띄우기 
    $open.click(function(){
        $main.show();
    });
    
    
    
    // x버튼 클릭시 모달창 닫기
    $close.click(function(){
      $main.hide();
    });

  });

  