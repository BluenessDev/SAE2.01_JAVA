package main.joueur;

import main.modele.Quete;
import main.modele.Scenario;

import java.util.ArrayList;

public class Joueur {

    private int experience;
    private int nombrePas;
    private int temps;
    private int[] position;
    private Scenario scenario;
    private ArrayList<Quete> quetesRealisables = new ArrayList<>();
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
     * Methode qui definit les quetes réalisables pour le joueur
     * @param quetesRealisables ArrayList, les quetes realisables par le joueur
     */
    public void setQuetesRealisables(ArrayList<Quete> quetesRealisables) {
        this.quetesRealisables = quetesRealisables;
    }

    /**
     * Methode qui reinitalise les statistiques du joueur
     */
    public void reinitialiserJoueur() {
        experience = 0;
        setTemps(0);
        setPosition(new int [2]);
        nombrePas = 0;
        setQuetesRealisables(new ArrayList<>());
        dejaTrouves = new ArrayList<>();
    }

    public void setTemps(int parTemps){
        temps = parTemps;
    }
    /**
     * Methode qui renoir l'experience du joueur
     * @return experience int, l'experience du joueur
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Methode qui definit le nombre de pas du joueur
     * @return nombrePas int, le nombre de pas du joueur
     */
    public int getNombrePas() {
        return nombrePas;
    }

    /**
     * Methode qui renvoie le temps de jeu du joueur
     * @return temps int, le temps du joueur
     */
    public int getTemps() {
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
        if (parQuete.getNumero() != 0 ){
            experience += parQuete.getExperience();
        }
        temps += parQuete.getDuree() + compareDistance(parQuete.getPositionQuete());
        quetesRealisables.add(parQuete);
        nombrePas += compareDistance(parQuete.getPositionQuete());
        //System.out.println(nombrePas);
        position[0] = parQuete.getPositionQuete()[0];
        position[1] = parQuete.getPositionQuete()[1];
    }

    /**
     * Methode qui permet d'annuler la realisation d'une quete
     * @param parQuete Quete, la quete à annuler
     */
    public void annulerRealisationQuete(Quete parQuete) {
        for (int i = 0; i < dejaTrouves.size()-1; i++){
            if (dejaTrouves.get(i).equals(parQuete.getNumero())) {
                dejaTrouves.remove(i);
            }
        }
        if (parQuete.getNumero() != 0 ){
            experience -= parQuete.getExperience();
        }
        temps = temps - (parQuete.getDuree() - (2*compareDistance(parQuete.getPositionQuete())));
        quetesRealisables.remove(parQuete);
        nombrePas = nombrePas - compareDistance(parQuete.getPositionQuete());
        position[0] = position[0] - parQuete.getPositionQuete()[0];
        position[1] = position[1] - parQuete.getPositionQuete()[1];
    }

    /**
     * Methode qui renvoie la liste des quetes realisées
     * @return quetesRealisees ArrayList, la liste des quetes realisées
     */
    public ArrayList<Quete> getQuetesRealisables() {
        return quetesRealisables;
    }

    /**
     * Methode qui renvoie la liste des quetes realisables
     */
    public void getRealisables() {
        quetesRealisables = new ArrayList<>();
        for (Quete quete : scenario.getStaticProvQuetes()) {

            if (!quetesRealisables.contains(quete)) {
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
                        quetesRealisables.add(quete);
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
        return quetesRealisables.contains(parQuete);
    }

    /**
     * Methode qui permet de savoir si la quete finale est realisable
     * @return boolean, true si la quete finale est realisable, false sinon
     */
    public boolean queteFinaleRealisable() {
        ArrayList<Quete> listeQuete = getQuetesRealisables();
        for (Quete quete : listeQuete) {
            if (quete.getNumero() == 0 && experience >= quete.getExperience()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode qui permet de savoir si la quete finale est realisable sans prendre en compte l'experience
     * @return boolean, true si la quete finale est realisable sans prendre en compte l'experience, false sinon
     */
    public boolean queteFinaleRealisableSansXp() {
        ArrayList<Quete> listeQuete = getQuetesRealisables();
        for (Quete quete : listeQuete) {
            if (quete.getNumero() == 0) {
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
     * @param parListe ArrayList, la liste des quetes
     * @return Quete, la quete la plus proche
     */
    public Quete quetePlusProche(ArrayList<Quete> parListe) {
        int distance = 100000; // valeur arbitraire
        Quete quetesPlusProches = null;
        for (Quete quete : parListe) {
            if (compareDistance(quete.getPositionQuete()) < distance) {
                distance = compareDistance(quete.getPositionQuete());
                quetesPlusProches = quete;
            }
        }
        return quetesPlusProches;
    }


}
