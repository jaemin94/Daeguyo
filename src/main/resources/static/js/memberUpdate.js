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






//제안보내기
