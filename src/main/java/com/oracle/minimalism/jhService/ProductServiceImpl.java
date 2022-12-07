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
	public List<ProductDto> listviewAll(ProductDto productDto) {
		List<ProductDto> viewAllList = null;
		System.out.println("ProductServiceImpl ListviewAll Start...");
		viewAllList = productDao.listviewAll(productDto);
		System.out.println("ProductServiceImpl ListviewAll viewAllList.size()->"+viewAllList.size());
		
		return viewAllList;
	}
	// Shoulder 상품 갯수
	@Override
	public int totalProduct1() {
		System.out.println("ProductServiceImpl Start total1...");
		int totalProduct1Cnt = productDao.totalProduct1(); 
		System.out.println("ProductServiceImpl totalProduct1 totalProduct1Cnt->"+totalProduct1Cnt);

		return totalProduct1Cnt;
	}
	// Shoulder 상품
	@Override
	public List<ProductDto> listviewShoulder(ProductDto productDto) {
		List<ProductDto> viewShoulderList = null;
		System.out.println("ProductServiceImpl ListviewShoulder Start...");
		viewShoulderList = productDao.listviewShoulder(productDto);
		System.out.println("ProductServiceImpl ListviewShoulder viewShoulderList.size()->"+viewShoulderList.size());
		
		return viewShoulderList;
	}
	// Cross 상품 갯수
	@Override
	public int totalProduct2() {
		System.out.println("ProductServiceImpl Start total2...");
		int totalProduct2Cnt = productDao.totalProduct2(); 
		System.out.println("ProductServiceImpl totalProduct1 totalProduct1Cnt->"+totalProduct2Cnt);

		return totalProduct2Cnt;
	}
	// Cross 상품
	@Override
	public List<ProductDto> listviewCross(ProductDto productDto) {
		List<ProductDto> viewCrossList = null;
		System.out.println("ProductServiceImpl listviewCross Start...");
		viewCrossList = productDao.listviewCross(productDto);
		System.out.println("ProductServiceImpl listviewCross viewCrossList.size()->"+viewCrossList.size());
		
		return viewCrossList;
	}
	// Bucket 상품 갯수
	@Override
	public int totalProduct3() {
		System.out.println("ProductServiceImpl Start total3...");
		int totalProduct3Cnt = productDao.totalProduct3(); 
		System.out.println("ProductServiceImpl totalProduct1 totalProduct1Cnt->"+totalProduct3Cnt);

		return totalProduct3Cnt;		
	}
	// Bucket 상품
	@Override
	public List<ProductDto> listviewBucket(ProductDto productDto) {
		List<ProductDto> viewBucketList = null;
		System.out.println("ProductServiceImpl ListviewBucket Start...");
		viewBucketList = productDao.listviewBucket(productDto);
		System.out.println("ProductServiceImpl ListviewBucket viewBucketList.size()->"+viewBucketList.size());
		
		return viewBucketList;
	}
	// Tote 상품 갯수
	@Override
	public int totalProduct4() {
		System.out.println("ProductServiceImpl Start total4...");
		int totalProduct4Cnt = productDao.totalProduct4(); 
		System.out.println("ProductServiceImpl totalProduct1 totalProduct1Cnt->"+totalProduct4Cnt);

		return totalProduct4Cnt;	
	}

	// Tote 상품
	@Override
	public List<ProductDto> listviewTote(ProductDto productDto) {
		List<ProductDto> viewToteList = null;
		System.out.println("ProductServiceImpl listviewTote Start...");
		viewToteList = productDao.listviewTote(productDto);
		System.out.println("ProductServiceImpl listviewTote viewToteList.size()->"+viewToteList.size());
		
		return viewToteList;
	}
		

}
