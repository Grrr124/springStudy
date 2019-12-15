package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	//어노테이션을 이용한 Mapper
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	//myBatis의 scan 기능을 이용한 xml Mapper
	public String getTime2();
}
