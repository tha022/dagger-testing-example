package com.dagger.sample2;

import com.dagger.sample2.MyApplication;
import com.dagger.sample2.modules.TestModuleForThreads;

import java.util.List;

/**
 * Test application context which initializes the Object Graph with objects from TestModule.
 *
 * Created by Thomas on 5/22/14.
 */
public class TestApplication extends MyApplication {

    int latchCounter;

    public TestApplication(int latchCounter) {
        this.latchCounter = latchCounter;
        onCreate();
    }

    @Override
    protected List<Object> getModules() {
        List<Object> list = super.getModules();
        list.add(new TestModuleForThreads(latchCounter));
        return list;
    }
}