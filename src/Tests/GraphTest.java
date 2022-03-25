import Graphs.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    Graph graph;


    @Before
    public void setup(){
        graph = new Graph();

        // 3 nodes to work with

        graph.addNode("One");
        graph.addNode("Two");
        graph.addNode("Three");
    }

    @Test
    public void addNode() {
        graph.addNode("Four");
        assertEquals(4, graph.nodes.size());
    }

    @Test
    public void addEdge() {
        graph.addEdge(0, 1);    // unweighted, bidirectional, 2 edges
        graph.addEdge(0, 2, 2); // weighted, bidirectional, 2 edges
        graph.addEdge(1, 2, false); // unweighted, one direction, 1 edge
        graph.addEdge(2, 1, 3, false); // weighted, one direction, 1 edge

        assertEquals(6, graph.getNumberOfEdges());          // check if number of edges in the graph is correct

        assertEquals(2, graph.nodes.get(0).edges.size());
        assertEquals(2, graph.nodes.get(1).edges.size());   // expecting two as first added edge was bidirectional
        assertEquals(2, graph.nodes.get(2).edges.size());   // expecting edges to 1 and 0 nodes, as second added edge was bidirectional

        assertSame(graph.nodes.get(0).edges.get(0).destination, graph.nodes.get(1));    // check if edge links to correct node
        assertSame(graph.nodes.get(2).edges.get(1).destination, graph.nodes.get(1));

        assertEquals(3, graph.nodes.get(2).edges.get(1).weight);                // check edge weights
        assertEquals(0, graph.nodes.get(1).edges.get(1).weight);                // unweighted edge weight should be 0

    }


    @Test
    public void removeEdge() {
        graph.addEdge(2,1); // adding edges in both directions
        graph.removeEdge(2, 1, true);
        assertEquals(0, graph.getNumberOfEdges()); // expected 0, as both directions are set to be removed

        graph.addEdge(0, 1);
        graph.removeEdge(0, 1);
        assertEquals(1, graph.getNumberOfEdges());  // expected 1 as added edge is bidirectional
    }
}