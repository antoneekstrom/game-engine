package core.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import core.Game;

/**
 * Handles the execution of threads in the {@link Game}.
 * <p>This can be used to execute threads that have to be performed simultaneously.
 */
public class ThreadHandler {

    /**
     * Is responsible for creating the threads that is used by the executor.
     */
    private ThreadFactory threadFactory;

    /**
     * The executor is used for executing threads which are submitted.
     */
    private ExecutorService executor;

    /**
     * Create a {@code ThreadHandler}.
     */
    public ThreadHandler() {
        init();
    }

    /**
     * Initialize the {@code ThreadHandler}.
     */
    private void init() {
        // default factory
        // pretty much just creates simple threads
        threadFactory = Executors.defaultThreadFactory();

        // cached thread pool gives better performance when creating many short-lived thread
        // it reuses threads but also creates new ones when needed
        // this type of threadpool seems like a safe bet for most things
        executor = Executors.newCachedThreadPool(threadFactory);
    }

    /**
     * Submit a task to be executed on a new thread.
     * 
     * @param task the task
     */
    public void submit(Runnable task) {
        executor.submit(task);
    }

}