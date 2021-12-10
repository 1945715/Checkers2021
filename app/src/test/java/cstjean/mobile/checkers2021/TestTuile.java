package cstjean.mobile.checkers2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import cstjean.mobile.checkers2021.code.Tuile;

public class TestTuile {
    /**
     * Test la création d'un objet tuile
     */
    @Test
    public void testCreer() {

        int xTuile = 5;
        int yTuile = 2;
        Tuile tuile1 = new Tuile(xTuile, yTuile);
        assertEquals(xTuile, tuile1.getX());
        assertEquals(yTuile, tuile1.getY());

        // Test setters
        tuile1.setX(2);
        tuile1.setY(5);

        assertEquals(tuile1.getX(), 2);
        assertEquals(tuile1.getY(), 5);

        int xTuile2 = 2;
        int yTuile2 = 5;
        Tuile tuile2 = new Tuile(xTuile2, yTuile2);
        assertEquals(xTuile2, tuile2.getX());
        assertEquals(yTuile2, tuile2.getY());

        // Test setters
        tuile2.setX(5);
        tuile2.setY(2);

        assertEquals(tuile2.getX(), 5);
        assertEquals(tuile2.getY(), 2);

    }

    /**
     * Test d'égalité
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
        assertFalse(tuileA.equals(null));
        // Validation
        assertNotEquals("MATHS334", tuileA);
    }

    /**
     * Test de HashCode
     */
    @Test
    public void testHashCode() {
        Tuile tuileA = new Tuile(5, 8);
        Tuile tuileB = new Tuile(5, 8);
        assertEquals(tuileA.hashCode(), tuileB.hashCode());
        assertEquals(tuileA.hashCode(), tuileA.hashCode());
    }
}

