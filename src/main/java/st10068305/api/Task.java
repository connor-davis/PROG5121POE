package st10068305.api;

public class Task {
    String taskName, taskDescription, developerDetails, taskId;
    int taskNumber, taskDuration;

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskId) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskId = taskId;
        this.taskNumber = taskNumber;
        this.taskDuration = taskDuration;
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

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getTaskDuration() {
        return taskDuration;
    }
}
