package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}
	
	//등록 작업이 끝난 후 다시 목록화면으로 이동하기 위해서 String을 리턴타입 지정, 
	//RedirectAttributes를 파라미터로 지정해서 추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해서 이용함
	//return시에는 redirect: 접두어를 사용하는데 스프링 mvc가 내부적으로 response.sendRedirect()를 처리해 주기 때문에 편리함
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	//@RequestParam 값의 명시적 처리..(파라미터이름과 변수이름을 기준으로 동작해서 생략해도 관계없음)
	//화면 쪽으로 해당 번호의 게시물을 전달해야 하므로 Model을 파라미터로 지정
	//@GetMapping @PostMapping에서는 URL을 배열로 처리할 수 있어서 하나의 메서드로 여러 URL 처리 가능
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	
	//수정 작업을 시작하는 화면의 경우, Get방식으로 접근하지만 실제 작업은 POST방식으로 동작함
	//service.modify()는 수정 여부를 boolean으로 처리함. 이를 이용해서 성공한 경우에 RedirectAttributes rttr에 추가함
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify: " + board);
		
		if(service.modify(board)) {
			rttr.addAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	
	//삭제후 페이지의 이동이 필요하므로 RedirectAttributes를 파라미터로 사용하고 redirect를 통해서 삭제 처리 후 목록 페이지로 이동
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove...." + bno);
		if (service.remove(bno)) {
			rttr.addAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
}
