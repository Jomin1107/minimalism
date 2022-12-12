let idck = false;
let passwordckk = false;
let emailck = false;
userJoinForm.id.addEventListener('change',function(event){
	let id = userJoinForm.id.value;
	let result;
	$.ajax({
		url:"/user/findid?id="+id,
		type:"POST",
		async:false,
		success: function(data)
		{
		result = data;
		}
	})
	
	console.log(result);
	if(result == 1)
	{
		idInvalid.innerText = "이미 존재하는 아이디 입니다.";
		idck = false;
	}
	else
	{
		idInvalid.innerText = "";
		idck = true;
	}

})

userJoinForm.passwordck.addEventListener('change', function(event){
	
	
	let password1 = userJoinForm.password.value;
	let password2 = userJoinForm.passwordck.value;
	
	if(password1 != password2)
	{
	passwordckInvalid.innerText = "비밀번호가 일치 하지 않습니다.";
	passwordckk = false;
	}else{
	passwordckInvalid.innerText = "";
	passwordckk = true;
	}
	
})

userJoinForm.email.addEventListener('change',function(event){
	let email = userJoinForm.email.value;
	let result;
	$.ajax({
		url:"/user/findemail?email="+email,
		type:"POST",
		async:false,
		success: function(data)
		{
		result = data;
		}
	})
	
	console.log(result);
	if(result == 1)
	{
		emailInvalid.innerText = "이미 존재하는 이메일 입니다.";
		emailck = false;
	}
	else
	{
		emailInvalid.innerText = "";
		emailck = true;
	}

})


userJoinForm.addEventListener('click', function(event){
	 const forms = document.querySelectorAll('.needs-validation')
	 Array.from(forms).forEach(form => {

	form.addEventListener('submit', event => {
      if (idck == false || passwordckk == false) {
        event.preventDefault()
        event.stopPropagation()
      }
    }, false)

  })
})