package demo.web.model;

public class SubstrsAndStrsInput {

    private String taskName;
    private String[] substrings;
    private String[] strings;

    public SubstrsAndStrsInput() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String[] getSubstrings() {
        return substrings;
    }

    public void setSubstrings(String[] substrings) {
        this.substrings = substrings;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
