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



//function deleteOrder() {
//var order = document.querySelector("#order").value;
// console.log("나오나요====================="+order)
//    if (confirm('정말로 주문을 삭제하시겠습니까?')) {
//        axios.delete('/deleteOrder/' + order)
//            .then(function (response) {
//                location.reload();
//
//            })
//            .catch(function (error) {
//                console.error('주문 삭제 실패:', error);
//            });
//    }
function deleteOrder(btnElement) {
    var order = btnElement.closest('.s1').querySelector("#order").textContent;
    console.log("나오나요====================="+order)
    if (confirm('정말로 주문을 삭제하시겠습니까?')) {
        axios.delete('/deleteOrder/' + order)
            .then(function (response) {
                location.reload();
            })
            .catch(function (error) {
                console.error('주문 삭제 실패:', error);
            });
    }
}