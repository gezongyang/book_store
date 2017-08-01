package com.atguigu.bookstore.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityMapper {

	Set<Long> selectAuthorityByRole(@Param("role") String role);
}
