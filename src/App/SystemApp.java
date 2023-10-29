package App;
import Entities.Nehnutelnost;
import Entities.Parcela;
import Entities.GPS;
import Structures.QuadTree;

import java.util.Random;

public class SystemApp {
    private QuadTree<Nehnutelnost> treeNehnutelnosti;
    private QuadTree<Parcela> treeParcely;

    public SystemApp(QuadTree<Nehnutelnost> treeNehnutelnosti, QuadTree<Parcela> treeParcely) {
        this.treeNehnutelnosti = treeNehnutelnosti;
        this.treeParcely = treeParcely;
    }

    public void fillSystem(double minX, double minY, double maxX, double maxY, int maxDepth, int nehnutelnostiCount, int parcelyCount,
                            double minWidthNehnutelnost, double minLengthNehnutelnost, double maxWidthNehnutelnost, double maxLengthNehnutelnost,
                             double minWidthParcela, double minLengthParcela, double maxWidthParcela, double maxLengthParcela) {

        treeNehnutelnosti = new QuadTree<Nehnutelnost>(minX, minY, maxX, maxY, maxDepth);
        treeParcely = new QuadTree<Parcela>(minX, minY, maxX, maxY, maxDepth);

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

            helpNehnutelnost = new Nehnutelnost(random.nextInt(1000), "RandomPopisTODO",null,
                                                 new GPS(yMinChar,positionMinX,xMinChar,positionMinY),
                                                    new GPS(yMaxChar,positionMaxX,xMaxChar,positionMaxY));
            treeNehnutelnosti.insert(treeNehnutelnosti.getRoot(),positionMinX, positionMinY,positionMaxX, positionMaxY, helpNehnutelnost);
        }

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

            helpParcela = new Parcela(random.nextInt(1000), "RandomPopisTODO",null,
                    new GPS(yMinChar,positionMinX,xMinChar,positionMinY),
                    new GPS(yMaxChar,positionMaxX,xMaxChar,positionMaxY));
            treeParcely.insert(treeParcely.getRoot(),positionMinX, positionMinY,positionMaxX, positionMaxY, helpParcela);
        }
    }
}
