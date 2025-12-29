package com.manoelmarquesfor.agendamento.dto;

import com.manoelmarquesfor.agendamento.models.Agendamento;

public class AgendamentoDTO {

    public static Agendamento fromCreateRequest(final AgendamentoRequestCreate dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setDescricao(dto.descricao());
        agendamento.setDataAgendamento(dto.dataAgendamento());
        return agendamento;
    }

    public static AgendamentoReponse toResponse(final Agendamento agendamento) {

        return new AgendamentoReponse(agendamento.getId(), agendamento.getDescricao(),
                agendamento.getDataAgendamento(), agendamento.getDataCriacao(),
                agendamento.getStatus());

    }

}
