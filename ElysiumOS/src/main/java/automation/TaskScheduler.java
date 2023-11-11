```java
package automation;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * TaskScheduler is responsible for scheduling and executing tasks that can be
 * both digital and physical in nature, reflecting the intertwined nature of
 * Elysium OS's digital and tangible realms.
 */
public class TaskScheduler {

    private final ScheduledExecutorService scheduler;

    public TaskScheduler() {
        this.scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Schedules a task to be executed after a given delay.
     *
     * @param task  The task to execute.
     * @param delay The delay after which to execute the task.
     * @param unit  The time unit of the delay.
     */
    public void schedule(Runnable task, long delay, TimeUnit unit) {
        scheduler.schedule(task, delay, unit);
    }

    /**
     * Schedules a recurring task to be executed with a fixed delay between executions.
     *
     * @param task         The task to execute.
     * @param initialDelay The initial delay before executing the task for the first time.
     * @param delay        The delay between the termination of one execution and the commencement of the next.
     * @param unit         The time unit of the initial delay and between executions delay.
     */
    public void scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
        scheduler.scheduleWithFixedDelay(task, initialDelay, delay, unit);
    }

    /**
     * Schedules a recurring task to be executed with a fixed rate of execution.
     *
     * @param task         The task to execute.
     * @param initialDelay The initial delay before executing the task for the first time.
     * @param period       The period between successive executions.
     * @param unit         The time unit of the initial delay and period.
     */
    public void scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(task, initialDelay, period, unit);
    }

    /**
     * Executes a task asynchronously and provides a callback to handle the result.
     *
     * @param task     The task to execute, which returns a result.
     * @param callback The callback to handle the result of the task.
     * @param <T>      The type of the result returned by the task.
     */
    public <T> void executeAsync(Callable<T> task, Consumer<T> callback) {
        CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, scheduler).thenAccept(callback);
    }

    /**
     * Shuts down the scheduler, attempting to stop all actively executing tasks.
     */
    public void shutdown() {
        scheduler.shutdown();
    }

    /**
     * Shuts down the scheduler immediately, trying to halt all currently executing tasks.
     */
    public void shutdownNow() {
        scheduler.shutdownNow();
    }
}
```