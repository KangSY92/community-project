let idFlag = false;
let passFlag = false;
let emailFlag = false;
let nicknameFlag = false;
let confirmFlag = false;
let checkboxFlag = false;


function idCheck() {
    const idchk = document.getElementById('idChecked');
    let ichk = /(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,20}/;
    const registerUsername = document.getElementById('registerUsername').value;
    const idHint = document.getElementById('id-hint')

    if (ichk.test(registerUsername) === false) {

        console.log("아이디 거짓")
        idHint.style.color = "red"
        idFlag = false;
    } else {
        console.log("아이디 참")
        idHint.style.color = "#6c757d"
        idFlag = true;
    }
}

function passCheck() {
    let pchk = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$/;
    const registerPassword = document.getElementById('registerPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const passHint = document.getElementById('pass-hint');

    if (pchk.test(registerPassword)) {
        passHint.style.color = "#6c757d";
        passwordCheck();
        passFlag = true;
    } else {
        passHint.style.color = "red";
        passFlag = false;
    }

}

function passwordCheck() {
    const registerPassword = document.getElementById('registerPassword').value;
    const confirmPassword = document.getElementById('confirmPassword');
    const confirmHint = document.getElementById('confirm-hint');
    if(confirmPassword.value === ''){
        confirmHint.style.display = 'none'
        confirmFlag = false;
    }else if (registerPassword === confirmPassword.value) {
        confirmHint.style.display = 'none'
        console.log("패스워드 확인 참");
        confirmFlag = true;

    } else {
        confirmHint.style.display = 'block'
        confirmHint.style.color = 'red'
        confirmHint.textContent = "패스워드가 일치하지 않습니다."
        console.log("패스와드 확인 거짓");
        confirmFlag = false;
    }
}

confirmPassword.addEventListener('focusout', passwordCheck)

registerEmail.addEventListener('focusout', function () {
    const registerEmail = document.getElementById('registerEmail');
    const emailHint = document.getElementById('email-hint');
    let echk = /^[A-Za-z0-9]+@[^\s@]+\.[^\s@]{2,}/;
    if (echk.test(registerEmail.value)) {
        emailHint.style.display = 'none';
        emailFlag = true;

    } else {
        emailHint.style.display = 'block';
        emailHint.textContent = "이메일 형식이 올바르지 않습니다.";
        emailFlag = false;
    }
})

function nicknameCheck() {
    let nchk = /^[A-Za-z0-9가-힣]{2,10}$/;
    const registerNickname = document.getElementById('registerNickname').value
    const nicknameHint = document.getElementById('nickname-hint');
    if (nchk.test(registerNickname)) {
        nicknameHint.style.color = "#6c757d";
        nicknameFlag = true;
    } else {
        nicknameHint.style.color = "red";
        nicknameFlag = false;
    }
}



function AgreeCheckbox() {
    const box1 = document.getElementById('termsAgree');
    const box2 = document.getElementById('privacyAgree');
	const box3 = document.getElementById('marketingAgreeId');
	
    if (box1.checked && box2.checked) { // 필수 체크 됐을 때
		const registerForm = document.getElementById('registerForm'); // form 요소 가져오기
		const marketingAgree = document.createElement('input');  // input 요소 생성
		
		marketingAgree.name = 'marketingAgree'; // <input name='marketingAgree'>
		marketingAgree.style.display = 'none';
		
		if(box3.checked){
			marketingAgree.value = 'Y'; // <input name='marketingAgree' value='Y'>
		} else{
		    marketingAgree.value = 'N'; //  <input name='marketingAgree' value='N'>
		}
		
		registerForm.appendChild(marketingAgree); // form 요소 자식으로 위에서 만든 input 넣어주기
		
        console.log("체크박스 동의")
        return true;
    } else {
        console.log("체크박스 비동의");
        return false;
    }

}


function allCheck() {
    if (idFlag && passFlag && emailFlag && nicknameFlag && confirmFlag && AgreeCheckbox()) {
        console.log("다 참")
        alert('회원가입이 완료되었습니다! 로그인해주세요.');
    } else if (idFlag && passFlag && emailFlag && nicknameFlag && confirmFlag) {
        console.log("체크박스 거짓")
        alert('이용약관 동의는 필수입니다.');
    } else {
        console.log(!idFlag);
        alert('형식이 일치하지 않습니다.');
    }
}