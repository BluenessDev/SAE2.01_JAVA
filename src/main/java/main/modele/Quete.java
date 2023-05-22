package main.modele;

import java.util.Scanner;

public class Quete {
    int numero;

    String pos;

    int [] position = new int[2];

    String precond;

    int [] preconditions = new int[4];

    int duree;
    int experience;
    String intitule;

    public Quete(String ligne) {
        Scanner scanner = new Scanner(ligne).useDelimiter("\\|");
        while(scanner.hasNext()) {
            this.numero = scanner.nextInt();
            String pos = scanner.next();
            pos = pos.replace("(", "");
            pos = pos.replace(")", "");
            pos = pos.replace(" ", "");
            Scanner scanPos = new Scanner(pos).useDelimiter(",");
            int i = 0;
            while (scanPos.hasNext()) {
                position[i] = Integer.parseInt(scanPos.next());
                i++;
            }
            String precond = scanner.next();
            precond = precond.replace("(", "");
            precond = precond.replace(")", "");
            precond = precond.replace(" ", "");
            Scanner scanPrecondition = new Scanner(precond).useDelimiter(",");
            int j = 0;
            while (scanPrecondition.hasNext()) {
                String extrait = scanPrecondition.next();
                if (!extrait.equals(""))
                    preconditions[j] = Integer.parseInt(extrait);
                j++;
            }
            this.duree = scanner.nextInt();
            this.experience = scanner.nextInt();
            this.intitule = scanner.next();
        }
    }

    public boolean aucunePrecond() {
        for (int i = 0; i < this.preconditions.length; i++) {
            if (preconditions[i] != 0)
                return false;
        }
        return true;
    }

    public int [] getPrecondition(String ligne) {
        return preconditions;
    }

    public int [] getPosition(String ligne){ return position; }

    public int getNumero() { return numero; }

    public String toString() {
        return numero + " [" + position[0] + "," + position[1] + "] [" + preconditions[0] + "," + preconditions[1] + "," + preconditions[2] +  "," + preconditions[3] + "] " + duree + " " + experience + " " + intitule;
    }

}