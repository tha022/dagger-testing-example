package com.dagger.sample2.http;

import org.apache.http.HttpResponse;

/**
 * Created by Thomas on 5/21/14.
 */
public class ResultWrapper {

    private HttpResponse httpResponse;
    private String responseStr;

    public ResultWrapper(HttpResponse httpResponse, String responseStr) {
        this.httpResponse = httpResponse;
        this.responseStr = responseStr;
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public String getResponseStr() {
        return responseStr;
    }
}
