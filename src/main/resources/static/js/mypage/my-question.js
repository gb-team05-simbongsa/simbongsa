const $modal = $('.change-modal');

// 상세보기 버튼을 클릭했을 때 수정 모달 띄우기
$('.go-support-request').on('click', function() {
    $('.full-screen').on('scroll touchmove mousewheel', function(e) {
        e.preventDefault();
        e.stopPropagation();
    });

    $modal.show();
});

// 수정 모달이 띄워졌을 때 X버튼 누르면 모달 닫기
$('.modal-close').on('click', () => {
    $modal.hide();

    $('.full-screen').off('scroll touchmove mousewheel');
});

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
                <div class="support-request-reply">공양미 100석 후원</div>
            </div>
        </div>`;

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
/*    if(result.paginationDTO.pageDTO.prev) {
        paging += `
                        <a class="changePage" data-page="${result.paginationDTO.pageDTO.startPage - 1}" onclick="preventDefault(this)" style="color: black"><code><</code></a>
                        `;
    }*/