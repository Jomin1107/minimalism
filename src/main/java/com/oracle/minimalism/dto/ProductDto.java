package com.oracle.minimalism.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDto {
	private int     product_number;
	private int     category_code;
	private String  product_name;
	private int     product_price;
	private int     product_stock;
	private Date    product_date;
	private int     product_delete;
	private String  product_color;
	private String  product_description;
	
	// 조회용
	private String pageNum;
	private int start;				private int end;
	private String stored_thumbnail; // 썸네일사진
	private String stored_filename;	 // 상품상세이미지
}
