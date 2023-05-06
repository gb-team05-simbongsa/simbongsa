const $container = $(".banner-swiper-container");
const $banner = $(".banner-swiper-wrapper");
const firstImageDiv = '<div class="swiper-slider"></div>';
const lastImageDiv = '<div class="swiper-slider"></div>';
const $imageDiv = $(".swiper-slider");
const $next = $(".next");
const $prev = $(".prev");
const $bannerCount = $(".banner-count strong");
let checkArrow = false;
let count = 1;

const autoSlideTimer = 2000;
const autoSlideActionTimer = "transform 0.3s";
const arrowLockTimer = 300;
let auto = setInterval(autoSlide, autoSlideTimer);
/* 모바일 검사 */
let mobileCheck = screen.width < 1024;

let imgURLs = [
    'url(/image/main/001.jpg)',
    'url(/image/main/002.jpg)',
    'url(/image/main/003.jpg)',
    'url(/image/main/004.jpg)'
];

const mobileImgURLs = [
    'url(/image/main/005.jpg)',
    'url(/image/main/006.jpg)',
    'url(/image/main/007.jpg)',
    'url(/image/main/008.jpg)'
];

/* 배너 수 */
let bannersLength = imgURLs.length;

/* PC환경이면 766px, 모바일이면 화면 크기에 맞춰서 */
console.log("screen.width" + screen.width);
let width = mobileCheck ? screen.width : 766;
let height = mobileCheck ? 228.88 : 280;

/* 모바일이면 모바일 배너로 바꾸기 */
imgURLs = mobileCheck ? mobileImgURLs : imgURLs;

/* container와 배너를 감싸고 있는 div 크기 조절 */
$container.css('width', width+'px');
$banner.css('width', width * (bannersLength + 2) + 'px');


$imageDiv.each((index, e) => {
    $(e).css('background-image', imgURLs[index]);
} )
/* ------------- */

/* 위치 중요 추가한 후 css 바꿔주기 */
/* 마지막에는 첫번째 사진 첫번째에는 마지막사진 */
$banner.append(lastImageDiv);
$(".swiper-slider").last().css('background-image', imgURLs[0]);

$banner.prepend(firstImageDiv);
$(".swiper-slider").first().css('background-image', imgURLs[imgURLs.length - 1]);

$('.swiper-slider').each((index, element) => {
    $(element).css({
        'width': width + 'px',
        'height': height + 'px',
        'background-size': 'inital'
    });
});

/* 처음 배너 사진 오타 조심*/
$banner.css("transform", `translate(${-width}px)`);
updateBannerCount();

/* ++count 여야함*/
/* count++이면 translatae가 변경 전의 값이 사용됨 */
function autoSlide(){
    $banner.css("transition", `${autoSlideActionTimer}`);
    $banner.css("transform", `translate(${-width * ++count}px)`);
    if(count == bannersLength + 1) {
        count = 1;
        updateBannerCount();
        setTimeout(function(){
        $banner.css("transition", "transform 0s");
        $banner.css("transform", `translate(${-width}px)`);
        }, arrowLockTimer);
    }
    updateBannerCount();
};

if (!mobileCheck) {
    $prev.on('click', function() {
        if (checkArrow) {
            return;
        }
        checkArrow = true;
        clearInterval(auto);
        $banner.css('transition', `${autoSlideActionTimer}`);
        $banner.css("transform", `translate(-${width * --count}px)`);

        if(count == 0){
            count = 4;
            updateBannerCount();
            setTimeout(function(){
                $banner.css("transition", "transform 0s");
                $banner.css("transform", `translate(${-width * $imageDiv.length}px)`);
            }, arrowLockTimer);
        }
        updateBannerCount();
        auto = setInterval(autoSlide, autoSlideTimer);
        setTimeout( () => {
            checkArrow = false;
        }, arrowLockTimer);
    });
}

if (!mobileCheck) {
    $prev.on('click', function() {
        if (checkArrow) {
            return;
        }
        checkArrow = true;
        clearInterval(auto);
        $banner.css('transition', `${autoSlideActionTimer}`);
        $banner.css("transform", `translate(-${width * ++count}px)`);

        if(count == bannersLength + 1){
            count = 1;
            updateBannerCount();
            setTimeout(function(){
                $banner.css("transition", "transform 0s");
                $banner.css("transform", `translate(-${width}px)`);
            }, arrowLockTimer);
        }
        updateBannerCount();
        auto = setInterval(autoSlide, autoSlideTimer);
        setTimeout( () => {
            checkArrow = false;
        }, arrowLockTimer);
    });
}


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
    $banner.css("transform", `translate(-${width * --count}px)`);

    if(count == 0){
        count = 4;
        updateBannerCount();
        setTimeout(function(){
            $banner.css("transition", "transform 0s");
            $banner.css("transform", `translate(${-width * $imageDiv.length}px)`);
        }, arrowLockTimer);
    }
    updateBannerCount();
    auto = setInterval(autoSlide, autoSlideTimer);
    setTimeout( () => {
        checkArrow = false
    }, arrowLockTimer);
});

$next.on("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    $banner.css("transition", "transform 0.3s");
    $banner.css("transform", `translate(-${width * ++count}px)`);
    if(count == 5){
        count = 1;
        updateBannerCount();
        setTimeout(function(){
            $banner.css("transition", "transform 0s");
            $banner.css("transform", `translate(-${width}px)`);
        }, arrowLockTimer);
    }
    updateBannerCount();
    auto = setInterval(autoSlide, autoSlideTimer);
    setTimeout( () => {
        checkArrow = false
    }, arrowLockTimer);
});
