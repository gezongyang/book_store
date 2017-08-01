package com.atguigu.bookstore.exception;

public class UserOperationForbiddenException extends RuntimeException {

static final long serialVersionUID = -703489719074576691L;
	
    public UserOperationForbiddenException(String message) {
    	super(message);
    }
}
