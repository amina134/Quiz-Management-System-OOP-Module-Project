package project_poo;

public class Option {
    private String text;
    private boolean isValid;

    public Option(String text, boolean isValid) {
        this.text = text;
        this.isValid = isValid;
    }

    // Getter for text
    public String getText() {
        return text;
    }

    // Setter for text
    public void setText(String text) {
        this.text = text;
    }

    // Getter for isValid
    public boolean getIsValid() {
        return isValid;
    }

    // Setter for isValid
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void printOption() {
        System.out.println(". " + text + (isValid ? " -->(Correct)" : " -->(Incorrect)"));
    }
}
