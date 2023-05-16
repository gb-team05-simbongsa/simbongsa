const adminService = (function() {
    function saveNotice(url, noticeDTO, callback) {
        $.ajax({
            url: url,
            type: "post",
            data: JSON.stringify(noticeDTO),
            contentType: 'application/json',
            success: function() {
                if(callback) {
                    callback();
                }
            }
        });
    }

    function getDetail(url, contentId, callback) {
        $.ajax({
            url: url,
            type: "post",
            data: { id : contentId },
            success: function(result) {
                if(callback) {
                    callback(result);
                }
            }
        });
    }

    function deleteAllById(url, contentIds, callback) {
        console.log(contentIds);
        $.ajax({
            url: url,
            type: "post",
            data: { ids : contentIds },
            traditional : true,
            success: function() {
                if(callback) {
                    callback();
                }
            }
        });
    }

    // function checkPhone(userPhone, callback) {
    //     $.ajax({
    //         url: "/users/userPhones-duplicate",
    //         type: "post",
    //         data: { userPhone : userPhone },
    //         async: false,
    //         success: function(result) {
    //             if(callback) {
    //                 callback(result);
    //             }
    //         }
    //     });
    // }
    //
    // function startTimer(count, display) {
    //     var minutes, seconds;
    //     timer = setInterval(function () {
    //         minutes = parseInt(count / 60, 10);
    //         seconds = parseInt(count % 60, 10);
    //
    //         minutes = minutes < 10 ? "0" + minutes : minutes;
    //         seconds = seconds < 10 ? "0" + seconds : seconds;
    //
    //         display.html(minutes + ":" + seconds);
    //
    //         // 타이머 끝
    //         if (--count < 0) {
    //             clearInterval(timer);
    //             display.html("00:00");
    //             $modal.css('visibility', 'visible');
    //             $modalText.text("유효시간이 만료되었습니다.\n다시 시도해주세요.");
    //             $verifiCheckBtn.attr('disabled', true);
    //         }
    //     }, 1000);
    // }
    //
    // function findByPhone() {
    //     $verifiCheckBtn.attr('disabled', false);
    //     clearInterval(timer);
    //
    //     overlapService.sendSMS(function(result) {
    //         console.log(result);
    //         authNumber = result;
    //     });
    //
    //     var display = $(".verification-timer");
    //     // 유효시간 설정
    //     var leftSec = 180;
    //
    //     overlapService.startTimer(leftSec, display);
    // }
    //
    // function checkAuthNumber() {
    //     if($('input[name=verificationNumber]').val() == authNumber) {
    //         $modal.css('visibility', 'visible');
    //         $modalText.text("인증확인이 완료되었습니다.");
    //         clearInterval(timer);
    //         $(".verification-timer").hide();
    //         $checkButton.on('click',()=>{
    //             $modal.css('visibility', 'hidden');
    //             $('#findForm').submit();
    //         });
    //     } else {
    //         $modal.css('visibility', 'visible');
    //         $modalText.text("인증번호가 일치하지 않습니다.");
    //         $checkButton.on('click',()=>{
    //             $modal.css('visibility', 'hidden');
    //         });
    //     }
    // }
    return { saveNotice : saveNotice, getDetail : getDetail, deleteAllById : deleteAllById };
})();