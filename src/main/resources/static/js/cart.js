$(document).ready(function () {
  var swiper1 = new Swiper(".mySwiper", {
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
    keyboard: true,
    loop:true ,
  });

  var swiper2 = new Swiper(".swiper2", {
     spaceBetween :30 ,
     centeredSlides :true ,
     autoplay:{
       delay :3000 ,
       disableOnInteraction:true
     },
     pagination:{
       el:".swiper-pagination",
       clickable:true
     },
     navigation:{
       nextEl:".swiper-button-next" ,
       prevEl:".swiper-button-prev"
     } ,
   keyboard:true ,
   loop:true
});

$(".hamburger_btn").click(function(){
 $(".side_menu").addClass("active");
});
$(".close").click(function(){
 $(".side_menu").removeClass("active");
});

$(".mainmenu > li").mouseenter(function(){
 $(this).addClass("active");
});

$("nav > ul > li").mouseleave(function(){
 $("header").removeClass("sub_active");
 $(this).removeClass("active");
});


var swiper3 = new Swiper('.Swiper3', {

 cssMode :true ,

 navigation :{
   nextEl : '.Swipersecond .swiper-button-next' ,
   prevEl : '.Swipersecond .swiper-button-prev' ,

 },

 pagination:{
   el :'.Swiper3 .swiper-pagination' ,

 },

 mousewheel:true ,
 keyboard:true ,

 loop :true ,

 autoplay:{delay :5000}

 });
      });

      let mapElement = document.querySelector('.map_bg'); // 변수명 변경

      let select = document.querySelector('.location_complete');
      let lct = document.querySelector('.current_location');
      let locationElement = document.querySelector('.location');
      select.addEventListener('click', function() { mapElement.style.display='none'; }); // 변수명 변경
      document.querySelector('.current_location').addEventListener('click', myFunction);

locationElement.addEventListener('click', function() {
  mapElement.style.display='block';
  myFunction(); // 지도 초기화 함수 호출
  });
      var mapContainer = document.getElementById('mapping'); // '맵'을 표시할 div 요소의 선택자입니다.
      var mapOption = { center:new kakao.maps.LatLng(33.450701,126.570667), level :3 }; // 지도의 중심 좌표

      // 전역변수로 선언한 지도 객체
      var map;

    function displayMarker(locPosition) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition
        });

        // geocoder 객체 생성
        var geocoder = new kakao.maps.services.Geocoder();

        // 마커에 클릭 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
            geocoder.coord2Address(locPosition.getLng(), locPosition.getLat(), function(result, status) {
                if (status === kakao.maps.services.Status.OK && result[0]) {
                    var placeName = result[0].address.address_name;
                    document.querySelector('.location_select').innerText = placeName;
                }
            });
        });

        map.setCenter(locPosition);
    }
      function myFunction() {
          var mapContainer = document.getElementById('mapping'),
              mapOption = { center: new kakao.maps.LatLng(33.450701,126.570667), level :10 };

          map = new kakao.maps.Map(mapContainer, mapOption);

          if (navigator.geolocation) {
              navigator.geolocation.getCurrentPosition(function(position) {
                  var lat = position.coords.latitude,
                      lon = position.coords.longitude,
                      locPosition = new kakao.maps.LatLng(lat, lon);
                  displayMarker(locPosition);
              }, function(error) {
                  // Geolocation API 에러 처리
                  console.error('Geolocation Error: ', error);
                  var locPosition=new kakao.maps.LatLng(33.450701,126.570667);
                  displayMarker(locPosition);
              });
          } else {
              console.error('Geolocation is not supported by this browser.');
              var locPosition=new kakao.maps.LatLng(33.450701,126.570667);
              displayMarker(locPosition);
          }
      }

      function changeMapCenter(locPosition){
        // Change the center of the Map to a given location and display marker
        map.setCenter(locPosition);
        displayMarker(locPosition);
      }

      document.querySelector('.map_search button').addEventListener('click', function() {
          // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
          var infowindow = new kakao.maps.InfoWindow({zIndex:1});

          var keyword = document.querySelector('.map_search input').value;

          // 장소 검색 객체를 생성합니다
          var ps = new kakao.maps.services.Places();

          // 키워드로 장소를 검색합니다
          ps.keywordSearch(keyword, placesSearchCB);

          // 키워드 검색 완료 시 호출되는 콜백함수 입니다
          function placesSearchCB (data, status, pagination) {
              if (status === kakao.maps.services.Status.OK) {

                  // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해 LatLngBounds 객체에 좌표를 추가합니다
                  var bounds = new kakao.maps.LatLngBounds();

                  for (var i=0; i<data.length; i++) {
                      displayMarker(new kakao.maps.LatLng(data[i].y, data[i].x));
                      bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
                  }

                  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                  map.setBounds(bounds);
              }
         }
      });


      window.onload = myFunction; // 페이지 로드 시에 지도 초기화




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
                                      u_email : uEmail,
                                      menu_name : menuNameElement.innerText,
                                      res_name : resId,
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

