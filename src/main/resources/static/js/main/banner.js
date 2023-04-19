/* 배너 */
const $container = $(".banner-swiper-container");
const $banner = $(".banner-swiper-wrapper");
const $imageDiv = $(".swiper-slider");
const firstImageDiv = '<div class="swiper-slider"></div>';
const lastImageDiv = '<div class="swiper-slider"></div>';
/* const lastImageDiv = document.createElement("div"); //append
const firstImageDiv = document.createElement("div"); */ //prepend
const $next = $(".next");
const $prev = $(".prev");
const $bannerCount = $(".banner-count strong");
let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 2000);

let imgUrl = [
    'url(../../static/image/main/001.jpg)',
    'url(../../static/image/main/002.jpg)',
    'url(../../static/image/main/003.jpg)',
    'url(../../static/image/main/004.jpg)'
];

$imageDiv.each(function(index) {
    $(this).css({
        'background-image': imgUrl[index],
        'width': '766px',
        'height': '280px',
        'background-size': 'cover'
    });
});

$banner.append(lastImageDiv);
$(".swiper-slider").last().css('background-image', imgUrl[0]);

$banner.prepend(firstImageDiv);
$(".swiper-slider").first().css('background-image', imgUrl[3]);

/* $banner.css("transition", "translate(-766px)"); */
updateBannerCount();

function autoSlide(){
    $banner.css("transition", "transform 0.3s");
    $banner.css("transform", `translate(${-766 * ++count}px)`);
    if(count == 5) {
        count = 1;
        updateBannerCount();
        setTimeout(function(){
        $banner.css("transition", "transform 0s");
        $banner.css("transform", "translate(-766px)");
        }, 300);
    }
    updateBannerCount();
};

function updateBannerCount() {
    $bannerCount.html(`${count} / ${$imageDiv.length}`);
};