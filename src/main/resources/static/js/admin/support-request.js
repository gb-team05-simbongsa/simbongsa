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