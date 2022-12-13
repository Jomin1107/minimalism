package com.oracle.minimalism.jhController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.jhService.ProductService;
import com.oracle.minimalism.jhService.ViewPaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	
	// 제품 전체 불러오기
	@GetMapping(value = "/viewAll") // get, post mapping
	public String ListviewAll( ProductDto productDto, String currentPage ,int value,int category, Model model) {
		
		// 0 전체 1 숄더  2 크로스 3 버킷 4 토드
		int totalProduct = 0;
		if(category == 0)
		{
			totalProduct = productService.totalProduct();
		}
		else {
			totalProduct = productService.totalProduct1(category);
		}
		
		System.out.println("ProductController ListviewAll Start...");
		// Product 전체 Count
		// int totalProduct = productService.totalProduct();
		System.out.println("ProductController totalProduct=>"+totalProduct); 
		
		// Paging 작업  -> 모듈화를 시켜놓음으로써 어디서든 paging 처리를 할 수 있음
		ViewPaging page = new ViewPaging(totalProduct, currentPage);
		// Parameter emp --> Page만 추가 Setting
		productDto.setStart(page.getStart());  // 시작시 1
		productDto.setEnd(page.getEnd());  	// 종료시 6
		List<ProductDto> listviewAll = null;
		
		System.out.println("category num ="+category);
		if(category == 0)
		{
			listviewAll = productService.listviewAll(productDto, value);
		}else {
			productDto.setCategory_code(category * 1000);
			productDto.setProduct_price((category*1000)+ 1000);
			listviewAll = productService.listviewCate(productDto, category, value);
		}
	    // List<ProductDto> listviewAll = productService.listviewAll(productDto, value);
		System.out.println("ProductController viewAllList.size()->"+listviewAll.size());
		System.out.println(listviewAll);
		String valueck = Integer.toString(value);
		String categoryck = Integer.toString(category);
		model.addAttribute("productTotal"	, totalProduct);
		model.addAttribute("listviewAll"	, listviewAll);
		model.addAttribute("page"			, page);
		model.addAttribute("value", valueck);
		model.addAttribute("category", categoryck);
		return "/viewAll";
	}
	

}
