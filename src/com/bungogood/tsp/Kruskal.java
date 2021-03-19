package com.bungogood.tsp;
import java.util.*;

public class Kruskal {

    private class Edge implements Comparable<Edge> {
        Vertex u, v;
        int dist;

        public Edge(Vertex u, Vertex v) {
            this.u = u;
            this.v = v;
            this.dist = u.getDist(v);
        }

        public int compareTo(Edge edge) {
            return this.dist - edge.dist;
        }
    }

    public static Order solve(Graph graph) {
        Kruskal kruskal = new Kruskal(graph);
        kruskal.MST();
        return kruskal.buildPath();
    }

    private Graph graph;
    private Hashtable<Vertex, Set<Vertex>> children;
    private Hashtable<Vertex, Vertex> parents;

    private Kruskal (Graph graph) {
        this.graph = graph;
    }

    private Queue<Edge> createEdges() {
        Queue<Edge> edges = new PriorityQueue<Edge>();
        Vertex a, b;
        for (int i=0; i < graph.vertices.length; i++) {
            a = graph.vertices[i];
            for (int j=i+1; j < graph.vertices.length; j++) {
                b = graph.vertices[j];
                edges.add(new Edge(a, b));
            }
        }
        return edges;
    }

    /*
    algorithm Kruskal(G) is
    F:= ∅
    for each v ∈ G.V do
        MAKE-SET(v)
    for each (u, v) in G.E ordered by weight(u, v), increasing do
        if FIND-SET(u) ≠ FIND-SET(v) then
            F:= F ∪ {(u, v)}
            UNION(FIND-SET(u), FIND-SET(v))
    return F
     */

    public void MST () {
        children = new Hashtable<Vertex, Set<Vertex>>();
        parents = new Hashtable<Vertex, Vertex>();

        Queue<Edge> edges = createEdges();
        Edge edge;

        for (Vertex vertex: graph.vertices) {
            children.put(vertex, new HashSet<Vertex>());
            parents.put(vertex, vertex);
        }

        while (!edges.isEmpty()) {
            edge = edges.poll();
            if (root(edge.u) != root(edge.v)) {
                backtrack(edge.v);
                reverse(edge.v, edge.u);
            }
        }
    }

    private Vertex root(Vertex vertex) {
        Vertex parent = parents.get(vertex);
        while (parent != vertex) {
            vertex = parent;
            parent = parents.get(vertex);
        }
        return parent;
    }

    private void backtrack(Vertex vertex) {
        Vertex parent = parents.get(vertex);

        if (vertex == parent) {
            return;
        }

        Vertex tmp = parents.get(parent);
        while (tmp != parent) {
            reverse(parent, vertex);
            vertex = parent;
            parent = tmp;
            tmp = parents.get(parent);
        }
        reverse(parent, vertex);
    }

    private void reverse(Vertex parent, Vertex child) {
        children.get(parent).remove(child);
        children.get(child).add(parent);
        parents.replace(parent, child);
    }

    private Order buildPath() {
        Vertex root = root(graph.vertices[0]);
        List<Vertex> path = new ArrayList<Vertex>();
        path.add(root);
        step(path, root);
        return new Order(path.toArray(new Vertex[0]));
    }

    private void step(List<Vertex> path, Vertex prev) {
        for (Vertex vertex: children.get(prev)) {
            path.add(vertex);
            step(path, vertex);
        }
    }
}
