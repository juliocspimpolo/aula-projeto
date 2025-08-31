package repository;

import model.Equipe;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EquipeRepository {
    private static List<Equipe> equipes = new ArrayList<>();

    // Bloco estático para popular com dados de teste
    static {
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        Usuario gerente = usuarioRepo.buscarPorLogin("gerente");
        Usuario colaborador = usuarioRepo.buscarPorLogin("colab");

        if (gerente != null && colaborador != null) {
            Equipe equipeAlpha = new Equipe("Equipe Alpha", "Equipe de desenvolvimento back-end.");
            equipeAlpha.addMembro(gerente);
            equipeAlpha.addMembro(colaborador);
            equipes.add(equipeAlpha);

            Equipe equipeBeta = new Equipe("Equipe Beta", "Equipe de design de interfaces.");
            equipeBeta.addMembro(colaborador);
            equipes.add(equipeBeta);
        }
    }

    public void salvar(Equipe equipe) {
        equipes.add(equipe);
    }

    public List<Equipe> listarTodos() {
        return new ArrayList<>(equipes);
    }

    public Equipe buscarPorId(String id) {
        for (Equipe e : equipes) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    // Você pode adicionar métodos para editar e deletar equipes aqui.
}