package main.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueteTest {

    /**
     * Test de la méthode aucunePrecond()
     */
    @Test
    public void testAucunePrecond() {
        // Création d'une quête sans préconditions
        Quete queteSansPrecond = new Quete("1|(0,0)|()|10|20|Quête sans préconditions");

        assertTrue(queteSansPrecond.aucunePrecond());


        // Création d'une quête avec préconditions
        Quete queteAvecPrecond = new Quete("2|(0,0)|(1,2,0,0)|20|30|Quête avec préconditions");

        assertFalse(queteAvecPrecond.aucunePrecond());
    }

    @Test
    void getPrecondition() {
    }
}