package com.atguigu.bookstore.common;

public enum ExceptionEnum {


	USER_OPERATION_FORBIDDEN("请登录后再操作！"), 
	UserOperationForbiddenException("您还没有登陆！"),
	HasNoAuthorityException("没有权限");
	
	ExceptionEnum( String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }


	
}
