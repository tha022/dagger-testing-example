package com.dagger.sample2.exceptions;

/**
 * Created by Thomas on 5/22/14.
 */
public class MyHttpException extends Exception{

    private int statusCode;

    public MyHttpException(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
