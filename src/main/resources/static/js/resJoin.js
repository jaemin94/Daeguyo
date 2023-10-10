
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




