package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Equipe {
    private String id;
    private String nomeEquipe;
    private String descricao;
    private List<Usuario> membros;

    public Equipe(String nomeEquipe, String descricao) {
        this.id = UUID.randomUUID().toString();
        this.nomeEquipe = nomeEquipe;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public void addMembro(Usuario membro) {
        if (membro != null && !membros.contains(membro)) {
            this.membros.add(membro);
        }
    }

    public void removeMembro(Usuario membro) {
        this.membros.remove(membro);
    }
}