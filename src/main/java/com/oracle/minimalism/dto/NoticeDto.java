package com.oracle.minimalism.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDto {
	private int    notice_id;			// 공지사항 번호
	private String notice_title;		// 제목
	private String notice_name;			// 작성자
	private String notice_content;		// 내용
	private Date   notice_date;			// 등록날짜
	private int    n_group;				// 답변글끼리 그룹
	private int    n_step;				// 그룹내의 순서
	private int    n_level;				// 들여쓰기
	private int    n_delete;			// 삭제여부 (기본0)
	
	/* 조회용 */
	private String search;    			// 검색         
	private String keyword;				// 검색어
	private String pagaNum;  		   	// 페이징 갯수
	private int    start;				// 페이지 시작		   
	private int    end;					// 페이지 끝
	
	private String id;			// 아이디
}
