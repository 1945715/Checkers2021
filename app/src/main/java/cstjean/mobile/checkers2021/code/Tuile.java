package cstjean.mobile.checkers2021.code;

import androidx.annotation.NonNull;
import java.util.Objects;

/**
 * Objet tuile qui contient ses coordonnées en X et en Y.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class Tuile implements Cloneable {

    /**
     * Coordonées en X de la tuile sur le damier.
     */
    private int xcoord;

    /**
     * Coordonées en Y de la tuile sur le damier.
     */
    private int ycoord;

    /**
     * permet d'aller cherche la tuille Haut gauche.
     *
     * @return tuile haut gauche de la tuile actuelle.
     */
    public Tuile getTuileHautGauche() {
        return tuileHautGauche;
    }

    /**
     * permet de changer la tuille Haut gauche.
     *
     * @param tuileHautGauche tuile haut gauche
     */
    public void setTuileHautGauche(Tuile tuileHautGauche) {
        this.tuileHautGauche = tuileHautGauche;
    }

    /**
     * permet d'aller cherche la tuille Haut gauche.
     *
     * @return tuile haut droite de la tuile actuelle.
     */
    public Tuile getTuileHautDroite() {
        return tuileHautDroite;
    }

    /**
     * permet de changer la tuille Haut gauche.
     *
     * @param tuileHautDroite tuile haut gauche
     */
    public void setTuileHautDroite(Tuile tuileHautDroite) {
        this.tuileHautDroite = tuileHautDroite;
    }

    /**
     * permet d'aller cherche la tuille Haut gauche.
     *
     * @return tuile bas droite de la tuile actuelle.
     */
    public Tuile getTuileBasDroite() {
        return tuileBasDroite;
    }

    /**
     * permet de changer la tuille bas droite.
     *
     * @param tuileBasDroite tuile bas droite
     */
    public void setTuileBasDroite(Tuile tuileBasDroite) {
        this.tuileBasDroite = tuileBasDroite;
    }

    /**
     * permet d'aller cherche la tuile bas gauche.
     *
     * @return tuile abs gauche de la tuile actuelle.
     */
    public Tuile getTuileBasGauche() {
        return tuileBasGauche;
    }

    /**
     * permet de changer la tuille Bas gauche.
     *
     * @param tuileBasGauche tuile bas gauche
     */
    public void setTuileBasGauche(Tuile tuileBasGauche) {
        this.tuileBasGauche = tuileBasGauche;
    }

    /**
     * Tuile En haut à gauche de la tuile actuelle.
     */
    private Tuile tuileHautGauche = null;

    /**
     * Tuile En haut à droite de la tuile actuelle.
     */
    private Tuile tuileHautDroite = null;

    /**
     * Tuile En bas à droite de la tuile actuelle.
     */
    private Tuile tuileBasDroite = null;

    /**
     * Tuile En bas à gauche de la tuile actuelle.
     */
    private Tuile tuileBasGauche = null;

    /**
     * Constructeur de l'objet Tuile.
     *
     * @param x coordonées en X de la tuile sur le damier
     * @param y coordonées en Y de la tuile sur le damier
     */
    public Tuile(int x, int y) {
        this.xcoord = x;
        this.ycoord = y;
    }

    /**
     * Méthode permettant le clonage de la tuile.
     *
     * @return clone de la tuile
     * @throws CloneNotSupportedException un clone n'est pas supporté
     */
    @NonNull
    public Tuile clone() throws CloneNotSupportedException {
        Tuile clone = (Tuile) super.clone();
        clone.setTuileBasDroite(null);
        clone.setTuileBasGauche(null);
        clone.setTuileHautDroite(null);
        clone.setTuileHautGauche(null);
        return clone;
    }

    /**
     * Permet d'obtenir la coordonée en X de la tuile sur le damier.
     *
     * @return la coordonée en X de la tuile sur le damier.
     */
    public int getX() {
        return xcoord;
    }

    /**
     * Permet d'obtenir la coordonée en Y de la tuile sur le damier.
     *
     * @return la coordonée en Y de la tuile sur le damier.
     */
    public int getYcoord() {
        return ycoord;
    }

    /**
     * Permet d'établir la coordonée en X de la tuile sur le damier.
     *
     * @param x coordonée x sur le damier
     */
    public void setX(int x) {
        this.xcoord = x;
    }

    /**
     * Permet d'établir la coordonée en Y de la tuile sur le damier.
     *
     * @param ycoord coordonée x sur le damier
     */
    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    /**
     * Permet de redéfinir la méthode equals afin de comparer les valeurs de l'objet et non sa.
     * valeur en référence
     *
     * @param o l'objet
     * @return vrai si l'objet est pareils sinon faux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tuile tuile = (Tuile) o;
        return xcoord == tuile.xcoord &&
                ycoord == tuile.ycoord;
    }

    /**
     * Permet d'obtenir le hashcode de l'objet.
     *
     * @return le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(xcoord, ycoord);
    }
}
