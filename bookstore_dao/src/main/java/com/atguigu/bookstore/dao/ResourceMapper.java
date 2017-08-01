package com.atguigu.bookstore.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.Resource;

@Repository
public interface ResourceMapper {

	int selectMaxResCodeOfResPos(@Param("resPos") int resPos);
	
	boolean checkResExists(@Param("servletPath") String servletPath);
    
	Integer selectMaxPos();
    
	int insertSource(Resource res);

	Resource selectResourceByServletPath(@Param("servletPath") String servletPath);

}
