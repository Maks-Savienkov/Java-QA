package cdu.edu.ua.service;


import cdu.edu.ua.exception.EmailSenderException;
import cdu.edu.ua.exception.EmailSendingException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@MockitoSettings
public class EmailServiceTest {

    private final String recipient = "recipient@example.com";
    private final String subject = "Test Subject";
    private final String message = "Test Message";

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendEmail() {
        emailService.sendEmail(recipient, subject, message);

        verify(emailSender).sendEmail(eq(recipient), eq(subject), eq(message));
    }

    @Test
    public void sendEmail_should_throw_exception_when_sender_fail() {
        doThrow(EmailSenderException.class).when(emailSender).sendEmail(anyString(), anyString(), anyString());

        String exceptionMessage = assertThrows(
                EmailSendingException.class,
                () -> emailService.sendEmail(recipient, subject, message)
        ).getMessage();
        assertEquals("Failed to send email" , exceptionMessage);
    }

    @Test
    public void sendEmail_should_fail_when_empty_message() {
        emailService.sendEmail(recipient, subject, "");

        verify(emailSender, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void sendEmailWithCustomHeader() {
        String headerName = "X-Custom-Header";
        String headerValue = "CustomValue";

        emailService.sendEmailWithCustomHeader(
                recipient,
                subject,
                message,
                headerName,
                headerValue
        );

        verify(emailSender).sendEmailWithCustomHeader(
                eq(recipient),
                eq(subject),
                eq(message),
                eq(headerName),
                eq(headerValue)
        );
    }

    @Test
    public void sendEmailWithCustomHeader_should_fail_when_empty_custom_header() {
        String headerName = "";
        String headerValue = "";

        emailService.sendEmailWithCustomHeader(
                recipient,
                subject,
                message,
                headerName,
                headerValue
        );

        verify(emailSender, never()).sendEmailWithCustomHeader(
                eq(recipient),
                eq(subject),
                eq(message),
                eq(headerName),
                eq(headerValue)
        );
    }
}
