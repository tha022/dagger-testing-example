package com.dagger.sample2.models;


/**
 * Created by Thomas on 5/22/14.
 */
public class MyModel {

    private String content;
    private int statusCode;


    public MyModel(int statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
