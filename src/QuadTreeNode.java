import java.awt.*;
import java.util.LinkedList;

public class QuadTreeNode<T extends Comparable<T> >  {
    private double minXBoundary;
    private double minYBoundary;
    private double maxXBoundary;
    private double maxYBoundary;

    private double minXElement;
    private double minYElement;
    private double maxXElement;
    private double maxYElement;

    private QuadTreeNode<T> parent;

    private QuadTreeNode<T> NWSon;
    private QuadTreeNode<T> NESon;
    private QuadTreeNode<T> SESon;
    private QuadTreeNode<T> SWSon;

    private QuadTreeNode<T>[] sons;

    private T data;
    private LinkedList<T> intersectingData;

//    public QuadTreeNode(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary, QuadTreeNode<T> parent, T data) {
//        this.minXBoundary = p_minXBoundary;
//        this.minYBoundary = p_minYBoundary;
//        this.maxXBoundary = p_maxXBoundary;
//        this.maxYBoundary = p_maxYBoundary;
//        this.parent = parent;
//        this.data = data;
//    }

    public QuadTreeNode(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary, QuadTreeNode<T> parent) {
        this.minXBoundary = p_minXBoundary;
        this.minYBoundary = p_minYBoundary;
        this.maxXBoundary = p_maxXBoundary;
        this.maxYBoundary = p_maxYBoundary;
        this.parent = parent;
    }

    public void split() {
        double halfX = (this.maxXBoundary - this.minXBoundary) / 2;
        double halfY = (this.maxYBoundary - this.minYBoundary) / 2;

        NWSon = new QuadTreeNode<T>(this.minXBoundary, this.minYBoundary + halfY, this.minXBoundary + halfX, this.maxYBoundary, this);

        NESon = new QuadTreeNode<T>(this.minXBoundary + halfX, this.minYBoundary + halfY, this.maxXBoundary, this.maxYBoundary , this);

        SESon = new QuadTreeNode<T>(this.minXBoundary + halfX, this.minYBoundary, this.maxXBoundary,  this.minYBoundary + halfY , this);

        SWSon = new QuadTreeNode<T>(this.minXBoundary, this.minYBoundary, this.minXBoundary + halfX, this.minYBoundary + halfY , this);

        sons = new QuadTreeNode[4];
        sons[0] = NWSon;
        sons[1] = NESon;
        sons[2] = SESon;
        sons[3] = SWSon;
    }

    public boolean contains(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary) {
        return (p_minXBoundary > minXBoundary && p_maxXBoundary < maxXBoundary && p_minYBoundary > minYBoundary && p_maxYBoundary < maxYBoundary);
    }

    public void insertData(double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, T data) {
        this.data = data;
        this.minXElement = p_minXElement;
        this.minYElement = p_minYElement;
        this.maxXElement = p_maxXElement;
        this.maxYElement = p_maxYElement;
    }

    public T removeData() {
        T helpData = this.data;
        this.data = null;
        this.minXElement = 0;
        this.minYElement = 0;
        this.maxXElement = 0;
        this.maxYElement = 0;
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

    public double getMinXBoundary() {
        return minXBoundary;
    }

    public void setMinXBoundary(double minXBoundary) {
        this.minXBoundary = minXBoundary;
    }

    public double getMinYBoundary() {
        return minYBoundary;
    }

    public void setMinYBoundary(double minYBoundary) {
        this.minYBoundary = minYBoundary;
    }

    public double getMaxXBoundary() {
        return maxXBoundary;
    }

    public void setMaxXBoundary(double maxXBoundary) {
        this.maxXBoundary = maxXBoundary;
    }

    public double getMaxYBoundary() {
        return maxYBoundary;
    }

    public void setMaxYBoundary(double maxYBoundary) {
        this.maxYBoundary = maxYBoundary;
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

    public QuadTreeNode<T>[] getSons() {
        return sons;
    }
}
