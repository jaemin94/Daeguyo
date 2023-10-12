

$(document).ready(function () {
  var swiper = new Swiper(".mySwiper", {
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
    loop: true,
  });

  var swiper = new Swiper(".swiper2", {
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: {
      delay: 3000,
      disableOnInteraction: true,
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
    loop: true,
  });

  $(".hamburger_btn").click(function () {
    $(".side_menu").addClass("active");
  });

  $(".close").click(function () {
    $(".side_menu").removeClass("active");
  });
  $(".mainmenu > li").mouseenter(function () {
    $(this).addClass("active");
  });

  $("nav > ul > li").mouseleave(function () {
    $("header").removeClass("sub_active");
    $(this).removeClass("active");
  });

  var swiper = new Swiper(".Swiper3", {
    cssMode: true,
    navigation: {
      nextEl: ".Swipersecond .swiper-button-next",
      prevEl: ".Swipersecond .swiper-button-prev",
    },
    pagination: {
      el: ".Swiper3 .swiper-pagination",
    },
    mousewheel: true,
    keyboard: true,
    loop: true,
    autoplay: { delay: 5000 },
  });
});

let select = document.querySelector(".location_complete");
let map = document.querySelector(".map_bg");
let lct = document.querySelector(".location");

select.addEventListener("click", function () {
  map.style.display = "none";
});

lct.addEventListener("click", function () {
  map.style.display = "block";
});

/* 지도 */
