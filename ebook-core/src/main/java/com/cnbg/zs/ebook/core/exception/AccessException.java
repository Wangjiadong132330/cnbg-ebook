package com.cnbg.zs.ebook.core.exception;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/5 23:10
* @Description
*/
public class AccessException extends RuntimeException{
    public AccessException() {
        super();
    }

    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Throwable cause) {
     super(message, cause);
    }

    public AccessException(Throwable cause) {
     super(cause);
    }

    protected AccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
