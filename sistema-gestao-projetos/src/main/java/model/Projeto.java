package model;

import java.util.Date;
import java.util.UUID;

public class Projeto {
    private String id;
    private String nomeProjeto;
    private String descricao;
    private Date dataInicio;
    private Date dataFimPrevista;
    private String status;
    private Usuario gerenteResponsavel;

    public Projeto(String nomeProjeto, String descricao, Date dataInicio, Date dataFimPrevista, String status, Usuario gerenteResponsavel) {
        this.id = UUID.randomUUID().toString();
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.status = status;
        this.gerenteResponsavel = gerenteResponsavel;
    }

    // Getters
    public String getId() { return id; }
    public String getNomeProjeto() { return nomeProjeto; }
    public String getDescricao() { return descricao; }
    public Date getDataInicio() { return dataInicio; }
    public Date getDataFimPrevista() { return dataFimPrevista; }
    public String getStatus() { return status; }
    public Usuario getGerenteResponsavel() { return gerenteResponsavel; }

    // Setters
    public void setNomeProjeto(String nomeProjeto) { this.nomeProjeto = nomeProjeto; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataFimPrevista(Date dataFimPrevista) { this.dataFimPrevista = dataFimPrevista; }
    public void setStatus(String status) { this.status = status; }
    public void setGerenteResponsavel(Usuario gerenteResponsavel) { this.gerenteResponsavel = gerenteResponsavel; }
}