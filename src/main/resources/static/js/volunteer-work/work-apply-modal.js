const $open = $('.apply-btn');
const $layout = $('.apply-layout');
const $close = $('.apply-close');

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