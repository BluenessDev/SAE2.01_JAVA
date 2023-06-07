package main.joueur;

import main.lectureEcritureFichier.LectureFichierTexte;
import main.modele.Quete;
import main.modele.Scenario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    /**
     * Test de la methode compareTo
     */
    @Test
    void testCompareDistancee() {
        Scenario scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        Joueur joueur = new Joueur(scenario);
        int[] pos = {0,0};
        joueur.setPosition(pos);
        int[] pos2 = {3,3};
        assertEquals(6, joueur.compareDistance(pos2));
        int[] pos3 = {3,3};
        joueur.setPosition(pos3);
        int[] pos4 = {0,0};
        assertEquals(6, joueur.compareDistance(pos4));
        int[] pos5 = {5,8};
        joueur.setPosition(pos5);
        int[] pos6 = {7,3};
        assertEquals(7, joueur.compareDistance(pos6));
        int[] pos7 = {2,4};
        joueur.setPosition(pos7);
        int[] pos8 = {10,6};
        assertEquals(10, joueur.compareDistance(pos8));
        int[] pos9 = {24,16};
        joueur.setPosition(pos9);
        int[] pos10 = {21,14};
        assertEquals(5, joueur.compareDistance(pos10));

    }

    /**
     * Test de la methode quetePlusProche
     */
    @Test
    void testQuetePlusProche(){
        Scenario scenario = LectureFichierTexte.lecture(new File("scenario" + File.separator + "scenario_0.txt"));
        Joueur joueur = new Joueur(scenario);
        ArrayList<Quete> listquetes = new ArrayList<>();
        Quete quete1 = new Quete("1|(30, 68)|((18,),)|5|200|quête 1");
        listquetes.add(quete1);
        Quete quete2 = new Quete("2|(32, 10)|((7,),)|2|150|quête 2");
        listquetes.add(quete2);
        Quete quete3 = new Quete("3|(183, 153)|((2, 11), (8,))|10|350|quête 3");
        listquetes.add(quete3);

        assertEquals(quete2, joueur.quetePlusProche(listquetes));
        int[] pos1 = {50,50};
        joueur.setPosition(pos1);
        assertEquals(quete1, joueur.quetePlusProche(listquetes));
        int[] pos2 = {150,150};
        joueur.setPosition(pos2);
        assertEquals(quete3, joueur.quetePlusProche(listquetes));
    }

    /**
     * Test de la methode estRealisable
     */
    @Test
    void testEstRealisable() {
        Scenario scenario = new Scenario();
        Joueur joueur = new Joueur(scenario);
        Quete quete1 = new Quete("1|(0,0)|()|10|20|quête 1");
        Quete quete2 = new Quete("2|(0,0)|((1,),)|20|30|quête 2");
        Quete quete3 = new Quete("3|(0,0)|((1,2),)|30|40|quête 3");
        Quete quete4 = new Quete("4|(0,0)|((1,), (2,))|40|50|quête 4");
        Quete quete5 = new Quete("5|(0,0)|((1,2), (3,))|50|60|quête 5");
        scenario.ajout(quete1);
        scenario.ajout(quete2);
        scenario.ajout(quete3);
        scenario.ajout(quete4);
        scenario.ajout(quete5);

        joueur.getRealisables();
        assertTrue(joueur.estRealisable(quete1));
        assertFalse(joueur.estRealisable(quete2));
        joueur.realiserQuete(quete1);
        joueur.getRealisables();
        assertTrue(joueur.estRealisable(quete2));
        assertTrue(joueur.estRealisable(quete3));
        joueur.realiserQuete(quete3);
        joueur.getRealisables();
        assertFalse(joueur.estRealisable(quete4));
        joueur.realiserQuete(quete2);
        joueur.getRealisables();
        assertTrue(joueur.estRealisable(quete4));
        assertTrue(joueur.estRealisable(quete5));
    }

    @Test
    public void testQueteFinaleRealisable() {
        // Test avec une quête finale réalisable avec l'xp nécessaire
        Scenario scenario = new Scenario();
        scenario.ajout(new Quete("1|(0,0)|()|3|100|explorer forêt interdite"));
        scenario.ajout(new Quete("2|(1,1)|((0),(1))|5|150|vaincre créature sombre"));
        scenario.ajout(new Quete("0|(2,2)|((1),(2))|6|200|collecter herbes magiques"));

        Joueur joueur = new Joueur(scenario);
        for (Quete quete : scenario.getStaticProvQuetes()) {
            joueur.realiserQuete(quete);
        }
        joueur.getRealisables();

        boolean expectedResult = true;
        boolean result = joueur.queteFinaleRealisable();

        Assertions.assertEquals(expectedResult, result);

        // Test avec une quête finale non réalisable (pas assez d'xp)
        scenario = new Scenario();
        scenario.ajout(new Quete("1|(0,0)|()|3|20|explorer forêt interdite"));
        scenario.ajout(new Quete("2|(1,1)|((0),(1))|5|150|vaincre créature sombre"));
        scenario.ajout(new Quete("0|(2,2)|((1),(2))|6|200|collecter herbes magiques"));

        joueur = new Joueur(scenario);
        for (Quete quete : scenario.getStaticProvQuetes()) {
            joueur.realiserQuete(quete);
        }
        joueur.getRealisables();

        expectedResult = false;
        result = joueur.queteFinaleRealisable();

        Assertions.assertEquals(expectedResult, result);

        // Test avec une quête finale non réalisable
        scenario = new Scenario();
        scenario.ajout(new Quete("1|(0,0)|()|3|20|explorer forêt interdite"));
        scenario.ajout(new Quete("2|(1,1)|((3),(1))|5|150|vaincre créature sombre"));
        scenario.ajout(new Quete("0|(2,2)|((4),(2))|6|200|collecter herbes magiques"));

        joueur = new Joueur(scenario);
        for (Quete quete : scenario.getStaticProvQuetes()) {
            joueur.realiserQuete(quete);
        }
        joueur.getRealisables();

        expectedResult = false;
        result = joueur.queteFinaleRealisable();

        Assertions.assertEquals(expectedResult, result);
    }
}