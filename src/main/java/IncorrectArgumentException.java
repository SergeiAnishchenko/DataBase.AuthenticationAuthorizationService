public class IncorrectArgumentException extends Exception {

    private String argument;

    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "Параметр " + argument + " задан не корректно.";
    }
}
