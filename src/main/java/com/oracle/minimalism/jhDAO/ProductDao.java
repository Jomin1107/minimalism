package com.oracle.minimalism.jhDAO;

import java.util.List;

import com.oracle.minimalism.dto.ProductDto;

public interface ProductDao {

	int 				totalProduct(); // viewAll 갯수

	List<ProductDto> 	listviewAll(ProductDto productDto, int value); // viewAll
	
	int 				totalProduct1(int category); // 카테고리별 갯수

	List<ProductDto> 	listviewCate(ProductDto productDto, int category, int value); // 카테고리별
}
