package Entities;

import java.util.LinkedList;

public class Parcela extends AreaObject implements Comparable<Parcela>{
    private LinkedList<Nehnutelnost> nehnutelnosti;

    public Parcela(int cisloParcely, String popis, GPS minGPS, GPS maxGPS) {
        super(cisloParcely, popis, minGPS, maxGPS);
        nehnutelnosti = new LinkedList<Nehnutelnost>();
    }

    public LinkedList<Nehnutelnost> getNehnutelnosti() {
        return nehnutelnosti;
    }

    public void setNehnutelnosti(LinkedList<Nehnutelnost> nehnutelnosti) {
        this.nehnutelnosti = nehnutelnosti;
    }

    @Override
    public int compareTo(Parcela o) {
        return 0;
    }
}
