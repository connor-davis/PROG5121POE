package st10068305.api.authentication;

import org.junit.jupiter.api.*;
import st10068305.api.User;
import st10068305.api.UserManager;
import st10068305.util.Messages;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationTests {
    private static final UserManager userManager = new UserManager();
    private static User testUser;

    @Test
    @Order(1)
    @DisplayName("Test checkUserName()")
    void checkUserName() {
        AuthenticationResponse pass = Authentication.checkUserName("kyl_!");
        AuthenticationResponse fail = Authentication.checkUserName("kyle!!!!!!!");

        assertEquals(pass.getMessage(), Messages.USERNAME_CAPTURED);
        assertEquals(fail.getMessage(), Messages.USERNAME_INCORRECT_FORMAT);

        assertTrue(pass.hasPassed());
        assertFalse(fail.hasPassed());
    }

    @Test
    @Order(2)
    @DisplayName("Test checkPasswordComplexity()")
    void checkPasswordComplexity() {
        AuthenticationResponse pass = Authentication.checkPasswordComplexity("Ch&&sec@ke99!");
        AuthenticationResponse fail = Authentication.checkPasswordComplexity("password");

        assertEquals(pass.getMessage(), Messages.PASSWORD_CAPTURED);
        assertEquals(fail.getMessage(), Messages.PASSWORD_INCORRECT_FORMAT);

        assertTrue(pass.hasPassed());
        assertFalse(fail.hasPassed());
    }

    @Test
    @Order(3)
    @DisplayName("Test registerUser()")
    void registerUser() {
        String registerStatus = Authentication.registerUser("cdav_", "@Cp2606#$%");

        if (registerStatus.equals(Messages.REGISTERED_SUCCESSFULLY)) {
            testUser = new User("Connor", "Davis", "cdav", "@Cp2606#$%");

            userManager.addUser(testUser);
        } else {
            System.out.println(registerStatus);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Test loginUser()")
    void loginUser() {
        assertTrue(Authentication.loginUser(testUser, "@Cp2606#$%"));
        assertFalse(Authentication.loginUser(testUser, "Connor100203"));
    }
}