const $container = $(".banner-swiper-container");
const $banner = $(".banner-swiper-wrapper");
const $imageDiv = $(".swiper-slider");
const firstImageDiv = '<div class="swiper-slider"></div>';
const lastImageDiv = '<div class="swiper-slider"></div>';
const $next = $(".next");
const $prev = $(".prev");
const $bannerCount = $(".banner-count strong");
let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 2000);
/* 모바일 검사 */
let mobileCheck = screen.width < 1024;

let imgUrl = [
    'url(../../static/image/main/001.jpg)',
    'url(../../static/image/main/002.jpg)',
    'url(../../static/image/main/003.jpg)',
    'url(../../static/image/main/004.jpg)'
];

const mobileImgURLs = [
    'url(../../static/image/main/005.jpg)',
    'url(../../static/image/main/006.jpg)',
    'url(../../static/image/main/007.jpg)',
    'url(../../static/image/main/008.jpg)'
];

/* PC환경이면 1920px, 모바일이면 화면 크기에 맞춰서 */
console.log("screen.width" + screen.width);
let width = mobileCheck ? screen.width : 1920;

/* 모바일이면 모바일 배너로 바꾸기 */
imgUrls = mobileCheck ? mobileImgURLs : imgUrl;


$imageDiv.each(function(index) {
    $(this).css({
        'background-image': imgUrls[index]
    });
});

/* 위치 중요 추가한 후 css 바꿔주기 */
/* 마지막에는 첫번째 사진 첫번째에는 마지막사진 */
$banner.append(lastImageDiv);
$(".swiper-slider").last().css('background-image', imgUrls[0]);

$banner.prepend(firstImageDiv);
$(".swiper-slider").first().css('background-image', imgUrls[3]);

const $imageDiv2 = $(".swiper-slider");

$imageDiv2.each(function(index) {
    if(mobileCheck) {
        $(this).css({
            'width': '539px',
            'height': '280px',
            'background-size': 'cover'
        });
    }
    else{
        $(this).css({
            'width': '766px',
            'height': '280px',
            'background-size': 'cover'
        });
    }
});

/* 처음 배너 사진 오타 조심*/
if(mobileCheck) {
    $banner.css("transform", "translate(-539px)");
}
else{
    $banner.css("transform", "translate(-766px)");
}
updateBannerCount();

/* ++count 여야함*/
/* count++이면 translatae가 변경 전의 값이 사용됨 */
function autoSlide(){
    if(mobileCheck){
        $banner.css("transition", "transform 0.3s");
        $banner.css("transform", `translate(${-539 * ++count}px)`);
        if(count == 5) {
            count = 1;
            updateBannerCount();
            setTimeout(function(){
            $banner.css("transition", "transform 0s");
            $banner.css("transform", "translate(-539px)");
            }, 300);
        }
    }
    else {
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
    }
    updateBannerCount();
};

function updateBannerCount() {
    $bannerCount.html(`${count} / ${$imageDiv.length}`);
};

/* if count == 0이면 마지막에서 2번째 div로 가게 해주기 */
$prev.on("click", function(){
    if(checkArrow){
        return;
    }
    checkArrow = true;
    clearInterval(auto);
    $banner.css("transition", "transform 0.3s");
    $banner.css("transform", `translate(${-766 * --count}px)`);

    if(count == 0){
        count = 4;
        updateBannerCount();
        setTimeout(function(){
            $banner.css("transition", "transform 0s");
            $banner.css("transform", `translate(${-766 * 4}px)`);
        }, 300);
    }
    updateBannerCount();
    auto = setInterval(autoSlide, 2000);
    setTimeout( () => {
        checkArrow = false
    }, 300);
});

$next.on("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    $banner.css("transition", "transform 0.3s");
    $banner.css("transform", `translate(${-766 * ++count}px)`);
    if(count == 5){
        count = 1;
        updateBannerCount();
        setTimeout(function(){
            $banner.css("transition", "transform 0s");
            $banner.css("transform", "translate(-766px)");
        }, 300);
    }
    updateBannerCount();
    auto = setInterval(autoSlide, 2000);
    setTimeout( () => {
        checkArrow = false
    }, 300);
});
