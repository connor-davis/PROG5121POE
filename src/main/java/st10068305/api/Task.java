package st10068305.api;

public class Task {
    String taskName, taskDescription, developerDetails, taskId, taskStatus;
    int taskNumber, taskDuration;

    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskId, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskId = taskId;
        this.taskNumber = taskNumber;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
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
