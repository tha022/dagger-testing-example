package com.dagger.sample2.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.dagger.sample2.job.IJobListener;
import com.dagger.sample2.models.MyModel;
import com.dagger.sample2.exceptions.MyHttpException;
import com.dagger.sample2.http.MyHttpClient;
import com.dagger.sample2.listeners.CallbackResponseListener;

import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Thomas on 5/21/14.
 */
public class ExecuteRequestTask extends AsyncTask<Void, Void, MyModel> {

    // dependencies
    private WeakReference<CallbackResponseListener> mListener;
    private MyHttpClient myHttpClient;

    // internal state
    private IOException ioException;
    private MyHttpException httpException;
    private IJobListener jobListener;


    public ExecuteRequestTask(MyHttpClient myHttpClient, CallbackResponseListener mListener, IJobListener jobListener) {
        this.myHttpClient = myHttpClient;
        this.mListener = new WeakReference<CallbackResponseListener>(mListener);
        this.jobListener = jobListener;
    }

    @Override
    protected void onPreExecute() {
        CallbackResponseListener mListener = this.mListener.get();
        if(mListener != null) {
            mListener.reportProgress("Working hard...");
        }
    }

    @Override
    protected MyModel doInBackground(Void... params) {

        HttpGet get = new HttpGet("http://ci.bipper.com/bsafe-server/speakers/corner");
       try {

            MyModel model = myHttpClient.executeRequest(get);
            Log.d("ExecuteRequestTask", "should onPostExecute");

           return model;

        } catch (IOException e) {
            this.ioException = e;
            return null;
        } catch (MyHttpException e) {
           this.httpException = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(MyModel model) {
        Log.d("ExecuteRequestTask", "welcome, model="+model);
        CallbackResponseListener mListener = this.mListener.get();
        if(mListener == null) {
            Log.d("ExecuteRequestTask", "mListener="+mListener);
            // activity garbage collected
            return;
        }

        if(model != null) {
            mListener.setResponse(model);
        }
        else {
            if(ioException != null) {
                mListener.handleIOException(ioException);
            }
            else if(httpException != null) {
                // ioException
                mListener.handleHttpException(httpException);
            }
        }

        jobListener.executionDone();
    }
}