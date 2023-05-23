

const fundingService = (function() {
    function saveFunding(fundingDTO, callback) {
        $.ajax({
            url: "/funding/funding-topContent",
            type: 'post',
            data: JSON.stringify(fundingDTO),
            contentType: "application/json; charset=utf-8",
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }


    function fundingFile(config, callback) {
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


    function dateFormat(date) {
        var date = new Date(date);
        return date.toLocaleString();
    }

    return { saveFunding : saveFunding, fundingFile : fundingFile};
})();


//file---------------------------------------------------------------------
globalThis.uuids;
globalThis.paths;
FileList.prototype.forEach = Array.prototype.forEach;
let prevFileList;

/* 저장할 파일들의 Array */

let $fileId;
let $imageFile = $("#imageFile");
const uploadAjaxConfig = (data) => {
    return {
        url: "/file/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}

$('#imageFile').on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();
    prevFileList = '';
    globalThis.uuids = prevFileList;
    globalThis.paths = prevFileList;

    if ($files.length > 1) {
        alert("파일은 1개만 등록할 수 있습니다.");
        /* 파일 input 초기화 */
        $('#imageFile')[0].files = prevFileList;
        console.log($('#imageFile')[0].files);
        return;
    }

    prevFileList = $files;

    // formData.append("file", $files);
    $files.forEach(file => formData.append("file", file));


    fundingService.fundingFile(uploadAjaxConfig(formData), (result) => {
        console.log(result)
        globalThis.uuids = result.uuids;
        globalThis.paths = result.paths;
        $('.ImgStyleList').empty();
        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $('.ImgStyleList').append(`<img data-path="${result.paths}" class="imageThumbnail" src="/file/display?fileName=${result.paths[i]}" style="width: 200px">`);
            } else $('.ImgStyleList').append(`<img style="width: 100px"/>`);
        });
    });
});

function loadPhoto() {
    $imageFile.on("change", function () {
        console.log("!!!!!!!!!!!!")
        let $files = $(this)[0].files;
        let formData = new FormData();
        prevFileList = '';
        globalThis.uuids = prevFileList;
        globalThis.paths = prevFileList;

        if ($files.length > 1) {
            alert("파일은 1개만 등록할 수 있습니다.");
            /* 파일 input 초기화 */
            $imageFile[0].files = prevFileList;
            console.log( $imageFile[0].files);
            return;
        }

        prevFileList = $files;

        // formData.append("file", $files);
        $files.forEach(file => formData.append("file", file));


        fundingService.fundingFile(uploadAjaxConfig(formData), (result) => {
            console.log(result)
            globalThis.uuids = result.uuids;
            globalThis.paths = result.paths;
            $('.ImgStyleList').empty();
            result.paths.forEach((path, i) => {
                if ($files[i].type.startsWith("image")) {
                    $('.ImgStyleList').append(`<img class="imageThumbnail" src="/file/display?fileName=${result.paths[i]}" style="width: 200px">`);
                } else $('.ImgStyleList').append(`<img style="width: 100px"/>`);
            });
        });
    });
}

let fileDTOs = new Object();

let setFundingDTO = function() {
    console.log($('#imageFile')[0])
    const $files = $('#imageFile')[0].files;

    $files.forEach((file, i) => {
        fileDTOs.fileName = $files[0].name;
        fileDTOs.fileUuid = globalThis.uuids[i];
        fileDTOs.filePath = globalThis.paths[0];
        fileDTOs.push(fileDTOs);

        console.log(fileDTOs);
        /*fileDTOs.fileName = $files[0].name;
        fileDTOs.fileUuid = globalThis.uuids[0];
        fileDTOs.filePath = globalThis.paths[0];*/
    });

    const fundingDTO = {
        fundingCategory : $('input[name=fundingCategory]').val(),
        fundingTitle : $('input[name=fundingTitle]').val(),
        fundingShortTitle : $('input[name=fundingShortTitle]').val(),
        fundingSummary : $('textarea[name=fundingSummary]').val(),
        fundingCreatorNickname : $('input[name=fundingCreatorNickname]').val(),
        fundingCreatorIntroduce : $('input[name=fundingCreatorIntroduce]').val(),
        fundingIntroduce : $('textarea[name=fundingIntroduce]').val(),
        fundingBudgetExplain : $('textarea[name=fundingBudgetExplain]').val(),
        fundingScheduleExplain : $('textarea[name=fundingScheduleExplain]').val(),
        fundingGiftExplain : $('textarea[name=fundingTargetPrice]').val(),
        fundingTargetPrice : $('input[name=volunteerWorkCategory]:checked').val(),
        fundingStartDate : $('input[name=fundingStartDate]').val(),
        fundingEndDate : $('input[name=fundingEndDate]').val(),

        fileDTOs : fileDTOs
    }

    console.log(fundingDTO);
    return fundingDTO;

}

$('#submitBtn').on('click', function(e) {
    e.preventDefault();

    let text = ``;
    $("img").each((i, img) => {
        let paths = $(img).data("paths");
        text += `<input type='hidden' name='fileDTOs[${i}].filePath' value=${paths}>`
    });

    $("form").append(text);
    $("form").submit();

    // fundingService.saveFunding(setFundingDTO(), function() {
    //     /* document.onsubmit;*/
    //     location.href = "/funding/funding-topContent";
    // });
});

/*function modify() {
    adminService.updateVolunteerWork(setVolunteerWorkModifyDTO(), function() {
        document.location.reload(true);
    });
}*/
