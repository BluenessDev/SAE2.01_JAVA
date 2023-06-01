package main.joueur;

import main.modele.Quete;
import main.modele.Scenario;

import java.util.ArrayList;

public class Joueur {

    private static int experience;
    private static int nombrePas;
    private static int temps;
    private int[] position;
    private static Scenario scenario;
    private ArrayList<Quete> quetesRealisees = new ArrayList<>();
    private ArrayList<Integer> dejaTrouves = new ArrayList<>();

    public Joueur() {
        experience = 0;
        nombrePas = 0;
        temps = 0;
        position = new int[2];
    }

    public Joueur(Scenario parScenario) {
        experience = 0;
        nombrePas = 0;
        temps = 0;
        position = new int[2];
    }


    /**
     * Methode qui definit l'experience du joueur
     *@param parExperience int, l'experience du joueur
     */
    public static void setExperience(int parExperience) {
        Joueur.experience = parExperience;
    }

    public static int getExperience() {
        return experience;
    }

    public static int getNombrePas() {
        return nombrePas;
    }

    public static int getTemps() {
        return temps;
    }

    public void setPosition(int[] parPos){
        position = parPos;
    }

    public int [] getPosition() {
        return position;
    }

    /**
     * Compare la position du joueur avec une position donnée
     * @param parPos int[2], la position du joueur
     * @return distance int, la distance entre les deux positions
     */
    public int compareDistance(int[] parPos){
            int Dx = this.position[0] - parPos[0];
            int Dy = this.position[1] - parPos[1];
            int distance = Math.abs(Dx) + Math.abs(Dy);
            return distance;
    }

    /**
     * Methode qui permet de realiser une quete
     * @param parQuete Quete, la quete à realiser
     */
    public void realiserQuete(Quete parQuete) {
        dejaTrouves.add(parQuete.getNumero());
        experience += parQuete.getExperience();
        temps += parQuete.getDuree() + compareDistance(parQuete.getPosition());
        quetesRealisees.add(parQuete);
        nombrePas += compareDistance(parQuete.getPosition());
        System.out.println(nombrePas);
        position = parQuete.getPosition();
    }

    public ArrayList<Quete> getQuetesRealisees() {
        return quetesRealisees;
    }

    /**
     * Methode qui renvoie la liste des quetes realisables
     */
    public void Realisable() {
        quetesRealisees = new ArrayList<>();
        for (Quete quete : scenario.getStaticProvQuetes()) {

            if (!quetesRealisees.contains(quete)) {
                if (quete.aucunePrecond()) {
                    quetesRealisees.add(quete);
                } else {
                    boolean preconditions1 = false;
                    boolean preconditions2 = false;
                    if (quete.getPrecondition()[0] == quete.getPrecondition()[1]) {
                        preconditions1 = true;
                    }
                    if (quete.getPrecondition()[2] == quete.getPrecondition()[3]) {
                        preconditions2 = true;
                    }
                    if (dejaTrouves.contains(quete.getPrecondition()[0]) || dejaTrouves.contains(quete.getPrecondition()[1])) {
                        preconditions1 = true;
                    }
                    if (dejaTrouves.contains(quete.getPrecondition()[2]) || dejaTrouves.contains(quete.getPrecondition()[3])) {
                        preconditions2 = true;
                    }
                    if (preconditions1 && preconditions2) {
                        quetesRealisees.add(quete);
                    }
                }
            }
        }
    }

    public boolean estRealisable(Quete parQuete) {;
        return quetesRealisees.contains(parQuete);
    }

    public boolean QueteFinaleRealisable() {
        ArrayList<Quete> listeQuete = getQuetesRealisees();
        for (Quete quete : listeQuete) {
            if (quete.getNumero() == 0 && experience >= quete.getExperience()) {
                return true;
            }
        }
        return false;
    }

    public Quete getQueteFinale(Scenario scenario) {
        ArrayList<Quete> listeQuete = scenario.getStaticProvQuetes();
        for (Quete quete : listeQuete) {
            if (quete.getNumero() == 0) {
                return quete;
            }
        }
        return null;
    }

    public Quete queteProche(ArrayList<Quete> parListe) {
        int distance = 100000; // valeur arbitraire
        Quete quetesPlusProches = null;
        for (Quete quete : parListe) {
            if (compareDistance(quete.getPosition()) < distance) {
                distance = compareDistance(quete.getPosition());
                quetesPlusProches = quete;
            }
        }
        System.out.println("Plus proche " + quetesPlusProches.toString());
        return quetesPlusProches;
    }


}
