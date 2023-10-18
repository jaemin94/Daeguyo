


 function handlePayment(res_id) {
     var orderName = res_id;
     var totalAmount = parseInt(document.getElementById('amount_price').innerHTML.split("원")[0].replace(/,/g, ''));
     var coupon_id = document.getElementById('coupon');
     var customer = {
         customerId: nickname,
         phoneNumber: phone
     };

     var paymentId = 'paymentId' + new Date().getTime();
     let orderId = 'orderId-' + new Date().getTime();
    PortOne.requestPayment({
        storeId: 'store-e41c5842-2d91-4a60-90bf-3b70da11378f',
        paymentId: paymentId,
        orderName: orderName,
        totalAmount: totalAmount,
        customer: customer,
        currency: 'CURRENCY_KRW',
        channelKey: 'channel-key-d184ee7f-a639-44ef-81b3-d1aeb4eba934',
        payMethod: "CARD",
    }).then(function (response) {
            if (response.transactionType === "PAYMENT") {

                var PaymentData = {
                    payment_id: paymentId,  // 위에서 생성한 paymentId 사용
                    order_id:orderId,  // 실제 주문 ID로 대체해야 함.
                    pay_method:"CARD",
                    pay_date:new Date(),
                    pay_status:"Waiting"
                };

                axios.post('/payment/save', PaymentData)
                      .then(function(response) {
                          console.log('Payment data saved successfully.');

                          // UUID for the orderId


                          // Get menu items from the page
                          let menuItems = document.querySelectorAll('.main_menu');

                          menuItems.forEach(function(menuItem) {
                              // Read necessary information from the menuItem element
                              let menuNameElement = menuItem.querySelector('.menu_name_box span');
                              let selectedOptionElement = menuItem.querySelector('.name_price ul li span:first-child');
                              let countElement = menuItem.querySelector('.quantity .amount');

                              if (menuNameElement && selectedOptionElement && countElement) {
                                  // Create a cartDto object for this menu item and send it to server

                                  var cartDto ={
                                      order_id : orderId,
                                      u_email : u_Email,
                                      menu_id : menuNameElement.innerText,
                                      res_id : orderName,
                                      selected_option : selectedOptionElement.innerText,
                                      count : countElement.innerText,
                                      total_price : totalAmount,  // This should be changed to the price of this particular item if available
                                      coupon_id :coupon_id,
                                      order_status : PaymentData.pay_status,
                                      order_date : PaymentData.pay_date
                                  }

                                  axios.post('/create', cartDto)
                                         .then(function(response) {
                                             console.log('Order created successfully.');
                                         })
                                         .catch(function(error) {
                                             console.error('Error creating order:', error);
                                         });
                                 }
                             })
                         }).catch(function(error){
                             console.error('Error saving payment data:', error);
                         });
             } else {
                 console.error('Payment failed:', response);
             }
         }).catch(function (error) {
             console.error('Error:', error);
         });
 }  // 이 부분이 수정된 부분입니다. 기존에 두 개였던 닫는 중괄호를 하나로 줄였습니다.





    function decreaseAmount(buttonElement) {
        var amountSpan = buttonElement.nextElementSibling;
        var currentAmount = parseInt(amountSpan.textContent);

        if (currentAmount > 0) { // Change this condition if you want a minimum limit other than 0
            amountSpan.textContent = currentAmount - 1;
        }
    }

    function increaseAmount(buttonElement) {
        var amountSpan = buttonElement.previousElementSibling;
        var currentAmount = parseInt(amountSpan.textContent);

        amountSpan.textContent = currentAmount + 1;
    }



     function changeAmount(buttonElement, delta, cart_id, price) {
        var amountSpan = buttonElement.className === 'minus' ? buttonElement.nextElementSibling : buttonElement.previousElementSibling;
        var currentAmount = parseInt(amountSpan.textContent.replace(/,/g,''));

        if (currentAmount + delta >= 0) {
            amountSpan.textContent = (currentAmount + delta).toLocaleString('ko-KR');

            axios.post('/updateOrder', { cart_id : cart_id , count : currentAmount + delta})
            .then(function (response) {

                var all_price = parseInt(document.getElementById('pap').innerText.replace(/,/g,''));
                var amount_price = parseInt(document.getElementById('amount_price').innerText.replace(/,/g,''));
                var newPrice = 0
                if(delta < 0){
                    newPrice = all_price - price;

                }else{
                    newPrice = all_price + price;

                }
                document.getElementById('pap').innerText = newPrice.toLocaleString('ko-KR') + "원";
                document.getElementById('amount_price').innerText = newPrice.toLocaleString('ko-KR') + "원";

            })
            .catch(function (error) {
                console.error(error);
            });
        }
    }









    function orderdelete(cartId){
        axios.post('/cart/delete', { cart_id : cartId })
            .then(function (response) {
                // Handle successful response here
                console.log(response);
            })
            .catch(function (error) {
                // Handle error here
                console.error(error);
            });
    }

  var totalDeduction = 0; // 전역 변수로 총 할인 금액을 추적합니다.

  function select_coupon(coupon_id, deductedPrice){
      axios.post('/cart/coupon', { coupon_id : coupon_id })
          .then(function (response) {
              //모달창 닫기
              document.getElementById('modal-toggle').checked = false;

              //쿠폰 쓴거 태그 지우기
              var couponTag = document.querySelector(`[data-coupon-id="${coupon_id}"]`);
              if (couponTag) {
                  couponTag.remove();
              }

              var amount_price = parseInt(document.getElementById('amount_price').innerText.replace(/,/g,''))

              totalDeduction += deductedPrice; // 새로운 할인 금액을 기존 값에 더해줍니다.

              var newPrice = amount_price - totalDeduction;

              document.getElementById('deducted').innerText = "- " + totalDeduction.toLocaleString('ko-KR') + "원";

              document.getElementById('amount_price').innerText = newPrice.toLocaleString('ko-KR') + "원";
          })
          .catch(function (error) {
               // Handle error here
               console.error(error);
           });
  }

