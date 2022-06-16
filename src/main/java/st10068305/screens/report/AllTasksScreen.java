package st10068305.screens.report;

import st10068305.Main;
import st10068305.api.Task;
import st10068305.api.TaskManager;
import st10068305.screens.Screen;

import javax.swing.*;
import java.util.ArrayList;

public class AllTasksScreen extends Screen {
    private final Main main = Main.getInstance();
    private final TaskManager taskManager = main.getTaskManager();
    private int currentPage = 0;
    private ArrayList<Task> tasks = new ArrayList<>();


    @Override
    public void display() {
        for (String taskId : taskManager.getTasks().keySet()) {
            Task task = taskManager.getTasks().get(taskId);

            tasks.add(task);
        }

        getCommand();
    }

    @Override
    public void message() {

    }

    @Override
    public void prompt() {

    }

    @Override
    public void getCommand() {
        int command;
        Object[] commands = new Object[]{"Previous", "Next","Finish"};
        Task currentTask = tasks.get(currentPage);

        String message =
                "Task Id: " + currentTask.getTaskId() + "\n" +
                "Task Name: " + currentTask.getTaskName() + "\n" +
                "Task Description: " + currentTask.getTaskDescription() + "\n" +
                "Developer Details: " + currentTask.getDeveloperDetails() + "\n" +
                "Task Duration: " + currentTask.getTaskDuration() + " hours\n" +
                "Task Status: " + currentTask.getTaskStatus();

        command = JOptionPane.showOptionDialog(null, message, "EasyKanban", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, commands, commands[0]);

        switch (command) {
            case 0:
                if (currentPage == 0) getCommand();
                else {
                    currentPage--;
                    getCommand();
                }

                break;

            case 1:
                if (currentPage == tasks.size() - 1) getCommand();
                else {
                    currentPage++;
                    getCommand();
                }
                break;

            default:
                ReportScreen reportScreen = new ReportScreen();
                reportScreen.display();

                break;
        }
    }
}
