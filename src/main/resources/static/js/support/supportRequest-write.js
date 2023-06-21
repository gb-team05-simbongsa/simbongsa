// submit 버튼
const $submitButton = $('.submit-button');

// 파일 선택 여부에 따른 버튼 활성/비활성화
$(document).ready(function (){
    if(!$('input[type=file]').val()) {
        $submitButton.attr('disabled', true);
    }else if($('input[type=file]').val()){
        $submitButton.attr('disabled', false);
    }
})
// 제출 버튼 클릭 시
$submitButton.on('click', function() {
        let supportRequestDTO = setSupportRequestDTO();

        // AJAX 요청 전송
        $.ajax({
            url: "/support/support-write",
            type: "post",
            data: JSON.stringify(supportRequestDTO),
            contentType: "application/json; charset=utf-8",
            success: function() {
                // 성공 시 페이지
                location.href = "/support/support-list";
            }
        });
});
// 전역변수 설정
globalThis.uuids;
globalThis.paths;
FileList.prototype.forEach = Array.prototype.forEach;

// 이전 파일 목록 저장변수
let prevFileList;

// AJAX 요청 수행 함수
const $doAjax = function (config, callback) {
    const isContentTypeDefined = config.contentType !== undefined;

    //AJAX 요청 전송
    $.ajax({
        url: config.url,
        data: config.data,
        method: config.method,
        processData: config.processData !== false,
        contentType: isContentTypeDefined ? config.contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (result) {
            // 요청 성공 시 콜백 함수 호출
            callback(result)
        },
        error: function () {
            // 에러 시 로그 출력
            console.log(config.data);
        }
    });
}

/* 저장할 파일들의 Array */
let $photoPicker = $("#photo-picker");
// 파일 업로드용 AJAX 설정
const uploadAjaxConfig = (data) => {
    return {
        url: "/file/upload",
        method: "POST",
        data: data,
        contentType: false,
        processData: false
    }
}
// 파일 선택 상자의 변경 이벤트 처리
$photoPicker.on("change", function () {
    // 선택된 파일들
    let $files = $(this)[0].files;

    // FormData 객체 생성
    let formData = new FormData();

    // 최대 파일 개수 제한 확인
    if ($files.length > 6) {
        alert("등록할 수 있는 파일의 최대 갯수는 6개 입니다.");
        /* 파일 input 초기화 */
        $photoPicker[0].files = prevFileList;
        return;
    }

    // 이전 파일 목록 저장
    prevFileList = $files;

    // FormData에 파일 추가
    $files.forEach(file => formData.append("file", file));

    // 파일 업로드 AJAX 요청 수행
    $doAjax(uploadAjaxConfig(formData), (result) => {
        // 업로드 결과의 UUID의 경로 정보 저장
        globalThis.uuids = result.uuids;
        globalThis.paths = result.paths;

        // 이미지 썸네일 영역 초기화
        if($('.imageThumbnail').length != 0) {
            $('.imageThumbnail').each((i, image) => {
                image.remove();
            });
        }
        // 결과로 받은 경로를 기반으로 이미지 파일의 경우 썸네일 생성하여 화면에 추가
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
