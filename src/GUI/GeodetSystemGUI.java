package GUI;

import App.SystemApp;
import Entities.Nehnutelnost;
import Entities.Parcela;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

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
    private DefaultTableModel parcelaEditTableModel;

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
    private JTable listEditNParcela;
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
    private JTable listEditParcela;
    private JTextField widthCharEditSearchParcela;
    private JTextField lengthCharEditSearchParcela;
    private JTextField widthPositionEditSearchParcela;
    private JTextField lengthPositionEditSearchParcela;
    private JButton editParcelySearchButton;
    private JTextField supisneCisloEditParcela;
    private JTextField popisEditParcela;
    private JTextField minWidthCharEditParcela;
    private JTextField minLengthCharEditParcela;
    private JTextField minWidthPositionEditParcela;
    private JTextField minLengthPositionEditParcela;
    private JTextField maxWidthCharEditParcela;
    private JTextField maxLengthCharEditParcela;
    private JTextField maxWidthPositionEditParcela;
    private JTextField maxLengthPositionEditParcela;
    private JButton editParcelyEditButton;


    private SystemApp app;
    LinkedList<Nehnutelnost> nehnutelnosti;
    LinkedList<Parcela> parcely;
    Nehnutelnost nehnutelnost_to_edit;
    Parcela parcela_to_edit;

    public GeodetSystemGUI(){
        this.setContentPane(mainPanel);
        this.setTitle("GeoSystem");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.app = new SystemApp();

        nehnutelnosti = new LinkedList<Nehnutelnost>();
        parcely = new LinkedList<Parcela>();

        nehnutelnostTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        nehnutelnostTableModel.addColumn("Cislo");
        nehnutelnostTableModel.addColumn("Popis");
        nehnutelnostTableModel.addColumn("GPS1");
        nehnutelnostTableModel.addColumn("GPS2");
        listSearchNehnutelnost.setModel(nehnutelnostTableModel);


        nehnutelnostAllTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        nehnutelnostAllTableModel.addColumn("Cislo");
        nehnutelnostAllTableModel.addColumn("Popis");
        nehnutelnostAllTableModel.addColumn("GPS1");
        nehnutelnostAllTableModel.addColumn("GPS2");
        listSearchAllNehnutelnost.setModel(nehnutelnostAllTableModel);

        nehnutelnostInsertTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        nehnutelnostInsertTableModel.addColumn("Cislo");
        nehnutelnostInsertTableModel.addColumn("Popis");
        nehnutelnostInsertTableModel.addColumn("GPS1");
        nehnutelnostInsertTableModel.addColumn("GPS2");
        listNehnutelnostInsert.setModel(nehnutelnostInsertTableModel);

        parcelaInsertTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        parcelaInsertTableModel.addColumn("Cislo");
        parcelaInsertTableModel.addColumn("Popis");
        parcelaInsertTableModel.addColumn("GPS1");
        parcelaInsertTableModel.addColumn("GPS2");
        listParcelaInsert.setModel(parcelaInsertTableModel);

        parcelaAllTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        parcelaAllTableModel.addColumn("Cislo");
        parcelaAllTableModel.addColumn("Popis");
        parcelaAllTableModel.addColumn("GPS1");
        parcelaAllTableModel.addColumn("GPS2");
        listSearchAllParcela.setModel(parcelaAllTableModel);

        parcelaSearchTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        parcelaSearchTableModel.addColumn("Cislo");
        parcelaSearchTableModel.addColumn("Popis");
        parcelaSearchTableModel.addColumn("GPS1");
        parcelaSearchTableModel.addColumn("GPS2");
        listSearchParcela.setModel(parcelaSearchTableModel);

        nehnutelnostDeleteTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        nehnutelnostDeleteTableModel.addColumn("Cislo");
        nehnutelnostDeleteTableModel.addColumn("Popis");
        nehnutelnostDeleteTableModel.addColumn("GPS1");
        nehnutelnostDeleteTableModel.addColumn("GPS2");
        listDeleteNehnutelnost.setModel(nehnutelnostDeleteTableModel);

        parcelaDeleteTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        parcelaDeleteTableModel.addColumn("Cislo");
        parcelaDeleteTableModel.addColumn("Popis");
        parcelaDeleteTableModel.addColumn("GPS1");
        parcelaDeleteTableModel.addColumn("GPS2");
        listDeleteParcela.setModel(parcelaDeleteTableModel);

        nehnutelnostEditTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        nehnutelnostEditTableModel.addColumn("Cislo");
        nehnutelnostEditTableModel.addColumn("Popis");
        nehnutelnostEditTableModel.addColumn("GPS1");
        nehnutelnostEditTableModel.addColumn("GPS2");
        listEditNehnutelnost.setModel(nehnutelnostEditTableModel);
        listEditNehnutelnost.setCellSelectionEnabled(false);

        parcelaEditTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        parcelaEditTableModel.addColumn("Cislo");
        parcelaEditTableModel.addColumn("Popis");
        parcelaEditTableModel.addColumn("GPS1");
        parcelaEditTableModel.addColumn("GPS2");
        listEditParcela.setModel(parcelaEditTableModel);

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
        editParcelySearchButton.addActionListener(this);
        editNehnutelnostiEditButton.addActionListener(this);
        editParcelyEditButton.addActionListener(this);

        listEditNehnutelnost.addMouseListener(this);
        listEditParcela.addMouseListener(this);
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
        else if (e.getSource() == editParcelySearchButton) {
            parcely = this.app.searchParcela(widthCharEditSearchParcela.getText().toCharArray()[0], Double.parseDouble(widthPositionEditSearchParcela.getText()),
                    lengthCharEditSearchParcela.getText().toCharArray()[0], Double.parseDouble(lengthPositionEditSearchParcela.getText()));

            parcelaEditTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                parcelaEditTableModel.addRow(new Object[]{parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }
        } else if (e.getSource() == editParcelyEditButton) {
            app.editParcela(parcela_to_edit, Integer.parseInt(this.supisneCisloEditParcela.getText()), this.popisEditParcela.getText(),
                    this.minWidthCharEditParcela.getText().toCharArray()[0], Double.parseDouble(this.minWidthPositionEditParcela.getText()) ,
                    this.minLengthCharEditParcela.getText().toCharArray()[0], Double.parseDouble(this.minLengthPositionEditParcela.getText()),
                    this.maxWidthCharEditParcela.getText().toCharArray()[0], Double.parseDouble(this.maxWidthPositionEditParcela.getText()) ,
                    this.maxLengthCharEditParcela.getText().toCharArray()[0], Double.parseDouble(this.maxLengthPositionEditParcela.getText())
            );

            parcely = this.app.searchParcela(widthCharEditSearchParcela.getText().toCharArray()[0], Double.parseDouble(widthPositionEditSearchParcela.getText()),
                    lengthCharEditSearchParcela.getText().toCharArray()[0], Double.parseDouble(lengthPositionEditSearchParcela.getText()));

            parcelaEditTableModel.setRowCount(0);
            for (Parcela parcela : parcely) {
                nehnutelnostEditTableModel.addRow(new Object[]{parcela.getSupisneCislo(), parcela.getPopis(), parcela.getMinGPS().toString(), parcela.getMaxGPS().toString()});
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == listEditNehnutelnost) {
            int row = listEditNehnutelnost.rowAtPoint(e.getPoint());
            if (row >= 0) {
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
        } else if (e.getSource() == listEditParcela) {
            int row = listEditParcela.rowAtPoint(e.getPoint());
            if (row >= 0) {
                parcela_to_edit = new Parcela(parcely.get(listEditParcela.getSelectedRow()));

                this.supisneCisloEditParcela.setText(String.valueOf(parcela_to_edit.getSupisneCislo()));
                this.popisEditParcela.setText(String.valueOf(parcela_to_edit.getPopis()));

                this.minWidthCharEditParcela.setText(String.valueOf(parcela_to_edit.getMinGPS().getY()));
                this.maxWidthCharEditParcela.setText(String.valueOf(parcela_to_edit.getMaxGPS().getY()));
                this.minLengthCharEditParcela.setText(String.valueOf(parcela_to_edit.getMinGPS().getX(    )));
                this.maxLengthCharEditParcela.setText(String.valueOf(parcela_to_edit.getMaxGPS().getX()));

                this.minWidthPositionEditParcela.setText(String.valueOf(parcela_to_edit.getMinGPS().getPositionY()));
                this.maxWidthPositionEditParcela.setText(String.valueOf(parcela_to_edit.getMaxGPS().getPositionY()));
                this.minLengthPositionEditParcela.setText(String.valueOf(parcela_to_edit.getMinGPS().getPositionX()));
                this.maxLengthPositionEditParcela.setText(String.valueOf(parcela_to_edit.getMaxGPS().getPositionX()));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {

            if (e.getSource() == listEditNehnutelnost) {
                int row = listEditNehnutelnost.rowAtPoint(e.getPoint());

                Nehnutelnost nehnutelnost = nehnutelnosti.get(row);
                if (row >= 0) {
                    NehnutelnostDetailyWindow detailsWindow = new NehnutelnostDetailyWindow(nehnutelnost);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listDeleteNehnutelnost) {
                int row = listDeleteNehnutelnost.rowAtPoint(e.getPoint());

                Nehnutelnost nehnutelnost = nehnutelnosti.get(row);
                if (row >= 0) {
                    NehnutelnostDetailyWindow detailsWindow = new NehnutelnostDetailyWindow(nehnutelnost);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listSearchNehnutelnost) {
                int row = listSearchNehnutelnost.rowAtPoint(e.getPoint());

                Nehnutelnost nehnutelnost = nehnutelnosti.get(row);
                if (row >= 0) {
                    NehnutelnostDetailyWindow detailsWindow = new NehnutelnostDetailyWindow(nehnutelnost);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listSearchAllNehnutelnost) {
                int row = listSearchAllNehnutelnost.rowAtPoint(e.getPoint());

                Nehnutelnost nehnutelnost = nehnutelnosti.get(row);
                if (row >= 0) {
                    NehnutelnostDetailyWindow detailsWindow = new NehnutelnostDetailyWindow(nehnutelnost);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listNehnutelnostInsert) {
                int row = listNehnutelnostInsert.rowAtPoint(e.getPoint());

                Nehnutelnost nehnutelnost = nehnutelnosti.get(row);
                if (row >= 0) {
                    NehnutelnostDetailyWindow detailsWindow = new NehnutelnostDetailyWindow(nehnutelnost);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listEditParcela) {
                int row = listEditParcela.rowAtPoint(e.getPoint());

                Parcela parcela = parcely.get(row);
                if (row >= 0) {
                    ParcelaDetailyWindow detailsWindow = new ParcelaDetailyWindow(parcela);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listParcelaInsert) {
                int row = listParcelaInsert.rowAtPoint(e.getPoint());

                Parcela parcela = parcely.get(row);
                if (row >= 0) {
                    ParcelaDetailyWindow detailsWindow = new ParcelaDetailyWindow(parcela);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listDeleteParcela) {
                int row = listDeleteParcela.rowAtPoint(e.getPoint());

                Parcela parcela = parcely.get(row);
                if (row >= 0) {
                    ParcelaDetailyWindow detailsWindow = new ParcelaDetailyWindow(parcela);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listSearchParcela) {
                int row = listSearchParcela.rowAtPoint(e.getPoint());

                Parcela parcela = parcely.get(row);
                if (row >= 0) {
                    ParcelaDetailyWindow detailsWindow = new ParcelaDetailyWindow(parcela);
                    detailsWindow.setVisible(true);
                }
            } else if (e.getSource() == listSearchAllParcela) {
                int row = listSearchAllParcela.rowAtPoint(e.getPoint());

                Parcela parcela = parcely.get(row);
                if (row >= 0) {
                    ParcelaDetailyWindow detailsWindow = new ParcelaDetailyWindow(parcela);
                    detailsWindow.setVisible(true);
                }
            }
        }
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
