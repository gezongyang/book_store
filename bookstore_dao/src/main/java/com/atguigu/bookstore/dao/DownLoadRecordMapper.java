package com.atguigu.bookstore.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.atguigu.bookstore.beans.DownloadRecord;

@Repository
public interface DownLoadRecordMapper {

	int insertDownLoadRecord(DownloadRecord DownloadRecord);
	
	DownloadRecord selectDownLoadRecordOfFailByParams(@Param("fileName") String fileName);
}
