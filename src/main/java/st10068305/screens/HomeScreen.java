package st10068305.screens;

import st10068305.Main;
import st10068305.api.UserManager;
import st10068305.util.Messages;
import st10068305.util.TextToAscii;

import java.awt.image.BufferedImage;
import java.util.Scanner;

import static st10068305.util.Commands.LOGOUT_COMMAND;
import static st10068305.util.Commands.QUIT_COMMAND;

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
        System.out.println("\n");

        TextToAscii textToAscii = new TextToAscii(100, 50, 2);
        BufferedImage bufferedImage = textToAscii.draw("Home");

        textToAscii.show(bufferedImage);

        String welcomeMessage = Messages.LOGGED_IN_SUCCESSFULLY;

        welcomeMessage = welcomeMessage.replace("<user first name>", userManager.getCurrentUser().getFirstName());
        welcomeMessage = welcomeMessage.replace("<user last name>", userManager.getCurrentUser().getLastName());

        System.out.println(welcomeMessage);
    }

    @Override
    public void prompt() {
        System.out.println("\nWould you like to ["
                + LOGOUT_COMMAND
                + "|"
                + QUIT_COMMAND
                + "]:");
        System.out.print("> ");

        getCommand();
    }

    @Override
    public void getCommand() {
        String command;

        try (Scanner scanner = new Scanner(System.in)) {
            command = scanner.nextLine();

            switch (command) {
                case LOGOUT_COMMAND:
                    userManager.setCurrentUser(null);
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.display();

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
