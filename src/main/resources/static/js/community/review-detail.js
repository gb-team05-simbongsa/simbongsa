$('.comment-box-span').on('keyup', () => {
    if($('.comment-box-span').val()) {
        $('.singUp').attr('disabled', false);
    } else {
        $('.singUp').attr('disabled', true);
    }
});

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

/*====================================================================================================*/
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

    if(!$replyContent.val()){
        alert("입력해주세요.");
        return false;
    }else {
        replyService.save(replyRequestDTO,function(){
            $(".comment-btn").show();
            page = 0;
            replyService.list({page: page, boardId : boardId},function(replies){
                $replyBox.html(repliesContent(replies));
                $replyContent.val("");

                if(replies.last) {
                    $(".comment-btn").hide();
                }
            });
        });
    }
});
// 삭제 버튼
$replyBox.on("click","button.x-btn", function(e){
    let i = e.target.id.replaceAll("delete","");
    replyService.deleteReply({
        replyId : replyId
    },function(){
        replyService.list({
            page : page,
            boardId : boardId
        },function(replies){
            $replyBox.html(repliesContent(replies));
        })
    })

});
