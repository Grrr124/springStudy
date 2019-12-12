package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;
//@Component 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시하는 어노테이션
//@Data lombok 기능
@Component
@Data 
public class Restaurant {
	
	//Setter는 자동으로 setChef()를 컴파일 시 생성합니다.
	//Setter에 사용된 onMethod 속성은 생성되는 sefChef()에 @Autowired 어노테이션을 추가하도록 합니다.
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
