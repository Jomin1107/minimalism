package com.oracle.minimalism.jhDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
	// SqlSession 연결
	private final SqlSession session;
	
	// 전체상품 갯수
	@Override
	public int totalProduct() {
		int totProductCount = 0;
		System.out.println("ProductDaoImpl Start total...");
		
		// 문제가 생기면 확인할 수 있도록
		try {
			totProductCount = session.selectOne("productTotal");
			System.out.println("ProductDaoImpl productTotal totProductCount->"+totProductCount);

		} catch (Exception e) {
			System.out.println("ProductDaoImpl productTotal Exception->"+e.getMessage());
		}		
		return totProductCount;
	}
	// viewAll 상품
	@Override
	public List<ProductDto> listviewAll(ProductDto productDto,int value) {
		List<ProductDto> viewAllList = null;
		System.out.println("ProductDaoImpl listviewAll Start...");
		try {
			//									Map ID			parameter
			
			if(value == 2) // 낮은 가격
			{
				viewAllList = session.selectList("jhViewLowPrice" , productDto); // 여러개면 selectList, 하나면 selectOne
			}else if(value == 3) // 높은 가격
			{
				viewAllList = session.selectList("jhViewHighPrice" , productDto); // 여러개면 selectList, 하나면 selectOne
			}else 
			{
				viewAllList = session.selectList("jhViewAllList" , productDto); // 여러개면 selectList, 하나면 selectOne
			}
			
			// viewAllList = session.selectList("jhViewAllList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewAll viewAllList.size()->"+viewAllList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewAll e.getMessage()->"+e.getMessage());
		}		
		return viewAllList;
	}
	
	// 카테고리별 상품 갯수
	@Override
	public int totalProduct1(int category) {
		int totProduct1Count = 0;
		System.out.println("ProductDaoImpl Start total1...");
		
		// 문제가 생기면 확인할 수 있도록
		try {
			System.out.println(category);
			totProduct1Count = session.selectOne("product1Total", category);
			System.out.println("ProductDaoImpl product1Total totProduct1Count->"+totProduct1Count);

		} catch (Exception e) {
			System.out.println("ProductDaoImpl product1Total Exception->"+e.getMessage());
		}		
		return totProduct1Count;
	}
	
	
	// 카테고리별 상품
	@Override
	public List<ProductDto> listviewCate(ProductDto productDto, int category, int value) {
		List<ProductDto> viewCateList = null;
		System.out.println("ProductDaoImpl listviewCate Start...");
		try {			
			if(value == 2) // 낮은 가격
			{
//													Map ID			parameter
				viewCateList = session.selectList("jhCateLowPrice" , productDto); // 여러개면 selectList, 하나면 selectOne
			}else if(value == 3) // 높은 가격
			{
//													Map ID			parameter
				viewCateList = session.selectList("jhCateHighPrice" , productDto); // 여러개면 selectList, 하나면 selectOne
			}else 
			{
//													Map ID		parameter
				viewCateList = session.selectList("jhCateList" , productDto); // 여러개면 selectList, 하나면 selectOne
			}
			System.out.println(productDto);
			// viewShoulderList = session.selectList("jhViewShoulderList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewCate viewCateList.size()->"+viewCateList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewCate e.getMessage()->"+e.getMessage());
		}		
		return viewCateList;
	}
	
	
}
