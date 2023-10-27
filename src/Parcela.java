import java.util.ArrayList;

public class Parcela implements Comparable<Parcela>{
    private int cisloParcely;
    private String popis;
    private ArrayList<Nehnutelnost> nehnutelnosti;
    private GPS minGPS;
    private GPS maxGPS;

    public Parcela(int cisloParcely, String popis, ArrayList<Nehnutelnost> nehnutelnosti, GPS minGPS, GPS maxGPS) {
        this.cisloParcely = cisloParcely;
        this.popis = popis;
        this.nehnutelnosti = nehnutelnosti;
        this.minGPS = minGPS;
        this.maxGPS = maxGPS;
    }

    public int getCisloParcely() {
        return cisloParcely;
    }

    public void setCisloParcely(int cisloParcely) {
        this.cisloParcely = cisloParcely;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public ArrayList<Nehnutelnost> getNehnutelnosti() {
        return nehnutelnosti;
    }

    public void setNehnutelnosti(ArrayList<Nehnutelnost> nehnutelnosti) {
        this.nehnutelnosti = nehnutelnosti;
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
    public int compareTo(Parcela o) {
        return 0;
    }
}
