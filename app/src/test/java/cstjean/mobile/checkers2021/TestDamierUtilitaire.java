package cstjean.mobile.checkers2021;

import static org.junit.Assert.assertEquals;

import cstjean.mobile.checkers2021.code.DamierUtilitaire;
import org.junit.Test;

/**
 * Série de test sur la classe utilitaire du damier.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class TestDamierUtilitaire {

    /**
     * Transforme des coordonées X et Y en nombre.
     * Exemple: 5,7 -> 57
     */
    @Test
    public void testTransformerCoordonneesEnNombre() {
        int x = 5;
        int y = 5;

        int nombre = DamierUtilitaire.transformerCoordonneesEnNombre(x, y);

        assertEquals(55, nombre);

    }

    /**
     * Transforme des coordonées X et Y en notation manoury.
     * Exemple: 5, 8 -> 43
     */
    @Test
    public void testTransformerPositionEnManoury() {

        // Rangée pair
        int x = 5;
        int y = 8;

        int manoury = DamierUtilitaire.transformerPositionEnManoury(x, y);

        assertEquals(43, manoury);

        // Rangée impair

        int x1 = 0;
        int y1 = 1;

        int manoury2 = DamierUtilitaire.transformerPositionEnManoury(x1, y1);

        assertEquals(6, manoury2);

        // Cas impossible

        int x2 = -1;
        int y2 = -1;

        int manoury3 = DamierUtilitaire.transformerPositionEnManoury(x2, y2);

        assertEquals(1, manoury3);

    }

    /**
     * Test de la transformation d'une notation manoury en position X et Y.
     * Exemple: 22 -> 3,4
     */
    @Test
    public void testTransformerManouryEnPosition() {
        String manoury = "22";
        int[] arrayTestManuel = new int[2];

        arrayTestManuel[0] = 3;
        arrayTestManuel[1] = 4;
        int[] arrayTestMethode = DamierUtilitaire.transformerManouryEnPosition(manoury);
        assertEquals(arrayTestManuel[0], arrayTestMethode[0]);
        assertEquals(arrayTestManuel[1], arrayTestMethode[1]);

        // Cas rangée impair

        int[] arrayTestManuel1 = new int[2];

        arrayTestManuel1[0] = 2;
        arrayTestManuel1[1] = 3;
        int[] arrayTestMethode1 =
                DamierUtilitaire.transformerManouryEnPosition("17");
        assertEquals(arrayTestManuel1[0], arrayTestMethode1[0]);
        assertEquals(arrayTestManuel1[1], arrayTestMethode1[1]);

        // Cas manoury qui sont des multiples de 5

        int[] arrayTestManuel2 = new int[2];

        arrayTestManuel2[0] = 8;
        arrayTestManuel2[1] = 5;
        int[] arrayTestMethode2 =
                DamierUtilitaire.transformerManouryEnPosition("30");
        assertEquals(arrayTestManuel2[0], arrayTestMethode2[0]);
        assertEquals(arrayTestManuel2[1], arrayTestMethode2[1]);
    }

    /**
     * Transforme un nombre en coordonées X et Y.
     * Exemple: 57 -> 7,5
     */
    @Test
    public void testTransformerNombreEnCoordonnes() {
        int nombre = 57;
        int[] arrayTestManuel = new int[2];
        arrayTestManuel[0] = 7;
        arrayTestManuel[1] = 5;

        int[] arrayTest = DamierUtilitaire.transformerNombreEnCoordonnes(nombre);

        assertEquals(arrayTestManuel[0], arrayTest[0]);
        assertEquals(arrayTestManuel[1], arrayTest[1]);

    }
}
