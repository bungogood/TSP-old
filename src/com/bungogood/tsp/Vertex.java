package com.bungogood.tsp;

import java.util.Hashtable;

public class Vertex {
    public Hashtable<Vertex, Integer> dist = new Hashtable<Vertex, Integer>();
    public String id, name;
    public Double lat, lng;

    public Vertex (String id, String name, Double lat, Double lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public void setDist(Vertex v, int d) {
        this.dist.put(v, d);
    }

    public int getDist(Vertex v) {
        return this.dist.get(v);
    }
}