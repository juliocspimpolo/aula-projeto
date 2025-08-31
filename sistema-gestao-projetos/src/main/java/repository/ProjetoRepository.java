package repository;

import model.Projeto;
import model.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors; // <-- Adicione esta linha de importação

public class ProjetoRepository {
    private static List<Projeto> projetos = new ArrayList<>();

    // Bloco estático para popular com dados de teste
    static {
        // Supondo que você já tem um Gerente de Projetos no seu repositório de usuários
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        Usuario gerente = usuarioRepo.buscarPorLogin("gerente");

        if (gerente != null) {
            // A classe Date() foi usada para simplificar, mas em um projeto real, você
            // usaria a biblioteca java.time.LocalDate para datas
            projetos.add(new Projeto("Projeto Alpha", "Desenvolvimento de um novo aplicativo mobile.", new Date(), new Date(), "Em andamento", gerente));
            projetos.add(new Projeto("Projeto Beta", "Criação de um website para a empresa.", new Date(), new Date(), "Concluído", gerente));
            projetos.add(new Projeto("Projeto Gamma", "Revisão da arquitetura de software.", new Date(), new Date(), "Planejado", gerente));
        }
    }

    /**
     * Salva um novo projeto na lista.
     * @param projeto O objeto Projeto a ser salvo.
     */
    public void salvar(Projeto projeto) {
        projetos.add(projeto);
    }
    
    /**
     * Lista todos os projetos disponíveis.
     * @return Uma lista de todos os projetos.
     */
    public List<Projeto> listarTodos() {
        return new ArrayList<>(projetos);
    }
    
    /**
     * Busca um projeto pelo seu ID.
     * @param id O ID do projeto a ser buscado.
     * @return O objeto Projeto encontrado, ou null se não for encontrado.
     */
    public Projeto buscarPorId(String id) {
        for (Projeto p : projetos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Lista projetos que estão com a data de término prevista no passado.
     * @return Uma lista de projetos atrasados.
     */
    public List<Projeto> listarProjetosAtrasados() {
        Date dataAtual = new Date();
        return projetos.stream()
                .filter(p -> p.getDataFimPrevista() != null && p.getDataFimPrevista().before(dataAtual))
                .collect(Collectors.toList());
    }
}