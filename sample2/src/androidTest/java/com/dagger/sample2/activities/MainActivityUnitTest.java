package com.dagger.sample2.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.TextView;

import com.bipper.dagger.app.R;
import com.dagger.sample2.MyApplication;
import com.dagger.sample2.models.MyModel;
import com.dagger.sample2.TestApplication;
import com.dagger.sample2.exceptions.MyHttpException;
import com.dagger.sample2.http.MyHttpClient;

import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Thomas on 5/21/14.
 */
public class MainActivityUnitTest extends ActivityUnitTestCase<MainActivity> {

    @Inject
    CountDownLatch latch;
    @Inject
    MyHttpClient myHttpClient;

    MainActivity activity;
    Intent intent;

    public MainActivityUnitTest() {
        super(MainActivity.class);
    }

    @SuppressLint("SdCardPath")
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", "/data/data/com.bipper.dagger.app/cache");

        // create test application context which we can use for create a Dagger Graph with our test module.
        MyApplication application = new TestApplication(1);
        application.inject(this); // inject the dependencies we need to this class
        setApplication(application); // use our custom test application context

        this.intent = new Intent();
    }

    /**
     * Test 200 success, model returned
     */
    public void testOnClick_200() throws InterruptedException, IOException, MyHttpException {

        this.activity = startActivity(intent, null, null);

        // set up HTTP mock
        MyModel model = new MyModel(200, "Slava Ukraini");
        when(myHttpClient.executeRequest(any(HttpUriRequest.class))).thenReturn(model);


        // the test
        View view = activity.findViewById(R.id.button);
        activity.onClick(view);


        latch.await(50, TimeUnit.SECONDS); // await till the count down latch is zero before proceeding

        // verify the mock was invoked
        verify(myHttpClient, times(1)).executeRequest(any(HttpUriRequest.class));

        // assert view got updated correctly
        TextView msgView = (TextView) activity.findViewById(R.id.textView);
        assertEquals(model.getContent(), msgView.getText());
        assertEquals(200, model.getStatusCode());
    }

    /**
     * Test a HTTP failure, 400 Bad Request
     */
    public void testOnClick_400() throws InterruptedException, IOException, MyHttpException {

        this.activity = startActivity(intent, null, null);

        // set up HTTP mock
        MyHttpException myHttpException = new MyHttpException(400);
        when(myHttpClient.executeRequest(any(HttpUriRequest.class))).thenThrow(myHttpException);


        // the test
        View view = activity.findViewById(R.id.button);
        activity.onClick(view);


        latch.await(50, TimeUnit.SECONDS); // await till the count down latch is zero before proceeding

        // verify the mock was invoked
        verify(myHttpClient, times(1)).executeRequest(any(HttpUriRequest.class));

        // assert view got updated correctly
        TextView msgView = (TextView) activity.findViewById(R.id.textView);
        String expected = String.format("Ooops, we got a %s exception", myHttpException.getStatusCode());
        assertEquals(expected, msgView.getText());
    }

    /**
     * IOException, for example cause of Bad connection
     */
    public void testOnClick_ioException() throws InterruptedException, IOException, MyHttpException {

        this.activity = startActivity(intent, null, null);

        // set up HTTP mock
        IOException ioException = new IOException("Bad Internet, check your connection");
        when(myHttpClient.executeRequest(any(HttpUriRequest.class))).thenThrow(ioException);


        // the test
        View view = activity.findViewById(R.id.button);
        activity.onClick(view);


        latch.await(50, TimeUnit.SECONDS); // await till the count down latch is zero before proceeding

        // verify the mock was invoked
        verify(myHttpClient, times(1)).executeRequest(any(HttpUriRequest.class));

        // assert view got updated correctly
        TextView msgView = (TextView) activity.findViewById(R.id.textView);
        assertEquals(ioException.getMessage(), msgView.getText());
    }


}
