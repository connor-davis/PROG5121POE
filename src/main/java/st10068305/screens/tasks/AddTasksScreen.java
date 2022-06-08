package st10068305.screens.tasks;

import st10068305.Main;
import st10068305.api.TaskManager;
import st10068305.screens.HomeScreen;
import st10068305.screens.Screen;

import javax.swing.*;

import static st10068305.util.Commands.ADD_TASK_COMMAND;
import static st10068305.util.Commands.FINISH_COMMAND;

public class AddTasksScreen extends Screen {
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
        try {
            taskManager.setMaximumTasks(taskManager.getTasks().size() + Integer.parseInt(JOptionPane.showInputDialog(null, "How many tasks would you like to add?", "EasyKanban", JOptionPane.INFORMATION_MESSAGE)));
        } catch (Exception e) {
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.getCommand();
        }

        getCommand();
    }

    @Override
    public void getCommand() {
        int command;
        Object[] commands = new Object[]{ADD_TASK_COMMAND, FINISH_COMMAND};

        String title = "EasyKanban";
        String message = "Welcome to EasyKanban\nYou can add " + taskManager.getTasksLimit() + " tasks.";

        if (taskManager.getTasks().size() == taskManager.getMaximumTasks()) {
            message += "\n\n";

            int totalHours = taskManager.returnTotalHours();

            message += "Total Hours: " + totalHours;
        }

        command = JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, commands, commands[0]);

        switch (command) {
            case 0:
                AddTaskScreen addTaskScreen = new AddTaskScreen();


                if (taskManager.getTasksLimit() > 0) {
                    addTaskScreen.display();
                } else {
                    JOptionPane.showMessageDialog(null, "You have reached the maximum number of tasks for the session.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
                    getCommand();
                }

                break;
            case 1:
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.getCommand();

                break;
            case 3:
                break;
            default:
                prompt();

                break;
        }
    }
}
