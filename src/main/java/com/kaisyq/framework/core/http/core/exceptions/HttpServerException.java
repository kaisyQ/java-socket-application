package com.kaisyq.framework.core.http.core.exceptions;

public final class HttpServerException extends RuntimeException {
    
    public HttpServerException(String message) {
        super(message);
    }

    public HttpServerException() {
        super("Http server error");
    }

}