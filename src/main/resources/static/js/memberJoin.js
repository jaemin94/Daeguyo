//이름 버튼
var cancelButton = document.querySelector(".name_input button");
var nameInput = document.getElementById("name");

cancelButton.addEventListener("click", function () {
  // 입력란의 값을 지웁니다.
  nameInput.value = "";
});

// 비밀번호1 버튼
var passwordInput1 = document.getElementById("password1");
var toggleButton1 = document.getElementById("pass1_btn");
var eyeImage1 = document.getElementById("eyeImage1");
var isPasswordVisible1 = false;

// 버튼 클릭 시 이벤트 핸들러를 추가합니다.
toggleButton1.addEventListener("click", function () {
  // 비밀번호1 입력란의 현재 타입을 확인합니다.
  var currentType1 = passwordInput1.getAttribute("type");

  // 현재 타입이 "password"라면 "text"로 변경하여 비밀번호를 표시하고, 그렇지 않다면 "password"로 변경하여 비밀번호를 숨깁니다.
  if (currentType1 === "password") {
    passwordInput1.setAttribute("type", "text");
    // 이미지를 숨김 아이콘으로 변경합니다.
    eyeImage1.src = "./images/off-eye.png";
  } else {
    passwordInput1.setAttribute("type", "password");
    // 이미지를 보임 아이콘으로 변경합니다.
    eyeImage1.src = "./images/eye.png";
  }
});

// 비밀번호2 버튼
var passwordInput2 = document.getElementById("password2");
var toggleButton2 = document.getElementById("pass2_btn");
var eyeImage2 = document.getElementById("eyeImage2");
var isPasswordVisible2 = false;

// 버튼 클릭 시 이벤트 핸들러를 추가합니다.
toggleButton2.addEventListener("click", function () {
  // 비밀번호2 입력란의 현재 타입을 확인합니다.
  var currentType2 = passwordInput2.getAttribute("type");

  // 현재 타입이 "password"라면 "text"로 변경하여 비밀번호를 표시하고, 그렇지 않다면 "password"로 변경하여 비밀번호를 숨깁니다.
  if (currentType2 === "password") {
    passwordInput2.setAttribute("type", "text");
    // 이미지를 숨김 아이콘으로 변경합니다.
    eyeImage2.src = "./images/off-eye.png";
  } else {
    passwordInput2.setAttribute("type", "password");
    // 이미지를 보임 아이콘으로 변경합니다.
    eyeImage2.src = "./images/eye.png";
  }
});


// 입력창
function Postcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      var addr = '';
      var extraAddr = '';

      if (data.userSelectedType === 'R') {
        addr = data.roadAddress;
      } else {
        addr = data.jibunAddress;
      }

      if (data.userSelectedType === 'R') {
        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        if (data.buildingName !== '' && data.apartment === 'Y') {
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        if (extraAddr !== '') {
          extraAddr = ' (' + extraAddr + ')';
        }
        document.getElementById("extraAddress").value = extraAddr;
      } else {
        document.getElementById("extraAddress").value = '';
      }

      // 수정된 부분: 주소 정보를 각각의 입력란에 설정
      document.getElementById("addr_number").value = data.zonecode;
      document.getElementById("address").value = addr;
      document.getElementById("detailAddress").value = ''; // 상세주소 초기화
    }
  }).open();
}



// 동의버튼들

// 1 클릭시 이미지 변경

// // 전체동의버튼
//   document.addEventListener("DOMContentLoaded", function() {
//     const allAgreeButton = document.querySelector(".all_agree_btn");
//     const allAgreeImage = allAgreeButton.querySelector("img");

//     let isButtonClicked = false;

//     allAgreeButton.addEventListener("click", function() {
//       isButtonClicked = !isButtonClicked;

//       if (isButtonClicked) {
//         allAgreeImage.src = "./images/check.png"; 
//       } else {
//         allAgreeImage.src = "./images/done_check.png"; 
//       }
//     });
//   });

//   // 다른동의버튼들
//   document.addEventListener("DOMContentLoaded", function() {
//   const agreeButtons = document.querySelectorAll(".agree_box button");

//   for (const agreeButton of agreeButtons) {
//     agreeButton.addEventListener("click", function() {
//       if (agreeButton.classList.contains("checked")) {
//         agreeButton.classList.remove("checked");
//         agreeButton.querySelector("img").src = "./images/nemo_check.png";
//       } else {
//         agreeButton.classList.add("checked");
//         agreeButton.querySelector("img").src = "./images/nemo.png"; 
//       }
//     });
// }
// });

// //전체동의 클릭시

// document.addEventListener("DOMContentLoaded", function() {
//     const allAgreeButton = document.querySelector(".all_agree_btn");

//     const agreeButtons = document.querySelectorAll(".agree_box button");

//     allAgreeButton.addEventListener("click", function() {
//       if (allAgreeButton.classList.contains("checked")) {
//         for (const agreeButton of agreeButtons) {
//           agreeButton.classList.remove("checked");
//         }
//       } else {
//         for (const agreeButton of agreeButtons) {
//           agreeButton.classList.add("checked");
//         }
//       }

//       allAgreeButton.classList.toggle("checked");
//     });

//     for (const agreeButton of agreeButtons) {
//       agreeButton.addEventListener("click", function() {
//         agreeButton.classList.toggle("checked");
//       });
//     }
//   });


document.addEventListener("DOMContentLoaded", function () {
  const allAgreeButton = document.querySelector(".all_agree_btn");
  const allAgreeImage = allAgreeButton.querySelector("img");
  let isAllAgreeClicked = false;

  allAgreeButton.addEventListener("click", function () {
    isAllAgreeClicked = !isAllAgreeClicked;

    for (const agreeButton of agreeButtons) {
      if (isAllAgreeClicked) {
        agreeButton.classList.add("checked");
        changeButtonImage(agreeButton, "./images/nemo_check.png");
      } else {
        agreeButton.classList.remove("checked");
        changeButtonImage(agreeButton, "./images/nemo.png");
      }
    }

    allAgreeButton.classList.toggle("checked", isAllAgreeClicked);
  });

  const agreeButtons = document.querySelectorAll(".agree_box button");

  for (const agreeButton of agreeButtons) {
    agreeButton.addEventListener("click", function () {
      agreeButton.classList.toggle("checked");
      const isAllAgreed = Array.from(agreeButtons).every((button) =>
        button.classList.contains("checked")
      );
      allAgreeButton.classList.toggle("checked", isAllAgreed);

      // 이미지 변경 함수 호출
      if (agreeButton.classList.contains("checked")) {
        changeButtonImage(agreeButton, "./images/nemo_check.png");
      } else {
        changeButtonImage(agreeButton, "./images/nemo.png");
      }
    });
  }

  // 이미지 변경 함수 정의
  function changeButtonImage(button, imagePath) {
    button.querySelector("img").src = imagePath;
  }
});

//제안보내기
document.addEventListener("DOMContentLoaded", function () {
  // 이름, 생년월일 (year, day), 이메일 (email), 비밀번호 (password1, password2), 휴대폰 (phone) 입력 상자 요소를 가져옵니다.
  const nameInput = document.getElementById("name");
  const yearInput = document.getElementById("year");
  const dayInput = document.getElementById("day");
  const emailInput = document.getElementById("email_id");
  const password1Input = document.getElementById("password1");
  const password2Input = document.getElementById("password2");
  const phoneInput = document.getElementById("phone");
  const plz1Button = document.getElementById("plz1");
  const plz2Button = document.getElementById("plz2");
  const submitButton = document.querySelector(".agree_submit_box");

  // plz1, plz2 버튼 클릭 여부를 나타내는 변수를 초기화합니다.
  let isPlz1Clicked = false;
  let isPlz2Clicked = false;

  // 입력 상자에 입력 이벤트 리스너를 추가하여 모든 입력 상자가 채워지면 버튼을 활성화합니다.
  function checkInputs() {
    const isAllInputsFilled =
      nameInput.value.trim() !== "" &&
      yearInput.value.trim() !== "" &&
      dayInput.value.trim() !== "" &&
      emailInput.value.trim() !== "" &&
      password1Input.value.trim() !== "" &&
      password2Input.value.trim() !== "" &&
      phoneInput.value.trim() !== "";

    submitButton.disabled = !(isAllInputsFilled && isPlz1Clicked && isPlz2Clicked);
  }

  // 입력 상자에 입력 이벤트 리스너를 추가합니다.
  nameInput.addEventListener("input", checkInputs);
  yearInput.addEventListener("input", checkInputs);
  dayInput.addEventListener("input", checkInputs);
  emailInput.addEventListener("input", checkInputs);
  password1Input.addEventListener("input", checkInputs);
  password2Input.addEventListener("input", checkInputs);
  phoneInput.addEventListener("input", checkInputs);

  // plz1 버튼 클릭 시 이벤트 리스너를 추가합니다.
  plz1Button.addEventListener("click", function () {
    isPlz1Clicked = !isPlz1Clicked;
    plz1Button.classList.toggle("checked", isPlz1Clicked);
    checkInputs();
  });

  // plz2 버튼 클릭 시 이벤트 리스너를 추가합니다.
  plz2Button.addEventListener("click", function () {
    isPlz2Clicked = !isPlz2Clicked;
    plz2Button.classList.toggle("checked", isPlz2Clicked);
    checkInputs();
  });

  // 제안 보내기 버튼 클릭 시 naver.com으로 이동합니다.
  submitButton.addEventListener("click", function () {
    if (submitButton.disabled === false) {
      window.location.href = "https://www.naver.com";
    }
  });
});




