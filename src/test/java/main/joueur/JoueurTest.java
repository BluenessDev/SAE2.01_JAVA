package main.joueur;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    /**
     * Test de la methode compareTo
     */
    @Test
    void compareTo() {
        Joueur joueur = new Joueur();
        int[] pos = {0,0};
        joueur.setPosition(pos);
        int[] pos2 = {3,3};
        assertEquals(6, joueur.compareTo(pos2));
        int[] pos3 = {3,3};
        joueur.setPosition(pos3);
        int[] pos4 = {0,0};
        assertEquals(6, joueur.compareTo(pos4));
        int[] pos5 = {5,8};
        joueur.setPosition(pos5);
        int[] pos6 = {7,3};
        assertEquals(7, joueur.compareTo(pos6));
        int[] pos7 = {2,4};
        joueur.setPosition(pos7);
        int[] pos8 = {10,6};
        assertEquals(10, joueur.compareTo(pos8));
        int[] pos9 = {24,16};
        joueur.setPosition(pos9);
        int[] pos10 = {21,14};
        assertEquals(5, joueur.compareTo(pos10));

    }
}