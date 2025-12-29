package com.manoelmarquesfor.agendamento.models.enums;

public enum StatusEnum {
    AGENDADO(1),
    CANCELADO(2),
    CONCLUIDO(3);

    private final int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}