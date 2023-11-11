```java
package automation;

import config.AutomationStationConfig;
import java.util.HashMap;
import java.util.Map;

public class AutomationService {

    private final Map<String, TaskScheduler> taskSchedulers;
    private final AutomationStationConfig config;

    public AutomationService(AutomationStationConfig config) {
        this.config = config;
        this.taskSchedulers = new HashMap<>();
        initializeSchedulers();
    }

    private void initializeSchedulers() {
        // Initialize TaskSchedulers based on the configuration
        config.getTaskConfigurations().forEach((taskName, taskConfig) -> {
            TaskScheduler scheduler = new TaskScheduler(taskConfig);
            taskSchedulers.put(taskName, scheduler);
        });
    }

    public void scheduleTask(String taskName, Map<String, Object> parameters) {
        if (taskSchedulers.containsKey(taskName)) {
            TaskScheduler scheduler = taskSchedulers.get(taskName);
            scheduler.schedule(parameters);
        } else {
            throw new IllegalArgumentException("Task with name " + taskName + " is not configured.");
        }
    }

    public void executeAllScheduledTasks() {
        taskSchedulers.values().forEach(TaskScheduler::executePendingTasks);
    }

    public void cancelTask(String taskName) {
        if (taskSchedulers.containsKey(taskName)) {
            TaskScheduler scheduler = taskSchedulers.get(taskName);
            scheduler.cancelAllScheduledTasks();
        } else {
            throw new IllegalArgumentException("Task with name " + taskName + " is not configured.");
        }
    }

    public void updateTaskConfiguration(String taskName, Map<String, Object> newConfig) {
        if (taskSchedulers.containsKey(taskName)) {
            TaskScheduler scheduler = taskSchedulers.get(taskName);
            scheduler.updateConfiguration(newConfig);
        } else {
            throw new IllegalArgumentException("Task with name " + taskName + " is not configured.");
        }
    }
}
```