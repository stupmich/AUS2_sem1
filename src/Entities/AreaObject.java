package Entities;

public class AreaObject {
    private int ID;
    private int supisneCislo;
    private String popis;
    private GPS minGPS;
    private GPS maxGPS;

    public AreaObject(int supisneCislo, String popis, GPS minGPS, GPS maxGPS) {
        this.supisneCislo = supisneCislo;
        this.popis = popis;
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
}
