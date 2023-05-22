package main.lectureEcritureFichier;

import java.io.File;
import java.io.FileNotFoundException;
import main.modele.Quete;
import main.modele.Scenario;
import java.util.Scanner;

public class LectureFichierTexte {

    public static Scenario lecture(File fichier) {
        Scenario scenario = new Scenario();
        try {
            Scanner scanner = new Scanner(fichier);
            while (scanner.hasNext()) {
                scenario.ajout(new Quete(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return scenario;
    }
}
