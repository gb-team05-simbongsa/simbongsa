ricePayments.forEach(ricePayment => {
    let text;

    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                    <input type="checkbox" name="check"/>
                </label>
            </td>
            <td class="content__id">${ricePayment.id}</td>
            <td>${ricePayment.memberDTO.memberName}</td>
            <td>${ricePayment.memberDTO.memberEmail}</td>
            <td>${ricePayment.ricePaymentUsed}</td>
            <td>` + adminService.dateFormat(`${ricePayment.createdDate}`) + `</td>
            <td>` + `${ricePayment.ricePaymentUsed}` * 110 + `</td>
        </tr>
    `;

    $('.table').append(text);
});

// /* 상세보기 모달창 ======================================= */
// $detailButton.on('click', function () {
//     var i = $detailButton.index($(this));
//
//     /* 해당 컨텐츠 번호 */
//     var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();
//
//     /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
//     adminService.getDetail("/admins/payments-details", contentId, function(result) {
//         $('input[name=id]').val(contentId);
//         $('input[name=noticeTitle]').val(result.noticeTitle);
//         $('.notice-detail-content').summernote('code', result.noticeContent);
//     });
//
//     /* 추후 타임리프로 대체할 예정 */
//     $modalStage.show();
//
//     /* 모달 닫는 이벤트 */
//     /* 추후 외부로 빼야함 */
//     $('.modal-close').on('click', function (e) {
//       $modalStage.fadeOut(500);
//     });
// });

$('#confirm-delete').on('click', function() {
    adminService.deleteOrUpdate("/admins/payments-delete", $checkArr, function() {
        document.location.reload(true);
    });
});

$('.search').on('click', () => {
    location.href = "/admin/payment?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});