package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor //replyCnt, list 생성자의 파라미터로 처리한다.
@Getter
public class ReplyPageDTO {
	
	private int replyCnt;
	private List<ReplyVO> list;
}
