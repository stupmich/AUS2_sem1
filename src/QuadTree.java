import java.util.ArrayList;
import java.util.Stack;

public class QuadTree<T extends Comparable<T>>  {
    private QuadTreeNode<T> root;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private int maxLevel;
    private int currentMaxID;

    public QuadTree(double p_minX, double p_minY, double p_maxX, double p_maxY, int p_maxLevel) {
        this.minX = p_minX;
        this.minY = p_minY;
        this.maxX = p_maxX;
        this.maxY = p_maxY;
        this.root = null;
        this.maxLevel = p_maxLevel;
        currentMaxID = 0;
    }

    public boolean insert(double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, T data) {
        boolean inserted = false;

        if (root == null) {
            root = new QuadTreeNode<T>(this.minX, this.minY, this.maxX, this.maxY, 1, null);
            root.insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
            currentMaxID++;
            inserted = true;
        } else {
            boolean end = false;
            QuadTreeNode<T> help_node = root;
            T helpData = null;
            double helpMinXElement;
            double helpMinYElement;
            double helpMaxXElement;
            double helpMaxYElement;

            while (end == false) {
                if(help_node.getNodeKeys() != null) {
                    if (help_node.getLevel() == this.maxLevel) { // reached max depth -> add data to list
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data));
                        currentMaxID++;
                        inserted = true;
                        break;
                    }

                    helpMinXElement = help_node.getMinXElement();
                    helpMinYElement = help_node.getMinYElement();
                    helpMaxXElement = help_node.getMaxXElement();
                    helpMaxYElement = help_node.getMaxYElement();
                    helpData = help_node.removeData();

                    help_node.split();

                    if (help_node.intersectsInnerLines(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement) &&
                            help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) {
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(currentMaxID,helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement, helpData));
                        currentMaxID++;
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data));
                        currentMaxID++;
                        end = true;
                        inserted = true;
                    }
                    else if(help_node.intersectsInnerLines(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // original node intersects new borders
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(currentMaxID,helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement, helpData));
                        currentMaxID++;

                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // node contains new data
                                help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
                                currentMaxID++;
                                end = true;
                                inserted = true;
                                break;
                            }
                        }
                    } else if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // new data intersects new borders
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data));
                        currentMaxID++;

                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // node contains old data
                                help_node.getSons()[i].insertData(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement, currentMaxID, helpData);
                                currentMaxID++;
                                end = true;
                                inserted = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)
                                    && help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) // both new data and old data are contained in same node
                            {
                                help_node = help_node.getSons()[i];
                                help_node.insertData(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement, currentMaxID, helpData); // store data, will be deleted in next cycle
                                currentMaxID++;
                                break;
                            }

                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // node contains new data
                                help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
                                currentMaxID++;
                                end = true;
                                inserted = true;
                            }

                            if (help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // node contains old data
                                help_node.getSons()[i].insertData(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement, currentMaxID, helpData);
                                currentMaxID++;
                            }
                        }
                    }
                } else { // node doesn't contain data
                    if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // new element intersects inner borders of node -> add to list
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data));
                        currentMaxID++;
                        end = true;
                    } else {
                        for (int i = 0; i < 4; i++) { // go through sons of node
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement))
                            {
                                if (help_node.getSons()[i].getNodeKeys() != null) { // node has data -> new cycle, new split
                                    help_node = help_node.getSons()[i];
                                } else {
                                    if (help_node.getSons()[i].getSons() != null) { // node doesn't have data + has sons -> new cycle, go through son's sons
                                        help_node = help_node.getSons()[i];
                                    } else { // no data in node + no sons -> insert data
                                        help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
                                        currentMaxID++;
                                        end = true;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        return inserted;
    }

    public ArrayList<T> find(QuadTreeNode<T> p_node, double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        ArrayList<T> data = new ArrayList<T>();
        Stack<QuadTreeNode<T>> stack = new Stack<QuadTreeNode<T>>();
        QuadTreeNode<T> helpNode = new QuadTreeNode<T>(p_minXToFind, p_minYToFind, p_maxXToFind, p_maxYToFind,0, null);

        if (p_node == null) {
            return null;
        }

        stack.push(p_node);

        while (!stack.isEmpty()) {
            QuadTreeNode<T> currentNode = stack.pop();

            if ( currentNode.getNodeKeys() != null &&
                    currentNode.getData() != null &&
                        helpNode.contains(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement()))
            {
                data.add(currentNode.getData());
            }

            for ( QuadTreeNodeKeys<T> key : currentNode.getIntersectingData())
            {
                if (helpNode.contains(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement())) {
                    data.add(key.getData());
                }

            }

            if (currentNode != null && currentNode.getSons() != null) {
                for (int i = 0; i < 4; i++) { // go through sons of node
                    stack.push(currentNode.getSons()[i]);
                }
            }
        }

        return data;
    }

    public QuadTreeNode<T> getRoot() {
        return root;
    }
}
