package Entities;

import java.util.LinkedList;
import java.util.Objects;

public class Nehnutelnost implements Comparable<Nehnutelnost> {
    private int supisneCislo;
    private String popis;
    private GPS minGPS;
    private GPS maxGPS;
    private LinkedList<Parcela> parcely;

    public Nehnutelnost(int supisneCislo, String popis, GPS minGPS, GPS maxGPS) {
        this.supisneCislo = supisneCislo;
        this.popis = popis;
        this.minGPS = minGPS;
        this.maxGPS = maxGPS;
        parcely = new LinkedList<Parcela>();
    }
    public Nehnutelnost() {
    }

    // Copy constructor for deep copy
    public Nehnutelnost(Nehnutelnost other) {
        this.supisneCislo = other.getSupisneCislo();
        this.popis = other.getPopis();
        this.minGPS = other.getMinGPS();
        this.maxGPS = other.getMaxGPS();
        this.parcely = new LinkedList<Parcela>(other.getParcely());
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

    @Override

    public boolean equals(Object o) {
        if (this == o) {
            return true;  // If the objects are the same, they are equal.
        }

        if (o == null || getClass() != o.getClass()) {
            return false;  // If the other object is null or of a different class, they are not equal.
        }

        Nehnutelnost other = (Nehnutelnost) o;  // Cast the object to Nehnutelnost.

        // Compare all the fields for equality.
        return this.getSupisneCislo() == other.getSupisneCislo() &&
                Objects.equals(this.getPopis(), other.getPopis()) &&
                Objects.equals(this.getMinGPS(), other.getMinGPS()) &&
                Objects.equals(this.getMaxGPS(), other.getMaxGPS()) &&
                Objects.equals(this.getParcely(), other.getParcely());
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
