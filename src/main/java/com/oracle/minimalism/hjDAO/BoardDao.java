package com.oracle.minimalism.hjDAO;

import java.util.List;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.RnQDto;

public interface BoardDao {
	
	/* ---------------------------- 공지사항 ---------------------------- */
	/* 공지사항 글작성 */
	int noticeInsert(NoticeDto noticeDto);
	/* 공지사항 목록 갯수 */
	int totalNotice();
	/* 공지사항 목록 내용 */
	List<NoticeDto> noticeListAll(NoticeDto notice);
	/* 공지사항 검색 기능 갯수 */
	int totalNoticeSearch(NoticeDto noticeDto);
	/* 공지사항 검색 기능 */
	List<NoticeDto> noticeListSearch(NoticeDto noticeDto);
	/* 공지사항 상세보기 */
	NoticeDto noticeDetail(int notice_id);
	/* 공지사항 내용 수정 */
	int noticeUpdate(NoticeDto noticeDto);
	/* 공지사항 내용 삭제 */
	int noticeDelete(int notice_id);
	/* ----------------------------  리뷰   ---------------------------- */
	/* 리뷰 목록 갯수 */
	int totalReview();
	/* 리뷰 목록 내용 */
	List<RnQDto> reviewListAll(RnQDto rnq);
	/* 검색 갯수 */
	int totalReviewSearch(RnQDto rnQDto);
	/* 검색 리스트 */
	List<RnQDto> reviewListSearch(RnQDto rnQDto);	
	/* 글쓰기 페이지에서 상품명선택 */
	List<ProductDto> productNameList();
	/* 리뷰 글작성 */
	int reviewInsert(RnQDto rnQDto, List<String> savedImageList);
	/* 상세보기 */
	List<RnQDto> reviewDetail(int rq_id);
	/* 글 조회수 */
	int boardReadcount(int rq_id);
	/* 내용 수정 */
	int reviewUpdate(RnQDto rnQDto);
	/* 내용삭제 */
	int reviewDelete(int rq_id);
	/* ----------------------------  문의   ---------------------------- */
	/* 문의 목록 갯수 */
	int totalQnA();
	/* 문의 목록 내용 */
	List<RnQDto> qnaListAll(RnQDto rnq);
	/* 문의 상세 보기 */
	List<RnQDto> qnaDetail(int rq_id);
	/* 글쓰기 */
	int qnaInsert(RnQDto rnQDto, List<String> savedImageList);
	/* 검색 갯수 */
	int totalQnaSearch(RnQDto rnQDto);
	/* 검색 리스트 */
	List<RnQDto> qnaListSearch(RnQDto rnQDto);
	/* 내용 수정 */
	int qnaUpdate(RnQDto rnQDto);
	/* 글 삭제 */
	int qnaDelete(int rq_id);





	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
