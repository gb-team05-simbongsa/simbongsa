// 내 문의 목록
myInquiries.forEach((inquiry, i) => {
    let text;

    text = `
        <div>
            <div class="support-request-one-wrap">
                <div class="support-request">
                    <div class="support-request-place">
                        <div class="support-request-title-wrap">
                            <div class="support-request-title">${inquiry.inquiryTitle}</div>
                        </div>
                        <div class="support-request-content">${inquiry.inquiryContent}</div>
                        <div class="support-request-reply">${inquiry.inquiryStatus}</div>
                    </div>
                `;
    if(inquiry.answerDTO != null){
        text += `
        <div class="button-class">
                        <div class="support-request-detail">
                            <button class="show-answer">
                                <span class="request-detail">
                                    <div class="detail-text">답변확인</div>
                                </span>
                            </button>
                        </div>
                    </div>
                    
                </div>
            </div>
            <div class="show-answer-wrap">
                <div class="support-request-title-wrap">
                <div class="support-request-title">${inquiry.answerDTO.answerTitle}</div>
                        </div>
                        <div class="support-request-content">${inquiry.answerDTO.answerContent}</div>
                    </div>
                </div>`;

    }
    else {
        text +=`
                    <div class="button-class">
                        <div class="support-request-detail update">
                            <button class="go-support-request">
                                <input class="inquiryId" type="hidden" value="${inquiry.id}"> 
                                <span class="request-detail">
                                    <div class="detail-text">수정</div>
                                </span>
                            </button>
                        </div>
                        <div class="support-request-detail">
                            <button class="go-support-request-delete">
                                <span class="request-detail">
                                    <div class="detail-text">삭제</div>
                                </span>
                            </button>
                        </div>
                    </div>`;
    }


    $('.support-request-inner-wrap').prepend(text);

});


// 답변확인 버튼 눌렀을 때 답변이 있을 경우
$('.show-answer').on('click', function() {
    if($(this).parent().parent().parent().parent().next().css('display') == 'none') {
        $(this).parent().parent().parent().parent().next().show();
    } else {
        $(this).parent().parent().parent().parent().next().hide();
    }
});

const $modal = $('.change-modal');

// 상세보기 버튼을 클릭했을 때 수정 모달 띄우기
$('.go-support-request').on('click', function() {
    $('.full-screen').on('scroll touchmove mousewheel', function(e) {
        e.preventDefault();
        e.stopPropagation();
    });

    /* 해당 문의 번호 */
    var inquiryId = $(this).find('.inquiryId').val();
    console.log("inquiryId: "+inquiryId);

    /* 상세보기 ajax */
    $.ajax({
        url: "/mypages/inquiry-details",
        type: "post",
        data: { id : inquiryId },
        success: function(result) {
            $('input[name=id]').val(inquiryId);
            $('.change-modal-form-title-input').val(result.inquiryTitle);
            $('.change-modal-form-field-text').val(result.inquiryContent);
        }
    });

    $modal.show();
});

/* 삭제 확인 버튼 눌렀을 때 */
$('.modal-ok').on('click',function () {
    var inquiryId = $('input[name=myInquiryId]').val();
    console.log("inquiryId: "+inquiryId);

    $a.ajax({
        url: "/mypages/inquiry-delete",
        type: "post",
        data: {id: "inquiryId"},
        success: function() {
            location.reload();
        }
    })
})


// 수정 모달이 띄워졌을 때 X버튼 누르면 모달 닫기
$('.modal-close').on('click', () => {
    $modal.hide();

    $('.full-screen').off('scroll touchmove mousewheel');
});

// 수정 모달이 띄워졌을 때 수정버튼 누르면 수정 하기
$('.change-modal-ok-btn').on('click', () => {
    document.updateForm.submit();
})

// 수정 모달이 띄워졌을 때 취소버튼 누르면 모달 닫기
$('.change-modal-delete-btn').on('click', () => {
    $modal.hide();

    $('.full-screen').off('scroll touchmove mousewheel');
});

// 모달에서 삭제 버튼을 눌렀을 때 진짜 삭제할건지 모달 띄우기
$('.go-support-request-delete').on('click', () => {
    $('.modal-wrap').show();
});

// 삭제할건지 묻는 모달에서 취소 버튼 눌렀을 때 모달 닫기
$('.modal-cancel').on('click', () => {
    $('.modal-wrap').hide();
    $modal.hide();
});