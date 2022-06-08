package st10068305.screens.authentication;

import st10068305.Main;
import st10068305.api.User;
import st10068305.api.UserManager;
import st10068305.api.authentication.Authentication;
import st10068305.screens.HomeScreen;
import st10068305.screens.Screen;
import st10068305.util.Messages;

import javax.swing.*;

public class LoginScreen extends Screen {
    private final Main main = Main.getInstance();
    private final UserManager userManager = main.getUserManager();

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
        String username;

        username = promptUserName();

        /*
          Here we find the stored user.
         */
        User user = userManager.getUser(username);

        /*
          If the user is not stored then we request for
          the user to be registered.
         */
        if (user == null) {
            System.out.println(Messages.USERNAME_INCORRECT);

            prompt();
        } else {
            promptPassword(user);
        }
    }

    private String promptUserName() {
        return JOptionPane.showInputDialog(
                null,
                "Please enter your username." +
                        "\n\nMust be 5 characters or less." +
                        "\nMust contain an underscore.",
                "Authentication",
                JOptionPane.QUESTION_MESSAGE);
    }

    private void promptPassword(User user) {
        String password;

        password = JOptionPane.showInputDialog(
                null,
                "Please enter your password." +
                        "\n\nMust be at least 8 characters." +
                        "\nMust contain special character, e.g. @" +
                        "\nMust contain a number." +
                        "\nMust contain a capital letter.",
                "Authentication",
                JOptionPane.QUESTION_MESSAGE);

        if (!Authentication.loginUser(user, password)) {
            promptPassword(user);
        } else {
            userManager.setCurrentUser(user);

            HomeScreen homeScreen = new HomeScreen();

            homeScreen.display();
        }
    }

    @Override
    public void getCommand() {

    }
}
