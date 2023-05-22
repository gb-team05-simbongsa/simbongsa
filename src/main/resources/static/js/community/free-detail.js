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
