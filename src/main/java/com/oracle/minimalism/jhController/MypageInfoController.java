package com.oracle.minimalism.jhController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.jhService.UserService;
import com.oracle.minimalism.mapper.UserMapper;

@Controller
public class MypageInfoController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserMapper usermapper;
	
	// 마이페이지 회원정보조회 (Login시 session에 loginUser로 user 데이터를 이미 저장해놨기 때문에 view에서 가져올수있음.)
    @GetMapping("/mypageInfo")
	public String mypage(HttpSession session) throws Exception {
    	UserDto user = (UserDto) session.getAttribute("loginUser");
    	String msg;
    	if(user == null) {
    		msg = "로그인 시간이 만료 되었습니다.";
    		session.setAttribute("msg", msg);
    		return "/loginForm";
    		
    	}
		return "/mypageInfo";
	}
    
    // 마이페이지 수정
    @PostMapping("/mypageUpdate")
    public String mypageUpdate(UserDto user, HttpSession session) {
    	int update = 0;
    	System.out.println("user = " + user);
    	UserDto userCk = usermapper.findbyId(user.getId());    	
    	if(!bCryptPasswordEncoder.matches(user.getPassword(), userCk.getPassword())) {
    		String msg = "비밀번호가 일치하지 않습니다.";
    		session.setAttribute("msg", msg);
    		return "redirect:/mypageInfo";
    	}
    	String ecryPassword = bCryptPasswordEncoder.encode(user.getPassword());
    	user.setPassword(ecryPassword);
    	update = usermapper.mypageUpdate(user);
    	if(update > 0) {
    		String msg = "정보수정 완료";
    		session.setAttribute("loginUser", user);
    		session.setAttribute("msg", msg);
    	}else {
    		String msg = "정보수정 실패";
    		session.setAttribute("msg", msg);
    	}
    	return "redirect:/";
    }
    
    // 마이페이지 비밀번호 변경  지금 form 에서 보내는걸 받는게 없쥬 네 근데
    @GetMapping("/changePwForm")
    public String changePwForm(HttpSession session) throws Exception {
    	UserDto user = (UserDto) session.getAttribute("loginUser");
    	String msg;
    	if(user == null) {
    		msg = "로그인 시간이 만료 되었습니다.";
    		session.setAttribute("msg", msg);
    		return "/loginForm";    		
    	}
		return "/changePwForm";
    }
    @PostMapping("/changePwForm")
    public String mypagePwUpdate(String password, String password1, String password1ck, HttpSession session) {
    	UserDto user = (UserDto) session.getAttribute("loginUser");
    	int update = 0;
    	String msg = "";
    	System.out.println(password +" "+ password1 +" "+ password1ck);
    	
    	if(!password1.equals(password1ck))
    	{
    		msg = "변경할 비밀번호가 일치하지 않습니다.";
			session.setAttribute("msg", msg);
			return "redirect:/changePwForm";
    	}
    	
    	if(bCryptPasswordEncoder.matches(password, user.getPassword()))
    	{
    		try {
    			String encPassword = bCryptPasswordEncoder.encode(password1);
    			user.setPassword(encPassword);
    			update = usermapper.mypagePwUpdate(user);
    			if(update > 0) {
    				msg = "비밀번호 변경 성공";
    				session.setAttribute("msg", msg);
    				session.removeAttribute("loginUser");
    				return "redirect:/loginForm";
    			}else {
    				msg = "비밀번호 변경 실패 다시시도하세요.";
    				session.setAttribute("msg", msg);
    				return "redirect:/changePwForm";
    			}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else {
    		msg = "비밀번호가 옳지 않습니다.";
    		session.setAttribute("msg", msg);
    		return "redirect:/changePwForm";
    	}
    	

    	return "redirect:/mypageInfo";
    }
    
    // 마이페이지 회원탈퇴
    @PostMapping("/mypageDelete")
    public String mypageDelete(UserDto user, HttpSession session) {
    	int update = 0;
    	String msg = "";
    	System.out.println("user:" + user);
    	
    	try {
    		UserDto userCk = usermapper.findbyId(user.getId());
    		if(userCk == null) {
    			msg = "데이터베이스 오류. 관리자에게 문의하세요.";
    			session.setAttribute("msg", msg);
    			return "redirect:/mypageInfo";
    		}else {
    			if(!bCryptPasswordEncoder.matches(user.getPassword(), userCk.getPassword())) {
    			msg = "잘못된 비밀번호 입니다.";
    			session.setAttribute("msg", msg);
    			return "redirect:/mypageInfo";
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	user.setUser_delete(1);
    	try {
    		update = usermapper.delete(user);
    		if(update > 0) {
    			msg = "유저 아이디 삭제 완료";
    			session.removeAttribute("loginUser");
    			session.setAttribute("msg", msg);
    		}else {
    			msg = "유저 아이디 삭제 실패 관리자에게 문의하세요";
    			session.setAttribute("msg", msg);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    	return "/loginForm";
    }
}
