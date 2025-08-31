-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS gestao_projetos;

-- Usa o banco de dados recém-criado
USE gestao_projetos;

-- Tabela para os perfis de acesso (Administrador, Gerente, Colaborador)
CREATE TABLE perfis (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_perfil VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela para os usuários
CREATE TABLE usuarios (
    id VARCHAR(36) PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    cargo VARCHAR(100),
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil_id INT NOT NULL,
    FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);

-- Tabela para as equipes
CREATE TABLE equipes (
    id VARCHAR(36) PRIMARY KEY,
    nome_equipe VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT
);

-- Tabela para os projetos
CREATE TABLE projetos (
    id VARCHAR(36) PRIMARY KEY,
    nome_projeto VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_inicio DATE,
    data_fim_prevista DATE,
    status VARCHAR(50) NOT NULL DEFAULT 'Planejado',
    gerente_id VARCHAR(36),
    FOREIGN KEY (gerente_id) REFERENCES usuarios(id)
);

-- Tabela de junção para alocar membros a equipes (N:N)
CREATE TABLE membros_equipe (
    equipe_id VARCHAR(36) NOT NULL,
    usuario_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (equipe_id, usuario_id),
    FOREIGN KEY (equipe_id) REFERENCES equipes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabela de junção para alocar equipes a projetos (N:N)
CREATE TABLE equipes_projeto (
    equipe_id VARCHAR(36) NOT NULL,
    projeto_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (equipe_id, projeto_id),
    FOREIGN KEY (equipe_id) REFERENCES equipes(id),
    FOREIGN KEY (projeto_id) REFERENCES projetos(id)
);

-- Tabela para as tarefas
CREATE TABLE tarefas (
    id VARCHAR(36) PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'Pendente',
    data_inicio_prevista DATE,
    data_fim_prevista DATE,
    data_inicio_real DATE,
    data_fim_real DATE,
    projeto_id VARCHAR(36) NOT NULL,
    responsavel_id VARCHAR(36),
    FOREIGN KEY (projeto_id) REFERENCES projetos(id),
    FOREIGN KEY (responsavel_id) REFERENCES usuarios(id)
);

-- Comandos de inserção para perfis padrão
INSERT INTO perfis (nome_perfil) VALUES ('Administrador'), ('Gerente'), ('Colaborador');