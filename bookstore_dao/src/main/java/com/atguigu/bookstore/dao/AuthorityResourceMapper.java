package com.atguigu.bookstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.Resource;
@Repository
public interface AuthorityResourceMapper {

	List<Resource> selectResourceByRole(@Param("role") String role);
}
