package com.manoelmarquesfor.agendamento.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import com.manoelmarquesfor.agendamento.models.enums.StatusEnum;

public class Agendamento {

    private UUID id;
    private String descricao;
    private LocalDateTime dataAgendamento;
    private LocalDateTime dataCriacao;
    private StatusEnum status;

    public Agendamento() {
    }

    public Agendamento(UUID id, String descricao, LocalDateTime dataAgendamento, LocalDateTime dataCriacao,
            StatusEnum status) {
        this.id = id;
        this.descricao = descricao;
        this.dataAgendamento = dataAgendamento;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    @ColumnName("dt_agendamento")
    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    @ColumnName("dt_criado")
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agendamento [id=" + id + ", descricao=" + descricao + ", dataAgendamento=" + dataAgendamento
                + ", dataCriacao=" + dataCriacao + ", status=" + status + "]";
    }
}
