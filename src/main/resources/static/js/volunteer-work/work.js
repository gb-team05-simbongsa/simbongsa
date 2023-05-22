const $div = $('.real_content');
let page = 0;
let keyword = '';

$(document).ready(function() {
    $('#location-select').change(function() {
        $('#search-form').submit();
    });
});



volunteerWorkDTOS.forEach((volunteerWorkDTOS, i) => {
        console.log('.volunteerWorkDTOS');
        let text='';
        text += `
                 <div class="first_content">
                            <div></div>
                            <div class="project_card">
                                <div></div>
                                <div class="project_card_div" id="${i}">
                                    <div class="project_card_img">
                                        <div class="image_wrapper">
                                            <a href="work-detail/${volunteerWorkDTOS.id}">
                       `;

        if(volunteerWorkDTOS.fileDTOS == null || volunteerWorkDTOS.fileDTOS == undefined) {
            text += `
                                    <img style=" width: 238px; height: 238px;" src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> `;
        } else {
            for(let j = 0; j <volunteerWorkDTOS.fileDTOS.length; j++) {
                if (volunteerWorkDTOS.fileDTOS[j].fileType === "REPRESENTATION") {
                    text += ` <img src="/file/display?fileName=${volunteerWorkDTOS.fileDTOS[j].filePath}/${volunteerWorkDTOS.fileDTOS[j].fileUuid}_${volunteerWorkDTOS.fileDTOS[j].fileName}">`;
                }
            }
        }

        text +=  `

                                                </a>
                                            <div class="like">
                                                <button class="like_button">
                                                    <!-- <span>좋아요</span> -->
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="project_name">
                                        <dl class="content_dl">
                                            <dd class="first_dd">
                                                <span>
                                                    <a class="first_dd_a">${volunteerWorkDTOS.volunteerWorkCategory}</a>
                                                </span>
                                                <span>
                                                    <a class="first_dd_a_a">${volunteerWorkDTOS.volunteerWorkPlace}</a>
                                                </span>
                                            </dd>
                                            <dt class="first_dt">
                                                <a style="color: rgb(61, 61, 61);">${volunteerWorkDTOS.volunteerWorkTitle}</a>
                                            </dt>
                                            <dd class="second_dd">${volunteerWorkDTOS.volunteerWorkContent}</dd>
                                        </dl>
                                        <div class="money">
                                            <div>
                                                <span class="money_achievement"${volunteerWorkDTOS.volunteerWorkTime}>
                                                </span>
                                                <span class="money_money">
                                                    <em style="font-style: normal;">${volunteerWorkDTOS.volunteerWorkRegisterAgency}</em>
                                                </span>
                                            </div>
                                            <span class="money_span">${volunteerWorkDTOS.volunteerWorkStartDate.substring(0,10)}</span>
                                            <span class="money_span">${volunteerWorkDTOS.volunteerWorkEndDate.substring(0,10)}</span>
                                        </div>
                                        <!-- <div class="line"></div> -->
                                    </div>
                                </div>
                            </div>
                        </div>
            `;
    $div.append(text);
    });

// 셀렉트 박스 변경 이벤트
// $('#location-select').change(function() {
//     var keyword = $(this).val();
//
//     $.ajax({
//         url: '/work-list', // 컨트롤러의 URL
//         type: 'GET',
//         data: {
//             keyword: keyword
//         },
//         success: function(result) {
//             // 요청 성공 시 처리할 코드
//         },
//         error: function(xhr, status, error) {
//             // 요청 실패 시 처리할 코드
//         }
//     });
// });
// list(page, keyword);

// function list(page, keyword) {
//     console.log(list + "들어옴");
//     $.ajax({
//         url: '/volunteers/work-list',
//         type: 'get',
//         data: { page: page, keyword: keyword },
//         success: function (list) {
//             console.log(list.content);
//             $div.append(listText(list));
//             // $(".count").text(list.content.length)
//
//
//         }
//     });
//
// }


// 리스트 갯수만큼
// function listText(list) {
//     console.log(list.content)
//     let volunteerDTOS = list.content
//     let text='';
//     console.log('.volunteerDTOS');
//
//     volunteerDTOS.forEach((volunteerDTO, i) => {
//         text += `
//                  <div class="first_content">
//                             <div></div>
//                             <div class="project_card">
//                                 <div></div>
//                                 <div class="project_card_div" id="${i}">
//                                     <div class="project_card_img">
//                                         <div class="image_wrapper">
//                                             <a href="work-detail/${volunteerDTO.id}">
//                        `;
//
//         if(volunteerDTO.fileDTOS == null || volunteerDTO.fileDTOS == undefined) {
//             text += `
//                                     <img style=" width: 238px; height: 238px;" src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> `;
//         } else {
//             for(let j = 0; j <volunteerDTO.fileDTOS.length; j++) {
//                 if (volunteerDTO.fileDTOS[j].fileType === "REPRESENTATION") {
//                     text += ` <img src="/file/display?fileName=${volunteerDTO.fileDTOS[j].filePath}/${volunteerDTO.fileDTOS[j].fileUuid}_${volunteerDTO.fileDTOS[j].fileName}">`;
//                 }
//             }
//         }
//
//         text +=  `
//
//                                                 </a>
//                                             <div class="like">
//                                                 <button class="like_button">
//                                                     <!-- <span>좋아요</span> -->
//                                                 </button>
//                                             </div>
//                                         </div>
//                                     </div>
//                                     <div class="project_name">
//                                         <dl class="content_dl">
//                                             <dd class="first_dd">
//                                                 <span>
//                                                     <a class="first_dd_a">${volunteerDTO.volunteerWorkCategory}</a>
//                                                 </span>
//                                                 <span>
//                                                     <a class="first_dd_a_a">${volunteerDTO.volunteerWorkPlace}</a>
//                                                 </span>
//                                             </dd>
//                                             <dt class="first_dt">
//                                                 <a style="color: rgb(61, 61, 61);">${volunteerDTO.volunteerWorkTitle}</a>
//                                             </dt>
//                                             <dd class="second_dd">${volunteerDTO.volunteerWorkContent}</dd>
//                                         </dl>
//                                         <div class="money">
//                                             <div>
//                                                 <span class="money_achievement"${volunteerDTO.volunteerWorkTime}>
//                                                 </span>
//                                                 <span class="money_money">
//                                                     <em style="font-style: normal;">${volunteerDTO.volunteerWorkRegisterAgency}</em>
//                                                 </span>
//                                             </div>
//                                             <span class="money_span">${volunteerDTO.volunteerWorkStartDate.substring(0,10)}</span>
//                                             <span class="money_span">${volunteerDTO.volunteerWorkEndDate.substring(0,10)}</span>
//                                         </div>
//                                         <!-- <div class="line"></div> -->
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>
//             `;
//
//     });
//     return text;
//
// }

// 스크롤 이벤트(무한 스크롤)
// $(window).scroll(function() {
//     if ($(window).scrollTop() == $(document).height() - $(window).height()) {
//         page++;
//         list(page, keyword);
//     }
// });