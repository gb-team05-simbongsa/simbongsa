myActivity.forEach((activity, i) => {
    let text;

    text = `
        <div class="support-request-one-wrap">
            <div class="support-request">
                <div class="support-request-place">
                    <div class="support-request-title-wrap">
                        <div class="support-request-title">${activity.volunteerWorkDTO.volunteerWorkTitle}</div>
                    </div>
                    <div class="support-request-content">${activity.volunteerWorkActivityDate}</div>
                    <div class="support-request-reply">${activity.volunteerWorkDTO.volunteerWorkPlace}</div>
                </div>
                <div class="support-request-detail">
                    <button class="go-support-request">
                        <span class="request-detail">
                            <a href="/volunteer-work/work-detail/${activity.volunteerWorkDTO.id}">
                                <div class="detail-text">상세보기</div>
                            </a>
                        </span>
                    </button>
                </div>
            </div>
        </div>
    `
    $('.support-request-inner-wrap').append(text);
})
