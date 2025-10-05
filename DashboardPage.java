package dashboardPage;

import javax.swing.*;
import java.awt.*;
import loginPage.LoginPage;

public class DashboardPage extends JFrame {
    
    public DashboardPage(String username) {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 245));
        
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(50, 50, 150));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(200, 50, 50));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> logout());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 245));
        buttonPanel.add(logoutButton);
        
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Logout", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            new LoginPage().setVisible(true);
            this.dispose();
        }
    }
}