package st10068305.screens;

import st10068305.Main;
import st10068305.api.UserManager;
import st10068305.screens.authentication.LoginScreen;
import st10068305.screens.authentication.RegisterScreen;
import st10068305.util.TextToAscii;

import javax.swing.*;
import java.awt.image.BufferedImage;

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
    }

    @Override
    public void prompt() {
        getCommand();
    }

    @Override
    public void getCommand() {
        int command;
        Object[] commands = new Object[]{LOGIN_COMMAND, REGISTER_COMMAND, QUIT_COMMAND};

        command = JOptionPane.showOptionDialog(null, "Welcome to EasyKanban", "Welcome", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, commands, commands[0]);

        switch (command) {
            case 0:
                if (userManager.getUsers().size() > 0) {
                    LoginScreen loginScreen = new LoginScreen();

                    loginScreen.display();
                } else {
                    JOptionPane.showMessageDialog(null, "There are no stored users, please register one.", "Warning", JOptionPane.INFORMATION_MESSAGE);

                    prompt();
                }

                break;

            case 1:
                RegisterScreen registerScreen = new RegisterScreen();

                registerScreen.display();

                break;
            default:
                break;
        }
    }
}
