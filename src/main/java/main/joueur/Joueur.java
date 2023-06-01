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

    /**
     * Constructeur de la classe Joueur
     */
    public Joueur() {
        experience = 0;
        nombrePas = 0;
        temps = 0;
        position = new int[2];
    }

    /**
     * Constructeur de la classe Joueur avec parametre
     * @param parScenario Scenario, le scenario du jeu
     */
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

    /**
     * Methode qui renoir l'experience du joueur
     * @return experience int, l'experience du joueur
     */
    public static int getExperience() {
        return experience;
    }

    /**
     * Methode qui definit le nombre de pas du joueur
     * @return nombrePas int, le nombre de pas du joueur
     */
    public static int getNombrePas() {
        return nombrePas;
    }

    /**
     * Methode qui renvoie le temps de jeu du joueur
     * @return temps int, le temps du joueur
     */
    public static int getTemps() {
        return temps;
    }

    /**
     * Methode qui definit la position du joueur
     * @param parPos int[2], la position du joueur
     */
    public void setPosition(int[] parPos){
        position = parPos;
    }

    /**
     * Methode qui renvoie la position du joueur
     * @return position int[2], la position du joueur
     */
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

    /**
     * Methode qui renvoie la liste des quetes realisées
     * @return quetesRealisees ArrayList<Quete>, la liste des quetes realisées
     */
    public ArrayList<Quete> getQuetesRealisees() {
        return quetesRealisees;
    }

    /**
     * Methode qui renvoie la liste des quetes realisables
     */
    public void getRealisables() {
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

    /**
     * Methode qui permet de savoir si une quete est realisable
     * @param parQuete Quete, la quete à realiser
     * @return boolean, true si la quete est realisable, false sinon
     */
    public boolean estRealisable(Quete parQuete) {;
        return quetesRealisees.contains(parQuete);
    }

    /**
     * Methode qui permet de savoir si la quete finale est realisable
     * @return boolean, true si la quete finale est realisable, false sinon
     */
    public boolean QueteFinaleRealisable() {
        ArrayList<Quete> listeQuete = getQuetesRealisees();
        for (Quete quete : listeQuete) {
            if (quete.getNumero() == 0 && experience >= quete.getExperience()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode qui permet de recuperer la quete finale
     * @param scenario Scenario, le scenario du jeu
     * @return Quete, la quete finale
     */
    public Quete getQueteFinale(Scenario scenario) {
        ArrayList<Quete> listeQuete = scenario.getStaticProvQuetes();
        for (Quete quete : listeQuete) {
            if (quete.getNumero() == 0) {
                return quete;
            }
        }
        return null;
    }

    /**
     * Methode qui permet de recuperer la quete la plus proche
     * @param parListe ArrayList<Quete>, la liste des quetes
     * @return Quete, la quete la plus proche
     */
    public Quete quetePlusProche(ArrayList<Quete> parListe) {
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
