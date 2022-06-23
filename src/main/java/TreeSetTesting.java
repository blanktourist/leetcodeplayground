package main.java;

import java.util.TreeSet;

public class TreeSetTesting {
    
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // TreeSet can be used 
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(6);
        treeSet.add(5);

        System.out.println("TreeSet");
        System.out.println(treeSet);
        System.out.println();
        System.out.println(treeSet.descendingSet());
        System.out.println();
        System.out.println(treeSet.tailSet(3));
        System.out.println();
        System.out.println(treeSet.floor(1));
        System.out.println(treeSet.ceiling(1));
        System.out.println(treeSet.higher(1));
        System.out.println(treeSet.lower(1));
        System.out.println();
        System.out.println(treeSet.floor(3));
        System.out.println(treeSet.ceiling(3));
        System.out.println(treeSet.higher(3));
        System.out.println(treeSet.lower(3));
        System.out.println();
        System.out.println(treeSet.floor(5));
        System.out.println(treeSet.ceiling(5));
        System.out.println(treeSet.higher(5));
        System.out.println(treeSet.lower(5));
        System.out.println();
        System.out.println(treeSet.floor(7));
        System.out.println(treeSet.ceiling(7));
        System.out.println(treeSet.higher(7));
        System.out.println(treeSet.lower(7));

        TreeSet<Node> nodeTreeSet = new TreeSet<Node>((a,b) -> a.value - b.value);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        nodeTreeSet.add(node1);
        nodeTreeSet.add(node3);
        nodeTreeSet.add(node2);
        nodeTreeSet.add(node4);
        nodeTreeSet.add(node6);
        nodeTreeSet.add(node5);

        System.out.println("NodeTreeSet");
        System.out.println(nodeTreeSet);
        System.out.println();
        System.out.println(nodeTreeSet.descendingSet());
        System.out.println();
        System.out.println(nodeTreeSet.tailSet(new Node(3)));
        System.out.println();
        System.out.println(nodeTreeSet.floor(new Node(1)));
        System.out.println(nodeTreeSet.ceiling(new Node(1)));
        System.out.println(nodeTreeSet.higher(new Node(1)));
        System.out.println(nodeTreeSet.lower(new Node(1)));
        System.out.println();
        System.out.println(nodeTreeSet.floor(new Node(3)));
        System.out.println(nodeTreeSet.ceiling(new Node(3)));
        System.out.println(nodeTreeSet.higher(new Node(3)));
        System.out.println(nodeTreeSet.lower(new Node(3)));
        System.out.println();
        System.out.println(nodeTreeSet.floor(new Node(5)));
        System.out.println(nodeTreeSet.ceiling(new Node(5)));
        System.out.println(nodeTreeSet.higher(new Node(5)));
        System.out.println(nodeTreeSet.lower(new Node(5)));
        System.out.println();
        System.out.println(nodeTreeSet.floor(new Node(7)));
        System.out.println(nodeTreeSet.ceiling(new Node(7)));
        System.out.println(nodeTreeSet.higher(new Node(7)));
        System.out.println(nodeTreeSet.lower(new Node(7)));
    }
}
