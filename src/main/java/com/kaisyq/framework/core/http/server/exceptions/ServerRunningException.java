package com.kaisyq.framework.core.http.server.exceptions;

public final class ServerRunningException extends RuntimeException {
    
    public ServerRunningException() {
        super("Server is already running");
    }

}