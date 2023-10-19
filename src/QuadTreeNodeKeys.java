public class QuadTreeNodeKeys {
    private int ID;
    private double minXElement;
    private double minYElement;
    private double maxXElement;
    private double maxYElement;

    public QuadTreeNodeKeys(int ID, double minXElement, double minYElement, double maxXElement, double maxYElement) {
        this.ID = ID;
        this.minXElement = minXElement;
        this.minYElement = minYElement;
        this.maxXElement = maxXElement;
        this.maxYElement = maxYElement;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMinXElement() {
        return minXElement;
    }

    public void setMinXElement(double minXElement) {
        this.minXElement = minXElement;
    }

    public double getMinYElement() {
        return minYElement;
    }

    public void setMinYElement(double minYElement) {
        this.minYElement = minYElement;
    }

    public double getMaxXElement() {
        return maxXElement;
    }

    public void setMaxXElement(double maxXElement) {
        this.maxXElement = maxXElement;
    }

    public double getMaxYElement() {
        return maxYElement;
    }

    public void setMaxYElement(double maxYElement) {
        this.maxYElement = maxYElement;
    }
}
