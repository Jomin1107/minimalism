package com.oracle.minimalism.hjService;

import java.util.List;

import com.oracle.minimalism.dto.ProductDto;

public interface ProductDetailService {
	
	/* ------------ viewAll - 제품 전체 불러오기 ------------ */

	int totalProduct();
	List<ProductDto> listviewAll(ProductDto productDto);
	
	/* -------------------- 상세상품조회 -------------------- */
//	ProductDto productDetail(int product_number);
	
	ProductDto productDetail(ProductDto productDto);

}
