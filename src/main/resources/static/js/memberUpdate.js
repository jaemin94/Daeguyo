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


//
// var email = document.getElementById('u_email').value;
// axios.get('/memberUpdate',email)
//     .then(function (response) {
//       console.log(response.data);
//     })
//     .catch(function (error) {
//       console.log(error);
//     });



//제안보내기
const updateMemberbtn = document.getElementById("updateMemberbtn");
updateMemberbtn.onclick = function () {
  //주소
  var postNumber = document.getElementById("addr_number").value;
  var address = document.getElementById("address").value;
  var extraAddress = document.getElementById("extraAddress").value;
  var detailAddress = document.getElementById("detailAddress").value;

  var password1 = document.getElementById("password1").value;
  var username = document.getElementById("nickname").value;
  var phoneNumber = document.getElementById("phone").value;
  var email_id = document.getElementById("email_id").value;

  // 주소합병
  var  fullAddress = postNumber + " " + address + " " + extraAddress + " " + detailAddress;

  const requestData = {

    nickname: username,
    phone: phoneNumber,
    u_email: email_id

  };

  if (password1.trim() !== "") {
    requestData.password = password1;

  }

  if(addr.trim() !== ""){
    requestData.addr = fullAddress;
  }


  axios.put('/memberUpdate', requestData)
      .then(function(response){
        alert('회원정보가 성공적으로 변경되었습니다.');
        console.log(response.data);
        location.reload();
      })
      .catch(function(error){
        alert('회원정보 변경에 실패하였습니다.');
        console.log(error);
      });
}

// 취소 버튼
document.getElementById('updatecancel').addEventListener('click', function() {
  window.location.href = '/myPage';
});

//회원 탈퇴 버튼
const userDeleteBtn = document.getElementById("userdeltebtn");
userDeleteBtn.onclick = function () {
  // 확인 메시지 표시
  const confirmed = confirm("정말로 탈퇴하시겠어요??");

  // 사용자가 확인 버튼을 선택한 경우에만 회원 탈퇴 요청 전송
  if (confirmed) {
    const u_email = document.getElementById("email_id").value;
    console.log(u_email);

    axios.delete('/memberUpdate1?u_email=' + u_email)
        .then(function (response) {
          console.log(u_email);
          alert('회원탈퇴가 성공적으로 처리되었습니다.');
          window.location.href = "/";
          console.log(response.data);
        })
        .catch(function (error) {
          alert('회원탈퇴 처리에 실패하였습니다.');
          console.log(error);
        });
  }
}

