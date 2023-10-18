

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

//      const mypage = document.getElementById("mypage");
//      mypage.addEventListener("click",function(){
//      var u_email = document.getElementById("u_email").value;
//
//      axios.get("/myPage?u_email=" + u_email)
//      .then(response =>{
//        console.log(response.data);
//      })
//      .catch(error=>{
//
//      console.error(error);
//      })
//
//      })

