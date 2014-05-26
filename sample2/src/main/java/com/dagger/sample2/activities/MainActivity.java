package com.dagger.sample2.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dagger.sample2.R;
import com.dagger.sample2.MyApplication;
import com.dagger.sample2.models.MyModel;
import com.dagger.sample2.exceptions.MyHttpException;
import com.dagger.sample2.http.MyHttpClient;
import com.dagger.sample2.job.IJobListener;
import com.dagger.sample2.listeners.CallbackResponseListener;
import com.dagger.sample2.tasks.ExecuteRequestTask;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Thomas on 5/21/14.
 */
public class MainActivity extends Activity implements View.OnClickListener, CallbackResponseListener {

    @Inject
    MyHttpClient myHttpClient;
    @Inject
    IJobListener jobListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);

        // set dependencies to this activity
        ((MyApplication)getApplication()).inject(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button) {
            ExecuteRequestTask task = new ExecuteRequestTask(myHttpClient, this, jobListener);
            task.execute();
        }
    }


    @Override
    public void setResponse(MyModel model) {
        setMessage(model.getContent());
        //jobListener.executionDone();

    }

    @Override
    public void handleIOException(IOException e) {
        setMessage(e.getMessage());
       // jobListener.executionDone();
    }

    @Override
    public void handleHttpException(MyHttpException e) {
        setMessage(String.format("Ooops, we got a %s exception", e.getStatusCode()));
        //jobListener.executionDone();
    }

    @Override
    public void reportProgress(String str) {
        setMessage(str);
    }



    private void setMessage(String str) {
        TextView msgView = (TextView) findViewById(R.id.textView2);
        msgView.setText(str);
    }

}
