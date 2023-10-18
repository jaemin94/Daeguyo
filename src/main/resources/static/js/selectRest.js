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
