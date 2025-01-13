package com.fiapx.grupo36.notificationms.domain.core.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Email {
    private String recipient;
    private String subject;
    private String body;
}