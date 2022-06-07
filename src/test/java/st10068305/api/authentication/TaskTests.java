package st10068305.api.authentication;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import st10068305.api.Task;
import st10068305.api.TaskManager;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskTests {
    private int maximumTasks = 2;

    private Task taskOne;
    private Task taskTwo;

    @BeforeEach
    void setup() {
        TaskManager taskManager = new TaskManager();

        taskManager.setTasks(new HashMap<>());
        taskManager.setMaximumTasks(maximumTasks);

        taskOne = new Task("Login Feature", taskManager.generateTaskNumber(), "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");

        taskManager.addTask(taskOne);

        taskTwo = new Task("Add Task Feature", taskManager.generateTaskNumber(), "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");

        taskManager.addTask(taskTwo);
    }

    @Test
    @Order(1)
    @DisplayName("Test checkTaskDescription()")
    @Description("Task Description should not be more than 50 Characters")
    void checkTaskDescription() {
        assertEquals(Task.testTaskDescription("This will pass."), "Task successfully captured");
        assertEquals(Task.testTaskDescription("This will not pass as it contains more than the limit of 50 characters."), "Please enter a task description of less than 50 characters");
    }

    @Test
    @Order(2)
    @DisplayName("Test taskIdCorrect()")
    @Description("TaskID is correct")
    void testIdCorrect() {
        assertEquals(taskOne.getTaskId(), "LF:0:BYN");
        assertEquals(taskTwo.getTaskId(), "AT:1:IKE");
    }
}
