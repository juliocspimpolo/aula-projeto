package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import repository.UsuarioRepository;
import model.Usuario;

public class LoginView extends JFrame {

    private JTextField loginField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginView() {
        // Configurações da Janela
        setTitle("Sistema de Gestão de Projetos - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel para o formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Componentes do Formulário
        JLabel loginLabel = new JLabel("Login:");
        loginField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();
        
        loginButton = new JButton("Entrar");

        // Adicionando componentes ao painel do formulário
        formPanel.add(loginLabel);
        formPanel.add(loginField);
        formPanel.add(senhaLabel);
        formPanel.add(senhaField);
        formPanel.add(new JLabel()); // Espaço vazio para layout
        formPanel.add(loginButton);

        // Label para mensagens de status
        statusLabel = new JLabel("");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        // Adicionando painéis à janela principal
        add(new JLabel("Acesso ao Sistema", SwingConstants.CENTER), BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Adicionando o evento ao botão de login
        loginButton.addActionListener(this::onLoginClick);
    }

    private void onLoginClick(ActionEvent e) {
        String login = loginField.getText();
        String senha = new String(senhaField.getPassword());
        
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Usuario usuario = usuarioRepository.buscarPorLogin(login);
        
        if (usuario != null && usuario.getSenha().equals(senha)) {
            statusLabel.setText("Login bem-sucedido! Bem-vindo, " + usuario.getNomeCompleto() + ".");
            // Aqui você pode abrir a tela principal do sistema
            new DashboardView(usuario); // Abre a nova tela
	    this.dispose(); // Fecha a tela de login
            // TO-DO: Chamar a classe da próxima tela aqui
        } else {
            statusLabel.setText("Login ou senha incorretos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}