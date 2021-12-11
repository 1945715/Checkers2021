package cstjean.mobile.checkers2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cstjean.mobile.checkers2021.code.Dame;
import cstjean.mobile.checkers2021.code.Damier;
import cstjean.mobile.checkers2021.code.Pion;
import cstjean.mobile.checkers2021.code.Tuile;
import org.junit.Test;

/**
 * Série de test sur le damier et ses propriétées.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class TestDamier {

    /**
     * Vérifie l'initialisation du damier.
     */
    @Test
    public void testInitialiser() {

        Damier damierTest = Damier.getInstance();

        damierTest.initialiser();

        assertEquals(40, damierTest.getNbPions());
    }

    @Test
    public void testViderBoard() {

        Damier damierTest = Damier.getInstance();

        damierTest.initialiser();
        damierTest.viderBoard();
        assertEquals(0, damierTest.getNbPions());
    }

    /**
     * Test si le pion est une dame ou non.
     */
    @Test
    public void testEstUneDame() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        testViderBoard();
        Pion pionTestNoir = new Pion(Pion.Couleur.NOIR);
        Pion pionTestBlanc = new Pion(Pion.Couleur.BLANC);

        damierTest.ajouterPion(damierTest.getTuile(1, 8), pionTestNoir);
        damierTest.deplacerPion(damierTest.getTuile(0, 9), damierTest.getTuile(1, 8));

        damierTest.ajouterPion(damierTest.getTuile(0, 1), pionTestBlanc);
        damierTest.deplacerPion(damierTest.getTuile(1, 0), damierTest.getTuile(0, 1));

        assertTrue((damierTest.getPion(damierTest.getTuile(0, 9)) instanceof Dame));
        assertTrue((damierTest.getPion(damierTest.getTuile(1, 0)) instanceof Dame));

    }

    /**
     * Test qui effectue un total des pions sur le Damier.
     */
    @Test
    public void testGetNbPion() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        assertEquals(40, damierTest.getNbPions());
    }

    /**
     * Test qui verifie qu'on retourne le bon pion sur une certaine tuile.
     */
    @Test
    public void testGetPion() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        final Pion pionTestNoir = new Pion(Pion.Couleur.NOIR);
        final Pion pionTestBlanc = new Pion(Pion.Couleur.BLANC);
        Tuile tuileTest = new Tuile(11, 11);
        assertNull(damierTest.getPion(tuileTest));

        damierTest.ajouterPion(new Tuile(0, 5), pionTestNoir);
        assertEquals(pionTestNoir, damierTest.getPion(new Tuile(0, 5)));

        damierTest.ajouterPion(new Tuile(0, 5), pionTestBlanc);
        assertEquals(pionTestBlanc, damierTest.getPion(new Tuile(0, 5)));
    }

    /**
     * Test qui verifie que le déplacement d'un pion s'effectue correctement.
     */
    @Test
    public void testDeplacerPion() throws CloneNotSupportedException {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        final Pion pionTestNoir = damierTest.getPion(damierTest.getTuile(0, 3));
        final Pion pionTestBlanc = damierTest.getPion(damierTest.getTuile(1, 6));

        damierTest.deplacerPion(damierTest.getTuile(1, 4), damierTest.getTuile(0, 3));
        assertNull(damierTest.getPion(damierTest.getTuile(0, 3)));
        assertEquals(pionTestNoir, damierTest.getPion(damierTest.getTuile(1, 4)));

        damierTest.deplacerPion(damierTest.getTuile(0, 5), damierTest.getTuile(1, 6));
        assertNull(damierTest.getPion(damierTest.getTuile(1, 6)));
        assertEquals(pionTestBlanc, damierTest.getPion(damierTest.getTuile(0, 5)));

        damierTest.initialiser();
        damierTest.deplacerPion(damierTest.getTuile(6, 5), damierTest.getTuile(7, 6));
        damierTest.deplacerPion(damierTest.getTuile(5, 4), damierTest.getTuile(4, 3));
        damierTest.deplacerPion(damierTest.getTuile(4, 3), damierTest.getTuile(6, 5));

    }

    @Test
    public void testNbMouvementMax() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        damierTest.getToutMouvement();

        assertEquals(9, damierTest.getListeMove().size());
        assertTrue(damierTest.getListeMove().get(0).contains(damierTest.getTuile(1, 6)));

        damierTest.initialiser();
        testViderBoard();
        damierTest.ajouterPion(damierTest.getTuile(5, 3), new Pion(Pion.Couleur.BLANC));
        damierTest.ajouterPion(damierTest.getTuile(6, 7), new Dame(Pion.Couleur.NOIR));
        damierTest.getToutMouvement();

        assertEquals(2, damierTest.getListeMove().size());
    }

    @Test
    public void testDamierClone() throws CloneNotSupportedException {
        Damier damier1 = Damier.getInstance();
        Damier damier2 = damier1.clone();
        damier2.supprimerPion(new Tuile(1, 0));
        assertNotEquals(damier2.estVideTuile(new Tuile(1, 0)), damier1.estVideTuile(new Tuile(1, 0)));
    }

    @Test
    public void testMouvementDame() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // Haut droit vers bas gauche
        damierTest.viderBoard();
        damierTest.ajouterPion(new Tuile(4, 5), new Dame(Pion.Couleur.BLANC));
        damierTest.ajouterPion(new Tuile(5, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(7, 2), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(1, 8), new Pion(Pion.Couleur.NOIR));
        damierTest.getToutMouvement();
        assertEquals(5, damierTest.getListeMove().size());

        // Haut Gauche vers bas droit
        damierTest.initialiser();
        damierTest.viderBoard();
        damierTest.ajouterPion(new Tuile(4, 5), new Dame(Pion.Couleur.BLANC));
        damierTest.ajouterPion(new Tuile(5, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(1, 2), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(7, 8), new Pion(Pion.Couleur.NOIR));
        damierTest.getToutMouvement();
        assertEquals(3, damierTest.getListeMove().size());

        // dame solo
        damierTest.initialiser();
        damierTest.viderBoard();
        damierTest.ajouterPion(new Tuile(4, 5), new Dame(Pion.Couleur.BLANC));
        damierTest.getToutMouvement();
        assertEquals(17, damierTest.getListeMove().size());
    }

    /**
     * Test verifiant qu'une tuile est vide.
     */
    @Test
    public void testEstVideTuile() {

        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        assertTrue(damierTest.estVideTuile(new Tuile(1, 4)));
        assertFalse(damierTest.estVideTuile(new Tuile(-1, -1)));
        assertFalse(damierTest.estVideTuile(new Tuile(1, 0)));
        assertFalse(damierTest.estVideTuile(new Tuile(15, 0)));
        assertFalse(damierTest.estVideTuile(new Tuile(9, -1)));
        assertFalse(damierTest.estVideTuile(new Tuile(9, 15)));
    }

    /**
     * Test verifiant que les cases disponibles pour un pion sont les bonnes.
     */
    @Test
    public void testObtenirCasesDisponibles() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // Test pion noir aucun déplacement
        assertEquals(0, damierTest.obtenirCasesDisponibles(damierTest.getTuile(1, 0)).size());

        // Test pion blanc aucun déplacement
        assertEquals(2, damierTest.obtenirCasesDisponibles(damierTest.getTuile(1, 6)).size());

    }

    /**
     * Test verifiant que si aucun pion d'une couleur est présent sur le jeu, on retourne vrai.
     */
    @Test
    public void testVerifierResteAucunPion() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        assertFalse(damierTest.verifierResteAucunPion(Pion.Couleur.NOIR));

        // Rangée une
        damierTest.viderBoard();

        assertTrue(damierTest.verifierResteAucunPion(Pion.Couleur.NOIR));
    }

    /**
     * Test verifiant que si aucun des pions d'une couleur peut bouger, on retourne vrai.
     */
    @Test
    public void testVerifierPeuxPasBouger() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        assertFalse(damierTest.verifierPeuxPasBouger(Pion.Couleur.BLANC));
        // Remplissage des cases du jeu par des pions noirs partout
        Pion pionNoir = new Pion(Pion.Couleur.NOIR);
        damierTest.ajouterPion(damierTest.getTuile(0, 5), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(1, 4), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(2, 5), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(3, 4), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(4, 5), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(5, 4), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(6, 5), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(7, 4), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(8, 5), pionNoir);
        damierTest.ajouterPion(damierTest.getTuile(9, 4), pionNoir);

        assertTrue(damierTest.verifierPeuxPasBouger(Pion.Couleur.BLANC));

    }


    /**
     * Verifie le deplacement d'un pion. Regarde si lors du deplacement le pion sur la tuile
     * désirée est celui qu'on à déplacé et que l'ancienne tuile ne contient aucun pion.
     */
    @Test
    public void testGererDeplacement() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();

        // debut de partie

        damierTest.viderBoard();

        // Test 5 possibilite
        damierTest.viderBoard();
        damierTest.gererDeplacement(damierTest.getTuile(4, 5), damierTest.getTuile(6, 7));
        damierTest.ajouterPion(new Tuile(4, 5), new Pion(Pion.Couleur.BLANC));
        damierTest.ajouterPion(new Tuile(3, 6), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(5, 6), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(5, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(7, 8), new Pion(Pion.Couleur.NOIR));

        damierTest.gererDeplacement(damierTest.getTuile(4, 5), damierTest.getTuile(6, 7));
        assertEquals(5, damierTest.getNbPions());
        // Test 4 possibilite
        damierTest.initialiser();
        damierTest.viderBoard();
        damierTest.ajouterPion(new Tuile(4, 5), new Pion(Pion.Couleur.BLANC));
        damierTest.ajouterPion(new Tuile(3, 6), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(5, 6), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(5, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.ajouterPion(new Tuile(3, 4), new Pion(Pion.Couleur.NOIR));
        damierTest.gererDeplacement(damierTest.getTuile(4, 5), damierTest.getTuile(6, 7));
        assertEquals(4, damierTest.getNbPions());
    }

    @Test
    public void testGererDeplacementPrecedant() {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        testViderBoard();

        Pion pionTestNoir = new Pion(Pion.Couleur.NOIR);

        damierTest.ajouterPion(damierTest.getTuile(1, 8), pionTestNoir);
        damierTest.deplacerPion(damierTest.getTuile(0, 9), damierTest.getTuile(1, 8));

        damierTest.gererDeplacementPrecedant(damierTest.getTuile(1, 8), damierTest.getTuile(0, 9));
    }

    /**
     * Test qui vérifie que la détermination de la notation manoury se fait correctement.
     * Vérifie aussi la fonctionnalité du getListeManoury.
     */
    @Test
    public void testDeterminerNotationManoury() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        // Déplacement pion blanc normal
        int nbPion = damierTest.getNbPions();
        damierTest.determinerNotationManoury(damierTest.getTuile(4, 5), damierTest.getTuile(3, 6), nbPion);
        damierTest.deplacerPion(damierTest.getTuile(3, 6), damierTest.getTuile(4, 5));

        damierTest.determinerNotationManoury(damierTest.getTuile(4, 5), damierTest.getTuile(3, 6), nbPion);

        assertEquals("32-28", damierTest.getlisteManouryMouvement().get(0));

        // Déplacement pion noir avec prise

        damierTest.initialiser();

        damierTest.ajouterPion(damierTest.getTuile(3, 4), new Pion(Pion.Couleur.BLANC));

        int nbPion1 = damierTest.getNbPions();
        damierTest.gererDeplacement(damierTest.getTuile(3, 6), damierTest.getTuile(4, 5));
        damierTest.determinerNotationManoury(damierTest.getTuile(4, 5), damierTest.getTuile(3, 6),
                nbPion1);

        int nbPion2 = damierTest.getNbPions();
        damierTest.gererDeplacement(damierTest.getTuile(4, 3), damierTest.getTuile(2, 5));
        damierTest.determinerNotationManoury(damierTest.getTuile(2, 5), damierTest.getTuile(4, 3),
                nbPion2);

        assertEquals("(18x27)", damierTest.getlisteManouryMouvement().get(2));
    }

    /**
     * Test qui vérifie que le get de la list de tuiles et de pions est de la bonne grandeur après.
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
     * qu'il change lorsque le joueur blanc joue un tour.
     */
    @Test
    public void testGetEstTourJoueurBlanc() throws CloneNotSupportedException {
        Damier damierTest = Damier.getInstance();
        damierTest.initialiser();
        assertTrue(damierTest.getestTourJoueurBlanc());
        damierTest.gererDeplacement(damierTest.getTuile(1, 6), damierTest.getTuile(2, 5));

        assertFalse(damierTest.getestTourJoueurBlanc());

    }

    /**
     * Test verifiant que la tuile qu'on veut est bien celle qui.
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
