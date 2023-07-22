$(".rice-charge[type='button']").click(function() {
    location.href = "/mypage/rice-charge";
});

/*헤더 문자열 비교 리스트*/
const $menu_list = [0 ,1, 2, 3, 4, 5, 6, 7, 8];

// 헤더의 항목 클릭했을때 해당 항목 색깔 바꾸기
$menu_list.forEach((value, index) => {
    if(index == menuNumber) {
        console.log("index: " + index);
        console.log("menuNumber: " + menuNumber);
        $('.header-menu-inner a').eq(index).attr('class', 'header-menu-a-select');
    }
    else{
        $('.place-ul a').eq(index).attr('class', 'header-menu-a');
    }
});

