/*$('#comment-id-button').click(function(){
    console.log("클릭됨");
    $('#comment-id-button').attr('aria-selected', 'true');
    $('#join-id-button').attr('aria-selected', 'false');
    $('#comment-tabpanel').attr('aria-hidden', 'false');
    $('#history-tabpanel').attr('aria-hidden', 'true');
})

$('#join-id-button').click(function(){
    console.log("클릭됨");
    $('#comment-id-button').attr('aria-selected', 'false');
    $('#join-id-button').attr('aria-selected', 'true');
    $('#comment-tabpanel').attr('aria-hidden', 'true');
    $('#history-tabpanel').attr('aria-hidden', 'false');
})*/

const $button = $('.img-div');

/* 첫 페이지 로딩될 때 첫번째 img 띄워주기 */
let $firstImage = $('.img-div').attr("src");
$("#expandedImg").attr("src", $firstImage);

$button.on('click', function(){
    console.log("클릭됨");
    let $imgUrl = $(this).attr("src").replace(/^url\(['"](.+)['"]\)/, '$1');
    let $image = $imgUrl.replace("http://127.0.0.1:5500","");
    console.log($image);


    $("#expandedImg").attr("src", $image);
    $("#expandedImg").css('width', '100%');
});