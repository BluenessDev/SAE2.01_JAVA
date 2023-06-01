package main.client;

import java.io.File;
import java.util.ArrayList;

import main.joueur.Joueur;
import main.lectureEcritureFichier.LectureFichierTexte;
import main.modele.Niveau1;
import main.modele.Quete;
import main.modele.Scenario;

public class ClientLectureScenario {
    public static void main(String[] args) {
        Scenario scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_10.txt"));
        Niveau1 parcours = new Niveau1(scenario);
        //System.out.println(parcours.cheminEfficace());

        Joueur joueur = new Joueur(scenario);
        /*
         * affichage propre
         */
        ArrayList<Quete> chemin = parcours.cheminExhaustif();
        joueur.setExperience(0);
        for (Quete quete : chemin) {
            System.out.print("numero: "+quete.getNumero() + " precondition: ");
            if (quete.getNumero() != 0) {
                joueur.addExperience(quete.getExperience());
            }

            int cpt = 0;
            for (int i : quete.getPrecondition()) {
                if (cpt == 1 ){
                    System.out.print(i+", ");
                }
                else {
                    System.out.print(i + " ");
                }
                cpt++;
            }
            System.out.print("distance: "+ joueur.compareDistance(quete.getPosition()) + " experience: "+ joueur.getExperience());
            System.out.println();
            joueur.setPosition(quete.getPosition());
        }
    }

}
