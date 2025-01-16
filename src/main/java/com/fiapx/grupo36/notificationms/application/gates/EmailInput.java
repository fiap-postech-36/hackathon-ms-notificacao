package com.fiapx.grupo36.notificationms.application.gates;

import com.fiapx.grupo36.notificationms.domain.core.entity.SubjectType;

public record EmailInput(String recipient, SubjectType subject, String body) {
}
