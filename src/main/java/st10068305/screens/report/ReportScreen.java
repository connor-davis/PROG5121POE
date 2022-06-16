package st10068305.screens.report;

import st10068305.Main;
import st10068305.api.ReportManager;
import st10068305.api.Task;
import st10068305.api.TaskManager;
import st10068305.screens.HomeScreen;
import st10068305.screens.Screen;

import javax.swing.*;

public class ReportScreen extends Screen {
    private final Main main = Main.getInstance();
    private final TaskManager taskManager = main.getTaskManager();
    private final ReportManager reportManager = new ReportManager(taskManager);

    @Override
    public void display() {
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
        try {
            String optionsList = "";

            optionsList += "1. Display by Status \"Done\".\n";
            optionsList += "2. Display by Longest Duration.\n";
            optionsList += "3. Search by Task Name.\n";
            optionsList += "4. Search by Developer Name.\n";
            optionsList += "5. Delete by Task Name.\n";
            optionsList += "6. Display All.\n";
            optionsList += "7. Finish.\n";

            int command = Integer.parseInt(JOptionPane.showInputDialog(null, optionsList, "EasyKanban", JOptionPane.INFORMATION_MESSAGE));

            switch (command) {
                case 1:
                    JOptionPane.showMessageDialog(null, reportManager.displayByStatusDoneMessage(), "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
                    display();

                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, reportManager.displayDeveloperAndDurationOfLongestDuration(), "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
                    display();

                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, reportManager.searchByTaskName(JOptionPane.showInputDialog(
                            null,
                            "Task Name?",
                            "EasyKanban",
                            JOptionPane.INFORMATION_MESSAGE)), "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
                    display();

                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, reportManager.searchByDeveloper(JOptionPane.showInputDialog(
                            null,
                            "First Name or Last Name of Developer?",
                            "EasyKanban",
                            JOptionPane.INFORMATION_MESSAGE)), "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
                    display();

                    break;

                case 5:
                    String taskName = JOptionPane.showInputDialog(
                            null,
                            "Task Name?",
                            "EasyKanban",
                            JOptionPane.INFORMATION_MESSAGE);
                    reportManager.deleteByTaskName(taskName, false);
                    display();

                    break;

                case 6:
                    AllTasksScreen allTasksScreen = new AllTasksScreen();
                    allTasksScreen.display();

                    break;

                case 7:
                    HomeScreen homeScreen = new HomeScreen();
                    homeScreen.getCommand();

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Please enter a number from 1-7.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);
                    getCommand();

                    break;
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Please enter a number.", "EasyKanban", JOptionPane.INFORMATION_MESSAGE);

            getCommand();
        }
    }
}
