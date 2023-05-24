const $image = $('.my-rank img');
console.log("memberDTO.memberRank: " +memberDTO.memberRank);
const $rank = memberDTO.memberRank;
$(document).ready(function () {
    if ($rank === "동냥1티어") {
        $image.attr('src', "/image/main/bronze1.png");
    } else if ($rank === "동냥2티어") {
        $image.attr("src", "/image/main/bronze2.png");
    } else if ($rank === "동냥3티어") {
        $image.attr("src", "/image/main/bronze3.png");
    } else if ($rank === "은냥1티어") {
        $image.attr("src", "/image/main/silver1.png");
    } else if ($rank === "은냥2티어") {
        $image.attr("src", "/image/main/silver2.png");
    } else if ($rank === "은냥3티어") {
        $image.attr("src", "/image/main/silver3.png");
    } else if ($rank === "금냥1티어") {
        $image.attr("src", "/image/main/gold1.png");
    } else if ($rank === "금냥2티어") {
        $image.attr("src", "/image/main/gold2.png");
    } else if ($rank === "금냥3티어") {
        $image.attr("src", "/image/main/gold3.png");
    }
});