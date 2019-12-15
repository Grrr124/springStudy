package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	//myBatis의 scan 기능을 이용한 xml Mapper
	public String getTime2();
}
