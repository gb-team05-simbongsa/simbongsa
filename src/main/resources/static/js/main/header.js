$(".test").mouseover( () => {
    $(".extended-category-section").attr("class", "extended-category-section-start");
});

$(".test").mouseout(() => {
        $(".extended-category-section-start").attr("class", "extended-category-section");
});

/* function showMyPage(result) {
    var html = ``;
        html = `
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
                    <div class="user-dimmed-layout"></div>
            `;
    $('#show').append(html);
}

$(".login-user-button-wrapper").on("click", showMyPage()); */