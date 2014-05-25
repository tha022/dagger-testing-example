package com.dagger.sample1.modules;

import android.content.Context;

import com.dagger.sample1.activities.MainActivity;
import com.dagger.sample1.utils.MyStringUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Thomas on 5/24/14.
 */
@Module(
        library = true,
        injects = {
                MyStringUtils.class, MainActivity.class
        }
)
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides @Singleton
    public MyStringUtils provideMyStringUtils(Context context) {
        return new MyStringUtils(context);
    }

}
