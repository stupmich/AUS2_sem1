package Entities;

import java.util.LinkedList;
import java.util.Objects;

public class Nehnutelnost extends AreaObject implements Comparable<Nehnutelnost> {
    private LinkedList<Parcela> parcely;

    public Nehnutelnost(int supisneCislo, String popis, GPS minGPS, GPS maxGPS) {
        super(supisneCislo, popis, minGPS, maxGPS);
        parcely = new LinkedList<Parcela>();
    }

    // Copy constructor for deep copy
    public Nehnutelnost(Nehnutelnost other) {
        super(other.getSupisneCislo(), other.getPopis(), other.getMinGPS(), other.getMaxGPS());
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

}
