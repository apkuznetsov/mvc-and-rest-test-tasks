package demo.web.model;

public class NumberInput {

    private String taskName;
    private int input;

    public NumberInput() {
    }

    public NumberInput(String taskName, int input) {
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
