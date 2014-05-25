package com.dagger.sample2.modules;

import android.app.Application;
import android.content.Context;

import com.dagger.sample2.activities.MainActivity;
import com.dagger.sample2.annotations.ApplicationQualifier;
import com.dagger.sample2.job.IJobListener;
import com.dagger.sample2.utils.MyStringUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Thomas on 5/21/14.
 */
@Module(
        library = true,
        injects = {
                MyStringUtils.class, MainActivity.class
        }
)
public class ApplicationModule {

    private Application application;


    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides @ApplicationQualifier
    public Context provideApplicationContext() {
        return application;
    }

    @Provides @Singleton
    public MyStringUtils provideMyStringUtils(@ApplicationQualifier Context application) {
        return new MyStringUtils(application);
    }

    @Provides
    public IJobListener provideJobListener() {
        return new IJobListener() {
            @Override
            public void executionDone() {
                // dummy implementation
            }
        };
    }

}
