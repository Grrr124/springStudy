package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	//create()의 파라미터는 @RequestBody를 이용해서 JSON 데이터를 ReplyVo 타입으로 변환하도록 지정
	@PostMapping(value ="/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE } )
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		
		log.info("ReplyVo : " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT: " + insertCount);
		
		return insertCount == 1 
				? new ResponseEntity<>("success", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//리스트
	//ReplyPageDTO 객체를 JSON으로 전송하게 되므로, 특정 게시물의 댓글 목록을 조회하면
	//replyCnt와 list라는 이름의 속성을 가지는 JSON 문자열이 전송
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE
							   																	  ,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList( //<List<ReplyVO>> 
			@PathVariable("page") int page
		   ,@PathVariable("bno") Long bno) {
		
		Criteria cri = new Criteria(page,10);
		
		log.info("get Reply List bno : " + bno);
		log.info("cri:" + cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
		
//		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
	}
	//조회
	@GetMapping(value = "/{rno}", produces = { MediaType.APPLICATION_XML_VALUE
																			,MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		
		log.info("get: " + rno);
		
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	//삭제
	@DeleteMapping(value = "/{rno}", produces = { MediaType.TEXT_PLAIN_VALUE	})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		log.info("remove : " + rno);
		
		return service.remove(rno) == 1
			? new ResponseEntity<>("success", HttpStatus.OK)
			:  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 수정은 PUT 방식이나 PATCH 방식으로 처리한다.
	//실제 수정되는 데이터는 JSON 포맷이므로 @RequestBody로 처리하는데 @RequestBody로 처리하는 데이터는
	//일반 파라미터나 @PathVariable 파라미터를 처리할 수 없기 때문에 직접 처리해 줘야 한다.
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }
													  ,value = "/{rno}"
													  ,consumes = "application/json"
													  ,produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno) {
		
		vo.setRno(rno);
		
		log.info("rno : " + rno);
		log.info("modify : " + vo);
		
		return service.modify(vo) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				:  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}