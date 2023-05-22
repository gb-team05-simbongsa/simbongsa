/* 목록 */
myFundings.forEach((myFunding, i) => {
    let text = ''; // 초기화된 빈 문자열로 시작해야 합니다.

    text += `
        <div class="my-support-one-wrap">
            <div class="my-support-one-place">
                <div class="my-support-one">
                    <div class="my-support-img-wrap">
                        <div class="my-support-img">
                            <a href="" class="go-support-detail-img">
                        `
    if (myFunding.fileDTOS == null || myFunding.fileDTOS == undefined) {
        text +=
            `
            <img class="my-support-img" src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> \`;
            `
    }
    else {
        for(let j = 0; j < myFunding.fileDTOS.length; j++) {
            text +=

                ` 
                    <img class="my-support-img" src="/file/display?fileName=${myFunding.fileDTOS[j].filePath + '/' + myFunding.fileDTOS[j].fileUuid + '_' + myFunding.fileDTOS[j].fileName}">
                    `;
        }
    }
                        text +=
                            `</a>
                        </div>
                    </div>
                    <div class="my-support-explain">
                        <div class="my-support-title">
                            <a href="">${myFunding.fundingTitle}</a>
                        </div>
                        <div class="my-support-content">${myFunding.fundingShortTitle}</div>
                    </div>
                </div>
            </div>
        </div>
    `;

    $('.my-support-wrap').append(text);
});
