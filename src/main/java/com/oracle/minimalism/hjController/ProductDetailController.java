package com.oracle.minimalism.hjController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oracle.minimalism.dto.ProductDto;
import com.oracle.minimalism.hjService.ProductDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductDetailController {
	
	private final ProductDetailService productService;
	
	/* 제품 상세 페이지 */
	@GetMapping(value = "/productDetail/{product_number}")
	public String productDetail(@PathVariable("product_number") int  product_number, 
								ProductDto productDto, Model model) {
		log.info("productDetail Start...");
		System.out.println("ProductController productDetail product_number->"+product_number);
		
		ProductDto productDetail = productService.productDetail(productDto);
		model.addAttribute("product",productDetail);
		
		return "/productDetail";
	}
	
	/* 제품 설명 */
//	@GetMapping(value = "/description/{product_number}")
//	public String productDescription(@PathVariable("product_number") int  product_number, 
//									ProductDto productDto, Model model) {
//		System.out.println("ProductController productDescription product_number-> " + product_number);
//		List<ProductDto> descripList = productService.descripList(product_number);
//		
//		model.addAttribute("productDesc",descripList);
//		return "redirect:productDetail";
//	}

}
