package com.fiapx.grupo36.notificationms.domain.core.gateway;

import com.fiapx.grupo36.notificationms.domain.core.entity.Email;

import java.util.Optional;

public interface NotificationGateway {
    Optional<String> send(Email email);
}
