package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {
	
	//번호
	private int tno;
	//소유주
	private String owner;
	//등급
	private String grade;
	
	
}
