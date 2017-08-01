package com.atguigu.bookstore.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMapper {

	void createTable(@Param("tableName") String tableName);
}
