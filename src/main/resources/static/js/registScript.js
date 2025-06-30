function idCheck() {
    const idchk = document.getElementById('idChecked');
    let ichk = /(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,20}/;
    const registerUsername = document.getElementById('registerUsername').value;

    if (ichk.test(registerUsername)) {
        console.log("아이디 참")
        return true
    } else {
        console.log("아이디 거짓")
        return false
    }
}

function passCheck() {
    let pchk = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$/;
    const registerPassword = document.getElementById('registerPassword').value;

    if (pchk.test(registerPassword)) {
        console.log("패스워드 참");
        return true
    } else {
        console.log("패스워드 거짓");
        return false
    }
}

function emailCheck() {
    let echk = /^[A-Za-z0-9]+@[^\s@]+\.[^\s@]{2,}/;
    const registerEmail = document.getElementById('registerEmail').value;

    if (echk.test(registerEmail)) {
        console.log("이메일 참")
        return true
    } else {
        console.log("이메일 거짓")
        return false
    }

}

function nicknameCheck() {
    let nchk = /^[A-Za-z0-9가-힣]{2,10}$/;
    const registerNickname = document.getElementById('registerNickname').value

    if (nchk.test(registerNickname)) {
        console.log("닉네임 참");
        return true
    } else {
        console.log("닉네임 거짓");
        return false
    }
}

function confassCheck() {
    const registerPassword = document.getElementById('registerPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    if(registerPassword === confirmPassword) {
        console.log("패스워드 확인 참");
        return true
    } else {
        console.log("패스와드 확인 거짓");
        return false
    }
}

function AgreeCheckbox() {
         const box1 = document.getElementById('termsAgree');
         const box2 = document.getElementById('privacyAgree');
         if(box1.checked && box2.checked) {
             return true
         } else {
             console.log("체크박스 비동의");
             return false
         }

     }


function allCheck() {
    if (idCheck()&&passCheck()&&emailCheck()&&nicknameCheck()&&confassCheck()&&AgreeCheckbox()) {
        console.log("다 참")
		alert('회원가입이 완료되었습니다! 로그인해주세요.');
		return true
    } else if(idCheck()&&passCheck()&&emailCheck()&&nicknameCheck()&&confassCheck()) {
        console.log("체크박스 거짓")
		alert('약관에 동의해주세요.');
		return false
		
    } else {
		alert('형식이 일치하지 않습니다.');
	}
}