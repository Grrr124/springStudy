package org.zerock.controller;

import java.net.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;


//@RequestMapping("/sample/*") 다음과 같은 경로로 지정했다면 모든 URL들은 모두 SampleController에서 처리됩니다.
//ex) sample/aaa , /sample/bbb
@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		
		log.info("basic....................");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		
		log.info("basic get..........................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void baiscGet2() {
		
		log.info("basic get only get......................");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info("" + dto);
		
		return "ex01";
	}
	
	//@RequestParam("") 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다를때 유용하게 사용하는 기능. 예제는 관계없음
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name : " + name);
		log.info("age : " + age);
		
		return "ex02";
	}
	
	//동일한 이름의 파라미터가 여러개 전달되는 경우 ArrayList 활용
	//http://localhost:8090/sample/ex02List?ids=111&ids=222&ids=333&ids=444 입력. 톰캣 로그 확인
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
	}
	
	//배열처리
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		
		log.info("array ids: " + Arrays.toString(ids));
		
		return "ex02Array";
	}
	
	//http://localhost:8090/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	//객체를 리스트화
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		
		log.info("list dtos: " + list);
		
		return "ex02Bean";
	}
	
	//DataTimeFormat도 변환 가능
	 @InitBinder 
	 public void initBinder(WebDataBinder binder) { 
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); }
	 
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("todo : " + todo);
		return "ex03";
	}
	
	//int page만 명시하면 타입까지 전달 안돼.. 그래서 ModelAttribute를 통해서 타입 관계없이 model에 담아 전달
	//Modle타입과 더불어서 RedirectAttributes 타입이 있는데 model과 같지만 일회성으로 데이터를 전달하는 용도로 사용
	//rttr.addFlashAttribute("name", "AAA");
	//rttr.addFlashAttribute("age", "10);
	//return "redirect:/";
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		return "/sample/ex04";
	}
	
	//servlet-context.xml 설정이 맞물려 URL 경로를 View로 처리하기 때문에 생기는 에러페이지 발생
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05................");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.............");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("/ex07.................");
		
		// {"name": "홍길동"}
		String msg = "{\"name\": \"홍길동\"}";
		
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
}