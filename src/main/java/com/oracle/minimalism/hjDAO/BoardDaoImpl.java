package com.oracle.minimalism.hjDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.NoticeDto;
import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.RnQDto;
import com.oracle.minimalism.dto.RnQImg;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {
	
	private final SqlSession session;

	/* ---------------------------- 공지사항 ---------------------------- */
	
	/* 공지사항 글작성 */
	@Override
	public int noticeInsert(NoticeDto noticeDto) {
		System.out.println("BoardDaoImpl noticeInsert Start...");
		int noticeInsertResult = 0;
		try {
			noticeInsertResult = session.insert("hjNoticeInsert",noticeDto);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeInsert Exception Message-> " + e.getMessage());
		}
		return noticeInsertResult;
	}
	
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
		int noticeDeleteResult = 0;
		System.out.println("BoardDaoImpl noticeDelete notice_id-> " + notice_id);
		
		try {
			noticeDeleteResult = session.update("hjNoticeDelete", notice_id);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeDelete Exception Message-> " + e.getMessage());
		}
		return noticeDeleteResult;
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
	
	/* 검색 갯수 */
	@Override
	public int totalReviewSearch(RnQDto rnQDto) {
		System.out.println("BoardDaoImpl totalReviewSearch Start...");
		int totalReviewSearchCnt = 0;
		
		try {
			totalReviewSearchCnt = session.selectOne("hjReviewTotalKeyword", rnQDto);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl totalReviewSearch Exception Message-> " + e.getMessage());
		}
		return totalReviewSearchCnt;
	}

	/* 검색 리스트  */
	@Override
	public List<RnQDto> reviewListSearch(RnQDto rnQDto) {
		System.out.println("BoardDaoImpl reviewListSearch Start...");
		List<RnQDto> reviewListSearch = null;
		
		try {
			reviewListSearch = session.selectList("hjReviewSearch", rnQDto);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewListSearch Exception Message-> " + e.getMessage());
		}
		return reviewListSearch;
	}
	
	
	/* 글쓰기 페이지에서 상품명선택 */
	@Override
	public List<ProductDto> productNameList() {
		System.out.println("BoardDaoImpl productNameList Start...");
		List<ProductDto> productList = null;
		
		try {
			productList = session.selectList("hjProductNameList");
		} catch (Exception e) {
			System.out.println("BoardDaoImpl productNameList Exception Message-> " + e.getMessage());
		}
		return productList;
	}

	/* 리뷰 글작성 */
	@Override
	public int reviewInsert(RnQDto rnQDto, List<String> savedImageList) {
		System.out.println("BoardDaoImpl reviewInsert Start...");
		int reviewInsertResult = 0;
		
		try {
			System.out.println("BoardDaoImpl reviewInsert rnQDto-> " + rnQDto);

			// 1.  RNQ Insert성공 
			reviewInsertResult = session.insert("hjReviewInsert",rnQDto);
			System.out.println("BoardDaoImpl reviewInsert reviewInsertResult-> " + reviewInsertResult);
			System.out.println("BoardDaoImpl reviewInsert rnQDto.getRq_id-> " + rnQDto.getRq_id());
			if (reviewInsertResult > 0) {
				// 2.  RNQ_IMG Insert   --> RnQImg
				for (String savedImage : savedImageList ) {
					System.out.println("BoardDaoImpl reviewInsert savedImage-> " + savedImage);
					RnQImg rnQImg = new RnQImg(rnQDto.getRq_id(), 0, savedImage);
//					rnQImg.setRq_id(rnQDto.getRq_id());
//					rnQImg.setRq_filename(savedImage);
					reviewInsertResult = session.insert("hjReviewImgInsert",rnQImg);
					if (reviewInsertResult < 1 ) {
						reviewInsertResult = -1;
						return reviewInsertResult;
					}
				}
				reviewInsertResult = 1;
			} else {
				reviewInsertResult = -1;
			}
		} catch (Exception e) {
			System.out.println("BoardDaoImpl noticeInsert Exception Message-> " + e.getMessage());
		}
		return reviewInsertResult;
	}
	
	/* 상세보기 */	
	@Override
	public List<RnQDto> reviewDetail(int rq_id) {
		System.out.println("BoardDaoImpl reviewDetail Start...");
		List<RnQDto> reviewDetailList = null;
		try {
			reviewDetailList = session.selectList("hjReviewDetail", rq_id);
			System.out.println("BoardDaoImpl reviewDetail rq_id-> " + rq_id);
			System.out.println("BoardDaoImpl reviewDetail rq_id-> " + reviewDetailList);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewDetail Exception Message-> " + e.getMessage());
		}
		return reviewDetailList;
	}

	/* 글 조회수 */
	@Override
	public int boardReadcount(int rq_id) {
		System.out.println("BoardDaoImpl reviewReadcount Start...");
		int reviewReadcount = 0;
		
		try {
			reviewReadcount = session.insert("hjReviewReadcount", rq_id);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewReadcount Exception Message-> " + e.getMessage());
		}
		return reviewReadcount;
	}
	
	/* 내용 수정 */
	@Override
	public int reviewUpdate(RnQDto rnQDto) {
		System.out.println("BoardDaoImpl reviewUpdate Start...");
		int reviewUpdateCtn = 0;
		
		try {
			reviewUpdateCtn = session.update("hjReviewUpdate", rnQDto);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewUpdate Exception Message-> " + e.getMessage());
		}
		return reviewUpdateCtn;
	}
	
	/* 내용 삭제 */
	@Override
	public int reviewDelete(int rq_id) {
		System.out.println("BoardDaoImpl reviewDelete Start...");
		int reviewDeleteResult = 0;
		System.out.println("BoardDaoImpl reviewDelete rq_id-> " + rq_id);
		
		try {
			reviewDeleteResult = session.update("hjReviewDelete", rq_id);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewDelete Exception Message-> " + e.getMessage());
		}
		return reviewDeleteResult;
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

	/* 상세보기 */
	@Override
	public List<RnQDto> qnaDetail(int rq_id) {
		System.out.println("BoardDaoImpl qnaDetail Start...");
		List<RnQDto> qnaDetailList = null;
		try {
			qnaDetailList = session.selectList("hjQnaDetail", rq_id);
			System.out.println("BoardDaoImpl qnaDetail qnaDetailList.size-> " + qnaDetailList.size());
		} catch (Exception e) {
			System.out.println("BoardDaoImpl qnaDetail Exception Message-> " + e.getMessage());
		}
		
		return qnaDetailList;
	}
	
	/* 문의 글쓰기 */
	@Override
	public int qnaInsert(RnQDto rnQDto, List<String> savedImageList) {
		System.out.println("BoardDaoImpl qnaInsert Start...");
		System.out.println("BoardDaoImpl qnaInsert rnQDto-> " + rnQDto);
		int qnaInsertResult = 0;
		
		try {
			qnaInsertResult = session.insert("hjQnaInsert",rnQDto);
			System.out.println("BoardDaoImpl qnaInsert rnQDto.getRq_id-> " + rnQDto.getRq_id());
			if(qnaInsertResult > 0) {
				for(String savedImage : savedImageList) {
					System.out.println("BoardDaoImpl qnaInsert savedImage-> " + savedImage);
					RnQImg rnQImg = new RnQImg(rnQDto.getRq_id(), 0, savedImage);
					qnaInsertResult = session.insert("hjQnaImgInsert",rnQImg);
					if(qnaInsertResult < 1) {
						qnaInsertResult = -1;
						return qnaInsertResult;
					}
				}
				qnaInsertResult = 1;
			} else {
				qnaInsertResult = -1;
			}
		} catch (Exception e) {
			System.out.println("BoardDaoImpl qnaInsert Exception Message-> " + e.getMessage());
		}
		return qnaInsertResult;
	}

	/* 검색 갯수 */
	@Override
	public int totalQnaSearch(RnQDto rnQDto) {
		System.out.println("BoardDaoImpl totalQnaSearch Start...");
		int totalQnaSearchCnt = 0;
		
		try {
			totalQnaSearchCnt = session.selectOne("hjQnaTotalKeyword", rnQDto);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl totalQnaSearch Exception Message-> " + e.getMessage());
		}
		return totalQnaSearchCnt;
	}

	/* 검색 리스트  */
	@Override
	public List<RnQDto> qnaListSearch(RnQDto rnQDto) {
		System.out.println("BoardDaoImpl qnaListSearch Start...");
		List<RnQDto> qnaListSearch = null;
		
		try {
			qnaListSearch = session.selectList("hjQnaSearch", rnQDto);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl qnaListSearch Exception Message-> " + e.getMessage());
		}
		return qnaListSearch;
	}

	/* 내용 삭제 */
	@Override
	public int qnaDelete(int rq_id) {
		System.out.println("BoardDaoImpl qnaDelete Start...");
		int qnaDeleteResult = 0;
		System.out.println("BoardDaoImpl qnaDelete rq_id-> " + rq_id);
		
		try {
			qnaDeleteResult = session.update("hjQnaDelete", rq_id);
		} catch (Exception e) {
			System.out.println("BoardDaoImpl reviewDelete Exception Message-> " + e.getMessage());
		}
		return qnaDeleteResult;
	}


}
