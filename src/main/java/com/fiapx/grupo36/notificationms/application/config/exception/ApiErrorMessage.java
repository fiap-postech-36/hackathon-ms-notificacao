package com.fiapx.grupo36.notificationms.application.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiErrorMessage {
    private List<String> errors;
}
