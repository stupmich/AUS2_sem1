package Entities;

import java.util.ArrayList;

public class Nehnutelnost extends AreaObject implements Comparable<Nehnutelnost> {
    private ArrayList<Parcela> parcely;

    public Nehnutelnost(int supisneCislo, String popis, ArrayList<Parcela> parcely, GPS minGPS, GPS maxGPS) {
        super(supisneCislo, popis, minGPS, maxGPS);
        this.parcely = parcely;
    }

    public ArrayList<Parcela> getParcely() {
        return parcely;
    }

    public void setParcely(ArrayList<Parcela> parcely) {
        this.parcely = parcely;
    }

    @Override
    public int compareTo(Nehnutelnost o) {
        return 0;
    }
}
