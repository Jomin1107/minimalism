package com.oracle.minimalism.hjDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDetailDaoImpl implements ProductDetailDao {

	private final SqlSession session;
	
	/* ------------ viewAll - 제품 전체 불러오기 ------------ */
	@Override
	public int totalProduct() {
		int totProductCount = 0;
		System.out.println("ProductDaoImpl Start total...");
		
		// 문제가 생기면 확인할 수 있도록
		try {
			totProductCount = session.selectOne("productTotal");
			System.out.println("ProductDaoImpl totalEmp totProductCount->"+totProductCount);

		} catch (Exception e) {
			System.out.println("ProductDaoImpl totalEmp Exception->"+e.getMessage());
		}		
		return totProductCount;
	}

	@Override
	public List<ProductDto> listviewAll(ProductDto productDto) {
		List<ProductDto> viewAllList = null;
		System.out.println("ProductDaoImpl listEmp Start...");
		try {
			//									Map ID		parameter
			viewAllList = session.selectList("jhViewAllList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listEmp viewAllList.size()->"+viewAllList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listEmp e.getMessage()->"+e.getMessage());
		}		
		return viewAllList;
	}
	
	/* -------------------- 상세상품조회 -------------------- */
//	@Override
//	public ProductDto productOneDetail(int product_number) {
//		System.out.println("ProductDaoImpl productOneDetail Start...");
//		ProductDto productDetail = null;
//		try {
//			productDetail = session.selectOne("hjProductOne",product_number);
//			System.out.println("ProductDaoImpl productOneDetail Product_name-> " + productDetail.getProduct_name());
//		} catch (Exception e) {
//			System.out.println("ProductDaoImpl productOneDetail Exception Message-> " + e.getMessage());
//		}		
//		return productDetail;
//	}

	@Override
	public ProductDto productOneDetail(ProductDto productDto) {
		System.out.println("ProductDaoImpl productOneDetail Start...");
		ProductDto productDetail = null;
		try {
			productDetail = session.selectOne("hjProductOne", productDto);
			System.out.println("ProductDaoImpl productOneDetail Product_name-> " + productDetail.getProduct_name());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl productOneDetail Exception Message-> " + e.getMessage());
		}		
		return productDetail;
	}

}
