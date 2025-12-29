package com.manoelmarquesfor.agendamento.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AgendamentoRequestCreate(
                @NotBlank(message = "O campo 'descricao' não pode estar em branco.") //
                @Size(min = 5, max = 255, message = "O campo 'descricao' deve ter entre 5 e 255 caracteres.") //
                String descricao,

                @NotNull(message = "O campo 'dataAgendamento' é obrigatório.") //
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") //
                LocalDateTime dataAgendamento

) {

}
