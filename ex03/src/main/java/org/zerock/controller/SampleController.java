package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

//@Controller는 문자열을 반환하는 경우에 JSP파일의 이름으로 처리하지만,
//RestController는 순수한 데이터가 된다.
@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	//produces 속성은 해당 메서드가 생산하는 MIME 타입을 의미. 문자열로 직접 지정도 되고 MediaType 클래스를 이용할 수도 있음
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	//안써도 상관없는데 명시적으로 하기 위해서 사용했다.
	@GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE
																					,MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		return new SampleVO(112, "스타", "골드");
	}
	
	@GetMapping(value = "/getSample2")
	public SampleVO getSampleVO() {
		return new SampleVO(113, "로켓", "라쿤");
	}
	
	//getList는 내부적으로 1~10미만 까지의 루프를 처리하면서 SampleVO 객체를 만들어서 List<SampleVO)로 만들어 낸다.
	//mapToObj 원시스트림을 객체 스트림으로 바꾼다. mapToObj( i -> "a" + i) = a1.. 식이다. 
	//1부터 9까지 예제는 1fist, 1last가 객체에 담긴다. collect(Collectors.toList)를 이용해 리스트로 리턴 받을 수 있다.
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last")).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		return map;
	}
	
	//ResponseEntity는 데이터와 함께 HTTP 헤더의 상태 메시지 등을 같이 전달하는 용도로 사용
	//http://localhost:8090/sample/check.json?height=150&weight=70
	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	//@PathVariable URL 상에 경로의 일부를 파라미터로 사용 가능
	//{}로 변수명 지정하고 지정된 이름의 변수값을 얻을 수 있다.
	//값을 얻을 때에는 int double 같은 기본 자료형을 사용할 수 없다.
	//http://localhost:8090/sample/product/bags/1234
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) {
		return new String[] { "category: " + cat, "productid: " + pid};
	}
	
	//@RequestBody가 말 그대로 요청(request)한 내용(body)을 처리하기 때문에 일반적인 파라미터 전달방식은 사용할 수 없어서
	//PostMapping 처리를 해주었다.
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("convert........ticket" + ticket);
		
		return ticket;
	}
}
