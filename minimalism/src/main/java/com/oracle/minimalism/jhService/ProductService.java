package com.oracle.minimalism.jhService;

import java.util.List;

import com.oracle.minimalism.dto.ProductDto;

public interface ProductService {

	int 				totalProduct();	// viewAll 갯수

	List<ProductDto> 	listviewAll(ProductDto productDto);
	
	int 				totalProduct1(); // shoulder 갯수

	List<ProductDto> 	listviewShoulder(ProductDto productDto);
	
	int 				totalProduct2(); // cross 갯수

	List<ProductDto> 	listviewCross(ProductDto productDto);
	
	int 				totalProduct3(); // bucket 갯수
	
	List<ProductDto> 	listviewBucket(ProductDto productDto);
	
	int 				totalProduct4(); // tote 갯수

	List<ProductDto> 	listviewTote(ProductDto productDto);
}
