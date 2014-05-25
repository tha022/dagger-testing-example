package com.dagger.sample2.tasks;

import android.os.AsyncTask;

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

    public ExecuteRequestTask(MyHttpClient myHttpClient, CallbackResponseListener mListener) {
        this.myHttpClient = myHttpClient;
        this.mListener = new WeakReference<CallbackResponseListener>(mListener);
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

            return myHttpClient.executeRequest(get);

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
        CallbackResponseListener mListener = this.mListener.get();
        if(mListener == null) {
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
    }
}