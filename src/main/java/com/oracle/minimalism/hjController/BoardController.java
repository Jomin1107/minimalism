package com.oracle.minimalism.hjController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.hjService.BoardPaging;
import com.oracle.minimalism.hjService.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	/* 게시판 - 공지사항 - 페이지연결 및 목록구현 */
	@RequestMapping(value = "/notice")
	public String boardNotice(NoticeDto noticeDto, String currentPage, Model model) {
		log.info("boardNotice Start...");
		System.out.println("BoardController boardNotice Start...");
		
		int totalNotice = boardService.totalNotice();
		
		BoardPaging page = new BoardPaging(totalNotice, currentPage);
		noticeDto.setStart(page.getStart());
		noticeDto.setEnd(page.getEnd());
		
		List<NoticeDto> noticeList = boardService.noticeList(noticeDto);
		System.out.println("BoardController boardNotice noticeList.size-> " + noticeList.size());
		
		model.addAttribute("totalNotice", totalNotice);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("page", page);
		
		return "/notice";
	}
	
	/* 게시판 - 공지사항 - 검색기능 */
//	total keyword에 맞게 불러오고
//	제목, 작성자에 맞게 쿼리짜기
	@GetMapping(value = "noticeSearch")
	public String noticeSearch(NoticeDto noticeDto, String currentPage, Model model) {
		System.out.println("BoardController noticeSearch Start...");
		System.out.println("BoardController noticeSearch getSearch->"+noticeDto.getSearch());
		System.out.println("BoardController noticeSearch getKeyword->"+noticeDto.getKeyword());

		int totalNoticeSearchCnt = boardService.totalNoticeSearch(noticeDto);
		System.out.println("BoardController noticeSearch totalNoticeSearchCnt->"+totalNoticeSearchCnt);
		
		BoardPaging page = new BoardPaging(totalNoticeSearchCnt, currentPage);
		noticeDto.setStart(page.getStart());
		noticeDto.setEnd(page.getEnd());

        System.out.println("BoardController noticeSearch noticeDto.getKeyword()->"+noticeDto.getKeyword());  
		List<NoticeDto> noticeListSearch = boardService.noticeListSearch(noticeDto);
		System.out.println("BoardController noticeSearch noticeList.size-> " + noticeListSearch.size());
		
		model.addAttribute("totalNotice", totalNoticeSearchCnt);
		model.addAttribute("noticeList", noticeListSearch);
		model.addAttribute("page", page);
		
		return "/notice";
	}
	
	/* 게시판 - 공지사항 - 상세보기  */
	@GetMapping(value = "noticeDetail")
	public String noticeDetail(int notice_id, Model model) {
		System.out.println("BoardController noticeDetail Start...");
		
		NoticeDto noticeDto = boardService.noticeDetail(notice_id);
		System.out.println("BoardController noticeDetail notice_id-> " + notice_id);
		
		model.addAttribute("notice",noticeDto);
		
		return "noticeDetail";
		
	}
	
	/* 게시판 - 공지사항 - 수정페이지 연결  */
	@GetMapping(value = "noticeModify")
	public String noticeModify(int notice_id, Model model) {
		System.out.println("BoardController noticeModify Start...(페이지연결)");
		
		NoticeDto noticeDto = boardService.noticeDetail(notice_id);
		
		model.addAttribute("notice", noticeDto);
		
		return "noticeUpdate";
		
	}
	
	/* 게시판 - 공지사항 - 내용 수정하기  */
	@PostMapping(value = "noticeUpdate")
	public String noticeUpdate(NoticeDto noticeDto, Model model) {
		System.out.println("BoardController noticeUpdate PostMapping Start...");
		System.out.println("BoardController noticeUpdate noticeDto.getNotice_title()->"+noticeDto.getNotice_title());
		System.out.println("BoardController noticeUpdate noticeDto.getNotice_content()->"+noticeDto.getNotice_content());
		 
		int noticeUpdateCtn = boardService.noticeUpdate(noticeDto);
		System.out.println("BoardController noticeUpdate noticeUpdateCtn-> " + noticeUpdateCtn);
		
		return "forward:notice";
	}
	
	/* 게시판 - 공지사항 - 내용 삭제하기  */
	@GetMapping(value = "noticeDelete")
	public String noticeDelete(int notice_id, Model model) {
		System.out.println("BoardController noticeDelete Start...");
		int result = boardService.noticeDelete(notice_id);
		return "redirect:notice";
	}
	
//--------------------------------------------------------------------------------------------------------------

	/* 게시판 - 리뷰 - 페이지연결 및 목록구현 */
	@GetMapping(value = "/reviews")
	public String boardReview(RnQDto rnq, String currentPage, Model model) {
		log.info("review Start...");
		System.out.println("BoardController boardReview Start...");
		
		int totalReview = boardService.totalReview();
		BoardPaging page = new BoardPaging(totalReview, currentPage);
		rnq.setStart(page.getStart());
		rnq.setEnd(page.getEnd());
		
		List<RnQDto> reviewList = boardService.reviewList(rnq);
		System.out.println("BoardController boardReview reviewList.size-> " + reviewList.size());
		
		model.addAttribute("totalReview", totalReview);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("page", page);
		
		return "/review";
	}
	
	/* 게시판 - 리뷰 - 글쓰기페이지이동 */
	@GetMapping(value = "/reviewWriteForm")
	private String qnaWriteForm() {
		System.out.println("BoardController qnaWriteForm Start...");
		return "reviewWriteForm";
	}
	
	/* 게시판 - 리뷰 - 글쓰기페이지작성할때 제품선택 */
	@GetMapping(value = "/reviewChoiceForm")
	private String reviewChoiceForm() {
		System.out.println("BoardController reviewChoiceForm Start...");
		return "reviewChoiceForm";
	}

//--------------------------------------------------------------------------------------------------------------
	
	/* 게시판 - 문의 - 페이지연결 및 목록구현 */
	@GetMapping(value = "/qnas")
	public String boardQna(RnQDto rnq, String currentPage, Model model) {
		log.info("qna Start...");
		System.out.println("BoardController boardQna Start...");
		
		int totalQnA = boardService.totalQnA();
		BoardPaging page = new BoardPaging(totalQnA, currentPage);
		rnq.setStart(page.getStart());
		rnq.setEnd(page.getEnd());
		
		List<RnQDto> qnaList = boardService.qnaList(rnq);
		System.out.println("BoardController boardQna qnaList.size-> " + qnaList.size());
		
		model.addAttribute("totalQnA", totalQnA);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("page", page);
		
		return "/qna";
	}
}
