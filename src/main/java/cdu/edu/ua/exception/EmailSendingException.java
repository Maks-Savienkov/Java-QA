package cdu.edu.ua.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message, EmailSenderException e) {
        super(message + (e.getMessage() == null ? "" : e.getMessage()));
    }
}
