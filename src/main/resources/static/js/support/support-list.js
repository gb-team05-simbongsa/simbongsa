// 데이터를 렌더링하는 함수
function renderSupportRequests(supportRequests) {
    var supportItems = '';

    supportRequests.forEach(function (request) {
        var serviceItem = `
            <div class="itemsWrap">
                <div class="service-items">
                    <div class="service-items-wrapper">
                        <div class="service-items-content">
                            <div class="name">
                                <span class="cruit">후원 금액</span>
                                [마감 <span class="last-date-text">3</span> 일전]
                            </div>
                            <div class="title">후원요청 게시물 제목 ${request.supportRequestTitle}</div>
                            <div class="dates">
                                <span class="dates-text">[후원등록날짜] 2023-04-19 ~ 2023-04-30 ${request.supportRequestContent}</span>
                                <span class="dates-text-work">[봉사기간] 2023-05-01 ~ 2023-05-30</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
        supportItems += serviceItem;
    });

    $('.itemsWrap').html(supportItems);
}

// 서포트 요청을 가져오기 위한 Ajax 요청
function getSupportRequests(page) {
    var keyword = null; // 여기에 원하는 키워드를 설정하세요

    $.ajax({
        type: "POST",
        url: "/api/support/requests",
        data: JSON.stringify({ keyword: keyword, page: page }),
        contentType: "application/json",
        success: function(response) {
            // 데이터를 성공적으로 받았을 때 처리할 로직을 여기에 작성하세요
            renderSupportRequests(response.content); // 받은 데이터로 렌더링 함수 호출

            var paging = `
                <div class="paging" style="text-align: center">
                    <a class="changePrev" style="color: black">
                        <code>&lt;</code>
                    </a>`;

            for (var i = 0; i < response.totalPages; i++) {
                if (i === response.currentPage) {
                    paging += `
                        <code class="currentPage">${i + 1}</code>`;
                } else {
                    paging += `
                        <a class="changePage" style="color: black">
                            <code>${i + 1}</code>
                        </a>`;
                }
            }

            paging += `
                    <a class="changeNext" style="color: black">
                        <code>&gt;</code>
                    </a>
                </div>
                <form name="pageForm">
                    <input type="hidden" name="keyword" value="${keyword}">
                    <input type="hidden" name="page" value="${response.currentPage + 1}">
                </form>
            `;

            // 페이징 요소를 삽입합니다
            $('.paging-container').html(paging);
        },
        error: function(xhr, status, error) {
            // 에러가 발생했을 때 처리할 로직을 여기에 작성하세요
        }
    });
}
// 페이지 버튼 클릭 이벤트 핸들러
$(document).on('click', '.changePage', function() {
    var page = parseInt($(this).text()); // 클릭한 페이지 번호를 가져옵니다.
    getSupportRequests(page); // 클릭한 페이지에 대한 데이터를 가져오는 함수 호출
});

// 서포트 요청을 가져오기 위한 Ajax 요청
// function getSupportRequests(page) {
//     var keyword = ""; // 여기에 원하는 키워드를 설정하세요
//
//     $.ajax({
//         type: "POST",
//         url: "/api/support/requests",
//         data: JSON.stringify({ keyword: keyword, page: page }),
//         contentType: "application/json",
//         success: function(response) {
//             // 데이터를 성공적으로 받았을 때 처리할 로직을 여기에 작성하세요
//             renderSupportRequests(response.content); // 받은 데이터로 렌더링 함수 호출
//
//             // 페이징 요소 생성
//             var paging = `
//                 <div class="paging" style="text-align: center">
//                     <a class="changePrev" style="color: black">
//                         <code>&lt;</code>
//                     </a>`;
//
//             for (var i = 0; i < response.totalPages; i++) {
//                 if (i === response.number) {
//                     paging += `
//                         <code class="currentPage">${i + 1}</code>`;
//                 } else {
//                     paging += `
//                         <a class="changePage" style="color: black">
//                             <code>${i + 1}</code>
//                         </a>`;
//                 }
//             }
//
//             paging += `
//                     <a class="changeNext" style="color: black">
//                         <code>&gt;</code>
//                     </a>
//                 </div>
//                 <form name="pageForm">
//                     <input type="hidden" name="keyword" value="${keyword}">
//                     <input type="hidden" name="page" value="${response.number + 1}">
//                 </form>
//             `;
//
//             // 페이징 요소를 삽입합니다
//             $('.paging-container').html(paging);
//         },
//         error: function(xhr, status, error) {
//             // 에러가 발생했을 때 처리할 로직을 여기에 작성하세요
//             console.log(error);
//         }
//     });
// }