package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//@RunWith 현재 테스트 코드가 스프링을 실행하는 역할을 할 것을 표시
//@ContextConfiguration 지정된 클래스나 문자열을 이용해서 필요한 객체를 스프링 내에 객체로 등록 classpath:, file: 이용
//*******Log4j******* 
//Lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성. 별도의 Logger객체 없이도 Log4j 라이브러리 설정 있으면 사용가능
//Spring Legacy Project로 생성하는 경우 기본적으로 Log4j 설정이 완료된 상태이기 때문에 별도 처리 없이 사용가능
//로그에 대한 설정은 src/main/resources와 test/resources에 별도로 존재

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	
	//@Autowired 해당 인스턴스 변수가 스프링으로부터 자동으로 주입해 달라는 표시
	@Setter(onMethod_ = { @Autowired})
	private Restaurant restaurant;
	
	//@Test는 JUnit에서 테스트 대상을 표시하는 어노테이션. 해당 메서드를 선택하고 JUnit Test 기능을 실행
	@Test
	public void testExist() {
		
		//assertNotNull은 restaurant 변수가 null이 아니어야만 테스트가 성공한다는 것을 의미함
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-------------------------------------------");
		log.info(restaurant.getChef());
		
	}
}
