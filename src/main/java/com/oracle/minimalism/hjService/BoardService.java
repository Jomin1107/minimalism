package com.oracle.minimalism.hjService;

import java.util.List;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.RnQDto;

public interface BoardService {
	
	/* ---------------------------- 공지사항 ---------------------------- */
	/* 목록 갯수 */
	int totalNotice();
	/* 목록 내용 */
	List<NoticeDto> noticeList(NoticeDto notice);
	/* 검색 기능 갯수 */
	int totalNoticeSearch(NoticeDto noticeDto);
	/* 검색 기능  */
	List<NoticeDto> noticeListSearch(NoticeDto noticeDto);
	/* 상세보기 */
	NoticeDto noticeDetail(int notice_id);
	/* 내용 수정 */
	int noticeUpdate(NoticeDto noticeDto);
	/* 내용 삭제 */
	int noticeDelete(int notice_id);
	/* 공지사항 글작성 */
	int noticeInsert(NoticeDto noticeDto);
	/* ----------------------------  리뷰   ---------------------------- */
	/* 목록 갯수 */
	int totalReview();
	/* 목록 내용 */
	List<RnQDto> reviewList(RnQDto rnq);
	/* 검색 갯수 */
	int totalReviewSearch(RnQDto rnQDto);
	/* 검색 리스트  */
	List<RnQDto> reviewListSearch(RnQDto rnQDto);
	/* 글쓰기 페이지에서 상품명선택 */
	List<ProductDto> productNameList();
	/* 리뷰 글작성 */
	int reviewInsert(RnQDto rnQDto, List<String> savedImageList);
	/* 상세보기 */
	List<RnQDto> reviewDetail(int rq_id);
	/* 글 조회수 */
	void boardReadcount(int rq_id);
	/* 내용 수정 */
	int reviewUpdate(RnQDto rnQDto);
	/* 내용 삭제 */
	int reviewDelete(int rq_id);
	/* ----------------------------  문의   ---------------------------- */
	/* 목록 갯수 */
	int totalQnA();
	/* 목록 내용 */
	List<RnQDto> qnaList(RnQDto rnq);
	/* 상세보기 */
	List<RnQDto> qnaDetail(int rq_id);
	/* 글쓰기 */
	int qnaInsert(RnQDto rnQDto, List<String> savedImageList);
	/* 검색 갯수 */
	int totalQnaSearch(RnQDto rnQDto);
	/* 검색 리스트  */
	List<RnQDto> qnaListSearch(RnQDto rnQDto);
	/* 내용 수정 */
	int qnaUpdate(RnQDto rnQDto);
	/* 글 삭제 */
	int qnaDelete(int rq_id);
	



	

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
