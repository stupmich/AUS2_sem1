package Entities;

import java.util.LinkedList;
import java.util.Objects;

public class Parcela implements Comparable<Parcela>{
    private int supisneCislo;
    private String popis;
    private GPS minGPS;
    private GPS maxGPS;
    private LinkedList<Nehnutelnost> nehnutelnosti;

    public Parcela(int cisloParcely, String popis, GPS minGPS, GPS maxGPS) {
        this.supisneCislo = cisloParcely;
        this.popis = popis;
        this.minGPS = minGPS;
        this.maxGPS = maxGPS;
        nehnutelnosti = new LinkedList<Nehnutelnost>();
    }

    public Parcela() {
        nehnutelnosti = new LinkedList<Nehnutelnost>();
    }

    public Parcela(Parcela other) {
        this.supisneCislo = other.getSupisneCislo();
        this.popis = other.getPopis();
        this.minGPS = other.getMinGPS();
        this.maxGPS = other.getMaxGPS();
        if (other.getNehnutelnosti() == null) {
            this.nehnutelnosti = new LinkedList<Nehnutelnost>();
        } else {
            this.nehnutelnosti = new LinkedList<Nehnutelnost>(other.getNehnutelnosti());
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;  // If the objects are the same, they are equal.
        }

        if (o == null || getClass() != o.getClass()) {
            return false;  // If the other object is null or of a different class, they are not equal.
        }

        Parcela other = (Parcela) o;

        // Compare all the fields for equality.
        return this.getSupisneCislo() == other.getSupisneCislo() &&
                Objects.equals(this.getPopis(), other.getPopis()) &&
                Objects.equals(this.getMinGPS(), other.getMinGPS()) &&
                Objects.equals(this.getMaxGPS(), other.getMaxGPS()) &&
                Objects.equals(this.getNehnutelnosti(), other.getNehnutelnosti());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nehnutelnosti);
    }

    public int getSupisneCislo() {
        return supisneCislo;
    }

    public void setSupisneCislo(int supisneCislo) {
        this.supisneCislo = supisneCislo;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public GPS getMinGPS() {
        return minGPS;
    }

    public void setMinGPS(GPS minGPS) {
        this.minGPS = minGPS;
    }

    public GPS getMaxGPS() {
        return maxGPS;
    }

    public void setMaxGPS(GPS maxGPS) {
        this.maxGPS = maxGPS;
    }

    @Override
    public String toString() {
        return   supisneCislo +
                ";" + popis +
                ";" + minGPS.toStringCSV() +
                ";" + maxGPS.toStringCSV() +
                ";";
    }
}
