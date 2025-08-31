package repository;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioRepository {
    private static List<Usuario> usuarios = new ArrayList<>();

    // Bloco estático para popular com dados de teste
    static {
        // Criando alguns usuários de teste
        usuarios.add(new Usuario(UUID.randomUUID().toString(), "Administrador do Sistema", "111.111.111-11", "admin@email.com", "CEO", "admin", "admin123", "Administrador"));
        usuarios.add(new Usuario(UUID.randomUUID().toString(), "Gerente de Projetos", "222.222.222-22", "gerente@email.com", "Gerente", "gerente", "gerente123", "Gerente"));
        usuarios.add(new Usuario(UUID.randomUUID().toString(), "Colaborador Comum", "333.333.333-33", "colaborador@email.com", "Desenvolvedor", "colab", "colab123", "Colaborador"));
    }

    public void salvar(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public Usuario buscarPorLogin(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }
    
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
}