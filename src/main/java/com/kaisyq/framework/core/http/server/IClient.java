package com.kaisyq.framework.core.http.server;

import java.util.Map;

public interface IClient {
    public void write(String message);
    public void submit(short status, Map<String, String> headers);
}
