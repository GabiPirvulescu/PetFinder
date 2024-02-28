package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginView extends JPanel{
    JLabel welcome = new JLabel("Welcome to PetFinder!");
    JLabel login = new JLabel("Login");
    JLabel username = new JLabel("Username");
    JLabel password = new JLabel("Password");
    JButton signUp = new JButton("Sign Up");
    JButton signIn = new JButton("Sign In");
    JTextField usernameBox = new JTextField();
    JPasswordField passwordBox = new JPasswordField();
    JCheckBox showPassword=new JCheckBox();

    public JButton getSignUp() {
        return signUp;
    }

    public JButton getSignIn() {
        return signIn;
    }

    public JTextField getUsernameBox() {
        return usernameBox;
    }

    public JPasswordField getPasswordBox() {
        return passwordBox;
    }

    public JCheckBox getShowPassword() {
        return showPassword;
    }

    LoginController loginController;
    public LoginView(LoginController loginController) {
        Color colorBackground=new Color(192, 214, 223);
        JFrame frame = new JFrame("PetFinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        Font customFontWelcome = new Font("Cambria", Font.BOLD, 30);
        Font customFontLogin = new Font("Cambria", Font.BOLD, 20);
        Font customFontButton = new Font("Cambria", Font.TRUETYPE_FONT, 16);

        welcome.setFont(customFontWelcome);
        login.setFont(customFontLogin);
        username.setFont(customFontButton);
        password.setFont(customFontButton);

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6; // Span 2 columns
        gbc.anchor = GridBagConstraints.PAGE_START; // Align to the top
        gbc.insets = new Insets(5, 30, 0, 30); // Adjust top margin
        mainPanel.add(welcome, gbc);

        usernameBox.setColumns(15);
        passwordBox.setColumns(15);

        gbc.insets = new Insets(5, 30, 5, 30); // Adjust top margin
        gbc.gridy++;
        mainPanel.add(login, gbc);

        gbc.gridy++;
        mainPanel.add(new JLabel(" "), gbc); // Empty label for spacing
        gbc.gridy++;
        mainPanel.add(username, gbc);
        gbc.gridy++;
        mainPanel.add(usernameBox, gbc);

        showPassword.setBackground(colorBackground);
        JPanel passwordPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        passwordPanel.setBackground(colorBackground);
        passwordPanel.setPreferredSize(new Dimension(217,30));
        passwordPanel.add(passwordBox);
        passwordPanel.add(showPassword);

        gbc.gridy++;
        mainPanel.add(password, gbc);
        gbc.gridy++;
        mainPanel.add(passwordPanel, gbc);

        signUp.addActionListener(e->loginController.signUpListener());

        JPanel panelSign = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSign.setBackground(colorBackground);
        panelSign.add(signUp);

        signIn.addActionListener(e-> {
            try {
                loginController.signInListener();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        panelSign.add(signIn);
        showPassword.addActionListener(e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            passwordBox.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
        });

        gbc.gridy++;
        mainPanel.add(new JLabel(" "), gbc);
        gbc.gridy++;
        mainPanel.add(panelSign, gbc);
        mainPanel.setBackground(colorBackground);
        add(mainPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

}
