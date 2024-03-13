package cdu.edu.ua.service;

public interface EmailSender {
    void sendEmail(String recipient, String subject, String message);

    void sendEmailWithCustomHeader(String recipient, String subject, String message, String headerName, String headerValue);
}
