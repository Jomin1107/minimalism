package com.oracle.minimalism.jhController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.minimalism.dto.MailDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.dto.UserRequestDto;
import com.oracle.minimalism.jhService.UserService;
import com.oracle.minimalism.mapper.UserMapper;


@Controller 	// Spring MVC 컨테이너에서 관리하는 servlet
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserMapper usermapper;	
	
	
	@RequestMapping(value = "/jsp") //url pattern mapping
	public String main() {
		//Model : 데이터를 담는 그릇 역할, map 구조로 저장됨
        // model.addattribute("변수명", 값)
        // <beans:property name = "prefix" value="WEB-INF/views" />
        // <beans:property name = "suffix" value = ".jsp" />
        // /WEB-INF/ views/main.jsp
        return "index"; //원래 코드는   "/WEB-INF/views/main.jsp"이지만 servlet-context에 
                        //main을 제외한 나머지 코드가 정의되어 있기 때문에 따로 작성하지 않아도 된다.
        
    }
	// 회원가입
	@GetMapping("/joinForm")
	public void insert(){}
	
	@PostMapping("/joinForm")
	public String insert(@Valid UserRequestDto user,Errors errors,Model model, HttpSession session) {
				
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("user", user);
            
            for (FieldError error : errors.getFieldErrors()) {
                String validKeyName = String.format("valid_%s", error.getField());
                model.addAttribute(validKeyName, error.getDefaultMessage());
            }
            
            /* 회원가입 페이지로 다시 리턴 */
            return "/joinForm";
        }
		
		int insert = 0;
		String msg = "";
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setRole("role_user");
		user.setPassword(password);

		System.out.println(user);
		try {
			insert = usermapper.join(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(insert > 0)
		{
			msg = "회원가입 성공";
			session.setAttribute("msg", msg);
			return "redirect:/loginForm";
		}else {
			msg = "회원가입 실패";
			session.setAttribute("msg", msg);
			return "redirect:/joinForm";
		}
		
	}
	// 아이디 중복 검사
	@PostMapping("/user/findid")
	public @ResponseBody int userfindid(@RequestParam String id) {
		System.out.println("id = " + id);
		UserDto user = null;
		
		try {
			user = usermapper.findbyId(id);
			if(user != null)
			{
				return 1; // 중복되는 id가 있다면 1 반환
			}else {
				return 0; // 중복되는 id가 없다면 0 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 2;
	}
	// 이메일 중복 검사
	@PostMapping("/user/findemail")
	public @ResponseBody int userfindemail(@RequestParam String email) {
		System.out.println("email = " + email);
		UserDto user = null;
		
		try {
			user = usermapper.findbyEmail(email);
			if(user != null)
			{
				return 1; // 중복되는 id가 있다면 1 반환
			}else {
				return 0; // 중복되는 id가 없다면 0 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 2;
	}
	
	// loginForm 이동
	 @GetMapping("/loginForm") 
	 public void login(HttpServletRequest req,HttpSession session ) 
	 { 
	     String refererPage = req.getHeader("Referer"); // 로그인 폼 오기 전 페이지 
		 session.setAttribute("redirectPage", refererPage); 
	 }
	 
	 // 일반 회원가입 로그인
	@GetMapping("/loginhelp")
	public String ddd(){
		return "redirect:/";
	}
	@PostMapping("/loginhelp")
	   public String loginhelp(HttpServletRequest request, 
	                         HttpServletResponse response,
	                         String username
	                         )
	   {         
	      HttpSession session = request.getSession();
	      String msg = "";
	      System.out.println("login: " + username);
	      UserDto user = usermapper.findbyId(username);
	      // hj request
	      String jspPage = (String) session.getAttribute("jspPage");
	      System.out.println("jspPage: " + jspPage);

	      if(user != null){
	         msg = "로그인 완료";
	         session.setAttribute("loginUser", user);
	         session.setAttribute("msg", msg);
	      }
	      
	      if (jspPage == null) {
	         return "redirect:/";
	      } else {
	         return "redirect:/reviews";
	      }
	      
	   }	
	
	// 소셜로그인
	@GetMapping("/Oauth2Login/{username}")
	public String oauth2Login(@PathVariable String username, HttpSession session, Authentication authentication){
		System.out.println("oauth2Login :" + username);
		UserDto user = usermapper.findbyId(username);
		String ck = user.getAge();
		if(ck != null)
		{
			String msg = "소셜로그인 성공";
			session.setAttribute("loginUser", user);
			session.setAttribute("msg", msg);
			return "redirect:/";
		}else {
			session.setAttribute("oauth2Login", user);
			return "redirect:/Oauth2Login";
		}
	}
	
	// 소셜로그인 (update후)
	@GetMapping("/Oauth2Login")
	public String oauth2Login(Model model, HttpSession session) {
		UserDto user = (UserDto) session.getAttribute("oauth2Login"); // 
		model.addAttribute("user",user);
		return "/Oauth2Login";
	}
	// 소셜로그인 추가정보입력 (update)
	@PostMapping("/Oauth2Login")
	public String oauth2Login(UserDto user, HttpSession session) {
		int update = 0;
		update = usermapper.update(user);
		
		if(update > 0 ) {
			String msg = "소셜로그인 성공";
			user = usermapper.findbyId(user.getId());
			session.setAttribute("loginUser", user);
			session.setAttribute("msg", msg);
		}else {
			String msg = "소셜로그인 실패";
			session.setAttribute("msg", msg);
		}
		return "redirect:/";
	}
	
	// 아이디 찾기
	@GetMapping("/findIdForm")
	public void findId() {}
	
	@PostMapping("/findIdForm")
	public String findId(String username, String email, HttpSession session) {
		String msg = "";
		UserDto user = null;
		System.out.println(username);
		if(username == null || email == null)
		{
			msg = "입력폼을 확인해주세요.";
		}else {  
			try {
				user = usermapper.findbyusername(username); //이름을 이용해서 user를 가져옴
				if(user == null) {
					msg = "입력하신 정보가 없습니다."; // username이 없을때
				}
				if(!email.equals(user.getEmail())) {
					msg = "이메일을 잘못 입력하셨습니다."; // username은 있는대 데이터베이스에 이메일과 다를때
				}else {
					session.setAttribute("findUser", user);
					return "redirect:/foundIdForm";
					// return "redirect:/foundIdForm/"+ user.getId(); // 둘다 맞을때 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("msg", msg);
		return "redirect:/findIdForm";
	}
	// 이름과 이메일 정보가 일치해서 찾은 id 불러오기
	@GetMapping("/foundIdForm")
	public String foundId(HttpSession session, Model model) {
		UserDto user = (UserDto) session.getAttribute("findUser");
		model.addAttribute("loginUser", user);
		return "/foundIdForm";
	}
	
//			@GetMapping("/foundIdForm/{Id}")
//			public String foundId(@PathVariable String Id, Model model) {
//				System.out.println("Id :" + Id);
//				UserDto user = null;
//				user = usermapper.findbyId(Id);
//				model.addAttribute("loginUser", user);
//				return "/foundIdForm";
//			}
	
	// 비밀번호찾기
	@GetMapping("/findPwForm")
	public void findPw() {}
	
	@PostMapping("/findPwForm")
	public String findPw(String id, String email, String username, HttpSession session) {
		System.out.println("userid: " +id );
		System.out.println("email: " +email );
		System.out.println("name: " + username);
		
		String msg = "";
		
		if(id == null || email == null || username == null)
		{
			msg = "입력을 확인해주세요.";
		}else	
		{
			UserDto user = usermapper.findbyId(id);
			if(user == null)
			{
				msg = "등록 되지 않은 아이디 입니다.";
			}else {
				String ckEmail = user.getEmail();
					if(!user.getEmail().equals(ckEmail))
					{
						msg = "등록하신 이메일이 아닙니다.";
					}
					else {
						MailDto dto = userService.createMailAndChangePassword(id, email); //이메일 전송
						userService.mailSend(dto);
					}
			}
		}
		session.setAttribute("msg", msg);
		return "redirect:/loginForm";
	}		
	
	// 로그아웃
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		String msg = "로그아웃 되었습니다.";
		session.removeAttribute("loginUser");
		session.setAttribute("msg", msg);
		return "redirect:/";
	}
	// 로그인실패
    @GetMapping("/failLogin")
    public String failLogin(@RequestParam(value = "error", required = false)String error,
    		@RequestParam(value = "exception", required = false)String exception,                        
    		HttpSession session) { 
    	System.out.println(exception);
    	session.setAttribute("msg", exception);
    	return "redirect:/loginForm";
    }
    
    

}



