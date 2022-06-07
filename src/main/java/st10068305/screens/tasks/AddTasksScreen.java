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
            taskManager.setMaximumTasks(Integer.parseInt(JOptionPane.showInputDialog(null, "How many tasks would you like to add?", "EasyKanban", JOptionPane.QUESTION_MESSAGE)));
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

        command = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban\nYou can add " + taskManager.getTasksLimit() + " tasks.", "Welcome", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, commands, commands[0]);

        switch (command) {
            case 0:
                AddTaskScreen addTaskScreen = new AddTaskScreen();


                if (taskManager.getTasksLimit() > 0) {
                    addTaskScreen.display();
                } else {
                    JOptionPane.showMessageDialog(null, "You have reached the maximum number of tasks for the session.", "EasyKanban", JOptionPane.WARNING_MESSAGE);
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
