package st10068305.api;

public class User {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;

    public User(
            String firstName,
            String lastName,
            String username,
            String password
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
