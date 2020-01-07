package org.zerock.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml"
										 ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class SampleControllerTests {
	
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;
	
	//MockMvc는 가짜 mvc라고 생각하면 됨.. 가짜로 URL과 파라미터등을 브라우저에서 사용하는 것처럼 만들어서
	//Controller 실행 가능
	private MockMvc mockMvc;
	
	//Before가 적용된 메서드는 모든 테스트 전에 매번 실행되는 메서드가 됨
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testConvert() throws Exception {
		
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");
		
		//Gson = java의 객체를 JSON 문자열로 변환하기 위해서 사용
		String jsonstr = new Gson().toJson(ticket);
		
		log.info(jsonstr);
		
		//contentType(MediaType.APPLICATION_JSON) 전달하는 데이터 타입은 JSON이다. 명시적 표현
		mockMvc.perform(post("/sample/ticket")
									  .contentType(MediaType.APPLICATION_JSON)
									  .content(jsonstr))
									  .andExpect(status().is(200));
	}
}
