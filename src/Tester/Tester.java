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

    public void testTree(int p_number_operations, int p_percent_insert, int p_percent_delete,int p_percent_change_height,
                         double p_minX, double p_minY, double p_maxX, double p_maxY, int p_maxLevel,int p_maxLevel_test, int p_seed,
                         QuadTree<Integer> p_tree) {
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

        QuadTree<Integer> tree;
        if (p_tree == null) {
            tree = new QuadTree<Integer>(p_minX, p_minY, p_maxX, p_maxY, p_maxLevel);
        } else {
            tree = p_tree;
        }

        QuadTreeNodeKeys<Integer> keys = null;

        ArrayList<Integer> added_numbers = new ArrayList<Integer>();
        ArrayList<QuadTreeNodeKeys<Integer>> added_keys = new ArrayList<QuadTreeNodeKeys<Integer>>();
        ArrayList<Integer> tree_numbers = new ArrayList<Integer>();
        LinkedList<QuadTreeNodeKeys<Integer>> tree_keys = new LinkedList<QuadTreeNodeKeys<Integer>>();
        ArrayList<Integer> wrong_numbers = new ArrayList<Integer>();

        Random random = new Random();
        int seed;
        if (p_seed == -1) {
            seed = random.nextInt(100000);
        } else {
            seed = p_seed;
        }

        System.out.println(seed);
        random.setSeed(seed);

        for (int i = 0; i < p_number_operations; i++) {
            random_numb_op = random.nextInt(100);

            double percentage = (i * 100.0) / p_number_operations;

            // Check if the progress is at 10%, 20%, etc.
            if (percentage % 10 == 0) {
                System.out.println("Progress: " + (int) percentage + "%");
            }

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

                    keys = tree.insert(minXElement, minYElement, maxXElement, maxYElement, max_data);
                    if (keys != null ) {
                        added_numbers.add(keys.getData());
                        added_keys.add(keys);
                        max_data++;
                        n_insert++;

                        tree_keys = tree.find(p_minX, p_minY, p_maxX, p_maxY);
                        if (tree_keys != null && tree_keys.size() != added_numbers.size()){
                            System.out.println();
                        }

                    } else {
                        i--;
                    }
                } else {
                    i--;
                }
            }
            else if (random_numb_op >= p_percent_insert && random_numb_op < p_percent_insert + p_percent_delete ) {
                if (n_delete < (p_number_operations * p_percent_delete / 100)) {
                    if (added_numbers.size() < 1) {
                        i--;
                    } else {
                        int index = random.nextInt(added_keys.size());

                        keys = added_keys.get(index);

                        tree_keys = tree.find(p_minX, p_minY, p_maxX, p_maxY);
                        if (tree_keys != null && tree_keys.size() != added_numbers.size()){
                            System.out.println();
                        }

                        if (tree.delete(keys.getMinXElement(), keys.getMinYElement(), keys.getMaxXElement(), keys.getMaxYElement(), keys.getID(), keys.getData()) != null){
                            n_delete++;
                            added_numbers.remove(index);
                            added_keys.remove(index);

                            tree_keys = tree.find(p_minX, p_minY, p_maxX, p_maxY);
                            if (tree_keys != null && tree_keys.size() != added_numbers.size()){
                                System.out.println();
                            }

                        } else {
                            i--;
                        }
                    }
                } else {
                    i--;
                }
            } else {
                if (n_change < (p_number_operations * p_percent_change_height  / 100)) {
                    random_max_height = random.nextInt(1,p_maxLevel_test+1);
                    tree.changeMaxHeight(random_max_height);
                    n_change++;

                    tree_keys = tree.find(p_minX, p_minY, p_maxX, p_maxY);
                    if (tree_keys != null && tree_keys.size() != added_numbers.size()){
                        System.out.println();
                    }

                } else {
                    i--;
                }
            }


            tree_keys = tree.find(p_minX, p_minY, p_maxX, p_maxY);
            if (tree_keys != null && tree_keys.size() != added_numbers.size()){
                System.out.println();
            }
        }

        tree_keys = tree.find(p_minX, p_minY, p_maxX, p_maxY);

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

}
