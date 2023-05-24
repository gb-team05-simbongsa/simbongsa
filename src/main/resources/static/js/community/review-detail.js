let replyService = (function () {

    function save(reply, callback) {
        $.ajax({
            url: '/community/review-save',
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
            url: '/community/review-list',
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
            url: '/community/revew-delete',
            type: 'delete',
            data: JSON.stringify({replyId: replyId}),
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
let page = 0;

const $registerButton = $(".singUp");
let $replyContent = $(".comment-box-span");
let $replyBox = $("ul.comment-lists");

showList();

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
        }
        $replyBox.html(repliesContent(replies));
    });
}

function repliesContent(replies) {
    let text = ``;
    replies.content.forEach(reply => {
        text += `
            <li class="comment-ok-list">
                <div style="width: 100%;">
                    <div class="comment-user">
                        <div class="comment-user-name">${reply.memberDTO.memberName}</div>`;
        if (memberId == reply.memberDTO.id) {

            text += `<button type="button" class="x-btn">
                        <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" class="x-btn-svg">
                            <path d="M5.707 5.707a1 1 0 0 0 0 1.414l4.95 4.95-4.95 4.95a1 1 0 1 0 1.414 1.414l4.95-4.95 4.95 4.95a1 1 0 0 0 1.414-1.414l-4.95-4.95 4.95-4.95a1 1 0 1 0-1.414-1.414l-4.95 4.95-4.95-4.95a1 1 0 0 0-1.414 0Z"></path>
                        </svg>
                    </button>`

            $("#modal-wrap").append(`<div class="modal">
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
             <div class="modal-back"></div>`);

        }
        text += `
                    </div>
                    <div style="flex: 0 0 auto; height: 5px;"></div>
                    <p class="comment-contant">${reply.reviewReplyContent}</p>
                    <div style="flex: 0 0 auto; height: 8px;"></div>
                    <span class="comment-day">${reply.createdDate}</span>
                </div>
            </li>
        `;
    });
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
            });
        });
    }
});

//  삭제모달
let modal;
let modalBack;
let replyIdToDelete;

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

    replyIdToDelete = $(this).siblings('.comment-user-name').find('.choce2-btn').data("reply-id");
}

function hideModal() {
    modal.style.display = 'none';
    modalBack.style.display = 'none';
    replyIdToDelete = null;
}

$replyBox.on("click", ".x-btn", showModal);
$replyBox.on("click", ".choce1-btn", hideModal);
$replyBox.on("click", ".modal-back", hideModal);
$replyBox.on("click", ".choce2-btn", function () {
    replyIdToDelete = $(this).data("reply-id");
    replyService.deleteReply(replyIdToDelete, function () {
        hideModal();
        showList();
    });
});
