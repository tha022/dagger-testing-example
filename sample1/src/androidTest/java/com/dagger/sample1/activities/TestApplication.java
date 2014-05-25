package com.dagger.sample1.activities;

import com.dagger.sample1.MyApplication;

import java.util.List;

/**
 * Test application context which initializes the Object Graph with objects from TestModule.
 *
 * Created by Thomas on 5/22/14.
 */
public class TestApplication extends MyApplication {

    public TestApplication() {
        onCreate();
    }

    @Override
    protected List<Object> getModules() {
        List<Object> list = super.getModules();
        list.add(new TestModule());

        return list;
    }
}