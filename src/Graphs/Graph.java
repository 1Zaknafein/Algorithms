package Graphs;

import java.util.ArrayList;

public class Graph {

    public final ArrayList<Node> nodes;    // list nodes
    private int numberOfEdges;              // number of edges in the graph
    public Graph(){
        nodes = new ArrayList<>(); numberOfEdges=0;
    }

    public void addNode(String name){
        nodes.add(new Node(name));
    }

    // default, simple edge; no weights and bidirectional
    public void addEdge(int source, int destination){
        addEdge(source, destination, 0, true);
    }

    // bidirectional but weighted
    public void addEdge(int source, int destination, int weight){
        addEdge(source, destination, weight, true);
    }

    // unweighted but with set direction
    public void addEdge(int source, int destination, boolean bidirectional){
        addEdge(source, destination, 0, bidirectional);
    }

    // fully custom
    public void addEdge(int source, int destination, int weight, boolean bidirectional){
        numberOfEdges += 1;
        nodes.get(source).edges.add(new Edge(nodes.get(destination), weight));
        if (bidirectional) {
            nodes.get(destination).edges.add(new Edge(nodes.get(source), weight));
            numberOfEdges += 1;
        }
    }

    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    public void removeEdge(int source, int destination, boolean bothDirections){
        Node sNode = nodes.get(source);
        Node dNode = nodes.get(destination);

        for (Edge e : sNode.edges){ if (e.destination.equals(dNode)){ sNode.edges.remove(e); numberOfEdges -= 1; break;} }

        // need to delete both directions if requested
        if (bothDirections){
            for (Edge e : dNode.edges){ if (e.destination.equals(sNode)){ dNode.edges.remove(e); numberOfEdges -= 1; break;} }
        }
    }
    public void removeEdge(int source, int destination){
        removeEdge(source, destination, false);
    }


    // print out whole graph in console
    public void printGraph() {
        System.out.println("\nGraphs.Graph nodes:");
        for (Node x : nodes) {
            System.out.println("("+ nodes.indexOf(x)+ ") "+ x.name);
            for (Edge e : x.edges) {
                System.out.println("     ("+ nodes.indexOf(e.destination) +") " + e.destination.name + "  (weight=" + e.weight + ")");
            }
            System.out.println();
        }
    }

    public class Node{
        public final String name;
        public ArrayList<Edge> edges;      // every node will have a list of edges connected to it
        public Node(String name){
            this.name = name;
            edges = new ArrayList<>();
        }
    }

    public class Edge{
        public Node destination;
        public int weight;
        public Edge(Node destination, int weight){
            this.weight = weight;
            this.destination = destination;
        }

    }


    public static Graph getExampleWeightedGraph(){
        Graph g = new Graph();
        g.addNode("Market");
        g.addNode("School");
        g.addNode("Library");
        g.addNode("Car park");
        g.addNode("Airport");
        g.addNode("Centre");
        g.addNode("Police Station");
        g.addNode("Cinema");

        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(0, 3, 5);

        g.addEdge(1, 2, 1);
        g.addEdge(1, 5, 3);
        g.addEdge(1, 7, 2);

        g.addEdge(2, 3, 2);
        g.addEdge(2, 7, 4);
        g.addEdge(2, 4, 2);

        g.addEdge(3, 4, 1);

        g.addEdge(4, 6, 7);
        g.addEdge(4, 7, 3);

        g.addEdge(5, 7, 6);
        g.addEdge(5, 6, 5);

        g.addEdge(6, 7, 1);
        return g;
    }
}
