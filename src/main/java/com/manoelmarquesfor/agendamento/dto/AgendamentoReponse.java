package com.manoelmarquesfor.agendamento.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manoelmarquesfor.agendamento.models.enums.StatusEnum;

public record AgendamentoReponse(
                UUID id,
                String descricao,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") //
                LocalDateTime dataAgendamento,
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") //
                LocalDateTime dataCriacao,
                StatusEnum status) {

}
