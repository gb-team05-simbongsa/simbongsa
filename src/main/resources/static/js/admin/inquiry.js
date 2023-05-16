inquiries.forEach(inquiry => {
    let text;
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
    $('.table').append(text);
});

$('.content__detail__btn').on('click', function () {
    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    adminService.getDetail("/admins/inquiry-details", contentId, function(result) {
        $('#memberName').val(result.memberDTO.memberName);
        $('#memberEmail').val(result.memberDTO.memberEmail);
        $('#inquiryContent').val(result.inquiryContent);

        result.answerDTO.answerContent == null ? $('#answerContent').val('') : $('#answerContent').val(result.answerDTO.answerContent);
    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});