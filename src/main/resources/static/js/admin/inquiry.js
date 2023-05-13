inquiries.forEach(inquiry => {
    let text;
    console.log('옴?')
    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                    <input type="checkbox" name="check"/>
                </label>
            </td>
            <td class="content__id">${inquiry.id}</td>
            <td>${inquiry.memberDTO.memberName}</td>
            <td>${inquiry.memberDTO.memberEmail}</td>
            <td>${inquiry.createdDate}</td>
            <!-- default.css -->
            <td class="enquiryOk">답변완료</td>
            <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
            </td>
        </tr>
    `;
    console.log(text)
    $('.table').append(text);
});