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
    void testSendEmail() {
        emailService.sendEmail(recipient, subject, message);

        verify(emailSender).sendEmail(eq(recipient), eq(subject), eq(message));
    }

    @Test
    public void testSendEmailFailure() {
        doThrow(EmailSenderException.class).when(emailSender).sendEmail(anyString(), anyString(), anyString());

        String exceptionMessage = assertThrows(
                EmailSendingException.class,
                () -> emailSender.sendEmail(recipient, subject, message)
        ).getMessage();
        assertEquals("Failed to send email" , exceptionMessage);
    }

    @Test
    public void testSendEmailEmptyMessage() {
        emailService.sendEmail(recipient, subject, "");

        verify(emailSender, never()).sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    public void testSendEmailWithCustomHeader() {
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
}
