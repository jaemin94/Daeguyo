const restList = document.querySelector('.rest_list');
const rests = document.querySelectorAll('.Rest');

restList.addEventListener('scroll', function() {
    rests.forEach(function(rest) {
        const restTop = rest.offsetTop - restList.scrollTop;
        const restBottom = restTop + rest.offsetHeight;
        const restCenter = restTop + (rest.offsetHeight / 2);
        const listCenter = restList.clientHeight / 2;

        // Rest 항목의 중앙이 rest_list의 중앙에 가까울수록 .active 클래스를 추가
        if (restCenter > listCenter - 100 && restCenter < listCenter + 100) {
            rest.classList.add('active');
        } else {
            rest.classList.remove('active');
        }
    });
});


document.querySelector('.order_list').addEventListener('click', function(e) {
    e.preventDefault();

    if (e.target.tagName === 'A') {
      const sortType = e.target.getAttribute('data-sort');

      switch(sortType) {
        case 'rating':
          // 별점순 정렬 로직
          break;
        case 'delivery':
          // 배달팁 정렬 로직
          break;
        case 'price':
          // 주문금액 정렬 로직
          break;
        // ... 기타 정렬 로직 추가
      }
    }
  });
document.addEventListener("DOMContentLoaded", function () {
  // select 요소를 가져옵니다.
  var select = document.querySelector(".order_list");

  // rest_list 요소를 가져옵니다.
  var restList = document.querySelector(".rest_list");

  // 초기 정렬 순서를 저장할 변수입니다.
  var defaultOrder = Array.from(restList.querySelectorAll(".Rest"));

  // select 요소가 변경될 때 실행될 함수를 정의합니다.
  select.addEventListener("click", function (event) {
    event.preventDefault();

    var target = event.target;
    if (target.tagName === "A") {
      var sortType = target.getAttribute("data-sort");
      sortRestaurants(sortType);
    }
  });

  // 주문 리스트 정렬 함수
  function sortRestaurants(sortType) {
    if (sortType === "default") {
      // 기본순으로 정렬
      restList.innerHTML = "";
      for (var i = 0; i < defaultOrder.length; i++) {
        restList.appendChild(defaultOrder[i]);
      }
    } else if (sortType === "rating") {
      // 별점순으로 정렬
      var restaurants = Array.from(defaultOrder);

      restaurants.sort(function (a, b) {
        var starA = parseFloat(a.querySelector(".star-score").textContent.replace(/[^0-9.]/g, ""));
        var starB = parseFloat(b.querySelector(".star-score").textContent.replace(/[^0-9.]/g, ""));
        return starB - starA;
      });

      updateRestaurantList(restaurants);
    } else if (sortType === "delivery") {
      // 배달팁 낮은 순으로 정렬
      var restaurants = Array.from(defaultOrder);

      restaurants.sort(function (a, b) {
        var deliveryA = parseInt(a.querySelector(".fee-list").textContent.replace(/[^0-9]/g, ""));
        var deliveryB = parseInt(b.querySelector(".fee-list").textContent.replace(/[^0-9]/g, ""));
        return deliveryA - deliveryB;
      });

     updateRestaurantList(restaurants);
    } else if (sortType === "price") {
       // 주문금액 낮은 순으로 정렬
       var restaurants = Array.from(defaultOrder);

       restaurants.sort(function (a, b) {
         var priceA = parseInt(a.querySelector(".pay-list").textContent.replace(/[^0-9]/g, ""));
         var priceB= parseInt(b.querySelector(".pay-list").textContent.replace(/[^0-9]/g, ""));
         return priceA - priceB;
       });

       updateRestaurantList(restaurants);
    } else if (sortType === "etc") {

           /* restaurant.res_name의 이름을 랜덤하게 배열 */

           var restaurants=Array.from(defaultOrder);

          restaurants.sort(function(){
             return Math.random() - 0.5;
          });

          updateRestaurantList(restaurants);


       }
     }

       function updateRestaurantList(restaurants){
         restList.innerHTML="";
         for(var i=0;i<restaurants.length;i++){
              restList.appendChild(restaurants[i]);
         }
       }

    });

