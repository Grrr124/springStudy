package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor 비어 있는 생성자를 만들기 위함

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleVO {
	
	private Integer mno;
	private String firstName;
	private String lastName;
}
