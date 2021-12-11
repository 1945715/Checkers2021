package cstjean.mobile.checkers2021.code;

import androidx.annotation.NonNull;

/**
 * Cette classe créer les pions.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class Pion implements Cloneable {
    /**
     * Enum des couleurs possibles pour le pion.
     */
    public enum Couleur {

        /**
         * Couleur noir.
         */
        NOIR,
        /**
         * Couleur blanche.
         */
        BLANC
    }

    /**
     * Couleur du pion.
     */
    private final Couleur couleur;

    /**
     * Constructeur du pion.
     *
     * @param couleur Couleur du pion.
     */
    public Pion(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Obtient la couleur du pion.
     *
     * @return la couleur du pion.
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Retourne vrai si la couleur du pion est noir.
     *
     * @return true si la couleur du pion est noir false si elle est blanche
     */
    public boolean estNoir() {
        return this.getCouleur().equals(Couleur.NOIR);
    }

    /**
     * Retourne vrai si la couleur du pion est blanc.
     *
     * @return true si la couleur du pion est blanche false si elle est noir
     */
    public boolean estBlanc() {
        return this.getCouleur().equals(Couleur.BLANC);
    }

    /**
     * Méthode permettant le clonage d'un pion.
     *
     * @return clone du pion
     * @throws CloneNotSupportedException clone n'est pas supporté.
     */
    @NonNull
    public Pion clone() throws CloneNotSupportedException {
        return (Pion) super.clone();
    }
}
