package repository;

import model.Tarefa;
import model.Projeto;
import model.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TarefaRepository {
    private static List<Tarefa> tarefas = new ArrayList<>();

    // Bloco estático para popular com dados de teste
    static {
        // Obter os dados de teste dos outros repositórios
        ProjetoRepository projetoRepo = new ProjetoRepository();
        UsuarioRepository usuarioRepo = new UsuarioRepository();

        // É necessário garantir que as listas de projetos e usuários não estão vazias
        Projeto projetoAlpha = null;
        if (!projetoRepo.listarTodos().isEmpty()) {
            projetoAlpha = projetoRepo.listarTodos().get(0);
        }

        Usuario colaborador = usuarioRepo.buscarPorLogin("colab");

        if (projetoAlpha != null && colaborador != null) {
            tarefas.add(new Tarefa("Planejar interface de usuário", "Criar wireframes e mockups para o app", "Em execução", new Date(), new Date(), projetoAlpha, colaborador));
            tarefas.add(new Tarefa("Configurar ambiente de desenvolvimento", "Instalar ferramentas e dependências", "Pendente", new Date(), new Date(), projetoAlpha, colaborador));
        }
    }

    /**
     * Salva uma nova tarefa na lista.
     * @param tarefa O objeto Tarefa a ser salvo.
     */
    public void salvar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }
    
    /**
     * Lista todas as tarefas disponíveis.
     * @return Uma lista de todas as tarefas.
     */
    public List<Tarefa> listarTodos() {
        return new ArrayList<>(tarefas);
    }

    /**
     * Lista as tarefas atribuídas a um usuário específico.
     * @param responsavel O usuário responsável pelas tarefas.
     * @return Uma lista de tarefas do usuário.
     */
    public List<Tarefa> listarPorResponsavel(Usuario responsavel) {
        List<Tarefa> tarefasDoUsuario = new ArrayList<>();
        for (Tarefa t : tarefas) {
            if (t.getResponsavel().getId().equals(responsavel.getId())) {
                tarefasDoUsuario.add(t);
            }
        }
        return tarefasDoUsuario;
    }
}