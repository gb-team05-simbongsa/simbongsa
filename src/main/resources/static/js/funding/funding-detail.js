// const rewardSection = document.querySelector('.rewardSection');
// const stepperWrapper = document.querySelector('.stepperWrapper');
// const stepperWrap = document.querySelector('.stepperWrap');

// rewardSection.addEventListener('click', () => {
//   stepperWrapper.style.height = '377px';
//   stepperWrap.style.display = 'block';
// });


$(document).ready(function(){
    $("#flip").click(function(){
      $("#panel").slideToggle("slow");
    });
  });