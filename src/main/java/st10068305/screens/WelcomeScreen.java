package st10068305.screens;

import st10068305.Main;
import st10068305.api.UserManager;
import st10068305.screens.authentication.LoginScreen;
import st10068305.screens.authentication.RegisterScreen;
import st10068305.util.TextToAscii;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import static st10068305.util.Commands.*;

public class WelcomeScreen extends Screen {
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
        BufferedImage bufferedImage = textToAscii.draw("L U P I N");

        textToAscii.show(bufferedImage);
    }

    @Override
    public void prompt() {
        String welcomeMessage
                = "\nWelcome to Task One, please choose whether you would like to ["
                + LOGIN_COMMAND
                + "|"
                + REGISTER_COMMAND
                + "|"
                + QUIT_COMMAND
                + "]:";

        System.out.println(welcomeMessage);
        System.out.print("> ");

        getCommand();
    }

    @Override
    public void getCommand() {
        String command;

        try (Scanner scanner = new Scanner(System.in)) {
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case LOGIN_COMMAND:
                    if (userManager.getUsersMap().size() > 0) {
                        LoginScreen loginScreen = new LoginScreen();

                        loginScreen.display();
                    } else {
                        System.out.println("\nThere are no stored users, please register first.");

                        prompt();
                    }

                    break;

                case REGISTER_COMMAND:
                    RegisterScreen registerScreen = new RegisterScreen();

                    registerScreen.display();

                    break;

                case QUIT_COMMAND:
                    scanner.close();

                    break;

                default:
                    prompt();

                    break;
            }
        }
    }
}
