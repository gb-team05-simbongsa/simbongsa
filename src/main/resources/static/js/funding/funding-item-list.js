const noticeUlContainer = $('.notice-list-content-ul');
console.log(noticeUlContainer);

const createDOM = function(notices){
    console.log("들어왓어?");
    let text = `
             <li>
                  <a href="/notice/detail/${notices.noticeId}">
                        <div class="notice-list-table-content-div">
                            <div class="notice-list-table-content-number">${notices.noticeId}</div>
                            <div class="notice-list-table-content-title">${notices.noticeTitle}</div>
                            <div class="notice-list-table-content-writer">${notices.noticeWriter}</div>
                            <div class="notice-list-table-content-date">${notices.noticeRegist}</div>
                        </div>
                  </a>
             </li>

`
    return text;
}
console.log("들어왓다");
console.log(notices);

notices.forEach((notices, i) => {
    noticeUlContainer.append(createDOM(notices));
});