package st10068305.screens.authentication;

import st10068305.Main;
import st10068305.api.User;
import st10068305.api.UserManager;
import st10068305.api.authentication.Authentication;
import st10068305.screens.HomeScreen;
import st10068305.screens.Screen;
import st10068305.util.Messages;
import st10068305.util.TextToAscii;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import static java.lang.System.in;

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
        System.out.println("\n");

        TextToAscii textToAscii = new TextToAscii(100, 50, 2);
        BufferedImage bufferedImage = textToAscii.draw("Login");

        textToAscii.show(bufferedImage);
    }

    @Override
    public void prompt() {
        String username;

        try (Scanner scanner = new Scanner(in)) {
            username = promptUserName(scanner);

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
                promptPassword(scanner, user);
            }
        }
    }

    private String promptUserName(Scanner scanner) {
        System.out.println("\nPlease enter your username:");
        System.out.print("> ");

        return scanner.nextLine();
    }

    private void promptPassword(Scanner scanner, User user) {
        String password;

        System.out.println("\nPlease enter your password:");
        System.out.print("> ");

        password = scanner.nextLine();

        if (!Authentication.loginUser(user, password)) {
            promptPassword(scanner, user);
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
