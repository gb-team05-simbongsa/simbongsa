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

const $list = $('.history-list');
let page = 0;

listService = (function(){
    function list(page, callback){
        $.ajax({
            url : '/supports/attend-list',
            type: 'get',
            data: {page: page},
            success: function(list){
                if(callback){
                    console.log("들어옴");
                    callback(list);
                }
            }
        });
    }
    return {
        list: list
    }
})();

getList(page);
function getList(page) {
    console.log("getList 들어옴");
    console.log(page);
    console.log(list);
    listService.list(page, function(list) { // 수정: page를 인자로 전달
        listText(list);
        displayPagination(list.totalPages);
    });
}
$('.paging-wrapper').on("click", ".page", function(e){
    e.preventDefault();
    console.log("page 들어옴");
    $list.empty();
    const targetPage = $(this).text();
    if($(this).hasClass("prev")){
        if(page>0){
            page--;
        }
    }else if($(this).hasClass("next")){
        page++;
    }else{
        page=parseInt(targetPage) - 1;
    }
    getList(page);
});


function displayPagination(totalPages) {
    const $pagination = $(".pages-wrapper");
    $pagination.empty();

    const maxDisplayedPages = 10; // 한 번에 표시할 페이지 수
    const startPage = Math.floor(page / maxDisplayedPages) * maxDisplayedPages; // 시작 페이지 번호

    if (page > 0) {
        $pagination.append("<div class='arrow-left page'></div>");
    }

    for (let i = startPage; i < startPage + maxDisplayedPages && i < totalPages; i++) {
        if (i === page) {
            $pagination.append("<div class='page active'>" + (i + 1) + "</div>");
        } else {
            $pagination.append("<div class='page'>" + (i + 1) + "</div>");
        }
    }

    if (page < totalPages - 1) {
        $pagination.append("<div class='arrow-right page'></div>");
    }
}


function listText(list) {
    console.log("list text 들어옴");
    let supportDTOS = list.content;
    $(supportDTOS).each((i, support) => {
        console.log("text 들어옴");
        var text = "";
        text += `
            <li class="history-item">
                                        <div class="history-card">
                                            <span class="history-card-date">${supportDTOS.createDate}</span>
                                            <strong class="history-card-name">${supportDTOs.memberName}</strong>
                                            <span class="history-amount">
                                                <span class="number">${supportDTOs.supportPrice}</span>
                                                원 참여
                                            </span>
                                        </div>
                                    </li>
        `;
        $list.append(text);
    });
}
