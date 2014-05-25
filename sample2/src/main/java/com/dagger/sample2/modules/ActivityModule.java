package com.dagger.sample2.modules;

import android.app.Activity;
import android.content.Context;

import com.dagger.sample2.activities.MainActivity;
import com.dagger.sample2.activities.SimpleMainActivity;
import com.dagger.sample2.annotations.ActivityQualifier;

import dagger.Module;
import dagger.Provides;

@Module(
        library = true,
        includes = ApplicationModule.class,
        injects = {
                MainActivity.class, SimpleMainActivity.class
        }
)
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityQualifier
    public Context provideActivityContext() {
        return activity;
    }
}
