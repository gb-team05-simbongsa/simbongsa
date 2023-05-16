fundings.forEach(funding => {
    let text;
    console.log(funding)

    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                    <input type="checkbox" name="check"/>
                </label>
            </td>
            <td class="content__id">${funding.id}</td>
            <td>${funding.fundingCategory}</td>
            <td>${funding.fundingTitle}</td>
            <td>${funding.fundingStartDate}</td>
            <td>${funding.fundingEndDate}</td>
            <td>${funding.fundingCreator.fundingCreatorNickname}</td>
            `;
        if(`${funding.fundingStatus}` == '승인') {
            text += `<td class="enquiryOk">승인</td>`;
        } else if(`${funding.fundingStatus}` == '대기') {
            text += `<td class="enquiryNo">대기</td>`;
        }
    text += `
            <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
            </td>
        </tr>
    `;

    $('.table').append(text);
});



$('#confirm-delete').on('click', function() {
    adminService.deleteAllById("/admins/fundings-delete", $checkArr, function() {
        document.location.reload(true);
    });
});