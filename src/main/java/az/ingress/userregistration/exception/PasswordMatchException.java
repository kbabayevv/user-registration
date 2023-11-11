package az.ingress.userregistration.exception;

public class PasswordMatchException extends RuntimeException{
    public PasswordMatchException(String message) {
        super(message);
    }
}
