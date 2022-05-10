package st10068305.api;

import java.util.HashMap;

public class UserManager {
    private final HashMap<String, User> users = new HashMap<>();
    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
    }

    public HashMap<String, User> getUsersMap() {
        return this.users;
    }

    public User getUser(String username) {
        return this.users.get(username);
    }
}
