package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GeodetSystemGUI extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JTabbedPane tabbedPane5;
    private JTextField textFieldReplications;
    private JTextField textFieldWorkers2;
    private JTextField textFieldWorkers1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTable table4;

    public GeodetSystemGUI(){
        this.setContentPane(mainPanel);
        this.setTitle("GeoSystem");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
