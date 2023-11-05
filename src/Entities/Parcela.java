package Entities;

import java.util.LinkedList;
import java.util.Objects;

public class Parcela extends AreaObject implements Comparable<Parcela>{
    private LinkedList<Nehnutelnost> nehnutelnosti;

    public Parcela(int cisloParcely, String popis, GPS minGPS, GPS maxGPS) {
        super(cisloParcely, popis, minGPS, maxGPS);
        nehnutelnosti = new LinkedList<Nehnutelnost>();
    }

    public Parcela(Parcela other) {
        super(other.getSupisneCislo(), other.getPopis(), other.getMinGPS(), other.getMaxGPS());
        this.nehnutelnosti = new LinkedList<Nehnutelnost>(other.getNehnutelnosti());
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

    @Override
    public String toString() {
        return super.toString();
    }
}
