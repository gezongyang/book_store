package com.atguigu.bookstore.exception;

public class HasNoAuthorityException extends RuntimeException{
   
static final long serialVersionUID = -70348971907457661L;
	
    public HasNoAuthorityException(String message) {
    	super(message);
    }
}
