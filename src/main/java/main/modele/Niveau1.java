package main.modele;

import main.joueur.Joueur;

import java.util.ArrayList;

public class Niveau1 {

    private static Joueur joueur;;
    private static Scenario scenario;

    /**
     * Constructeur de la classe Niveau1
     * @param parScenario le scenario du jeu
     */
    public Niveau1(Scenario parScenario) {
        joueur = new Joueur(parScenario);
        scenario = parScenario;
    }

    /**
     * Methode qui retourne le chemin le plus efficace pour finir le scenario
     * @return le chemin le plus efficace pour finir le jeu
     */
    public ArrayList<Quete> cheminEfficace(){
        ArrayList<Quete> chemin = new ArrayList<>();
        ArrayList<Quete> scenarioProvQuetes = scenario.getStaticProvQuetes();
        Quete quete0 = joueur.getQueteFinale(scenario);
        boolean queteRealisee = false;
        while (!joueur.QueteFinaleRealisable()) {
            joueur.getRealisables();
            System.out.println(joueur.getQuetesRealisees().size());
            queteRealisee = false; // Initialiser la variable de contrôle

            for (Quete quete : scenarioProvQuetes) {
                if (joueur.estRealisable(quete)) {
                    if (joueur.getQuetesRealisees().size() > 1) {
                        quete = joueur.quetePlusProche(joueur.getQuetesRealisees());
                    }
                    joueur.realiserQuete(quete);
                    chemin.add(quete);
                    scenarioProvQuetes.remove(quete);
                    queteRealisee = true; // Marquer qu'une quête a été réalisée
                    break; // Sortir de la boucle dès qu'une quête est réalisée
                }
            }

            if (!queteRealisee) {
                System.out.println("Aucune quête réalisable");
                break; // Sortir de la boucle si aucune quête n'a été réalisée
            }

        }
        joueur.realiserQuete(quete0);
        chemin.add(quete0);
        return chemin;
    }

    /**
     * Methode qui retourne le chemin pour finir le scenario
     * @return le chemin pour finir le jeu
     */
    public ArrayList<Quete> cheminExhaustif() {
        ArrayList<Quete> chemin = new ArrayList<>();
        ArrayList<Quete> scenarioProvQuetes = scenario.getStaticProvQuetes();
        Quete quete0 = joueur.getQueteFinale(scenario);
        boolean queteRealisee = false;
        while (!scenarioProvQuetes.isEmpty()) {
            joueur.getRealisables();
            System.out.println(joueur.getQuetesRealisees().size());
            queteRealisee = false; // Initialiser la variable de contrôle
            for (Quete quete : scenarioProvQuetes) {
                ArrayList<Quete> sans0 = joueur.getQuetesRealisees();
                if (sans0.contains(quete0)) {
                    sans0.remove(quete0);
                }
                else if (joueur.estRealisable(quete)) {
                    if (joueur.getQuetesRealisees().size() > 1) {
                        quete = joueur.quetePlusProche(sans0);
                    }
                    joueur.realiserQuete(quete);
                    chemin.add(quete);
                    scenarioProvQuetes.remove(quete);
                    queteRealisee = true; // Marquer qu'une quête a été réalisée
                    break; // Sortir de la boucle dès qu'une quête est réalisée
                }
            }
            if (!queteRealisee) {
                System.out.println("Aucune quête réalisable");
                break; // Sortir de la boucle si aucune quête n'a été réalisée
            }
        }
        joueur.realiserQuete(quete0);
        chemin.add(quete0);
        return chemin;

    }
}
