package com.dagger.sample2;

import android.app.Application;

import com.dagger.sample2.modules.ApplicationModule;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Thomas on 5/21/14.
 */
public class MyApplication extends Application {

    private ObjectGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mGraph = ObjectGraph.create(getModules().toArray());
    }

    public void inject(Object o){
        mGraph.inject(o);
    }

    public ObjectGraph getObjectGraph() {
        return mGraph;
    }

    protected List<Object> getModules() {
        List<Object> result = new ArrayList<Object>();
        result.add(new ApplicationModule(this));
        return result;
    }
}
