const $div = $('.listWrapper');
let page = 0;
list(page);


function list(page) {

    $.ajax({
        url: '/volunteers/work-list',
        type: 'get',
        data: {page: page},
        success: function (list) {
            console.log(list.content);
            $div.append(listText(list));
              // $(".count").text(list.content.length)


        }
    });

}


// 리스트 갯수만큼
    function listText(list) {

    let fundingDTOS = list.content
    let text='';

        fundingDTOS.forEach((fundingDTO, i) => {
            text += `
                 <div class="listByinfinity" id="${i}">
                                <div></div>
                                <div class="project-card project-card">
                                    <div></div>
                                    <div class="CardOpx link-wrapper">
                                        <div class="imgStyleBox">
                                            <div class="image-wrapper">
                                                <a href="">
                       `;

                if(fundingDTO.fileDTOS == null || fundingDTO.fileDTOS == undefined) {
                                text += `
                                    <img src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> `;
                                } else {
                                    for(let j = 0; j <fundingDTO.fileDTOS.length; j++) {
                                        if (fundingDTO.fileDTOS[j].fileType === "REPRESENTATION") {
                                            text += ` <img src="/file/display?fileName=${fundingDTO.fileDTOS[j].filePath}/${diaryDTO.fileDTOS[j].fileUuid}_${fundingDTO.fileDTOS[j].fileName}">`;
                                        }
                                    }
                }

                text +=  `

                                                </a>
                                            </div>
                                        </div>
                                        <div class=" styleByInfoBox">
                                            <dl>
                                                <dd class="project-sub-info">
                                                    <span><a href="">${fundingDTO.fundingCategory}</a></span>
                                                    <span><a href="">${fundingDTO.fundingTitle}</a></span>
                                                </dd>
                                                <dt><a href="">${fundingDTO.fundingShortTitle}</a></dt>
                                                <dd class="project-desc">${fundingDTO.fundingSummary}</dd>
                                            </dl>
                                            <div class="projectCard2 projectCard1">
                                                <div>
                                                    <span class="fundingState">${fundingDTO.fundingPercent}%</span>
                                                    <input class="inputHidden" type="hidden" value="">
                                                    <span class="funding-amount"><em>${fundingDTO.fundingCurrentPrice}</em>원</span>
                                                </div>
<!--                                                <span class="rest-day">20일 남음</span>-->
                                            </div>
                                                <div class="percentByRed">
                                                 ${fundingDTO.fundingPercent}
                                                    <div class="percentBar">${fundingDTO.fundingPercent}</div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
            `;

        });
        return text;

    }

// 스크롤 이벤트(무한 스크롤)
$(window).scroll(
    function() {
        if ($(window).scrollTop() == $(document).height() - $(window).height()) {
            page++;
            list(page);
        }
    }
);
