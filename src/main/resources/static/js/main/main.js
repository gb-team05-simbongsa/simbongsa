// 메뉴바 다음, 이전버튼
const $prev1 = $('.main-content-slider-nav-button-left');
const $next1 = $('.main-content-slider-nav-button-right');
let countcount = 0;
let countcount1 = 0;
let nowPage = 1;
let nowPage1 = 1;

$prev1.css('display', 'none');

if (!mobileCheck) {
    $prev1.click(function(){
        console.log("prev 클릭됨");
        $(this).parent().find('.swiper-wrapper').css('transition', 'transform 0.3s');
        $(this).parent().find('.swiper-wrapper').css('transform', `translate(${-1160 * (this == $prev1[0] ? --countcount1 : --countcount)}px)`);
        /* $('.swiper-wrapper').css('transition', 'transform 0.3s');
        $('.swiper-wrapper').css('transform', `translate(${-1160 * --countcount}px)`); */
        if((this == $prev1[0] ? --nowPage1 : --nowPage) == 1) {
            $(this).css('display', 'none');
            $(this).next().css('display', 'inline-flex');
        }
    });

    $next1.click(function(){
        console.log("next 클릭됨");
        $(this).parent().find('.swiper-wrapper').css('transition', 'transform 0.3s');
        $(this).parent().find('.swiper-wrapper').css('transform', `translate(${-1160 * (this == $next1[0] ? ++countcount1 : ++countcount)}px)`);
        if((this == $next1[0] ? ++nowPage1 : ++nowPage) == 2) {
            $(this).css('display', 'none');
            $(this).prev().css('display', 'inline-flex');
        }
    });
}


if (mobileCheck) {
    $prev1.click(function(){
        console.log("prev 클릭됨");
        $(this).parent().find('.swiper-wrapper').css('transition', 'transform 0.3s');
        $(this).parent().find('.swiper-wrapper').css('transform', `translate(${-390 * (this == $prev1[0] ? --countcount1 : --countcount)}px)`);
        if((this == $prev1[0] ? --nowPage1 : --nowPage) == 1) {
            $(this).css('display', 'none');
            $(this).next().css('display', 'inline-flex');
        } else {
            $(this).next().css('display', 'inline-flex');
        }
    });

    $next1.click(function(){
        console.log("next 클릭됨");
        $(this).parent().find('.swiper-wrapper').css('transition', 'transform 0.3s');
        $(this).parent().find('.swiper-wrapper').css('transform', `translate(${-390 * (this == $next1[0] ? ++countcount1 : ++countcount)}px)`);
        if((this == $next1[0] ? ++nowPage1 : ++nowPage) >= 4) {
            $(this).css('display', 'none');
            $(this).prev().css('display', 'inline-flex');
        } else {
            $(this).prev().css('display', 'inline-flex');
        }
    });


}
