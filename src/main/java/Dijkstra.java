// Original source: https://www.baeldung.com/java-dijkstra
package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Dijkstra {

    public static class Graph {
        Set<Node> nodes = new HashSet<>();

        public void addNode(Node node) {
            nodes.add(node);
        }
    }

    public static class Node {
        String name;
        Map<Node, Integer> adjacentNodes; // Used to associated neighbors AND edge weight
        Integer distance;
        List<Node> shortestPath;

        public Node(String name) {
            this.name = name;
            this.adjacentNodes = new HashMap<>();
            this.distance = Integer.MAX_VALUE;
            this.shortestPath = new ArrayList<>();
        }

        public void addAdjacentNode(Node node, int distance) {
            adjacentNodes.put(node, distance);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void calculateShortestPathFromSource(Graph graph, Node source) {
        source.distance = 0;

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Integer> adjacentNodePair : currentNode.adjacentNodes.entrySet()) {
                Node adjacentNode = adjacentNodePair.getKey();
                Integer edgeWeight = adjacentNodePair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    updateAdjacentNodeIfPathIsShorter(currentNode, adjacentNode, edgeWeight);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> nodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : nodes) {
            int nodeDistance = node.distance;
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void updateAdjacentNodeIfPathIsShorter(Node sourceNode, Node destinationNode, Integer edgeWeight) {
        Integer sourceDistance = sourceNode.distance;
        if (sourceDistance + edgeWeight < destinationNode.distance) {
            destinationNode.distance = sourceDistance + edgeWeight;
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.shortestPath);
            shortestPath.add(sourceNode);
            destinationNode.shortestPath = shortestPath;
        }
    }

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addAdjacentNode(nodeB, 10);
        nodeA.addAdjacentNode(nodeC, 15);

        nodeB.addAdjacentNode(nodeD, 12);
        nodeB.addAdjacentNode(nodeF, 15);

        nodeC.addAdjacentNode(nodeE, 10);

        nodeD.addAdjacentNode(nodeE, 2);
        nodeD.addAdjacentNode(nodeF, 1);

        nodeF.addAdjacentNode(nodeB, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        calculateShortestPathFromSource(graph, nodeA);
        for (Node node : graph.nodes) {
            System.out.println("NodeA to " + node.name + " has shortestDistance " + node.distance + " with path: " + Arrays.toString(node.shortestPath.toArray()));
        }
    }
}
