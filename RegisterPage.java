package registerPage;

import javax.swing.*;
import java.awt.*;
import userDAO.UserDAO;
import user.User;
import loginPage.LoginPage;

public class RegisterPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backButton;
    private UserDAO userDAO;
    private LoginPage loginPage;
    
    public RegisterPage(LoginPage loginPage) {
        this.loginPage = loginPage;
        userDAO = new UserDAO();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Register New Account");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        
        // Username
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField = new JTextField(20);
        mainPanel.add(usernameField, gbc);
        
        // Email
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        emailField = new JTextField(20);
        mainPanel.add(emailField, gbc);
        
        // Password
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);
        
        // Confirm Password
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(new JLabel("Confirm Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        confirmPasswordField = new JPasswordField(20);
        mainPanel.add(confirmPasswordField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(240, 240, 245));
        
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(50, 150, 50));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        
        backButton = new JButton("Back to Login");
        backButton.setBackground(new Color(150, 150, 150));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel);
        
        // Action Listeners
        registerButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> backToLogin());
    }
    
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (userDAO.isUserExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = new User(username, password, email);
        if (userDAO.registerUser(user)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            backToLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed! Please try again.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void backToLogin() {
        loginPage.setVisible(true);
        this.dispose();
    }
}