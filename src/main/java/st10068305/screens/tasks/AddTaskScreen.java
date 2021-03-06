package st10068305.screens.tasks;

import st10068305.Main;
import st10068305.api.Task;
import st10068305.api.TaskManager;
import st10068305.screens.Screen;

import javax.swing.*;

public class AddTaskScreen extends Screen {
    private final Main main = Main.getInstance();
    private final TaskManager taskManager = main.getTaskManager();

    @Override
    public void display() {
        message();
        prompt();
    }

    @Override
    public void message() {

    }

    @Override
    public void prompt() {
        String taskName = promptTaskName();
        String taskDescription = promptTaskDescription();
        String developerDetails = promptDeveloperDetails();
        int taskDuration = promptTaskDuration();
        String taskStatus = promptTaskStatus();

        Task task = new Task(
                taskName,
                taskManager.generateTaskNumber(),
                taskDescription,
                developerDetails,
                taskDuration,
                taskStatus);

        taskManager.addTask(task);

        printTaskDetails(task);

        AddTasksScreen addTasksScreen = new AddTasksScreen();
        addTasksScreen.getCommand();
    }

    private String promptTaskName() {
        String taskName = JOptionPane.showInputDialog(
                null,
                "Please enter the task name." +
                        "\n\nMust be at least 2 words long.",
                "EasyKanban",
                JOptionPane.INFORMATION_MESSAGE);

        if (taskName.split(" ").length < 2) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a task name of two words at least.",
                    "EasyKanban",
                    JOptionPane.INFORMATION_MESSAGE);

            taskName = promptTaskName();
        }

        return taskName;
    }

    private String promptTaskDescription() {
        String taskDescription = JOptionPane.showInputDialog(
                null,
                "Please enter the task description.",
                "EasyKanban",
                JOptionPane.INFORMATION_MESSAGE);

        if (!Task.checkTaskDescription(taskDescription)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter a task description of less than 50 characters.",
                    "EasyKanban",
                    JOptionPane.INFORMATION_MESSAGE);

            taskDescription = promptTaskDescription();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Task successfully captured.",
                    "EasyKanban",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return taskDescription;
    }

    private String promptDeveloperDetails() {
        String developerDetails = "";

        developerDetails += JOptionPane.showInputDialog(
                null,
                "Please enter the developers first name.",
                "EasyKanban",
                JOptionPane.INFORMATION_MESSAGE);
        developerDetails += " " + JOptionPane.showInputDialog(
                null,
                "Please enter the developers last name.",
                "EasyKanban",
                JOptionPane.INFORMATION_MESSAGE);

        return developerDetails;
    }

    private int promptTaskDuration() {
        return Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "The estimated duration of the task in hours.",
                "EasyKanban",
                JOptionPane.INFORMATION_MESSAGE));
    }

    private String promptTaskStatus() {
        int command;
        String[] commands = new String[]{"To Do", "Doing", "Done"};

        command = JOptionPane.showOptionDialog(
                null,
                "Please choose a status for the task",
                "EasyKanBan",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                commands,
                commands[0]);

        switch (command) {
            case 0:
                return "To Do";
            case 1:
                return "Doing";
            case 2:
                return "Done";
            default:
                promptTaskStatus();

                break;
        }

        return null;
    }

    private void printTaskDetails(Task task) {
        String taskDetails = "";

        taskDetails += "Task Status: \t" + task.getTaskStatus();
        taskDetails += "\nDeveloper Details: \t" + task.getDeveloperDetails();
        taskDetails += "\nTask Number: \t" + task.getTaskNumber();
        taskDetails += "\nTask Name: \t" + task.getTaskName();
        taskDetails += "\nTask Description: \t" + task.getTaskDescription();
        taskDetails += "\nTask ID: \t" + task.getTaskId();
        taskDetails += "\nTask Duration: \t" + task.getTaskDuration() + " hours";

        JOptionPane.showMessageDialog(
                null,
                taskDetails,
                "EasyKanban",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void getCommand() {

    }
}
