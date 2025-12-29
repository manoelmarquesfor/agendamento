package com.manoelmarquesfor.agendamento.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.manoelmarquesfor.agendamento.models.Agendamento;

public interface AgendamentoRepositoryInterface {
    public boolean existByAgendamento(LocalDateTime dataAgendamento);

    public Optional<Agendamento> findById(UUID id);

    public List<Agendamento> findAll();

    public void save(Agendamento agendamento);

    public void update(Agendamento agendamento);

}
