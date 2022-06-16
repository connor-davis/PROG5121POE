package st10068305.api;

import st10068305.Main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReportManager {
    private static final Main main = Main.getInstance();
    private static final TaskManager taskManager = main.getTaskManager();

    public static String displayByStatusDoneMessage() {
        ArrayList<String> tasksDone = new ArrayList<>();

        taskManager.getTasks().forEach((String taskId, Task task) -> {
            if (Objects.equals(task.getTaskStatus(), "Done"))
                tasksDone.add(
                        "1." +
                                task.getDeveloperDetails() + " - " +
                                task.getTaskName() + " - " +
                                task.getTaskDuration() + " hours");
        });

        return String.join("\n", tasksDone);
    }

    public static String displayDeveloperAndDurationOfLongestDuration() {
        ArrayList<Task> tasksList = new ArrayList<>();

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            tasksList.add(task);
        }

        tasksList.sort((Task a, Task b) -> {
            if (a.getTaskDuration() > b.getTaskDuration()) return -1;
            if (a.getTaskDuration() < b.getTaskDuration()) return 1;

            return 0;
        });

        System.out.println(tasksList);

        Task greatestDuration = tasksList.get(0);

        return greatestDuration.getDeveloperDetails() + " - " + greatestDuration.getTaskDuration() + " hours.";
    }

    public static String searchByTaskName(String taskName) {
        Task taskFound = null;

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            taskFound = task;
        }

        if (taskFound != null)
            return taskFound.getTaskName() + " - " + taskFound.getDeveloperDetails() + " - " + taskFound.getTaskStatus();
        return "Task not found";
    }

    public static String searchByDeveloper(String developer) {
        ArrayList<String> tasksByDeveloper = new ArrayList<>();

        taskManager.getTasks().forEach((String taskId, Task task) -> {
            if (task.getDeveloperDetails().contains(developer))
                tasksByDeveloper.add(
                        "1." +
                                task.getTaskName() + " - " +
                                task.getTaskStatus());
        });

        return String.join("\n", tasksByDeveloper);
    }

    public static void deleteByTaskName(String taskName) {
        Task taskFound = null;

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            if (Objects.equals(task.getTaskName(), taskName))
                taskFound = task;
        }

        if (taskFound == null)
            JOptionPane.showMessageDialog(null, "Task not found.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
        else {
            taskManager.removeTask(taskFound.getTaskId());

            if (!taskManager.getTasks().containsKey(taskFound.getTaskId()))
                JOptionPane.showMessageDialog(null, "Task deleted.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Unable to delete task.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
