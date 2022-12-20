package com.oracle.minimalism.hjService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.hjDAO.BoardDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDao boardDao;
	
	/* ---------------------------- 공지사항 ---------------------------- */
	
	/* 공지사항 글작성 */
	@Override
	public int noticeInsert(NoticeDto noticeDto) {
		System.out.println("BoardServiceImpl noticeInsert Start...");
		int noticeInsertResult = 0;
		noticeInsertResult = boardDao.noticeInsert(noticeDto);
		return noticeInsertResult;
	}
	
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
		int noticeDeleteResult = 0;
		noticeDeleteResult = boardDao.noticeDelete(notice_id);		
		return noticeDeleteResult;
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
	
	/* 검색 갯수 */
	@Override
	public int totalReviewSearch(RnQDto rnQDto) {
		System.out.println("BoardServiceImpl totalReviewSearch Start...");
		int totalReviewSearchCnt = boardDao.totalReviewSearch(rnQDto);
		System.out.println("BoardServiceImpl totalReviewSearch totalReviewSearchCnt-> "+totalReviewSearchCnt);
		return totalReviewSearchCnt;
	}

	/* 검색 리스트  */
	@Override
	public List<RnQDto> reviewListSearch(RnQDto rnQDto) {
		System.out.println("BoardServiceImpl reviewListSearch Start...");
		List<RnQDto> reviewListSearch = null;
		reviewListSearch = boardDao.reviewListSearch(rnQDto);
		System.out.println("BoardServiceImpl reviewListSearch.size-> " + reviewListSearch.size());
		return reviewListSearch;
	}
	
	/* 글쓰기 페이지에서 상품명선택 */
	@Override
	public List<ProductDto> productNameList() {
		System.out.println("BoardServiceImpl productNameList Start...");
		List<ProductDto> productList = null;
		productList = boardDao.productNameList();
		System.out.println("BoardController productNameList productList.size-> " + productList.size());

		return productList;
	}
	
	/* 리뷰 글작성 */
	@Override
	public int reviewInsert(RnQDto rnQDto, List<String> savedImageList) {
		System.out.println("BoardServiceImpl reviewInsert Start...");
		int reviewInsertResult = 0;
		reviewInsertResult = boardDao.reviewInsert(rnQDto, savedImageList);
		return reviewInsertResult;
	}
	
	/* 상세보기 */
	@Override
	public List<RnQDto> reviewDetail(int rq_id) {
		System.out.println("BoardServiceImpl reviewDetail Start...");
		
		List<RnQDto> reviewDetailList = null;
		reviewDetailList = boardDao.reviewDetail(rq_id);
		return reviewDetailList;
	}
	
	/* 글 조회수 */
	@Override
	public void boardReadcount(int rq_id) {
		System.out.println("BoardServiceImpl reviewReadcount Start...");
		int reviewReadcount = 0;
		reviewReadcount = boardDao.boardReadcount(rq_id);
		System.out.println("BoardServiceImpl reviewReadcount-> " + reviewReadcount);
		
	}
	
	/* 내용수정 */
	@Override
	public int reviewUpdate(RnQDto rnQDto) {
		System.out.println("BoardServiceImpl reviewUpdate Start...");
		int reviewUpdateCtn = 0;
		reviewUpdateCtn = boardDao.reviewUpdate(rnQDto);
		return reviewUpdateCtn;
	}
	

	
	
	/* 리뷰 내용 삭제 */
	@Override
	public int reviewDelete(int rq_id) {
		System.out.println("BoardServiceImpl reviewDelete Start...");
		int reviewDeleteResult = 0;
		reviewDeleteResult = boardDao.reviewDelete(rq_id);	
		return reviewDeleteResult;
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
	
	/* 상세보기 */
	@Override
	public List<RnQDto> qnaDetail(int rq_id) {
		System.out.println("BoardServiceImpl qnaDetail Start...");
		
		List<RnQDto> qnaDetailList = null;
		qnaDetailList = boardDao.qnaDetail(rq_id);
		System.out.println("BoardServiceImpl qnaDetailList.size-> " + qnaDetailList.size());
		return qnaDetailList;
	}

	/* 문의 글쓰기 */
	@Override
	public int qnaInsert(RnQDto rnQDto, List<String> savedImageList) {
		System.out.println("BoardServiceImpl qnaInsert Start...");
		int qnaInsertResult = 0;
		qnaInsertResult = boardDao.qnaInsert(rnQDto, savedImageList);
		return qnaInsertResult;
	}

	/* 검색 갯수 */
	@Override
	public int totalQnaSearch(RnQDto rnQDto) {
		System.out.println("BoardServiceImpl totalQnaSearch Start...");
		int totalQnaSearchCnt = boardDao.totalQnaSearch(rnQDto);
		System.out.println("BoardServiceImpl totalQnaSearch totalQnaSearchCnt-> "+totalQnaSearchCnt);
		return totalQnaSearchCnt;
	}

	/* 검색 리스트 */
	@Override
	public List<RnQDto> qnaListSearch(RnQDto rnQDto) {
		System.out.println("BoardServiceImpl qnaListSearch Start...");
		List<RnQDto> qnaListSearch = null;
		qnaListSearch = boardDao.qnaListSearch(rnQDto);
		System.out.println("BoardServiceImpl qnaListSearch.size-> " + qnaListSearch.size());
		return qnaListSearch;
	}
	
	/* 내용수정 */
	@Override
	public int qnaUpdate(RnQDto rnQDto) {
		System.out.println("BoardServiceImpl qnaUpdate Start...");
		int qnaUpdateCtn = 0;
		qnaUpdateCtn = boardDao.qnaUpdate(rnQDto);
		return qnaUpdateCtn;
	}

	/* 글 삭제 */
	@Override
	public int qnaDelete(int rq_id) {
		System.out.println("BoardServiceImpl qnaDelete Start...");
		int qnaDeleteResult = 0;
		qnaDeleteResult = boardDao.qnaDelete(rq_id);	
		System.out.println("BoardServiceImpl qnaDelete qnaDeleteResult-> "+qnaDeleteResult);
		return qnaDeleteResult;
	}

	

	
	
}
