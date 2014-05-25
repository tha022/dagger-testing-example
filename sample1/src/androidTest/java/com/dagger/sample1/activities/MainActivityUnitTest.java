package com.dagger.sample1.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.TextView;

import com.dagger.sample1.MyApplication;
import com.dagger.sample1.R;
import com.dagger.sample1.utils.MyStringUtils;

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
    MyStringUtils myStringUtils;

    MainActivity activity;
    Intent intent;

    public MainActivityUnitTest() {
        super(MainActivity.class);
    }

    @SuppressLint("SdCardPath")
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", "/data/data/com.dagger.sample1/cache");

        // create test application context which we can use for create a Dagger Graph with our test module.
        MyApplication application = new TestApplication();
        application.inject(this); // inject the dependencies we need to this class
        setApplication(application); // use our custom test application context

        this.intent = new Intent();
    }

    /**
     * Test success
     */
    public void testOnClick()  {

        String testingStr = "olala";
        when(myStringUtils.helloWorld()).thenReturn(testingStr);

        this.activity = startActivity(intent, null, null);


        // the test
        View view = activity.findViewById(R.id.button);
        activity.onClick(view);

        // verify the mock was invoked
        verify(myStringUtils, times(1)).helloWorld();

        // assert view got updated correctly
        TextView msgView = (TextView) activity.findViewById(R.id.textView);
        assertEquals(testingStr, msgView.getText());
    }


}
