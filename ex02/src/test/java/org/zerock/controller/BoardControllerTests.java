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
}
