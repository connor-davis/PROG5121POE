package st10068305.api;

public class Task {
    String taskName, taskDescription, developerDetails, taskId, taskStatus;
    int taskNumber, taskDuration;

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskId = createTaskID();
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
    }

    public static boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    public static String testTaskDescription(String taskDescription) {
        boolean passed = checkTaskDescription(taskDescription);

        if (passed) return "Task successfully captured";
        else return "Please enter a task description of less than 50 characters";
    }

    public String createTaskID() {
        String taskId = "";

        String[] taskNameSplit = taskName.split(" ");

        taskId += taskNameSplit[0].split("")[0].toUpperCase()
                + taskNameSplit[1].split("")[0].toUpperCase()
                + ":";
        taskId += taskNumber + ":";
        taskId += developerDetails.split(" ")[0].substring(developerDetails.split(" ")[0].length() - 3).toUpperCase();

        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDeveloperDetails() {
        return developerDetails;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getTaskDuration() {
        return taskDuration;
    }
}
