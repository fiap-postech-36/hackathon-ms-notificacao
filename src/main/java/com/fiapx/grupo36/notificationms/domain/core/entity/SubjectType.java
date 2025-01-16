package com.fiapx.grupo36.notificationms.domain.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectType {
    ERROR("Problema ao Processar seu Vídeo"),
    SUCCESS("Seu Vídeo Foi Processado com Sucesso e Já Está Disponível"),;

    private final String value;
}
