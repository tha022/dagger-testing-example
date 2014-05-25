package com.dagger.sample2.modules;

import com.dagger.sample2.activities.MainActivityUnitTest;
import com.dagger.sample2.http.MyHttpClient;
import com.dagger.sample2.job.IJobListener;

import java.util.concurrent.CountDownLatch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Thomas on 5/21/14.
 */
@Module(
        overrides = true,
        includes = { ApplicationModule.class },
        injects = MainActivityUnitTest.class
)
public class TestModuleForThreads {

    final CountDownLatch latch;
    int latchCounter;


    public TestModuleForThreads(int latchCounter) {
        this.latchCounter = latchCounter;
        this.latch = new CountDownLatch(latchCounter);
    }

    @Provides @Singleton
    public MyHttpClient provideMyHttpClient() {
        return mock(MyHttpClient.class);
    }

    @Provides @Singleton
    public IJobListener provideJobListener() {

        return new IJobListener() {
            @Override
            public void executionDone() {
                latch.countDown();
            }
        };
    }

    @Provides @Singleton
    public CountDownLatch provideCountDownLatch() {
        return latch;
    }
}
