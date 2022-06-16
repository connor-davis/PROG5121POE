package st10068305.api;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReportManager {
    private TaskManager taskManager;

    public ReportManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public String displayByStatusDoneMessage() {
        ArrayList<String> tasksDone = new ArrayList<>();
        int currentIndex = 0;

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            if (Objects.equals(task.getTaskStatus(), "Done")) {
                currentIndex++;
                tasksDone.add(
                        currentIndex + ". " +
                                task.getDeveloperDetails() + ", " +
                                task.getTaskName() + ", " +
                                task.getTaskDuration() + " hours");
            }
        }

        return String.join("\n", tasksDone);
    }

    public String displayDeveloperAndDurationOfLongestDuration() {
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

        return greatestDuration.getDeveloperDetails() + ", " + greatestDuration.getTaskDuration();
    }

    public String searchByTaskName(String taskName) {
        Task taskFound = null;

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            if (Objects.equals(task.getTaskName(), taskName)) taskFound = task;
        }

        if (taskFound != null)
            return taskFound.getDeveloperDetails() + ", " + taskFound.getTaskName() + ", " + taskFound.getTaskStatus();
        return "Task not found";
    }

    public String searchByDeveloper(String developer) {
        ArrayList<String> tasksByDeveloper = new ArrayList<>();
        int currentIndex = 0;

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            if (task.getDeveloperDetails().contains(developer)) {
                currentIndex++;

                tasksByDeveloper.add(
                        currentIndex + ". " +
                                task.getTaskName() + ", " +
                                task.getTaskStatus());
            }
        }

        return String.join("\n", tasksByDeveloper);
    }

    public String deleteByTaskName(String taskName, boolean debug) {
        Task taskFound = null;

        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            if (Objects.equals(task.getTaskName(), taskName))
                taskFound = task;
        }

        if (taskFound == null) {
            JOptionPane.showMessageDialog(null, "Entry \"" + taskName + "\" not found.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);

            return "Entry \"" + taskName + "\" not found.";
        } else {
            taskManager.removeTask(taskFound.getTaskId());

            if (!taskManager.getTasks().containsKey(taskFound.getTaskId())) {
                if (!debug)
                    JOptionPane.showMessageDialog(null, "Entry \"" + taskName + "\" successfully deleted.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);

                return "Entry \"" + taskName + "\" successfully deleted.";
            } else {
                if (!debug)
                    JOptionPane.showMessageDialog(null, "Entry \"" + taskName + "\" failed to delete.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);

                return "Entry \"" + taskName + "\" failed to delete.";
            }
        }
    }
}
