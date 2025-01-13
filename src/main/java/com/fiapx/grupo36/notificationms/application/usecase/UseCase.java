package com.fiapx.grupo36.notificationms.application.usecase;

import jakarta.validation.Valid;

import java.util.Optional;

public interface UseCase<Input, Output>{
    Optional<Output> execute(final @Valid Input input);
}
