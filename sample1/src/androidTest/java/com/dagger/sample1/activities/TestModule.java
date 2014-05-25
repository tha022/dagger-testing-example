package com.dagger.sample1.activities;

import com.dagger.sample1.utils.MyStringUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Thomas on 5/21/14.
 */
@Module(
        overrides = true,
        injects = {
                MainActivity.class,
                MainActivityUnitTest.class
        }
)
public class TestModule {

    @Provides @Singleton
    public MyStringUtils provideMyStringUtils() {
        return mock(MyStringUtils.class);
    }

}
