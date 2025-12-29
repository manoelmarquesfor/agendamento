package com.manoelmarquesfor.agendamento.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manoelmarquesfor.agendamento.models.Agendamento;

@Repository
public class AgendamentoRepositoryImpl implements AgendamentoRepositoryInterface {

    @Autowired
    Jdbi jdbi;

    public boolean existByAgendamento(LocalDateTime dataAgendamento) {
        final String sql = "SELECT COUNT(1) FROM agendamentos WHERE dt_agendamento = :dataAgendamento and status != 'CANCELADO'";

        Integer count = jdbi.withHandle(handle -> handle.createQuery(sql)
                .bind("dataAgendamento", dataAgendamento)
                .mapTo(Integer.class)
                .one());

        return count != null && count > 0;
    }

    public Optional<Agendamento> findById(UUID id) {
        final String sql = "SELECT id, descricao, dt_agendamento, dt_criado, status FROM agendamentos WHERE id = :id";

        return jdbi.withHandle(handle -> handle.createQuery(sql)
                .bind("id", id)
                .mapToBean(Agendamento.class)
                .findOne());
    }

    public List<Agendamento> findAll() {
        final String sql = "SELECT id, descricao, dt_agendamento, dt_criado, status FROM agendamentos";

        return jdbi.withHandle(handle -> handle.createQuery(sql)
                .mapToBean(Agendamento.class)
                .list());
    }

    public void save(Agendamento agendamento) {
        final String sql = """
                INSERT INTO agendamentos (descricao, dt_agendamento, dt_criado, status)
                    VALUES (:descricao, :dataAgendamento, :dataCriacao, :status) RETURNING id""";

        final UUID id = jdbi.withHandle(handle -> handle.createUpdate(sql)
                .bind("descricao", agendamento.getDescricao())
                .bind("dataAgendamento", agendamento.getDataAgendamento())
                .bind("dataCriacao", agendamento.getDataCriacao())
                .bind("status", agendamento.getStatus().toString())
                .executeAndReturnGeneratedKeys("id")
                .mapTo(UUID.class)
                .one());

        agendamento.setId(id);
    }

    @Override
    public void update(Agendamento agendamento) {
        final String sql = """
                UPDATE agendamentos
                SET descricao = :descricao,
                    dt_agendamento = :dataAgendamento,
                    status = :status
                WHERE id = :id""";

        jdbi.withHandle(handle -> handle.createUpdate(sql)
                .bind("id", agendamento.getId())
                .bind("descricao", agendamento.getDescricao())
                .bind("dataAgendamento", agendamento.getDataAgendamento())
                .bind("status", agendamento.getStatus().toString())
                .execute());
    }
}
