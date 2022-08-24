package bcd.gui;

import bcd.client.ManagementUser;
import bcd.client.User;
import bcd.config.GeneralOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class LoginPage extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton loginButton;
    private JButton signUpButton;
    private User u = new User();
    public LoginPage(){
        setContentPane(panel1);
        setTitle("Login");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(GeneralOperation.getWindow_width(),GeneralOperation.getWindow_height());
        setVisible(true);
        AtomicBoolean iscorrect = new AtomicBoolean(false);
        AtomicReference<String> email = new AtomicReference<>();
        loginButton.addActionListener(e -> {
            if(!textField1.getText().trim().isBlank()){
                if(!iscorrect.get()) {
                    email.set(textField1.getText());
                    iscorrect.set(u.login(textField1.getText(),textField1));
                }else{
                    if(u.verify(email.get(), textField1)) {
                        JOptionPane.showMessageDialog(panel1, "Login successful. Welcome back.\n" + email.get());
                        dispose();
                        if (email.get().startsWith("admin")) { HomePage hp = new HomePage(u = new ManagementUser());}
                        else { HomePage hp = new HomePage(u);}
                    }
                }
            }else
                JOptionPane.showMessageDialog(panel1, "Please enter your email to continue.");
        });
        signUpButton.addActionListener(e -> {
            Register r = new Register();
            dispose();
        });
    }
}
