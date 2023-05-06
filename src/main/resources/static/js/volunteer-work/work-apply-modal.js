const $open = $('.apply-btn');
const $layout = $('.apply-layout');
// const $layout = $('.all-layout');
const $close = $('.apply-close');
// const $open = $('#work-open');

$(document).ready(function(){

// 버튼 클릭시 모달창 띄우기
    $open.click(function(){
        $('body').css('background-color','#dfdbdb');
        $layout.show();
    });

// x버튼 클릭시 모달창 닫기
    $close.click(function(){
        $('body').css('background-color','white');
        $layout.hide();
    })
// 전송 
// $submit.click(function(){
//     document.$submit();
// });

});