package App;
import Entities.Nehnutelnost;
import Entities.Parcela;
import Entities.GPS;
import Structures.QuadTree;
import Structures.QuadTreeNode;
import Structures.QuadTreeNodeKeys;

import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.Random;

public class SystemApp {
    private QuadTree<Nehnutelnost> treeNehnutelnosti;
    private QuadTree<Parcela> treeParcely;

    public SystemApp() {
        treeNehnutelnosti = new QuadTree<Nehnutelnost>(0, 0, 100, 100, 20);
        treeParcely = new QuadTree<Parcela>(0, 0, 100, 100, 20);
    }

    public void fillSystem(double minX, double minY, double maxX, double maxY, int maxDepth, int nehnutelnostiCount, int parcelyCount,
                            double minWidthNehnutelnost, double minLengthNehnutelnost, double maxWidthNehnutelnost, double maxLengthNehnutelnost,
                             double minWidthParcela, double minLengthParcela, double maxWidthParcela, double maxLengthParcela,
                              boolean optimize) {

        treeNehnutelnosti = new QuadTree<Nehnutelnost>(minX, minY, maxX, maxY, maxDepth);
        treeParcely = new QuadTree<Parcela>(minX, minY, maxX, maxY, maxDepth);

        LinkedList<QuadTreeNodeKeys<Parcela>> parcelykeys = new LinkedList<QuadTreeNodeKeys<Parcela>>();
        LinkedList<QuadTreeNodeKeys<Nehnutelnost>> nehnutelnostikeys = new LinkedList<QuadTreeNodeKeys<Nehnutelnost>>();

        LinkedList<Nehnutelnost> nehnutelnosti = new LinkedList<Nehnutelnost>();
        LinkedList<Parcela> parcely = new LinkedList<Parcela>();

        Nehnutelnost helpNehnutelnost = null;
        Parcela helpParcela = null;
        GPS helpGPS = null;

        Random random = new Random();

        double helpWidth = 0.0;
        double helpLength = 0.0;

        double positionMinX = 0.0;
        double positionMaxX = 0.0;
        double positionMinY = 0.0;
        double positionMaxY = 0.0;

        double halfX = (maxX - minX) / 2.0;
        double halfY = (maxY - minY) / 2.0;

        char xMinChar;
        char xMaxChar;
        char yMinChar;
        char yMaxChar;

        int helpNumber;

        for (int j = 0; j < parcelyCount; j++) {
            helpWidth = random.nextDouble(minWidthParcela, maxWidthParcela);
            helpLength = random.nextDouble(minLengthParcela, maxLengthParcela);

            positionMinX = random.nextDouble(minX, maxX - helpWidth);
            positionMinY = random.nextDouble(minY, maxY - helpLength);

            if (positionMinX >= minX && positionMinX <= minX + halfX) {
                xMinChar = 'W';
            } else {
                xMinChar = 'E';
            }

            if (positionMinY >= minY && positionMinY <= minY + halfY) {
                yMinChar = 'S';
            } else {
                yMinChar = 'N';
            }

            positionMaxX = positionMinX + helpWidth;
            positionMaxY = positionMinY + helpLength;

            if (positionMaxX >= minX && positionMaxX <= minX + halfX) {
                xMaxChar = 'W';
            } else {
                xMaxChar = 'E';
            }

            if (positionMaxY >= minY && positionMaxY <= minY + halfY) {
                yMaxChar = 'S';
            } else {
                yMaxChar = 'N';
            }

            helpNumber = random.nextInt(1000);

            helpParcela = new Parcela(random.nextInt(1000), "Parcela" + helpNumber ,
                    new GPS(yMinChar,positionMinX,xMinChar,positionMinY),
                    new GPS(yMaxChar,positionMaxX,xMaxChar,positionMaxY));

            treeParcely.insert(positionMinX, positionMinY,positionMaxX, positionMaxY, helpParcela);
        }

        for (int i = 0; i < nehnutelnostiCount; i++) {
            helpWidth = random.nextDouble(minWidthNehnutelnost, maxWidthNehnutelnost);
            helpLength = random.nextDouble(minLengthNehnutelnost, maxLengthNehnutelnost);

            positionMinX = random.nextDouble(minX, maxX - helpWidth);
            positionMinY = random.nextDouble(minY, maxY - helpLength);

            if (positionMinX >= minX && positionMinX <= minX + halfX) {
                xMinChar = 'W';
            } else {
                xMinChar = 'E';
            }

            if (positionMinY >= minY && positionMinY <= minY + halfY) {
                yMinChar = 'S';
            } else {
                yMinChar = 'N';
            }

            positionMaxX = positionMinX + helpWidth;
            positionMaxY = positionMinY + helpLength;

            if (positionMaxX >= minX && positionMaxX <= minX + halfX) {
                xMaxChar = 'W';
            } else {
                xMaxChar = 'E';
            }

            if (positionMaxY >= minY && positionMaxY <= minY + halfY) {
                yMaxChar = 'S';
            } else {
                yMaxChar = 'N';
            }

            helpNumber = random.nextInt(1000);

            parcelykeys = treeParcely.findContainedOrIntersecting(positionMinX, positionMinY, positionMaxX, positionMaxY);

            parcely.clear();
            if (parcelykeys != null) {
                for (QuadTreeNodeKeys<Parcela> keys: parcelykeys) {
                    parcely.add(keys.getData());
                }
            }

            helpNehnutelnost = new Nehnutelnost(helpNumber, "Nehnutelnost" + helpNumber,
                    new GPS(yMinChar,positionMinX,xMinChar,positionMinY),
                    new GPS(yMaxChar,positionMaxX,xMaxChar,positionMaxY));
            treeNehnutelnosti.insert(positionMinX, positionMinY,positionMaxX, positionMaxY, helpNehnutelnost);

            helpNehnutelnost.getParcely().addAll(parcely);

            if (parcely.getFirst() != null) {
                parcely.getFirst().getNehnutelnosti().add(helpNehnutelnost);
            }
        }

        if (optimize) {
            System.out.println("pred " + treeNehnutelnosti.findAllIntersectingData().size());
            treeNehnutelnosti = treeNehnutelnosti.optimize();
            System.out.println("po " + treeNehnutelnosti.findAllIntersectingData().size());
            System.out.println("minx: " + treeNehnutelnosti.getMinX() + " minY: " + treeNehnutelnosti.getMinY() + " maxx: " + treeNehnutelnosti.getMaxX() + " maxY: " + treeNehnutelnosti.getMaxY());


            System.out.println("pred " + treeParcely.findAllIntersectingData().size());
            treeParcely = treeParcely.optimize();
            System.out.println("po " + treeParcely.findAllIntersectingData().size());
            System.out.println("minx: " + treeParcely.getMinX() + " minY: " + treeParcely.getMinY() + " maxx: " + treeParcely.getMaxX() + " maxY: " + treeParcely.getMaxY());

        }

        System.out.println("Pocet parciel" + treeParcely.find(treeParcely.getMinX(),treeParcely.getMinY(),treeParcely.getMaxX(),treeParcely.getMaxY()).size());
        System.out.println("Pocet nehnutelnosti" + treeNehnutelnosti.find(treeNehnutelnosti.getMinX(),treeNehnutelnosti.getMinY(),treeNehnutelnosti.getMaxX(),treeNehnutelnosti.getMaxY()).size());
    }

    public LinkedList<Nehnutelnost> searchNehnutelnost(char xChar, double p_x, char yChar, double p_y) {
        if (treeNehnutelnosti == null) {
            return null;
        }

        LinkedList<Nehnutelnost> nehnutelnosti = new LinkedList<Nehnutelnost>();
        LinkedList<QuadTreeNodeKeys<Nehnutelnost>> nehnutelnostiKeys = new LinkedList<QuadTreeNodeKeys<Nehnutelnost>>();

        double x = p_x;
        double y = p_y;

//        if (xChar == 'N') {
//            x = p_x;
//        } else if (xChar == 'S') {
//            x = p_x * -1.0;
//        }
//
//        if (yChar == 'W') {
//            y = p_y * -1.0;
//        } else if (yChar == 'E') {
//            y = p_y;
//        }

        nehnutelnostiKeys = treeNehnutelnosti.findContainedOrIntersecting(x,y,x,y);

        if (nehnutelnostiKeys != null) {
            for (QuadTreeNodeKeys<Nehnutelnost> key : nehnutelnostiKeys) {
                nehnutelnosti.add(key.getData());
            }
        }

        return nehnutelnosti;
    }

    public LinkedList<Parcela> searchParcela(char xChar, double p_x, char yChar, double p_y) {
        if (treeParcely == null) {
            return null;
        }

        LinkedList<Parcela> parcely = new LinkedList<Parcela>();
        LinkedList<QuadTreeNodeKeys<Parcela>> parcelyKeys = new LinkedList<QuadTreeNodeKeys<Parcela>>();

        double x = p_x;
        double y = p_y;

//        if (xChar == 'N') {
//            x = p_x;
//        } else if (xChar == 'S') {
//            x = p_x * -1.0;
//        }
//
//        if (yChar == 'W') {
//            y = p_y * -1.0;
//        } else if (yChar == 'E') {
//            y = p_y;
//        }

        parcelyKeys = treeParcely.findContainedOrIntersecting(x,y,x,y);

        if (parcelyKeys != null) {
            for (QuadTreeNodeKeys<Parcela> key : parcelyKeys) {
                parcely.add(key.getData());
            }
        }

        return parcely;
    }

    public LinkedList<Nehnutelnost> searchNehnutelnostArea(char minXChar, double p_minX, char minYChar, double p_minY, char maxXChar, double p_maxX, char maxYChar, double p_maxy) {
        if (treeNehnutelnosti == null) {
            return null;
        }

        LinkedList<Nehnutelnost> nehnutelnosti = new LinkedList<Nehnutelnost>();
        LinkedList<QuadTreeNodeKeys<Nehnutelnost>> nehnutelnostiKeys = new LinkedList<QuadTreeNodeKeys<Nehnutelnost>>();


        nehnutelnostiKeys = treeNehnutelnosti.findContainedOrIntersecting(p_minX,p_minY,p_maxX,p_maxy);

        if (nehnutelnostiKeys != null) {
            for (QuadTreeNodeKeys<Nehnutelnost> key : nehnutelnostiKeys) {
                nehnutelnosti.add(key.getData());
            }
        }

        return nehnutelnosti;
    }

    public LinkedList<Parcela> searchParcelaArea(char minXChar, double p_minX, char minYChar, double p_minY, char maxXChar, double p_maxX, char maxYChar, double p_maxy) {
        if (treeParcely == null) {
            return null;
        }

        LinkedList<Parcela> parcely = new LinkedList<Parcela>();
        LinkedList<QuadTreeNodeKeys<Parcela>> parcelyKeys = new LinkedList<QuadTreeNodeKeys<Parcela>>();


        parcelyKeys = treeParcely.findContainedOrIntersecting(p_minX,p_minY,p_maxX,p_maxy);

        if (parcelyKeys != null) {
            for (QuadTreeNodeKeys<Parcela> key : parcelyKeys) {
                parcely.add(key.getData());
            }
        }

        return parcely;
    }

    public Nehnutelnost insertNehnutelnost(char minXChar, double p_minX, char minYChar, double p_minY, char maxXChar, double p_maxX, char maxYChar, double p_maxY, int supisneCislo, String popis, boolean optimize) {
        if (treeNehnutelnosti == null) {
            return null;
        }

        LinkedList<QuadTreeNodeKeys<Parcela>> parcelykeys = treeParcely.findContainedOrIntersecting(p_minX, p_minY, p_maxX, p_maxY);
        LinkedList<Parcela> parcely = new LinkedList<Parcela>();

        if (parcelykeys != null) {
            for (QuadTreeNodeKeys<Parcela> keys: parcelykeys) {
                parcely.add(keys.getData());
            }
        }

        Nehnutelnost nehnutelnost = new Nehnutelnost(supisneCislo, popis, new GPS(minYChar, p_minY, minXChar, p_minX), new GPS(maxYChar, p_maxY, maxXChar, p_maxX) ); // TODO REFERENCIE NA PARCELY
        nehnutelnost.getParcely().addAll(parcely);

        if (parcely != null) {
            for (Parcela parcela : parcely) {
                parcela.getNehnutelnosti().add(nehnutelnost);
            }
        }

        treeNehnutelnosti.insert(p_minX, p_minY, p_maxX, p_maxY, nehnutelnost);

        if (optimize) {
            treeNehnutelnosti = treeNehnutelnosti.optimize();
            System.out.println("minx: " + treeNehnutelnosti.getMinX() + " minY: " + treeNehnutelnosti.getMinY() + " maxx: " + treeNehnutelnosti.getMaxX() + " maxY: " + treeNehnutelnosti.getMaxY());
        }

        return nehnutelnost;
    }

    public Parcela insertParcela(char minXChar, double p_minX, char minYChar, double p_minY, char maxXChar, double p_maxX, char maxYChar, double p_maxY, int supisneCislo, String popis, boolean optimize) {
        if (treeParcely == null) {
            return null;
        }

        LinkedList<QuadTreeNodeKeys<Nehnutelnost>> nehnutelnostiKeys = treeNehnutelnosti.findContainedOrIntersecting(p_minX, p_minY, p_maxX, p_maxY);
        LinkedList<Nehnutelnost> nehnutelnosti = new LinkedList<Nehnutelnost>();

        if (nehnutelnostiKeys != null) {
            for (QuadTreeNodeKeys<Nehnutelnost> keys: nehnutelnostiKeys) {
                nehnutelnosti.add(keys.getData());
            }
        }

        Parcela parcela = new Parcela(supisneCislo, popis, new GPS(minYChar, p_minY, minXChar, p_minX), new GPS(maxYChar, p_maxY, maxXChar, p_maxX) );
        parcela.getNehnutelnosti().addAll(nehnutelnosti);

        if (nehnutelnosti != null) {
            for (Nehnutelnost nehnutelnost : nehnutelnosti) {
                nehnutelnost.getParcely().add(parcela);
            }
        }

        treeParcely.insert(p_minX, p_minY, p_maxX, p_maxY, parcela);

        if (optimize) {
            treeParcely = treeParcely.optimize();
            System.out.println("minx: " + treeParcely.getMinX() + " minY: " + treeParcely.getMinY() + " maxx: " + treeParcely.getMaxX() + " maxY: " + treeParcely.getMaxY());
        }

        return parcela;
    }

    public Nehnutelnost deleteNehnutelost(Nehnutelnost p_nehnutelnost) {
        Nehnutelnost nehnutelnost = treeNehnutelnosti.deleteByData(p_nehnutelnost);

        LinkedList<Parcela> parcely = nehnutelnost.getParcely();

        for (Parcela parcela: parcely) {
            parcela.getNehnutelnosti().remove(nehnutelnost);
        }

        return nehnutelnost;
    }

    public Parcela deleteParcela(Parcela p_parcela) {
        Parcela parcela = treeParcely.deleteByData(p_parcela);

        LinkedList<Nehnutelnost> nehnutelnosti = parcela.getNehnutelnosti();

        for (Nehnutelnost nehnutelnost: nehnutelnosti) {
            nehnutelnost.getParcely().remove(parcela);
        }

        return parcela;
    }

    public Nehnutelnost editNehnutelnost(Nehnutelnost p_nehnutelnost_to_edit, int supisneCislo, String popis,
                                         char min_y, double min_positionY, char min_x, double min_positionX,
                                         char max_y, double max_positionY, char max_x, double max_positionX) {

        QuadTreeNodeKeys<Nehnutelnost> keys = treeNehnutelnosti.findByData(p_nehnutelnost_to_edit);
        keys.setMinXElement(min_positionX);
        keys.setMinYElement(min_positionY);
        keys.setMaxXElement(max_positionX);
        keys.setMaxYElement(max_positionY);

        keys.getData().setSupisneCislo(supisneCislo);
        keys.getData().setPopis(popis);

        keys.getData().getMinGPS().setX(min_x);
        keys.getData().getMinGPS().setPositionX(min_positionX);
        keys.getData().getMinGPS().setY(min_y);
        keys.getData().getMinGPS().setPositionY(min_positionY);

        keys.getData().getMaxGPS().setX(max_x);
        keys.getData().getMaxGPS().setPositionX(max_positionX);
        keys.getData().getMaxGPS().setY(max_y);
        keys.getData().getMaxGPS().setPositionY(max_positionY);

        LinkedList<Parcela> parcely_old = new LinkedList<Parcela>(keys.getData().getParcely());
        LinkedList<Parcela> parcely_new = treeParcely.findContainedOrIntersectingData(min_positionX, min_positionY, max_positionX, max_positionY);

        keys.getData().getParcely().clear();
        keys.getData().getParcely().addAll(parcely_new);

        for ( Parcela parcela : parcely_new ) {
            parcela.getNehnutelnosti().add(keys.getData());
        }

        for(Parcela parcela_old : parcely_old) {
            LinkedList<Nehnutelnost> nehnutelnosti = treeNehnutelnosti.findContainedOrIntersectingData(parcela_old.getMinGPS().getPositionX(),parcela_old.getMinGPS().getPositionY(),
                                                              parcela_old.getMaxGPS().getPositionX(),parcela_old.getMaxGPS().getPositionY());

            if (!nehnutelnosti.contains(keys.getData())) {
                parcela_old.getNehnutelnosti().remove(keys.getData());
            }
        }

        return keys.getData();
    }

    public Parcela editParcela(Parcela p_parcela_to_edit, int supisneCislo, String popis,
                               char min_y, double min_positionY, char min_x, double min_positionX,
                               char max_y, double max_positionY, char max_x, double max_positionX) {

        QuadTreeNodeKeys<Parcela> keys = treeParcely.findByData(p_parcela_to_edit);
        keys.setMinXElement(min_positionX);
        keys.setMinYElement(min_positionY);
        keys.setMaxXElement(max_positionX);
        keys.setMaxYElement(max_positionY);

        keys.getData().setSupisneCislo(supisneCislo);
        keys.getData().setPopis(popis);

        keys.getData().getMinGPS().setX(min_x);
        keys.getData().getMinGPS().setPositionX(min_positionX);
        keys.getData().getMinGPS().setY(min_y);
        keys.getData().getMinGPS().setPositionY(min_positionY);

        keys.getData().getMaxGPS().setX(max_x);
        keys.getData().getMaxGPS().setPositionX(max_positionX);
        keys.getData().getMaxGPS().setY(max_y);
        keys.getData().getMaxGPS().setPositionY(max_positionY);

        LinkedList<Nehnutelnost> nehnutelnosti_old = new LinkedList<Nehnutelnost>(keys.getData().getNehnutelnosti());
        LinkedList<Nehnutelnost> nehnutelnosti_new = treeNehnutelnosti.findContainedOrIntersectingData(min_positionX, min_positionY, max_positionX, max_positionY);

        keys.getData().getNehnutelnosti().clear();
        keys.getData().getNehnutelnosti().addAll(nehnutelnosti_new);

        for ( Nehnutelnost nehnutelnost : nehnutelnosti_new ) {
            nehnutelnost.getParcely().add(keys.getData());
        }

        for(Nehnutelnost nehnutelnost_old : nehnutelnosti_old) {
            LinkedList<Parcela> parcely = treeParcely.findContainedOrIntersectingData(nehnutelnost_old.getMinGPS().getPositionX(),nehnutelnost_old.getMinGPS().getPositionY(),
                    nehnutelnost_old.getMaxGPS().getPositionX(),nehnutelnost_old.getMaxGPS().getPositionY());

            if (!parcely.contains(keys.getData())) {
                nehnutelnost_old.getParcely().remove(keys.getData());
            }
        }

        return keys.getData();
    }
}
