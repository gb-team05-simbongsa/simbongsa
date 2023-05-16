volunteers.forEach(volunteer => {
    let text;

    text = `
        <tr class="table__content">
            <td>
                <label class="check-label">
                  <input type="checkbox" name="check" />
                </label>
            </td>
            <td class="content__id">${volunteer.id}</td>
            <td>${volunteer.volunteerWorkTitle}</td>
            <td>${volunteer.volunteerWorkCategory}</td>
            <td>${volunteer.volunteerWorkPlace}</td>
            <td>${volunteer.volunteerWorkRegisterAgency}</td>
            <td>${volunteer.volunteerWorkRecruitNumber}</td>
            <td>
                <button class="content__detail__btn button__type_2 button__color__green">
                    상세보기
                </button>
                <button class="button__type_2 volunteer_button button__color__green">
                    신청명단
                </button>
            </td>
        </tr>
    `;

    $('.table').append(text);
});

$('#confirm-delete').on('click', function() {
    adminService.deleteAllById("/admins/volunteers-delete", $checkArr, function() {
        document.location.reload(true);
    });
});