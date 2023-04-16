var date = new Date();
// 달력연도
var calendarYear = date.getFullYear();
// 달력 월
var calendarMonth = date.getMonth() +1;
// 달력 일
var calendarToday = date.getDate();

const monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
// 윤년 계산
if (calendarYear % 400 == 0) {
	monthDays[1] = 29;
} else if (calendarYear % 100 == 0) {
	monthDays[1] = 28;
} else if (calendarYear % 4 == 0) {
	monthDays[1] = 29;
}

function calendar(date) {
	// 달력 연도
	var calendarYear = date.getFullYear();
	// 달력 월
	var calendarMonth = date.getMonth() + 1;
	// 달력 일
	var calendarToday = date.getDate();
	
	var monthLastDate = new Date(calendarYear, calendarMonth, 0);
	// 달력 월의 마지막 일
	var calendarMonthLastDate = monthLastDate.getDate();
	
	var monthStartDay = new Date(calendarYear, date.getMonth(), 1);
	// 달력 월의 시작 요일
	var calendarMonthStartDay = monthStartDay.getDay();
	
	// 주 카운트	
	var calendarWeekCount = Math.ceil((calendarMonthStartDay + calendarMonthLastDate) / 7);
	
	// 년월
	$(".calendar-yearmonth").html(calendarYear + "." + calendarMonth);
	
	// 오늘
	var today = new Date();
	var html = "";
	html += "<table>";
	html += "<thead>";
	html += "<tr>";
	html += "<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>";
	html += "</tr>";
	html += "</thead>";
	html += "<tbody>";
	// 위치
	var calendarPos = 0;
	// 날짜
	var calendarDay = 0;
	for (var index1 = 0; index1 < calendarWeekCount; index1++) {
		html += "<tr>";
		for (var index2 = 0; index2 < 7; index2++) {
			html += "<td>";
			if (calendarMonthStartDay <= calendarPos && calendarDay < calendarMonthLastDate) {
				calendarDay++;
				html += "<div class= \"wrap\"><span class=\"date\"";
				if (calendarYear == today.getFullYear() && calendarMonth == today.getMonth() + 1
					&& calendarDay == today.getDate()) {
					html += "class=\"today\"";
				}
				html += ">" + calendarDay + "</span><a class=\"item\">(마감)Test</a></div>";
                console.log(calendarDay);
                /* AJAX를 써서 a태그에 정보를 입력해야 할듯 
                1. 봉사이름 
                2. 봉사시작 [시작]
                3. 봉사마감 [마감]
                if문으로 값이 있으면 display:visibility or block 으로 일정 표시 하면 될듯?
                */
			}
			html += "</td>";
			calendarPos++;
		}
		html += "</tr>";
	}
	html += "</tbody>";
	html += "</table>";
    
	$("#calendar").html(html);
}

calendar(new Date());

// 이전 달 이동 버튼 클릭
$(".prev").on("click", function(e) {
    e.preventDefault();
	var yearmonth = $(".calendar-yearmonth").text().split(".");
	calendar(new Date(parseInt(yearmonth[0]), parseInt(yearmonth[1]) - 2, 1));
});

// 다음 달 이동 버튼 클릭
$(".next").on("click", function(e) {
    e.preventDefault();
	var yearmonth = $(".calendar-yearmonth").text().split(".");
	calendar(new Date(parseInt(yearmonth[0]), parseInt(yearmonth[1]), 1));
});
