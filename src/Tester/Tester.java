package Tester;

import Structures.QuadTree;
import Structures.QuadTreeNodeKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Tester {

    public Tester() {
    }

    public void testTree(int p_number_operations, int p_percent_insert, int p_percent_delete,int p_percent_change_height, double p_minX, double p_minY, double p_maxX, double p_maxY, int p_maxLevel,int p_maxLevel_test) {
        int random_numb_op = 0;
        int random_max_height = 0;
//        int random_numb_data = 0;
        int max_data = 0;
        int quadrant = 0;
        double minXElement;
        double minYElement;
        double maxXElement;
        double maxYElement;
        double halfX = (p_maxX - p_minX) / 2;
        double halfY = (p_maxY - p_minY) / 2;

        int n_insert = 0;
        int n_delete = 0;
        int n_change = 0;

        QuadTree<Integer> tree = new QuadTree<Integer>(p_minX, p_minY, p_maxX, p_maxY, p_maxLevel);

        QuadTreeNodeKeys<Integer> keys = null;

        ArrayList<Integer> added_numbers = new ArrayList<Integer>();
        ArrayList<QuadTreeNodeKeys<Integer>> added_keys = new ArrayList<QuadTreeNodeKeys<Integer>>();
        ArrayList<Integer> tree_numbers = new ArrayList<Integer>();
        LinkedList<QuadTreeNodeKeys<Integer>> tree_keys = new LinkedList<QuadTreeNodeKeys<Integer>>();
        ArrayList<Integer> wrong_numbers = new ArrayList<Integer>();

        Random random = new Random();
        random.setSeed(91331);
        int seed = random.nextInt(100000);
        System.out.println(seed);

        for (int i = 0; i < p_number_operations; i++) {
            random_numb_op = random.nextInt(100);

            if (random_numb_op < p_percent_insert) {
                if ( n_insert < (p_number_operations * p_percent_insert / 100)) {
                    quadrant = random.nextInt(4);

                    if (quadrant == 0) {
                        minXElement = random.nextDouble(p_minX,p_minX + halfX);
                        minYElement = random.nextDouble(p_minY + halfY,p_maxY);
                        maxXElement = random.nextDouble(minXElement, p_maxX);
                        maxYElement = random.nextDouble(minYElement, p_maxY);
                    } else if (quadrant == 1) {
                        minXElement = random.nextDouble(p_minX + halfX,p_maxX);
                        minYElement = random.nextDouble(p_minY + halfY,p_maxY);
                        maxXElement = random.nextDouble(minXElement, p_maxX);
                        maxYElement = random.nextDouble(minYElement, p_maxY);
                    } else if (quadrant == 2) {
                        minXElement = random.nextDouble(p_minX + halfX,p_maxX);
                        minYElement = random.nextDouble(p_minY,p_minY + halfY);
                        maxXElement = random.nextDouble(minXElement, p_maxX);
                        maxYElement = random.nextDouble(minYElement, p_maxY);
                    } else {
                        minXElement = random.nextDouble(p_minX,p_minX + halfX);
                        minYElement = random.nextDouble(p_minY,p_minY + halfY);
                        maxXElement = random.nextDouble(minXElement, p_minX + halfX);
                        maxYElement = random.nextDouble(minYElement, p_minY + halfY);
                    }

                    keys = tree.insert(tree.getRoot(), minXElement, minYElement, maxXElement, maxYElement, max_data);
                    if (keys != null ) {
                        added_numbers.add(keys.getData());
                        added_keys.add(keys);
                        max_data++;
                        n_insert++;
                    } else {
                        i--;
                    }
                } else {
                    i--;
                }
            }
            else if (random_numb_op >= p_percent_insert && random_numb_op < p_percent_insert + p_percent_delete ) {
                if (n_delete < (p_number_operations * p_percent_insert / 100)) {
                    if (added_numbers.size() < 1) {
                        i--;
                    } else {
                        int index = random.nextInt(added_keys.size());

                        keys = added_keys.get(index);
                        if (tree.delete(keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(), keys.getID()) != null){
                            n_delete++;
                            added_numbers.remove(index);
                            added_keys.remove(index);
                        } else {
                            i--;
                        }
                    }
                } else {
                    i--;
                }
            } else {
                random_max_height = random.nextInt(1,p_maxLevel_test+1);


                if (random_max_height == 40) {
                    System.out.println(random_max_height);
                }
                tree.changeMaxHeight(random_max_height);
            }

        }

        tree_keys = tree.find(tree.getRoot(),p_minX, p_minY, p_maxX, p_maxY);

        for (QuadTreeNodeKeys<Integer> key : tree_keys) {
            tree_numbers.add(key.getData());
        }

        Collections.sort(added_numbers);
        Collections.sort(tree_numbers);

        for (int itemExpected : added_numbers ) {
            if (!tree_numbers.contains(itemExpected)) {
                wrong_numbers.add(itemExpected);
            }
        }

        System.out.println("");
        System.out.println("Results:");
        System.out.println("Number of executed operations:");
        System.out.println("Insert: " + n_insert);
        System.out.println("Delete: " + n_delete);
        System.out.println("Number of data in Quad Tree: " + tree_numbers.size());
        System.out.println("Number of expected data in Quad Tree: " + added_numbers.size());
        System.out.println("Number of wrong numbers: " + wrong_numbers.size());
    }

    public void testChangeMaxHeight(int p_number_operations, int p_number_data, double p_minX, double p_minY, double p_maxX, double p_maxY, int p_maxLevel, int p_maxLevel_test) {
        int random_max_height = 0;
        int max_data = 0;
        int quadrant = 0;
        double minXElement;
        double minYElement;
        double maxXElement;
        double maxYElement;
        double halfX = (p_maxX - p_minX) / 2;
        double halfY = (p_maxY - p_minY) / 2;

        QuadTree<Integer> tree = new QuadTree<Integer>(p_minX, p_minY, p_maxX, p_maxY, p_maxLevel);

        QuadTreeNodeKeys<Integer> keys = null;

        ArrayList<Integer> added_numbers = new ArrayList<Integer>();
        ArrayList<QuadTreeNodeKeys<Integer>> added_keys = new ArrayList<QuadTreeNodeKeys<Integer>>();
        ArrayList<Integer> tree_numbers = new ArrayList<Integer>();
        LinkedList<QuadTreeNodeKeys<Integer>> tree_keys = new LinkedList<QuadTreeNodeKeys<Integer>>();
        ArrayList<Integer> wrong_numbers = new ArrayList<Integer>();

        Random random = new Random();
        random.setSeed(97732);
        int seed = random.nextInt(100000);

        for (int i = 0; i < p_number_data; i++) {
            quadrant = random.nextInt(4);

            if (quadrant == 0) {
                minXElement = random.nextDouble(p_minX,p_minX + halfX);
                minYElement = random.nextDouble(p_minY + halfY,p_maxY);
                maxXElement = random.nextDouble(minXElement, p_maxX);
                maxYElement = random.nextDouble(minYElement, p_maxY);
            } else if (quadrant == 1) {
                minXElement = random.nextDouble(p_minX + halfX,p_maxX);
                minYElement = random.nextDouble(p_minY + halfY,p_maxY);
                maxXElement = random.nextDouble(minXElement, p_maxX);
                maxYElement = random.nextDouble(minYElement, p_maxY);
            } else if (quadrant == 2) {
                minXElement = random.nextDouble(p_minX + halfX,p_maxX);
                minYElement = random.nextDouble(p_minY,p_minY + halfY);
                maxXElement = random.nextDouble(minXElement, p_maxX);
                maxYElement = random.nextDouble(minYElement, p_maxY);
            } else {
                minXElement = random.nextDouble(p_minX,p_minX + halfX);
                minYElement = random.nextDouble(p_minY,p_minY + halfY);
                maxXElement = random.nextDouble(minXElement, p_minX + halfX);
                maxYElement = random.nextDouble(minYElement, p_minY + halfY);
            }

            keys = tree.insert(tree.getRoot(), minXElement, minYElement, maxXElement, maxYElement, max_data);
            if (keys != null ) {
                added_numbers.add(keys.getData());
                added_keys.add(keys);
                max_data++;
            } else {
                i--;
            }
        }

        for (int i = 0; i < p_number_operations; i++) {
            random_max_height = random.nextInt(1,p_maxLevel_test+1);

            tree.changeMaxHeight(random_max_height);
        }

        tree_keys = tree.find(tree.getRoot(),p_minX, p_minY, p_maxX, p_maxY);

        for (QuadTreeNodeKeys<Integer> key : tree_keys) {
            tree_numbers.add(key.getData());
        }

        Collections.sort(added_numbers);
        Collections.sort(tree_numbers);

        for (int itemExpected : added_numbers ) {
            if (!tree_numbers.contains(itemExpected)) {
                wrong_numbers.add(itemExpected);
            }
        }

        System.out.println("");
        System.out.println("Results:");
        System.out.println("Number of executed operations:");
        System.out.println("Change height: " + p_number_operations);
        System.out.println("Number of data in Quad Tree: " + tree_numbers.size());
        System.out.println("Number of expected data in Quad Tree: " + added_numbers.size());
        System.out.println("Number of wrong numbers: " + wrong_numbers.size());
    }
}
