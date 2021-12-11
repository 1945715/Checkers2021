package cstjean.mobile.checkers2021.code;

/**
 * Classe utilitaire qui effectue différents calcul pour le damier.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class DamierUtilitaire {

    /**
     * Permet de transformer des une paire de coordonnées X et Y en un nombre de deux chiffres.
     *
     * @param x la coordonées en X
     * @param y la coordonées en Y
     * @return un nombre selon une paire de coordonnées X et Y (EXEMPLE : 5,7 -> 75)
     */
    public static int transformerCoordonneesEnNombre(int x, int y) {
        return y * 10 + x;
    }

    /**
     * Permet de transformer des coordonnées X et Y en notation manoury.
     *
     * @param x le x de la position
     * @param y le y de la position
     * @return un nombre en notation manoury
     */
    public static int transformerPositionEnManoury(int x, int y) {
        if (x == -1) {
            x = 0;
        }
        if (y == -1) {
            y = 0;
        }
        int dizaine;
        int unite;
        if (y % 2 == 0) {
            dizaine = (y / 2) * 10;
            unite = x / 2;
            return (dizaine + unite + 1);
        } else {
            dizaine = y / 2;
            dizaine = dizaine * 10;
            unite = 6 + x / 2;
            return (dizaine + unite);
        }
    }

    /**
     * Permet de transformer la notation manoury en coordonnées X et Y.
     *
     * @param nombreManoury la notation manoury à transformer
     * @return un array contenant le X à l'index 0 et le Y à l'index 1
     */
    public static int[] transformerManouryEnPosition(String nombreManoury) {
        int manouryInt = Integer.parseInt(nombreManoury);
        int[] positionXetY = new int[2];
        int x;
        int y;
        if (manouryInt % 5 != 0) {
            x = (manouryInt % 5) * 2 - 1;
            y = manouryInt / 5;
        } else {
            x = 9;
            y = manouryInt / 5 - 1;
        }

        if (y % 2 != 0) {
            x -= 1;
        }

        positionXetY[0] = x;
        positionXetY[1] = y;

        return positionXetY;

    }

    /**
     * Permet de transformer un nombre de deux chiffres en une paire de coordonnées X et Y.
     *
     * @param nombre le nombre qu'on veut transformer en coordonnées
     * @return un array de int avec la coordonée X à l'index 0 et la coordonée en Y à l'index 1
     */
    public static int[] transformerNombreEnCoordonnes(int nombre) {
        int x = nombre % 10;
        int y = (nombre - x) / 10;
        int[] listeInt = new int[2];
        listeInt[0] = x;
        listeInt[1] = y;
        return listeInt;
    }
}

