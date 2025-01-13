package com.fiapx.grupo36.notificationms.application.usecase;

import com.fiapx.grupo36.notificationms.application.gates.EmailOutput;
import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SendNotification implements UseCase<Email, EmailOutput> {
    @Override
    public Optional<EmailOutput> execute(Email email) {
        Resend resend = new Resend("token");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("FiapX <contato@samuelmteixeira.dev>")
                .to(email.getRecipient())
                .subject(email.getSubject())
                .text(email.getBody())
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            return Optional.of(new EmailOutput(data.getId()));
        } catch (ResendException e) {
            return Optional.empty();
        }
    }
}
