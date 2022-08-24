package bcd.gui;

import bcd.app.ViewRecord;
import bcd.client.ManagementUser;
import bcd.client.User;
import bcd.config.GeneralOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordHomepage extends JFrame{
    private JTabbedPane tabbedPane1;
    private JTextField textField3;
    private JButton homePageButton;
    private JPanel panel1;
    private JButton searchButton;

    public RecordHomepage(User u) {
        setContentPane(panel1);
        setTitle("View Record");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(GeneralOperation.getWindow_width(),GeneralOperation.getWindow_height());
        setVisible(true);
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage hp = new HomePage(u);
                dispose();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField3.getText().trim().isBlank())
                    ViewRecord.findRecord(textField3.getText());
                else
                    JOptionPane.showMessageDialog(panel1,"Please enter Student ID to search.\nFormat is (ID).");
            }
        });
    }
}
