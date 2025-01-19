package com.fiapx.grupo36.notificationms.infra.gateway;

import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import com.fiapx.grupo36.notificationms.domain.core.entity.SubjectType;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailGatewayImplTest {

    @Mock
    private Resend resend;

    @Mock
    private CreateEmailResponse createEmailResponse;

    @InjectMocks
    private EmailGatewayImpl emailGateway;

    @BeforeEach
    void setUp() {
        String resendToken = "test-token";
       // emailGateway = new EmailGatewayImpl(resendToken);
    }

    @Test
    void testSendEmailSuccess() throws ResendException {
        // Arrange
        Email email = new Email("recipient@example.com", SubjectType.SUCCESS, "Test Body");
        when(resend.emails().send(any(CreateEmailOptions.class))).thenReturn(createEmailResponse);
        when(createEmailResponse.getId()).thenReturn("email-id");

        Optional<String> result = emailGateway.send(email);

        assertTrue(result.isPresent());
        assertEquals("email-id", result.get());
        ArgumentCaptor<CreateEmailOptions> captor = ArgumentCaptor.forClass(CreateEmailOptions.class);
        verify(resend.emails()).send(captor.capture());
        CreateEmailOptions sentEmailOptions = captor.getValue();
        assertEquals("FiapX <contato@samuelmteixeira.dev>", sentEmailOptions.getFrom());
        assertEquals("recipient@example.com", sentEmailOptions.getTo());
        assertEquals("Test Subject", sentEmailOptions.getSubject());
        assertEquals("Test Body", sentEmailOptions.getText());
    }

    @Test
    void testSendEmailFailure() throws ResendException {
        Email email = new Email("recipient@example.com", SubjectType.ERROR, "Test Body");
        when(resend.emails().send(any(CreateEmailOptions.class))).thenThrow(new ResendException("Error"));

        Optional<String> result = emailGateway.send(email);

        assertTrue(result.isEmpty());
        verify(resend.emails()).send(any(CreateEmailOptions.class));
    }
}