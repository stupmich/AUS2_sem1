public class QuadTree<T extends Comparable<T>>  {
    private QuadTreeNode<T> root;

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    public QuadTree(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.root = null;
    }

    public T insert(double minX, double minY, double maxX, double maxY, T data) {
        QuadTreeNode<T> new_node = null;

        if (root == null) {
            root = new QuadTreeNode<T>(this.minX, this.minY, this.maxX, this.maxY, null, data);
            new_node = root;
        } else {
            boolean end = false;
            QuadTreeNode<T> help_node = root;
            T helpData = null;

            while (end == false) {
                if(help_node.getData() != null) {
                    helpData = help_node.removeData();
                    help_node.split();

                    if (help_node.getNWSon().contains(minX, minY, maxX, maxY)) {

                    }

                    if (help_node.getNESon().contains(minX, minY, maxX, maxY)) {

                    }

                    if (help_node.getSESon().contains(minX, minY, maxX, maxY)) {

                    }

                    if (help_node.getSWSon().contains(minX, minY, maxX, maxY)) {

                    }

                }
            }
        }

        return new_node.getData();
    }
}
