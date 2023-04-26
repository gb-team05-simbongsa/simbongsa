$('#comment-id-button').click(function(){
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
})