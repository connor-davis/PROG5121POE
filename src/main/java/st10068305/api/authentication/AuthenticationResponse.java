package st10068305.api.authentication;

public class AuthenticationResponse {
    private final boolean passed;
    private final String message;

    public AuthenticationResponse(boolean passed, String message) {
        this.passed = passed;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasPassed() {
        return passed;
    }
}
