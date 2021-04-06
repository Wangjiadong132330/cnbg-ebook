package com.cnbg.zs.ebook.core.exception;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/5 23:10
* @Description
*/
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}