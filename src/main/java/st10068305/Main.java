package st10068305;

import st10068305.api.UserManager;
import st10068305.screens.WelcomeScreen;

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

        WelcomeScreen welcomeScreen = new WelcomeScreen();

        welcomeScreen.display();
    }
}