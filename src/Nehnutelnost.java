import java.util.ArrayList;

public class Nehnutelnost implements Comparable<Nehnutelnost> {
    private int supisneCislo;
    private String popis;
    private ArrayList<Parcela> parcely;
    private GPS minGPS;
    private GPS maxGPS;

    public Nehnutelnost(int supisneCislo, String popis, ArrayList<Parcela> parcely, GPS minGPS, GPS maxGPS) {
        this.supisneCislo = supisneCislo;
        this.popis = popis;
        this.parcely = parcely;
        this.minGPS = minGPS;
        this.maxGPS = maxGPS;
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

    public ArrayList<Parcela> getParcely() {
        return parcely;
    }

    public void setParcely(ArrayList<Parcela> parcely) {
        this.parcely = parcely;
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
    public int compareTo(Nehnutelnost o) {
        return 0;
    }
}
