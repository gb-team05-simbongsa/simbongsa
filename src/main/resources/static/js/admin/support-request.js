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
            <td>${supportRequest.createdDate}</td>
            `;
            <!-- default.css -->
    if(`${supportRequest.supportRequestStatus}` == '승인') {
        text += `<td class="enquiryOk">승인</td>`;
    } else if(`${supportRequest.supportRequestStatus}` == '대기') {
        text += `<td class="enquiryNo">대기</td>`;
    }
    text += `
            <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
                <button class="button__type_2 volunteer_button button__color__green">
                    후원명단
                </button>
            </td>
        </tr>
    `;

    $('.table').append(text);
});

$('.volunteer_button').on('click', function() {
    $('.volunteer-modal').show();
    $('.modal-stage').fadeOut(500);

    /* 해당 컨텐츠 번호 */
    var contentId = $(this).parent().parent().siblings('.content__id').text();
    console.log(contentId);

    adminService.getDetail("/admins/support-list", contentId, function(results) {
        results.forEach(result => {
            let text;

            text = `
                <div class="user__profile volunteer_profile">
                    <h5>회원정보</h5>
                    <div class="user__profile__input volunteer_input">
                        <input type="text" name="id" readonly="true" value="${result.memberDTO.id}"/>
                        <input type="text" name="memberName" value="${result.memberDTO.memberName}" readonly="true"/>
                        <input type="text" name="memberEmail" value="${result.memberDTO.memberEmail}" readonly="true"/>
                        <input type="text" name="supportPrice" value="${result.supportPrice}" readonly="true"/>
                    </div>
                </div>
            `;

            $('.modal__profile__top').append(text);
        });
    })

    $('.modal-close').on('click', function () {
        $('.volunteer-modal').fadeOut(500);
    });

})
//
// $detailButton.on('click', () => {
//     $('.modal-stage').show();
//     $('.volunteer-modal').fadeOut(500);
//
//     $('.modal-close').on('click', function () {
//         $('.modal-stage').fadeOut(500);
//     });
//
// })