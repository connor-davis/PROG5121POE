package st10068305.api;

import java.util.HashMap;

public class TaskManager {
    private HashMap<String, Task> tasks = new HashMap<>();
    private int maximumTasks = 0;
    private int tasksCount = 0;

    public void setMaximumTasks(int maximumTasks) {
        this.maximumTasks = maximumTasks;
    }

    public int getMaximumTasks() {
        return maximumTasks;
    }

    public int getTasksLimit() {
        return maximumTasks - tasks.size();
    }

    public int returnTotalHours() {
        int totalHours = 0;

        for (String id : tasks.keySet()) {
            Task task = tasks.get(id);

            totalHours += task.getTaskDuration();
        }

        return totalHours;
    }

    public void addTask(Task task) {
        this.tasks.put(task.getTaskId(), task);
        this.tasksCount++;
    }

    public HashMap<String, Task> getTasks() {
        return this.tasks;
    }

    public int generateTaskNumber() {
        return tasks.size();
    }

    public Task getTask(String taskId) {
        return this.tasks.get(taskId);
    }

    public void setTasks(HashMap<String, Task> tasks) {
        this.tasks = tasks;
    }
}