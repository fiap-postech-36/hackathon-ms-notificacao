package com.fiapx.grupo36.notificationms.infra.gateway;

import com.fiapx.grupo36.notificationms.domain.core.entity.Email;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailGatewayImpl implements EmailSender {

    @Value("${resend.api-key}")
    private String resendToken;

    @Override
    public Optional<String> sendEmail(Email email) {
        Resend resend = new Resend(resendToken);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("FiapX <contato@samuelmteixeira.dev>")
                .to(email.getRecipient())
                .subject(email.getSubject().getValue())
                .text(email.getBody())
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            return Optional.of(data.getId());
        } catch (ResendException e) {
            return Optional.empty();
        }
    }
}
