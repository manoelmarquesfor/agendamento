package com.manoelmarquesfor.agendamento.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoelmarquesfor.agendamento.dto.AgendamentoDTO;
import com.manoelmarquesfor.agendamento.dto.AgendamentoReponse;
import com.manoelmarquesfor.agendamento.dto.AgendamentoRequestCreate;
import com.manoelmarquesfor.agendamento.repository.AgendamentoRepositoryImpl;
import com.manoelmarquesfor.agendamento.service.AgendamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

        private AgendamentoService agendamentoService;

        public AgendamentoController(AgendamentoRepositoryImpl agendamentoRepository) {
                this.agendamentoService = new AgendamentoService(agendamentoRepository);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<AgendamentoReponse> getAgendamento(@PathVariable("id") UUID id) {
                return ResponseEntity.ok(agendamentoService.getAgendamentoById(id));
        }

        @GetMapping("/")
        public ResponseEntity<List<AgendamentoReponse>> getAllAgendamentos() {
                return ResponseEntity.ok(agendamentoService.getAllAgendamentos());
        }

        @PostMapping("/")
        public ResponseEntity<AgendamentoReponse> createAgendamento(
                        @RequestBody @Valid AgendamentoRequestCreate request) {
                return ResponseEntity.ok(
                                agendamentoService.createAgendamento(AgendamentoDTO.fromCreateRequest(request)));
        }

        @PutMapping(value = "/{id}")
        public ResponseEntity<AgendamentoReponse> concluirAgendamento(@PathVariable UUID id) {
                return ResponseEntity.ok(
                                agendamentoService.concluirAgendamento(id));
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<AgendamentoReponse> cancelarAgendamento(@PathVariable UUID id) {
                return ResponseEntity.ok(
                                agendamentoService.cancelarAgendamento(id));
        }

}
