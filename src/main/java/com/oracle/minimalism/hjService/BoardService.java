package com.oracle.minimalism.hjService;

import java.util.List;

import com.oracle.minimalism.dto.NoticeDto;
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
	/* ----------------------------  리뷰   ---------------------------- */
	/* 목록 갯수 */
	int totalReview();
	/* 목록 내용 */
	List<RnQDto> reviewList(RnQDto rnq);
	/* ----------------------------  문의   ---------------------------- */
	/* 목록 갯수 */
	int totalQnA();
	/* 목록 내용 */
	List<RnQDto> qnaList(RnQDto rnq);

	
	
	
	
}
