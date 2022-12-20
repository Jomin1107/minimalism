package com.oracle.minimalism.hjController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.hjService.BoardPaging;
import com.oracle.minimalism.hjService.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageBoardController {

	private final MypageService mypageService;
	
	@GetMapping(value = "/mypageBoardService")
	public String myBoardService(RnQDto rnQDto, String currentPage, Model model,
								HttpServletRequest request) {
		System.out.println("MypageBoardController myBoardService Start...");
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("MypageBoardController myBoardService userDto->"+ userDto);
		System.out.println("MypageBoardController myBoardService userDto.getUsername->"+ userDto.getUsername());
		
		int totalMypageCnt = mypageService.totalMypageCnt(userDto);
		System.out.println("MypageBoardController myBoardService totalMypageCnt-> "+totalMypageCnt);
		BoardPaging page = new BoardPaging(totalMypageCnt, currentPage);
		rnQDto.setStart(page.getStart());
		rnQDto.setEnd(page.getEnd());
		System.out.println("MypageBoardController myBoardService currentPage-> "+currentPage);
		
		List<RnQDto> mypageList = mypageService.mypageList(rnQDto);
		System.out.println("MypageBoardController myBoardService mypageList.size-> "+ mypageList.size());
		
		model.addAttribute("totalMypage", totalMypageCnt);
		model.addAttribute("rnqList", mypageList);
		model.addAttribute("rq_name", rnQDto.getRq_name());
		model.addAttribute("page", page);	
		
		return "mypageBoardFrom";
	}
}
