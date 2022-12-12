package com.oracle.minimalism.jhController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	/* 메인화면 - 페이지연결 */
	@GetMapping(value = "/")
	public String mainStart() {
		log.info("main Start...");
		System.out.println("HomeController mainStart...");
		return "index";
	}
	
	/* 가게정보 - about페이지연결 */
	@GetMapping(value = "/about")
	public String storeInfoAbout() {
		log.info("storeInfoAbout Start...");
		return "/about";
	}
	
	/* 가게정보 - stockist페이지연결 */
	@GetMapping(value = "/stockist")
	public String storeInfoStockist() {
		log.info("storeInfoStockist Start...");
		return "/stockist";
	}
	
	/* 회원정보 - mypageForm페이지연결 */
	   @GetMapping(value = "/mypageForm")
	   public String userMypageForm() {
	      log.info("HomeController userMypageForm Start...");
	      return "/mypageForm";
	   }
}
