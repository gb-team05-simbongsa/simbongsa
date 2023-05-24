$(".category-extend-select").mouseover( () => {
    $(".extended-category-section").attr("class", "extended-category-section-start");
    $(".category-extend-select").css("color", "rgba(254, 95, 76, 1)");
});

$(".category-extend-select").mouseout(() => {
    $(".extended-category-section-start").attr("class", "extended-category-section");
    $(".category-extend-select").css("color", "rgba(13, 13, 13, 1)");
});

$('.extended-categeory-header').click( () => {
    $(".extended-category-section-start").attr("class", "extended-category-section");
    $(".category-extend-select").css("color", "rgba(13, 13, 13, 1)");
});

function showMyPage(result) {
    var html = ``;
        html = `
        <div class="my-user-wrapper">
            <div class="user-menu-list">
                <a href="/mypage/support-request"><div class="menu-item">마이페이지</div></a>
                <a href="/mypage/rice-list"><div class="menu-item">내 공양미</div></a>
                <div class="menu-item-divider"></div>
                <a href="/mypage/volunteer-work-list"><div class="menu-item">봉사내역</div></a>
                <a href="/mypage/my-support-list/funding"><div class="menu-item">펀딩내역</div></a>
                <a href="/mypage/my-support-list"><div class="menu-item">후원내역</div></a>
                <div class="menu-item-divider"></div>
                <a href="/mypage/rice-charge"><div class="menu-item">공양미충전</div></a>
                <a href="/mypage/exchange-request"><div class="menu-item">공양미환전</div></a>
                <div class="menu-item-divider"></div>
                <a href="/mypage/my-funding-list"><div class="menu-item">내가 만든 펀딩</div></a>
                <a href="/mypage/user-modify"><div class="menu-item">회원정보수정</div></a>
                <div class="menu-item-divider"></div>
                <a href="/member/logout"><div class="menu-item">로그아웃</div></a>
            </div>
        </div>
        <div class="user-dimmed-layout"></div>
            `;
    $('#show').append(html);

    /* 한번 더 클릭하면 모두 없애기 */
    $(".user-dimmed-layout").on("click", function() {
        $(this).remove();
        $(".user-menu-list").remove();
        $(".my-user-wrapper").remove(); 
    });
}

$(".login-user-button-wrapper").on("click", showMyPage);