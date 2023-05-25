const $closeInquiry = $('.inquiry-close-btn-wrap');
const $mainInquiry = $('.main');
const $openInquiry = $('.footer-link-customer-channel-talk');

var Target = document.getElementById("clock");
var Target_apm = document.getElementById("apm");

function clock() {
    var time = new Date();
    var hours = time.getHours();
    var minutes = time.getMinutes();
    // var seconds = time.getSeconds();
    var AmPm ="AM";
    if(hours > 12){
        var AmPm ="PM";
        hours %= 12;
    }

    Target.innerText =
        `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}`;

    Target_apm.innerText = `${AmPm}`;

}
clock();
setInterval(clock, 1000); // 1초마다 실행


$(document).ready(function(){
    
    // 버튼 클릭시 모달창 띄우기 
    $openInquiry.click(function(){
        $mainInquiry.show();
    });
    
    
    
    // x버튼 클릭시 모달창 닫기
    $closeInquiry.click(function(){
      $mainInquiry.hide();
    });

});

$('.inquiry-regist-btn').on('click', function() {
    document.questionForm.submit();
});