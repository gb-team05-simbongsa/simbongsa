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
    adminService.deleteOrUpdate("/admins/volunteers-delete", $checkArr, function() {
        document.location.reload(true);
    });
});

$('.content__detail__btn').on('click', function() {

});

$('.search').on('click', () => {
    location.href = "/admin/volunteer?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});

//file---------------------------------------------------------------------
globalThis.uuids;
FileList.prototype.forEach = Array.prototype.forEach;
let prevFileList;

/* 저장할 파일들의 Array */


const $photoPicker = $("#photo-picker");
const uploadAjaxConfig = (data) => {
    return {
        url: "/admins/files/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}

$photoPicker.on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();

    if ($files.length > 1) {
        alert("파일은 1개만 등록할 수 있습니다.");
        /* 파일 input 초기화 */
        $photoPicker[0].files = prevFileList;
        console.log($photoPicker[0].files);
        return;
    }

    prevFileList = $files;

    // formData.append("file", $files);
    $files.forEach(file => formData.append("file", file));


    adminService.volunteerFile(uploadAjaxConfig(formData), (result) => {
        console.log(result)
        globalThis.uuids = result.uuids;
        $('.content__img__wrap').empty();
        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $('.content__img__wrap').append(`<img class="imageThumbnail" src="/admins/files/display?fileName=${result.paths[i]}">`);
            } else $('.content__img__wrap').append(`<img style="width: 100px"/>`);
        });
    });
});

let setVolunteerWorkDTO = function() {
    const $files = $photoPicker[0].files;

    let fileDTO = new Object();
    fileDTO.fileOrgName = file.name;
    fileDTO.fileUuid = globalThis.uuids[i];

    const volunteerWorkDTO = {
        volunteerWorkTitle : $('input[name=volunteerWorkTitle]').val(),
        volunteerWorkContent : $('input[name=volunteerWorkContent]').val(),
        volunteerWorkStartDate : $('input[name=volunteerWorkStartDate]').val(),
        volunteerWorkEndDate : $('input[name=volunteerWorkEndDate]').val(),
        volunteerWorkTime : $('input[name=volunteerWorkTime]').val(),
        volunteerWorkJoinStartDate : $('input[name=volunteerWorkJoinStartDate]').val(),
        volunteerWorkJoinEndDate : $('input[name=volunteerWorkJoinEndDate]').val(),
        volunteerWorkRecruitNumber : $('input[name=volunteerWorkRecruitNumber]').val(),
        volunteerWorkCategory : $('input[name=volunteerWorkCategory]').val(),
        volunteerWorkPlace : $('input[name=volunteerWorkPlace]').val(),
        volunteerWorkRegisterAgency : $('input[name=volunteerWorkRegisterAgency]').val(),

        fileDTO : fileDTO
    }

    return volunteerWorkDTO;
}

$('#submitBtn').on('click', function() {
    adminService.saveVolunteerWork(setVolunteerWorkDTO(), function() {
        location.href = "/admin/volunteer";
    });
});

// let $fileAjax = (contentId, table) => {
//     const $files = $photoPicker[0].files;
//
//     if ($files.length === 0) {
//         return;
//     }
//
//     fileVOs = new Array();
//
//     $files.forEach((file, i) => {
//         let fileVO = new Object();
//         fileVO.fileOrgName = file.name;
//         fileVO.fileUuid = globalThis.uuids[i];
//         fileVOs.push(fileVO);
//     });
//
//     let config = {
//         url: `/users/mypage/files/save/${contentId}?table=${table}`,
//         method: "POST",
//         data: JSON.stringify(fileVOs),
//         contentType: "application/json; charset=utf-8",
//     }
//
// }