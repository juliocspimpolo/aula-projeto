package view;

import model.Usuario;
import model.Projeto;
import model.Tarefa;
import model.Equipe;
import repository.ProjetoRepository;
import repository.TarefaRepository;
import repository.EquipeRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat; 

public class DashboardView extends JFrame {

    private Usuario usuarioLogado;
    private JPanel mainPanel;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public DashboardView(Usuario usuario) {
        this.usuarioLogado = usuario;

        setTitle("Sistema de Gestão de Projetos - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bem-vindo, " + usuario.getNomeCompleto() + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        configureDashboard();

        setVisible(true);
    }

    private void configureDashboard() {
        String perfil = usuarioLogado.getPerfil();
        
        switch (perfil) {
            case "Administrador":
                showAdminDashboard();
                break;
            case "Gerente":
                showManagerDashboard();
                break;
            case "Colaborador":
                showCollaboratorDashboard();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Perfil não reconhecido.");
                break;
        }
    }

    private void showAdminDashboard() {
        mainPanel.removeAll();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Projetos", createProjectsPanel());
        tabbedPane.addTab("Equipes", createEquipesPanel());
        tabbedPane.addTab("Relatórios", createReportsPanel());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showManagerDashboard() {
        mainPanel.removeAll();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Projetos", createProjectsPanel());
        tabbedPane.addTab("Equipes", createEquipesPanel());
        tabbedPane.addTab("Relatórios", createReportsPanel());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showCollaboratorDashboard() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Minhas Tarefas", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(title, BorderLayout.NORTH);

        String[] colunas = {"Título", "Projeto", "Status", "Data Início Prevista", "Data Fim Prevista"};
        TarefaRepository tarefaRepo = new TarefaRepository();
        List<Tarefa> minhasTarefas = tarefaRepo.listarPorResponsavel(usuarioLogado);
        
        Object[][] dados = new Object[minhasTarefas.size()][5];
        for (int i = 0; i < minhasTarefas.size(); i++) {
            Tarefa t = minhasTarefas.get(i);
            dados[i][0] = t.getTitulo();
            dados[i][1] = t.getProjetoVinculado().getNomeProjeto();
            dados[i][2] = t.getStatus();
            dados[i][3] = DATE_FORMAT.format(t.getDataInicioPrevista());
            dados[i][4] = DATE_FORMAT.format(t.getDataFimPrevista());
        }

        JTable tabelaTarefas = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaTarefas);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    // Novo método para criar o painel de Equipes
    private JPanel createEquipesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] colunas = {"Nome da Equipe", "Descrição", "Membros"};
        EquipeRepository equipeRepo = new EquipeRepository();
        List<Equipe> listaEquipes = equipeRepo.listarTodos();
        
        Object[][] dados = new Object[listaEquipes.size()][3];
        for (int i = 0; i < listaEquipes.size(); i++) {
            Equipe e = listaEquipes.get(i);
            String membros = "";
            for(Usuario u : e.getMembros()) {
                membros += u.getNomeCompleto() + ", ";
            }
            if (!membros.isEmpty()) {
                membros = membros.substring(0, membros.length() - 2); // Remove a última vírgula
            }
            dados[i][0] = e.getNomeEquipe();
            dados[i][1] = e.getDescricao();
            dados[i][2] = membros;
        }

        JTable tabelaEquipes = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaEquipes);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createProjectsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] colunas = {"Nome do Projeto", "Status", "Gerente"};
        ProjetoRepository projetoRepo = new ProjetoRepository();
        List<Projeto> listaProjetos = projetoRepo.listarTodos();
        
        Object[][] dados = new Object[listaProjetos.size()][3];
        for (int i = 0; i < listaProjetos.size(); i++) {
            Projeto p = listaProjetos.get(i);
            dados[i][0] = p.getNomeProjeto();
            dados[i][1] = p.getStatus();
            dados[i][2] = p.getGerenteResponsavel().getNomeCompleto();
        }

        JTable tabelaProjetos = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaProjetos);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Relatórios do Sistema", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel reportsContent = new JPanel();
        reportsContent.setLayout(new BoxLayout(reportsContent, BoxLayout.Y_AXIS));
        reportsContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel atrasadosTitle = new JLabel("Projetos com Risco de Atraso");
        atrasadosTitle.setFont(new Font("Arial", Font.BOLD, 14));
        reportsContent.add(atrasadosTitle);

        ProjetoRepository projetoRepo = new ProjetoRepository();
        List<Projeto> projetosAtrasados = projetoRepo.listarProjetosAtrasados();

        if (projetosAtrasados.isEmpty()) {
            reportsContent.add(new JLabel("Nenhum projeto atrasado encontrado."));
        } else {
            for (Projeto p : projetosAtrasados) {
                reportsContent.add(new JLabel("- " + p.getNomeProjeto() + " (Gerente: " + p.getGerenteResponsavel().getNomeCompleto() + ")"));
            }
        }
        
        panel.add(reportsContent, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        // Não é necessário executar esta classe diretamente, a chamada vem do LoginView
    }
}