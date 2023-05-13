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