package com.manoelmarquesfor.agendamento.service;

import java.util.List;
import java.util.UUID;

import com.manoelmarquesfor.agendamento.dto.AgendamentoDTO;
import com.manoelmarquesfor.agendamento.dto.AgendamentoReponse;
import com.manoelmarquesfor.agendamento.models.Agendamento;
import com.manoelmarquesfor.agendamento.models.enums.StatusEnum;
import com.manoelmarquesfor.agendamento.repository.AgendamentoRepositoryInterface;
import com.manoelmarquesfor.agendamento.service.exceptions.NotFoundException;
import com.manoelmarquesfor.agendamento.service.exceptions.ValidacaoException;

public class AgendamentoService {

    private final AgendamentoRepositoryInterface agendamentoRepository;

    public AgendamentoService(AgendamentoRepositoryInterface agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    private Agendamento findById(UUID id) {
        return agendamentoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado."));
    }

    public AgendamentoReponse getAgendamentoById(UUID id) {
        final Agendamento agendamento = this.findById(id);
        return AgendamentoDTO.toResponse(agendamento);
    }

    public AgendamentoReponse createAgendamento(Agendamento agendamento) {
        if (agendamentoRepository.existByAgendamento(agendamento.getDataAgendamento())) {
            throw new ValidacaoException("Já existe um agendamento para essa data e hora.");
        }

        agendamento.setStatus(StatusEnum.AGENDADO);
        agendamento.setDataCriacao(java.time.LocalDateTime.now());
        agendamentoRepository.save(agendamento);
        return AgendamentoDTO.toResponse(agendamento);
    }

    public List<AgendamentoReponse> getAllAgendamentos() {
        final List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return agendamentos.stream()
                .map(AgendamentoDTO::toResponse)
                .toList();
    }

    public AgendamentoReponse concluirAgendamento(UUID id) {
        final Agendamento agendamento = this.findById(id);

        if (agendamento.getStatus() == StatusEnum.CANCELADO) {
            throw new ValidacaoException("Não é possível concluir um agendamento cancelado.");
        }

        agendamento.setStatus(StatusEnum.CONCLUIDO);
        agendamentoRepository.update(agendamento);
        return AgendamentoDTO.toResponse(agendamento);
    }

    public AgendamentoReponse cancelarAgendamento(UUID id) {
        final Agendamento agendamento = this.findById(id);

        switch (agendamento.getStatus()) {
            case CANCELADO:
                throw new ValidacaoException("Agendamento já está cancelado.");
            case CONCLUIDO:
                throw new ValidacaoException("Não é possível cancelar um agendamento concluído.");
            default:
                break;
        }

        agendamento.setStatus(StatusEnum.CANCELADO);
        agendamentoRepository.update(agendamento);
        return AgendamentoDTO.toResponse(agendamento);
    }
}
