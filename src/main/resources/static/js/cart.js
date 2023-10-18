


 function handlePayment(res_id) {
     var orderName = res_id;
     var totalAmount = parseInt(document.getElementById('amount_price').innerHTML.split("원")[0].replace(/,/g, ''));
     var resName = document.getElementById('res-id').textContent;


     var customer = {
         customerId: nickname,
         phoneNumber: phone
     };

     var paymentId = 'paymentId' + new Date().getTime();
     let orderId = 'orderId-' + new Date().getTime();

     PortOne.requestPayment({
         storeId: 'store-e41c5842-2d91-4a60-90bf-3b70da11378f',
         paymentId: paymentId,
         orderName: resName,
         totalAmount: totalAmount,
         customer: customer,
         currency: 'CURRENCY_KRW',
         channelKey: 'channel-key-d184ee7f-a639-44ef-81b3-d1aeb4eba934',
         payMethod: "CARD"

     }).then(function (response) {


             if (response.transactionType === "PAYMENT") {

                 var PaymentData = {
                     payment_id: paymentId,
                     order_id : orderId,
                     pay_method : "CARD",
                     pay_date : new Date(),
                     pay_status : "Waiting"
                 };

                 axios.post('/payment/save', PaymentData)
                      .then(function(response) {
                           console.log('Payment data saved successfully.');
                            // 메뉴이름 저장하는 배열
                           let menuNames = [];
                           let menuIds = [];
                           // 옵션 저장하는 배열
                           let selectedOptions = [];
                           let menuItems = document.querySelectorAll('.main_menu');
                           // 수량 카운트 변수
                           let countElement = 0;
                           // 각 메뉴 아이템에 대해 반복문을 실행
                           menuItems.forEach(function(menuItem) {

                               let menuId = menuItem.querySelector('.menu_name_box span').dataset.menuId;
                               let menuNameElements = menuItem.querySelector('.menu_name_box span'); //menuId

                               let selectedOptionElements =  menuItem.querySelector('.name_price ul li span:first-child'); //selectoption
                               countElement = countElement + parseInt(menuItem.querySelector('.quantity .amount').innerHTML); //amount
                                  if (menuId) { // 해당 메뉴 아이템에 data-menu-id 값이 존재하면
                                          menuIds.push(menuId); // menuIds 배열에 추가
                                      }
                                   // 텍스트 내용을 menuNames 배열에 추가
                                   if (menuNameElements) {
                                       menuNames.push(menuNameElements.textContent.trim());
                                   }
                                   // 텍스트 내용을 selectedOptions  배열에 추가
                                   if (selectedOptionElements) {
                                       selectedOptions.push(selectedOptionElements.textContent.trim());
                                   }

                            });
                            // 모든 메뉴 이름들을 ,로 연결한 문자열로 변환하여 저장합니다.
                            // tbl_order에 menu_name을 보낼때 사용
                               let menuNameElement = menuNames.join(',');
                            // 모든 옵션들을 ,로 연결한 문자열로 변환하여 저장합니다.
                               let selectedOptionElement  = selectedOptions.join(',');

                               let menu_Id = menuIds.join(',');
                            axios.post('/create',{ order_id : PaymentData.order_id,
                                                  u_email : u_Email,
                                                  menu_id : menu_Id,
                                                  res_id : orderName,
                                                  selected_option : selectedOptionElement,
                                                  order_amount : countElement,
                                                  total_price : totalAmount,
                                                  coupon_id : selectedCouponId,
                                                  order_status : PaymentData.pay_status,
                                                  order_date : PaymentData.pay_date})
                                 .then(function(response) {
                                     console.log('Order created successfully.');
                                 })
                                 .catch(function(error) {
                                     console.error('Error creating order:', error);
                                 });
                       }).catch(function(error){
                             console.error('Error saving payment data:', error);
                       });
             } else {
                  console.error('Payment failed:', response);
             }
     }).catch(function (error) {
         console.error('Error:', error);
     });
 }
  // 이 부분이 수정된 부분입니다. 기존에 두 개였던 닫는 중괄호를 하나로 줄였습니다.





    function decreaseAmount(buttonElement) { //버튼 요소를 인자로 받아, 해당 버튼 다음에 위치한 요소(수량을 표시하는 span 태그를 가정)의 텍스트 값을 1만큼 감소시킵니다.
        var amountSpan = buttonElement.nextElementSibling; // 버튼 다음에 위치한 요소를 선택
        var currentAmount = parseInt(amountSpan.textContent); // 현재 수량을 가져와 정수형으로 변환

        if (currentAmount > 0) {
            amountSpan.textContent = currentAmount - 1; // 현재 수량에서 1을 뺀 값을 다시 span 태그의 내용으로 설정합니다.
        }
    }

    function increaseAmount(buttonElement) {
        var amountSpan = buttonElement.previousElementSibling;// 버튼 이전에 위치한 요소를 선택
        var currentAmount = parseInt(amountSpan.textContent);// 현재 수량을 가져와 정수형으로 변환

        amountSpan.textContent = currentAmount + 1;  // 현재 수량에서 1을 더한 값을 다시 span 태그의 내용으로 설정합니다
    }



     function changeAmount(buttonElement, delta, cart_id, price) {

        // 마이너스 버튼이 클릭되었으면 다음요소인 수량표시를 선택하고 아니라면 이전요소를 선택
        var amountSpan = buttonElement.className === 'minus' ? buttonElement.nextElementSibling : buttonElement.previousElementSibling;
        // 현재 수량을 가져와서 정수로 변환하고 콤마를 제거
        var currentAmount = parseInt(amountSpan.textContent.replace(/,/g,''));
        // 현재수량 + 변화량이 0이상일때
        if (currentAmount + delta >= 0) {
        // 현재 수량에서 변화량을 더한 값을 수량표시에 설정
            amountSpan.textContent = (currentAmount + delta).toLocaleString('ko-KR');

            axios.post('/updateOrder', { cart_id : cart_id , count : currentAmount + delta})
            .then(function (response) {
                //상품가격
                var all_price = parseInt(document.getElementById('pap').innerText.replace(/,/g,''));
                //총 상품가격
                var amount_price = parseInt(document.getElementById('amount_price').innerText.replace(/,/g,''));

                var newPrice = 0
                //수량조절시 상품금액 추가,차감
                if(delta < 0){
                    newPrice = all_price - price;

                }else{
                    newPrice = all_price + price;

                }
                //할인금액
               let dedElement = document.getElementById('deducted');
               let numberOnly = 0;
               if (dedElement) {
               // 할인금액 문자열에서 앞,뒤 공백 제거
                   let dedText = dedElement.innerText.trim();

                    //할인금액을 '원'문자제거, 콤마제거, 공백제거 후 정수로 변환
                   numberOnly = parseInt(dedText.replace(/원/g, '').replace(/,/g, '').replace(/\s/g, ''));
               }


             //총 상품금액 - 할인금액
             let total = newPrice + numberOnly;

             // 상품금액 업데이트
               document.getElementById('pap').innerText = newPrice.toLocaleString('ko-KR') + "원";
             // 총상품금액 업데이트
               document.getElementById('amount_price').innerText = total.toLocaleString('ko-KR') + "원";

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

  var totalDeduction = 0; // 전역 변수선언 할인 금액
var selectedCouponId = 0; ///create에 보낼 coupon_id값
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
                selectedCouponId = coupon_id // 선택된 쿠폰 ID를 저장
                var all_price = parseInt(document.getElementById('pap').innerText.replace(/,/g,'')); //상품금액

               totalDeduction += deductedPrice; // 새로운 할인 금액을 기존 값에 더해줍니다.



               var newPrice = all_price - totalDeduction; //실제 결제 금액
               //할인금액 표시
               document.getElementById('deducted').innerText = "- " + totalDeduction.toLocaleString('ko-KR') + "원";
               //결제예정 금액
               document.getElementById('amount_price').innerText = newPrice.toLocaleString('ko-KR') + "원";
           })
           .catch(function (error) {
                // Handle error here
                console.error(error);
            });
   }

