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


 function handlePayment(cart_id){


       axios.post('/user/details', { cart_id : cart_id })
           .then(function (userDetailsResponse) {
               var data = userDetailsResponse.data;
               var orderName = data.res_id;
               var totalAmount = parseInt(document.getElementById('amount_price').innerHTML.split("원")[0]);
               var customer = {
                   customerId: data.nickname,
                   phoneNumber: data.phone
               };
               var paymentId = 'paymentId' + new Date().getTime();

               PortOne.requestPayment({
                   storeId: 'store-e41c5842-2d91-4a60-90bf-3b70da11378f',
                   paymentId: paymentId,
                   orderName: orderName,
                   totalAmount: totalAmount,
                   customer: customer,
                   currency: 'CURRENCY_KRW',
                   channelKey: 'channel-key-d184ee7f-a639-44ef-81b3-d1aeb4eba934',
                   payMethod: "CARD",
               }).then(function(response) {
                      if (response.transactionType === "PAYMENT") {

                         var PaymentData = {
                              payment_id: paymentId,  // 위에서 생성한 paymentId 사용
                              order_id: "temp_order_id",  // 실제 주문 ID로 대체해야 함.
                              pay_method:"CARD",
                              pay_date:new Date(),
                              pay_status:"Waiting"
                          };

                          axios.post('/payment/save', PaymentData)
                               .then(function(response) {
                                   console.log('Payment data saved successfully.');
                               })
                               .catch(function(error) {
                                   console.error('Error saving payment data:', error);
                               });
                      } else {
                         console.error('Payment failed:', response);
                      }
                  }).catch(function (error) {
                      console.error('Error:', error);
                  });
           }).catch(function (error) {
              console.error('Error:', error);
          });
   });



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
               var currentAmount = parseInt(amountSpan.textContent);

               if (currentAmount + delta >= 0) { // Change this condition if you want a minimum limit other than 0
                   amountSpan.textContent = currentAmount + delta;

                    axios.post('/updateOrder', { cart_id : cart_id , count : currentAmount + delta})
                   .then(function (response) {

                     var all_price = parseInt(document.getElementById('amount_price').innerHTML.split("원")[0]);

                   if(delta < 0){
                     document.getElementById('amount_price').innerText = (all_price  - price) +  "원";
                   }else{
                     document.getElementById('amount_price').innerText = (all_price + price) +  "원";
                   }
                   $(".total_price span:last-child").text( (document.getElementById('amount_price').innerHTML.split("원")[0]) + "원");


                   })

                   .catch(function (error) {
                       // Handle error here
                       console.error(error);
                   });
           }


           }


            $(document).ready(function() {
                   var total = 0;

                   // 모든 .price 요소를 순회하면서 가격을 합산
                   $(".price").each(function() {
                       var price = $(this).text();
                       // price 문자열에서 콤마와 원 단위 제거 후 숫자로 변환
                       price = parseInt(price.replace(/,/g, "").replace("원", ""));

                       if (!isNaN(price)) { // 숫자인 경우만 합산
                           total += price;
                       }
                   });

                   // 총 상품금액 표시 업데이트 (콤마와 원 단위 추가)
           <!--        $(".total_price span:last-child").text(total.toLocaleString() + "원");-->
               });



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