package com.oracle.minimalism.hjDAO;

import java.util.List;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.RnQDto;

public interface BoardDao {
	
	/* ---------------------------- 공지사항 ---------------------------- */
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
	/* ----------------------------  문의   ---------------------------- */
	/* 문의 목록 갯수 */
	int totalQnA();
	/* 문의 목록 내용 */
	List<RnQDto> qnaListAll(RnQDto rnq);
	
	
	
	
	
}
