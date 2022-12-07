package com.oracle.minimalism.hjService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.hjDAO.ProductDetailDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
	
	private final ProductDetailDao productDao;

	/* ------------ viewAll - 제품 전체 불러오기 ------------ */
	
	@Override
	public int totalProduct() {
		System.out.println("ProductServiceImpl Start total...");
		int totalProductCnt = productDao.totalProduct(); // totalEmp호출
		System.out.println("ProductServiceImpl totalProduct totalProductCnt->"+totalProductCnt);

		return totalProductCnt;
	}

	@Override
	public List<ProductDto> listviewAll(ProductDto productDto) {
		List<ProductDto> viewAllList = null;
		System.out.println("ProductServiceImpl ListviewAll Start...");
		//viewAllListImg = productImg.
		viewAllList = productDao.listviewAll(productDto);
		System.out.println("ProductServiceImpl ListviewAll viewAllList.size()->"+viewAllList.size());
		
		return viewAllList;
	}

	/* -------------------- 상세상품조회 -------------------- */
//	@Override
//	public ProductDto productDetail(int product_number) {
//		System.out.println("ProductServiceImpl productDetail Start...");
//		ProductDto productDetail = null;
//		productDetail = productDao.productOneDetail(product_number);
//		System.out.println("ProductServiceImpl productDetail-> " + productDetail);
//		return productDetail;
//	}

	@Override
	public ProductDto productDetail(ProductDto productDto) {
		System.out.println("ProductServiceImpl productDetail Start...");
		ProductDto productDetail = null;
		productDetail = productDao.productOneDetail(productDto);
		System.out.println("ProductServiceImpl productDetail-> " + productDetail);
		return productDetail;
	}


}
