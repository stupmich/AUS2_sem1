package Structures;

import java.util.LinkedList;

public class QuadTreeNode<T extends Comparable<T> >  {
    private double minXBoundary;
    private double minYBoundary;
    private double maxXBoundary;
    private double maxYBoundary;

    private QuadTreeNodeKeys<T> nodeKeys;

    private int level;

    private QuadTreeNode<T> parent;

    private QuadTreeNode<T>[] sons;

    private LinkedList<QuadTreeNodeKeys<T>> intersectingData;

    public QuadTreeNode(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary, int p_level, QuadTreeNode<T> parent) {
        this.minXBoundary = p_minXBoundary;
        this.minYBoundary = p_minYBoundary;
        this.maxXBoundary = p_maxXBoundary;
        this.maxYBoundary = p_maxYBoundary;
        this.level = p_level;
        this.parent = parent;
        this.intersectingData = new LinkedList<>();
    }

    public void split() {
        double halfX = (this.maxXBoundary - this.minXBoundary) / 2;
        double halfY = (this.maxYBoundary - this.minYBoundary) / 2;

        sons = new QuadTreeNode[4];
        sons[0] = new QuadTreeNode<T>(this.minXBoundary, this.minYBoundary + halfY, this.minXBoundary + halfX, this.maxYBoundary,this.level + 1, this);
        sons[1] = new QuadTreeNode<T>(this.minXBoundary + halfX, this.minYBoundary + halfY, this.maxXBoundary, this.maxYBoundary ,this.level + 1, this);
        sons[2] = new QuadTreeNode<T>(this.minXBoundary + halfX, this.minYBoundary, this.maxXBoundary,  this.minYBoundary + halfY ,this.level + 1, this);
        sons[3] = new QuadTreeNode<T>(this.minXBoundary, this.minYBoundary, this.minXBoundary + halfX, this.minYBoundary + halfY ,this.level + 1, this);
    }

    public boolean contains(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary) {
        return (p_minXBoundary >= minXBoundary && p_maxXBoundary <= maxXBoundary && p_minYBoundary >= minYBoundary && p_maxYBoundary <= maxYBoundary);
    }

    public boolean intersects(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary) {
        return !(p_maxXBoundary <= minXBoundary || p_minXBoundary >= maxXBoundary || p_maxYBoundary <= minYBoundary || p_minYBoundary >= maxYBoundary);
    }

    public boolean isContainedBy(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary) {
        return (minXBoundary >= p_minXBoundary && maxXBoundary <= p_maxXBoundary && minYBoundary >= p_minYBoundary && maxYBoundary <= p_maxYBoundary);
    }

    public boolean intersectsInnerLines(double p_minXBoundary, double p_minYBoundary, double p_maxXBoundary, double p_maxYBoundary) {
        double halfX = (this.maxXBoundary - this.minXBoundary) / 2;
        double halfY = (this.maxYBoundary - this.minYBoundary) / 2;

        // Check if the provided boundaries intersect through the lines halfX or halfY
        boolean xIntersectsHalfX = !(p_maxXBoundary < minXBoundary + halfX || p_minXBoundary > minXBoundary + halfX);
        boolean yIntersectsHalfY = !(p_maxYBoundary < minYBoundary + halfY || p_minYBoundary > minYBoundary + halfY);

        // Return true only if the provided boundaries intersect through either halfX or halfY
        return xIntersectsHalfX || yIntersectsHalfY;
    }

    public QuadTreeNodeKeys<T> insertData(double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, int p_id, T data) {
        this.nodeKeys = new QuadTreeNodeKeys(p_id,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
        return this.nodeKeys;
    }

    public QuadTreeNodeKeys<T> removeData() {
        QuadTreeNodeKeys<T> NodeKeys = this.nodeKeys;
        this.nodeKeys = null;
        return NodeKeys;
    }
    
    public T getData() {
        return this.nodeKeys.getData();
    }

    public void setData(T data) {
        this.nodeKeys.setData(data);
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
        return this.nodeKeys.getMinXElement();
    }

    public void setMinXElement(double minXElement) {
        this.nodeKeys.setMinXElement(minXElement);
    }

    public double getMinYElement() {
        return this.nodeKeys.getMinYElement();
    }

    public void setMinYElement(double minYElement) {
        this.nodeKeys.setMinYElement(minYElement);
    }

    public double getMaxXElement() {
        return this.nodeKeys.getMaxXElement();
    }

    public void setMaxXElement(double maxXElement) {
        this.nodeKeys.setMaxXElement(maxXElement);
    }

    public double getMaxYElement() {
        return this.nodeKeys.getMaxYElement();
    }

    public void setMaxYElement(double maxYElement) {
        this.nodeKeys.setMaxYElement(maxYElement);
    }

    public QuadTreeNode<T>[] getSons() {
        return sons;
    }

    public QuadTreeNode<T> getParent() {
        return parent;
    }

    public LinkedList<QuadTreeNodeKeys<T>> getIntersectingData() {
        return intersectingData;
    }

    public int getLevel() {
        return level;
    }

    public QuadTreeNodeKeys<T> getNodeKeys() {
        return nodeKeys;
    }

    public void setSons(QuadTreeNode<T>[] sons) {
        this.sons = sons;
    }
}
