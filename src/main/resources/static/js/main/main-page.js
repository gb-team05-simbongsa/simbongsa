const $volunteerAppend = $('.main-first-list-layout'); // 봉사 리스트를 추가할 대상
const $memberAppend = $('.main-popular-list-layout'); // 멤버 랭크 리스트를 추가할 대상
const $freeBoardAppend = $('.swiper-wrapper'); // 자유게시판 리스트를 추가할 대상

// 심청이 랭 리스트 반복문
memberRankList.forEach((memberRankList,i) => {
    let text;
    text = `
     <div class="list-wrapper">
        <div></div>
        <div class="volunteer-percentage-wrapper">
            <div></div>
            <div class="popular-percentage-layout">
                <span class="rank-layout-top">${i+1}</span>
                <div class="info-wrapper" style="margin-left: 11px; width: calc(100% - 120px);">
                    <dl>
                        <dd class="volunteer-sub-info">
                            <div class="memberRank" style= "
                                            background-size: 20px;
                                            width: 20px;
                                            height: 20px;
                                            display: inline-block;">
                            </div>
                            <span>
                                <a href="#" class="rank" style="font-size: 12px">${memberRankList.memberRank}</a>
                            </span>
                        </dd>
                        <dt>
                            <a href="#">
                                <strong>${memberRankList.memberName}</strong>
                            </a>
                        </dt>
                    </dl>
                    <div class="funding-status" style="margin-left: 2px;">
                        <span class="percentage">누적 </span><span class="percentage" >${memberRankList.memberVolunteerTime}</span><span class="percentage">시간 달성</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;
    $memberAppend.append(text);

})
// 자유게시판 리스트 반복문
freeBoardList.forEach(freeBoard => {
    let text;
    text = `
        <div class="swiper-slide swiper-slide-active" style="width:220.8px; margin-right: 14px;">
            <div></div>
            <div class="volunteer-percentage-wrapper">
                <div></div>
                <div class="volunteer-link-wrapper">
                    <div class="volunteer-image-wrapper freeBoardImg">
                        <a href="/community/free-detail/${freeBoard.id}">
                            <img class="fundingImg" src="/file/display?fileName=${freeBoard.fileDTOS[0].filePath}">
                            <img class="fundingImg" src="/file/display?fileName=${freeBoard.fileDTOS.filePath}">
                        </a>
                    </div>
                    <div class="info-wrapper">
                        <dl>
                            <dd class="volunteer-sub-info">
                                <span>
                                    <a href="#">댓글수</a>
                                </span>
                                <span>
                                    <a href="#">${freeBoard.replyCount}</a>
                                </span>
                                <span>
                                    <a href="#">자유게시판</a>
                                </span>
                            </dd>
                            <dt>
                                <a href="">
                                    <strong>${freeBoard.boardTitle}</strong>
                                </a>
                            </dt>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    `
    $freeBoardAppend.append(text);
})

// 봉사활동 리스트 반목문
volunteerList.forEach(volunteer => {
    let text;
    text = `
    <div class="list-wrapper">
         <div></div>
           <div class="volunteer-percentage-wrapper">
            <div></div>
            <div class="volunteer-link-wrapper">
             <div class="volunteer-image-wrapper volunteerImg">
             <a href="/volunteer-work/work-detail/${volunteer.id}">
                <img class="fundingImg" src="/file/display?fileName=${volunteer.fileDTO.filePath}">
            </a>
            </div>
            <div class="info-wrapper" >
            <dl>
                <dd class="volunteer-sub-info">
                    <span>
                        <a href="/volunteer-work/work-detail/${volunteer.id}">${volunteer.volunteerWorkCategory}</a>
                    </span>
                    <span>
                        <a href="/volunteer-work/work-detail/${volunteer.id}" >${volunteer.volunteerWorkPlace}</a>
                    </span>
                </dd>
                <dt>
                    <a href="/volunteer-work/work-detail/${volunteer.id}">
                        <strong>${volunteer.volunteerWorkTitle}</strong>
                    </a>
                </dt>
            </dl>
        </div>
        </div>
    </div>
</div>
`;
    $volunteerAppend.append(text);
});


const $rank = $('.rank'); // 랭크 표시할 요소
const $image = $('.memberRank'); // 랭크 이미지 표시 요소
$(document).ready(function () {
    $rank.each(function (index) {
        const text = $(this).text();
        if (text === "동냥1티어") {
            $image.eq(index).css("background-image", "url(/image/main/bronze1.png)");
        } else if (text === "동냥2티어") {
            $image.eq(index).css("background-image", "url(/image/main/bronze2.png)");
        } else if (text === "동냥3티어") {
            $image.eq(index).css("background-image", "url(/image/main/bronze3.png)");
        } else if (text === "은냥1티어") {
            $image.eq(index).css("background-image", "url(/image/main/silver1.png)");
        } else if (text === "은냥2티어") {
            $image.eq(index).css("background-image", "url(/image/main/silver2.png)");
        } else if (text === "은냥3티어") {
            $image.eq(index).css("background-image", "url(/image/main/silver3.png)");
        } else if (text === "금냥1티어") {
            $image.eq(index).css("background-image", "url(/image/main/gold1.png)");
        } else if (text === "금냥2티어") {
            $image.eq(index).css("background-image", "url(/image/main/gold2.png)");
        } else if (text === "금냥3티어") {
            $image.eq(index).css("background-image", "url(/image/main/gold3.png)");
        }
    });
});