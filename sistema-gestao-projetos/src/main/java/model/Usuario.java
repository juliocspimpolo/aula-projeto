package model;

public class Usuario {
    private String id; // Alterado de int para String
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private String perfil;

    // Construtor alterado para aceitar String id
    public Usuario(String id, String nomeCompleto, String cpf, String email, String cargo, String login, String senha, String perfil) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    // Getters
    public String getId() { return id; } // Retorna String
    public String getNomeCompleto() { return nomeCompleto; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getCargo() { return cargo; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public String getPerfil() { return perfil; }

    // Setters (se precisar)
    // ...
}