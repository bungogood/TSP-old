package com.bungogood.tsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    public Vertex[] vertices;
    public String filename;

    public Graph(String filename) {
        this.filename = filename;
        try {
            vertices = readCSV(filename);
            CalculateDist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Vertex[] readCSV(String filename) throws IOException {
        List<Vertex> vertices = new ArrayList<>();
        double lat, lng;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                lat = Double.parseDouble(values[1]);
                lng = Double.parseDouble(values[2]);
                vertices.add(new Vertex(values[0], values[3], lat, lng));
            }
        }
        return vertices.toArray(new Vertex[0]);
    }

    void CalculateDist() {
        int dist;
        for (int i=0; i < vertices.length; i++) {
            for (int j=i; j < vertices.length; j++) {
                dist = dist(vertices[i], vertices[j]);
                vertices[i].setDist(vertices[j], dist);
                vertices[j].setDist(vertices[i], dist);
            }
        }
    }

    int dist(Vertex a, Vertex b) {
        int Radius = 6371; //km
        double dLat = Math.toRadians(a.lat-b.lat);
        double dLon = Math.toRadians(a.lng-b.lng);
        double working;
        working = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(b.lat)) * Math.cos(Math.toRadians(a.lat)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        working = Radius * 2 * Math.atan2(Math.sqrt(working), Math.sqrt(1-working));
        return (int) working;
    }
}
