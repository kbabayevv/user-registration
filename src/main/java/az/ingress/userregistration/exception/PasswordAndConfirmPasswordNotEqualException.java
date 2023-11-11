package az.ingress.userregistration.exception;

public class PasswordAndConfirmPasswordNotEqualException extends RuntimeException{
    public PasswordAndConfirmPasswordNotEqualException(String message) {
        super(message);
    }
}
