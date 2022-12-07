package com.oracle.minimalism.hjService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.hjDAO.BoardDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	/* ---------------------------- 공지사항 ---------------------------- */
	/* 목록 갯수 */
	@Override
	public int totalNotice() {
		System.out.println("BoardServiceImpl totalNotice Start...");
		int totNoticeCnt = boardDao.totalNotice();
		System.out.println("BoardServiceImpl totalNotice Cnt-> " + totNoticeCnt);
		return totNoticeCnt;
	}

	/* 목록 내용 */
	@Override
	public List<NoticeDto> noticeList(NoticeDto notice) {
		System.out.println("BoardServiceImpl noticeList Start...");
		List<NoticeDto> noticeList = null;
		noticeList = boardDao.noticeListAll(notice);
		System.out.println("BoardServiceImpl noticeList.size-> " + noticeList.size());
		return noticeList;
	}
	
	/* 검색 기능 갯수 */
	@Override
	public int totalNoticeSearch(NoticeDto noticeDto) {
		System.out.println("BoardServiceImpl totalNoticeSearch Start...");
		int totalNoticeSearchCnt = boardDao.totalNoticeSearch(noticeDto);
		System.out.println("BoardServiceImpl totalNoticeSearchCnt->" + totalNoticeSearchCnt);
		return totalNoticeSearchCnt;
	}
	
	/* 검색 기능 */
	@Override
	public List<NoticeDto> noticeListSearch(NoticeDto noticeDto) {
		System.out.println("BoardServiceImpl noticeListSearch Start...");
		List<NoticeDto> noticeListSearch = null;
		noticeListSearch = boardDao.noticeListSearch(noticeDto);
		System.out.println("BoardServiceImpl noticeListSearch.size-> " + noticeListSearch.size());
		return noticeListSearch;
	}
	
	/* 상세보기 */
	@Override
	public NoticeDto noticeDetail(int notice_id) {
		System.out.println("BoardServiceImpl noticeDetail Start...");
		NoticeDto noticeDto = null;
		noticeDto = boardDao.noticeDetail(notice_id);
		return noticeDto;
	}
	
	/* 내용 수정 */
	@Override
	public int noticeUpdate(NoticeDto noticeDto) {
		System.out.println("BoardServiceImpl noticeUpdate Start...");
		int noticeUpdateCtn = 0;
		noticeUpdateCtn = boardDao.noticeUpdate(noticeDto);
		return noticeUpdateCtn;
	}
	
	/* 내용 삭제 */
	@Override
	public int noticeDelete(int notice_id) {
		System.out.println("BoardServiceImpl noticeDelete Start...");
		int result = 0;
		result = boardDao.noticeDelete(notice_id);		
		return result;
	}
	
	/* ----------------------------  리뷰   ---------------------------- */
	/* 목록 갯수 */
	public int totalReview() {
		System.out.println("BoardServiceImpl totalReview Start...");
		int totReviewCnt = boardDao.totalReview();
		System.out.println("BoardServiceImpl totalReview Cnt-> " + totReviewCnt);
		return totReviewCnt;
	}

	/* 목록 내용 */
	@Override
	public List<RnQDto> reviewList(RnQDto rnq) {
		System.out.println("BoardServiceImpl reviewList Start...");
		List<RnQDto> reviewList = null;
		reviewList = boardDao.reviewListAll(rnq);
		System.out.println("BoardServiceImpl reviewList.size-> " + reviewList.size());
		return reviewList;
	}
	/* ----------------------------  문의   ---------------------------- */
	/* 목록 갯수 */
	@Override
	public int totalQnA() {
		System.out.println("BoardServiceImpl totalQnA Start...");
		int totQnACnt = boardDao.totalQnA();
		System.out.println("BoardServiceImpl totalQnA Cnt-> " + totQnACnt);
		return totQnACnt;
	}

	/* 목록 내용 */
	@Override
	public List<RnQDto> qnaList(RnQDto rnq) {
		System.out.println("BoardServiceImpl qnaList Start...");
		List<RnQDto> qnaList = null;
		qnaList = boardDao.qnaListAll(rnq);
		System.out.println("BoardServiceImpl qnaList.size-> " + qnaList.size());
		return qnaList;
	}

	

	

	

	

	
}
