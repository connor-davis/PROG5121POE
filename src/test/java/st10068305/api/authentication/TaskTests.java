package st10068305.api.authentication;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import st10068305.api.Task;
import st10068305.api.TaskManager;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskTests {
    private TaskManager taskManager;
    private Task taskOne;
    private Task taskTwo;

    @BeforeEach
    void setup() {
        taskManager = new TaskManager();

        taskManager.setTasks(new HashMap<>());

        int maximumTasks = 2;

        taskManager.setMaximumTasks(maximumTasks);

        taskOne = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");

        taskManager.addTask(taskOne);

        taskTwo = new Task("Add Task Feature", taskManager.generateTaskNumber(), "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");

        taskManager.addTask(taskTwo);
    }

    @Test
    @Order(1)
    @DisplayName("Test checkTaskDescription()")
    void checkTaskDescription() {
        assertEquals(Task.testTaskDescription("This will pass."), "Task successfully captured");
        assertEquals(Task.testTaskDescription("This will not pass as it contains more than the limit of 50 characters."), "Please enter a task description of less than 50 characters");
    }

    @Test
    @Order(2)
    @DisplayName("Test testIdCorrect()")
    void testIdCorrect() {
        assertEquals(taskOne.getTaskId(), "LF:0:BYN");
        assertEquals(taskTwo.getTaskId(), "AT:1:IKE");
    }

    @Test
    @Order(3)
    @DisplayName("Test testHoursCorrectlyAccumulated()")
    void testHoursCorrectlyAccumulated() {
        assertEquals(taskManager.returnTotalHours(), 18);


        taskManager.setTasks(new HashMap<>());

        Task taskOne = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 10, "To Do");

        taskManager.addTask(taskOne);

        Task taskTwo = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 12, "To Do");

        taskManager.addTask(taskTwo);

        Task taskThree = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 55, "To Do");

        taskManager.addTask(taskThree);

        Task taskFour = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 11, "To Do");

        taskManager.addTask(taskFour);

        Task taskFive = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 1, "To Do");

        taskManager.addTask(taskFive);

        assertEquals(taskManager.returnTotalHours(), 89);
    }
}
