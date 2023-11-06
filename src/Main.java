
import App.SystemApp;
import Entities.GPS;
import Entities.Nehnutelnost;
import Entities.Parcela;
import GUI.GeodetSystemGUI;
import Structures.QuadTree;
import Structures.QuadTreeNode;
import Structures.QuadTreeNodeKeys;
import Tester.Tester;
import com.sun.nio.sctp.NotificationHandler;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        GeodetSystemGUI gui = new GeodetSystemGUI();

//        SystemApp app = new SystemApp();
//        app.fillSystem(0,0,100,100,3,10000,0,0.5,0.5,1,1,1,1,2,2,false);
//
//        Random random = new Random();
//        random.setSeed(5);
//        QuadTree<Nehnutelnost> tree = app.getTreeNehnutelnosti();
//
//
//        QuadTree<Nehnutelnost> treeOpt = tree.optimize();
//        System.out.println(treeOpt.getMinX());
//        System.out.println(treeOpt.getMaxX());
//        System.out.println(treeOpt.getMinY());
//        System.out.println(treeOpt.getMaxY());
//        System.out.println(treeOpt.getMaxLevel());
////        TestQuadTreeOpt(treeOpt);
//        TestQuadTreeOptDelete(treeOpt);

    }


    public static void TestQuadTreeOptInsert( QuadTree<Nehnutelnost> p_tree) {
                Random random = new Random();
        random.setSeed(5);

        double helpWidth = 0.0;
            double helpLength = 0.0;

            double positionMinX = 0.0;
            double positionMaxX = 0.0;
            double positionMinY = 0.0;
            double positionMaxY = 0.0;

            double halfX = (1 - 0.5) / 2.0;
            double halfY = (1 - 0.5) / 2.0;

            char xMinChar;
            char xMaxChar;
            char yMinChar;
            char yMaxChar;

            int helpNumber;

        for (int i = 0; i < 50000; i++) {
            helpWidth = random.nextDouble(0.5, 1);
            helpLength = random.nextDouble(0.5, 1);

            positionMinX = random.nextDouble(p_tree.getMinX(), p_tree.getMaxX() - helpWidth);
            positionMinY = random.nextDouble(p_tree.getMinY(), p_tree.getMaxY() - helpLength);

            if (positionMinX >= 0 && positionMinX <= 0 + halfX) {
                xMinChar = 'W';
            } else {
                xMinChar = 'E';
            }

            if (positionMinY >= 0 && positionMinY <= 0 + halfY) {
                yMinChar = 'S';
            } else {
                yMinChar = 'N';
            }

            positionMaxX = positionMinX + helpWidth;
            positionMaxY = positionMinY + helpLength;

            if (positionMaxX >= 0 && positionMaxX <= 0 + halfX) {
                xMaxChar = 'W';
            } else {
                xMaxChar = 'E';
            }

            if (positionMaxY >= 0 && positionMaxY <= 0 + halfY) {
                yMaxChar = 'S';
            } else {
                yMaxChar = 'N';
            }

            helpNumber = random.nextInt(1000);

            Nehnutelnost helpNehnutelnost = new Nehnutelnost(helpNumber, "Nehnutelnost" + helpNumber,
                    new GPS(yMinChar,positionMinY ,xMinChar,positionMinX),
                    new GPS(yMaxChar,positionMaxY ,xMaxChar,positionMaxX));

            p_tree.insert(positionMinX, positionMinY,positionMaxX, positionMaxY, helpNehnutelnost);

        }
    }

    public static void TestQuadTreeOptDelete( QuadTree<Nehnutelnost> p_tree) {
        Random random = new Random();
        random.setSeed(5);

        double helpWidth = 0.0;
        double helpLength = 0.0;

        double positionMinX = 0.0;
        double positionMaxX = 0.0;
        double positionMinY = 0.0;
        double positionMaxY = 0.0;

        double halfX = (1 - 0.5) / 2.0;
        double halfY = (1 - 0.5) / 2.0;

        char xMinChar;
        char xMaxChar;
        char yMinChar;
        char yMaxChar;

        int helpNumber;

        LinkedList<QuadTreeNodeKeys<Nehnutelnost>> nehnutelnostikeys = p_tree.find(p_tree.getMinX(),p_tree.getMinY(),p_tree.getMaxX(),p_tree.getMaxY());


        for (int i = 0; i < 10000; i++) {
            helpWidth = random.nextDouble(0.5, 1);
            helpLength = random.nextDouble(0.5, 1);

            positionMinX = random.nextDouble(p_tree.getMinX(), p_tree.getMaxX() - helpWidth);
            positionMinY = random.nextDouble(p_tree.getMinY(), p_tree.getMaxY() - helpLength);

            if (positionMinX >= 0 && positionMinX <= 0 + halfX) {
                xMinChar = 'W';
            } else {
                xMinChar = 'E';
            }

            if (positionMinY >= 0 && positionMinY <= 0 + halfY) {
                yMinChar = 'S';
            } else {
                yMinChar = 'N';
            }

            positionMaxX = positionMinX + helpWidth;
            positionMaxY = positionMinY + helpLength;

            if (positionMaxX >= 0 && positionMaxX <= 0 + halfX) {
                xMaxChar = 'W';
            } else {
                xMaxChar = 'E';
            }

            if (positionMaxY >= 0 && positionMaxY <= 0 + halfY) {
                yMaxChar = 'S';
            } else {
                yMaxChar = 'N';
            }

            helpNumber = random.nextInt(nehnutelnostikeys.size());

            Nehnutelnost helpNehnutelnost = nehnutelnostikeys.get(helpNumber).getData();

            p_tree.deleteByData(helpNehnutelnost);
            nehnutelnostikeys.remove(helpNumber);
        }
    }

}