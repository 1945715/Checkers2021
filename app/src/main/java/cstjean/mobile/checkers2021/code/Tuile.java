package cstjean.mobile.checkers2021.code;

import java.util.Objects;

/**
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 * <p>
 * Objet tuile qui contient ses coordonnées en X et en Y
 */
public class Tuile implements Cloneable {

    /**
     * Coordonées en X de la tuile sur le damier
     */
    private int x;

    /**
     * Coordonées en Y de la tuile sur le damier
     */
    private int y;

    public Tuile getTuileHautGauche() {
        return tuileHautGauche;
    }

    public void setTuileHautGauche(Tuile tuileHautGauche) {
        this.tuileHautGauche = tuileHautGauche;
    }

    public Tuile getTuileHautDroite() {
        return tuileHautDroite;
    }

    public void setTuileHautDroite(Tuile tuileHautDroite) {
        this.tuileHautDroite = tuileHautDroite;
    }

    public Tuile getTuileBasDroite() {
        return tuileBasDroite;
    }

    public void setTuileBasDroite(Tuile tuileBasDroite) {
        this.tuileBasDroite = tuileBasDroite;
    }

    public Tuile getTuileBasGauche() {
        return tuileBasGauche;
    }

    public void setTuileBasGauche(Tuile tuileBasGauche) {
        this.tuileBasGauche = tuileBasGauche;
    }

    /**
     * Tuile En haut à gauche de la tuile actuelle
     */
    private Tuile tuileHautGauche = null;

    /**
     * Tuile En haut à droite de la tuile actuelle
     */
    private Tuile tuileHautDroite = null;

    /**
     * Tuile En bas à droite de la tuile actuelle
     */
    private Tuile tuileBasDroite = null;

    /**
     * Tuile En bas à gauche de la tuile actuelle
     */
    private Tuile tuileBasGauche = null;



    /**
     * Constructeur de l'objet Tuile
     *
     * @param p_x coordonées en X de la tuile sur le damier
     * @param p_y coordonées en Y de la tuile sur le damier
     */
    public Tuile(int p_x, int p_y) {
        x = p_x;
        y = p_y;
    }

    public Tuile clone() throws CloneNotSupportedException{
        Tuile clone = (Tuile) super.clone();
        clone.setTuileBasDroite(null);
        clone.setTuileBasGauche(null);
        clone.setTuileHautDroite(null);
        clone.setTuileHautGauche(null);
        return clone;
    }


    /**
     * Permet d'obtenir la coordonée en X de la tuile sur le damier
     *
     * @return la coordonée en X de la tuile sur le damier
     */
    public int getX() {
        return x;
    }

    /**
     * Permet d'obtenir la coordonée en Y de la tuile sur le damier
     *
     * @return la coordonée en Y de la tuile sur le damier
     */
    public int getY() {
        return y;
    }

    /**
     * Permet d'établir la coordonée en X de la tuile sur le damier
     */
    public void setX(int p_x) {
        x = p_x;
    }

    /**
     * Permet d'établir la coordonée en Y de la tuile sur le damier
     */
    public void setY(int p_y) {
        y = p_y;
    }

    /**
     * Permet de redéfinir la méthode equals afin de comparer les valeurs de l'objet et non sa
     * valeur en référence
     *
     * @param p_o l'objet
     * @return vrai si l'objet est pareils sinon faux
     */
    @Override
    public boolean equals(Object p_o) {
        if (this == p_o) return true;
        if (p_o == null || getClass() != p_o.getClass()) return false;
        Tuile tuile = (Tuile) p_o;
        return x == tuile.x &&
                y == tuile.y;
    }

    /**
     * Permet d'obtenir le hashcode de l'objet
     *
     * @return le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
