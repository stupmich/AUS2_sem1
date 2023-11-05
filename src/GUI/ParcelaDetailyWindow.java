package GUI;

import Entities.Nehnutelnost;
import Entities.Parcela;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ParcelaDetailyWindow extends JFrame {
    private Parcela parcela;

    private JLabel supisneCisloLabel;
    private JLabel popisLabel;
    private JLabel minGPSLabel;
    private JLabel maxGPSLabel;

    private DefaultTableModel nehnutelnostTableModel;
    private JTable listNehnutelnosti;

    public ParcelaDetailyWindow(Parcela parcela) {
        this.parcela = parcela;

        setTitle("Detaily parcely");

        supisneCisloLabel = new JLabel("Číslo parcely: " + parcela.getSupisneCislo());
        popisLabel = new JLabel("Popis: " + parcela.getPopis());
        minGPSLabel = new JLabel("Min GPS: " + parcela.getMinGPS().toString());
        maxGPSLabel = new JLabel("Max GPS: " + parcela.getMaxGPS().toString());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        labelPanel.add(supisneCisloLabel);
        labelPanel.add(popisLabel);
        labelPanel.add(minGPSLabel);
        labelPanel.add(maxGPSLabel);

        nehnutelnostTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        nehnutelnostTableModel.addColumn("Súpisné číslo");
        nehnutelnostTableModel.addColumn("Popis");
        nehnutelnostTableModel.addColumn("GPS1");
        nehnutelnostTableModel.addColumn("GPS2");
        listNehnutelnosti= new JTable(nehnutelnostTableModel);
        listNehnutelnosti.setPreferredScrollableViewportSize(new Dimension(400, 200));

        if (this.parcela.getNehnutelnosti() != null) {
            for (Nehnutelnost nehnutelnost : this.parcela.getNehnutelnosti()) {
                nehnutelnostTableModel.addRow(new Object[]{nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }
        }
        TitledBorder scrollPaneBorder = BorderFactory.createTitledBorder("Zoznam nehnuteľností");
        JScrollPane scrollPane = new JScrollPane(listNehnutelnosti);
        scrollPane.setBorder(scrollPaneBorder);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPanel, BorderLayout.NORTH);
        contentPane.add(tablePanel, BorderLayout.CENTER);

        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
