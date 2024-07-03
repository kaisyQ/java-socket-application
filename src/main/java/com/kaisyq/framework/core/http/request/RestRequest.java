package com.kaisyq.framework.core.http.request;

public class RestRequest {
    
    private String url;

    public RestRequest() {
    }

    public RestRequest(String url) {
        this.url = url;
    }


    public RestRequest setUrl(String url) {
        this.url = url;
        return this;
    }


}
