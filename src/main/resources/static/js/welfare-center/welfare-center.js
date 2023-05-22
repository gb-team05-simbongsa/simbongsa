let type = 'GN';

let getList = function(callback) {
    $.ajax({
        url: "http://openapi.seoul.go.kr:8088/7852734252746f6e3537597746496d/json/fcltOpenInfo_" + type + "/1/1000/",
        type: "get",
        success: function(result) {
            if(callback) {
                callback(result);
            }
        }
    });
}

function setList(center) {
    let text = `
        <tr>
            <td class="facility-name">${center.FCLT_NM}</td>
            <td>${center.JRSD_SGG_NM}</td>
            <td>${center.FCLT_ADDR}</td>
            <td>${center.FCLT_KIND_DTL_NM}</td>
            <td>${center.RPRSNTV}</td>
            <td>${center.FCLT_TEL_NO}</td>
        </tr>
    `;
    return text;
}

getList(function(result) {

    let text = ``;
    result.fcltOpenInfo_GN.row.forEach(center => {
        text += setList(center);
    });
    $('.content').html(text);
});

$('.welfare-center-search-button').on('click', function () {
    let select = $('#address-second').val();

    switch(select) {
        case "강남구" :
            type = "GN";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GN.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "강동구" :
            type = "GD";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GD.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "강북구" :
            type = "GB";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GB.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "강서구" :
            type = "GS";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GS.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "관악구" :
            type = "GA";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GA.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "광진구" :
            type = "GJ";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GJ.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "구로구" :
            type = "GR";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GR.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "금천구" :
            type = "GC";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_GC.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "노원구" :
            type = "NW";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_NW.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "도봉구" :
            type = "DB";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_DB.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "동대문구" :
            type = "DD";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_DD.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "동작구" :
            type = "DJ";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_DJ.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "마포구" :
            type = "MP";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_MP.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "서대문구" :
            type = "SM";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_SM.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "서초구" :
            type = "SC";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_SC.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "성동구" :
            type = "SD";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_SD.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "성북구" :
            type = "SB";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_SB.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "송파구" :
            type = "SP";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_SP.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "양천구" :
            type = "YC";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_YC.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "영등포구" :
            type = "YD";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_YD.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "용산구" :
            type = "YS";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_YS.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "은평구" :
            type = "EP";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_EP.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "종로구" :
            type = "JN";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_JN.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "중구" :
            type = "JG";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_JG.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;

        case "중랑구" :
            type = "JR";
            getList(function(result) {
                let text = ``;
                result.fcltOpenInfo_JR.row.forEach(center => {
                    text += setList(center);
                });
                $('.content').html(text);
            });
            break;
    }


});

