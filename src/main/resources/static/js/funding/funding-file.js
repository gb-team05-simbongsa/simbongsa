const file = document.querySelector('input[type=file]');
const $ImgStyleList = $('.ImgStyleList');
const $imgList = $('.imgList');
let nextImage =
`
<li class="ImgStyleList">
<div class="imgWithButtonBox"> <div class="imgBox">
    <img class="preview" src="https://tumblbug-pci.imgix.net/23132d1b1603bf67d41cae29a8bdeccf17b07d13/8002be7d5c77ab6e0b8020b1340198e8c37c611a/c28b4e86405484b4ad12bafd38ea47912c705ece/f9dfada5-495f-4550-a2c5-8ebd8bfa13ca.png?auto=format%2Ccompress&amp;fit=crop&amp;h=465&amp;lossless=true&amp;w=620&amp;s=213697091fbcd8643b46bb0f5eea56ed" alt="test">
    </div>
    <div class="styleBtWrap">
        <button class="lineBt changeBt fnt-p1 test" color="grayEditor200">
            <i>
                <div name="share-1" class="icon-svg">
                    <svg viewBox="0 0 48 48">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M25.9087 8.12155L36.4566 18.3158C37.2603 18.7156 38.2648 18.6156 38.968 18.3158C39.6712 17.5163 39.6712 16.4169 38.968 15.7173L25.3059 2.5247C24.6027 1.8251 23.4977 1.8251 22.7945 2.5247L9.03196 15.8172C8.32877 16.5168 8.32877 17.6162 9.03196 18.3158C9.73516 19.0154 10.9406 19.0154 11.6438 18.3158L22.2922 8.12155V28.4111C22.2922 29.4106 23.0959 30.2091 24.1005 30.2091C25.105 30.2091 25.9087 29.4106 25.9087 28.4111V8.12155ZM5.61644 29.4104C5.61644 28.4109 4.81279 27.6104 3.80822 27.6104C2.80365 27.6104 2 28.5099 2 29.5093V44.202C2 45.2015 2.80365 46 3.80822 46H44.1918C45.1963 46 46 45.2015 46 44.202V29.5093C46 28.5099 45.1963 27.7113 44.1918 27.7113C43.1872 27.7113 42.3836 28.5099 42.3836 29.5093V42.3021H5.61644V29.4104Z">
                        </path>
                    </svg>
                </div>
            </i>변경
            <input type="file" id="changeFileInput" accept="image/*" data-index="1" multiple="" values="https://tumblbug-pci.imgix.net/23132d1b1603bf67d41cae29a8bdeccf17b07d13/8002be7d5c77ab6e0b8020b1340198e8c37c611a/c28b4e86405484b4ad12bafd38ea47912c705ece/05388a1d-65de-4194-bd85-40c6ee9a9443.png?auto=format%2Ccompress&amp;fit=crop&amp;h=465&amp;lossless=true&amp;w=620&amp;s=3b75e10a7ae02059bd05b1fc6bb9c58e,https://tumblbug-pci.imgix.net/23132d1b1603bf67d41cae29a8bdeccf17b07d13/8002be7d5c77ab6e0b8020b1340198e8c37c611a/c28b4e86405484b4ad12bafd38ea47912c705ece/f9dfada5-495f-4550-a2c5-8ebd8bfa13ca.png?auto=format%2Ccompress&amp;fit=crop&amp;h=465&amp;lossless=true&amp;w=620&amp;s=213697091fbcd8643b46bb0f5eea56ed"></button>
        <button type="button" value="1" class="ImageStepper__StyledRemoveButton-p2ixf6-5 removeBt removeBt">
            <div name="delete" class="icon-svg">
                <svg viewBox="0 0 48 48">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                        d="M38.814 42.172C38.814 42.946 38.064 43.574 37.144 43.574H10.856C9.936 43.574 9.186 42.946 9.186 42.172V12.218H38.814V42.172ZM17.564 4.426L30.542 4.524V9.794H17.462L17.564 4.426ZM44.786 9.794H32.968V4.524C32.968 3.13 31.832 2 30.436 2H17.564C16.168 2 15.03 3.13 15.03 4.524V9.794H3.212C2.542 9.794 2 10.336 2 11.006C2 11.676 2.542 12.218 3.212 12.218H6.76V42.172C6.76 44.284 8.598 46 10.856 46H37.144C39.402 46 41.24 44.284 41.24 42.172V12.218H44.786C45.456 12.218 46 11.676 46 11.006C46 10.336 45.456 9.794 44.786 9.794ZM18.857 36.9338C19.527 36.9338 20.069 36.3918 20.069 35.7218V20.0738C20.069 19.4038 19.527 18.8618 18.857 18.8618C18.187 18.8618 17.645 19.4038 17.645 20.0738V35.7218C17.645 36.3918 18.187 36.9338 18.857 36.9338ZM30.3542 35.7218C30.3542 36.3918 29.8122 36.9338 29.1422 36.9338C28.4722 36.9338 27.9302 36.3918 27.9302 35.7218V20.0738C27.9302 19.4038 28.4722 18.8618 29.1422 18.8618C29.8122 18.8618 30.3542 19.4038 30.3542 20.0738V35.7218Z">
                    </path>
                </svg>
            </div>
        </button>
    </div>
</div>
</li>

`

function handleFiles(files) {
    for (let i = 0; i < files.length; i++) {
        /* 파일절대경로얻기 */  
        const file = files[i];
        const reader = new FileReader();

        /* reader가 onload 할때 */
        reader.onload = function(event) {
            let result = event.target.result;
            /* 썸네일 담을 div에 절대경로 넣어주기 */
            $('.preview').attr('src', result);
            /* 삭제버튼 선언 */
            const closeButton = document.querySelector(".removeBt");
            
            /* 삭제버튼 누를 시 x버튼과 backgroundImage 지워주기 */
            closeButton.addEventListener('click', function (e) {
                e.preventDefault();
                console.log("클릭됨");
                file.value = "";
                $('.imgList').empty();
                $('.preview').attr('src', '');
                $('.uploadWrap').show();
            });

            /* 사진이 한개 추가됐을 때 버튼 숨기기 */
            if($('.ImgStyleList').length = 4) {
                $('.uploadWrap').hide();
            }

        };
        /* result 속성(attribute)에 담기 */
        reader.readAsDataURL(file);  

        /* 파일썸네일 레이아웃 append */
        $imgList.append(nextImage);  
    }
}

function changeFiles(files) {
    for (let i = 0; i < files.length; i++) {
        /* 파일절대경로얻기 */  
        const file = files[i];
        const reader = new FileReader();

        /* reader가 onload 할때 */
        reader.onload = function(event) {
            let result = event.target.result;
            /* 썸네일 담을 div에 절대경로 넣어주기 */
            $('.preview').attr('src', result);
        };

        /* result 속성(attribute)에 담기 */
        reader.readAsDataURL(file);    
    }
}


/* 사진버튼을 감싸고있는 label객체 들고오기 */
const fileInput = document.getElementById("imageFile");


/* 버튼을 감싸고있는 label객체 클릭하면 위에 function handleFiles 실행 */
fileInput.addEventListener("change", function(event) {
    handleFiles(event.target.files);

    /* 변경 파일 객체 들고오기 */
    const changeFileInput = document.getElementById("changeFileInput");

    changeFileInput.addEventListener("change", function(event) {
        changeFiles(event.target.files);
    });
});