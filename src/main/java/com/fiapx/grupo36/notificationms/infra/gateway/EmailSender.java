package com.fiapx.grupo36.notificationms.infra.gateway;

import com.fiapx.grupo36.notificationms.domain.core.entity.Email;

import java.util.Optional;

public interface EmailSender {
    Optional<String> sendEmail(Email email);
}
