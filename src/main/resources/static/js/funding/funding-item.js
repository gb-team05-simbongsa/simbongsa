const $submitBtn = $('.saveButton');

$submitBtn.on("click", function () {
    let content = '';
    if($('input[name=itemType]:checked').val() == '주관식') {
        content = $('.textareaJu').val()
    } else {
        $('.value').each(function() {
            console.log($(this).text())
            content += $(this).text() + '/';
        });
    }

    $.ajax({
        url: "/funding/funding-item",
        type: "post",
        data:{ title : $('#itemTitle').val(), content : content, itemType : $('input[name=itemType]:checked').val() },
        async: false,
        success: function() {
            append($('#itemTitle').val(), $('input[name=itemType]:checked').val());
            console.log("승전보");
        }
    });
})
