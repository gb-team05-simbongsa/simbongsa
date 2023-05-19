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

    var i = $detailButton.index($(this));

    /* 해당 컨텐츠 번호 */
    var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();

    adminService.getDetail("/admins/volunteer-work-details", contentId, function(result) {
        let text;

        text = `
            <section class="modal">
                <div class="modal__header">
                    <h3 class="modal__title">봉사 상세보기</h3>
                    <a class="modal-close">
                        <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                            <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                        </svg>
                    </a>
                </div>
                <form class="storage_form" name="registForm">
                    <main class="modal__main">
                        <div class="modal__profile__top">
                            <h4>봉사 정보</h4>
                            <div class="user__profile">
                                <h5>게시글 제목</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkTitle" value="${result.volunteerWorkTitle}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉사기간</h5>
                                <div class="user__profile__input_volunteer_modal">
                                    <input type="datetime-local" name="volunteerWorkStartDate" value="${result.volunteerWorkStartDate}" readonly/>
                                    <input type="datetime-local" name="volunteerWorkEndDate" value="${result.volunteerWorkEndDate}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉시시간</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkTime" value="${result.volunteerWorkTime}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>모집기간</h5>
                                <div class="user__profile__input">
                                    <input type="date" name="volunteerWorkJoinStartDate" value="${result.volunteerWorkJoinStartDate}" readonly/>
                                    <input type="date" name="volunteerWorkJoinEndDate" value="${result.volunteerWorkJoinEndDate}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>모집인원</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkRecruitNumber" value="${result.volunteerWorkRecruitNumber}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉사분야</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkCategory" value="${result.volunteerWorkCategory}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉사장소</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkPlace" value="${result.volunteerWorkPlace}" readonly/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>등록기관</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkRegisterAgency" value="${result.volunteerWorkRegisterAgency}" readonly/>
                                </div>
                            </div>
                            <h4 style="margin-top: 20px;">이미지</h4>
                            <!-- 이미지 파일 -->
                            <div class="content__img__wrap">
                                <!-- 임시로 name='file' 해둠 -->
                                <label>
                                    <div class="content__img">
                                        <img src="/file/display?fileName=${result.fileDTO.filePath}"/>
                                    </div>
                                </label>
                            </div>
                            <div class="modal__profile__bottom">
                                <h4>게시물 내용</h4>
                                <textarea cols="20" rows="10" name="volunteerWorkContent" readonly>${result.volunteerWorkContent}</textarea>
                            </div>
                            <div class="user__profile__button" style="margin-top: 20px;">
                                <button id="submitBtn" class="button__type_2 button__color__green" type="button">
                                    닫기
                                </button>
                            </div>
                        </div>
                    </main>
                </form>
            </section>
        `;

        $('.modal-stage').html(text);
    });


    $('.modal-stage').show();
    $('.volunteer-modal').fadeOut(500);

    $('.modal-close').on('click', function () {
        $('.modal-stage').fadeOut(500);
    });
});

$('.search').on('click', () => {
    location.href = "/admin/volunteer?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});

//file---------------------------------------------------------------------
globalThis.uuids;
globalThis.paths;
FileList.prototype.forEach = Array.prototype.forEach;
let prevFileList;

/* 저장할 파일들의 Array */


const $photoPicker = $("#photo-picker");
const uploadAjaxConfig = (data) => {
    return {
        url: "/file/upload",
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
        globalThis.paths = result.paths;
        $('.content__img__wrap').empty();
        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $('.content__img__wrap').append(`<img class="imageThumbnail" src="/file/display?fileName=${result.paths[i]}" style="width: 200px">`);
            } else $('.content__img__wrap').append(`<img style="width: 100px"/>`);
        });
    });
});

let setVolunteerWorkDTO = function() {
    const $files = $photoPicker[0].files;

    let fileDTO = new Object();
    fileDTO.fileName = $files[0].name;
    fileDTO.fileUuid = globalThis.uuids[0];
    fileDTO.filePath = globalThis.paths[0];
    // fileDTO.fileRepresentationalType = null;

    const volunteerWorkDTO = {
        volunteerWorkTitle : $('input[name=volunteerWorkTitle]').val(),
        volunteerWorkContent : $('textarea[name=volunteerWorkContent]').val(),
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