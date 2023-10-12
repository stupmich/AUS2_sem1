import java.awt.*;
import java.util.LinkedList;

public class QuadTreeNode<T extends Comparable<T> >  {
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    private QuadTreeNode<T> parent;

    private QuadTreeNode<T> NWSon;
    private QuadTreeNode<T> NESon;
    private QuadTreeNode<T> SESon;
    private QuadTreeNode<T> SWSon;

    private T data;
    private LinkedList<T> intersectingData;

    public QuadTreeNode(double minX, double minY, double maxX, double maxY, QuadTreeNode<T> parent, T data) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.parent = parent;
        this.data = data;
    }

    public void split() {
        double halfX = (this.maxX - this.minX) / 2;
        double halfY = (this.maxY - this.minY) / 2;

        NWSon = new QuadTreeNode<T>(this.minX, this.minY + halfY, this.minX + halfX, this.maxY, this, null);

//        NWSon.minX = this.minX;
//        NWSon.minY = this.minY + halfY;
//        NWSon.maxX = this.minX + halfX;
//        NWSon.maxY = this.maxY;

        NESon = new QuadTreeNode<T>(this.minX + halfX, this.minY + halfY, this.maxX, this.maxY , this, null);

//        NESon.minX = this.minX + halfX;
//        NESon.minY = this.minY + halfY;
//        NESon.maxX = this.maxX;
//        NESon.maxY = this.maxY;
        SESon = new QuadTreeNode<T>(this.minX + halfX, this.minY, this.maxX,  this.minY + halfY , this, null);

//        SESon.minX = this.minX + halfX;
//        SESon.minY = this.minY;
//        SESon.maxX = this.maxX;
//        SESon.maxY = this.minY + halfY;

        SWSon = new QuadTreeNode<T>(this.minX, this.minY, this.minX + halfX, this.minY + halfY , this, null);

//        SWSon.minX = this.minX;
//        SWSon.minY = this.minY;
//        SWSon.maxX = this.minX + halfX;
//        SWSon.maxY = this.maxY + halfY;
    }

    public boolean contains(double p_minX, double p_minY, double p_maxX, double p_maxY) {
        return (p_minX > minX && p_maxX < maxX && p_minY > minY && p_maxY < maxY);
    }

    public T removeData() {
        T helpData = this.data;
        this.data = null;
        return helpData;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public QuadTreeNode<T> getNWSon() {
        return NWSon;
    }

    public QuadTreeNode<T> getNESon() {
        return NESon;
    }

    public QuadTreeNode<T> getSESon() {
        return SESon;
    }

    public QuadTreeNode<T> getSWSon() {
        return SWSon;
    }
}
