package st10068305.screens.authentication;

import st10068305.Main;
import st10068305.api.User;
import st10068305.api.UserManager;
import st10068305.api.authentication.Authentication;
import st10068305.api.authentication.AuthenticationResponse;
import st10068305.screens.Screen;
import st10068305.util.Messages;

import javax.swing.*;

public class RegisterScreen extends Screen {
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
        String firstName, lastName, username, password;

        firstName = promptFirstName();
        lastName = promptLastName();
        username = promptUserName();
        password = promptPassword();

        String registerStatus = Authentication.registerUser(username, password);

        if (registerStatus.equals(Messages.REGISTERED_SUCCESSFULLY)) {
            User user = new User(firstName, lastName, username, password);

            userManager.addUser(user);

            String welcomeMessage = Messages.REGISTERED_SUCCESSFULLY;

            welcomeMessage = welcomeMessage.replace("<user first name>", user.getFirstName());
            welcomeMessage = welcomeMessage.replace("<user last name>", user.getLastName());

            System.out.println(welcomeMessage);

            LoginScreen loginScreen = new LoginScreen();

            loginScreen.display();
        } else {
            System.out.println(registerStatus);

            prompt();
        }

    }

    /**
     * This function prompts the user for their first name.
     *
     * @return String
     */
    private String promptFirstName() {
        return JOptionPane.showInputDialog(null, "Please enter your first name", "Registration", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * This function prompts the user for their last name.
     *
     * @return String
     */
    private String promptLastName() {
        return JOptionPane.showInputDialog(null, "Please enter your last name", "Registration", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * This function prompts the user for their username,
     * and it checks that the username is correctly
     * formatted.
     *
     * @return String
     */
    private String promptUserName() {
        String username;

        username = JOptionPane.showInputDialog(
                null,
                "Please enter your username" +
                        "\n\nMust be 5 characters or less." +
                        "\nMust contain an underscore.",
                "Registration",
                JOptionPane.QUESTION_MESSAGE);

        AuthenticationResponse userNameCheck = Authentication.checkUserName(username);

        if (!userNameCheck.hasPassed()) {
            System.out.println(userNameCheck.getMessage());

            username = promptUserName();
        }

        return username;
    }

    /**
     * This function prompts the user for their password,
     * and it checks that the password is complex enough.
     *
     * @return String
     */
    private String promptPassword() {
        String password;

        password = JOptionPane.showInputDialog(
                null,
                "Please enter your password" +
                        "\n\nMust be at least 8 characters." +
                        "\nMust contain special character, e.g. @" +
                        "\nMust contain a number." +
                        "\nMust contain a capital letter.",
                "Registration",
                JOptionPane.QUESTION_MESSAGE);

        AuthenticationResponse complexityCheck = Authentication.checkPasswordComplexity(password);

        if (!complexityCheck.hasPassed()) {
            System.out.println(complexityCheck.getMessage());

            password = promptPassword();
        }

        return password;
    }

    @Override
    public void getCommand() {

    }
}
