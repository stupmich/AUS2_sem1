package Entities;

import java.util.ArrayList;

public class Parcela extends AreaObject implements Comparable<Parcela>{
    private ArrayList<Nehnutelnost> nehnutelnosti;

    public Parcela(int cisloParcely, String popis, ArrayList<Nehnutelnost> nehnutelnosti, GPS minGPS, GPS maxGPS) {
        super(cisloParcely, popis, minGPS, maxGPS);
        this.nehnutelnosti = nehnutelnosti;
    }

    public ArrayList<Nehnutelnost> getNehnutelnosti() {
        return nehnutelnosti;
    }

    public void setNehnutelnosti(ArrayList<Nehnutelnost> nehnutelnosti) {
        this.nehnutelnosti = nehnutelnosti;
    }

    @Override
    public int compareTo(Parcela o) {
        return 0;
    }
}
