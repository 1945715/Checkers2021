package cstjean.mobile.checkers2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import cstjean.mobile.checkers2021.code.Tuile;
import org.junit.Test;

/**
 * Série de test sur les tuiles et ses propriétées.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class TestTuile {

    /**
     * Test la création d'un objet tuile.
     */
    @Test
    public void testCreer() {

        int xtuile = 5;
        int ytuile = 2;
        Tuile tuile1 = new Tuile(xtuile, ytuile);
        assertEquals(xtuile, tuile1.getX());
        assertEquals(ytuile, tuile1.getYcoord());

        // Test setters
        tuile1.setX(2);
        tuile1.setYcoord(5);

        assertEquals(tuile1.getX(), 2);
        assertEquals(tuile1.getYcoord(), 5);

        int xtuile2 = 2;
        int ytuile2 = 5;
        Tuile tuile2 = new Tuile(xtuile2, ytuile2);
        assertEquals(xtuile2, tuile2.getX());
        assertEquals(ytuile2, tuile2.getYcoord());

        // Test setters
        tuile2.setX(5);
        tuile2.setYcoord(2);

        assertEquals(tuile2.getX(), 5);
        assertEquals(tuile2.getYcoord(), 2);

    }

    /**
     * Test d'égalité.
     */
    @Test
    public void testEgalite() {
        Tuile tuileA = new Tuile(5, 7);
        Tuile tuileB = new Tuile(5, 7);
        assertEquals(tuileA, tuileB);
        Tuile tuileC = new Tuile(5, 3);
        assertNotEquals(tuileA, tuileC);
        // Réflexivité
        assertEquals(tuileA, tuileA);
        // Symétrie
        assertEquals(tuileB, tuileA);
        // Transitivité
        Tuile coursD = new Tuile(5, 7);
        assertEquals(tuileB, coursD);
        assertEquals(tuileA, coursD);
        // Constance
        assertEquals(tuileA, tuileB);
        // Comparaison à null
        // LINT : jUnit n'appelle pas le equal si on envoit null donc on veut comparer directement
        // On veut vraiment tester le null ici...
        assertNotNull(tuileA);
        // Validation
        assertNotEquals("MATHS334", tuileA);
    }

    /**
     * Test de HashCode.
     */
    @Test
    public void testHashCode() {
        Tuile tuileA = new Tuile(5, 8);
        Tuile tuileB = new Tuile(5, 8);
        assertEquals(tuileA.hashCode(), tuileB.hashCode());
        assertEquals(tuileA.hashCode(), tuileA.hashCode());
    }
}

