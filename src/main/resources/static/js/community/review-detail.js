let replyService = (function () {

    function save(reply, callback) {
        $.ajax({
            url: '/communities/review-save',
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }

    function list(reply, callback) {
        $.ajax({
            url: '/communities/review-list',
            type: 'post',
            data: reply,
            success: function (replies) {
                if (callback) {
                    callback(replies);
                }
            }
        });
    }

    function deleteReply(replyId, callback) {
        $.ajax({
            url: '/communities/review-delete',
            type: 'delete',
            data: {replyId: replyId},
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }

    return {
        save: save,
        list: list,
        deleteReply: deleteReply
    }
})();

$('.comment-box-span').on('keyup', () => {
    if($('.comment-box-span').val()) {
        $('.singUp').attr('disabled', false);
    } else {
        $('.singUp').attr('disabled', true);
    }
});

/*====================================================================================================================*/
/*댓글*/
/*====================================================================================================================*/
function formatDate(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

let page = 0;

const $registerButton = $(".singUp");
let $replyContent = $(".comment-box-span");
let $replyBox = $("ul.comment-lists");

showList();
showFiles();

function showFiles() {
    fileDTOS.forEach((fileDTO, i) => {
        if(i == 0) {
            $('#expandedImg').attr('src', `/file/display?fileName=${fileDTO.filePath}`);
        }

        let text = ``;

        text = `
            <li class="on">
                <img class="img-div" src="/file/display?fileName=${fileDTO.filePath}" style="height: 100%; cursor: pointer">
            </li>
        `;

        $('.thumbnail-list ul').append(text);
    });
}

function showList() {
    replyService.list({
        page: page,
        boardId: boardId
    }, function (replies) {
        if (replies.content.length < 1) {
            let text = `
                    <div class="comment-list">
                        <p class="NoComment">댓글이 없습니다.</p>
                        <p class="NoComment2">첫 댓글을 남겨보세요</p>
                    </div>
                `;
            $replyBox.html(text);
            $(".comment-btn").hide();
            return false;
        }
        if (replies.last) {
            $(".comment-btn").hide();
        } else {
            $(".comment-btn").show();
        }

        $replyBox.html(repliesContent(replies));
    });
}

function repliesContent(replies) {
    let text = ``;
    let modalText = document.querySelector('#modal-wrap').innerHTML;
    replies.content.forEach(reply => {
        console.log(`${reply.id}` + "번")
        text += `
            <li class="comment-ok-list">
                <div style="width: 100%;">
                    <div class="comment-user">
                        <div class="comment-user-name">${reply.memberDTO.memberName}</div>`;
        if (memberId == reply.memberDTO.id) {

            text += `<button type="button" class="x-btn" data-reply-id="${reply.id}">
                        <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="x-btn-svg">
                            <path d="M5.707 5.707a1 1 0 0 0 0 1.414l4.95 4.95-4.95 4.95a1 1 0 1 0 1.414 1.414l4.95-4.95 4.95 4.95a1 1 0 0 0 1.414-1.414l-4.95-4.95 4.95-4.95a1 1 0 1 0-1.414-1.414l-4.95 4.95-4.95-4.95a1 1 0 0 0-1.414 0Z"></path>
                        </svg>
                    </button>`

            modalText += `<div class="modal">
                 <h4 class="modal-title">댓글 삭제</h4>
                 <div class="modal-content">댓글을 삭제하시겠습니까?</div>
                 <div class="modal-choce">
                     <div class="choce1">
                         <button class="choce1-btn">취소</button>
                     </div>
                     <div class="choce2">
                        <button type="button" class="choce2-btn" fill="true" data-reply-id="${reply.id}">삭제</button>
                     </div>
                 </div>
              </div>
              <div class="modal-back"></div>`;

            // $("#modal-wrap").append(`<div class="modal">
            //     <h4 class="modal-title">댓글 삭제</h4>
            //     <div class="modal-content">댓글을 삭제하시겠습니까?</div>
            //     <div class="modal-choce">
            //         <div class="choce1">
            //             <button class="choce1-btn">취소</button>
            //         </div>
            //         <div class="choce2">
            //         <button type="button" class="choce2-btn" fill="true" data-reply-id="${reply.id}">삭제</button>
            //         </div>
            //     </div>
            //  </div>
            //  <div class="modal-back"></div>`);

        }
        text += `
                    </div>
                    <div style="flex: 0 0 auto; height: 5px;"></div>
                    <p class="comment-contant">${reply.reviewReplyContent}</p>
                    <div style="flex: 0 0 auto; height: 8px;"></div>
                    <span class="comment-day">${formatDate(reply.createdDate)}</span>
                </div>
            </li>
        `;
    });
    $("#modal-wrap").html(modalText);
    return text;
}

// 댓글 등록 시
$registerButton.click(() => {
    let replyRequestDTO = new Object();

    replyRequestDTO.memberId = memberId;
    replyRequestDTO.replyContent = $replyContent.val();
    replyRequestDTO.boardId = boardId;

    if (!$replyContent.val()) {
        alert("입력해주세요.");
        return false;
    } else {
        replyService.save(replyRequestDTO, function () {
            $(".comment-btn").show();
            page = 0;
            replyService.list({page: page, boardId: boardId}, function (replies) {
                $replyBox.html(repliesContent(replies));
                $replyContent.val("");
                console.log(replies);
                if (replies.last) {
                    $(".comment-btn").hide();
                }
                $('#countReply').text('댓글수' + (parseInt($('#countReply').text().replace('댓글수', '')) + 1));
                $('#countReply2').text(parseInt($('#countReply2').text()) + 1);
            });
        });
    }
});


// 더보기 버튼
$(".comment-btn").click(() => {
    page++;
    replyService.list({
        page: page,
        boardId: boardId
    }, function (replies) {
        $replyBox.append(repliesContent(replies));

        if (replies.last) {
            $(".comment-btn").hide();
        }
    });
});

$('.img-div').on('click', function() {
    $('#expandedImg').attr('src', $(this).attr('src'));
})


//  삭제모달
let modal;
let modalBack;
let replyIdToDelete;
let $modalWrap = $('#modal-wrap');

function showModal() {
    const modals = document.querySelectorAll('.modal');
    const modalBacks = document.querySelectorAll('.modal-back');
    const xBtns = document.querySelectorAll('.x-btn');
    const cancelBtns = document.querySelectorAll('.choce1-btn');

    let i = $(xBtns).index(this);
    modal = modals[i];
    modalBack = modalBacks[i];
    modal.style.display = 'flex';
    modal.style.top = '50%';
    modal.style.left = '50%';
    modal.style.transform = 'translate(-50%, -50%)';
    modalBack.style.display = 'block';
    modalBack.style.position = 'fixed';

    // replyIdToDelete = $(this).siblings('.comment-user-name').find('.choce2-btn').data("reply-id");
    replyIdToDelete = $(this).data("reply-id");
    console.log(replyIdToDelete);
}

function hideModal() {
    modal.style.display = 'none';
    modalBack.style.display = 'none';
    replyIdToDelete = null;
}

$replyBox.on("click", ".x-btn", showModal);
$modalWrap.on("click", ".choce1-btn", hideModal);
$modalWrap.on("click", ".modal-back", hideModal);
$modalWrap.on("click", ".choce2-btn", function () {
    console.log(replyIdToDelete);
    replyService.deleteReply(replyIdToDelete, function () {
        page = 0;
        hideModal();
        $('#countReply').text('댓글수' + (parseInt($('#countReply').text().replace('댓글수', '')) - 1));
        $('#countReply2').text(parseInt($('#countReply2').text()) - 1);

        showList();
    });
});
