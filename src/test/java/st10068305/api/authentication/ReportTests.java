package st10068305.api.authentication;

import org.junit.jupiter.api.*;
import st10068305.api.ReportManager;
import st10068305.api.Task;
import st10068305.api.TaskManager;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportTests {
    private TaskManager taskManager;
    private ReportManager reportManager;

    @BeforeEach
    void setup() {
        taskManager = new TaskManager();
        reportManager = new ReportManager(taskManager);

        taskManager.setTasks(new HashMap<>());

        int maximumTasks = 4;

        taskManager.setMaximumTasks(maximumTasks);

        Task taskOne = new Task("Create Login", taskManager.generateTaskNumber(), "Test", "Mike Smith", 5, "To Do");

        taskManager.addTask(taskOne);

        Task taskTwo = new Task("Create Add Features", taskManager.generateTaskNumber(), "Test", "Edward Harrington", 8, "Doing");

        taskManager.addTask(taskTwo);

        Task taskThree = new Task("Create Reports", taskManager.generateTaskNumber(), "Test", "Samantha Paulson", 2, "Done");

        taskManager.addTask(taskThree);

        Task taskFour = new Task("Add Arrays", taskManager.generateTaskNumber(), "Test", "Glenda Oberholzer", 11, "To Do");

        taskManager.addTask(taskFour);
    }

    @Test
    @Order(1)
    @DisplayName("Developer array correctly populated")
    void checkDeveloperArrayCorrectlyPopulated() {
        String developers = String.join(", ", taskManager.getDevelopers());

        assertEquals(developers, "Mike Smith, Edward Harrington, Samantha Paulson, Glenda Oberholzer");
    }

    @Test
    @Order(2)
    @DisplayName("Display Developer and Duration for task with longest duration")
    void displayDeveloperAndTaskDuration() {
        assertEquals(reportManager.displayDeveloperAndDurationOfLongestDuration(), "Glenda Oberholzer, 11");
    }

    @Test
    @Order(3)
    @DisplayName("Search for tasks by name")
    void searchForTasksByName() {
        assertEquals(reportManager.searchByTaskName("Create Login"), "Mike Smith, Create Login, To Do");
    }

    @Test
    @Order(4)
    @DisplayName("Search all tasks assigned to Developer")
    void searchAllTasksAssignedToDeveloper() {
        assertEquals(reportManager.searchByDeveloper("Samantha Paulson"), "1. Create Reports, Done");
    }

    @Test
    @Order(5)
    @DisplayName("Delete Task from array")
    void deleteTaskFromArray() {
        assertEquals(reportManager.deleteByTaskName("Create Reports", true), "Entry \"Create Reports\" successfully deleted.");
    }

    @Test
    @Order(6)
    @DisplayName("Display Report")
    void displayReport() {

    }
}
