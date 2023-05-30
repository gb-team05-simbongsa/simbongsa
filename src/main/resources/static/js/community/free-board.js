
/* 눌렀을 때 색 변환 - 활동후기, 자유*/
const headerTitle1 = document.querySelector('.header-title1');
const headerTitle2 = document.querySelector('.header-title2');

headerTitle1.addEventListener('click', () => {
    headerTitle1.style.color = '#1d1d1e';
    headerTitle1.style.fill = '#1d1d1e';
    headerTitle2.style.color = '#b9b9bb';
    headerTitle2.style.fill = '#b9b9bb';
});

headerTitle2.addEventListener('click', () => {
    headerTitle2.style.color = '#1d1d1e';
    headerTitle2.style.fill = '#1d1d1e';
    headerTitle1.style.color = '#b9b9bb';
    headerTitle1.style.fill = '#b9b9bb';
});

/* 눌렀을 때 색 변환 - 최신글, 인기글*/
const categoriNew = document.querySelector('.categori-new');
const categoriBest = document.querySelector('.categori-best');

const userTimeElements = document.querySelectorAll('.user-time');

userTimeElements.forEach((userTimeElement) => {
    const userTime = new Date(userTimeElement.textContent);
    const currentTime = new Date();
    const timeDifferenceInMs = currentTime - userTime;
    const timeDifferenceInDays = Math.floor(timeDifferenceInMs / (1000 * 60 * 60 * 24));

    let formattedTime;

    if (timeDifferenceInDays === 0) {
        formattedTime = '오늘';
    } else if (timeDifferenceInDays === 1) {
        formattedTime = '어제';
    } else {
        formattedTime = `${timeDifferenceInDays}일 전`;
    }

    userTimeElement.textContent = formattedTime;
});


// categoriNew.addEventListener('click', () => {
//     categoriNew.style.color = '#1d1d1e';
//     categoriNew.style.fill = '#1d1d1e';
//     categoriBest.style.color = '#8a8a8b';
//     categoriBest.style.fill = '#8a8a8b';
//
//     $.ajax({
//         url: "/community/free-board/newList",
//         type: "get",
//         success: function (result) {
//             let text = ``;
//             console.log("몇번")
//             result.forEach(freeboards => {
//
//                 text += `
//                             <a href="/community/free-detail/${freeboards.id}">
//                                 <li class="contant-list-container" style="margin-top: 10px">
//                                     <div>
//                                         <button class="contant-btn">
//                                             <div class="contant-title-container">
//                                                 <div class="contant-title-wrapper">
//                                                     <h2 class="contant-title">${freeboards.boardTitle}</h2>
//                                                     <p class="contant-contant">${freeboards.boardContent}</p>
//                                                 </div>
//                                                 <div class="contant-img">
//                                                     <span class="contant-img-span">
//                                                         <img class="contant-image" src="https://steadio.imgix.net/creator-posts/079d1a28-343d-4f6a-8889-e9328964aafd/creatorPostImage/269f44f6-9d34-47bb-83a2-7d99d86a5280.jpg?auto=format%2Ccompress&fit=crop&h=420&lossless=true&w=680">
//                                                     </span>
//                                                 </div>
//                                             </div>
//                                             <div class="contant-user">
//                                                 <div class="user-profile">
//                                                     <span class="user-name">${freeboards.memberDTO.memberName}</span>
//                                                     <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
//                                                     <span class="user-time">${date()}</span>
//                                                     <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
//                                                     <span class="user-see">댓글수 ${freeboards.replyCount}</span>
//                                                 </div>
//                                             </div>
//                                         </button>
//                                     </div>
//                                 </li>
//                             </a>
//                         `;
//             });
//             $('.contant').html(text);
//         }
//     });
// });

// categoriBest.addEventListener('click', () => {
//     categoriBest.style.color = '#1d1d1e';
//     categoriBest.style.fill = '#1d1d1e';
//     categoriNew.style.color = '#8a8a8b';
//     categoriNew.style.fill = '#8a8a8b';
//
//     $.ajax({
//         url: "/community/free-board/likes",
//         type: "get",
//         success: function (results) {
//             let text = ``;
//             if (Array.isArray(results)) {
//                 results.forEach(freeboard => {
//
//                     text += `
//                         <a href="/community/free-detail/${freeboard.id}">
//                             <li class="contant-list-container" style="margin-top: 10px">
//                                 <div>
//                                     <button class="contant-btn">
//                                         <div class="contant-title-container">
//                                             <div class="contant-title-wrapper">
//                                                 <h2 class="contant-title">${freeboard.boardTitle}</h2>
//                                                 <p class="contant-contant">${freeboard.boardContent}</p>
//                                             </div>
//                                             <div class="contant-img">
//                                                 <span class="contant-img-span">
//                                                     <img class="contant-image" src="https://steadio.imgix.net/creator-posts/079d1a28-343d-4f6a-8889-e9328964aafd/creatorPostImage/269f44f6-9d34-47bb-83a2-7d99d86a5280.jpg?auto=format%2Ccompress&fit=crop&h=420&lossless=true&w=680">
//                                                 </span>
//                                             </div>
//                                         </div>
//                                         <div class="contant-user">
//                                             <div class="user-profile">
//                                                 <span class="user-name">${freeboard.memberDTO.memberName}</span>
//                                                 <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
//                                                 <span class="user-time">${date()}</span>
//                                                 <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
//                                                 <span class="user-see">댓글수 ${freeboard.replyCount}</span>
//                                             </div>
//                                         </div>
//                                     </button>
//                                 </div>
//                             </li>
//                         </a>
//                     `;
//                 });
//                 $('.contant').html(text);
//             }
//         }
//     });
// });

const $div = $('.contant');
let page = 0;
list(page);
let $categoriContent = $('.categori-content');
console.log($categoriContent);
$categoriContent.on('click', function() {
    if($(this).text() == '최신글'){
        console.log('ghkrdls')
        categoriNew.style.color = '#1d1d1e';
        categoriNew.style.fill = '#1d1d1e';
        categoriBest.style.color = '#8a8a8b';
        categoriBest.style.fill = '#8a8a8b';
        page = 0;
        $.ajax({
            url: '/community/free-board/newList',
            type: 'get',
            data: {page: page},
            success: function (list) {
                console.log(list.content);
                $div.empty();

                $div.append(listText(list));
            }
        });
    }else if($(this).text() == '인기글'){
        console.log('ghdsfkjahksdhfjakw')
        categoriBest.style.color = '#1d1d1e';
        categoriBest.style.fill = '#1d1d1e';
        categoriNew.style.color = '#8a8a8b';
        categoriNew.style.fill = '#8a8a8b';
        page = 0;
        $.ajax({
            url: '/community/free-board/likes',
            type: 'get',
            data: {page: page},
            success: function (list) {
                console.log(list.content);
                $div.empty();

                $div.append(listText(list));
            }
        });
    }
})
function list(page) {
    let $url  = '';

    // if ($('.categori-best').text() == "최신순") {
    //     $url= '/community/free-board/newList';
    // } else if ($('.categori-best').text() == "인기순") {
    //     $url = '/community/free-board/likes';
    // }

    $.ajax({
        url: '/community/free-board/newList',
        type: 'get',
        data: {page: page},
        success: function (list) {
            console.log(list.content);
            $div.append(listText(list));
        }
    });
}
function listText(list){
    let freeBoardDTOS = list.content;
    let text = '';

    freeBoardDTOS.forEach((freeBoardDTO, i) => {
        text += `
             <a href="/community/free-detail/${freeBoardDTO.id}">
                <li class="contant-list-container" style="margin-top: 10px">
                    <div>
                        <button class="contant-btn">
                            <div class="contant-title-container">
                                <div class="contant-title-wrapper">
                                    <h2 class="contant-title">${freeBoardDTO.boardTitle}</h2>
                                    <p class="contant-contant">${freeBoardDTO.boardContent}</p>
                                </div>
                                <div class="contant-img">
                                    <span class="contant-img-span">
                                    `;

                                        if(freeBoardDTO.fileDTOS[0] == null || freeBoardDTO.fileDTOS[0] == undefined){
                                            text += `<img src="https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/007b6da4e73604f590568620636df9f45c1c385b/99ad89d91fbd1abe533280381a952c13901f41bf/4e17000d-8d19-4c6c-97a9-8772101c7f60.png?auto=format%2Ccompress&fit=crop&h=288&lossless=true&w=384&s=ccaf0c29a4a9c34a703c08e3ddef88d1">`;
                                        }else{
                                            text += `<img class="contant-image" src="/file/display?fileName=${freeBoardDTO.fileDTOS[0].filePath}">`
                                        }

                                   text += `
                                    </span>
                                </div>
                            </div>
                            <div class="contant-user">
                                <div class="user-profile">
                                    <span class="user-name">${freeBoardDTO.memberDTO.memberName}</span>
                                    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                    <span class="user-time"></span>
                                    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                    <span class="user-see">댓글수 ${freeBoardDTO.replyCount}</span>
                                </div>
                            </div>
                        </button>
                    </div>
                </li>
            </a>
        `;
    });
    return text;
}


$(window).scroll(
    function() {
        if ($(window).scrollTop() == $(document).height() - $(window).height()) {
            page++;
            list(page);
        }
    }
);



// let nextPage = 0;
// let checkStatus = false;
//
// // ajax로 게시글 목록 불러오기(10개씩 가져옴)
// const fetchData = () => {
//     let page = nextPage;
//     nextPage++;
//     console.log("nextPage: " + nextPage);
//     checkStatus = false;
//     $.ajax({
//         type: 'GET',
//         url: `/community/free-board/newList`,
//         success: function (result) {
//             setTimeout(function() { checkStatus = true; }, 500);
//             freeBoardList(result);
//         },
//         error: function (error) {
//             console.log('Error fetching data:', error);
//         }
//     });
// };

// 추가적인 데이터 불러오기
// const loadMoreData = () => {
//     if (checkStatus) {
//         fetchData();
//     }
// };

// 처음에 화면에 목록 뿌려주기
// $(document).ready(function () {
//     console.log("Ready 들어옴");
//     fetchData();
// });

// 무한 스크롤 이벤트
// $(window).scroll(function () {
//     if (Math.ceil($(window).scrollTop()) >= $(document).height() - $(window).height() - 50) {
//         loadMoreData();
//     }
// });


// 리뷰 게시물 목록 로드 함수
// function loadReviewBoardList(url, data) {
//     $.ajax({
//         type: "GET",
//         url: url,
//         data: data,
//         dataType: "json",
//         success: function (response) {
//             showList(response);
//             console.log(response.content.length);
//         },
//         error: function (error) {
//             console.log('Error loading review board list:', error);
//         }
//     });
// }

// 초기 페이지 로드
// var page = 0;
// loadReviewBoardList('/community/free-board/new', {page: page});

// 스크롤 이벤트 핸들러
// $(window).scroll(function () {
//     if ($(window).scrollTop() + $(window).height() > $(document).height() * 0.9) {
//         page++;
//         if ($('.categori-best').text() == "최신순") {
//             loadReviewBoardList('/community/free-board/newList', {page: page});
//         } else if ($('.categori-best').text() == "인기순") {
//             loadReviewBoardList('/community/free-board/likes', {page: page});
//         }
//
//         // 이전에 추가된 항목 수를 업데이트
//         previousItemCount = $('.contant').children('.contant-list-container').length;
//     }
// });







// freeBoardList.forEach(freeboard => {
//     let text;
//     console.log("번")
//     text = `
//         <a href="/community/free-detail/${freeboard.id}">
//             <li class="contant-list-container" style="margin-top: 10px">
//                 <div>
//                     <button class="contant-btn">
//                         <div class="contant-title-container">
//                             <div class="contant-title-wrapper">
//                                 <h2 class="contant-title">${freeboard.boardTitle}</h2>
//                                 <p class="contant-contant">${freeboard.boardContent}</p>
//                             </div>
//                             <div class="contant-img">
//                                     <span class="contant-img-span">
//                                         <img class="contant-image" src="https://steadio.imgix.net/creator-posts/079d1a28-343d-4f6a-8889-e9328964aafd/creatorPostImage/269f44f6-9d34-47bb-83a2-7d99d86a5280.jpg?auto=format%2Ccompress&fit=crop&h=420&lossless=true&w=680">
//                                     </span>
//                             </div>
//                         </div>
//                         <div class="contant-user">
//                             <div class="user-profile">
//                                 <span class="user-name">${freeboard.memberDTO.memberName}</span>
//                                 <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
//                                 <span class="user-time">${freeboard.createdDate}</span>
//                                 <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
//                                 <span class="user-see">댓글수 ${freeboard.replyCount}</span>
//                             </div>
//                         </div>
//                     </button>
//                 </div>
//             </li>
//         </a>
//     `;
//     $('.contant').append(text);
// })


/* 스크롤 */
// $(document).ready(function () {
//     // 스크롤 위치 감지
//     $(window).scroll(function () {
//         if ($(this).scrollTop() > 100) {
//             $('.scrollTop').fadeIn();
//         } else {
//             $('.scrollTop').fadeOut();
//         }
//     });
//
//     // 맨 위로 스크롤 이동
//     $('.scrollTop').click(function () {
//         $('html, body').animate({
//             scrollTop: 0
//         }, 800);
//         return false;
//     });
// });
