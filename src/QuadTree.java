public class QuadTree<T extends Comparable<T>>  {
    private QuadTreeNode<T> root;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private int maxLevel;

    public QuadTree(double p_minX, double p_minY, double p_maxX, double p_maxY, int p_maxLevel) {
        this.minX = p_minX;
        this.minY = p_minY;
        this.maxX = p_maxX;
        this.maxY = p_maxY;
        this.root = null;
        this.maxLevel = p_maxLevel;
    }

    public boolean insert(double p_minXElement, double p_minYElement, double p_maxXElement, double p_maxYElement, T data) {
        boolean inserted = false;

        if (root == null) {
            root = new QuadTreeNode<T>(this.minX, this.minY, this.maxX, this.maxY, 1, null);
            root.insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement, data);
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
                if(help_node.getData() != null) {
                    if (help_node.getLevel() == this.maxLevel) {
                        help_node.getIntersectingData().add(data);
                        inserted = true;
                        break;
                    }

                    helpMinXElement = help_node.getMinXElement();
                    helpMinYElement = help_node.getMinYElement();
                    helpMaxXElement = help_node.getMaxXElement();
                    helpMaxYElement = help_node.getMaxYElement();
                    helpData = help_node.removeData();

                    help_node.split();

                    if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) {
                        help_node.getIntersectingData().add(data);
                        end = true;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)
                                    && help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement))
                            {
                                help_node = help_node.getSons()[i];
                                help_node.insertData(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement, helpData);
                                break;
                            }

                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) {
                                help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement,data);
                                end = true;
                                inserted = true;
                            }

                            if (help_node.getSons()[i].contains(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement)) {
                                help_node.getSons()[i].insertData(helpMinXElement, helpMinYElement, helpMaxXElement, helpMaxYElement, helpData);
                            }
                        }
                    }
                } else {
                    if (help_node.intersectsInnerLines(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement)) {
                        help_node.getIntersectingData().add(data);
                        end = true;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            if (help_node.getSons()[i].contains(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement))
                            {
                                if (help_node.getSons()[i].getData() != null) {
                                    help_node = help_node.getSons()[i];
                                } else {
                                    if (help_node.getSons()[i].getSons() != null) {
                                        help_node = help_node.getSons()[i];
                                    } else {
                                        help_node.getSons()[i].insertData(p_minXElement, p_minYElement, p_maxXElement, p_maxYElement,data);
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
}
