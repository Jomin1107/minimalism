package com.oracle.minimalism.jhService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.dto.ProductImg;
import com.oracle.minimalism.jhDAO.ProductDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	// ProductDao 연결
		private final ProductDao productDao;
		
		// 전체 상품 갯수
		@Override
		public int totalProduct() {
			System.out.println("ProductServiceImpl Start total...");
			int totalProductCnt = productDao.totalProduct();
			System.out.println("ProductServiceImpl totalProduct totalProductCnt->"+totalProductCnt);

			return totalProductCnt;
		}
		// viewAll 상품
		@Override
		public List<ProductDto> listviewAll(ProductDto productDto, int value) {
			List<ProductDto> viewAllList = null;
			System.out.println("ProductServiceImpl ListviewAll Start...");
			System.out.println("value =" +value);
			viewAllList = productDao.listviewAll(productDto, value);

			System.out.println("ProductServiceImpl ListviewAll viewAllList.size()->"+viewAllList.size());
			return viewAllList;
		}
		
		
		
		// 카테고리별 상품 갯수
		@Override
		public int totalProduct1(int category) {
			System.out.println("ProductServiceImpl Start total1...");
			int totalProduct1Cnt = productDao.totalProduct1(category); 
			System.out.println("ProductServiceImpl totalProduct1 totalProduct1Cnt->"+totalProduct1Cnt);

			return totalProduct1Cnt;
		}
		// 카테고리별 상품
		@Override
		public List<ProductDto> listviewCate(ProductDto productDto, int category, int value) {
			List<ProductDto> viewCateList = null;
			System.out.println("ProductServiceImpl listviewCate Start...");
			viewCateList = productDao.listviewCate(productDto, category, value);
			System.out.println("ProductServiceImpl listviewCate viewCateList.size()->"+viewCateList.size());
			
			return viewCateList;
		}
		

}
