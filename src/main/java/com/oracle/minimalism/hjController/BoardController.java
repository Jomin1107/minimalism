package com.oracle.minimalism.hjController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.dto.UserDto;
import com.oracle.minimalism.hjService.BoardPaging;
import com.oracle.minimalism.hjService.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	/* 게시판 - 공지사항 - 글쓰기 페이지 이동 */
	@RequestMapping(value = "/board/WriteNoticeForm", method = RequestMethod.GET)
	public String noticeWriteForm(HttpServletRequest request) {
		System.out.println("BoardController noticeWriteForm Page Start...");
		
		/* minimalism만 글쓰기버튼 보이기 - 로그인 체크 */
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("BoardController noticeWriteForm userDto-> " + userDto);
				
		return "noticeWriteForm";
	}
	
	/* 게시판 - 공지사항 - 글쓰기 */
	@RequestMapping(value = "/board/noticeWrite",  method = RequestMethod.POST)
	public String noticeWrite(NoticeDto noticeDto, Model model,
							  HttpServletRequest request) {
		System.out.println("BoardController noticeWrite Start...");
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("BoardController noticeWriteForm userDto-> " + userDto);
		
		int noticeInsertResult = boardService.noticeInsert(noticeDto);
		
		if (noticeInsertResult > 0) {
			return "redirect:/board/notices";
		} else {
			model.addAttribute("msg", "게시글 등록이 되지 않았습니다.");
			return "forward:/board/WriteNoticeForm";
		}
		
	}
	
	/* 게시판 - 공지사항 - 페이지연결 및 목록구현 */
	@RequestMapping(value = "/board/notices")
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
	@GetMapping(value = "/board/noticeSearch")
	public String noticeSearch(NoticeDto noticeDto, String currentPage, Model model) {
		System.out.println("BoardController noticeSearch Start...");
		System.out.println("BoardController noticeSearch currentPage->"+currentPage);
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
		model.addAttribute("noticeDto", noticeDto);
		model.addAttribute("page", page);
		
		return "/notice";
	}
	
	/* 게시판 - 공지사항 - 상세보기  */
	@GetMapping(value = "/board/noticeDetail")
	public String noticeDetail(int notice_id, Model model,
							   HttpServletRequest request) {
		System.out.println("BoardController noticeDetail Start...");
		
		/* 작성자만 수정,삭제버튼 보이기 - 로그인 체크 */
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("BoardController noticeWriteForm userDto-> " + userDto);

		
		NoticeDto noticeDto = boardService.noticeDetail(notice_id);
		System.out.println("BoardController noticeDetail notice_id-> " + notice_id);
		
		model.addAttribute("notice",noticeDto);
		
		return "noticeDetail";		
	}
	
	/* 게시판 - 공지사항 - 수정페이지 연결  */
	@GetMapping(value = "/board/noticeModify")
	public String noticeModify(int notice_id, Model model) {
		System.out.println("BoardController noticeModify Start...(페이지연결)");
		
		NoticeDto noticeDto = boardService.noticeDetail(notice_id);
		
		model.addAttribute("notice", noticeDto);
		
		return "noticeUpdate";
		
	}
	
	/* 게시판 - 공지사항 - 내용 수정하기  */
	@PostMapping(value = "/board/noticeUpdate")
	public String noticeUpdate(NoticeDto noticeDto, Model model) {
		System.out.println("BoardController noticeUpdate PostMapping Start...");
		System.out.println("BoardController noticeUpdate noticeDto.getNotice_title()->"+noticeDto.getNotice_title());
		System.out.println("BoardController noticeUpdate noticeDto.getNotice_content()->"+noticeDto.getNotice_content());
		 
		int noticeUpdateCtn = boardService.noticeUpdate(noticeDto);
		System.out.println("BoardController noticeUpdate noticeUpdateCtn-> " + noticeUpdateCtn);
		
		return "forward:/board/notices";
	}
	
	/* 게시판 - 공지사항 - 내용 삭제하기  */
	@GetMapping(value = "/board/noticeDelete")
	public String noticeDelete(int notice_id, Model model) {
		System.out.println("BoardController noticeDelete Start...");
		int noticeDeleteResult = boardService.noticeDelete(notice_id);
		System.out.println("BoardController noticeDeleteResult-> " + noticeDeleteResult);
		return "redirect:/board/notices";
	}
	
//--------------------------------------------------------------------------------------------------------------

	/* 게시판 - 리뷰 - 페이지연결 및 목록구현 */
	@RequestMapping(value = "/board/reviews" )
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
	
	/* 게시판 - 리뷰 - 검색기능 */
	@GetMapping(value = "/board/reviewSearch")
	public String reviewSearch(RnQDto rnQDto, String currentPage, Model model) {
		System.out.println("BoardController reviewSearch Start...");
		
		int totalReviewSearchCnt = boardService.totalReviewSearch(rnQDto);
		System.out.println("BoardController reviewSearch totalReviewSearchCnt->"+totalReviewSearchCnt);
		
		BoardPaging page = new BoardPaging(totalReviewSearchCnt, currentPage);
		rnQDto.setStart(page.getStart());
		rnQDto.setEnd(page.getEnd());
		
		List<RnQDto> reviewListSearch = boardService.reviewListSearch(rnQDto);
		System.out.println("BoardController reviewSearch reviewListSearch.size-> " + reviewListSearch.size());
		
		model.addAttribute("totalReview", totalReviewSearchCnt);
		model.addAttribute("reviewList", reviewListSearch);
		model.addAttribute("rnQDto", rnQDto);
		model.addAttribute("page", page);
		
		return "/review";
	}	
	
	
	/* 게시판 - 리뷰 - 글쓰기페이지이동 (InterCeptor 설정했지롱)*/
	// 2
	@RequestMapping(value = "/board/WriteReviewForm")
	public String reviewWriteForm(Model model) {
		System.out.println("BoardController reviewWriteForm (2번) Start...");
		
		/* 글쓰기 페이지에서 상품명선택 */
		List<ProductDto> productList = boardService.productNameList();
		System.out.println("BoardController reviewWriteForm productList.size-> " + productList.size());
		
		model.addAttribute("productList", productList);
		
		return "reviewWriteForm";
	}
	
	/* 리뷰 글작성 */
	@PostMapping(value = "/board/reviewWrite")
	public String reviewWrite(RnQDto rnQDto, Model model, HttpServletRequest request, 
							  MultipartFile imgFile1, MultipartFile imgFile2,
							  MultipartFile imgFile3, MultipartFile imgFile4, MultipartFile imgFile5 
							  ) throws IOException, Exception {
		System.out.println("BoardController reviewWrite Start...");
		System.out.println("BoardController reviewWrite rnQDto.getProduct_name->"+rnQDto.getProduct_name());
		
		List<String> savedImageList = new ArrayList<>();  //리스트
		String uploadPath = request.getSession().getServletContext().getRealPath("/reviewImg");
		System.out.println("BoardController reviewWrite uploadPath-> " + uploadPath);
		
		for(int i=1; i < 6 ; i++ ) {
			switch (i) {
			case 1:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile1.getOriginalFilename());
			    if (imgFile1.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile1 getOriginalFilename" +  imgFile1.getOriginalFilename());
					log.info("imgFile1.getSize" + imgFile1.getSize());
					log.info("imgFile1.getContentType" + imgFile1.getContentType());
					log.info("imgFile1 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile1.getOriginalFilename(), imgFile1.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile1-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;

			case 2:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile2.getOriginalFilename());
			    if (imgFile2.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile2 getOriginalFilename:" +  imgFile2.getOriginalFilename());
					log.info("imgFile2.getSize:" + imgFile2.getSize());
					log.info("imgFile2.getContentType:" + imgFile2.getContentType());
					log.info("imgFile2 uploadPath:" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile2.getOriginalFilename(), imgFile2.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile2-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;
			
			case 3:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile3.getOriginalFilename());
			    if (imgFile3.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile3 getOriginalFilename" +  imgFile3.getOriginalFilename());
					log.info("imgFile3.getSize" + imgFile3.getSize());
					log.info("imgFile3.getContentType" + imgFile3.getContentType());
					log.info("imgFile3 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile3.getOriginalFilename(), imgFile3.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile3-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;
				
			case 4:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile4.getOriginalFilename());
			    if (imgFile4.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile4 getOriginalFilename" +  imgFile4.getOriginalFilename());
					log.info("imgFile4.getSize" + imgFile4.getSize());
					log.info("imgFile4.getContentType" + imgFile4.getContentType());
					log.info("imgFile4 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile4.getOriginalFilename(), imgFile4.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile4-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;	
				
			case 5:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile5.getOriginalFilename());
			    if (imgFile5.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile5 getOriginalFilename" +  imgFile5.getOriginalFilename());
					log.info("imgFile5.getSize" + imgFile5.getSize());
					log.info("imgFile5.getContentType" + imgFile5.getContentType());
					log.info("imgFile5 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile5.getOriginalFilename(), imgFile5.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile5-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;			
			default:
				break;
			}			
		}

		int reviewInsertResult = boardService.reviewInsert(rnQDto, savedImageList );
		if (reviewInsertResult > 0) {
			model.addAttribute("msg", "게시글 등록 되었습니다.");
			return  "redirect:/board/reviews";
		}
		else {
			model.addAttribute("msg", "게시글 등록이 되지 않았습니다.");
			return "forward:/board/reviewWriteForm";
		}		
	}
	
	private String uploadFile(String originalName, byte[] fileData, String uploadPath)throws Exception {
		
		UUID uid = UUID.randomUUID();
		System.out.println("uploadFile uploadPath-> " + uploadPath);
		
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("uploadFile 업로드용 폴더 생성"+ uploadPath);
		}
		String savedName = uid.toString() + "_" + originalName;
		log.info("uploadFile  savedName-> " + savedName);
		File target = new File(uploadPath,savedName);
		FileCopyUtils.copy(fileData, target);
		System.out.println("uploadFile FileCopyUtils After.. ");
		return savedName;
	}
	
	/* 게시판 - 리뷰 - 상세보기  */
	@GetMapping(value = "/board/reviewDetail")
	public String reviewDetail(int rq_id, Model model,
							   HttpServletRequest request) {
		System.out.println("BoardController reviewDetail Start...");

		
		/* 작성자만 수정,삭제버튼 보이기 - 로그인 체크*/
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("BoardController reviewDetail userDto->"+ userDto);
		
		/* 조회수 UP */
		boardService.boardReadcount(rq_id);
		
		/* 내용 보이기 */
		System.out.println("BoardController reviewDetail rq_id-> " + rq_id);
		List<RnQDto> reviewDetailList = boardService.reviewDetail(rq_id);
		System.out.println("BoardController reviewDetail reviewDetailList.size()-> " + reviewDetailList.size());
		System.out.println("BoardController reviewDetail rq_id-> " + rq_id);

		model.addAttribute("reviewDetailList",reviewDetailList);
		
		return "reviewDetail";
	}	
	
	/* 게시판 - 리뷰 - 수정페이지 연결  */
	/* 수정페이지 내용(이미지수정불가) */	
	@RequestMapping(value = "/board/reviewModify", method = RequestMethod.GET)
	public String reviewModify(int rq_id, Model model,
			 HttpServletRequest request) {
		System.out.println("BoardController reviewModify Start...(페이지연결)");
		System.out.println("BoardController reviewModify rq_id->" + rq_id);
		
		/* 작성자만 수정,삭제버튼 보이기 - 로그인 체크*/
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");

		List<RnQDto> reviewModifyList = boardService.reviewDetail(rq_id);

		model.addAttribute("rq_id",rq_id);
		model.addAttribute("reviewDetailList",reviewModifyList);
		
		return "reviewUpdate";
		
	}		
	
	/* 게시판 - 리뷰 - 내용 수정하기  */
	@PostMapping(value = "/board/reviewUpdate")
	public String reviewUpdate(RnQDto rnQDto, Model model,
							   HttpServletRequest request) {
		System.out.println("BoardController reviewUpdate PostMapping Start...");
		System.out.println("BoardController reviewUpdate rnQDto.getRq_id->"+ rnQDto.getRq_id());
		System.out.println("BoardController reviewUpdate rnQDto.getRq_title->"+ rnQDto.getRq_title());
		System.out.println("BoardController reviewUpdate rnQDto.getRq_content->"+ rnQDto.getRq_content());
		 
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("loginUser");
//		System.out.println("BoardController reviewUpdate userDto-> " + userDto);
		
		int reviewUpdateCtn = boardService.reviewUpdate(rnQDto);
		System.out.println("BoardController reviewUpdate noticeUpdateCtn-> " + reviewUpdateCtn);
		
		return "forward:/board/reviews";
	}
	
	
	/* 댓글 작성 - 미구현 */
//	@PostMapping(value = "/board/reviewReplyWrite")
//	public String reviewReplyWrite() {
//		return "redirect:/board/reviewDetail";
//	}
	
	/* 게시판 - 리뷰 - 글삭제  */
	@GetMapping(value = "/board/reviewDelete")
	public String reviewDelete(int rq_id, Model model) {
		System.out.println("BoardController reviewDelete Start...");
		int reviewDeleteResult = boardService.reviewDelete(rq_id);
		System.out.println("BoardController reviewDeleteResult-> " + reviewDeleteResult);
		return "redirect:/board/reviews";
	}


//--------------------------------------------------------------------------------------------------------------
	
	/* 게시판 - 문의 - 페이지연결 및 목록구현 */
	@GetMapping(value = "/board/qnas")
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
	
	/* 상세보기 */
	@GetMapping(value = "/board/qnaDetail")
	public String qnaDetail(int rq_id, Model model,
							HttpServletRequest request) {
		System.out.println("BoardController qnaDetail Start...");
		System.out.println("BoardController qnaDetail rq_id-> "+ rq_id);	
		
		/* 작성자만 수정,삭제버튼 보이기 - 로그인 체크*/
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");
		System.out.println("BoardController reviewDetail userDto->"+ userDto);
		
		/* 조회수 UP */
		boardService.boardReadcount(rq_id);
		
		/* 내용보이기 */
		List<RnQDto> qnaDetailList = boardService.qnaDetail(rq_id);	
		System.out.println("BoardController qnaDetail rq_id-> "+ rq_id);	
		
		model.addAttribute("qnaDetailList", qnaDetailList);

		return "qnaDetail";
	}
	
	/* 게시판 - 문의  - 글쓰기페이지이동  (InterCeptor 설정) */
	@RequestMapping(value = "/board/WriteQnAForm")
	public String qnaWriteForm(Model model) {
		System.out.println("BoardController qnaWriteForm Start...");
		
		/* 글쓰기 페이지에서 상품명선택 */
		List<ProductDto> productList = boardService.productNameList();
		System.out.println("BoardController reviewWriteForm productList.size-> " + productList.size());
		
		model.addAttribute("productList", productList);
		
		return "qnaWriteForm";
	}
	
	/* 게시판 - 문의 - 글쓰기 */
	
	/* 리뷰 글작성 */
	@PostMapping(value = "/board/qnaWrite")
	public String qnaWrite(RnQDto rnQDto, Model model, HttpServletRequest request, 
							  MultipartFile imgFile1, MultipartFile imgFile2,
							  MultipartFile imgFile3, MultipartFile imgFile4, MultipartFile imgFile5 
							  ) throws IOException, Exception {
		System.out.println("BoardController reviewWrite Start...");
		System.out.println("BoardController reviewWrite rnQDto.getProduct_name->"+rnQDto.getProduct_name());
		
		List<String> savedImageList = new ArrayList<>();  //리스트
		String uploadPath = request.getSession().getServletContext().getRealPath("/qnaImg");
		System.out.println("BoardController reviewWrite uploadPath-> " + uploadPath);
		
		for(int i=1; i < 6 ; i++ ) {
			switch (i) {
			case 1:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile1.getOriginalFilename());
			    if (imgFile1.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile1 getOriginalFilename" +  imgFile1.getOriginalFilename());
					log.info("imgFile1.getSize" + imgFile1.getSize());
					log.info("imgFile1.getContentType" + imgFile1.getContentType());
					log.info("imgFile1 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile1.getOriginalFilename(), imgFile1.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile1-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;

			case 2:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile2.getOriginalFilename());
			    if (imgFile2.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile2 getOriginalFilename:" +  imgFile2.getOriginalFilename());
					log.info("imgFile2.getSize:" + imgFile2.getSize());
					log.info("imgFile2.getContentType:" + imgFile2.getContentType());
					log.info("imgFile2 uploadPath:" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile2.getOriginalFilename(), imgFile2.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile2-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;
			
			case 3:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile3.getOriginalFilename());
			    if (imgFile3.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile3 getOriginalFilename" +  imgFile3.getOriginalFilename());
					log.info("imgFile3.getSize" + imgFile3.getSize());
					log.info("imgFile3.getContentType" + imgFile3.getContentType());
					log.info("imgFile3 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile3.getOriginalFilename(), imgFile3.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile3-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;
				
			case 4:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile4.getOriginalFilename());
			    if (imgFile4.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile4 getOriginalFilename" +  imgFile4.getOriginalFilename());
					log.info("imgFile4.getSize" + imgFile4.getSize());
					log.info("imgFile4.getContentType" + imgFile4.getContentType());
					log.info("imgFile4 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile4.getOriginalFilename(), imgFile4.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile4-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;	
				
			case 5:
				System.out.println("BoardController reviewWrite"+ i + " :  "+imgFile5.getOriginalFilename());
			    if (imgFile5.getSize() < 1) {
			    	System.out.println("BoardController reviewWrite"+ i + " 번째 이미지는 없습니다.  ");
			    } else {
					log.info("imgFile5 getOriginalFilename" +  imgFile5.getOriginalFilename());
					log.info("imgFile5.getSize" + imgFile5.getSize());
					log.info("imgFile5.getContentType" + imgFile5.getContentType());
					log.info("imgFile5 uploadPath" + uploadPath);

					/* 진짜 업로드 로직 */
			    	String reviewSaveName = uploadFile(imgFile5.getOriginalFilename(), imgFile5.getBytes(), uploadPath);
			    	savedImageList.add(reviewSaveName);
			    	System.out.println("BoardController reviewWrite reviewSaveName imgFile5-> " + reviewSaveName);
			    	System.out.println("BoardController reviewWrite savedImage.size-> " + savedImageList.size());
			    }				
				break;			
			default:
				break;
			}			
		}

		int reviewInsertResult = boardService.qnaInsert(rnQDto, savedImageList);
		if (reviewInsertResult > 0) {
			model.addAttribute("msg", "게시글 등록 되었습니다.");
			return  "redirect:/board/qnas";
		}
		else {
			model.addAttribute("msg", "게시글 등록이 되지 않았습니다.");
			return "forward:/board/qnaWriteForm";
		}		
	}
	
	/* 검색기능 */
	@GetMapping(value = "/board/qnaSearch")
	public String qnaSearch(RnQDto rnQDto, String currentPage, Model model) {
		System.out.println("BoardController qnaSearch Start...");
		
		int totalQnaSearchCnt = boardService.totalQnaSearch(rnQDto);
		System.out.println("BoardController qnaSearch totalReviewSearchCnt->"+totalQnaSearchCnt);
		
		BoardPaging page = new BoardPaging(totalQnaSearchCnt, currentPage);
		rnQDto.setStart(page.getStart());
		rnQDto.setEnd(page.getEnd());
		
		List<RnQDto> qnaListSearch = boardService.qnaListSearch(rnQDto);
		System.out.println("BoardController reviewSearch reviewListSearch.size-> " + qnaListSearch.size());
		
		model.addAttribute("totalReview", totalQnaSearchCnt);
		model.addAttribute("qnaList", qnaListSearch);
		model.addAttribute("rnQDto", rnQDto);
		model.addAttribute("page", page);
		
		return "/qna";
	}
	
	/* 게시판 - 문의 - 수정페이지 연결  */
	/* 수정페이지 내용(이미지수정불가) */	
	@RequestMapping(value = "/board/qnaModify", method = RequestMethod.GET)
	public String qnaModify(int rq_id, Model model,
			 HttpServletRequest request) {
		System.out.println("BoardController qnaModify Start...(페이지연결)");
		System.out.println("BoardController qnaModify rq_id->" + rq_id);
		
		/* 작성자만 수정,삭제버튼 보이기 - 로그인 체크*/
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("loginUser");

		List<RnQDto> qnaModifyList = boardService.qnaDetail(rq_id);

		model.addAttribute("rq_id",rq_id);
		model.addAttribute("qnaDetailList",qnaModifyList);
		
		return "qnaUpdate";
		
	}		
	
	/* 게시판 - 문의 - 내용 수정하기  */
	@PostMapping(value = "/board/qnaUpdate")
	public String qnaUpdate(RnQDto rnQDto, Model model,
							   HttpServletRequest request) {
		System.out.println("BoardController reviewUpdate PostMapping Start...");
		System.out.println("BoardController reviewUpdate rnQDto.getRq_id->"+ rnQDto.getRq_id());
		System.out.println("BoardController reviewUpdate rnQDto.getRq_title->"+ rnQDto.getRq_title());
		System.out.println("BoardController reviewUpdate rnQDto.getRq_content->"+ rnQDto.getRq_content());
		
		int qnaUpdateCtn = boardService.qnaUpdate(rnQDto);
		System.out.println("BoardController reviewUpdate noticeUpdateCtn-> " + qnaUpdateCtn);
		
		return "forward:/board/reviews";
	}
	
	/* 글 삭제 */
	@GetMapping(value = "/board/qnaDelete")
	public String qnaDelete(int rq_id, Model model) {
		System.out.println("BoardController qnaDelete Start...");
		int qnaDeleteResult = boardService.qnaDelete(rq_id);
		System.out.println("BoardController qnaDeleteResult-> " + qnaDeleteResult);
		return "redirect:/board/qnas";
	}

	
	
		

	
}
