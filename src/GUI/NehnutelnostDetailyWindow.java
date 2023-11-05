package GUI;

import Entities.Nehnutelnost;
import Entities.Parcela;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class NehnutelnostDetailyWindow extends JFrame {
    private Nehnutelnost nehnutelnost;

    private JLabel supisneCisloLabel;
    private JLabel popisLabel;
    private JLabel minGPSLabel;
    private JLabel maxGPSLabel;

    private DefaultTableModel parcelaTableModel;
    private JTable listParcely;

    public NehnutelnostDetailyWindow(Nehnutelnost nehnutelnost) {
        this.nehnutelnost = nehnutelnost;

        setTitle("Detaily nehnuteľnosti");

        supisneCisloLabel = new JLabel("Súpisné číslo: " + nehnutelnost.getSupisneCislo());
        popisLabel = new JLabel("Popis: " + nehnutelnost.getPopis());
        minGPSLabel = new JLabel("Min GPS: " + nehnutelnost.getMinGPS().toString());
        maxGPSLabel = new JLabel("Max GPS: " + nehnutelnost.getMaxGPS().toString());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        labelPanel.add(supisneCisloLabel);
        labelPanel.add(popisLabel);
        labelPanel.add(minGPSLabel);
        labelPanel.add(maxGPSLabel);

        parcelaTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        parcelaTableModel.addColumn("Číslo parcely");
        parcelaTableModel.addColumn("Popis");
        parcelaTableModel.addColumn("GPS1");
        parcelaTableModel.addColumn("GPS2");
        listParcely = new JTable(parcelaTableModel);
        listParcely.setPreferredScrollableViewportSize(new Dimension(400, 200));

        if (this.nehnutelnost.getParcely() != null) {
            for (Parcela parcela : this.nehnutelnost.getParcely()) {
                parcelaTableModel.addRow(new Object[]{parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }
        }
        TitledBorder scrollPaneBorder = BorderFactory.createTitledBorder("Zoznam parciel");
        JScrollPane scrollPane = new JScrollPane(listParcely);
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
