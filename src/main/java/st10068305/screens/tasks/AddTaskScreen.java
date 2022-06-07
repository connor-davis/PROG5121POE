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
        int taskNumber = promptTaskNumber();
        String taskDescription = promptTaskDescription();
        String developerDetails = promptDeveloperDetails();
        int taskDuration = promptTaskDuration();
        String taskId = generateTaskId(taskName, taskNumber, developerDetails);

        Task task = new Task(taskName, taskNumber, taskDescription, developerDetails, taskDuration, taskId);

        taskManager.addTask(task);

        AddTasksScreen addTasksScreen = new AddTasksScreen();
        addTasksScreen.getCommand();
    }

    private String promptTaskName() {
        return JOptionPane.showInputDialog(null, "Please enter the task name", "EasyKanban", JOptionPane.QUESTION_MESSAGE);
    }

    private int promptTaskNumber() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the task number", "EasyKanban", JOptionPane.QUESTION_MESSAGE));
    }

    private String promptTaskDescription() {
        return JOptionPane.showInputDialog(null, "Please enter the task description", "EasyKanban", JOptionPane.QUESTION_MESSAGE);
    }

    private String promptDeveloperDetails() {
        return JOptionPane.showInputDialog(null, "Please enter the developers details", "EasyKanban", JOptionPane.QUESTION_MESSAGE);
    }

    private int promptTaskDuration() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "The estimated duration of the task in hours.", "EasyKanban", JOptionPane.QUESTION_MESSAGE));
    }

    private String generateTaskId(String taskName, int taskNumber, String developerDetails) {
        String taskId = "";

        taskId += taskName.substring(0, 1).toUpperCase() + ":";
        taskId += taskNumber + ":";
        taskId += developerDetails.substring(developerDetails.length() - 3);

        return taskId;
    }

    @Override
    public void getCommand() {

    }
}
