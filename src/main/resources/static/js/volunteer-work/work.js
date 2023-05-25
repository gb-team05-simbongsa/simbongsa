const $div = $('.real_content');
let page = 0;
let keyword = '';

$(document).ready(function() {
    $('#location-select').change(function() {
        $('#search-form').submit();
    });
});


console.log(volunteerWorkDTOS);

volunteerWorkDTOS.forEach((volunteerWorkDTOS, i) => {
    let fileDTO = volunteerWorkDTOS.fileDTO;
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

        if(fileDTO == null || fileDTO == undefined) {
            text += `
                                    <img style=" width: 238px; height: 238px;" src=""> `;

        } else {

            text += `<img src="/file/display?fileName=${fileDTO.filePath}" style=" width: 238px; height: 238px;">`;
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
