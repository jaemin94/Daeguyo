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



  async function deleteOrder(orderId) {
      try {
          // 서버에 주문 삭제 요청을 보냅니다.
          const response = await fetch('/delete-order', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify({ orderId: orderId }),
          });

          if (response.ok) {
              // 삭제 요청이 성공하면 해당 주문을 화면에서 제거합니다.
              const orderElement = document.querySelector(`.orderstatus-deletebtn[data-order-id="${orderId}"]`).closest('.s1');
              orderElement.remove();
          } else {
              console.error('주문 삭제에 실패했습니다.');
          }
      } catch (error) {
          console.error('오류 발생:', error);
      }
  }

  // 주문 삭제 버튼 클릭 이벤트 처리
  document.querySelectorAll('.orderstatus-deletebtn').forEach(button => {
      button.addEventListener('click', async () => {
          const orderId = button.dataset.orderId;
          await deleteOrder(orderId);
      });
  });
});
