package demo.web.model;

public class TaskInput {

    private String taskName;
    private int input;

    public TaskInput() {
    }

    public TaskInput(String taskName, int input) {
        this.taskName = taskName;
        this.input = input;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }
}
