import java.awt.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        QuadTree<Integer> tree = new QuadTree<Integer>(0,0,100,100, 10);

//        tree.insert(10,10,20,20,1);
//        tree.insert(30,30,40,40,2);
//        tree.insert(80,80,100,100,3);
//        tree.insert(50,50,60,60,4);
//        tree.insert(20,20,30,30,5);

//        tree.insert(0,0,1,1,1);
//        tree.insert(1,1,2,2,2);

        tree.insert(0,0,1,1,1);
        tree.insert(0,0,1,1,2);

    }
}