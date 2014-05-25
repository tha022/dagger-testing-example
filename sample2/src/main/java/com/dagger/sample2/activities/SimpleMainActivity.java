package com.dagger.sample2.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bipper.dagger.app.R;
import com.dagger.sample2.MyApplication;
import com.dagger.sample2.modules.ActivityModule;
import com.dagger.sample2.utils.MyStringUtils;


import javax.inject.Inject;

import dagger.ObjectGraph;


public class SimpleMainActivity extends ActionBarActivity implements View.OnClickListener {

    @Inject
    MyStringUtils myStringUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set dependencies to this activity

        // expand the application graph with the activity-specific module(s)
        ObjectGraph appGraph = ((MyApplication) getApplication()).getObjectGraph();
        appGraph = appGraph.plus(new ActivityModule(this));
        appGraph.inject(this); // and injecting our dependencies

        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button) {
            String str = myStringUtils.helloWorld();
            TextView msgView = (TextView) findViewById(R.id.textView);
            msgView.setText(str);
        }
    }
}
