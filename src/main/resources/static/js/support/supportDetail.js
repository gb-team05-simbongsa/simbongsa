/*$('#comment-id-button').click(function(){
    console.log("클릭됨");
    $('#comment-id-button').attr('aria-selected', 'true');
    $('#join-id-button').attr('aria-selected', 'false');
    $('#comment-tabpanel').attr('aria-hidden', 'false');
    $('#history-tabpanel').attr('aria-hidden', 'true');
})

$('#join-id-button').click(function(){
    console.log("클릭됨");
    $('#comment-id-button').attr('aria-selected', 'false');
    $('#join-id-button').attr('aria-selected', 'true');
    $('#comment-tabpanel').attr('aria-hidden', 'true');
    $('#history-tabpanel').attr('aria-hidden', 'false');
})*/

const $button = $('.img-div');

/* 첫 페이지 로딩될 때 첫번째 img 띄워주기 */
let $firstImage = $('.img-div').attr("src");
$("#expandedImg").attr("src", $firstImage);

$button.on('click', function(){
    console.log("클릭됨");
    let $imgUrl = $(this).attr("src").replace(/^url\(['"](.+)['"]\)/, '$1');
    let $image = $imgUrl.replace("http://127.0.0.1:5500","");
    console.log($image);


    $("#expandedImg").attr("src", $image);
    $("#expandedImg").css('width', '100%');
});

/*여기서 부터*/
let total;
let endPage;
let startPage;
const $list = $('.history-list');
const contentId = supportRequestDTO.id;
let goPage = 0;
function getList(contentId, page) {
    adminService.getDetailList("/support/attend-member", contentId, page,function(results) {
        let supportDTOS = results.content;

        let text = ``;

        supportDTOS.forEach(support => {

            text += `
                <li class="history-item">
                    <div class="history-card">
                        <span class="history-card-date">${support.supportPrice}</span>
                        <strong class="history-card-name">${support.memberDTO.memberName}</strong>
                        <span class="history-amount">
                            <span class="number">${support.supportPrice}</span>
                            원 참여
                        </span>
                    </div>
                </li>
            `;
        });
        $list.html(text);

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
getList(contentId, goPage);
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

console.log(supportRequestDTO);
console.log();
let file = supportRequestDTO.fileDTOS;
file.forEach((file, i) =>{
    let text = ' ';
        text = `
                <li class="on">
                    <button href="#">
                `
                    if(file == null || file == undefined) {
                        text = `
                                <img style=" width: 238px; height: 238px;" src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1"> `;
                    } else {
                                text += ` <img src="/file/display?fileName=${file.filePath}">`;
                            }
        `
                    </button>
                </li>
            `;
    $('.thumbnail-list ul').append(text);
});

let text = ' ';
    text = `
     <img id="expandedImg" src="/file/display?fileName=${file[0].filePath}" style="cursor:pointer; width:624 height=351">
    `;
$('.view').html(text);
console.log(file[0]);