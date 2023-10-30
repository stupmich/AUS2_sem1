package Entities;

import java.util.LinkedList;

public class Nehnutelnost extends AreaObject implements Comparable<Nehnutelnost> {
    private LinkedList<Parcela> parcely;

    public Nehnutelnost(int supisneCislo, String popis, GPS minGPS, GPS maxGPS) {
        super(supisneCislo, popis, minGPS, maxGPS);
        parcely = new LinkedList<Parcela>();
    }

    public LinkedList<Parcela> getParcely() {
        return parcely;
    }

    public void setParcely(LinkedList<Parcela> parcely) {
        this.parcely = parcely;
    }

    @Override
    public int compareTo(Nehnutelnost o) {
        return 0;
    }
}
