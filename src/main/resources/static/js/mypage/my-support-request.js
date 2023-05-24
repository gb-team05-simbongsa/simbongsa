mySupportRequests.forEach((supportRequest, i) => {
    let text;

    text = `
        <div class="support-request-one-wrap">
                        <div class="support-request">
                            <div class="support-request-place">
                                <div class="support-request-title-wrap">
                                    <div class="support-request-title">${supportRequest.supportRequestTitle}</div>
                                </div>
                                <div class="support-request-content">${supportRequest.supportRequestContent}</div>
                                <div class="support-request-reply">${supportRequest.supportRequestStatus}</div>
                            </div>
                            <div class="support-request-detail">
                                <a href="/support/support-detail/${supportRequest.id}" class="go-support-request">
                                    <span class="request-detail">
                                        <div class="detail-text">상세보기</div>
                                    </span>
                                </a>
                            </div>
                        </div>
                    </div>
                    
    `;

    $('.support-request-inner-wrap').prepend(text);
});

$('.go-support-request').click( function () {

})