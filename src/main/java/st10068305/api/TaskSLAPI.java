package st10068305.api;

import org.yaml.snakeyaml.Yaml;
import st10068305.Main;

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
public class TaskSLAPI {
    private static final Main main = Main.getInstance();
    private static final TaskManager taskManager = main.getTaskManager();

    /**
     * This function takes in the tasks HashMap and converts it to the
     * required data for saving to a yaml file.
     *
     * @param tasks The tasks HashMap
     * @throws IOException Throws IOException if an exception occurs while saving users.
     */
    public static void saveTasks(HashMap<String, Task> tasks) throws IOException {
        File tasksFile = new File("tasks.yml");

        if (!tasksFile.exists()) tasksFile.createNewFile();

        PrintWriter tasksWriter = new PrintWriter(tasksFile);
        Yaml tasksYaml = new Yaml();

        HashMap<String, HashMap<String, Object>> allHashMap = new HashMap<>(); // This hashmap stores all tasks.

        for (String key : tasks.keySet()) {
            HashMap<String, Object> singleHashMap = new HashMap<>(); // This hashmap stores a single task.

            singleHashMap.put("taskName", tasks.get(key).getTaskName());
            singleHashMap.put("taskNumber", tasks.get(key).getTaskNumber());
            singleHashMap.put("taskDescription", tasks.get(key).getTaskDescription());
            singleHashMap.put("developerDetails", tasks.get(key).getDeveloperDetails());
            singleHashMap.put("taskDuration", tasks.get(key).getTaskDuration());
            singleHashMap.put("taskId", tasks.get(key).getTaskId());
            singleHashMap.put("taskStatus", tasks.get(key).getTaskStatus());

            allHashMap.put(key, singleHashMap);
        }

        tasksYaml.dump(allHashMap, tasksWriter);
    }

    /**
     * This function loads the Users HashMap from the users.yml file stored.
     *
     * @return Users HashMap
     * @throws IOException Throws IOException if an exception occurs when loading users.
     */
    public static HashMap<String, Task> loadTasks() throws IOException {
        File tasksFile = new File("tasks.yml");

        if (!tasksFile.exists()) {
            tasksFile.createNewFile();

            return new HashMap<>();
        }

        InputStream tasksInputStream = Files.newInputStream(tasksFile.toPath());
        Yaml tasksYaml = new Yaml();

        HashMap<String, HashMap<String, Object>> allHashMap = tasksYaml.load(tasksInputStream);
        HashMap<String, Task> tasksHashMap = new HashMap<>();

        for (String key : allHashMap.keySet()) {
            HashMap<String, Object> singleHashMap = allHashMap.get(key);

            String taskName = (String) singleHashMap.get("taskName");
            String taskDescription = (String) singleHashMap.get("taskDescription");
            String developerDetails = (String) singleHashMap.get("developerDetails");
            int taskDuration = (int) singleHashMap.get("taskDuration");
            String taskStatus = (String) singleHashMap.get("taskStatus");

            Task task = new Task(taskName, taskManager.generateTaskNumber(), taskDescription, developerDetails, taskDuration, taskStatus);

            tasksHashMap.put(key, task);
        }

        return tasksHashMap;
    }
}
