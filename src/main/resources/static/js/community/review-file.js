
$('.choce2-btn').on('click', function() {
    if(!$('input[type=file]').val()) {
        alert("파일을 넣어주세요")
        // 파일을 아무것도 안넣었을 때 모달
        // $('.pay-content').text('파일을 선택해주세요.')
        // $container.css("display", "block");
        //
        // $close.on("click", function () {
        //     $container.css("display", "none");
        // });
        return;
    } else {
        let reviewDTO = setReviewDTO();
        /* let formData = new FormData();*/
        // formData.append("file", $photo[0].files[0]); // 파일
        // formData.append("title", ReviewDTO.boardTitle); // input 태그의 값
        // formData.append("content", ReviewDTO.boardContent);
        $.ajax({
            url: "/community/review-create",
            type: "post",
            data: JSON.stringify(reviewDTO),
            contentType: "application/json; charset=utf-8",
            success: function() {
                console.log("다시 옵니까")
                location.href = "/community/review-board/new";
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
const $photo = $("#file-input");
const uploadAjaxConfig = (data) => {
    return {
        url: "/file/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false

    }
}

$photo.on("change", function () {
    let $files = $(this)[0].files;
    let formData = new FormData();

    if ($files.length > 6) {
        alert("등록할 수 있는 파일의 최대 갯수는 6개 입니다.");
        /* 파일 input 초기화 */
        $photo[0].files = prevFileList;
        return;

    }


    prevFileList = $files;

    $files.forEach(file => formData.append("file", file));


    $doAjax(uploadAjaxConfig(formData), (result) => {
        globalThis.uuids = result.uuids;
        globalThis.paths = result.paths;
        if($('.imageThumbnail').length != 0) {
            $('.imageThumbnail').each((i, image) => {
                image.remove();
            });
        }

        result.paths.forEach((path, i) => {
            if ($files[i].type.startsWith("image")) {
                $('#photo-picker2').append(`<img class="imageThumbnail" src="/file/display?fileName=${result.paths[i]}" style="width: 80px">`);
            } else $('#photo-picker2').append(`<img style="width: 60px"/>`);
        });
    });

});


let fileDTOS = new Array();

let setReviewDTO = function() {
    const $files = $photo[0].files;

    $files.forEach((file, i) => {
        let fileDTO = new Object();

        fileDTO.fileName = file.name;
        fileDTO.fileUuid = globalThis.uuids[i];
        fileDTO.filePath = globalThis.paths[i];

        i == 0 ? fileDTO.fileRepresentationalType = 'REPRESENTATION' : fileDTO.fileRepresentationalType = 'NORMAL';

        fileDTOS.push(fileDTO);
    });


    const reviewDTO = {
        boardTitle : $('.title-input').val(),
        boardContent : $('.contents-text').val(),
        fileDTOS : fileDTOS
    }

    return reviewDTO;
}
