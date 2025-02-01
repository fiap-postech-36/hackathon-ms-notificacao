package com.fiapx.grupo36.notificationms.application.controller;

import com.fiapx.grupo36.notificationms.application.gates.EmailInput;
import com.fiapx.grupo36.notificationms.application.gates.EmailOutput;
import com.fiapx.grupo36.notificationms.application.usecase.SendNotification;
import com.fiapx.grupo36.notificationms.domain.core.entity.SubjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NotificationControllerTest {

    @Mock
    private SendNotification sendNotification;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTriggerEmail() {
        EmailInput emailInput = new EmailInput("recipient@example.com", SubjectType.SUCCESS, "This is a test email body");
        EmailOutput emailOutput = new EmailOutput("123456789");
        when(sendNotification.execute(any())).thenReturn(java.util.Optional.of(emailOutput));

        ResponseEntity<EmailOutput> response = notificationController.triggerEmail(emailInput);

        assertEquals(ResponseEntity.ok(emailOutput), response);
    }
}
