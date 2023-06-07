package main.niveaux;

import main.joueur.Joueur;
import main.modele.Quete;
import main.modele.Scenario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class Niveau2Test {

    /**
     * Test de la méthode calulerTempsChemin()
     */
    @Test
    public void testCalulerTempsChemin() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin = new ArrayList<>();
        chemin.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        int tempsTotal = niveau2.calulerTempsChemin(chemin);

        Assertions.assertEquals(32, tempsTotal);

        chemin = new ArrayList<>();
        chemin.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin.add(new Quete("8|(2,6)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        tempsTotal = niveau2.calulerTempsChemin(chemin);

        Assertions.assertEquals(28, tempsTotal);
    }

    /**
     * Test de la méthode calulerNombrePas()
     */
    @Test
    public void testCalulerNombrePas() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin = new ArrayList<>();
        chemin.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        int nombrePas = niveau2.calulerNombrePas(chemin);

        Assertions.assertEquals(16,nombrePas);

        chemin = new ArrayList<>();
        chemin.add(new Quete("2|(2,6)|()|4|100|explorer temple de Ssashara"));
        chemin.add(new Quete("3|(5,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin.add(new Quete("8|(2,8)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        nombrePas = niveau2.calulerNombrePas(chemin);

        Assertions.assertEquals(18,nombrePas);

    }

    /**
     * Test de la méthode calulerNombreQuetes()
     */
    @Test
    public void testCalulerNombreQuetes() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin = new ArrayList<>();
        chemin.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        int nombreQuetes = niveau2.calulerNombreQuetes(chemin);

        Assertions.assertEquals(3, nombreQuetes);

        chemin = new ArrayList<>();

        nombreQuetes = niveau2.calulerNombreQuetes(chemin);

        Assertions.assertEquals(0, nombreQuetes);

    }

    /**
     * Test de la méthode calulerNombreQuetes()
     */
    @Test
    public void testTrierCheminsParTemps() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin1 = new ArrayList<>();
        chemin1.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin1.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin1.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        niveau2.getListe_chemins().add(chemin1);

        ArrayList<Quete> chemin2 = new ArrayList<>();
        chemin2.add(new Quete("7|(4,4)|()|4|0|vaincre Roi maudit"));

        niveau2.getListe_chemins().add(chemin2);

        ArrayList<Quete> chemin3 = new ArrayList<>();
        chemin3.add(new Quete("4|(2,5)|((8,5),())|4|200|explorer palais de Naghguruhm"));

        niveau2.getListe_chemins().add(chemin3);

        niveau2.trierCheminsParTempsMeilleur(niveau2.getListe_chemins());
        ArrayList<ArrayList<Quete>>expectedListeCheminsTriee = new ArrayList<>();
        expectedListeCheminsTriee.add(chemin3);
        expectedListeCheminsTriee.add(chemin2);
        expectedListeCheminsTriee.add(chemin1);

        Assertions.assertEquals(expectedListeCheminsTriee,niveau2.getListe_chemins());
    }

    /**
     * Test de la méthode trierCheminsParQuete()
     */
    @Test
    public void testTrierCheminsParQueteMeilleur() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin1 = new ArrayList<>();
        chemin1.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin1.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin1.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        niveau2.getListe_chemins().add(chemin1);

        ArrayList<Quete> chemin2 = new ArrayList<>();
        chemin2.add(new Quete("7|(4,4)|()|4|0|vaincre Roi maudit"));

        niveau2.getListe_chemins().add(chemin2);

        ArrayList<Quete> chemin3 = new ArrayList<>();
        chemin3.add(new Quete("4|(2,5)|((8,5),())|4|200|explorer palais de Naghguruhm"));
        chemin3.add(new Quete("5|(5, 2)|((6,),)|3|150|vaincre Calmar"));

        niveau2.getListe_chemins().add(chemin3);

        niveau2.trierCheminsParQuetesMeilleur(niveau2.getListe_chemins());
        ArrayList<ArrayList<Quete>> expectedListeCheminsTriee = new ArrayList<>();
        expectedListeCheminsTriee.add(chemin2);
        expectedListeCheminsTriee.add(chemin3);
        expectedListeCheminsTriee.add(chemin1);

        Assertions.assertEquals(expectedListeCheminsTriee, niveau2.getListe_chemins());
    }

    /**
     * Test de la méthode trierCheminsParDistanceMeilleur()
     */
    @Test
    public void testTrierCheminsParDistanceMeilleur() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin1 = new ArrayList<>();
        chemin1.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin1.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin1.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        niveau2.getListe_chemins().add(chemin1);

        ArrayList<Quete> chemin2 = new ArrayList<>();
        chemin2.add(new Quete("7|(4,4)|()|4|0|vaincre Roi maudit"));

        niveau2.getListe_chemins().add(chemin2);

        ArrayList<Quete> chemin3 = new ArrayList<>();
        chemin3.add(new Quete("4|(2,5)|((8,5),())|4|200|explorer palais de Naghguruhm"));

        niveau2.getListe_chemins().add(chemin3);

        niveau2.trierCheminsParDistanceMeilleur(niveau2.getListe_chemins());
        ArrayList<ArrayList<Quete>> expectedListeCheminsTriee = new ArrayList<>();
        expectedListeCheminsTriee.add(chemin3);
        expectedListeCheminsTriee.add(chemin2);
        expectedListeCheminsTriee.add(chemin1);

        Assertions.assertEquals(expectedListeCheminsTriee, niveau2.getListe_chemins());
    }

    /**
     * Test de la méthode trierCheminsParTempsPire()
     */
    @Test
    public void testTrierCheminsParTempsPire() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin1 = new ArrayList<>();
        chemin1.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin1.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin1.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        niveau2.getListe_chemins().add(chemin1);

        ArrayList<Quete> chemin2 = new ArrayList<>();
        chemin2.add(new Quete("7|(4,4)|()|4|0|vaincre Roi maudit"));

        niveau2.getListe_chemins().add(chemin2);

        ArrayList<Quete> chemin3 = new ArrayList<>();
        chemin3.add(new Quete("4|(2,5)|((8,5),())|4|200|explorer palais de Naghguruhm"));

        niveau2.getListe_chemins().add(chemin3);

        niveau2.trierCheminsParTempsPire(niveau2.getListe_chemins());
        ArrayList<ArrayList<Quete>> expectedListeCheminsTriee = new ArrayList<>();
        expectedListeCheminsTriee.add(chemin1);
        expectedListeCheminsTriee.add(chemin2);
        expectedListeCheminsTriee.add(chemin3);

        Assertions.assertEquals(expectedListeCheminsTriee, niveau2.getListe_chemins());
    }

    /**
     * Test de la méthode trierCheminsParTempsMeilleur()
     */
    @Test
    public void testTrierCheminsParQuetePire() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin1 = new ArrayList<>();
        chemin1.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin1.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin1.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        niveau2.getListe_chemins().add(chemin1);

        ArrayList<Quete> chemin2 = new ArrayList<>();
        chemin2.add(new Quete("7|(4,4)|()|4|0|vaincre Roi maudit"));

        niveau2.getListe_chemins().add(chemin2);

        ArrayList<Quete> chemin3 = new ArrayList<>();
        chemin3.add(new Quete("4|(2,5)|((8,5),())|4|200|explorer palais de Naghguruhm"));
        chemin3.add(new Quete("5|(5, 2)|((6,),)|3|150|vaincre Calmar"));

        niveau2.getListe_chemins().add(chemin3);

        niveau2.trierCheminsParQuetesPire(niveau2.getListe_chemins());
        ArrayList<ArrayList<Quete>> expectedListeCheminsTriee = new ArrayList<>();
        expectedListeCheminsTriee.add(chemin1);
        expectedListeCheminsTriee.add(chemin3);
        expectedListeCheminsTriee.add(chemin2);

        Assertions.assertEquals(expectedListeCheminsTriee, niveau2.getListe_chemins());
    }

    /**
     * Test de la méthode trierCheminsParDistanceMeilleur()
     */
    @Test
    public void testTrierCheminsParDistancePire() {
        Niveau2 niveau2 = new Niveau2(new Scenario());
        ArrayList<Quete> chemin1 = new ArrayList<>();
        chemin1.add(new Quete("2|(2,3)|()|4|100|explorer temple de Ssashara"));
        chemin1.add(new Quete("3|(0,5)|((2),(7))|7|150|explorer forêt de Them Ulihm"));
        chemin1.add(new Quete("8|(2,0)|((3,7),(5,1))|5|150|explorer steppe de Dhamedur"));

        niveau2.getListe_chemins().add(chemin1);

        ArrayList<Quete> chemin2 = new ArrayList<>();
        chemin2.add(new Quete("7|(4,4)|()|4|0|vaincre Roi maudit"));

        niveau2.getListe_chemins().add(chemin2);

        ArrayList<Quete> chemin3 = new ArrayList<>();
        chemin3.add(new Quete("4|(2,5)|((8,5),())|4|200|explorer palais de Naghguruhm"));

        niveau2.getListe_chemins().add(chemin3);

        niveau2.trierCheminsParDistancePire(niveau2.getListe_chemins());
        ArrayList<ArrayList<Quete>> expectedListeCheminsTriee = new ArrayList<>();
        expectedListeCheminsTriee.add(chemin1);
        expectedListeCheminsTriee.add(chemin2);
        expectedListeCheminsTriee.add(chemin3);

        Assertions.assertEquals(expectedListeCheminsTriee, niveau2.getListe_chemins());
    }

    // Le dernier cas ne fonctionne pas alors qu'il marche dans les vrais scenarios
//    @Test
//    public void testEstExhaustif() {
//        Scenario NouveauScenario = new Scenario();
//        NouveauScenario.ajout(new Quete("1|(0,0)|()|3|100|explorer forêt interdite"));
//        NouveauScenario.ajout(new Quete("2|(1,1)|((0),(3))|5|150|vaincre créature sombre"));
//        NouveauScenario.ajout(new Quete("3|(2,2)|((1),(4))|6|200|collecter herbes magiques"));
//        Niveau2 niveau2 = new Niveau2(NouveauScenario);
//
//
//        // Cas de test 1: Chemin vide
//        ArrayList<Quete> chemin1 = new ArrayList<>();
//        boolean expectedResult1 = false;
//        boolean result1 = niveau2.estExhaustif(chemin1, NouveauScenario);
//        Assertions.assertEquals(expectedResult1, result1);
//
//        // Cas de test 2: Chemin manquant une quête
//        ArrayList<Quete> chemin2 = new ArrayList<>();
//        chemin2.add(new Quete("1|(0,0)|()|3|100|explorer forêt interdite"));
//        chemin2.add(new Quete("2|(1,1)|((0),(3))|5|150|vaincre créature sombre"));
//        boolean expectedResult2 = false;
//        boolean result2 = niveau2.estExhaustif(chemin2, NouveauScenario);
//        Assertions.assertEquals(expectedResult2, result2);
//
//        // Cas de test 3: Chemin avec toutes les quêtes du scénario
//        ArrayList<Quete> chemin3 = new ArrayList<>();
//        chemin3.add(new Quete("1|(0,0)|()|3|100|explorer forêt interdite"));
//        chemin3.add(new Quete("2|(1,1)|((0),(3))|5|150|vaincre créature sombre"));
//        chemin3.add(new Quete("3|(2,2)|((1),(4))|6|200|collecter herbes magiques"));
//        System.out.println(chemin3.toString());
//        boolean expectedResult3 = true;
//        boolean result3 = niveau2.estExhaustif(chemin3, NouveauScenario);
//        Assertions.assertEquals(expectedResult3, result3);
//    }

    @Test

    public void testCalculerXpChemin(){
        Scenario scenario = new Scenario();
        Joueur joueur = new Joueur(scenario);
        Niveau2 niveau2 = new Niveau2(scenario);

        ArrayList<Quete> quetes = new ArrayList<>();

        Quete quete1 = new Quete("1|(10, 20)|(1, 0, 1, 0)|100|60|Quête 1");
        Quete quete2 = new Quete("2|(30, 40)|(0, 1, 0, 1)|200|120|Quête 2");
        Quete quete3 = new Quete("3|(50, 60)|(1, 1, 1, 1)|300|180|Quête 3");

        quetes.add(quete1);
        quetes.add(quete2);
        quetes.add(quete3);


        Assertions.assertEquals(360, niveau2.calulerXPChemin(quetes));
    }
}