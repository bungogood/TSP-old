package com.bungogood.tsp;

public class Main {
    public static void main(String[] args) {
        Graph states = new Graph("res/East.txt");
        //23769: CO,KS,NE,SD,ND,MN,IA,OH,PA,NY,CT,RI,MA,NH,ME,VT,MI,WI,IL,IN,SC,GA,FL,PR,NC,VA,DC,MD,DE,NJ,WV,KY,TN,MO,AR,AL,MS,LA,OK,TX,NM,AZ,UT,NV,CA,OR,WA,ID,MT,WY

        Order base = new Order(states.vertices);
        base.show();

        Order kruskal = Kruskal.solve(states);
        kruskal.show();

        // Order brute = BruteForce.solve(states);
        // brute.show();

        // Annealing ann = new Annealing(states);
        // ann.best.show();
    }
}
