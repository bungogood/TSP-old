package com.bungogood.tsp;

public class Order {
    public Vertex[] order;
    public int fitness;
    public int length;

    public Order(Vertex[] order) {
        this.order = order.clone();
        this.length = order.length;
        this.fitness = this.dist();
    }

    public Order(Vertex[] order, int fitness) {
        this.order = order.clone();
        this.length = order.length;
        this.fitness = fitness;
    }

    public Order copy() {
        return new Order(this.order, this.fitness);
    }

     public int dist() {
        int total = order[0].getDist(order[length-1]);
        for (int i = 0; i < length-1; i++) {
            total = order[i].getDist(order[i+1]);
        }
        return total;
    }

    public void show() {
        System.out.print(this.fitness);
        System.out.print(": ");
        for (Vertex v: this.order) {
            System.out.print(v.id);
            System.out.print(",");
        }
        System.out.println("\b");
    }

    public void swap(int a, int b) {
        Vertex tmp = order[a];
        replace(a, b);
        replace(b, a);
        order[a] = order[b];
        order[b] = tmp;
    }

    private void replace(int a, int b) {
        upper(a, b);
        lower(a, b);
    }

    private void upper(int prev, int next) {
        Vertex v = prev == length-1 ? order[0] : order[prev+1];
        if (order[next] != v && order[prev] != v) {
            fitness += order[next].getDist(v) - order[prev].getDist(v);
        }
    }

    private void lower(int prev, int next) {
        Vertex v = prev == 0 ? order[length-1] : order[prev-1];
        if (order[next] != v && order[prev] != v) {
            fitness += order[next].getDist(v) - order[prev].getDist(v);
        }
    }
}
