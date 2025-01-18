package com.fiapx.grupo36.notificationms.application.integration;

import com.fiapx.grupo36.notificationms.application.gates.EmailInput;
import com.fiapx.grupo36.notificationms.application.infra.RabbitMQConfig;
import com.fiapx.grupo36.notificationms.application.mapper.EmailMapper;
import com.fiapx.grupo36.notificationms.application.usecase.SendNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SendNotification sendNotification;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(EmailInput emailInput) {
        var email = EmailMapper.INSTANCE.emailInputToEmail(emailInput);
        sendNotification.execute(email);
    }
}
