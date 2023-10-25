import java.util.ArrayList;
import java.util.Iterator;
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

    public QuadTreeNodeKeys<T> insert(QuadTreeNode<T> p_node, double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, T data) {
        QuadTreeNodeKeys<T> insertedKeys = null;

        if (!(p_minXElement >= minX && p_maxXElement <= maxX && p_minYElement >= minY && p_maxYElement <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        if (root == null) {
            root = new QuadTreeNode<T>(this.minX, this.minY, this.maxX, this.maxY, 1, null);
            insertedKeys = root.insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
            currentMaxID++;
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
                        insertedKeys = new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
                        currentMaxID++;
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
                        insertedKeys = new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
                        currentMaxID++;
                        end = true;
                    }
                    else if(help_node.intersectsInnerLines(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // original node intersects new borders
                        help_node.getIntersectingData().add(new QuadTreeNodeKeys(helpData.getID(),helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement, helpData.getData()));

                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // node contains new data
                                insertedKeys = help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
                                currentMaxID++;
                                end = true;
                                break;
                            }
                        }
                    } else if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // new data intersects new borders
                        insertedKeys = new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
                        currentMaxID++;

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
                                insertedKeys = help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
                                currentMaxID++;
                                end = true;
                            }

                            if (help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) { // node contains old data
                                help_node.getSons()[i].insertData(helpMinXElement,helpMinYElement, helpMaxXElement, helpMaxYElement,helpData.getID(), helpData.getData());
                            }
                        }
                    }
                } else { // node doesn't contain data
                    if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) { // new element intersects inner borders of node -> add to list
                        insertedKeys = new QuadTreeNodeKeys(currentMaxID,p_minXElement,p_minYElement, p_maxXElement, p_maxYElement, data);
                        help_node.getIntersectingData().add(insertedKeys);
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
                                        insertedKeys = help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, currentMaxID, data);
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

        return insertedKeys;
    }

    public ArrayList<T> find(QuadTreeNode<T> p_node, double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind) {
        if (!(p_minXToFind >= minX && p_maxXToFind <= maxX && p_minYToFind >= minY && p_maxYToFind <= maxY)){
            return null;
            //TODO EXCEPTION
        }

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
                    stack.push(currentNode.getSons()[i]); //TODO possible rework go only  through sons that at least intersect the area we search for
                }
            }
        }

        return data;
    }

    public QuadTreeNodeKeys<T> delete(double p_minXToFind, double p_minYToFind, double p_maxXToFind, double p_maxYToFind, int p_ID) {
        if (!(p_minXToFind >= minX && p_maxXToFind <= maxX && p_minYToFind >= minY && p_maxYToFind <= maxY)){
            return null;
            //TODO EXCEPTION
        }

        int countRecords = 0;
        boolean end = false;

        QuadTreeNodeKeys<T> dataFound = null;
        QuadTreeNodeKeys<T> helpKeys = new QuadTreeNodeKeys<T>(p_ID,p_minXToFind,p_minYToFind, p_maxXToFind, p_maxYToFind, null);
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
                if (currentNode.getNodeKeys().equals(root.getNodeKeys())) {
                    dataFound = currentNode.removeData(); // key found in root
                    this.root = null;
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

                                if (currentNode.getParent().getSons()[i].getNodeKeys() != null && currentNode.getParent().getSons()[i].getData() != null) { // count how many records are among sons, 1 -> remove whole level, > 1 -> dont remove whole level
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

    public void changeMaxHeight(int p_max_height) {
        int helpMaxHeight = this.maxLevel;
        Stack<QuadTreeNode<T>> stack = new Stack<QuadTreeNode<T>>();
        QuadTreeNodeKeys<T> helpKeys = null;
        QuadTreeNode<T> nodeOnNewLevel = null;


        this.maxLevel = p_max_height;
        stack.push(this.root);

        if (helpMaxHeight < p_max_height) {

            while (!stack.isEmpty()) {
                QuadTreeNode<T> currentNode = stack.pop();

                if (currentNode.getLevel() == helpMaxHeight &&
                        currentNode.getIntersectingData().size() != 0)
                {

                    for (int i = 0; i < currentNode.getIntersectingData().size() ; i++) {
                        this.insert(currentNode, currentNode.getIntersectingData().get(i).getMinXElement(), currentNode.getIntersectingData().get(i).getMinYElement(),
                                    currentNode.getIntersectingData().get(i).getMaxXElement(), currentNode.getIntersectingData().get(i).getMaxYElement(),
                                    currentNode.getIntersectingData().get(i).getData());
                        currentNode.getIntersectingData().remove(i);
                    }

                    LinkedList<QuadTreeNodeKeys<T>> helpIntersectingData = new LinkedList<QuadTreeNodeKeys<T>>();

                    for (QuadTreeNodeKeys<T> keys : currentNode.getIntersectingData() ) {
                        if (!currentNode.intersectsInnerLines(keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement()))
                        {
                            helpIntersectingData.add(keys);
                            this.insert(currentNode, keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(),keys.getData());
                        }
                    }
                    currentNode.getIntersectingData().removeAll(helpIntersectingData);

                }

                if (currentNode != null && currentNode.getSons() != null) {
                    for (int i = 0; i < 4; i++) { // go through sons of node
                        stack.push(currentNode.getSons()[i]);
                    }
                }
            }

        } else if (helpMaxHeight > p_max_height) {

            while (!stack.isEmpty()) {
                QuadTreeNode<T> currentNode = stack.pop();
                LinkedList<QuadTreeNodeKeys<T>> keys_list = new LinkedList<QuadTreeNodeKeys<T>>();

                if (currentNode != null && currentNode.getSons() != null) {
                    for (int i = 0; i < 4; i++) { // go through sons of node
                        stack.push(currentNode.getSons()[i]);
                    }
                }

                if (currentNode.getLevel() > p_max_height )
                {
                    if (currentNode.getNodeKeys() != null) {
                        keys_list.add(currentNode.getNodeKeys());
                    }

                    for (QuadTreeNodeKeys<T> keys : currentNode.getIntersectingData() ) {
                        keys_list.add(keys);
                    }

                    if (keys_list.size() != 0) {
                        nodeOnNewLevel = currentNode;

                        while(nodeOnNewLevel.getLevel() != p_max_height) {
                            nodeOnNewLevel = nodeOnNewLevel.getParent();
                        }

                        if (nodeOnNewLevel != null ) {
                            if (nodeOnNewLevel.getNodeKeys() == null) {
                                helpKeys = keys_list.removeFirst();
                                nodeOnNewLevel.insertData(helpKeys.getMinXElement(),helpKeys.getMinYElement(),helpKeys.getMaxXElement(),helpKeys.getMaxYElement(),helpKeys.getID(),helpKeys.getData());
                            }
                            nodeOnNewLevel.getIntersectingData().addAll(keys_list);
                        }
                    }
                    currentNode.getParent().setSons(null);
                }
            }

        }
    }

    public QuadTreeNode<T> getRoot() {
        return root;
    }
}
