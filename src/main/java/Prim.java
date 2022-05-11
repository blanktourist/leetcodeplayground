package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Prim {

    public static class Edge {
        int weight;
        boolean isIncluded;

        public Edge(int weight) {
            this.weight = weight;
            this.isIncluded = false;
        }
    }

    public static class Vertex {
        String name;
        Map<Vertex, Edge> neighbors;
        boolean isVisited;

        public Vertex(String name) {
            this.name = name;
            this.neighbors = new HashMap<>();
            this.isVisited = false;
        }
    }

    // Input is a list of vertices
    // Output is a list of vertices
    public List<Vertex> findMinimumSpanningTree(List<Vertex> graph) {
        if (graph == null || graph.size() < 1) {
            return new ArrayList<>();
        }

        graph.get(0).isVisited = true;

        List<Vertex> output = new ArrayList<>();
        while (isDisconnected(graph)) {
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);   // Placeholder
            Vertex nextVertex = graph.get(0);           // Placeholder

            for (Vertex vertex : graph) {
                if (vertex.isVisited) {
                    Entry<Vertex, Edge> candidate = returnBestCandidateEdge(vertex);
                    if (candidate.getValue().weight < nextMinimum.weight) {
                        nextMinimum = candidate.getValue();
                        nextVertex = candidate.getKey();
                    }
                }
            }

            // TODO: build output list with cloned verticies (?)
            nextVertex.isVisited = true;
            output.add(nextVertex);
        }
        return output;
    }

    private boolean isDisconnected(List<Vertex> graph) {
        for (Vertex vertex : graph) {
            if (!vertex.isVisited) {
                return false;
            }
        }
        return true;
    }

    // Looks at the neighbors of vertex and returns the lowest weight edge to unvisited vertex
    private Entry<Vertex, Edge> returnBestCandidateEdge(Vertex vertex) {
        Entry<Vertex, Edge> next = null;
        int minEdgeWeight = Integer.MAX_VALUE;
        for (Entry<Vertex, Edge> pair : vertex.neighbors.entrySet()) {
            if (!pair.getKey().isVisited) {
                if (pair.getValue().weight < minEdgeWeight) {
                    next = pair;
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
        // TODO: make test graph and run the alg on it
    }
}
