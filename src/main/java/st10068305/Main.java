package st10068305;

import st10068305.api.TaskManager;
import st10068305.api.TaskSLAPI;
import st10068305.api.UserManager;
import st10068305.api.UserSLAPI;
import st10068305.screens.WelcomeScreen;

import java.io.IOException;

public class Main {
    private static Main instance;
    private static UserManager userManager;
    private static TaskManager taskManager;

    public static Main getInstance() {
        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public static void main(String[] args) {
        instance = new Main();
        userManager = new UserManager();
        taskManager = new TaskManager();

        try {
            // Here we attempt to load the users from the users.yml file.
            userManager.setUsers(UserSLAPI.loadUsers());
            // Here we attempt to load the tasks from the tasks.yml file.
            taskManager.setTasks(TaskSLAPI.loadTasks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WelcomeScreen welcomeScreen = new WelcomeScreen();

        welcomeScreen.display();

        try {
            // Here we attempt to save the users to the users.yml file.
            UserSLAPI.saveUsers(userManager.getUsers());
            // Here we attempt to save the tasks to the tasks.yml file.
            TaskSLAPI.saveTasks(taskManager.getTasks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}