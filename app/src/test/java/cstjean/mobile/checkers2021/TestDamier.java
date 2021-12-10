package cstjean.mobile.checkers2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.LinkedList;

import cstjean.mobile.checkers2021.code.Dame;
import cstjean.mobile.checkers2021.code.Damier;
import cstjean.mobile.checkers2021.code.Pion;
import cstjean.mobile.checkers2021.code.Tuile;

/**
 * Série de test sur le damier et ses propriétées.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class TestDamier {

    /**
     * Vérifie l'initialisation du damier
     */
    @Test
    public void testInitialiser() {

        Damier damierTest = Damier.getInstance();

        damierTest.initialiser();

        assertEquals(40, damierTest.getNbPions());
        assertEquals(20, damierTest.compterCouleur(Pion.Couleur.NOIR));
        assertEquals(20, damierTest.compterCouleur(Pion.Couleur.BLANC));
    }


    /**
     * Test si la méthode CompterCouleur compte le bon nombre de pions selon leur couleur
     */
    @Test
    public void testCompterCouleur() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        assertEquals(20, damierTest.compterCouleur(Pion.Couleur.NOIR));
        assertEquals(20, damierTest.compterCouleur(Pion.Couleur.BLANC));

    }

    /**
     * Test si le pion est une dame ou non
     */
    @Test
    public void testEstUneDame() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        Tuile tuileTest = new Tuile(1, 0);
        damierTest.transformerPion(tuileTest);
        assertTrue((damierTest.getPion(tuileTest) instanceof Dame));

    }

    /**
     * Test qui effectue un total des pions sur le Damier
     */
    @Test
    public void testGetNbPion() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        assertEquals(40, damierTest.getNbPions());
    }

    /**
     * Test qui verifie qu'on retourne le bon pion sur une certaine tuile
     */
    @Test
    public void testGetPion() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        Pion pionTestNoir = new Pion(Pion.Couleur.NOIR);
        Pion pionTestBlanc = new Pion(Pion.Couleur.BLANC);
        Tuile tuileTest = new Tuile(11, 11);
        assertNull(damierTest.getPion(tuileTest));

        damierTest.ajouterPion(new Tuile(0, 5), pionTestNoir);
        assertEquals(pionTestNoir, damierTest.getPion(new Tuile(0, 5)));

        damierTest.ajouterPion(new Tuile(0, 5), pionTestBlanc);
        assertEquals(pionTestBlanc, damierTest.getPion(new Tuile(0, 5)));


    }

    /**
     * Test qui verifie que le déplacement d'un pion s'effectue correctement
     */
    @Test
    public void testDeplacerPion() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        Pion pionTestNoir = damierTest.getPion(new Tuile(0, 3));
        Pion pionTestBlanc = damierTest.getPion(new Tuile(1, 6));

        damierTest.deplacerPion(pionTestNoir, new Tuile(0, 3), new Tuile(1, 4));
        assertNull(damierTest.getPion(new Tuile(0, 3)));
        assertEquals(pionTestNoir, damierTest.getPion(new Tuile(1, 4)));

        damierTest.deplacerPion(pionTestBlanc, new Tuile(1, 6), new Tuile(0, 5));
        assertNull(damierTest.getPion(new Tuile(1, 6)));
        assertEquals(pionTestBlanc, damierTest.getPion(new Tuile(0, 5)));

    }

    @Test
    public void testNbMouvementMax(){
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        damierTest.detecterNbMovementPion(damierTest.getTuile(4,5), new LinkedList<Tuile>());
        LinkedList<Tuile> listTest = new LinkedList<Tuile>();
        assertTrue(damierTest.listeMove.get(0).contains(damierTest.getTuile(4,5)));
        assertTrue(damierTest.listeMove.get(0).contains(damierTest.getTuile(2,3)));

        assertTrue(damierTest.listeMove.get(1).contains(damierTest.getTuile(6,3)));
        assertTrue(damierTest.listeMove.get(1).contains(damierTest.getTuile(8,1)));
    }


    @Test
    public void testDamierCLone() throws CloneNotSupportedException {
        Damier damier1 = Damier.getInstance();
        Damier damier2 = damier1.clone();
        damier2.supprimerPion(new Tuile(1,0));
        assertNotEquals(damier2.estVideTuile(new Tuile(1,0)), damier1.estVideTuile(new Tuile(1,0)));
    }


    /**
     * Test verifiant qu'une tuile est vide
     */
    @Test
    public void testEstVideTuile() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();


        assertTrue(damierTest.estVideTuile(new Tuile(1, 4)));
        assertFalse(damierTest.estVideTuile(new Tuile(-1, -1)));
        assertFalse(damierTest.estVideTuile(new Tuile(1, 0)));
        assertFalse(damierTest.estVideTuile(new Tuile(15, 0)));
    }

    /**
     * Test verifiant que les cases disponibles pour un pion sont les bonnes
     */
    @Test
    public void testObtenirCasesDisponibles() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // Test pion noir aucun déplacement
        assertEquals(0,
                damierTest.obtenirCasesDisponibles(new Tuile(1, 0)).size());


        // Test Pion Blanc avec 3 déplacements
        LinkedList<Tuile> listePionBlanc = new LinkedList<>();
        listePionBlanc.add(new Tuile(0, 5));
        listePionBlanc.add(new Tuile(2, 5));

        assertEquals(listePionBlanc,
                damierTest.obtenirCasesDisponibles(new Tuile(1, 6)));

        // Test Dame noir avec déplacement reculons et simple

        damierTest.supprimerPion(new Tuile(3, 2));

        damierTest.transformerPion(new Tuile(4, 3));

        LinkedList<Tuile> listeDameNoir = new LinkedList<>();
        listeDameNoir.add(new Tuile(3, 2));
        listeDameNoir.add(new Tuile(5, 4));
        listeDameNoir.add(new Tuile(6, 5));
        listeDameNoir.add(new Tuile(3, 4));
        listeDameNoir.add(new Tuile(2, 5));

        assertTrue((damierTest.getPion(new Tuile(4, 3)) != null));
        assertEquals(listeDameNoir,
                damierTest.obtenirCasesDisponibles(new Tuile(4, 3)));

        // Test Dame Blanche
        Dame dameBlancheTest = new Dame(Pion.Couleur.BLANC);
        damierTest.initialiser();

        damierTest.supprimerPion(new Tuile(5, 6));

        damierTest.ajouterPion(new Tuile(5, 6), dameBlancheTest);
        damierTest.ajouterPion(new Tuile(4, 5), new Pion(Pion.Couleur.BLANC));
        LinkedList<Tuile> listeCaseDispo = new LinkedList<>();

        listeCaseDispo.add(new Tuile(6, 5));
        listeCaseDispo.add(new Tuile(7, 4));

        assertEquals(listeCaseDispo, damierTest.obtenirCasesDisponibles(new Tuile(5, 6)));

        // Test dame manger

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(2, 3));

        damierTest.ajouterPion(new Tuile(1, 4), new Pion(Pion.Couleur.BLANC));

        damierTest.gererDeplacement(new Tuile(2, 3), new Tuile(0, 5));

        assertTrue(damierTest.getPion(new Tuile(0, 5)) instanceof Dame);

        // Test dame position posible

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(4, 3));

        damierTest.ajouterPion(new Tuile(2, 5), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(1, 6), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(5, 4), new Pion(Pion.Couleur.BLANC));

        LinkedList<Tuile> listeCaseDispoTestDame = new LinkedList<>();
        listeCaseDispoTestDame.add(new Tuile(6, 5));
        listeCaseDispoTestDame.add(new Tuile(3, 4));

        assertEquals(listeCaseDispoTestDame,
                damierTest.obtenirCasesDisponibles(new Tuile(4, 3)));

        // Test dame position possible haut gauche

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(1, 6));
        damierTest.supprimerPion(new Tuile(4, 3));
        //damierTest.ajouterPion(new Tuile(2,5), new Pion(Pion.Couleur.BLANC));
        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.BLANC));
        damierTest.ajouterPion(new Tuile(0, 5), new Pion(Pion.Couleur.BLANC));
        assertEquals(1,
                damierTest.obtenirCasesDisponibles(new Tuile(1, 6)).size());

        // Test dame peutManger haut gauche

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(3, 6));

        damierTest.ajouterPion(new Tuile(2, 5), new Pion(Pion.Couleur.NOIR));
        assertEquals(3,
                damierTest.obtenirCasesDisponibles(new Tuile(3, 6)).size());

        // Test dame pas tué allié bas droite

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(0, 3));

        damierTest.ajouterPion(new Tuile(1, 4), new Pion(Pion.Couleur.NOIR));
        assertEquals(0,
                damierTest.obtenirCasesDisponibles(new Tuile(0, 3)).size());

        // Test dame pas tué allié bas gauche

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(2, 3));

        damierTest.ajouterPion(new Tuile(1, 4), new Pion(Pion.Couleur.NOIR));
        assertEquals(2,
                damierTest.obtenirCasesDisponibles(new Tuile(2, 3)).size());

        // Test dame peutManger haut droite

        damierTest.initialiser();

        damierTest.transformerPion(new Tuile(3, 6));

        damierTest.ajouterPion(new Tuile(4, 5), new Pion(Pion.Couleur.NOIR));
        assertEquals(3,
                damierTest.obtenirCasesDisponibles(new Tuile(3, 6)).size());
    }

    /**
     * Test verifiant que si aucun pion d'une couleur est présent sur le jeu, on retourne vrai
     */
    @Test
    public void testVerifierResteAucunPion() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // Élimination des pion noirs

        // Rangée une
        damierTest.supprimerPion(new Tuile(1, 0));
        damierTest.supprimerPion(new Tuile(3, 0));
        damierTest.supprimerPion(new Tuile(5, 0));
        damierTest.supprimerPion(new Tuile(7, 0));
        damierTest.supprimerPion(new Tuile(9, 0));

        // Rangée deux
        damierTest.supprimerPion(new Tuile(0, 1));
        damierTest.supprimerPion(new Tuile(2, 1));
        damierTest.supprimerPion(new Tuile(4, 1));
        damierTest.supprimerPion(new Tuile(6, 1));
        damierTest.supprimerPion(new Tuile(8, 1));

        // Rangée trois
        damierTest.supprimerPion(new Tuile(1, 2));
        damierTest.supprimerPion(new Tuile(3, 2));
        damierTest.supprimerPion(new Tuile(5, 2));
        damierTest.supprimerPion(new Tuile(7, 2));
        damierTest.supprimerPion(new Tuile(9, 2));

        // Rangée quatre
        damierTest.supprimerPion(new Tuile(0, 3));
        damierTest.supprimerPion(new Tuile(2, 3));
        damierTest.supprimerPion(new Tuile(4, 3));
        damierTest.supprimerPion(new Tuile(6, 3));
        damierTest.supprimerPion(new Tuile(8, 3));

        assertTrue(damierTest.verifierResteAucunPion(Pion.Couleur.NOIR));
    }

    /**
     * Test verifiant que si aucun des pions d'une couleur peut bouger, on retourne vrai
     */
    @Test
    public void testVerifierPeuxPasBouger() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // NE PASSE PAR CAR OBTENIR CASE DÉPLACEMENT EST PAS BON
        assertFalse(damierTest.verifierPeuxPasBouger(Pion.Couleur.BLANC));
        assertFalse(damierTest.verifierPeuxPasBouger(Pion.Couleur.NOIR));

        // Remplissage des cases du jeu par des pions noirs partout
        Pion pionNoir = new Pion(Pion.Couleur.NOIR);
        damierTest.ajouterPion(new Tuile(0, 5), pionNoir);
        damierTest.ajouterPion(new Tuile(1, 4), pionNoir);
        damierTest.ajouterPion(new Tuile(2, 5), pionNoir);
        damierTest.ajouterPion(new Tuile(3, 4), pionNoir);
        damierTest.ajouterPion(new Tuile(4, 5), pionNoir);
        damierTest.ajouterPion(new Tuile(5, 4), pionNoir);
        damierTest.ajouterPion(new Tuile(6, 5), pionNoir);
        damierTest.ajouterPion(new Tuile(7, 4), pionNoir);
        damierTest.ajouterPion(new Tuile(8, 5), pionNoir);
        damierTest.ajouterPion(new Tuile(9, 4), pionNoir);

        assertEquals(damierTest.compterCouleur(Pion.Couleur.NOIR), 30);
        assertTrue(damierTest.verifierPeuxPasBouger(Pion.Couleur.BLANC));

    }


    /**
     * Verifie le deplacement d'un pion. Regarde si lors du deplacement le pion sur la tuile
     * désirée est celui qu'on à déplacé et que l'ancienne tuile ne contient aucun pion.
     */
    @Test
    public void testGererDeplacement() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // Test déplacement Pion

        // Test pion blanc Haut Droite
        damierTest.ajouterPion(new Tuile(4, 5), new Pion(Pion.Couleur.NOIR));

        damierTest.gererDeplacement(new Tuile(3, 6), new Tuile(5, 4));

        Pion pionTestBlanc = damierTest.getPion(new Tuile(5, 4));
        // Pion n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(4, 5)));

        // Pion qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(pionTestBlanc.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(5, 4)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(5, 4)));

        // Test pion blanc haut gauche
        damierTest.initialiser();
        damierTest.ajouterPion(new Tuile(2, 5), new Pion(Pion.Couleur.NOIR));

        damierTest.gererDeplacement(new Tuile(3, 6), new Tuile(1, 4));

        Pion pionTestBlanc2 = damierTest.getPion(new Tuile(1, 4));
        // Pion n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(3, 6)));

        // Pion qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(pionTestBlanc2.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(1, 4)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(1, 4)));


        // déplacement pion  noir Bas gauche avec prise
        damierTest.initialiser();
        Pion pionNoirTest = damierTest.getPion(new Tuile(2, 3));
        damierTest.ajouterPion(new Tuile(1, 4), new Pion(Pion.Couleur.BLANC));

        damierTest.gererDeplacement(new Tuile(2, 3), new Tuile(0, 5));

        // Pion n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(2, 3)));

        // Pion qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(pionNoirTest.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(0, 5)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(0, 5)));

        // déplacement pion Bas droite avec prise
        damierTest.initialiser();
        Pion pionTestNoirDeux = damierTest.getPion(new Tuile(2, 3));
        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.BLANC));

        damierTest.gererDeplacement(new Tuile(2, 3), new Tuile(4, 5));

        // Pion n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(2, 3)));

        // Pion qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(pionTestNoirDeux.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(4, 5)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(4, 5)));

        // déplacement simple pion noir

        damierTest.initialiser();
        Pion pionNoirTest3 = damierTest.getPion(new Tuile(0, 3));

        damierTest.gererDeplacement(new Tuile(0, 3), new Tuile(1, 4));

        // Pion n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(0, 3)));

        // Pion qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(pionNoirTest3.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(1, 4)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(1, 4)));

        // déplacement simple pion blanc

        damierTest.initialiser();
        Pion pionBlancTest3 = damierTest.getPion(new Tuile(1, 6));

        damierTest.gererDeplacement(new Tuile(1, 6), new Tuile(0, 5));

        // Pion n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(1, 6)));

        // Pion qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(pionBlancTest3.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(0, 5)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(0, 5)));

        // Test déplacement Dame
        damierTest.initialiser();

        Dame dameTest = new Dame(Pion.Couleur.BLANC);

        damierTest.ajouterPion(new Tuile(3, 4), dameTest);

        // Test bas gauche
        damierTest.gererDeplacement(new Tuile(3, 4), new Tuile(2, 5));

        // Dame n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(3, 4)));

        // Dame qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(dameTest.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(2, 5)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(2, 5)));

        // Supprimation d'un pion pour le déplacement de la dame
        damierTest.supprimerPion(new Tuile(3, 6));

        // Test Bas droite
        damierTest.gererDeplacement(new Tuile(2, 5), new Tuile(3, 6));

        // Dame n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(2, 5)));

        // Dame qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(dameTest.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(3, 6)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(3, 6)));

        // Test Haut Droite
        damierTest.gererDeplacement(new Tuile(3, 6), new Tuile(4, 5));

        // Dame n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(3, 6)));

        // Dame qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(dameTest.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(4, 5)));

        // La case où on voulait se déplacer n'est plus vide
        assertFalse(damierTest.estVideTuile(new Tuile(4, 5)));

        // Test haut gauche
        damierTest.gererDeplacement(new Tuile(4, 5), new Tuile(3, 4));

        // Dame n'est plus à sa place initiale
        assertTrue(damierTest.estVideTuile(new Tuile(4, 5)));

        // Dame qui a bougé est rendu sur la bonne case et est de la bonne couleur
        assertEquals(dameTest.getCouleur(),
                damierTest.getCouleurPionSurTuile(new Tuile(3, 4)));

        // Test transformation en dame pion noir
        damierTest.initialiser();

        damierTest.supprimerPion(new Tuile(3, 8));
        damierTest.supprimerPion(new Tuile(2, 9));

        damierTest.ajouterPion(new Tuile(3, 8), new Pion(Pion.Couleur.NOIR));

        damierTest.gererDeplacement(new Tuile(3, 8), new Tuile(2, 9));

        assertTrue(damierTest.getPion(new Tuile(2, 9)) instanceof Dame);

        // Test transformation en dame pion blanc
        damierTest.initialiser();

        damierTest.supprimerPion(new Tuile(5, 0));
        damierTest.supprimerPion(new Tuile(4, 1));

        damierTest.ajouterPion(new Tuile(4, 1), new Pion(Pion.Couleur.BLANC));

        damierTest.gererDeplacement(new Tuile(4, 1), new Tuile(5, 0));

        assertTrue(damierTest.getPion(new Tuile(5, 0)) instanceof Dame);

    }

    /**
     * Test qui attend de recevoir une exception lorsque la une tuile qui ne
     * contient pas de pion est utilisée lors de la méthode gererDeplacement
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGererDeplacementNull() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        damierTest.gererDeplacement(new Tuile(0, 5), new Tuile(1, 4));
    }

    /**
     * Test qui vérifie que la détermination de la notation manoury se fait correctement
     * Vérifie aussi la fonctionnalité du getListeManoury
     */
    @Test
    public void testDeterminerNotationManoury() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();


        // Déplacement pion blanc normal
        int nbPion = damierTest.getNbPions();
        damierTest.gererDeplacement(new Tuile(3, 6), new Tuile(4, 5));

        damierTest.determinerNotationManoury(new Tuile(4, 5), new Tuile(3, 6),
                nbPion);

        assertEquals("32-28", damierTest.getlisteManouryMouvement().get(0));

        // Déplacement pion noir avec prise

        damierTest.initialiser();

        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.BLANC));

        int nbPion1 = damierTest.getNbPions();
        damierTest.gererDeplacement(new Tuile(3, 6), new Tuile(4, 5));
        damierTest.determinerNotationManoury(new Tuile(4, 5), new Tuile(3, 6),
                nbPion1);

        int nbPion2 = damierTest.getNbPions();
        damierTest.gererDeplacement(new Tuile(4, 3), new Tuile(2, 5));
        damierTest.determinerNotationManoury(new Tuile(2, 5), new Tuile(4, 3),
                nbPion2);

        assertEquals("(18x27)", damierTest.getlisteManouryMouvement().get(2));
    }

    /**
     * Test qui vérifie que le get de la list de tuiles et de pions est de la bonne grandeur après
     * son initialisation
     */
    @Test
    public void testGetTuiles() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        assertEquals(damierTest.getTuiles().size(), 100);
    }

    /**
     * Verifie que le tour du joueur blanc est vrai au début et
     * qu'il change lorsque le joueur blanc joue un tour
     */
    @Test
    public void testGetEstTourJoueurBlanc() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        assertTrue(damierTest.getestTourJoueurBlanc());

        Pion pionTest = new Pion(Pion.Couleur.BLANC);
        damierTest.deplacerPion(pionTest, new Tuile(1, 6), new Tuile(2, 5));

        assertFalse(damierTest.getestTourJoueurBlanc());

    }

    /**
     * Test verifiant que la tuile qu'on veut est bien celle qui
     * est à l'emplacement du damier
     */
    @Test
    public void testGetTuile() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        Tuile tuileTestValide = new Tuile(2, 7);

        assertEquals(tuileTestValide, damierTest.getTuile(2, 7));

        assertNull(damierTest.getTuile(-1, -1));
    }


}
