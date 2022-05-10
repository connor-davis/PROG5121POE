package st10068305.screens.authentication;

import st10068305.Main;
import st10068305.api.User;
import st10068305.api.UserManager;
import st10068305.api.authentication.Authentication;
import st10068305.api.authentication.AuthenticationResponse;
import st10068305.screens.Screen;
import st10068305.util.Messages;
import st10068305.util.TextToAscii;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import static java.lang.System.in;

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
        System.out.println("\n");

        TextToAscii textToAscii = new TextToAscii(100, 50, 2);
        BufferedImage bufferedImage = textToAscii.draw("Register");

        textToAscii.show(bufferedImage);
    }

    @Override
    public void prompt() {
        String firstName, lastName, username, password;

        try (Scanner scanner = new Scanner(in)) {
            firstName = promptFirstName(scanner);
            lastName = promptLastName(scanner);
            username = promptUserName(scanner);
            password = promptPassword(scanner);

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
    }

    /**
     * This function prompts the user for their first name.
     *
     * @param scanner The scanner class.
     * @return String
     */
    private String promptFirstName(Scanner scanner) {
        System.out.println("\nPlease enter your first name:");
        System.out.print("> ");

        return scanner.nextLine();
    }

    /**
     * This function prompts the user for their last name.
     *
     * @param scanner The scanner class.
     * @return String
     */
    private String promptLastName(Scanner scanner) {
        System.out.println("\nPlease enter your last name:");
        System.out.print("> ");

        return scanner.nextLine();
    }

    /**
     * This function prompts the user for their username,
     * and it checks that the username is correctly
     * formatted.
     *
     * @param scanner The scanner class.
     * @return String
     */
    private String promptUserName(Scanner scanner) {
        String username;

        System.out.println("\nPlease enter your username:");
        System.out.print("> ");

        username = scanner.nextLine();

        AuthenticationResponse userNameCheck = Authentication.checkUserName(username);

        if (!userNameCheck.hasPassed()) {
            System.out.println(userNameCheck.getMessage());

            username = promptUserName(scanner);
        }

        return username;
    }

    /**
     * This function prompts the user for their password,
     * and it checks that the password is complex enough.
     *
     * @param scanner The scanner class.
     * @return String
     */
    private String promptPassword(Scanner scanner) {
        String password;

        System.out.println("\nPlease enter your password:");
        System.out.print("> ");

        password = scanner.nextLine();

        AuthenticationResponse complexityCheck = Authentication.checkPasswordComplexity(password);

        if (!complexityCheck.hasPassed()) {
            System.out.println(complexityCheck.getMessage());

            password = promptPassword(scanner);
        }

        return password;
    }

    @Override
    public void getCommand() {

    }
}
