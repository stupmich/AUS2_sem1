package GUI;

import javax.swing.*;

public class GeodetSystemGUI extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JTabbedPane tabbedPane5;
    private JTextField widthCharSearchNehnutelnost;
    private JTextField lengthCharSearchNehnutelnost;
    private JTextField popisInsertNehnutelnost;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTable table4;
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
    private JScrollPane listParcelaFill;
    private JScrollPane listNehnutelnostFill;
    private JTextField lengthPositionSearchNehnutelnost;
    private JTextField widthPositionSearchNehnutelnost;
    private JTextField widthCharSearchParcela;
    private JTextField lengthCharSearchParcela;
    private JTextField lengthPositionSearchParcela;
    private JTextField widthPositionSearchParcela;
    private JTextField minWidthCharSearchObjekt;
    private JTextField minLengthCharSearchObjekt;
    private JTextField lengthPositionSearchObjekt;
    private JTextField widthPositionSearchObjekt;
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

    public GeodetSystemGUI(){
        this.setContentPane(mainPanel);
        this.setTitle("GeoSystem");
        this.setSize(1920,1080);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
