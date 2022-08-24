package bcd.gui;

import bcd.app.CreateRecord;
import bcd.client.ManagementUser;
import bcd.client.User;
import bcd.config.GeneralOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JButton createRecordButton;
    private JButton viewRecordButton;
    private JButton logOut;

    public HomePage(User user){
        setContentPane(panel1);
        setTitle("Home Page");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(GeneralOperation.getWindow_width(),GeneralOperation.getWindow_height());
        setVisible(true);
        if(!(user instanceof ManagementUser))
            createRecordButton.setVisible(false);
        logOut.addActionListener(e -> {
            LoginPage lp = new LoginPage();
            dispose();
        });
        viewRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecordHomepage rp = new RecordHomepage(user);
                dispose();
            }
        });
        createRecordButton.addActionListener(e -> CreateRecord.createRecord());
    }
}
