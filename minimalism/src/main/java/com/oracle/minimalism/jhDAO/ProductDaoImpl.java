package com.oracle.minimalism.jhDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.minimalism.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
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
	public List<ProductDto> listviewAll(ProductDto productDto) {
		List<ProductDto> viewAllList = null;
		System.out.println("ProductDaoImpl listviewAll Start...");
		try {
			//									Map ID		parameter
			viewAllList = session.selectList("jhViewAllList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewAll viewAllList.size()->"+viewAllList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewAll e.getMessage()->"+e.getMessage());
		}		
		return viewAllList;
	}
	// Shoulder 상품 갯수
	@Override
	public int totalProduct1() {
		int totProduct1Count = 0;
		System.out.println("ProductDaoImpl Start total1...");
		
		// 문제가 생기면 확인할 수 있도록
		try {
			totProduct1Count = session.selectOne("product1Total");
			System.out.println("ProductDaoImpl product1Total totProduct1Count->"+totProduct1Count);

		} catch (Exception e) {
			System.out.println("ProductDaoImpl product1Total Exception->"+e.getMessage());
		}		
		return totProduct1Count;
	}
	// Shoulder 상품
	@Override
	public List<ProductDto> listviewShoulder(ProductDto productDto) {
		List<ProductDto> viewShoulderList = null;
		System.out.println("ProductDaoImpl listviewShoulder Start...");
		try {
			//										Map ID			parameter
			viewShoulderList = session.selectList("jhViewShoulderList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewShoulder viewShoulderList.size()->"+viewShoulderList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewShoulder e.getMessage()->"+e.getMessage());
		}		
		return viewShoulderList;
	}
	// Cross 상품 갯수
		@Override
		public int totalProduct2() {
			int totProduct2Count = 0;
			System.out.println("ProductDaoImpl Start total2...");
			
			// 문제가 생기면 확인할 수 있도록
			try {
				totProduct2Count = session.selectOne("product2Total");
				System.out.println("ProductDaoImpl product2Total totProduct2Count->"+totProduct2Count);

			} catch (Exception e) {
				System.out.println("ProductDaoImpl product2Total Exception->"+e.getMessage());
			}		
			return totProduct2Count;
		}
	// Cross 상품
	@Override
	public List<ProductDto> listviewCross(ProductDto productDto) {
		List<ProductDto> viewCrossList = null;
		System.out.println("ProductDaoImpl listviewCross Start...");
		try {
			//										Map ID			parameter
			viewCrossList = session.selectList("jhViewCrossList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewCross viewShoulderList.size()->"+viewCrossList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewCross e.getMessage()->"+e.getMessage());
		}		
		return viewCrossList;
	}
	// Bucket 상품 갯수
	@Override
	public int totalProduct3() {
		int totProduct3Count = 0;
		System.out.println("ProductDaoImpl Start total3...");
		
		// 문제가 생기면 확인할 수 있도록
		try {
			totProduct3Count = session.selectOne("product3Total");
			System.out.println("ProductDaoImpl product3Total totProduct3Count->"+totProduct3Count);

		} catch (Exception e) {
			System.out.println("ProductDaoImpl product3Total Exception->"+e.getMessage());
		}		
		return totProduct3Count;
	}

	// Bucket 상품
	@Override
	public List<ProductDto> listviewBucket(ProductDto productDto) {
		List<ProductDto> viewBucketList = null;
		System.out.println("ProductDaoImpl listviewBucket Start...");
		try {
			//										Map ID		   parameter
			viewBucketList = session.selectList("jhViewBucketList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewBucket viewBucketList.size()->"+viewBucketList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewBucket e.getMessage()->"+e.getMessage());
		}		
		return viewBucketList;
	}
	// Tote 상품 갯수
	@Override
	public int totalProduct4() {
		int totProduct4Count = 0;
		System.out.println("ProductDaoImpl Start total4...");
		
		// 문제가 생기면 확인할 수 있도록
		try {
			totProduct4Count = session.selectOne("product4Total");
			System.out.println("ProductDaoImpl product4Total totProduct4Count->"+totProduct4Count);

		} catch (Exception e) {
			System.out.println("ProductDaoImpl product4Total Exception->"+e.getMessage());
		}		
		return totProduct4Count;
	}
	
	// Tote 상품
	@Override
	public List<ProductDto> listviewTote(ProductDto productDto) {
		List<ProductDto> viewToteList = null;
		System.out.println("ProductDaoImpl listviewBucket Start...");
		try {
			//										Map ID		   parameter
			viewToteList = session.selectList("jhViewToteList" , productDto); // 여러개면 selectList, 하나면 selectOne
			System.out.println("ProductDaoImpl listviewTote viewToteList.size()->"+viewToteList.size());
		} catch (Exception e) {
			System.out.println("ProductDaoImpl listviewTote e.getMessage()->"+e.getMessage());
		}		
		return viewToteList;
	}
	
	
}
