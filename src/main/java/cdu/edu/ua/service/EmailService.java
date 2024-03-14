package cdu.edu.ua.service;

import cdu.edu.ua.exception.EmailSenderException;
import cdu.edu.ua.exception.EmailSendingException;

public class EmailService {

    private final EmailSender emailSender;

    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String recipient, String subject, String message) {
        if (message.isEmpty()) {
            return; // Не відправляти лист, якщо текст порожній
        }
        try {
            emailSender.sendEmail(recipient, subject, message);
        } catch (EmailSenderException e) {
            throw new EmailSendingException("Failed to send email", e);
        }
    }

    public void sendEmailWithCustomHeader(String recipient, String subject, String message, String headerName, String headerValue) {
        if (headerName.isEmpty() || headerValue.isEmpty()) {
            return;
        }
        try {
            emailSender.sendEmailWithCustomHeader(recipient, subject, message, headerName, headerValue);
        } catch (EmailSenderException e) {
            throw new EmailSendingException("Failed to send email with custom header", e);
        }
    }
}
