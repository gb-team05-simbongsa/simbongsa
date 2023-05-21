supportRequests.forEach(supportRequest => {
    let text;

    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                    <input type="checkbox" name="check"/>
                </label>
            </td>
            <td class="content__id">${supportRequest.id}</td>
            <td>${supportRequest.memberDTO.memberEmail}</td>
            <td>${supportRequest.memberDTO.memberName}</td>
            <td>${supportRequest.supportRequestTitle}</td>
            <td>` + adminService.dateFormat(`${supportRequest.createdDate}`) + `</td>
            `;
            <!-- default.css -->
    if(`${supportRequest.supportRequestStatus}` == '승인') {
        text += `<td class="enquiryOk">승인</td>`;
    } else if(`${supportRequest.supportRequestStatus}` == '대기') {
        text += `<td class="enquiryNo" style="color: #0d0d0d">대기</td>`;
    } else if(`${supportRequest.supportRequestStatus}` == '반려') {
        text += `<td class="enquiryNo">반려</td>`;
    }
    text += `
            <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
                `
        if(`${supportRequest.totalSupportPrice}` != 0) {
            text += `
                        <button class="button__type_2 volunteer_button button__color__green">
                            후원명단
                        </button>`;
        }

        text += `
                    </td>
                </tr>
            `;

    $('.table').append(text);
});

let goPage = 0;
let contentId = 0;

$('.volunteer_button').on('click', function() {
    $('.volunteer-modal').show();
    $('.modal-stage').fadeOut(500);

    /* 해당 컨텐츠 번호 */
    contentId = $(this).parent().parent().find('.content__id').text();

    getList(contentId, goPage);

    $('.modal-close').on('click', function () {
        $('.volunteer-modal').fadeOut(500);
    });

})

let total;
let endPage;
let startPage;

function getList(contentId, page) {
    adminService.getDetailList("/admins/support-list", contentId, page,function(results) {
        let supportRequestDTOS = results.content;

        let text = ``;

        supportRequestDTOS.forEach(supportRequest => {

            text += `
                <div class="user__profile">
                    <h5></h5>
                    <div class="user__profile__input volunteer_input">
                        <input type="text" name="id" readonly="true" value="${supportRequest.memberDTO.id}"/>
                        <input type="text" name="memberName" value="${supportRequest.memberDTO.memberName}" readonly="true"/>
                        <input type="text" name="memberEmail" value="${supportRequest.memberDTO.memberEmail}" readonly="true"/>
                        <input type="text" name="supportPrice" value="${supportRequest.supportPrice}" readonly="true"/>
                    </div>
                </div>
            `;
        });
        $('.append-div').html(text);

        //여기가 문제
        const $pagination = $(".paging-modal");
        $pagination.empty();

        const maxDisplayedPages = 5; // 한 번에 표시할 페이지 수
        total = results.totalElements;

        endPage = Math.ceil( ++goPage / 5.0) * maxDisplayedPages;
        startPage = endPage - 4; // 시작 페이지 번호

        let listSize = total < 5 ? total : 5;
        // if(total < 5) {
        //     listSize = total;
        // } else {
        //     listSize = 5;
        // }
        // listSize = supportRequestDTOS.length;
        const realEnd = Math.ceil(Math.ceil(total * 1.0) / listSize);

        if(realEnd < endPage) {
            endPage = realEnd;
        }

        if (startPage > 1) {
            $pagination.prepend(`<a class="changePage-modal arrow-right" style="color: black"><code><</code></a>`);
        }

        for (let i = startPage; i <= endPage; i++) {
            if (i === goPage) {
                $pagination.append(`<code id="currentPage-modal">` + goPage +`</code>`);
            } else {
                $pagination.append(`<a class="changePage-modal count" style="color: black"><code>` + i + `</code></a>`);
            }
        }

        if (endPage < realEnd) {
            $pagination.append(`<a class="changePage-modal arrow-right" style="color: black"><code>></code></a>`);
        }
    });
}

$(".paging-modal").on("click", ".changePage-modal", function(e) {
    e.preventDefault();
    $('.append-div').empty();
    $(".paging-modal").empty();
    const targetPage = $(this).text();

    if (targetPage == '<') {
        console.log(startPage)
        goPage = startPage - 6;
    } else if (targetPage == '>') {
        goPage = endPage;
    } else {
        goPage = parseInt(targetPage) - 1;
    }
    // if ($(this).hasClass("arrow-left")) {
    //     if (goPage > 0) {
    //         goPage--;
    //     }
    // } else if ($(this).hasClass("arrow-right")) {
    //     goPage++;
    // } else {
    //     goPage = parseInt(targetPage) - 1;
    // }

    getList(contentId, goPage);
});

// $detailButton.on('click', () => {
//     adminService.getDetailList("/admins/support-list", page, function(results) {
//         results.forEach(result => {
//             let text;
//
//             text = `
//                 <div class="user__profile volunteer_profile">
//                     <h5>회원정보</h5>
//                     <div class="user__profile__input volunteer_input">
//                         <input type="text" name="" value="회원번호" readonly="true"/>
//                         <input type="text" name="" value="이름" readonly="true"/>
//                         <input type="text" name="" value="전화번호" readonly="true"/>
//                         <input type="text" name="" value="아이디" readonly="true"/>
//                     </div>
//                 </div>
//             `;
//
//
//         });
//     });
//
//     $('.modal-stage').show();
//     $('.volunteer-modal').fadeOut(500);
//
//     $('.modal-close').on('click', function () {
//         $('.modal-stage').fadeOut(500);
//     });
//
// })

$('#answer-check, #denied-check').on('click', function() {
    adminService.updateStatus("/admins/update-requests-status", $checkArr, $(this).text().substr(-2), function() {
        document.location.reload(true);
    });
});

$('#confirm-delete').on('click', function() {
    adminService.deleteOrUpdate("/admins/support-requests-delete", $checkArr, function() {
        document.location.reload(true);
    });
});

$('.search').on('click', () => {
    location.href = "/admin/sponsorship-request?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});

$('.content__detail__btn').on('click', function () {
    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    adminService.getDetail("/admins/support-request-details", contentId, function(result) {
        let text;

        text = `
            <section class="modal">
                <div class="modal__header">
                    <h3 class="modal__title">후원 요청 상세보기</h3>
                    <a class="modal-close" onclick="modalClose()">
                        <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                            <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                        </svg>
                    </a>
                </div>
                <form action="">
                    <main class="modal__main">
                        <div class="modal__profile__top">
                            <h4>회원정보</h4>
                            <div class="user__profile">
                                <h5>이름</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.memberDTO.memberName}" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>이메일</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.memberDTO.memberEmail}" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>공양미</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.memberDTO.memberRice}" readonly="true"/>
                                </div>
                            </div>
                        </div>
                        
                        <h4 style="margin-top: 20px;">이미지</h4>
                        <!-- 이미지 파일 -->
                        <div class="content__img__wrap">
                            <!-- 임시로 name='file' 해둠 -->
                            <label>
                                <div class="content__img">
                                    <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6"/>
                                </div>
                                <input type="file" name="file" style="display: none"/>
                            </label>
                            <label>
                                <div class="content__img">
                                    <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6"/>
                                </div>
                                <input type="file" name="file" style="display: none"/>
                            </label>
                            <label>
                                <div class="content__img">
                                    <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6"/>
                                </div>
                                <input type="file" name="file" style="display: none"/>
                            </label>
                            <label>
                                <div class="content__img">
                                    <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6"/>
                                </div>
                                <input type="file" name="file" style="display: none"/>
                            </label>
                            <label>
                                <div class="content__img">
                                    <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6"/>
                                </div>
                                <input type="file" name="file" style="display: none"/>
                            </label>
                        </div>
                        <div class="modal__profile__bottom">
                            <h4>후원요청 제목</h4>
                            <input type="text" name="" value="${result.supportRequestTitle}" style="width: 100%; border: 0;" readonly="true"/>
                        </div>
                        <div class="modal__profile__bottom">
                            <h4>후원요청 내용</h4>
                            <div class="notice__content">
                                <textarea>${result.supportRequestContent}</textarea>
                            </div>
                        </div>
    
                        <div class="user__profile__button">
                            <button id="completeBtn" class="button__type_2 button__color__green" type="button" onclick="modalClose()">
                                닫기
                            </button>
                        </div>
                    </main>
                </form>
            </section>
        `;

        $('.modal-stage').html(text);

//         $('input[name=volunteerWorkCategoryModify]').each(function() {
//             if($(this).val() == `${result.volunteerWorkCategory}`) {
//                 $(this).prop('checked', true);
//             }
//         });
//
//         fileDTO.fileName = `${result.fileDTO.fileName}`
//         fileDTO.filePath = `${result.fileDTO.filePath}`
//         fileDTO.fileUuid = `${result.fileDTO.fileUuid}`
//
//         $photoPicker = $('#photo-picker-modify');
//         $fileId = $('#fileId');
//         loadPhoto();
    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

});


function modalClose() {
    $modalStage.fadeOut(500);
    $('.modal-stage').empty();
}