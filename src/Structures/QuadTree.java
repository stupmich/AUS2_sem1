package Structures;

import Entities.GPS;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.LinkedList;
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


    private QuadTreeNodeKeys<T> insert(QuadTreeNode<T> p_node, double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, T data, int p_id) {
        QuadTreeNodeKeys<T> insertedKeys = null;

        if (!(p_minXElement >= minX && p_maxXElement <= maxX && p_minYElement >= minY && p_maxYElement <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        if (root == null) {
            root = new QuadTreeNode<T>(this.minX, this.minY, this.maxX, this.maxY, 1, null);
            insertedKeys = root.insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, p_id, data);
        } else {
            boolean end = false;
            QuadTreeNode<T> help_node = p_node;
            QuadTreeNodeKeys<T> helpData = null;
            double helpMinXElement;
            double helpMinYElement;
            double helpMaxXElement;
            double helpMaxYElement;

            while (end == false) {
                if(help_node.getNodeKeys() != null) {
                    if (help_node.getLevel() == this.maxLevel) { // reached max depth -> add data to list
                        insertedKeys = new QuadTreeNodeKeys(p_id,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
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
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(helpData.getID(),helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement, helpData.getData()));
                        insertedKeys = new QuadTreeNodeKeys(p_id,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
//                        help_node.setSons(null); //no need for sons, both are intersecting
                        end = true;
                    }
                    else if(help_node.intersectsInnerLines(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // original node intersects new borders
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(helpData.getID(),helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement, helpData.getData()));

                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // node contains new data
                                insertedKeys = help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, p_id, data);
                                end = true;
                                break;
                            }
                        }
                    } else if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // new data intersects new borders
                        insertedKeys = new QuadTreeNodeKeys(p_id,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);

                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // node contains old data
                                help_node.getSons()[i].insertData(helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement,helpData.getID(), helpData.getData());
                                end = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)
                                    && help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) // both new data and old data are contained in same node
                            {
                                help_node = help_node.getSons()[i];
                                help_node.insertData(helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement,helpData.getID(), helpData.getData()); // store data, will be deleted in next cycle
                                break;
                            }

                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // node contains new data
                                insertedKeys = help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, p_id, data);
                                end = true;
                            }

                            if (help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // node contains old data
                                help_node.getSons()[i].insertData(helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement,helpData.getID(), helpData.getData());
                            }
                        }
                    }
                } else { // node doesn't contain data
                    if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // new element intersects inner borders of node -> add to list
                        insertedKeys = new QuadTreeNodeKeys(p_id,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
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
                                        insertedKeys = help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, p_id, data);
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

        return insertedKeys;
    }

    public QuadTreeNodeKeys<T> insert(double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, T data) {
        this.currentMaxID++;
        return this.insert(this.root,p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, data, this.currentMaxID);
    }

    private LinkedList<QuadTreeNodeKeys<T>> find(QuadTreeNode<T> p_node, double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        if (!(p_minXToFind >= minX && p_maxXToFind <= maxX && p_minYToFind >= minY && p_maxYToFind <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        LinkedList<QuadTreeNodeKeys<T>> data = new LinkedList<QuadTreeNodeKeys<T>>();
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
                data.add(currentNode.getNodeKeys());
            }

            for ( QuadTreeNodeKeys<T> key : currentNode.getIntersectingData())
            {
                if (helpNode.contains(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement())) {
                    data.add(key);
                }

            }

            if (currentNode != null && currentNode.getSons() != null) {
                for (int i = 0; i < 4; i++) { // go through sons of node
                    stack.push(currentNode.getSons()[i]); //TODO possible rework go only  through sons that at least intersect the area we search for
                }
            }
        }

        return data;
    }

    public QuadTreeNodeKeys<T> findByData(T p_data_to_find) {
        if (p_data_to_find == null){
            return null;
            //TODO EXCEPTION
        }

        Stack<QuadTreeNode<T>> stack = new Stack<QuadTreeNode<T>>();

        if (root == null) {
            return null;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            QuadTreeNode<T> currentNode = stack.pop();

            if ( currentNode.getNodeKeys() != null && currentNode.getData() != null ) {
                if (currentNode.getData().equals(p_data_to_find)) {
                    return currentNode.getNodeKeys();
                }
            }

            for ( QuadTreeNodeKeys<T> key : currentNode.getIntersectingData())
            {
                if (key.getData().equals(p_data_to_find)) {
                    return key;
                }
            }

            if (currentNode != null && currentNode.getSons() != null) {
                for (int i = 0; i < 4; i++) { // go through sons of node
                    stack.push(currentNode.getSons()[i]);
                }
            }
        }

        return null;
    }


    public LinkedList<QuadTreeNodeKeys<T>> find(double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        return this.find(this.root, p_minXToFind, p_minYToFind, p_maxXToFind, p_maxYToFind);
    }

    private LinkedList<QuadTreeNodeKeys<T>> findContainedOrIntersecting(QuadTreeNode<T> p_node, double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        if (!(p_minXToFind >= minX && p_maxXToFind <= maxX && p_minYToFind >= minY && p_maxYToFind <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        LinkedList<QuadTreeNodeKeys<T>> data = new LinkedList<QuadTreeNodeKeys<T>>();
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
                    (helpNode.contains(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement()) ||
                            helpNode.intersects(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement()) ||
                                helpNode.isContainedBy(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement())))

            {
                data.add(currentNode.getNodeKeys());
            }

            for ( QuadTreeNodeKeys<T> key : currentNode.getIntersectingData())
            {
                if (helpNode.contains(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement()) ||
                        helpNode.intersects(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement()) ||
                        helpNode.isContainedBy(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement())) {
                    data.add(key);
                }

            }

            if (currentNode != null && currentNode.getSons() != null) {
                for (int i = 0; i < 4; i++) { // go through sons of node
                    stack.push(currentNode.getSons()[i]); //TODO possible rework go only  through sons that at least intersect the area we search for
                }
            }
        }

        return data;
    }

    private LinkedList<T> findContainedOrIntersectingData(QuadTreeNode<T> p_node, double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        if (!(p_minXToFind >= minX && p_maxXToFind <= maxX && p_minYToFind >= minY && p_maxYToFind <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        LinkedList<T> data = new LinkedList<T>();
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
                    (helpNode.contains(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement()) ||
                            helpNode.intersects(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement()))
            )

            {
                data.add(currentNode.getNodeKeys().getData());
            }

            for ( QuadTreeNodeKeys<T> key : currentNode.getIntersectingData())
            {
                if (helpNode.contains(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement()) ||
                        helpNode.intersects(key.getMinXElement(),key.getMinYElement(),key.getMaxXElement(),key.getMaxYElement())) {
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

    public LinkedList<QuadTreeNodeKeys<T>> findContainedOrIntersecting(double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        return this.findContainedOrIntersecting(this.root, p_minXToFind, p_minYToFind, p_maxXToFind, p_maxYToFind);
    }

    public LinkedList<T> findContainedOrIntersectingData(double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        return this.findContainedOrIntersectingData(this.root, p_minXToFind, p_minYToFind, p_maxXToFind, p_maxYToFind);
    }

    public T deleteByData(T p_data_to_remove) {
        QuadTreeNodeKeys<T> keys = this.findByData(p_data_to_remove);

        return (this.delete(keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(), keys.getID(), keys.getData())).getData();
    }

    public QuadTreeNodeKeys<T> delete(double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind, int p_ID, T data) {
        if (!(p_minXToFind >= minX && p_maxXToFind <= maxX && p_minYToFind >= minY && p_maxYToFind <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        int countRecords = 0;
        boolean end = false;

        QuadTreeNodeKeys<T> dataFound = null;
        QuadTreeNodeKeys<T> helpKeys = new QuadTreeNodeKeys<T>(p_ID,p_minXToFind,p_minYToFind, p_maxXToFind, p_maxYToFind, data);
        Stack<QuadTreeNode<T>> stack = new Stack<QuadTreeNode<T>>();
        QuadTreeNode<T> helpNode = new QuadTreeNode<T>(p_minXToFind, p_minYToFind, p_maxXToFind, p_maxYToFind,0, null);

        if (root == null) {
            return null;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            QuadTreeNode<T> currentNode = stack.pop();

            if ( currentNode.getNodeKeys() != null &&
                    currentNode.getData() != null &&
                    helpNode.contains(currentNode.getMinXElement(),currentNode.getMinYElement(),currentNode.getMaxXElement(),currentNode.getMaxYElement()))
            {
                if (helpKeys.equals(root.getNodeKeys())) {
                    dataFound = currentNode.removeData(); // key found in root

                    if (currentNode.getIntersectingData().size() == 0) {
                        this.root = null;
                    }

                } else  if (currentNode.getNodeKeys().equals(helpKeys)) {
                    dataFound = currentNode.removeData(); // key found in node

                    if (currentNode.getLevel() == this.maxLevel && currentNode.getIntersectingData().size() != 0 ) { // TODO TEST THIS!!!!!
                        currentNode.insertData(currentNode.getIntersectingData().getFirst().getMinXElement(),currentNode.getIntersectingData().getFirst().getMinYElement(),
                                currentNode.getIntersectingData().getFirst().getMaxXElement(), currentNode.getIntersectingData().getFirst().getMaxYElement(),
                                currentNode.getIntersectingData().getFirst().getID(), currentNode.getIntersectingData().getFirst().getData());
                        currentNode.getIntersectingData().removeFirst();
                    }

                    while(end = true) {
                        if (currentNode.getParent() != null ) {
                            for (int i = 0; i < 4; i++) { // go through sons of node
                                if (currentNode.getParent().getSons()[i].getSons() != null) { // if parent of current node has son that has sons, dont remove whole level
                                    return dataFound;
                                }

                                if ((currentNode.getParent().getSons()[i].getNodeKeys() != null && currentNode.getParent().getSons()[i].getData() != null) ||
                                        (currentNode.getParent().getSons()[i].getIntersectingData() != null && currentNode.getParent().getSons()[i].getIntersectingData().size() != 0 )) { // count how many records are among sons, 1 -> remove whole level, > 1 -> dont remove whole level
                                    countRecords++;
                                    helpNode = currentNode.getParent().getSons()[i];
                                }

                            }
                        } else {
                            return dataFound;
                        }

                        if (countRecords == 1 && helpNode.getParent().getIntersectingData().size() == 0) {
                            helpNode.getParent().insertData(helpNode.getMinXElement(), helpNode.getMinYElement(), helpNode.getMaxXElement(), helpNode.getMaxYElement(), helpNode.getNodeKeys().getID(), helpNode.getData());
                            helpNode.getParent().setSons(null);
                            currentNode = helpNode.getParent();
                            countRecords = 0;
                        } else if (countRecords == 0 && currentNode.getParent().getIntersectingData().size() == 1) {
                            helpKeys = currentNode.getParent().getIntersectingData().removeFirst();
                            currentNode.getParent().insertData(helpKeys.getMinXElement(), helpKeys.getMinYElement(), helpKeys.getMaxXElement(), helpKeys.getMaxYElement(), helpKeys .getID(), helpKeys.getData());
                            currentNode.getParent().setSons(null);
                            currentNode = currentNode.getParent();
                            countRecords = 0;
                        } else {
                            return dataFound;
                        }
                    }
                }
            }

            for ( QuadTreeNodeKeys<T> key : currentNode.getIntersectingData())
            {
                if (key.equals(helpKeys)) {
                    dataFound = key;
                    currentNode.getIntersectingData().remove(dataFound);
                    return dataFound;
                }
            }

            if (currentNode != null && currentNode.getSons() != null) {
                for (int i = 0; i < 4; i++) { // go through sons of node
                    if (currentNode.getSons()[i].intersects(p_minXToFind,p_minYToFind, p_maxXToFind, p_maxYToFind)) {
                        stack.push(currentNode.getSons()[i]); //TODO possible rework go only  through sons that at least intersect the area we search for
                    }
                }
            }
        }

        return dataFound;
    }

    public void changeMaxHeight(int p_new_max_height) {
        int currentMaxHeight = this.maxLevel;
        this.maxLevel = p_new_max_height;

        if (this.root == null) {
            return;
        }

        Stack<QuadTreeNode<T>> stack = new Stack<>();
        stack.push(this.root);

        if (currentMaxHeight < p_new_max_height) {
            while (!stack.isEmpty()) {
                QuadTreeNode<T> currentNode = stack.pop();

                if (currentNode.getLevel() == currentMaxHeight && !currentNode.getIntersectingData().isEmpty()) { // data that are supposed to be lower
                    LinkedList<QuadTreeNodeKeys<T>> keysIntersectingData = new LinkedList<>(currentNode.getIntersectingData());

                    for (QuadTreeNodeKeys<T> keys : keysIntersectingData) { // go through intersecting data, if intersects boundaries in next node leave it in list, else insert lower
                        if (!currentNode.intersectsInnerLines(keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement())) {
                            this.insert(currentNode, keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(), keys.getData(), keys.getID());
                            currentNode.getIntersectingData().remove(keys);
                        }
                    }
                }

                if (currentNode.getSons() != null) {
                    for (int i = 0; i < 4; i++) {
                        stack.push(currentNode.getSons()[i]);
                    }
                }
            }
        } else if (currentMaxHeight > p_new_max_height) {
            while (!stack.isEmpty()) {
                QuadTreeNode<T> currentNode = stack.pop();
                LinkedList<QuadTreeNodeKeys<T>> keysList = new LinkedList<>();

                if (currentNode.getSons() != null) { //get sons before, later will set them as null
                    for (int i = 0; i < 4; i++) {
                        stack.push(currentNode.getSons()[i]);
                    }
                }

                if (currentNode.getLevel() > p_new_max_height) {
                    if (currentNode.getNodeKeys() != null) {
                        keysList.add(currentNode.getNodeKeys()); // get data from node
                    }

                    keysList.addAll(currentNode.getIntersectingData()); // get intersecting data

                    if (!keysList.isEmpty()) {
                        QuadTreeNode<T> nodeOnNewHeight = findNodeOnNewHeight(currentNode, p_new_max_height); // go up in tree to the new max height

                        if (nodeOnNewHeight != null) {
                            if (nodeOnNewHeight.getNodeKeys() == null) { // if there is no data insert data from son
                                QuadTreeNodeKeys<T> helpKeys = keysList.removeFirst();
                                nodeOnNewHeight.insertData(helpKeys.getMinXElement(), helpKeys.getMinYElement(), helpKeys.getMaxXElement(), helpKeys.getMaxYElement(), helpKeys.getID(), helpKeys.getData());
                            }
                            nodeOnNewHeight.getIntersectingData().addAll(keysList); // add other data to list
                        }
                    }
                    currentNode.getParent().setSons(null); // delete reference to next nodes
                }
            }
        }
    }

    private QuadTreeNode<T> findNodeOnNewHeight(QuadTreeNode<T> node, int newMaxHeight) {
        QuadTreeNode<T> nodeOnNewHeight = node;

        while (nodeOnNewHeight.getLevel() != newMaxHeight) {
            nodeOnNewHeight = nodeOnNewHeight.getParent();
        }

        return nodeOnNewHeight;
    }

    public QuadTree<T> getOptimizedTree() {
        LinkedList<QuadTreeNodeKeys<T>> tree_keys = this.find(this.minX, this.minY, this.maxX, this.maxY);
        LinkedList<QuadTreeNodeKeys<T>> intersectingDataCurrent = this.findAllIntersectingData();
        int bestNumberOfIntersectingData = intersectingDataCurrent.size();

        // no intersecting data -> tree is perfecto
        if (bestNumberOfIntersectingData == 0) {
            return this;
        }

        QuadTree<T> optimized_tree = this;
        int currentMaxLevel = this.maxLevel;

        double min_max_X = Double.MIN_VALUE;
        double max_min_X = Double.MAX_VALUE;
        double min_max_Y = Double.MIN_VALUE;
        double max_min_Y = Double.MAX_VALUE;

        for (Structures.QuadTreeNodeKeys<T> keys : tree_keys ) {
            if (keys.getMaxXElement() > min_max_X) {
                min_max_X = keys.getMaxXElement();
            }
            if (keys.getMaxYElement() > min_max_Y) {
                min_max_Y = keys.getMaxYElement();
            }

            if (keys.getMinXElement() < max_min_X) {
                max_min_X = keys.getMinXElement();
            }
            if (keys.getMinYElement() < max_min_Y) {
                max_min_Y = keys.getMinYElement();
            }
        }

        // try to change max height of tree, max current * 2
        for (int newMaxLevel = currentMaxLevel; newMaxLevel <= currentMaxLevel * 2; newMaxLevel++) {

            // try to resize boundaries, <90% of original size, 110% of original size>
            for (double sizeFactor = 0.9; sizeFactor <= 1.1; sizeFactor += 0.1) {

                // calculate new size based on the size factor
                double newWidth = (this.maxX - this.minX) * sizeFactor;
                double newHeight = (this.maxY - this.minY) * sizeFactor;

                // shift boundaries by 10 percent of size
                double xShift = newWidth * 0.1;
                double yShift = newHeight * 0.1;

                // Try shifting the center of the QuadTree within the range of the original size
                for (double xChange = this.minX - xShift; xChange <= this.maxX + xShift; xChange += 1) {
                    for (double yChange = this.minY - yShift; yChange <= this.maxY + yShift; yChange += 1) {

                        double newMinX = xChange - newWidth / 2;
                        double newMaxX = xChange + newWidth / 2;
                        double newMinY = yChange - newHeight / 2;
                        double newMaxY = yChange + newHeight / 2;

                        if (newMinX > max_min_X) {
                            continue;
                        }

                        if (newMinY > max_min_Y) {
                            continue;
                        }

                        if (newMaxX < min_max_X) {
                            continue;
                        }

                        if (newMaxY < min_max_Y) {
                            continue;
                        }

                        QuadTree<T> temp_tree = new QuadTree<>(newMinX, newMinY, newMaxX, newMaxY, newMaxLevel);
                        temp_tree.currentMaxID = this.currentMaxID;

                        // insert all data from original tree
                        for (QuadTreeNodeKeys<T> keys : tree_keys) {
                            temp_tree.insert(temp_tree.getRoot(), keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(), keys.getData(), keys.getID());
                        }

                        LinkedList<QuadTreeNodeKeys<T>> new_tree_keys = temp_tree.find(temp_tree.getRoot(),temp_tree.minX, temp_tree.minY, temp_tree.maxX, temp_tree.maxY);

                        // check if all data were inserted from original tree
                        if (new_tree_keys.size() == tree_keys.size()) {
                            LinkedList<QuadTreeNodeKeys<T>> intersectingDataNew = temp_tree.findAllIntersectingData();

                            // check if new tree is better than current best
                            if (intersectingDataNew.size() < bestNumberOfIntersectingData) {
                                bestNumberOfIntersectingData = intersectingDataNew.size();
                                optimized_tree = temp_tree;

                                // return if new tree is perfecto
                                if (bestNumberOfIntersectingData == 0) {
                                    return optimized_tree;
                                }
                            }
                        }
                    }
                }
            }
        }

        return optimized_tree;
    }

    public QuadTree<T> optimize() {
        QuadTree<T> optimized_tree = this.getOptimizedTree();

        if (optimized_tree != null) {
            if (this.evaluateHealth(optimized_tree) < 0.9) {
                return optimized_tree;
            }
        }

        return this;
    }

    public LinkedList<QuadTreeNodeKeys<T>> findAllIntersectingData() {
        LinkedList<QuadTreeNodeKeys<T>> intersectingData = new LinkedList<QuadTreeNodeKeys<T>>();
        Stack<QuadTreeNode<T>> stack = new Stack<QuadTreeNode<T>>();

        if (this.getRoot() == null || this == null) {
            return null;
        }

        stack.push(this.getRoot());

        while (!stack.isEmpty()) {
            QuadTreeNode<T> currentNode = stack.pop();

            intersectingData.addAll(currentNode.getIntersectingData());

            if (currentNode != null && currentNode.getSons() != null) {
                for (int i = 0; i < 4; i++) { // go through sons of node
                    stack.push(currentNode.getSons()[i]);
                }
            }
        }

        return intersectingData;
    }

    public double evaluateHealth(QuadTree<T> p_tree) {
        LinkedList<QuadTreeNodeKeys<T>> intersectingDataOptimized = p_tree.findAllIntersectingData();
        LinkedList<QuadTreeNodeKeys<T>> intersectingDataCurrent = this.findAllIntersectingData();

        double health = ((double)intersectingDataOptimized.size() / intersectingDataCurrent.size());

        return health;
    }

    public QuadTreeNode<T> getRoot() {
        return root;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void saveToFile(String file_name) {
        if (root == null ) {
            return;
        }

        Stack<QuadTreeNode<T>> stack = new Stack<QuadTreeNode<T>>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name))) {

            writer.write(this.minX + ";" + this.minY + ";" + this.maxX + ";" + this.maxY + ";" + this.maxLevel);
            writer.write("\n");

            stack.push(this.getRoot());

            while (!stack.isEmpty()) {
                QuadTreeNode<T> currentNode = stack.pop();

                if (currentNode.getNodeKeys() != null) {
                    writer.write(currentNode.getNodeKeys().toString());
                    writer.write("\n");
                }

                if (currentNode.getIntersectingData().size() != 0 &&currentNode.getIntersectingData() != null) {
                    for(QuadTreeNodeKeys<T> keys : currentNode.getIntersectingData() ) {
                        writer.write(keys.toString());
                        writer.write("\n");
                    }
                }


                if (currentNode != null && currentNode.getSons() != null) {
                    for (int i = 0; i < 4; i++) { // go through sons of node
                        stack.push(currentNode.getSons()[i]);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QuadTree<T> loadFromFile(String file_name, Class<T> p_clazz) {
        Class<T> clazz = p_clazz;
        QuadTree<T> loadedTree = null;

        int linesWentThrough = 0;
        String line;

        double min_x = 0;
        double min_y = 0;
        double max_x = 0;
        double max_y = 0;
        T data = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (linesWentThrough == 0) { // Load attributes of tree
                    linesWentThrough++;
                    loadedTree = new QuadTree<T>(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                                                 Double.parseDouble(parts[3]),Integer.parseInt(parts[4]));
                    continue;
                }

                if (linesWentThrough % 2 == 1) { // odd lines are boundaries
                    min_x = Double.parseDouble(parts[1]);
                    min_y = Double.parseDouble(parts[2]);
                    max_x = Double.parseDouble(parts[3]);
                    max_y = Double.parseDouble(parts[4]);
                } else { // even lines are data
                    try {
                        Constructor<T> constructor = clazz.getDeclaredConstructor(); // dynamic constructor
                        data = constructor.newInstance();

                        Field[] fields = clazz.getDeclaredFields(); // get attributes

                        for (int i = 0; i < fields.length ; i++) { // go through attributes and set their value loaded from file
                            Field field = fields[i];
                            field.setAccessible(true);

                            if (i < parts.length) {
                                String value = parts[i];
                                Class<?> fieldType = field.getType();

                                if (fieldType == String.class) {
                                    field.set(data, value);
                                } else if (fieldType == int.class || fieldType == Integer.class) {
                                    field.set(data, Integer.parseInt(value));
                                } else if (fieldType == double.class || fieldType == Double.class) {
                                    field.set(data, Double.parseDouble(value));
                                } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                                    field.set(data, Boolean.parseBoolean(value));
                                } else if (fieldType == char.class || fieldType == Character.class) {
                                    field.set(data, value.charAt(0));
                                } else if (fieldType == long.class || fieldType == Long.class) {
                                    field.set(data, Long.parseLong(value));
                                } else if (fieldType == float.class || fieldType == Float.class) {
                                    field.set(data, Float.parseFloat(value));
                                } else if (fieldType == short.class || fieldType == Short.class) {
                                    field.set(data, Short.parseShort(value));
                                } else if (fieldType == byte.class || fieldType == Byte.class) {
                                    field.set(data, Byte.parseByte(value));
                                } else {
                                    // Try to use a constructor that accepts a String for complex types
                                    try {
                                        Constructor<?> constructorAttribute = fieldType.getConstructor(String.class);
                                        field.set(data, constructorAttribute.newInstance(value));
                                    } catch (NoSuchMethodException e) {
                                        throw new IllegalArgumentException("No appropriate constructor for field type: " + fieldType, e);
                                    }
                                }
                            }
                        }

                    } catch (NoSuchMethodException e) { // primitive types and wrapper classes
                        if (clazz == String.class) {
                            String data_string = parts[0];
                            data = (T) data_string;
                        } else if (clazz == int.class || clazz == Integer.class) {
                            Integer data_int = Integer.parseInt(parts[0]);
                            data = (T) data_int;
                        } else if (clazz == double.class || clazz == Double.class) {
                            Double data_double = Double.parseDouble(parts[0]);
                            data = (T) data_double;
                        } else if (clazz == boolean.class || clazz == Boolean.class) {
                            Boolean data_bool = Boolean.parseBoolean(parts[0]);
                            data = (T) data_bool;
                        } else if (clazz == char.class || clazz == Character.class) {
                            Character data_char = parts[0].charAt(0);
                            data = (T) data_char;
                        } else if (clazz == long.class || clazz == Long.class) {
                            Long data_long = Long.parseLong(parts[0]);
                            data = (T) data_long;
                        } else if (clazz == float.class || clazz == Float.class) {
                            Float data_float = Float.parseFloat(parts[0]);
                            data = (T) data_float;
                        } else if (clazz == short.class || clazz == Short.class) {
                            Short data_short = Short.parseShort(parts[0]);
                            data = (T) data_short;
                        }
                    }

                    loadedTree.insert(min_x,min_y,max_x,max_y, data); // insert data to the tree

                }
                linesWentThrough++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loadedTree;
    }
}
