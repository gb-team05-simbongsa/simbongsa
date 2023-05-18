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

$('.content__detail__btn').on('click', function () {
    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    /* ajax 에 콜백 넘겨주는 코드 작성해야 함 (검색기능 ajax로)*/
    adminService.getDetail("/admins/funding-details", contentId, function(result) {
        // $('input[name=id]').val(contentId);
        // $('input[name=noticeTitle]').val(result.noticeTitle);
        // $('.notice-detail-content').val(result.noticeContent);
    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});


$('#confirm-delete').on('click', function() {
    adminService.deleteOrUpdate("/admins/fundings-delete", $checkArr, function() {
        document.location.reload(true);
    });
});

$('.search').on('click', () => {
    location.href = "/admin/funding?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});