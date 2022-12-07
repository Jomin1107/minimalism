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
	
		
		// 제품 viewAll - 제품 전체 불러오기
		@GetMapping(value = "/viewAll") // get, post mapping
		public String ListviewAll( ProductDto productDto, String currentPage , Model model) {
			System.out.println("ProductController ListviewAll Start...");
			// Product 전체 Count
			int totalProduct = productService.totalProduct();
			System.out.println("ProductController totalProduct=>"+totalProduct); 
			
			// Paging 작업  -> 모듈화를 시켜놓음으로써 어디서든 paging 처리를 할 수 있음
			ViewPaging page = new ViewPaging(totalProduct, currentPage);
			// Parameter emp --> Page만 추가 Setting
			productDto.setStart(page.getStart());  // 시작시 1
			productDto.setEnd(page.getEnd());  	// 종료시 6
			
			List<ProductDto> listviewAll = productService.listviewAll(productDto);
			System.out.println("ProductController viewAllList.size()->"+listviewAll.size());
			System.out.println(listviewAll);
			model.addAttribute("productTotal"	, totalProduct);
			model.addAttribute("listviewAll"	, listviewAll);
			model.addAttribute("page"			, page);
			
			return "/viewAll";
		}		
		// 제품 viewShoulder - 제품 전체 불러오기
		@GetMapping(value = "/viewShoulder") // get, post mapping
		public String ListviewShoulder( ProductDto productDto, String currentPage , Model model) {
			System.out.println("ProductController ListviewAll Start...");
			// Product 전체 Count
			int totalProduct1 = productService.totalProduct1();
			System.out.println("ProductController totalProduct=>"+totalProduct1); 
			
			// Paging 작업  -> 모듈화를 시켜놓음으로써 어디서든 paging 처리를 할 수 있음
			ViewPaging page = new ViewPaging(totalProduct1, currentPage);
			// Parameter emp --> Page만 추가 Setting
			productDto.setStart(page.getStart());  // 시작시 1
			productDto.setEnd(page.getEnd());  	// 종료시 6
			
			List<ProductDto> listviewShoulder = productService.listviewShoulder(productDto);
			System.out.println("ProductController viewShoulderList.size()->"+listviewShoulder.size());
			System.out.println(listviewShoulder);
			model.addAttribute("productTotal"	, totalProduct1);
			model.addAttribute("listviewShoulder"	, listviewShoulder);
			model.addAttribute("page"			, page);
			
			return "/viewShoulder";
		}
		// 제품 viewCross - 제품 전체 불러오기
		@GetMapping(value = "/viewCross") // get, post mapping
		public String ListviewCross( ProductDto productDto, String currentPage , Model model) {
			System.out.println("ProductController ListviewCross Start...");
			// Product 전체 Count
			int totalProduct2 = productService.totalProduct2();
			System.out.println("ProductController totalProduct=>"+totalProduct2); 
			
			// Paging 작업  -> 모듈화를 시켜놓음으로써 어디서든 paging 처리를 할 수 있음
			ViewPaging page = new ViewPaging(totalProduct2, currentPage);
			// Parameter emp --> Page만 추가 Setting
			productDto.setStart(page.getStart());  // 시작시 1
			productDto.setEnd(page.getEnd());  	// 종료시 6
			
			List<ProductDto> listviewCross = productService.listviewCross(productDto);
			System.out.println("ProductController listviewCross.size()->"+listviewCross.size());
			System.out.println(listviewCross);
			model.addAttribute("productTotal"	, totalProduct2);
			model.addAttribute("listviewCross"	, listviewCross);
			model.addAttribute("page"			, page);
			
			return "/viewCross";
		}		
		// 제품 viewBucket - 제품 전체 불러오기
		@GetMapping(value = "/viewBucket") // get, post mapping
		public String ListviewBucket( ProductDto productDto, String currentPage , Model model) {
			System.out.println("ProductController ListviewBucket Start...");
			// Product 전체 Count
			int totalProduct3 = productService.totalProduct3();
			System.out.println("ProductController totalProduct=>"+totalProduct3); 
			
			// Paging 작업  -> 모듈화를 시켜놓음으로써 어디서든 paging 처리를 할 수 있음
			ViewPaging page = new ViewPaging(totalProduct3, currentPage);
			// Parameter emp --> Page만 추가 Setting
			productDto.setStart(page.getStart());  // 시작시 1
			productDto.setEnd(page.getEnd());  	// 종료시 6
			
			List<ProductDto> listviewBucket = productService.listviewBucket(productDto);
			System.out.println("ProductController listviewBucket.size()->"+listviewBucket.size());
			System.out.println(listviewBucket);
			model.addAttribute("productTotal"	, totalProduct3);
			model.addAttribute("listviewBucket"	, listviewBucket);
			model.addAttribute("page"			, page);
			
			return "/viewBucket";
		}
		// 제품 viewTote - 제품 전체 불러오기
		@GetMapping(value = "/viewTote") // get, post mapping
		public String ListviewTote( ProductDto productDto, String currentPage , Model model) {
			System.out.println("ProductController ListviewBucket Start...");
			// Product 전체 Count
			int totalProduct4 = productService.totalProduct4();
			System.out.println("ProductController totalProduct=>"+totalProduct4); 
			
			// Paging 작업  -> 모듈화를 시켜놓음으로써 어디서든 paging 처리를 할 수 있음
			ViewPaging page = new ViewPaging(totalProduct4, currentPage);
			// Parameter emp --> Page만 추가 Setting
			productDto.setStart(page.getStart());  // 시작시 1
			productDto.setEnd(page.getEnd());  	// 종료시 6
			
			List<ProductDto> listviewTote = productService.listviewTote(productDto);
			System.out.println("ProductController listviewTote.size()->"+listviewTote.size());
			System.out.println(listviewTote);
			model.addAttribute("productTotal"	, totalProduct4);
			model.addAttribute("listviewTote"	, listviewTote);
			model.addAttribute("page"			, page);
			
			return "/viewTote";
		}
	

}
