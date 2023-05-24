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
                                            <a href="/volunteer-work/work-detail/${volunteerWorkDTOS.id}">
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
