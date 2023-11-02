package GUI;

import App.SystemApp;
import Entities.Nehnutelnost;
import Entities.Parcela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.LinkedList;

public class GeodetSystemGUI extends JFrame implements ActionListener, MouseListener {

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
    private JTable listSearchParcela;
    private JTable listSearchAllNehnutelnost;
    private DefaultTableModel nehnutelnostTableModel;
    private DefaultTableModel nehnutelnostAllTableModel;
    private DefaultTableModel nehnutelnostInsertTableModel;
    private DefaultTableModel parcelaInsertTableModel;
    private DefaultTableModel parcelaSearchTableModel;
    private DefaultTableModel parcelaAllTableModel;
    private DefaultTableModel nehnutelnostDeleteTableModel;
    private DefaultTableModel parcelaDeleteTableModel;
    private DefaultTableModel nehnutelnostEditTableModel;

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
    private JButton insertParcelaButton;
    private JTextField minWidthCharInsertParcela;
    private JTextField minWidthPositionInsertParcela;
    private JTextField minLengthCharInsertParcela;
    private JTextField minLengthPositionInsertParcela;
    private JTextField maxWidthCharInsertParcela;
    private JTextField maxWidthPositionInsertParcela;
    private JTextField maxLengthCharInsertParcela;
    private JTextField maxLengthPositionInsertParcela;
    private JTextField supisneCisloInsertParcela;
    private JTextField popisInsertParcela;
    private JCheckBox optimalizeTreeCheckBoxInsertParcela;
    private JTable listParcelaInsert;
    private JButton deleteNehnutelnostButton;
    private JButton deleteNehnutelnostiSearchButton;
    private JTable listDeleteNehnutelnost;
    private JTextField widthCharDeleteNehnutelnost;
    private JTextField lengthCharDeleteNehnutelnost;
    private JTextField widthPositionDeleteNehnutelnost;
    private JTextField lengthPositionDeleteNehnutelnost;
    private JTable listDeleteParcela;
    private JButton deleteParcelySearchButton;
    private JButton deleteParcelaButton;
    private JTextField widthCharDeleteParcela;
    private JTextField lengthCharDeleteParcela;
    private JTextField widthPositionDeleteParcela;
    private JTextField lengthPositionDeleteParcela;
    private JButton editNehnutelnostiSearchButton;
    private JTextField widthCharEditSearchNehnutelnost;
    private JTextField lengthCharEditSearchNehnutelnost;
    private JTextField widthPositionEditSearchNehnutelnost;
    private JTextField lengthPositionEditSearchNehnutelnost;
    private JTable listEditNehnutelnost;
    private JTextField supisneCisloEditNehnutelnost;
    private JTextField popisEditNehnutelnost;
    private JTextField minWidthCharEditNehnutelnost;
    private JTextField maxWidthCharEditNehnutelnost;
    private JTextField minLengthCharEditNehnutelnost;
    private JTextField maxLengthCharEditNehnutelnost;
    private JTextField minWidthPositionEditNehnutelnost;
    private JTextField maxWidthPositionEditNehnutelnost;
    private JTextField minLengthPositionEditNehnutelnost;
    private JTextField maxLengthPositionEditNehnutelnost;
    private JButton editNehnutelnostiEditButton;


    private SystemApp app;
    LinkedList<Nehnutelnost> nehnutelnosti;
    LinkedList<Parcela> parcely;
    Nehnutelnost nehnutelnost_to_edit;

    public GeodetSystemGUI(){
        this.setContentPane(mainPanel);
        this.setTitle("GeoSystem");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.app = new SystemApp();

        nehnutelnosti = new LinkedList<Nehnutelnost>();
        parcely = new LinkedList<Parcela>();

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

        parcelaInsertTableModel = new DefaultTableModel();
        parcelaInsertTableModel.addColumn("Cislo");
        parcelaInsertTableModel.addColumn("Popis");
        parcelaInsertTableModel.addColumn("GPS1");
        parcelaInsertTableModel.addColumn("GPS2");
        listParcelaInsert.setModel(parcelaInsertTableModel);

        parcelaAllTableModel = new DefaultTableModel();
        parcelaAllTableModel.addColumn("Cislo");
        parcelaAllTableModel.addColumn("Popis");
        parcelaAllTableModel.addColumn("GPS1");
        parcelaAllTableModel.addColumn("GPS2");
        listSearchAllParcela.setModel(parcelaAllTableModel);

        parcelaSearchTableModel = new DefaultTableModel();
        parcelaSearchTableModel.addColumn("Cislo");
        parcelaSearchTableModel.addColumn("Popis");
        parcelaSearchTableModel.addColumn("GPS1");
        parcelaSearchTableModel.addColumn("GPS2");
        listSearchParcela.setModel(parcelaSearchTableModel);

        nehnutelnostDeleteTableModel = new DefaultTableModel();
        nehnutelnostDeleteTableModel.addColumn("Cislo");
        nehnutelnostDeleteTableModel.addColumn("Popis");
        nehnutelnostDeleteTableModel.addColumn("GPS1");
        nehnutelnostDeleteTableModel.addColumn("GPS2");
        listDeleteNehnutelnost.setModel(nehnutelnostDeleteTableModel);

        parcelaDeleteTableModel = new DefaultTableModel();
        parcelaDeleteTableModel.addColumn("Cislo");
        parcelaDeleteTableModel.addColumn("Popis");
        parcelaDeleteTableModel.addColumn("GPS1");
        parcelaDeleteTableModel.addColumn("GPS2");
        listDeleteParcela.setModel(parcelaDeleteTableModel);

        nehnutelnostEditTableModel = new DefaultTableModel();
        nehnutelnostEditTableModel.addColumn("Cislo");
        nehnutelnostEditTableModel.addColumn("Popis");
        nehnutelnostEditTableModel.addColumn("GPS1");
        nehnutelnostEditTableModel.addColumn("GPS2");
        listEditNehnutelnost.setModel(nehnutelnostEditTableModel);

        fillSystemButton.addActionListener(this);

        searchNehnutelnostiButton.addActionListener(this);
        searchParcelyButton.addActionListener(this);
        searchObjectsButton.addActionListener(this);

        insertNehnutelnostButton.addActionListener(this);
        insertParcelaButton.addActionListener(this);

        deleteNehnutelnostiSearchButton.addActionListener(this);
        deleteParcelySearchButton.addActionListener(this);
        deleteNehnutelnostButton.addActionListener(this);
        deleteParcelaButton.addActionListener(this);

        editNehnutelnostiSearchButton.addActionListener(this);
        editNehnutelnostiEditButton.addActionListener(this);


        listEditNehnutelnost.addMouseListener(this);
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
            nehnutelnosti = this.app.searchNehnutelnost(widthCharSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(widthPositionSearchNehnutelnost.getText()),
                    lengthCharSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(lengthPositionSearchNehnutelnost.getText()));

            nehnutelnostTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostTableModel.addRow(new Object[]{nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }

        } else if (e.getSource() == searchParcelyButton) {
            LinkedList<Parcela> parcely = this.app.searchParcela(widthCharSearchParcela.getText().toCharArray()[0], Double.parseDouble(widthPositionSearchParcela.getText()),
                    lengthCharSearchParcela.getText().toCharArray()[0], Double.parseDouble(lengthPositionSearchParcela.getText()));

            parcelaSearchTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                parcelaSearchTableModel.addRow(new Object[]{parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }

        } else if (e.getSource() == searchObjectsButton) {
            nehnutelnosti = this.app.searchNehnutelnostArea(minWidthCharSearchObjekt.getText().toCharArray()[0], Double.parseDouble(minWidthPositionSearchObjekt.getText()),
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

            parcelaAllTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                parcelaAllTableModel.addRow(new Object[]{ parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }

        } else if (e.getSource() == insertNehnutelnostButton) {
            Nehnutelnost nehnutelnost = app.insertNehnutelnost( minWidthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(minWidthPositionInsertNehnutelnost.getText()),
                                                                minLengthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(minLengthPositionInsertNehnutelnost.getText()),
                                                                maxWidthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(maxWidthPositionInsertNehnutelnost.getText()),
                                                                maxLengthCharInsertNehnutelnost.getText().toCharArray()[0], Double.parseDouble(maxLengthPositionInsertNehnutelnost.getText()),
                                                                Integer.parseInt(supisneCisloInsertNehnutelnost.getText()), popisInsertNehnutelnost.getText(), optimalizeTreeCheckBoxInsertNehnutelnost.isSelected());

            nehnutelnostInsertTableModel.addRow(new Object[]{ nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});

        } else if (e.getSource() == insertParcelaButton) {
            Parcela parcela = app.insertParcela( minWidthCharInsertParcela.getText().toCharArray()[0], Double.parseDouble(minWidthPositionInsertParcela.getText()),
                    minLengthCharInsertParcela.getText().toCharArray()[0], Double.parseDouble(minLengthPositionInsertParcela.getText()),
                    maxWidthCharInsertParcela.getText().toCharArray()[0], Double.parseDouble(maxWidthPositionInsertParcela.getText()),
                    maxLengthCharInsertParcela.getText().toCharArray()[0], Double.parseDouble(maxLengthPositionInsertParcela.getText()),
                    Integer.parseInt(supisneCisloInsertParcela.getText()), popisInsertParcela.getText(), optimalizeTreeCheckBoxInsertParcela.isSelected());

            parcelaInsertTableModel.addRow(new Object[]{ parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
        }
        else if (e.getSource() == deleteNehnutelnostiSearchButton) {
            nehnutelnosti = this.app.searchNehnutelnost(widthCharDeleteNehnutelnost.getText().toCharArray()[0], Double.parseDouble(widthPositionDeleteNehnutelnost.getText()),
                    lengthCharDeleteNehnutelnost.getText().toCharArray()[0], Double.parseDouble(lengthPositionDeleteNehnutelnost.getText()));

            nehnutelnostDeleteTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostDeleteTableModel.addRow(new Object[]{nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }

        } else if (e.getSource() == deleteNehnutelnostButton) {
            Nehnutelnost nehnutelnost_to_delete = nehnutelnosti.get(listDeleteNehnutelnost.getSelectedRow());

            nehnutelnosti.remove(app.deleteNehnutelost(nehnutelnost_to_delete));

            nehnutelnostDeleteTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostDeleteTableModel.addRow(new Object[]{nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }

        }
        else if (e.getSource() == deleteParcelySearchButton) {
            parcely = this.app.searchParcela(widthCharDeleteParcela.getText().toCharArray()[0], Double.parseDouble(widthPositionDeleteParcela.getText()),
                    lengthCharDeleteParcela.getText().toCharArray()[0], Double.parseDouble(lengthPositionDeleteParcela.getText()));

            parcelaDeleteTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                parcelaDeleteTableModel.addRow(new Object[]{parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }

        } else if (e.getSource() == deleteParcelaButton) {
            Parcela parcela_to_delete = parcely.get(listDeleteParcela.getSelectedRow());

            parcely.remove(app.deleteParcela(parcela_to_delete));

            parcelaDeleteTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                parcelaDeleteTableModel.addRow(new Object[]{parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }
        } else if (e.getSource() == editNehnutelnostiSearchButton) {
            nehnutelnosti = this.app.searchNehnutelnost(widthCharEditSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(widthPositionEditSearchNehnutelnost.getText()),
                    lengthCharEditSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(lengthPositionEditSearchNehnutelnost.getText()));

            nehnutelnostEditTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostEditTableModel.addRow(new Object[]{nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }
        } else if (e.getSource() == editNehnutelnostiEditButton) {
            app.editNehnutelnost(nehnutelnost_to_edit, Integer.parseInt(this.supisneCisloEditNehnutelnost.getText()), this.popisEditNehnutelnost.getText(),
                                 this.minWidthCharEditNehnutelnost.getText().toCharArray()[0], Double.parseDouble(this.minWidthPositionEditNehnutelnost.getText()) ,
                                 this.minLengthCharEditNehnutelnost.getText().toCharArray()[0], Double.parseDouble(this.minLengthPositionEditNehnutelnost.getText()),
                                 this.maxWidthCharEditNehnutelnost.getText().toCharArray()[0], Double.parseDouble(this.maxWidthPositionEditNehnutelnost.getText()) ,
                                 this.maxLengthCharEditNehnutelnost.getText().toCharArray()[0], Double.parseDouble(this.maxLengthPositionEditNehnutelnost.getText())
            );

            nehnutelnosti = this.app.searchNehnutelnost(widthCharEditSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(widthPositionEditSearchNehnutelnost.getText()),
                    lengthCharEditSearchNehnutelnost.getText().toCharArray()[0], Double.parseDouble(lengthPositionEditSearchNehnutelnost.getText()));

            nehnutelnostEditTableModel.setRowCount(0);
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnostEditTableModel.addRow(new Object[]{nehnutelnost.getSupisneCislo(), nehnutelnost.getPopis(), nehnutelnost.getMinGPS().toString(), nehnutelnost.getMaxGPS().toString()});
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == listEditNehnutelnost) {
            int row = listEditNehnutelnost.rowAtPoint(e.getPoint());
            if (row >= 0) {
//                nehnutelnost_to_edit = nehnutelnosti.get(listEditNehnutelnost.getSelectedRow());
                nehnutelnost_to_edit = new Nehnutelnost(nehnutelnosti.get(listEditNehnutelnost.getSelectedRow()));

                this.supisneCisloEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getSupisneCislo()));
                this.popisEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getPopis()));

                this.minWidthCharEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMinGPS().getY()));
                this.maxWidthCharEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMaxGPS().getY()));
                this.minLengthCharEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMinGPS().getX(    )));
                this.maxLengthCharEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMaxGPS().getX()));

                this.minWidthPositionEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMinGPS().getPositionY()));
                this.maxWidthPositionEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMaxGPS().getPositionY()));
                this.minLengthPositionEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMinGPS().getPositionX()));
                this.maxLengthPositionEditNehnutelnost.setText(String.valueOf(nehnutelnost_to_edit.getMaxGPS().getPositionX()));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
