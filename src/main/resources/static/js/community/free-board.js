/* 스크롤 */
$(document).ready(function() {
    // 스크롤 위치 감지
    $(window).scroll(function() {
      if ($(this).scrollTop() > 100) {
        $('.scrollTop').fadeIn();
      } else {
        $('.scrollTop').fadeOut();
      }
    });
  
    // 맨 위로 스크롤 이동
    $('.scrollTop').click(function() {
      $('html, body').animate({
        scrollTop: 0
      }, 800);
      return false;
    });
  });

  /* 눌렀을 때 색 변환 - 활동후기, 자유*/
const headerTitle1 = document.querySelector('.header-title1');
const headerTitle2 = document.querySelector('.header-title2');

headerTitle1.addEventListener('click', () => {
  headerTitle1.style.color = '#1d1d1e';
  headerTitle1.style.fill = '#1d1d1e';
  headerTitle2.style.color = '#b9b9bb';
  headerTitle2.style.fill = '#b9b9bb';
});

headerTitle2.addEventListener('click', () => {
  headerTitle2.style.color = '#1d1d1e';
  headerTitle2.style.fill = '#1d1d1e';
  headerTitle1.style.color = '#b9b9bb';
  headerTitle1.style.fill = '#b9b9bb';
});

 /* 눌렀을 때 색 변환 - 최신글, 인기글*/
 const categoriNew = document.querySelector('.categori-new');
 const categoriBest = document.querySelector('.categori-best');
 
 categoriNew.addEventListener('click', () => {
    categoriNew.style.color = '#1d1d1e';
    categoriNew.style.fill = '#1d1d1e';
    categoriBest.style.color = '#8a8a8b';
    categoriBest.style.fill = '#8a8a8b';
 });
 
 categoriBest.addEventListener('click', () => {
    categoriBest.style.color = '#1d1d1e';
    categoriBest.style.fill = '#1d1d1e';
   categoriNew.style.color = '#8a8a8b';
   categoriNew.style.fill = '#8a8a8b';
 });
