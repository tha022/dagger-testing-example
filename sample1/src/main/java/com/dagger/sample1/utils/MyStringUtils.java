package com.dagger.sample1.utils;

import android.content.Context;

import com.dagger.sample1.R;

/**
 * Created by Thomas on 5/24/14.
 */
public class MyStringUtils {

    private Context context;

    private static MyStringUtils instance;
    public static MyStringUtils getInstance(Context context) {
        if(instance == null) {
            instance = new MyStringUtils(context);
        }
        return null;
    }

    public MyStringUtils(Context context) {
        this.context = context;
    }

    public String helloWorld() {
        return context.getString(R.string.hello_ciklum);
    }
}
