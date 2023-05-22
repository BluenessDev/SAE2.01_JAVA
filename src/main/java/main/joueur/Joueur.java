package main.joueur;

public class Joueur {

    private static int experience;
    private static int nombrePas;
    private static int temps;
    private static int[] position;

    public Joueur() {
        experience = 0;
        nombrePas = 0;
        temps = 0;
        position = new int[2];
    }


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

    public static void setPosition(int[] parPos){
        position = parPos;
    }

    public static int[] getPosition() {
        return position;
    }

    /**
     * Compare la position du joueur avec une position donnée
     * @param parPos int[2], la position du joueur
     * @return distance int, la distance entre les deux positions
     */
    public int compareTo(int[] parPos){
            int Dx = position[0] - parPos[0];
            int Dy = position[1] - parPos[1];
            int distance = Math.abs(Dx) + Math.abs(Dy);
            return distance;
    }


}
