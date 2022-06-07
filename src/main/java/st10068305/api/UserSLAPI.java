package st10068305.api;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;

/**
 * SLAPI<br><br>
 * <p>
 * S-Saving<br>
 * L-Loading<br>
 * API-Application Programming Interface<br><br>
 * <p>
 * This class will save and load users from a yaml file using <a href="https://stackabuse.com/reading-and-writing-yaml-files-in-java-with-snakeyaml">SnakeYaml</a>.
 */
public class UserSLAPI {
    /**
     * This function takes in the users HashMap and converts it to the
     * required data for saving to a yaml file.
     *
     * @param users The users HashMap
     * @throws IOException Throws IOException if an exception occurs while saving users.
     */
    public static void saveUsers(HashMap<String, User> users) throws IOException {
        File usersFile = new File("users.yml");

        if (!usersFile.exists()) usersFile.createNewFile();

        PrintWriter usersWriter = new PrintWriter(usersFile);
        Yaml usersYaml = new Yaml();

        HashMap<String, HashMap<String, Object>> allHashMap = new HashMap<>(); // This hashmap stores all users.

        for (String key : users.keySet()) {
            HashMap<String, Object> singleHashMap = new HashMap<>(); // This hashmap stores a single user.

            singleHashMap.put("firstName", users.get(key).getFirstName());
            singleHashMap.put("lastName", users.get(key).getLastName());
            singleHashMap.put("username", users.get(key).getUsername());
            singleHashMap.put("password", users.get(key).getPassword());

            allHashMap.put(key, singleHashMap);
        }

        usersYaml.dump(allHashMap, usersWriter);
    }

    /**
     * This function loads the Users HashMap from the users.yml file stored.
     *
     * @return Users HashMap
     * @throws IOException Throws IOException if an exception occurs when loading users.
     */
    public static HashMap<String, User> loadUsers() throws IOException {
        File usersFile = new File("users.yml");

        if (!usersFile.exists()) {
            usersFile.createNewFile();

            return new HashMap<>();
        }

        InputStream usersInputStream = Files.newInputStream(usersFile.toPath());
        Yaml usersYaml = new Yaml();

        HashMap<String, HashMap<String, Object>> allHashMap = usersYaml.load(usersInputStream);
        HashMap<String, User> usersHashMap = new HashMap<>();

        for (String key : allHashMap.keySet()) {
            HashMap<String, Object> singleHashMap = allHashMap.get(key);

            String firstName = singleHashMap.get("firstName").toString();
            String lastName = singleHashMap.get("lastName").toString();
            String username = singleHashMap.get("username").toString();
            String password = singleHashMap.get("password").toString();

            User user = new User(firstName, lastName, username, password);

            usersHashMap.put(key, user);
        }

        return usersHashMap;
    }
}
