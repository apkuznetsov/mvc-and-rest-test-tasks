package demo.web.model;

public class SubstrsAndStrs {

    private String[] substrings;
    private String[] strings;

    public SubstrsAndStrs() {
    }

    public SubstrsAndStrs(String[] substrings, String[] strings) {
        this.substrings = substrings;
        this.strings = strings;
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
