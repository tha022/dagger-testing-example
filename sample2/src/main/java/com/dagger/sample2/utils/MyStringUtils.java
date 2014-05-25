package com.dagger.sample2.utils;

import android.content.Context;

import com.bipper.dagger.app.R;

/**
 * Created by Thomas on 5/24/14.
 */
public class MyStringUtils {

    private Context context;

    public MyStringUtils(Context context) {
        this.context = context;
    }

    public String helloWorld() {
        return context.getString(R.string.hello_world);
    }
}
