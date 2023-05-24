//
// const file = document.querySelector('input[type=file]');
// const imgButton = document.querySelector(".imgButton");
// console.log(imgButton);
// const $thumbnailWrap = $(".thumbnailWrap");
// function handleFiles(files) {
//     /* 썸네일 담을 div의 부모 */
//     const thumbnailList = document.getElementById("thumbnail-list");
//
//     for (let i = 0; i < files.length; i++) {
//
//         /* 8개 이미지 추가되면 버튼 없애기 */
//         if ($(".imageThumbnail").length > 7) {
//             $(".imgButtonWrap").hide();
//         }
//
//
//         /* 파일절대경로얻기 */
//         const file = files[i];
//         const reader = new FileReader();
//         /* reader가 onload 할때 */
//         reader.onload = function (event) {
//             /* 썸네일 담을 div와 그 자식의 span 선언 */
//             const thumbnail = document.createElement("div");
//             const thumbnailSpan = document.createElement("span");
//
//
//             let result = event.target.result;
//
//             /* 썸네일 담을 div와 그 자식의 span에 썸네일 css와 x버튼 css 추가*/
//             thumbnail.classList.add("imageThumbnail");
//             thumbnailSpan.classList.add("closeImgButton");
//
//             /* 썸네일 담을 div에 절대경로 넣어주기 */
//             thumbnail.style.backgroundImage = `url('${result}')`;
//
//             /* 썸네일 담을 div와 그 자식의 span 추가해주기 */
//             thumbnailList.prepend(thumbnail);
//             thumbnail.appendChild(thumbnailSpan);
//
//             /* x버튼 선언 */
//             const closeButton = document.querySelector(".closeImgButton");
//             const submitBtn = document.querySelector(".submitButton");
//
//             /* x버튼 누를 시 x버튼과 backgroundImage 지워주기 */
//             closeButton.addEventListener('click', function (e) {
//                 e.preventDefault();
//                 file.value = "";
//                 this.style.display = 'none';
//                 thumbnail.style.backgroundImage = `url('')`;
//                 thumbnail.remove(thumbnail);
//                 $(".imgButtonWrap").show();
//             });
//
//
//             /* 파일 개수가 8개 이상이면 버튼숨기기 */
//             if ($(".imageThumbnail").length > 7) {
//                 $(".imgButtonWrap").hide();
//                 return;
//             }
//
//         };
//         /* result 속성(attribute)에 담기 */
//         reader.readAsDataURL(file);
//     }
//
// }
//
// /* 버튼을 감싸고있는 label객체 들고오기 */
// const fileInput = document.getElementById("photo-picker");
//
// /* 버튼을 감싸고있는 label객체 클릭하면 위에 function handleFiles 실행 */
// fileInput.addEventListener("change", function (event) {
//     handleFiles(event.target.files);
// });
//
//
// /* 베낀거------------------------*/
// FileList.prototype.forEach = Array.prototype.forEach;
//
// let files = [];
// if(fileDTOs != null && fileDTOs != undefined){
//     fileDTOS.forEach((file,i) => {
//         files.push(file);
//     });
// }
// console.log(files+ "3 . ==================");
//
// $("input[type=file]").on("change", function () {
//     const $files = $("input[type=file]")[0].files;
//     let formData = new FormData();
//
//     $($files).each((i, file) => {
//         files.push(file);
//     })
//     console.log(files + "4 . ==================")
//
//     files.forEach((file, e) => {
//         formData.append("file", file);
//     })
//     console.log(formData + "5 . ==================")
//     $.ajax({
//         url: "/file/upload",
//         type: "post",
//         data: formData,
//         contentType: false,
//         processData:false,
//         success: function (uuids){
//             globalThis.uuids = uuids;
//             console.log(uuids);
//             $files.forEach((file,i) => {
//                 if(file.type.startsWith("image")){
//                     let text = `
//                     <img class="imageThumbnail" src="/file/display?fileName=${toStringByFormatting(new Date())}/t_${uuids[i]}_${file.fileName}">
//                     `;
//                     $thumbnailWrap.append(text);
//                     console.log($thumbnailWrap + "6. ======================")
//                 }
//             })
//         }
//     })
// })
//
// $('.closeImgButton').on('click', function (e){
//     const dataTransfer = new DataTransfer();
//     let target = $(e.currentTarget).parent();
//     let fileArray = Array.from(files);
//     let ul = target.parent();
//     let i = ul.find("imageThumbnail").index(target);
//     files = [];
//     fileArray.splice(i, 1);
//     fileArray.forEach(file => {
//         if(file.fileName == null && file.fileName == undefined){
//             dataTransfer.items.add(file);
//         }else {
//             files.push(file);
//         }
//     })
//
//     target.remove();
//     dataTransfer.files.forEach((file, i) =>{
//         files.push(file);
//     });
//
//     console.log(files);
// })
//
//
// $(".submit-button-layout").click(() => {
//     let text = "";
//
//     FileList.prototype.forEach = Array.prototype.forEach;
//     files.forEach((file, i) => {
//         text +=
//             `
//                 <input type="hidden" name="fileDTOS[${i}].fileName" value="${file.fileName}">
//                 <input type="hidden" name="fileDTOS[${i}].fileUuid" value="${globalThis.uuids[i]}">
//                 <input type="hidden" name="fileDTOS[${i}].filePath" value="${toStringByFormatting(new Date())}">
//                 `;
//     });
//     $("form[name=form]").append(text);
//     $("form[name=form]").submit();
// })
// function toStringByFormatting(source, delimiter = '/') {
//     const year = source.getFullYear();
//     const month = leftPad(source.getMonth() + 1);
//     const day = leftPad(source.getDate());
//
//     return [year, month, day].join(delimiter);
// }

/*================================================ END ================================================*/

//
// const fileInput = document.getElementById("photo-picker");
// const thumbnailList = document.getElementById("thumbnail-list");
// const submitButton = document.querySelector(".submit-button");
//
// fileInput.addEventListener("change", function (event) {
//     const files = event.target.files;
//     const formData = new FormData();
//
//     for (let i = 0; i < files.length; i++) {
//         formData.append("file", files[i]);
//     }
//
//     $.ajax({
//         url: "/file/upload",
//         type: "POST",
//         data: formData,
//         contentType: false,
//         processData: false,
//         success: function (response) {
//             const uuids = response.uuids;
//             const paths = response.paths;
//
//             for (let i = 0; i < uuids.length; i++) {
//                 const thumbnail = document.createElement("div");
//                 thumbnail.classList.add("imageThumbnail");
//                 thumbnail.style.backgroundImage = `url('/file/display?fileName=${paths[i]}')`;
//
//                 const closeButton = document.createElement("span");
//                 closeButton.classList.add("closeImgButton");
//                 closeButton.addEventListener("click", function () {
//                     thumbnail.remove();
//                     submitButton.disabled = false;
//                 });
//
//                 thumbnail.appendChild(closeButton);
//                 thumbnailList.prepend(thumbnail);
//             }
//
//             if ($(".imageThumbnail").length >= 8) {
//                 $(".imgButtonWrap").hide();
//             }
//
//             submitButton.disabled = false;
//         },
//         error: function (error) {
//             console.error(error);
//             submitButton.disabled = false;
//         },
//     });
//
//     submitButton.disabled = true;
// });
//
// $(".submit-button-layout").click(function () {
//     const form = document.forms["form"];
//     const fileDTOs = [];
//
//     $(".imageThumbnail").each(function () {
//         const filePath = $(this).css("background-image");
//         const fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf('"'));
//         const fileUuid = fileName.substring(fileName.indexOf("_") + 1, fileName.lastIndexOf("_"));
//
//         fileDTOs.push({
//             fileName: fileName,
//             fileUuid: fileUuid,
//             filePath: filePath,
//         });
//     });
//
//     fileDTOs.forEach(function (fileDTO, index) {
//         const fileNameInput = document.createElement("input");
//         fileNameInput.type = "hidden";
//         fileNameInput.name = `fileDTOS[${index}].fileName`;
//         fileNameInput.value = fileDTO.fileName;
//
//         const fileUuidInput = document.createElement("input");
//         fileUuidInput.type = "hidden";
//         fileUuidInput.name = `fileDTOS[${index}].fileUuid`;
//         fileUuidInput.value = fileDTO.fileUuid;
//
//         const filePathInput = document.createElement("input");
//         filePathInput.type = "hidden";
//         filePathInput.name = `fileDTOS[${index}].filePath`;
//         filePathInput.value = fileDTO.filePath;
//
//         form.appendChild(fileNameInput);
//         form.appendChild(fileUuidInput);
//         form.appendChild(filePathInput);
//     });
//
//     form.submit();
// });






function insertMultiplePhotos() {
    const fileInput = document.getElementById("photo-picker");
    const thumbnailList = document.getElementById("thumbnail-list");
    const submitButton = document.querySelector(".submit-button");

    fileInput.addEventListener("change", function (event) {
        const files = event.target.files;
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            formData.append("file", files[i]);
        }

        $.ajax({
            url: "/file/upload",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                const uuids = response.uuids;
                const paths = response.paths;

                for (let i = 0; i < uuids.length; i++) {
                    const thumbnail = document.createElement("div");
                    thumbnail.classList.add("imageThumbnail");
                    thumbnail.style.backgroundImage = `url('/file/display?fileName=${paths[i]}')`;

                    const closeButton = document.createElement("span");
                    closeButton.classList.add("closeImgButton");
                    closeButton.addEventListener("click", function () {
                        thumbnail.remove();
                        submitButton.disabled = false;
                    });

                    thumbnail.appendChild(closeButton);
                    thumbnailList.prepend(thumbnail);
                }

                if ($(".imageThumbnail").length >= 8) {
                    $(".imgButtonWrap").hide();
                }

                submitButton.disabled = false;
            },
            error: function (error) {
                console.error(error);
                submitButton.disabled = false;
            },
        });

        submitButton.disabled = true;
    });
}

// 호출하여 사용하기
insertMultiplePhotos();