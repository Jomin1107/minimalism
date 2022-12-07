package com.oracle.minimalism.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RnQDto {
	private int    rq_id;		        // 리뷰문의 번호
	private String id;			        // 회원아이디
	private int    product_number;      // 상품번호
	private String rq_category;			// 게시판분류
	private String rq_title;			// 제목
	private String rq_name;				// 작성자
	private String rq_content;			// 내용
	private Date   rq_date;				// 등록날짜
	private int	   rq_group;			// 답변글끼리 그룹
	private int    rq_step;				// 그룹내의 순서
	private int    rq_level;			// 들여쓰기
	private int    rq_readcount;		// 조회수
	private int    rq_secret;			// 비밀글여부 (기본0)
	private int	   rq_delete;			// 삭제여부    (기본0)
	
	/* 리뷰목록조회 */
	private String stored_thumbnail;	// 제품이미지테이블 - 썸네일이미지
	private String product_name;		// 제품테이블 - 상품명
	
	private String search;              // 검색
	private String keyword;				// 검색어
	private String pagaNum;  		    // 페이지 갯수
	private int    start;			    // 행의 시작
	private int    end; 				// 행의 끝
}
