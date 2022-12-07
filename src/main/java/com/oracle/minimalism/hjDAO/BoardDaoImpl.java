package com.oracle.minimalism.hjDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.RnQDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {
	
	private final SqlSession session;

	/* ---------------------------- 공지사항 ---------------------------- */
	/* 목록 갯수 */
	@Override
	public int totalNotice() {
		System.out.println("BoardDaoImpl totalNotice Start...");
		int totNoticeCnt = 0;
		
		try {
			totNoticeCnt = session.selectOne("hjNoticeTotal");
			System.out.println("BoardDaoImpl totalNotice totNoticeCnt-> " + totNoticeCnt);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl totalNotice Exception Message-> " + e.getMessage());
		}
		
		return totNoticeCnt;
	}

	/* 목록 내용 */
	@Override
	public List<NoticeDto> noticeListAll(NoticeDto notice) {
		System.out.println("BoardDaoImpl noticeListAll Start...");
		List<NoticeDto> noticeList = null;
		
		try {
			noticeList = session.selectList("hjNoticeAll",notice);
			System.out.println("BoardDaoImpl noticeListAll noticeList.size-> " + noticeList.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeListAll Exception Message-> " + e.getMessage());
		}
		return noticeList;
	}
	
	/* 검색 기능 갯수 */
	@Override
	public int totalNoticeSearch(NoticeDto noticeDto) {
		System.out.println("BoardDaoImpl totalNoticeSearch Start...");
		int totalNoticeSearchCnt = 0;
		
		try {
			totalNoticeSearchCnt = session.selectOne("hjNoticeTotalKeyword", noticeDto);
			System.out.println("BoardDaoImpl totalNoticeSearchCnt-> " + totalNoticeSearchCnt);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl totalNoticeSearch Exception Message-> " + e.getMessage());
		}
		return totalNoticeSearchCnt;
	}

	
	/* 검색 기능 */
	@Override
	public List<NoticeDto> noticeListSearch(NoticeDto noticeDto) {
		System.out.println("BoardDaoImpl noticeListSearch Start...");
		List<NoticeDto> noticeListSearch = null;
        System.out.println("BoardDaoImpl noticeListSearch noticeDto.getStart()->"+noticeDto.getStart());  
        System.out.println("BoardDaoImpl noticeListSearch noticeDto.getEnd()->"+noticeDto.getEnd());  
        System.out.println("BoardDaoImpl noticeListSearch noticeDto.getKeyword()->"+noticeDto.getKeyword());  

		
		try {
			noticeListSearch = session.selectList("hjNoticeSearch", noticeDto);
			System.out.println("BoardDaoImpl noticeListSearch noticeListSearch.size-> " + noticeListSearch.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeListSearch Exception Message-> " + e.getMessage());
		}
		return noticeListSearch;
	}
	
	/* 상세보기 */
	@Override
	public NoticeDto noticeDetail(int notice_id) {
		System.out.println("BoardDaoImpl noticeDetail Start...");
		NoticeDto noticeDto = null;
		
		try {
			noticeDto = session.selectOne("hjNoticeDetail",notice_id);
			System.out.println("BoardDaoImpl noticeDetail notice_id-> " + notice_id);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeDetail Exception Message-> " + e.getMessage());
		}
		return noticeDto;
	}
	
	/* 내용 수정 */
	@Override
	public int noticeUpdate(NoticeDto noticeDto) {
		System.out.println("BoardDaoImpl noticeUpdate Start...");
		int noticeUpdateCtn = 0;
		
		try {
			noticeUpdateCtn = session.update("hjNoticeUpdate",noticeDto);
			System.out.println("BoardDaoImpl noticeUpdate noticeUpdateCtn-> " + noticeUpdateCtn);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeUpdate Exception Message-> " + e.getMessage());
		}
		return noticeUpdateCtn;
	}
	
	/* 내용 삭제 */
	@Override
	public int noticeDelete(int notice_id) {
		System.out.println("BoardDaoImpl noticeDelete Start...");
		int result = 0;
		System.out.println("BoardDaoImpl noticeDelete notice_id-> " + notice_id);
		
		try {
			result = session.delete("hjNoticeDelete", notice_id);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeDelete Exception Message-> " + e.getMessage());
		}
		return result;
	}
	
	
	

	/* ----------------------------  리뷰   ---------------------------- */
	/* 목록 갯수 */
	@Override
	public int totalReview() {
		System.out.println("BoardDaoImpl totalReview Start...");
		int totReviewCnt = 0;
		try {
			totReviewCnt = session.selectOne("hjReviewTotal");
			System.out.println("BoardDaoImpl totalReview totReviewCnt-> " + totReviewCnt);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl totalReview Exception Message-> " + e.getMessage());
		}
		return totReviewCnt;
	}

	/* 목록 내용 */
	@Override
	public List<RnQDto> reviewListAll(RnQDto rnq) {
		System.out.println("BoardDaoImpl reviewListAll Start...");
		List<RnQDto> reviewList = null;
		try {
			reviewList = session.selectList("hjReviewAll",rnq);
			System.out.println("BoardDaoImpl reviewListAll reviewList.size-> " + reviewList.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewListAll Exception Message-> " + e.getMessage());
		}
		return reviewList;
	}
	
	/* ----------------------------  문의   ---------------------------- */
	/* 목록 갯수 */
	@Override
	public int totalQnA() {
		System.out.println("BoardDaoImpl totalQnA Start...");
		int totQnACnt = 0;
		try {
			totQnACnt = session.selectOne("hjQnaTotal");
			System.out.println("BoardDaoImpl totalQnA totQnACnt-> " + totQnACnt);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl totalQnA Exception Message-> " + e.getMessage());
		}
		return totQnACnt;
	}

	/* 목록 내용 */
	@Override
	public List<RnQDto> qnaListAll(RnQDto rnq) {
		System.out.println("BoardDaoImpl qnaListAll Start...");
		List<RnQDto> qnaList = null;
		try {
			qnaList = session.selectList("hjQnaAll",rnq);
			System.out.println("BoardDaoImpl qnaListAll qnaList.size-> " + qnaList.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl qnaListAll Exception Message-> " + e.getMessage());
		}
		return qnaList;
	}

	
	

	

	

	
}
