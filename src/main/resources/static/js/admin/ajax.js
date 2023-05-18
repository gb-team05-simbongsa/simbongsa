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

    function deleteOrUpdate(url, contentIds, callback) {
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

    function updateStatus(url, contentIds, status, callback) {
        console.log(contentIds)
        $.ajax({
            url: url,
            type: "post",
            data: { ids : contentIds, status : status },
            traditional : true,
            success: function() {
                if(callback) {
                    callback();
                }
            }
        });
    }

    function answerRegistration(answerDTO, inquiryId, callback) {
        console.log(answerDTO)
        $.ajax({
            url: "/admins/answer-registration",
            type: "post",
            data: JSON.stringify({ answerDTO : answerDTO, inquiryId : inquiryId }),
            contentType: 'application/json',
            success: function() {
                if(callback) {
                    callback();
                }
            }
        });
    }

    function volunteerFile(config, callback) {
        const isContentTypeDefined = config.contentType !== undefined;

        $.ajax({
            url: config.url,
            data: config.data,
            method: config.method,
            processData: config.processData !== false,
            contentType: isContentTypeDefined ? config.contentType : "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (result) {
                callback(result)
            },
            error: function () {
                console.log(config.data);
            }
        });
    }

    return { saveNotice : saveNotice, getDetail : getDetail, deleteOrUpdate : deleteOrUpdate, updateStatus : updateStatus,
        answerRegistration : answerRegistration, volunteerFile : volunteerFile };
})();