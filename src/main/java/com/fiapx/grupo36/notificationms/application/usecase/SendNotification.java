package com.fiapx.grupo36.notificationms.application.usecase;

import com.fiapx.grupo36.notificationms.application.gates.EmailOutput;
import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import com.fiapx.grupo36.notificationms.domain.core.gateway.NotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SendNotification implements UseCase<Email, EmailOutput> {

    private final NotificationGateway notificationGateway;

    @Override
    public Optional<EmailOutput> execute(Email email) {
        Optional<String> id = notificationGateway.send(email);

        if(id.isEmpty()) {
            throw new RuntimeException("Failed to send email");
        }

        return Optional.of(new EmailOutput(id.orElse(null)));
    }
}
