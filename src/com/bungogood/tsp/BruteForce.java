package com.bungogood.tsp;
import java.util.*;

public class BruteForce {

    Graph graph;
    Order best;

    private BruteForce (Graph graph) {
        this.graph = graph;
        best = new Order(graph.vertices);
        perm(best, best.length-1);
    }

    public static Order solve (Graph graph) {
        BruteForce brute = new BruteForce(graph);
        return brute.best;
    }

    private void perm(Order order, int depth) {
        if (depth == 1) {
            if (order.fitness <= best.fitness) {
                best = order.copy();
            }
        } else {
            depth--;
            perm(order, depth);
            if (depth % 2 == 0) {
                for (int i=0; i < depth; i++) {
                    order.swap(0, depth);
                    perm(order, depth);
                }
            } else {
                for (int i=0; i < depth; i++) {
                    order.swap(i, depth);
                    perm(order, depth);
                }
            }
        }
    }
}
