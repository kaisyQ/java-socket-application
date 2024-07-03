package com.kaisyq.framework.core.http.server.exceptions;

public final class ServerRunningException extends Exception {
    
    public ServerRunningException() {
        super("Server is already running");
    }

}