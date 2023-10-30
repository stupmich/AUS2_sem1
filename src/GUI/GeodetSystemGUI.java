package GUI;

import App.SystemApp;
import Entities.Nehnutelnost;
import Entities.Parcela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GeodetSystemGUI extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JTabbedPane tabbedPane5;
    private JTextField widthCharSearchNehnutelnost;
    private JTextField lengthCharSearchNehnutelnost;
    private JTextField popisInsertNehnutelnost;
    private JTable listSearchNehnutelnost;
    private JTable table4;
    private JTable listSearchAllNehnutelnost;
    private DefaultTableModel nehnutelnostTableModel;
    private DefaultTableModel nehnutelnostAllTableModel;
    private DefaultTableModel nehnutelnostInsertTableModel;
    private DefaultTableModel parcelaTableModel;

    private JTextField tree_minX;
    private JTextField tree_minY;
    private JTextField tree_maxDepth;
    private JTextField tree_maxX;
    private JTextField tree_maxY;
    private JTextField minWidthParcela;
    private JTextField minLengthParcela;
    private JTextField maxWidthParcela;
    private JTextField maxLengthParcela;
    private JTextField numberParcela;
    private JTextField minWidthNehnutelnost;
    private JTextField minLengthNehnutelnost;
    private JTextField maxWidthNehnutelnost;
    private JTextField maxLengthNutelnost;
    private JTextField numberNehnutelnost;
    private JTextField lengthPositionSearchNehnutelnost;
    private JTextField widthPositionSearchNehnutelnost;
    private JTextField widthCharSearchParcela;
    private JTextField lengthCharSearchParcela;
    private JTextField lengthPositionSearchParcela;
    private JTextField widthPositionSearchParcela;
    private JTextField minWidthCharSearchObjekt;
    private JTextField minLengthCharSearchObjekt;
    private JTextField minLengthPositionSearchObjekt;
    private JTextField minWidthPositionSearchObjekt;
    private JTextField maxWidthCharSearchObjekt;
    private JTextField maxLengthCharSearchObjekt;
    private JTextField maxWidthPositionSearchObjekt;
    private JTextField maxLengthPositionSearchObjekt;
    private JTextField supisneCisloInsertNehnutelnost;
    private JTextField minWidthCharInsertNehnutelnost;
    private JTextField minWidthPositionInsertNehnutelnost;
    private JTextField minLengthPositionInsertNehnutelnost;
    private JTextField minLengthCharInsertNehnutelnost;
    private JTextField maxWidthCharInsertNehnutelnost;
    private JTextField maxLengthCharInsertNehnutelnost;
    private JTextField maxWidthPositionInsertNehnutelnost;
    private JTextField maxLengthPositionInsertNehnutelnost;
    private JButton fillSystemButton;
    private JButton searchNehnutelnostiButton;
    private JButton searchParcelyButton;
    private JButton searchObjectsButton;
    private JTable listSearchAllParcela;
    private JButton insertNehnutelnostButton;
    private JTable listNehnutelnostInsert;
    private JCheckBox optimalizeTreeCheckBoxInsertNehnutelnost;
    private JCheckBox optimalizeTreeCheckBoxFillSystem;


    private SystemApp app;

    public GeodetSystemGUI(){
        this.setContentPane(mainPanel);
        this.setTitle("GeoSystem");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.app = new SystemApp();

        nehnutelnostTableModel = new DefaultTableModel();
        nehnutelnostTableModel.addColumn("Cislo");
        nehnutelnostTableModel.addColumn("Popis");
        nehnutelnostTableModel.addColumn("GPS1");
        nehnutelnostTableModel.addColumn("GPS2");
        listSearchNehnutelnost.setModel(nehnutelnostTableModel);

        nehnutelnostAllTableModel = new DefaultTableModel();
        nehnutelnostAllTableModel.addColumn("Cislo");
        nehnutelnostAllTableModel.addColumn("Popis");
        nehnutelnostAllTableModel.addColumn("GPS1");
        nehnutelnostAllTableModel.addColumn("GPS2");
        listSearchAllNehnutelnost.setModel(nehnutelnostAllTableModel);

        nehnutelnostInsertTableModel = new DefaultTableModel();
        nehnutelnostInsertTableModel.addColumn("Cislo");
        nehnutelnostInsertTableModel.addColumn("Popis");
        nehnutelnostInsertTableModel.addColumn("GPS1");
        nehnutelnostInsertTableModel.addColumn("GPS2");
        listNehnutelnostInsert.setModel(nehnutelnostInsertTableModel);

        parcelaTableModel = new DefaultTableModel();
        parcelaTableModel.addColumn("Cislo");
        parcelaTableModel.addColumn("Popis");
        parcelaTableModel.addColumn("GPS1");
        parcelaTableModel.addColumn("GPS2");
        listSearchAllParcela.setModel(parcelaTableModel);

        fillSystemButton.addActionListener(this);
        searchNehnutelnostiButton.addActionListener(this);
        searchObjectsButton.addActionListener(this);
        insertNehnutelnostButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fillSystemButton) {
            this.app.fillSystem( Double.parseDouble(tree_minX.getText()), Double.parseDouble(tree_minY.getText()),
                                 Double.parseDouble(tree_maxX.getText()), Double.parseDouble(tree_maxY.getText()), Integer.parseInt(tree_maxDepth.getText()),
                                 Integer.parseInt(numberNehnutelnost.getText()), Integer.parseInt(numberParcela.getText()),
                                 Double.parseDouble(minWidthNehnutelnost.getText()), Double.parseDouble(minLengthNehnutelnost.getText()),
                                 Double.parseDouble(maxWidthNehnutelnost.getText()), Double.parseDouble(maxLengthNutelnost.getText()),
                                 Double.parseDouble(minWidthParcela.getText()), Double.parseDouble(minLengthParcela.getText()),
                                 Double.parseDouble(maxWidthParcela.getText()), Double.parseDouble(maxLengthParcela.getText()), optimalizeTreeCheckBoxFillSystem.isSelected());

            JOptionPane.showMessageDialog(null,"Systém bol naplnený.");

        } else if (e.getSource() == searchNehnutelnostiButton) {
            LinkedList<Nehnutelnost> nehnutelnosti = this.app.searchNehnutelnost(widthCharSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(widthPositionSearchNehnutelnost.getText()) ,
                                                                                    lengthCharSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(lengthPositionSearchNehnutelnost.getText()));

            nehnutelnostTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostTableModel.addRow(new Object[]{ nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }

        } else if (e.getSource() == searchObjectsButton) {
            LinkedList<Nehnutelnost> nehnutelnosti = this.app.searchNehnutelnostArea(minWidthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(minWidthPositionSearchObjekt.getText()),
                                                                                     minLengthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(minLengthPositionSearchObjekt.getText()),
                                                                                     maxWidthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(maxWidthPositionSearchObjekt.getText()),
                                                                                     maxLengthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(maxLengthPositionSearchObjekt.getText())
            );

            LinkedList<Parcela> parcely = this.app.searchParcelaArea(minWidthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(minWidthPositionSearchObjekt.getText()),
                                                                     minLengthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(minLengthPositionSearchObjekt.getText()),
                                                                     maxWidthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(maxWidthPositionSearchObjekt.getText()),
                                                                     maxLengthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(maxLengthPositionSearchObjekt.getText())
            );

            nehnutelnostAllTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostAllTableModel.addRow(new Object[]{ nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }

            parcelaTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                parcelaTableModel.addRow(new Object[]{ parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }

        } else if (e.getSource() == insertNehnutelnostButton) {
            Nehnutelnost nehnutelnost = app.insertNehnutelnost( minWidthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(minWidthPositionInsertNehnutelnost.getText()),
                                                                minLengthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(minLengthPositionInsertNehnutelnost.getText()),
                                                                maxWidthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(maxWidthPositionInsertNehnutelnost.getText()),
                                                                maxLengthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(maxLengthPositionInsertNehnutelnost.getText()),
                                                                Integer.parseInt(supisneCisloInsertNehnutelnost.getText()), popisInsertNehnutelnost.getText(), optimalizeTreeCheckBoxInsertNehnutelnost.isSelected());

            nehnutelnostInsertTableModel.addRow(new Object[]{ nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
        }
    }
}
