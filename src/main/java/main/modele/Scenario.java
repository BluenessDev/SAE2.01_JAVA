package main.modele;

import java.util.ArrayList;

public class Scenario {

    ArrayList<Quete> staticProvQuetes;

    public Scenario() {
        staticProvQuetes = new ArrayList<>();
    }

    public void ajout(Quete quete) {
        staticProvQuetes.add(quete);
    }

    public String toString() {
        return staticProvQuetes.size() + " " + staticProvQuetes.toString();
    }
}
