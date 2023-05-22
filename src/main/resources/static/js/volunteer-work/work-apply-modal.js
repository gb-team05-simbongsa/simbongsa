const $open = $('.apply-btn');
const $layout = $('.apply-layout');
// const $layout = $('.all-layout');
const $close = $('.apply-close');
// const $open = $('#work-open');

let text = `<div class="apply-layout" style="display: none;">
             <div class="apply-container">
                 <div class="apply-layer">
                     <p class="apply-title">자원봉사자참여신청</p>
                     <form name="goForm"  th:action="/volunteerWork/work-detail">
                         <!-- 로그인 정보 DB로 숨겨서 들어감 -->
                         <!-- <input type="hidden" id="seq" name="seq" value="">
                         <input type="hidden" id="id" name="id" value="">
                         <input type="hidden" id="password" name="password" value="">
                         <input type="hidden" id="" name="" value="N">
                         <input type="hidden" id="" name="" value="Y"> -->
                         <p class="apply-star-title">* 표시는 필수입력사항 입니다</p>

                         <table class="apply-content-container">
                             <colgroup>
                                 <col style="width:30%">
                                 <col style="width:70%">
                             </colgroup>
                             <tbody>

                             <tr>
                                 <th scope="row">* 이름</th>
                                 <td>
                                     <th:block th:if="${memberInfo.memberName == null}">
                                     <input type="text" name="memberName"  value="" readonly="readonly" title="이름" class="apply-text">
                                     </th:block>
                                     <th:block th:if="${memberInfo.memberName}">
                                     <input type="text" name="memberName" th:value="${memberInfo.memberName}"  value="" readonly="readonly" title="이름" class="apply-text">
                                     </th:block>
                                 </td>
                             </tr>
                             <tr>
                                 <th scope="row">* 연락처</th>
                             </tr>
                             <tr>
                                 <th scope="row">* 이메일</th>

                                 <td>
                                     <th:block th:if="${memberInfo.memberEmail == null}">
                                         <input type="text" name="memberEmail" value="" title="이메일" class="apply-text">
                                     </th:block>
                                     <th:block th:if="${memberInfo.memberEmail}">
                                         <input type="text" name="memberEmail" title="이메일" class="apply-text" th:value="${memberInfo.memberEmail}">
                                     </th:block>
                                 </td>
                             </tr>
                             <tr>
                                 <th scope="row">* 봉사 날짜</th>
                                 <td>
                                     <input type="date" name="volunteerWorkActivityDate" value="" title="날짜" class="apply-text">
                                 </td>
                             </tr>
                             </tbody>
                         </table>
                     </form>
                 </div>

                 <!-- 신청하기 버튼 -->
                 <div class="apply-btn-layout">
                     <button type="button" class="apply-btn" onclick="">신청하기</button>
                 </div>

                 <button type="button" class="apply-close">X</button>
                 <!-- <img src="/static/image/volunteer-work/close.png"> -->


             </div>
         </div>
`;


$(document).ready(function(){

// 버튼 클릭시 모달창 띄우기
    $open.click(function(){
        $layout.show();
    });

// x버튼 클릭시 모달창 닫기
    $close.click(function(){
        $layout.hide();
    })
// 전송 
// $submit.click(function(){
//     document.$submit();
// });

});