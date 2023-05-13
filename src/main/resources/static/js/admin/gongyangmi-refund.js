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