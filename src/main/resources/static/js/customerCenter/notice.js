notices.forEach(notice => {
    let text;

    text = `
        <li class="list-li">
            <a href="/inquiry/notice-detail/${notice.id}">
                <span class="title-wrap">
                    <span class="group">공지사항</span>
                    <span class="title">${notice.noticeTitle}</span>
                    <span class="list-date">` + adminService.dateFormat(`${notice.createdDate}`) + `</span>
                </span>
            </a>
        </li>
    `;

    $('.list-ul').append(text);
});

$('.search-btn-wrapper').on('click', () => {
    location.href = "/inquiry/notice?&searchContent=" + $('.search-text').val();
});