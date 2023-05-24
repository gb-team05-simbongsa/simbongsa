
$('.submit-button').on('click', function() {
    if(!$('input[type=file]').val()) {

        // 파일을 아무것도 안넣었을 때 모달
        // $('.pay-content').text('파일을 선택해주세요.')
        // $container.css("display", "block");
        //
        // $close.on("click", function () {
        //     $container.css("display", "none");
        // });
    } else {
        let supportRequestDTO = setSupportRequestDTO();
        $.ajax({
            url: "/support/support-write",
            type: "post",
            data: JSON.stringify(supportRequestDTO),
            contentType: "application/json; charset=utf-8",
            success: function() {
                location.href = "/support/support-list";
            }
        });
    }
});

globalThis.uuids;
globalThis.paths;
FileList.prototype.forEach = Array.prototype.forEach;
let prevFileList;

const $doAjax = function (config, callback) {
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

/* 저장할 파일들의 Array */

// let $fileId;
let $photoPicker = $("#photo-picker");
const uploadAjaxConfig = (data) => {
    return {
        url: "/file/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}

$photoPicker.on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();

    if ($files.length > 8) {
        alert("등록할 수 있는 파일의 최대 갯수는 8개 입니다.");
        /* 파일 input 초기화 */
        $photoPicker[0].files = prevFileList;
        return;
    }


    prevFileList = $files;

    $files.forEach(file => formData.append("file", file));


    $doAjax(uploadAjaxConfig(formData), (result) => {
        globalThis.uuids = result.uuids;
        globalThis.paths = result.paths;
        $('#thumbnail-list').empty();
        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $('#thumbnail-list').append(`<img class="imageThumbnail" src="/file/display?fileName=${result.paths[i]}" style="width: 80px">`);
            } else $('#thumbnail-list').append(`<img style="width: 60px"/>`);
        });
    });
});


let fileDTOS = new Array();

let setSupportRequestDTO = function() {
    const $files = $photoPicker[0].files;

    $files.forEach((file, i) => {
        let fileDTO = new Object();
        fileDTO.fileName = file.name;
        fileDTO.fileUuid = globalThis.uuids[i];
        fileDTO.filePath = globalThis.paths[i];
        fileDTOS.push(fileDTO);
    });

    const supportRequestDTO = {
        supportRequestTitle : $('#inquiry-subject').val(),
        supportRequestContent : $('.write-content-contents-textarea-write').val(),

        fileDTOS : fileDTOS
    }

    return supportRequestDTO;
}
