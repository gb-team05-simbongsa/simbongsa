fundings.forEach(funding => {
    let text;
console.log("왔나")
    text = `
        <div class="support-request-one-wrap">
            <div class="support-request">
                <div class="support-request-place">
                    <div class="support-request-title-wrap">
                        <div class="support-request-title">${funding.fundingDTO.fundingTitle}</div>
                    </div>
                    <div class="support-request-content">현재 금액 : ${funding.fundingDTO.fundingCurrentPrice}</div>
                    <div class="support-request-reply">${funding.fundingPaymentPrice}</div>
                    <div class="support-request-reply">펀딩</div>
                </div>
                <div class="support-request-detail">
                    <button class="go-support-request">
                        <span class="request-detail">
                            <div class="detail-text">상세보기</div>
                        </span>
                    </button>
                </div>
            </div>
        </div>
    `;

    $('.support-request-inner-wrap').append(text);
});