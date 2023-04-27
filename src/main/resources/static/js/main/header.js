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
                <div class="menu-item">프로필</div>
                <div class="menu-item">응원권</div>
                <div class="menu-item-divider"></div>
                <div class="menu-item">후원현황</div>
                <div class="menu-item">관심 프로젝트</div>
                <div class="menu-item">팔로우</div>
                <div class="menu-item-divider"></div>
                <div class="menu-item">알림</div>
                <div class="menu-item">메시지</div>
                <div class="menu-item-divider"></div>
                <div class="menu-item">내가 만든 프로젝트</div>
                <div class="menu-item">설정</div>
                <div class="menu-item-divider"></div>
                <div class="menu-item">로그아웃</div>
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