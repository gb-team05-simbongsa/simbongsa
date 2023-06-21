
// Option box 선택
$('#selectOp').change(function () {
    console.log($(this).val());
    $('#inputOp').val($(this).val());

    console.log($('#inputOp').val());
    var keyword;

    if($(this).val() == "참여 많은순"){
        keyword = "참여 많은순";
    }else if($(this).val() == "참여 적은순"){
        keyword = "참여 적은순";
    }else{
        keyword = null;
    }
    $('#inputOp').val(keyword);
    console.log($('#inputOp').val(keyword))
    ;
    document.goForm.submit();
})
