freeBoardList.forEach(freeboard => {
    let text;
    console.log("번")
    text = `
        <a href="/community/free-detail/${freeboard.id}">
            <li class="contant-list-container" style="margin-top: 10px">
                <div>
                    <button class="contant-btn">
                        <div class="contant-title-container">
                            <div class="contant-title-wrapper">
                                <h2 class="contant-title">${freeboard.boardTitle}</h2>
                                <p class="contant-contant">${freeboard.boardContent}</p>
                            </div>
                            <div class="contant-img">
                                    <span class="contant-img-span">
                                        <img class="contant-image" src="https://steadio.imgix.net/creator-posts/079d1a28-343d-4f6a-8889-e9328964aafd/creatorPostImage/269f44f6-9d34-47bb-83a2-7d99d86a5280.jpg?auto=format%2Ccompress&fit=crop&h=420&lossless=true&w=680">
                                    </span>
                            </div>
                        </div>
                        <div class="contant-user">
                            <div class="user-profile">
                                <span class="user-name">${freeboard.memberDTO.memberName}</span>
                                <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                <span class="user-time">${freeboard.createdDate}</span>
                                <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                <span class="user-see">댓글수 ${freeboard.freeBoardReplyCount}</span>
                            </div>
                        </div>
                    </button>
                </div>
            </li>
        </a>
    `;
    $('.contant').append(text);
})


/* 스크롤 */
$(document).ready(function() {
    // 스크롤 위치 감지
    $(window).scroll(function() {
      if ($(this).scrollTop() > 100) {
        $('.scrollTop').fadeIn();
      } else {
        $('.scrollTop').fadeOut();
      }
    });

    // 맨 위로 스크롤 이동
    $('.scrollTop').click(function() {
      $('html, body').animate({
        scrollTop: 0
      }, 800);
      return false;
    });
  });

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


categoriNew.addEventListener('click', () => {
    categoriNew.style.color = '#1d1d1e';
    categoriNew.style.fill = '#1d1d1e';
    categoriBest.style.color = '#8a8a8b';
    categoriBest.style.fill = '#8a8a8b';

     $.ajax({
         url: "/community/free-board/newList",
         type: "get",
         success: function(result) {
             let text = ``;
             console.log("몇번")
             result.forEach(freeboards => {

                 text += `
                            <a href="/community/free-detail/${freeboards.id}">
                                <li class="contant-list-container" style="margin-top: 10px">
                                    <div>
                                        <button class="contant-btn">
                                            <div class="contant-title-container">
                                                <div class="contant-title-wrapper">
                                                    <h2 class="contant-title">${freeboards.boardTitle}</h2>
                                                    <p class="contant-contant">${freeboards.boardContent}</p>
                                                </div>
                                                <div class="contant-img">
                                                        <span class="contant-img-span">
                                                            <img class="contant-image" src="https://steadio.imgix.net/creator-posts/079d1a28-343d-4f6a-8889-e9328964aafd/creatorPostImage/269f44f6-9d34-47bb-83a2-7d99d86a5280.jpg?auto=format%2Ccompress&fit=crop&h=420&lossless=true&w=680">
                                                        </span>
                                                </div>
                                            </div>
                                            <div class="contant-user">
                                                <div class="user-profile">
                                                    <span class="user-name">${freeboards.memberDTO.memberName}</span>
                                                    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                                    <span class="user-time">${date()}</span>
                                                    <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                                    <span class="user-see">댓글수 ${freeboards.replyCount}</span>
                                                </div>
                                            </div>
                                        </button>
                                    </div>
                                </li>
                            </a>
                        `;
             });
             $('.contant').html(text);
         }
     });
 });

 categoriBest.addEventListener('click', () => {
    categoriBest.style.color = '#1d1d1e';
    categoriBest.style.fill = '#1d1d1e';
    categoriNew.style.color = '#8a8a8b';
    categoriNew.style.fill = '#8a8a8b';

     $.ajax({
         url: "/community/free-board/likes",
         type: "get",
         success: function(results) {
             let text = ``;
             results.forEach(freeboard => {

                 text += `
                        <a href="/community/free-detail/${freeboard.id}">
                            <li class="contant-list-container" style="margin-top: 10px">
                                <div>
                                    <button class="contant-btn">
                                        <div class="contant-title-container">
                                            <div class="contant-title-wrapper">
                                                <h2 class="contant-title">${freeboard.boardTitle}</h2>
                                                <p class="contant-contant">${freeboard.boardContent}</p>
                                            </div>
                                            <div class="contant-img">
                                                    <span class="contant-img-span">
                                                        <img class="contant-image" src="https://steadio.imgix.net/creator-posts/079d1a28-343d-4f6a-8889-e9328964aafd/creatorPostImage/269f44f6-9d34-47bb-83a2-7d99d86a5280.jpg?auto=format%2Ccompress&fit=crop&h=420&lossless=true&w=680">
                                                    </span>
                                            </div>
                                        </div>
                                        <div class="contant-user">
                                            <div class="user-profile">
                                                <span class="user-name">${freeboard.memberDTO.memberName}</span>
                                                <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                                <span class="user-time">${date()}</span>
                                                <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="contant-user-span"><circle cx="12" cy="12" r="4"></circle></svg>
                                                <span class="user-see">댓글수 ${freeboard.replyCount}</span>
                                            </div>
                                        </div>
                                    </button>
                                </div>
                            </li>
                        </a>
                    `;
             });
             $('.contant').html(text);
         }
     });
 });



