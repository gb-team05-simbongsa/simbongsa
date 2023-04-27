// 카테고리 옵션
const $selectOption = $(".selectWithOp");
// 카테고리 박스
const $inputSelectWrap = $(".selectWithWrapper");
// 카테고리 종류
const $customOption = $(".customOption button");
// 카테고리창 input
const $innerInput = $('.categoryInput');


// svg
const $inputSvg = $('.slectedSingle svg');

// 카테고리 창 선택 JS
$inputSelectWrap.on('click', function(){
    if($selectOption.css('display')=='block'){
        $selectOption.css('display', 'none')
        $inputSvg.css('transform', 'rotate(180deg)')
    }else{
        $selectOption.css('display', 'block');
        $inputSvg.css('transform', 'rotate(0deg)')
    }
    

    $customOption.each((i, e) => {
        var text = $customOption.eq(i).text();
        $customOption.eq(i).on('click', function(){
            $innerInput.val(text);
            $inputSvg.css('transform', 'rotate(0deg)')
            console.log($(".selectWithOp"));
            $selectOption.fadeOut(200);
        });
        
        
    })
});



$('.registButton ').on('click', function(){
    $('.formClass1').css('display','none');
    $('.formClass2').css('display', 'block');     
})

const $contentCount = $('.contentLength');
const $contentInput = $('.innerInput');

$contentInput.keyup((e) =>{
    let $content = $contentInput.val();
    if($content.length == 0 || $content ==''){
        $contentCount.text('0/20');
    }else{
        $contentCount.text($content.length + '/20');

    }
    if($content){
        $('.inputWrapper').css('border', '1px solid rgb(240, 240, rgba(0, 0, 0, 0.87)');
        $contentCount.css('color', 'rgba(0, 0, 0, 0.87)');
    }else{
        $('.inputWrapper').css('border', '1px solid rgb(244, 69, 68)');
        $contentCount.css('color', 'rgb(244, 69, 68)');
    }
})

const $textAreaStyle = $('.textareaStyle');
const $textAreaCount = $('.textguideArea');

$textAreaStyle.keyup((e) =>{
    let $content = $textAreaStyle.val();
    if($content.length == 0 || $content ==''){
        $textAreaCount.text('0/300');
    }else{
        $textAreaCount.text($content.length + '/300');

    }
    if($content){
        $('.TextAreaWrap').css('border', '1px solid rgb(240, 240, rgba(0, 0, 0, 0.87)');
        $textAreaCount.css('color', 'rgba(0, 0, 0, 0.87)');
    }else{
        $('.TextAreaWrap').css('border', '1px solid rgb(244, 69, 68)');
        $textAreaCount.css('color', 'rgb(244, 69, 68)');
    }
})

