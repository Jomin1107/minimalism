package com.oracle.minimalism.hjDAO;

import java.util.List;

import com.oracle.minimalism.dto.ProductDto;

public interface ProductDetailDao {

	/* ------------ viewAll - 제품 전체 불러오기 ------------ */
	
	int totalProduct();
	List<ProductDto> listviewAll(ProductDto productDto);
	
	/* -------------------- 상세상품조회 -------------------- */
	
	ProductDto productOneDetail(ProductDto productDto);
	/* 상세설명 */
	List<ProductDto> descripList(int product_number);

}
