document.addEventListener("DOMContentLoaded", function() {
  var statusTextElements = document.querySelectorAll('.status-text');
  var reviewButtons = document.querySelectorAll('.reviewbtn');
  var cancelButtons = document.querySelectorAll('.cancelledbtn');

  statusTextElements.forEach(function(element) {
      var statusText = element.textContent.trim();

      if (statusText === "주문취소") {
          element.classList.add('cancelled-text');
      }

      if (statusText === "주문완료") {
          reviewButtons.forEach(function(btn) {
              btn.classList.remove('hidden');
          });
          cancelButtons.forEach(function(btn) {
              btn.classList.add('hidden');
          });
      } else if (statusText === "주문취소") {
          reviewButtons.forEach(function(btn) {
              btn.classList.add('hidden');
          });
          cancelButtons.forEach(function(btn) {
              btn.classList.remove('hidden');
          });
      }
  });
});
