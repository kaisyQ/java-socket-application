package com.kaisyq.framework.core.http.response;

import java.util.Map;
import java.util.Map.Entry;

public abstract class BaseResonse {

    protected int status;
    protected Map<String, String> headers;


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public void addHeaders(Map<String, String> headers) {
        
        if (this.headers.isEmpty()) {
            this.headers = headers;
            return;
        }

        for (Entry<String, String> entry : this.headers.entrySet()) {

            String value = this.headers.get(entry.getValue());;

            if (headers.get(entry.getKey()) != null) {
                value = headers.get(entry.getKey());
            }

            this.headers.put(entry.getKey(), value);
        }
    }

}
