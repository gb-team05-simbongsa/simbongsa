// 카테고리 옵션
const $selectOption = $(".select-option");
// 카테고리 박스
const $inputSelectWrap = $(".input-select-wrap");
// 카테고리 종류
const $customOption = $(".customOption>button");
// 카테고리창 input
const $innerInput = $('.inputCategory');

// svg
const $inputSvg = $('.input-select-wrap svg');

// 카테고리 창 선택 JS
$inputSelectWrap.on('click', function(){
    if($selectOption.css('display')=='block'){
        $selectOption.css('display', 'none');
    }else{
        $selectOption.css('display', 'block');
    }
    $customOption.each((i, e) => {
        var text = $customOption.eq(i).text();
        $customOption.eq(i).on('click', function(){
            $innerInput.val(text);
            $selectOption.css('display', 'none');
            $inputSvg.css('transform', 'rotate(0deg)')
        });
    })   
});
// 긴글 count
const $longCountLength = $(".long-count");
// 긴글 input
const $longInput = $(".longInput");
// 짧은글 count
const $shortCountLength = $(".short-count");
// 짧은글 input
const $shortInput = $(".shortInput");
// textarea
const $textarea = $('.textarea-style');
// textareaCount
const $textareaCount = $('.textareaCount');
const $inputCount = $('.inputCount');
const $inputText = $('.inputText');

$longInput.keyup((e)=>{
    let $content = $longInput.val();

    if($content.length == 0 || $content == ''){
        $longCountLength.text('0/32');
         
    }else{
        $longCountLength.text($content.length + '/32');
    }
});
$shortInput.keyup((e)=>{
    let $content = $shortInput.val();

    if($content.length == 0 || $content == ''){
        $shortCountLength.text('0/7');
    }else{
        $shortCountLength.text($content.length + '/7');
    }
});
$textarea.keyup((e)=>{
    let $content = $textarea.val();

    if($content.length == 0 || $content == ''){
        $textareaCount.text('0/50');
    }else{
        $textareaCount.text($content.length + '/50');
    }
});

$inputText.each((i,e)=>{
    $inputText.keyup(()=>{
        if($inputText.eq(i).val()){
            $inputCount.eq(i).css('color', 'rgb(158, 158, 158)');
            $('.inputBorder').eq(i).css('border', '1px solid rgb(158, 158, 158)')
        }else{
            $inputCount.eq(i).css('color', 'rgb(244, 69, 68)');
            $('.inputBorder').eq(i).css('border', '1px solid rgb(244, 69, 68)')
        }
    })
    
})

// 모달
$('.expendButton').on('click', function(){
    $('.modal-layer').css('display', 'block');


    $('.modalCloseButton').on('click', function(){
        $('.modal-layer').css('display', 'none');

    })
})

// 슬라이드
$('.label-areaToolTip').each((i,e)=>{
    $('.label-areaToolTip').eq(i).on('click',function(){
        $('div.areaTooltip').eq(i).slideToggle(300);
    }) 
})