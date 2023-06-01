package main.modele;

import java.util.ArrayList;

public class Scenario {

    ArrayList<Quete> staticProvQuetes;

    public Scenario() {
        staticProvQuetes = new ArrayList<>();
    }

    /**
     * Ajoute une quete au scenario
     * @param quete la quete a ajouter au scenario
     */
    public void ajout(Quete quete) {
        staticProvQuetes.add(quete);
    }

    public String toString() {
        return staticProvQuetes.size() + " " + staticProvQuetes.toString();}

    public ArrayList<Quete> getStaticProvQuetes() {
        return staticProvQuetes;
    }
}
