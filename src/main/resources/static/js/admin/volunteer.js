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

function modalClose() {
    $('.detail-modal').fadeOut(500);
}


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
                    <a class="modal-close" onclick="modalClose()">
                        <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                            <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                        </svg>
                    </a>
                </div>
                <form class="storage_form" name="registForm">
                    <input type="hidden" id="modifyId" value="${result.id}">
                    <main class="modal__main">
                        <div class="modal__profile__top">
                            <h4>봉사 정보</h4>
                            <div class="user__profile">
                                <h5>게시글 제목</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkTitleModify" value="${result.volunteerWorkTitle}"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉사기간</h5>
                                <div class="user__profile__input_volunteer_modal">
                                    <input type="datetime-local" name="volunteerWorkStartDateModify" value="${result.volunteerWorkStartDate}"/>
                                    <input type="datetime-local" name="volunteerWorkEndDateModify" value="${result.volunteerWorkEndDate}"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉시시간</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkTimeModify" value="${result.volunteerWorkTime}"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>모집기간</h5>
                                <div class="user__profile__input">
                                    <input type="date" name="volunteerWorkJoinStartDateModify" value="${result.volunteerWorkJoinStartDate}"/>
                                    <input type="date" name="volunteerWorkJoinEndDateModify" value="${result.volunteerWorkJoinEndDate}"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>모집인원</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkRecruitNumberModify" value="${result.volunteerWorkRecruitNumber}"/>
                                </div>
                            </div>
                            <div class="user__profile" style="width: 900px;">
                                <h5>봉사분야</h5>
                                <div class="user__profile__input" style="width: 620px;">
                                    <input type="radio" name="volunteerWorkCategoryModify" value="시설봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">시설봉사</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="재가봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">재가봉사</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="전문봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">전문봉사</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="지역사회봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">지역사회봉사</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="금물품봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">금물품봉사</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="해외봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">해외봉사</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="헌혈" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">헌혈</p>
                                    <input type="radio" name="volunteerWorkCategoryModify" value="재능기부봉사" style="width: 15px"/>
                                    <p style="font-size: 13px; margin-right: 5px;">재능기부봉사</p>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>봉사장소</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkPlaceModify" value="${result.volunteerWorkPlace}"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>등록기관</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="volunteerWorkRegisterAgencyModify" value="${result.volunteerWorkRegisterAgency}"/>
                                </div>
                            </div>
                            <h4 style="margin-top: 20px;">이미지</h4>
                            <!-- 이미지 파일 -->
                            <input type="hidden" id="fileId" value="${result.fileDTO.id}">
                            <input type="file" name="fileModify" id="photo-picker-modify" style="display: none"/>
                            <div class="content__img__wrap-modify">
                                <!-- 임시로 name='file' 해둠 -->
                                <label for="photo-picker-modify">
                                    <div class="content__img">
                                        <img src="/file/display?fileName=${result.fileDTO.filePath}"/>
                                    </div>
                                </label>
                            </div>
                            <div class="modal__profile__bottom">
                                <h4>게시물 내용</h4>
                                <textarea cols="20" rows="10" name="volunteerWorkContentModify">${result.volunteerWorkContent}</textarea>
                            </div>
                            <div class="user__profile__button" style="margin-top: 20px;">
                                <button id="modifyButton" class="button__type_2 button__color__green" type="button" onclick="modify()">
                                    수정 완료
                                </button>
                            </div>
                        </div>
                    </main>
                </form>
            </section>
        `;

        $('.detail-modal').html(text);

        $('input[name=volunteerWorkCategoryModify]').each(function() {
            if($(this).val() == `${result.volunteerWorkCategory}`) {
                $(this).prop('checked', true);
            }
        });

        fileDTO.fileName = `${result.fileDTO.fileName}`
        fileDTO.filePath = `${result.fileDTO.filePath}`
        fileDTO.fileUuid = `${result.fileDTO.fileUuid}`

        $photoPicker = $('#photo-picker-modify');
        $fileId = $('#fileId');
        loadPhoto();
    });

    $('.detail-modal').show();
    $('.volunteer-modal').fadeOut(500);
});

$('#submitBtn').on('click', function() {
    adminService.saveVolunteerWork(setVolunteerWorkDTO(), function() {
        location.href = "/admin/volunteer";
    });
});

$('.search').on('click', () => {
    location.href = "/admin/volunteer?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});


let goPage = 0;
let contentId = 0;

$('.volunteer_button').on('click', function() {
    $('.volunteer-modal').show();
    $('.modal-stage').fadeOut(500);

    /* 해당 컨텐츠 번호 */
    contentId = $(this).parent().parent().find('.content__id').text();

    getList(contentId, goPage);

    $('.modal-close').on('click', function () {
        $('.volunteer-modal').fadeOut(500);
    });

})

let total;
let endPage;
let startPage;

function getList(contentId, page) {
    adminService.getDetailList("/admins/activity-lists", contentId, page,function(results) {
        let volunteerWorkActivityDTOS = results.content;

        let text = ``;

        volunteerWorkActivityDTOS.forEach(volunteerWorkActivity => {

            text += `
                <div class="user__profile">
                    <h5></h5>
                    <div class="user__profile__input volunteer_input">
                        <input type="text" name="id" readonly="true" value="${volunteerWorkActivity.memberDTO.id}"/>
                        <input type="text" name="memberName" value="${volunteerWorkActivity.memberDTO.memberName}" readonly="true"/>
                        <input type="text" name="memberEmail" value="${volunteerWorkActivity.memberDTO.memberEmail}" readonly="true"/>
                        <input type="text" name="supportPrice" value="${volunteerWorkActivity.volunteerWorkDTO.volunteerWorkTime}" readonly="true"/>
                    </div>
                </div>
            `;
        });
        $('.append-div').html(text);

        //여기가 문제
        const $pagination = $(".paging-modal");
        $pagination.empty();

        const maxDisplayedPages = 5; // 한 번에 표시할 페이지 수
        total = results.totalElements;

        endPage = Math.ceil( ++goPage / 5.0) * maxDisplayedPages;
        startPage = endPage - 4; // 시작 페이지 번호

        let listSize = total < 5 ? total : 5;
        // if(total < 5) {
        //     listSize = total;
        // } else {
        //     listSize = 5;
        // }
        // listSize = supportRequestDTOS.length;
        const realEnd = Math.ceil(Math.ceil(total * 1.0) / listSize);

        if(realEnd < endPage) {
            endPage = realEnd;
        }

        if (startPage > 1) {
            $pagination.prepend(`<a class="changePage-modal arrow-right" style="color: black"><code><</code></a>`);
        }

        for (let i = startPage; i <= endPage; i++) {
            if (i === goPage) {
                $pagination.append(`<code id="currentPage-modal">` + goPage +`</code>`);
            } else {
                $pagination.append(`<a class="changePage-modal count" style="color: black"><code>` + i + `</code></a>`);
            }
        }

        if (endPage < realEnd) {
            $pagination.append(`<a class="changePage-modal arrow-right" style="color: black"><code>></code></a>`);
        }
    });
}

$(".paging-modal").on("click", ".changePage-modal", function(e) {
    e.preventDefault();
    $('.append-div').empty();
    $(".paging-modal").empty();
    const targetPage = $(this).text();

    if (targetPage == '<') {
        console.log(startPage)
        goPage = startPage - 6;
    } else if (targetPage == '>') {
        goPage = endPage;
    } else {
        goPage = parseInt(targetPage) - 1;
    }
    // if ($(this).hasClass("arrow-left")) {
    //     if (goPage > 0) {
    //         goPage--;
    //     }
    // } else if ($(this).hasClass("arrow-right")) {
    //     goPage++;
    // } else {
    //     goPage = parseInt(targetPage) - 1;
    // }

    getList(contentId, goPage);
});

//file---------------------------------------------------------------------
globalThis.uuids;
globalThis.paths;
FileList.prototype.forEach = Array.prototype.forEach;
let prevFileList;

/* 저장할 파일들의 Array */

let $fileId;
let $photoPicker = $("#photo-picker");
const uploadAjaxConfig = (data) => {
    return {
        url: "/file/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}

$('#photo-picker').on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();
    prevFileList = '';
    globalThis.uuids = prevFileList;
    globalThis.paths = prevFileList;

    if ($files.length > 1) {
        alert("파일은 1개만 등록할 수 있습니다.");
        /* 파일 input 초기화 */
        $('#photo-picker')[0].files = prevFileList;
        console.log($('#photo-picker')[0].files);
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

function loadPhoto() {
    $photoPicker.on("change", function () {
        console.log("!!!!!!!!!!!!")
        let $files = $(this)[0].files;
        let formData = new FormData();
        prevFileList = '';
        globalThis.uuids = prevFileList;
        globalThis.paths = prevFileList;

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
            $('.content__img__wrap-modify').empty();
            result.paths.forEach((path, i) => {
                if ($files[i].type.startsWith("image")) {
                    $('.content__img__wrap-modify').append(`<img class="imageThumbnail" src="/file/display?fileName=${result.paths[i]}" style="width: 200px">`);
                } else $('.content__img__wrap-modify').append(`<img style="width: 100px"/>`);
            });
        });
    });
}

let fileDTO = new Object();

let setVolunteerWorkDTO = function() {
    console.log($('#photo-picker')[0])
    const $files = $('#photo-picker')[0].files;

    fileDTO.fileName = $files[0].name;
    fileDTO.fileUuid = globalThis.uuids[0];
    fileDTO.filePath = globalThis.paths[0];

    const volunteerWorkDTO = {
        volunteerWorkTitle : $('input[name=volunteerWorkTitle]').val(),
        volunteerWorkContent : $('textarea[name=volunteerWorkContent]').val(),
        volunteerWorkStartDate : $('input[name=volunteerWorkStartDate]').val(),
        volunteerWorkEndDate : $('input[name=volunteerWorkEndDate]').val(),
        volunteerWorkTime : $('input[name=volunteerWorkTime]').val(),
        volunteerWorkJoinStartDate : $('input[name=volunteerWorkJoinStartDate]').val(),
        volunteerWorkJoinEndDate : $('input[name=volunteerWorkJoinEndDate]').val(),
        volunteerWorkRecruitNumber : $('input[name=volunteerWorkRecruitNumber]').val(),
        volunteerWorkCategory : $('input[name=volunteerWorkCategory]:checked').val(),
        volunteerWorkPlace : $('input[name=volunteerWorkPlace]').val(),
        volunteerWorkRegisterAgency : $('input[name=volunteerWorkRegisterAgency]').val(),

        fileDTO : fileDTO
    }

    return volunteerWorkDTO;
}

let setVolunteerWorkModifyDTO = function() {
    const $files = $photoPicker[0].files;

    console.log($files)
    if($files.length != 0) {
        // let fileDTO = new Object();
        fileDTO.fileName = $files[0].name;
        fileDTO.fileUuid = globalThis.uuids[0];
        fileDTO.filePath = globalThis.paths[0];
    }

    fileDTO.id = $fileId.val();

    const volunteerWorkDTO = {
        id : $('#modifyId').val(),
        volunteerWorkTitle : $('input[name=volunteerWorkTitleModify]').val(),
        volunteerWorkContent : $('textarea[name=volunteerWorkContentModify]').val(),
        volunteerWorkStartDate : $('input[name=volunteerWorkStartDateModify]').val(),
        volunteerWorkEndDate : $('input[name=volunteerWorkEndDateModify]').val(),
        volunteerWorkTime : $('input[name=volunteerWorkTimeModify]').val(),
        volunteerWorkJoinStartDate : $('input[name=volunteerWorkJoinStartDateModify]').val(),
        volunteerWorkJoinEndDate : $('input[name=volunteerWorkJoinEndDateModify]').val(),
        volunteerWorkRecruitNumber : $('input[name=volunteerWorkRecruitNumberModify]').val(),
        volunteerWorkCategory : $('input[name=volunteerWorkCategoryModify]:checked').val(),
        volunteerWorkPlace : $('input[name=volunteerWorkPlaceModify]').val(),
        volunteerWorkRegisterAgency : $('input[name=volunteerWorkRegisterAgencyModify]').val(),

        fileDTO : fileDTO
    }

    return volunteerWorkDTO;
}


function modify() {
    adminService.updateVolunteerWork(setVolunteerWorkModifyDTO(), function() {
        document.location.reload(true);
    });
}

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