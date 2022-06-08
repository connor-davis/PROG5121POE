package st10068305.api;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users = new HashMap<>();
    private User currentUser;

    public UserManager() {

    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
    }

    public HashMap<String, User> getUsers() {
        return this.users;
    }

    public User getUser(String username) {
        return this.users.get(username);
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
}
