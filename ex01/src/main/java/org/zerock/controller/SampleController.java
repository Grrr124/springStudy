package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;

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
}