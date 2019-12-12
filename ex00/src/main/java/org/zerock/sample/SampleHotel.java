package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//@RequiredArgsConstructor는 @NonNULL이나 final이 붙은 인스턴스 변수에 대한 생성자를 만들어서 특정 변수에 대해서만 생성자 작성가능
@Component
@ToString
@Getter
@AllArgsConstructor
public class SampleHotel {

	private Chef chef;
	
//	public SampleHotel(Chef chef) {
//		
//		this.chef = chef;
//		
//	}
}
