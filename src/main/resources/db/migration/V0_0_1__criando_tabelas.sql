
CREATE TABLE IF NOT EXISTS agendamentos (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    descricao varchar(255) NOT NULL,
    status varchar(20) NOT NULL,
    dt_agendamento timestamp NOT NULL,
    dt_criado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dt_alterado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT table_agendamentos_id_key PRIMARY KEY (id)
);