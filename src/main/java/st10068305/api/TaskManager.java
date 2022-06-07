package st10068305.api;

import java.util.HashMap;

public class TaskManager {
    private HashMap<String, Task> tasks = new HashMap<>();
    private int maximumTasks = 0;

    public TaskManager() {
    }

    public void setMaximumTasks(int maximumTasks) {
        this.maximumTasks = maximumTasks;
    }

    public int getMaximumTasks() {
        return maximumTasks;
    }

    public int getTasksLimit() {
        return maximumTasks - tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.put(task.getTaskId(), task);
    }

    public HashMap<String, Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(String taskId) {
        return this.tasks.get(taskId);
    }

    public void setTasks(HashMap<String, Task> tasks) {
        this.tasks = tasks;
    }
}
