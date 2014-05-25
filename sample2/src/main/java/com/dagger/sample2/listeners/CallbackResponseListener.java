package com.dagger.sample2.listeners;

import com.dagger.sample2.models.MyModel;
import com.dagger.sample2.exceptions.MyHttpException;

import java.io.IOException;

/**
 * Created by Thomas on 5/21/14.
 */
public interface CallbackResponseListener {

    void setResponse(MyModel model);

    void handleIOException(IOException e);

    void handleHttpException(MyHttpException e);

    void reportProgress(String str);
}
