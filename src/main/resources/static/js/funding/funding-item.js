const $submitBtn = $('.saveButton');
const $removeBtn = $('.removeButton');


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
        success: function(result) {
            append($('#itemTitle').val(), $('input[name=itemType]:checked').val(), result);
            console.log("승전보");
        }
    });
})

// let id = fundingItemDTO.itemId;
console.log($removeBtn);
function removeItem(itemId, e) {
    $.ajax({
            url: "/funding/funding-item-delete",
            type: "post",
            data: { itemId : itemId },
            traditional : true,
             success : function(result){
                 removeButton(e);
                if(result) {
                location.reload();
            }
        }
    });

}