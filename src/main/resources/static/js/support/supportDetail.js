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







//
// let text =`
//                                     <li class="history-item">
//                                         <div class="history-card">
//                                             <span class="history-card-date">2023.04.27 00:22</span>
//                                             <strong class="history-card-name">wish</strong>
//                                             <span class="history-amount">
//                                                 <span class="number">1,800</span>
//                                                 원 참여
//                                             </span>
//                                         </div>
//                                     </li>`
//
// let page = `
//                             <div class="paging-wrapper">
//                                     <ul class="paging-wrap">
//                                         <li class="paging-prev-wrap" title="이전 페이지">
//                                             <div class="prev">
//                                                 <svg viewBox="0 0 48 48">
//                                                     <path fill-rule="evenodd" clip-rule="evenodd" d="M32.8912 45.3014L12 23.913L32.8912 2.52471C33.5866 1.8251 34.7804 1.8251 35.4777 2.52471C36.1741 3.22333 36.1741 4.42368 35.4777 5.22224L17.1731 23.913L35.4777 42.7018C36.1741 43.4024 36.1741 44.6018 35.4777 45.4013C35.0788 45.8011 34.6819 46 34.1845 46C33.7855 45.9011 33.2881 45.7002 32.8912 45.3014Z"></path>
//                                                 </svg>
//                                             </div>
//                                         </li>
//                                         <li title="1" class="paging-item-select" tabindex="0">
//                                             <a rel="nofollow">1</a>
//                                         </li>
//                                         <li title="2" class="paging-item" tabindex="0">
//                                             <a rel="nofollow">2</a>
//                                         </li>
//                                         <li title="다음 페이지" tabindex="0" class="paging-next" aria-disabled="false">
//                                             <div class="next">
//                                                 <svg viewBox="0 0 48 48">
//                                                     <path fill-rule="evenodd" clip-rule="evenodd" d="M13.9071 46C13.4118 46 12.9164 45.8001 12.6192 45.4003C11.9257 44.7007 11.9257 43.5014 12.6192 42.7019L30.8493 24.0125L12.5201 5.22317C11.8266 4.52357 11.8266 3.32425 12.5201 2.5247C13.2136 1.8251 14.3034 1.8251 15.096 2.5247L36 24.0125L15.195 45.4003C14.7988 45.8001 14.3034 46 13.9071 46Z"></path>
//                                                 </svg>
//                                             </div>
//                                         </li>
//                                     </ul>
//                                 </div>
// `
//
// const $list = $('.history-list');
// let page = 0;
//
// listService = (function(){
//     function list(page, callback){
//         $.ajax({
//             url : '/supports/attend-list',
//             type: 'get',
//             data: {page: page},
//             success: function(list){
//                 if(callback){
//                     console.log("들어옴");
//                     callback(list);
//                 }
//             }
//         });
//     }
//     return {
//         list: list
//     }
// })();
//
// getList(page);
// function getList(page) {
//     console.log("getList 들어옴");
//     console.log(page);
//     console.log(list);
//     listService.list(page, function(list) { // 수정: page를 인자로 전달
//         listText(list);
//         displayPagination(list.totalPages);
//     });
// }
// $('.paging-wrapper').on("click", ".page", function(e){
//     e.preventDefault();
//     console.log("page 들어옴");
//     $list.empty();
//     const targetPage = $(this).text();
//     if($(this).hasClass("prev")){
//         if(page>0){
//             page--;
//         }
//     }else if($(this).hasClass("next")){
//         page++;
//     }else{
//         page=parseInt(targetPage) - 1;
//     }
//     getList(page);
// });
//
//
// function displayPagination(totalPages) {
//     const $pagination = $(".pages-wrapper");
//     $pagination.empty();
//
//     const maxDisplayedPages = 10; // 한 번에 표시할 페이지 수
//     const startPage = Math.floor(page / maxDisplayedPages) * maxDisplayedPages; // 시작 페이지 번호
//
//     if (page > 0) {
//         $pagination.append("<div class='arrow-left page'></div>");
//     }
//
//     for (let i = startPage; i < startPage + maxDisplayedPages && i < totalPages; i++) {
//         if (i === page) {
//             $pagination.append("<div class='page active'>" + (i + 1) + "</div>");
//         } else {
//             $pagination.append("<div class='page'>" + (i + 1) + "</div>");
//         }
//     }
//
//     if (page < totalPages - 1) {
//         $pagination.append("<div class='arrow-right page'></div>");
//     }
// }
//
//
// function listText(list) {
//     console.log("list text 들어옴");
//     let supportDTOS = list.content;
//     $(supportDTOS).each((i, support) => {
//         console.log("text 들어옴");
//         var text = "";
//         text += `
//             <li class="history-item">
//                                         <div class="history-card">
//                                             <span class="history-card-date">${supportDTOS.createDate}</span>
//                                             <strong class="history-card-name">${supportDTOs.memberName}</strong>
//                                             <span class="history-amount">
//                                                 <span class="number">${supportDTOs.supportPrice}</span>
//                                                 원 참여
//                                             </span>
//                                         </div>
//                                     </li>
//         `;
//         $list.append(text);
//     });
// }




/*여기서 부터*/
// let total;
// let endPage;
// let startPage;
// const $list = $('.history-list');
// const contentId = supportRequestDTO.id;
// function getList(contentId, page) {
//     adminService.getDetailList("/support/attend-member", contentId, page,function(results) {
//         let supportRequestDTO = results.content;
//
//         let text = ``;
//
//         supportRequestDTO.forEach(supportRequest => {
//
//             text += `
//             <li class="history-item">
//                                         <div class="history-card">
//                                             <span class="history-card-date">${supportRequest.createDate}</span>
//                                             <strong class="history-card-name">${supportRequest.memberName}</strong>
//                                             <span class="history-amount">
//                                                 <span class="number">${supportRequest.supportPrice}</span>
//                                                 원 참여
//                                             </span>
//                                         </div>
//                                     </li>
//         `;
//         });
//         $list.append(text);
//
//         //여기가 문제
//         const $pagination = $(".paging-modal");
//         $pagination.empty();
//
//         const maxDisplayedPages = 5; // 한 번에 표시할 페이지 수
//         total = results.totalElements;
//
//         endPage = Math.ceil( ++goPage / 5.0) * maxDisplayedPages;
//         startPage = endPage - 4; // 시작 페이지 번호
//
//         let listSize = total < 5 ? total : 5;
//         // if(total < 5) {
//         //     listSize = total;
//         // } else {
//         //     listSize = 5;
//         // }
//         // listSize = supportRequestDTOS.length;
//         const realEnd = Math.ceil(Math.ceil(total * 1.0) / listSize);
//
//         if(realEnd < endPage) {
//             endPage = realEnd;
//         }
//
//         if (startPage > 1) {
//             $pagination.prepend(`<a class="changePage-modal arrow-right" style="color: black"><code><</code></a>`);
//         }
//
//         for (let i = startPage; i <= endPage; i++) {
//             if (i === goPage) {
//                 $pagination.append(`<code id="currentPage-modal">` + goPage +`</code>`);
//             } else {
//                 $pagination.append(`<a class="changePage-modal count" style="color: black"><code>` + i + `</code></a>`);
//             }
//         }
//
//         if (endPage < realEnd) {
//             $pagination.append(`<a class="changePage-modal arrow-right" style="color: black"><code>></code></a>`);
//         }
//     });
// }
//
// $(".paging-modal").on("click", ".changePage-modal", function(e) {
//     e.preventDefault();
//     $('.append-div').empty();
//     $(".paging-modal").empty();
//     const targetPage = $(this).text();
//
//     if (targetPage == '<') {
//         console.log(startPage)
//         goPage = startPage - 6;
//     } else if (targetPage == '>') {
//         goPage = endPage;
//     } else {
//         goPage = parseInt(targetPage) - 1;
//     }
//     // if ($(this).hasClass("arrow-left")) {
//     //     if (goPage > 0) {
//     //         goPage--;
//     //     }
//     // } else if ($(this).hasClass("arrow-right")) {
//     //     goPage++;
//     // } else {
//     //     goPage = parseInt(targetPage) - 1;
//     // }
//
//     getList(contentId, goPage);
// });
//
//
// let total;
// let endPage;
// let startPage;
// const $list = $('.history-list');
// const contentId = supportRequestDTO.id;
// function getList(contentId, page) {
//     adminService.getDetailList("/support/attend-member", contentId, page,function(results) {
//         let supportRequestDTO = results.content;
//
//         let text = ``;
//
//         supportRequestDTO.forEach(supportRequest => {
//
//             text += `
//             <li class="history-item">
//                                         <div class="history-card">
//                                             <span class="history-card-date">${supportRequest.createDate}</span>
//                                             <strong class="history-card-name">${supportRequest.memberName}</strong>
//                                             <span class="history-amount">
//                                                 <span class="number">${supportRequest.supportPrice}</span>
//                                                 원 참여
//                                             </span>
//                                         </div>
//                                     </li>
//         `;
//         });
//         $list.append(text);
//
//         //여기가 문제
//         const $pagination = $(".paging-modal");
//         $pagination.empty();
//
//         const maxDisplayedPages = 5; // 한 번에 표시할 페이지 수
//         total = results.totalElements;
//
//         endPage = Math.ceil( ++goPage / 5.0) * maxDisplayedPages;
//         startPage = endPage - 4; // 시작 페이지 번호
//
//         let listSize = total < 5 ? total : 5;
//         // if(total < 5) {
//         //     listSize = total;
//         // } else {
//         //     listSize = 5;
//         // }
//         // listSize = supportRequestDTOS.length;
//         const realEnd = Math.ceil(Math.ceil(total * 1.0) / listSize);
//
//         if(realEnd < endPage) {
//             endPage = realEnd;
//         }
//
//         if (startPage > 1) {
//             $pagination.prepend(`<a class="changePage-modal arrow-right" style="color: black"><code><</code></a>`);
//         }
//
//         for (let i = startPage; i <= endPage; i++) {
//             if (i === goPage) {
//                 $pagination.append(`<code id="currentPage-modal">` + goPage +`</code>`);
//             } else {
//                 $pagination.append(`<a class="changePage-modal count" style="color: black"><code>` + i + `</code></a>`);
//             }
//         }
//
//         if (endPage < realEnd) {
//             $pagination.append(`<a class="changePage-modal arrow-right" style="color: black"><code>></code></a>`);
//         }
//     });
// }
//
// $(".paging-modal").on("click", ".changePage-modal", function(e) {
//     e.preventDefault();
//     $('.append-div').empty();
//     $(".paging-modal").empty();
//     const targetPage = $(this).text();
//
//     if (targetPage == '<') {
//         console.log(startPage)
//         goPage = startPage - 6;
//     } else if (targetPage == '>') {
//         goPage = endPage;
//     } else {
//         goPage = parseInt(targetPage) - 1;
//     }
//     // if ($(this).hasClass("arrow-left")) {
//     //     if (goPage > 0) {
//     //         goPage--;
//     //     }
//     // } else if ($(this).hasClass("arrow-right")) {
//     //     goPage++;
//     // } else {
//     //     goPage = parseInt(targetPage) - 1;
//     // }
//
//     getList(contentId, goPage);
// });


//




let total;
let endPage;
let startPage;
const $list = $('.history-list');
const id = supportRequestDTO.id;

function doAjax(url, data, successCallback, errorCallback) {
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        success: successCallback,
        error: errorCallback
    });
}

function getList(id, page) {
    const url = '/support/attend-member';
    const data = {
        id: id,
        page: page
    };

    doAjax(url, data, function(results) {
        let supportRequestDTO = results.content;

        let text = ``;

        supportRequestDTO.forEach(supportRequest => {

            text += `
        <li class="history-item">
          <div class="history-card">
            <span class="history-card-date">${supportRequest.createDate}</span>
            <strong class="history-card-name">${supportRequest.memberName}</strong>
            <span class="history-amount">
              <span class="number">${supportRequest.supportPrice}</span>
              원 참여
            </span>
          </div>
        </li>
      `;
        });
        $list.append(text);

        const $pagination = $(".paging-modal");
        $pagination.empty();

        const maxDisplayedPages = 5;
        total = results.totalElements;

        endPage = Math.ceil(++goPage / 5.0) * maxDisplayedPages;
        startPage = endPage - 4;

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
                $pagination.append(`<code id="currentPage-modal">${goPage}</code>`);
            } else {
                $pagination.append(`<a class="changePage-modal count" style="color: black"><code>${i}</code></a>`);
            }
        }

        if (endPage < realEnd) {
            $pagination.append(`<a class="changePage-modal arrow-right" style="color: black"><code>></code></a>`);
        }
    }, function(error) {
        console.error('AJAX request failed:', error);
    });
}

$(".paging-modal").on("click", ".changePage-modal", function(e) {
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