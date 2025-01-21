package com.fiapx.grupo36.notificationms.infra.gateway;

import com.fiapx.grupo36.notificationms.application.gates.EmailOutput;
import com.fiapx.grupo36.notificationms.application.usecase.SendNotification;
import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import com.fiapx.grupo36.notificationms.domain.core.entity.SubjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailGatewayImplTest {

    @Mock
    private EmailSender emailSender;

    private SendNotification sendNotification;

    @BeforeEach
    void setUp() {
        sendNotification = new SendNotification(emailSender);
    }

    @Test
    void testSendEmailSuccessfully() {
        Email email = new Email("recipient@example.com", SubjectType.SUCCESS, "Test email body");
        when(emailSender.sendEmail(email)).thenReturn(Optional.of("12345"));
        Optional<EmailOutput> result = sendNotification.execute(email);
        assertTrue(result.isPresent());
        assertEquals("12345", result.get().id());
        verify(emailSender, times(1)).sendEmail(email);
    }

    @Test
    void testSendEmailFails() {
        Email email = new Email("recipient@example.com", SubjectType.ERROR, "Test email body");
        when(emailSender.sendEmail(email)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> sendNotification.execute(email));
        verify(emailSender, times(1)).sendEmail(email);
    }
}
