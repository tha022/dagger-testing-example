package com.dagger.sample1.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dagger.sample1.R;
import com.dagger.sample1.MyApplication;
import com.dagger.sample1.utils.MyStringUtils;

import javax.inject.Inject;


public class MainActivity extends Activity implements View.OnClickListener {

    @Inject
    MyStringUtils myStringUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set dependencies to this activity
        ((MyApplication)getApplication()).inject(this);

        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button) {

            MyStringUtils myStringUtils1 = new MyStringUtils(this.getApplication());
            MyStringUtils myStringUtils2 = MyStringUtils.getInstance(this);

            String str = myStringUtils.helloWorld();
            TextView msgView = (TextView) findViewById(R.id.textView);
            msgView.setText(str);
        }
    }
}
