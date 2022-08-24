package bcd.gui;

import bcd.client.User;
import bcd.config.GeneralOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton loginPageButton;
    private JTextField textField1;
    private JButton registerButton;
    public Register(){
        setContentPane(panel1);
        setTitle("Register");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(GeneralOperation.getWindow_width(),GeneralOperation.getWindow_height());
        setVisible(true);
        loginPageButton.addActionListener(e -> {
            LoginPage lp = new LoginPage();
            dispose();
        });
        registerButton.addActionListener(e -> {
            User u = new User();
            if(textField1.getText().trim().isBlank())
                JOptionPane.showMessageDialog(this,"Please enter your email to register");
            else {
                u.register(textField1.getText());
                LoginPage lp = new LoginPage();
                dispose();
            }
        });
    }
}
