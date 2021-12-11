package cstjean.mobile.checkers2021;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cstjean.mobile.checkers2021.code.Pion;
import org.junit.Test;

/**
 * Série de test sur les pions et ses propriétées.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 */
public class TestPion {

    /**
     * Creer un pion et verifie sa couleur.
     */
    @Test
    public void testCreer() {

        Pion pion1 = new Pion(Pion.Couleur.BLANC);
        Pion pion2 = new Pion(Pion.Couleur.NOIR);

        assertEquals(Pion.Couleur.BLANC, pion1.getCouleur());
        assertEquals(Pion.Couleur.NOIR, pion2.getCouleur());
    }

    /**
     * Verifie qu'un pion est blanc.
     */
    @Test
    public void testPionEstBlanc() {

        Pion pion1 = new Pion(Pion.Couleur.BLANC);
        Pion pion2 = new Pion(Pion.Couleur.NOIR);
        assertTrue(pion1.estBlanc());
        assertFalse(pion2.estBlanc());
    }

    /**
     * Verifie qu'un pion est noir.
     */
    @Test
    public void testPionEstNoir() {

        Pion pion1 = new Pion(Pion.Couleur.NOIR);
        Pion pion2 = new Pion(Pion.Couleur.BLANC);
        assertTrue(pion1.estNoir());
        assertFalse(pion2.estNoir());
    }
}
