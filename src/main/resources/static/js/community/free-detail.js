let replyService = (function(){

    function save(reply, callback){
        $.ajax({
            url : '/save',
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

    function list(reply, callback){
        $.ajax({
            url : '/list',
            type: 'get',
            data: reply,
            success : function(replies){
                if(callback) {
                    callback(replies);
                }
            }
        });
    }

    function deleteReply(reply, callback){
        $.ajax({
            url : '/delete',
            type : 'delete',
            data : reply,
            success : function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    return {
        save : save,
        list : list,
        deleteReply : deleteReply
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
let page = 0;

replyService.list({
    page: page,
    boardId: boardId
}, function (replies) {
    if (replies.content.length < 1) {
        let text = `
                        <div class="comment-list" style="display: none;">
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
/* 댓글 */
const $registerButton = $(".singUp");
let $replyContent = $(".comment-box-span");
let $replyBox = $(".comment-ok-list");

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

                if (replies.last) {
                    $(".comment-btn").hide();
                }
            });
        });
    }
});
// 삭제 버튼
$replyBox.on("click", "button.delete-button", function (e) {
    let i = e.target.id.replaceAll("delete", "");
    let replyId = $($(".reply-modify-content")[i]).prop("id").replaceAll("reply", "");
    page = 0;
    replyService.deleteReply({
        replyId: replyId
    }, function () {
        replyService.list({
            page: page,
            boardId: boardId
        }, function (replies) {
            $replyBox.html(repliesContent(replies));
        })
    });
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

function repliesContent(replies) {
    let text = '';
    let replyDTO = replies.content;

    if (replyDTO.length < 1) {
        text = `
                <div class="comment-list" style="display: none;">
                    <p class="NoComment">댓글이 없습니다.</p>
                    <p class="NoComment2">첫 댓글을 남겨보세요</p>
                </div>
        `;
        return text;
    }

    $(replyDTO).each((i, reply) => {
        text += `
        
        `;
        /*====================================================================================================================*/

        const modal = document.querySelector('.modal');
        const modalBack = document.querySelector('.modal-back');
        const xBtn = document.querySelector('.x-btn');
        const cancelBtn = document.querySelector('.choce1-btn');

        function showModal() {
            modal.style.display = 'flex';
            modal.style.top = '50%';
            modal.style.left = '50%';
            modal.style.transform = 'translate(-50%, -50%)';
            modalBack.style.display = 'block';
            modalBack.style.position = 'fixed';
        }

        function hideModal() {
            modal.style.display = 'none';
            modalBack.style.display = 'none';
        }

        xBtn.addEventListener('click', showModal);
        cancelBtn.addEventListener('click', hideModal);
        modalBack.addEventListener('click', hideModal);
    });
}

/*====================================================================================================*/
