package money_problem.domain;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String message) {
        super("Invalid argument: " + message);
    }
}
