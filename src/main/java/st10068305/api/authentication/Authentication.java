package st10068305.api.authentication;

import st10068305.api.User;
import st10068305.util.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authentication {
    /**
     * This function validates the given password with the users'
     * password stored in memory and if they match then the user
     * may see the home screen.
     *
     * @param user     The user found from the memory store.
     * @param password The given password.
     * @return boolean
     */
    public static boolean loginUser(User user, String password) {
        /*
          Here we check that the entered password matches the
          stored users' password. If not we ask the user for
          the correct password.
         */
        if (password.equals(user.getPassword())) {
            return true;
        } else {
            System.out.println(Messages.PASSWORD_INCORRECT);

            return false;
        }
    }

    /**
     * This function validates the users' username and password
     * and if it passes then the users' data can continue to be
     * stored.
     *
     * @param userName The given username.
     * @param password The given password.
     * @return String
     */
    public static String registerUser(String userName, String password) {
        AuthenticationResponse userNameCheck = checkUserName(userName);
        AuthenticationResponse complexityCheck = checkPasswordComplexity(password);

        if (userNameCheck.hasPassed()) {
            System.out.println(userNameCheck.getMessage());

            if (complexityCheck.hasPassed()) {
                System.out.println(complexityCheck.getMessage());

                return Messages.REGISTERED_SUCCESSFULLY;
            } else {
                System.out.println(complexityCheck.getMessage());

                return complexityCheck.getMessage();
            }
        } else {
            System.out.println(userNameCheck.getMessage());

            return userNameCheck.getMessage();
        }
    }

    /**
     * This static function will check the given username
     * and make sure it is correctly formatted.
     *
     * @param username The given username.
     * @return AuthenticationResponse
     */
    public static AuthenticationResponse checkUserName(String username) {
        /*
          Check that the username has no more than five characters.
         */
        boolean noMoreThanFiveCharacters = username.length() <= 5 && username.length() > 0;

        /*
          Check that the username contains an underscore.
         */
        boolean containsAnUnderscore = username.contains("_");

        if (!noMoreThanFiveCharacters)
            return new AuthenticationResponse(false, Messages.USERNAME_INCORRECT_FORMAT);
        if (!containsAnUnderscore) return new AuthenticationResponse(false, Messages.USERNAME_INCORRECT_FORMAT);

        return new AuthenticationResponse(true, Messages.USERNAME_CAPTURED);
    }

    /**
     * This static function will check the given passwords'
     * complexity using regex pattern matches.
     *
     * @param password The given password.
     * @return AuthenticationResponse
     */
    public static AuthenticationResponse checkPasswordComplexity(String password) {
        /*
          Here we build the regex that makes sure the password
          contains at least one upper or lower case character.
         */
        Pattern lettersRegex = Pattern.compile("[a-zA-z]");
        Matcher hasLetterMatcher = lettersRegex.matcher(password);

        /*
          Here we build the regex that makes sure the password
          contains at least one special character.
         */
        Pattern specialCharactersRegex = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecialCharacterMatcher = specialCharactersRegex.matcher(password);

        /*
          Here we build the regex that makes sure the password
          contains at least one number.
         */
        Pattern numbersRegex = Pattern.compile("\\d");
        Matcher hasNumberMatcher = numbersRegex.matcher(password);

        /*
          Here we initialize our booleans and return the corresponding
          authentication responses back.
         */
        boolean hasUpperAndLowerCaseLetters = hasLetterMatcher.find();
        boolean hasSpecialCharacters = hasSpecialCharacterMatcher.find();
        boolean hasNumbers = hasNumberMatcher.find();
        boolean hasAtLeastEightCharacters = password.length() >= 8;

        if (!hasAtLeastEightCharacters)
            return new AuthenticationResponse(false, Messages.PASSWORD_INCORRECT_FORMAT);
        if (!hasUpperAndLowerCaseLetters)
            return new AuthenticationResponse(false, Messages.PASSWORD_INCORRECT_FORMAT);
        if (!hasSpecialCharacters)
            return new AuthenticationResponse(false, Messages.PASSWORD_INCORRECT_FORMAT);
        if (!hasNumbers) return new AuthenticationResponse(false, Messages.PASSWORD_INCORRECT_FORMAT);

        return new AuthenticationResponse(true, Messages.PASSWORD_CAPTURED);
    }
}
