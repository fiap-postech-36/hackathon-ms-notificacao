package com.fiapx.grupo36.notificationms.application.controller;

import com.fiapx.grupo36.notificationms.application.gates.EmailInput;
import com.fiapx.grupo36.notificationms.application.gates.EmailOutput;
import com.fiapx.grupo36.notificationms.application.mapper.EmailMapper;
import com.fiapx.grupo36.notificationms.application.usecase.SendNotification;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class NotificationController {
    private final SendNotification sendNotification;

    @PostMapping
    public ResponseEntity<EmailOutput> triggerEmail(@RequestBody @Valid EmailInput emailInput)  {
        var email = EmailMapper.INSTANCE.emailInputToEmail(emailInput);
        var id = sendNotification.execute(email).orElse(null);

        return ResponseEntity.ok(id);
    }
}
