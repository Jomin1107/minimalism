package com.oracle.minimalism.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RnQImg {
	private int    rq_id;		        // 리뷰문의 번호
	private int    rq_imgnumber;		// 이미지 번호
	private String rq_filename;			// 리뷰이미지파일명
}
