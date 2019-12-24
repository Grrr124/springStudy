package org.zerock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//Test for controller (WebApplicationContext 이용하기 위해서)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"
										,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	//MockMvc는 가짜 mvc라고 생각하면 됨.. 가짜로 URL과 파라미터등을 브라우저에서 사용하는 것처럼 만들어서
	//Controller 실행 가능
	private MockMvc mockMvc;
	
	
	//Before가 적용된 메서드는 모든 테스트 전에 매번 실행되는 메서드가 됨
	@org.junit.Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//get 방식의 호출후 getList()에서 반환된 결과를 이용해서 Model에 데이터 확인
	@Test
	public void testLsit() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
					 .andReturn()
					 .getModelAndView()
					 .getModelMap());
	}
	
	@Test
	public void testRegister() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
										.param("title", "테스트 새글 제목")
										.param("content", "테스트 새글 내용")
										.param("writer", "user00")
									).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	//특정 게시물을 죄할 때 반드시 bno라는 파라미터가 필요하므로 param()을 통해서 추가 실행
	@Test
	public void tetGet() throws Exception {
	
		log.info(mockMvc.perform(MockMvcRequestBuilders
				    .get("/board/get")
					.param("bno", "2"))
					.andReturn()
					.getModelAndView()
					.getModelMap());
	}
	
	@Test
	public void testModify() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
										.param("bno", "1")
										.param("title", "수정된 테스트 새글 제목")
										.param("content", "수정된 테스트 새글 내용")
										.param("writer", "user00"))
										.andReturn()
										.getModelAndView()
										.getViewName();
		
		log.info(resultPage);
	}
	
	//MockMvc를 이용해서 파라미터를 전달할 때에는 문자열로만 처리해야 함.
	@Test
	public void testRemove() throws Exception {
		//삭제전 데이터베이스에 게시물 번호 확인할 것
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
										.param("bno", "6"))
										.andReturn()
										.getModelAndView()
										.getViewName();
		
		log.info(resultPage);
	}
}
