package st10068305.api;

import java.util.ArrayList;
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

    public String[] getDevelopers() {
        ArrayList<Task> tasksList = new ArrayList<>();

        for (Task task : tasks.values()) {
            tasksList.add(task);
        }

        tasksList.sort((Task a, Task b) -> {
            int taskNumberA = Integer.parseInt(a.getTaskId().split(":")[1]);
            int taskNumberB = Integer.parseInt(b.getTaskId().split(":")[1]);

            if (taskNumberA > taskNumberB) return 1;
            if (taskNumberA < taskNumberB) return -1;

            return 0;
        });

        String[] developers = new String[tasksList.size()];
        int currentIndex = 0;

        for (Task task : tasksList) {
            developers[currentIndex] = task.getDeveloperDetails();

            currentIndex++;
        }

        return developers;
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

    public void removeTask(String taskId) {
        tasks.remove(taskId);
    }
}
