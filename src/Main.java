
import Entities.GPS;
import Entities.Nehnutelnost;
import GUI.GeodetSystemGUI;
import Structures.QuadTree;
import Structures.QuadTreeNodeKeys;
import Tester.Tester;
import com.sun.nio.sctp.NotificationHandler;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        GeodetSystemGUI gui = new GeodetSystemGUI();

        Tester tester = new Tester();

        QuadTree<Integer> tree = new QuadTree<Integer>(0,0,100,100, 5);
        tester.testTree(10000,100,0,0,0,0,
                100,100,10, 100, 5, tree);

        System.out.println("filled");

        tester.testTree(10000,100,0,0,0,0,
                100,100,10, 100, 5, tree);

        System.out.println("insert");














//        QuadTree<Nehnutelnost> tree = new QuadTree<Nehnutelnost>(0,0,100,100, 10);
//
//        tree.insert(10,10,20,20,new Nehnutelnost(2,"AGA",new GPS('N',60,'E',60),
//                new GPS('N',61,'E',61)));
//        tree.insert(20,20,30,30,new Nehnutelnost(3,"AhjrjrA",new GPS('N',65,'E',65),
//                new GPS('N',66,'E',66)));
//        tree.insert(50,50,60,60,new Nehnutelnost(3,"AhjrjrA",new GPS('N',65,'E',65),
//                new GPS('N',66,'E',66)));
//        tree.saveToFile("tree.csv");
//        tree.loadFromFile("tree.csv", Nehnutelnost.class);

//        QuadTree<Boolean> tree = new QuadTree<Boolean>(0,0,100,100, 10);
//
//        Boolean d = true;
//
//        tree.insert(10.0,10.0,20.0,20.0,d);
//        tree.insert(40,40,60,60,2);
//        tree.insert(10,30,50,50,2);
//        tree.insert(10,10,20,20,1);
//        tree.insert(30,30,40,40,2);
//        tree.insert(80,80,100,100,3);
//        tree.insert(80,80,100,100,4);
//        tree.insert(50,50,60,60,5);
//        tree.insert(20,20,30,30,6);
//        tree.insert(20,90,30,92,7);
//        tree.insert(90,10,95,30,8);
//        tree.insert(99,99,100,100,9);
//        tree.insert(99,99,100,100,10);

//        tree.saveToFile("treeint.csv");
//        tree.loadFromFile("treeint.csv", Boolean.class);

//        QuadTreeNodeKeys<Integer> k = tree.findByData(2);
//
//        ArrayList<QuadTreeNodeKeys<Integer>> al = tree.findContainedOrIntersecting(tree.getRoot(),0,0,50,50);

//        Structures.QuadTree<Entities.Nehnutelnost> tree1 = new Structures.QuadTree<Entities.Nehnutelnost>(0,0,100,100, 10);
//        Entities.Nehnutelnost n = new Entities.Nehnutelnost(1,"ss",null, null, null);
//        tree1.insert(tree1.getRoot(),10,10,20,20,n);

//        tree.insert(tree.getRoot(),10,10,20,20,1);
//        tree.insert(tree.getRoot(),30,30,40,40,2);
//        tree.insert(tree.getRoot(),80,80,100,100,3);
//        tree.insert(tree.getRoot(),80,80,100,100,4);
//        tree.insert(tree.getRoot(),50,50,60,60,5);
//        tree.insert(tree.getRoot(),20,20,30,30,6);
//        tree.insert(tree.getRoot(),20,90,30,92,7);
//        tree.insert(tree.getRoot(),90,10,95,30,8);
//        tree.insert(tree.getRoot(),99,99,100,100,9);
//        tree.insert(tree.getRoot(),99,99,100,100,10);
//
//        tree.optimize();

//        tree.insert(tree.getRoot(),10,10,12,12,1);
//        tree.insert(tree.getRoot(),30,30,32,32,2);
//        tree.insert(tree.getRoot(),80,80,82,82,3);
//        tree.insert(tree.getRoot(),80,80,82,82,4);
//        tree.insert(tree.getRoot(),50,50,52,52,5);
//        tree.insert(tree.getRoot(),20,20,21,21,6);
//        tree.insert(tree.getRoot(),20,90,25,92,7);
//        tree.insert(tree.getRoot(),90,10,95,12,8);
//        tree.insert(tree.getRoot(),99,99,100,100,9);
//        tree.insert(tree.getRoot(),99,99,100,100,10);
//        tree.insert(tree.getRoot(),25,25,26,26,6);

//        tree.changeMaxHeight(10);

//        ArrayList<QuadTreeNodeKeys<Integer>> al = new ArrayList<QuadTreeNodeKeys<Integer>>();
//        al  = tree.find(tree.getRoot(),0,0,100,100);
//
//        tree.optimizeTree();
//
//        System.out.println(":)");
//        tree.insert(20,70,25,80,1);
//        tree.insert(10,10,15,15,2);
//        tree.insert(30,10,30,15,3);
////        tree.insert(30,30,40,40,4);
//        tree.insert(50,50,60,60,4);
//

//
//        Structures.QuadTreeNodeKeys<Integer> dataFound = null;
//        dataFound = tree.delete(20,70,25,80,0);
//        dataFound = tree.delete(10,10,15,15,1);
//        dataFound = tree.delete(50,50,60,60,3);
//        dataFound = tree.delete(10,10,15,15,1);

//        tree.insert(0,0,2,2,1);
//        tree.insert(1,1,2,2,2);

//        tree.insert(0,0,1,1,1);
//        tree.insert(0,0,1,1,2);




    }


//    public static void TestQuadTree(int p_number_operations, int p_percent_insert, int p_percent_delete, double p_minX, double p_minY, double p_maxX, double p_maxY, int p_maxLevel) {
//        int random_numb_op = 0;
////        int random_numb_data = 0;
//        int max_data = 0;
//        int quadrant = 0;
//        double minXElement;
//        double minYElement;
//        double maxXElement;
//        double maxYElement;
//        double halfX = (p_maxX - p_minX) / 2;
//        double halfY = (p_maxY - p_minY) / 2;
//
//        int n_insert = 0;
//        int n_delete = 0;
//
//        QuadTree<Integer> tree = new QuadTree<Integer>(p_minX, p_minY, p_maxX, p_maxY, p_maxLevel);
//
//        QuadTreeNodeKeys<Integer> keys = null;
//
//        ArrayList<Integer> added_numbers = new ArrayList<Integer>();
//        ArrayList<QuadTreeNodeKeys<Integer>> added_keys = new ArrayList<QuadTreeNodeKeys<Integer>>();
//        ArrayList<Integer> tree_numbers = new ArrayList<Integer>();
//        LinkedList<QuadTreeNodeKeys<Integer>> tree_keys = new LinkedList<QuadTreeNodeKeys<Integer>>();
//        ArrayList<Integer> wrong_numbers = new ArrayList<Integer>();
//
//        Random random = new Random();
//        int seed = random.nextInt(100000);
//        System.out.println(16404);
//        random.setSeed(16404);
//
//        for (int i = 0; i < p_number_operations; i++) {
//            random_numb_op = random.nextInt(100);
//
//            if (random_numb_op <= p_percent_insert) {
//                if ( n_insert < (p_number_operations * p_percent_insert / 100)) {
//                    quadrant = random.nextInt(4);
//
//                    if (quadrant == 0) {
//                        minXElement = random.nextDouble(p_minX,p_minX + halfX);
//                        minYElement = random.nextDouble(p_minY + halfY,p_maxY);
//                        maxXElement = random.nextDouble(minXElement, p_maxX);
//                        maxYElement = random.nextDouble(minYElement, p_maxY);
//                    } else if (quadrant == 1) {
//                        minXElement = random.nextDouble(p_minX + halfX,p_maxX);
//                        minYElement = random.nextDouble(p_minY + halfY,p_maxY);
//                        maxXElement = random.nextDouble(minXElement, p_maxX);
//                        maxYElement = random.nextDouble(minYElement, p_maxY);
//                    } else if (quadrant == 2) {
//                        minXElement = random.nextDouble(p_minX + halfX,p_maxX);
//                        minYElement = random.nextDouble(p_minY,p_minY + halfY);
//                        maxXElement = random.nextDouble(minXElement, p_maxX);
//                        maxYElement = random.nextDouble(minYElement, p_maxY);
//                    } else {
//                        minXElement = random.nextDouble(p_minX,p_minX + halfX);
//                        minYElement = random.nextDouble(p_minY,p_minY + halfY);
//                        maxXElement = random.nextDouble(minXElement, p_minX + halfX);
//                        maxYElement = random.nextDouble(minYElement, p_minY + halfY);
//                    }
//
//                    keys = tree.insert(tree.getRoot(), minXElement, minYElement, maxXElement, maxYElement, max_data);
//                    if (keys != null ) {
//                        added_numbers.add(keys.getData());
//                        added_keys.add(keys);
//                        max_data++;
//                        n_insert++;
//                    } else {
//                        i--;
//                    }
//                } else {
//                    i--;
//                }
//            }
//            else {
//                if (n_delete < (p_number_operations * p_percent_insert / 100)) {
//                    if (added_numbers.size() < 1) {
//                        i--;
//                    } else {
//                        int index = random.nextInt(added_keys.size());
//
//                        keys = added_keys.get(index);
//                        if (tree.delete(keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(), keys.getID()) != null){
//                            n_delete++;
//                            added_numbers.remove(index);
//                            added_keys.remove(index);
//                        } else {
//                            i--;
//                        }
//                    }
//                } else {
//                    i--;
//                }
//            }
//
//        }
//
//        tree_keys = tree.find(tree.getRoot(),p_minX, p_minY, p_maxX, p_maxY);
//
//        for (QuadTreeNodeKeys<Integer> key : tree_keys) {
//            tree_numbers.add(key.getData());
//        }
//
//        Collections.sort(added_numbers);
//        Collections.sort(tree_numbers);
//
//        System.out.println("");
//        System.out.println("Results:");
//        System.out.println("Number of executed operations:");
//        System.out.println("Insert: " + n_insert);
//        System.out.println("Delete: " + n_delete);
//        System.out.println("Number of data in Quad Tree: " + tree_numbers.size());
//        System.out.println("Number of expected data in Quad Tree: " + added_numbers.size());
//
//        for (int itemExpected : added_numbers ) {
//            if (!tree_numbers.contains(itemExpected)) {
//                wrong_numbers.add(itemExpected);
//            }
//        }
//    }

}