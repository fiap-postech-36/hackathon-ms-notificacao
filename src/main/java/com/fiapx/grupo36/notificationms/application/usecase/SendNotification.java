package com.fiapx.grupo36.notificationms.application.usecase;

import com.fiapx.grupo36.notificationms.application.gates.EmailOutput;
import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SendNotification implements UseCase<Email, EmailOutput> {
    @Override
    public Optional<EmailOutput> execute(Email email) {
        return Optional.of(new EmailOutput("fsdfA3wefwey_2dned"));
    }
}
