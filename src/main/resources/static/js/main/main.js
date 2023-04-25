const $prev1 = $('.main-content-slider-nav-button-left');
const $next1 = $('.main-content-slider-nav-button-right');
let countcount = 0;
let nowPage = 1;

$prev1.css('display', 'none');

$prev1.click(function(){
    console.log("prev 클릭됨");
    $('.swiper-wrapper').css('transition', 'transform 0.3s');
    $('.swiper-wrapper').css('transform', `translate(${-1160 * --countcount}px)`);
    nowPage--;
    if(nowPage == 1) {
        $(this).css('display', 'none');
        $(this).next().css('display', 'inline-flex');
    }
});

$next1.click(function(){
    console.log("next 클릭됨");
    $('.swiper-wrapper').css('transition', 'transform 0.3s');
    $('.swiper-wrapper').css('transform', `translate(${-1160 *  ++countcount}px)`);
    nowPage++;
    if(nowPage == 2) {
        $(this).css('display', 'none');
        $(this).prev().css('display', 'inline-flex');
    }
});