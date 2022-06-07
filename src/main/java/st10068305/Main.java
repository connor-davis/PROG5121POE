package st10068305;

import st10068305.api.UserManager;
import st10068305.api.UserSLAPI;
import st10068305.screens.WelcomeScreen;

import java.io.IOException;

public class Main {
    private static Main instance;
    private static UserManager userManager;

    public static Main getInstance() {
        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public static void main(String[] args) {
        instance = new Main();
        userManager = new UserManager();

        try {
            // Here we attempt to load the users from the users.yml file.
            userManager.setUsers(UserSLAPI.loadUsers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WelcomeScreen welcomeScreen = new WelcomeScreen();

        welcomeScreen.display();

        try {
            // Here we attempt to save the users to the users.yml file.
            UserSLAPI.saveUsers(userManager.getUsers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}