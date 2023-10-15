import java.awt.*;

public class Main {
    public static void main(String[] args) {
        QuadTree<Integer> tree = new QuadTree<Integer>(0,0,100,100);

        tree.insert(10,10,20,20,1);
        tree.insert(30,30,40,40,2);
        tree.insert(80,80,85,85,3);

    }
}