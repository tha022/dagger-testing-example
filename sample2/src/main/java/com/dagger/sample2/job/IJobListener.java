package com.dagger.sample2.job;

/**
 * We use this listener to give callback to our tests that something is done.
 * @author thomas.vervik
 */
public interface IJobListener {

    /**
     * The given thread is finished executing.
     */
    void executionDone();
}
