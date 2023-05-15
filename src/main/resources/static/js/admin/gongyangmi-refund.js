ricePayments.forEach(ricePayment => {
    let text;
    console.log(ricePayment)

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
            <td>${ricePayment.createdDate}</td>
            `;
    if(`${ricePayment.ricePaymentStatus}` == '환전승인') {
        text += `<td class="enquiryOk">환전승인</td>`;
    } else if(`${ricePayment.ricePaymentStatus}` == '환전대기') {
        text += `<td class="enquiryNo">환전대기</td>`;
    }
            <!-- default.css -->
    text += `<td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
            </td>
        </tr>
    `;

    $('.table').append(text);
});

$('.content__detail__btn').on('click', function () {
    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    adminService.getDetail("/admins/rice-exchange-details", contentId, function(result) {
        $('#name').val(result.memberDTO.memberName);
        $('#email').val(result.memberDTO.memberEmail);
        $('#createdDate').val(result.createdDate);
        $('#exchangeBank').val(result.ricePaymentExchangeBank);
        $('#exchangeAccount').val(result.ricePaymentExchangeAccount);
        $('#price').val(result.ricePaymentUsed);
    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});