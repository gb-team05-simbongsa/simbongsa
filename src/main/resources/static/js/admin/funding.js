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
            <td>` + adminService.dateFormat(`${funding.fundingStartDate}`) + `</td>
            <td>` + adminService.dateFormat(`${funding.fundingEndDate}`) + `</td>
            <td>${funding.fundingCreator.fundingCreatorNickname}</td>
            `;
        if(`${funding.fundingStatus}` == '승인') {
            text += `<td class="enquiryOk">승인</td>`;
        } else if(`${funding.fundingStatus}` == '대기') {
            text += `<td class="enquiryNo" style="color: #0d0d0d">대기</td>`;
        } else if(`${funding.fundingStatus}` == '반려') {
            text += `<td class="enquiryNo">반려</td>`;
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
        let text;

        text = `
            <section class="modal">
                <div class="modal__header">
                    <h3 class="modal__title">펀딩 상세보기</h3>
                    <a class="modal-close" onclick="modalClose()">
                        <svg xmlns="http://www.w3.org/2000/svg" data-name="Capa 1" id="Capa_1" viewBox="0 0 20 19.84">
                            <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                        </svg>
                    </a>
                </div>
                <form class="storage_form" action="">
                    <main class="modal__main">
                        <div class="modal__profile__top">
                            <h4>펀딩 정보</h4>
                            <div class="user__profile">
                                <h5>창작자 이름</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.fundingCreator.fundingCreatorNickname}" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>프로젝트 카테고리</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.fundingCategory}" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>프로젝트 제목</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.fundingTitle}" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>프로젝트 짧은제목</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.fundingShortTitle}" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>펀딩 일정</h5>
                                <div class="user__profile__input" style="width: 335px;">
                                    <input type="text" name="" value="` + adminService.dateFormat(`${result.fundingStartDate}`) + `" readonly="true"/>
                                    <input type="text" name="" value="` + adminService.dateFormat(`${result.fundingEndDate}`) + `" readonly="true"/>
                                </div>
                            </div>
                            <div class="user__profile">
                                <h5>목표금액</h5>
                                <div class="user__profile__input">
                                    <input type="text" name="" value="${result.fundingTargetPrice}" readonly="true"/>
                                </div>
                            </div>

                            <h4 style="margin-top: 20px;">이미지</h4>
                            <!-- 이미지 파일 -->
                            <div class="content__img__wrap">
                                <!-- 임시로 name='file' 해둠 -->
                                        `;
        let fileDTOs = result.fileDTOs;
        console.log(fileDTOs.length);
        if(fileDTOs.length == 0) {
            text += `
                    <label>
                        <div class="content__img">
                            <img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" style="width: 200px"> 
                        </div>
                    </label>
                    `;
        } else {
            fileDTOs.forEach((fileDTO) => {
                console.log(fileDTO); // 각 요소 출력 예시
                text += `
                        <label>
                            <div class="content__img">
                                <img src="/file/display?fileName=` + fileDTO.filePath + `" style="width: 200px">
                            </div>
                        </label>
                        `;
            });
        }

        text += `
                            </div>
                            <h4 style="margin-top: 40px; margin-bottom: -15px;">프로젝트 소개</h4>
                            <div class="notice__content">
                                <textarea id="introduce">${result.fundingIntroduce}</textarea>
                            </div>
                            <h4 style="margin-top: 40px; margin-bottom: -15px;">프로젝트 예산소개</h4>
                            <div class="notice__content">
                                <textarea id="budget">${result.fundingBudgetExplain}</textarea>
                            </div>
                            <h4 style="margin-top: 40px; margin-bottom: -15px;">프로젝트 일정소개</h4>
                            <div class="notice__content">
                                <textarea id="schedule">${result.fundingScheduleExplain}</textarea>
                            </div>
                            <h4 style="margin-top: 40px; margin-bottom: -15px;">프로젝트 선물소개</h4>
                            <div class="notice__content">
                                <textarea id="gift">${result.fundingGiftExplain}</textarea>
                            </div>
                            <div class="user__profile__button" style="margin-top: 20px;">
                                <button id="completeBtn" class="button__type_2 button__color__green" type="button" onclick="modalClose()">
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

    /* 추후 타임리프로 대체할 예정 */
    $modalStage.show();

    /* 모달 닫는 이벤트 */
    /* 추후 외부로 빼야함 */
    $('.modal-close, #completeBtn').on('click', function (e) {
        $modalStage.fadeOut(500);
    });
});

function modalClose() {
    $modalStage.fadeOut(500);
    $('.modal-stage').empty();
}


$('#confirm-delete').on('click', function() {
    adminService.deleteOrUpdate("/admins/fundings-delete", $checkArr, function() {
        document.location.reload(true);
    });
});

$('#accept-check, #denied-check').on('click', function() {
    adminService.updateStatus("/admins/update-fundings-status", $checkArr, $(this).text().substr(-2),function() {
        document.location.reload(true);
    })
})

$('.search').on('click', () => {
    location.href = "/admin/funding?searchType=" + $('.listbox-selecter').text() + "&searchContent=" + $('.search-input').val();
});


// $('.content__detail__btn').on('click', function() {
//
//     var i = $detailButton.index($(this));
//
//     /* 해당 컨텐츠 번호 */
//     var contentId = $detailButton.eq(i).parent().siblings('.content__id').text();
//
//
//
//     $('.detail-modal').show();
//     $('.volunteer-modal').fadeOut(500);
// });