package Structures;

import java.util.Objects;

public class QuadTreeNodeKeys<T extends Comparable<T>> {
    private int ID;
    private double minXElement;
    private double minYElement;
    private double maxXElement;
    private double maxYElement;
    private T data;

    public QuadTreeNodeKeys(int ID, double minXElement, double minYElement, double maxXElement, double maxYElement, T data) {
        this.ID = ID;
        this.minXElement = minXElement;
        this.minYElement = minYElement;
        this.maxXElement = maxXElement;
        this.maxYElement = maxYElement;
        this.data = data;
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

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadTreeNodeKeys<?> that = (QuadTreeNodeKeys<?>) o;
        return ID == that.ID && Double.compare(that.minXElement, minXElement) == 0 && Double.compare(that.minYElement, minYElement) == 0 &&
                Double.compare(that.maxXElement, maxXElement) == 0 && Double.compare(that.maxYElement, maxYElement) == 0 &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, minXElement, minYElement, maxXElement, maxYElement, data);
    }
}
