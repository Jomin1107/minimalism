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
	private String search;             
	private String keyword;
	private String pagaNum;  		   
	private int    start;			   
	private int    end;
}
