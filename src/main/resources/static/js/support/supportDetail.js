let total;
// 끝 페이지
let endPage;
// 시작페이지
let startPage;
// 참여내역 리스트
const $list = $('.history-list');
// 후원 요청 ID
const contentId = supportRequestDTO.id;
let goPage = 0;
// contentId와 page를 인자로 받아 리스트를 가져오는 함수
function getList(contentId, page) {
    adminService.getDetailList("/support/attend-member", contentId, page,function(results) {
        let supportDTOS = results.content;

        let text = ``;

        supportDTOS.forEach(support => {

            text += `
                <li class="history-item">
                    <div class="history-card">
                        <span class="history-card-date">${support.memberDTO.memberRank}</span>
                        <strong class="history-card-name">${support.memberDTO.memberName}</strong>
                        <span class="history-amount">
                            <span class="number">${support.supportPrice}</span>
                            석 참여
                        </span>
                    </div>
                </li>
            `;
        });
        $list.html(text);

        // 페이징 처리
        const $pagination = $(".paging-modal");
        // 비워줌
        $pagination.empty();

        const maxDisplayedPages = 5; // 한 번에 표시할 페이지 수
        total = results.totalElements;

        endPage = Math.ceil( ++goPage / 5.0) * maxDisplayedPages;
        startPage = endPage - 4; // 시작 페이지 번호

        let listSize = total < 5 ? total : 5;
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

// 페이지 로딩 시 getList 호출
getList(contentId, goPage);
$(".paging-modal").on("click", ".changePage-modal", function(e){
    e.preventDefault();
    $('.append-div').empty();
    $(".paging-modal").empty();
    const targetPage = $(this).text();

    if (targetPage == '<') {
        goPage = startPage - 6;
    } else if (targetPage == '>') {
        goPage = endPage;
    } else {
        goPage = parseInt(targetPage) - 1;
    }

    getList(contentId, goPage);
});

// supportRequestDTO의 파일 목록을 가져와서 각 파일에 대한 이미지 썸네일과 확대된 이미지를 표시

// supportRequestDTO의 파일 부분
let files = supportRequestDTO.fileDTOS;
files.forEach((file, i) =>{
    let text = ' ';
        text = `
                <li class="on">
                    <button href="#">
                `;
                    // 파일이 없는 경우 미리지정된 이미지를 썸네일로 표시
                    if(file == null || file == undefined) {
                        text = `
                                <img style=" width: 238px; height: 238px;" class="img-div" src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> `;
                    } else {
                                text += ` <img class="img-div" src="/file/display?fileName=${file.filePath}" style="width: 100%; height: 100%">`;
                            }
        `
                    </button>
                </li>
            `;

    let expandImg = `
        <img id="expandedImg" src="/file/display?fileName=${files[0].filePath}" style="cursor:pointer; width:624px; height:351px">
    `;
    // 확대된 이미지를 표시할 요소에 삽입
    $('.view').html(expandImg);
    $('.thumbnail-list ul').append(text);
});
// 썸네일 버튼
const $button = $('.img-div');

/* 첫 페이지 로딩될 때 첫번째 img 띄워주기 */
let $firstImage = $('.img-div').attr("src");
$("#expandedImg").attr("src", $firstImage);

console.log($button)
$button.on('click', function(){

    let $imgUrl = $(this).attr("src").replace(/^url\(['"](.+)['"]\)/, '$1');
    let $image = $imgUrl.replace("/file/display?fileName=${file.filePath}");

    $("#expandedImg").attr("src", $image);
    $("#expandedImg").css('width', '100%');
});
