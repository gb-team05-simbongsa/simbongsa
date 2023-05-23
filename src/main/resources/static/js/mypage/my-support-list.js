supports.forEach(support => {
    let text;

    text = `
        <div class="support-request-one-wrap">
            <div class="support-request">
                <div class="support-request-place">
                    <div class="support-request-title-wrap">
                        <div class="support-request-title">${support.supportRequestDTO.supportRequestTitle}</div>
                    </div>
                    <div class="support-request-content">현재 모금된 공양미: ${support.supportRequestDTO.totalSupportPrice}석</div>
                    <div class="support-request-reply">후원한 공양미: ${support.supportPrice}석</div>
                    <div class="support-request-reply">후원</div>
                </div>
                <div class="support-request-detail">
                    <button class="go-support-request">
                        <span class="request-detail">
                        <a href="/support/support-detail/${support.supportRequestDTO.id}">
                            <div class="detail-text">상세보기</div>
                        </a>
                        </span>
                    </button>
                </div>
            </div>
        </div>
    `;

    $('.support-request-inner-wrap').append(text);
});