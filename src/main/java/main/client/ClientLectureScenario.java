package main.client;

import java.io.File;
import main.lectureEcritureFichier.LectureFichierTexte;
import main.modele.Scenario;

public class ClientLectureScenario {
    public static void main(String[] args) {
        Scenario scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        System.out.println(scenario);
    }
}
