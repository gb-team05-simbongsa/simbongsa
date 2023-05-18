members.forEach(member => {
    let text;

    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                    <input type="checkbox" name="check" />
                </label>
            </td>
            <td class="content__id">${member.id}</td>
            <td>${member.memberName}</td>
            <td>${member.memberEmail}</td>
            <td>${member.memberRank}</td>
            `;
    if(`${member.memberStatus}` == '가입') {
        text += `<td class="enquiryOk">가입</td>`;
    } else if(`${member.memberStatus}` == '탈퇴') {
        text += `<td class="enquiryNo">탈퇴</td>`;
    }
    text += `
<!--            <td>${member.memberStatus}</td>-->
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
    adminService.getDetail("/admins/member-details", contentId, function(result) {
        $('input[name=id]').val(result.id);
        $('#email').val(result.memberEmail);
        $('#age').val(result.memberAge);
        $('#joinType').val(result.memberJoinType);
        $('#name').val(result.memberName);
        $('#rice').val(result.memberRice);
        $('#address').val(result.memberAddress);
        $('#interest').val(result.memberInterest);
        $('#volunteerTime').val(result.memberVolunteerTime);
    });

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});

$('.search').on('click', () => {
    location.href = "/admin/user?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});

$('#confirm-delete').on('click', () => {
    adminService.deleteOrUpdate("/admins/update-member-status", $checkArr, function () {
        document.location.reload(true);
    })
});