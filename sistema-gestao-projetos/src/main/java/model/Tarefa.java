package model;

import java.util.Date;
import java.util.UUID;

public class Tarefa {
    private String id;
    private String titulo;
    private String descricao;
    private String status; // Ex: "Pendente", "Em execução", "Concluída"
    private Date dataInicioPrevista;
    private Date dataFimPrevista;
    private Date dataInicioReal;
    private Date dataFimReal;
    private Projeto projetoVinculado;
    private Usuario responsavel;

    public Tarefa(String titulo, String descricao, String status, Date dataInicioPrevista, Date dataFimPrevista, Projeto projetoVinculado, Usuario responsavel) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataFimPrevista = dataFimPrevista;
        this.projetoVinculado = projetoVinculado;
        this.responsavel = responsavel;
    }
    
    // Getters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getStatus() { return status; }
    public Date getDataInicioPrevista() { return dataInicioPrevista; }
    public Date getDataFimPrevista() { return dataFimPrevista; }
    public Date getDataInicioReal() { return dataInicioReal; }
    public Date getDataFimReal() { return dataFimReal; }
    public Projeto getProjetoVinculado() { return projetoVinculado; }
    public Usuario getResponsavel() { return responsavel; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setDataInicioReal(Date dataInicioReal) { this.dataInicioReal = dataInicioReal; }
    public void setDataFimReal(Date dataFimReal) { this.dataFimReal = dataFimReal; }
}