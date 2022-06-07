package st10068305.screens;

import st10068305.Main;
import st10068305.api.UserManager;
import st10068305.screens.tasks.AddTasksScreen;
import st10068305.util.Messages;
import st10068305.util.Table;

import javax.swing.*;
import java.util.ArrayList;

import static st10068305.util.Commands.*;

public class HomeScreen extends Screen {
    private final Main main = Main.getInstance();
    private final UserManager userManager = main.getUserManager();

    @Override
    public void display() {
        message();
        prompt();
    }

    @Override
    public void message() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);

        String welcomeMessage = Messages.LOGGED_IN_SUCCESSFULLY;

        welcomeMessage = welcomeMessage.replace("<user first name>", userManager.getCurrentUser().getFirstName());
        welcomeMessage = welcomeMessage.replace("<user last name>", userManager.getCurrentUser().getLastName());

        JOptionPane.showMessageDialog(null, welcomeMessage, "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void prompt() {
        getCommand();
    }

    @Override
    public void getCommand() {
        int command;
        Object[] commands = new Object[]{ADD_TASKS_COMMAND, SHOW_REPORT_COMMAND, QUIT_COMMAND};

        command = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, commands, commands[0]);

        switch (command) {
            case 0:
                AddTasksScreen addTasksScreen = new AddTasksScreen();
                addTasksScreen.display();

                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Coming Soon", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);

                getCommand();

                break;
            default:
                break;
        }
    }

}
