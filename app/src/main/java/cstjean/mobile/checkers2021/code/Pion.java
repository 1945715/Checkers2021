package cstjean.mobile.checkers2021.code;

/**
 * Cette classe créer les pions
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class Pion implements Cloneable{

    /**
     * Enum des couleurs possibles pour le pion
     */
    public enum Couleur {
        NOIR,
        BLANC
    }

    /**
     * Couleur du pion
     */
    private final Couleur m_couleur;

    /**
     * Constructeur du pion
     *
     * @param p_couleur Couleur du pion
     */
    public Pion(Couleur p_couleur) {
        m_couleur = p_couleur;
    }

    /**
     * Obtient la couleur du pion
     *
     * @return la couleur du pion
     */
    public Couleur getCouleur() {
        return m_couleur;
    }

    /**
     * Retourne vrai si la couleur du pion est noir
     *
     * @return true si la couleur du pion est noir
     * false si elle est blanche
     */
    public boolean estNoir() {
        return this.getCouleur().equals(Couleur.NOIR);
    }

    /**
     * Retourne vrai si la couleur du pion est blanc
     *
     * @return true si la couleur du pion est blanche
     * false si elle est noir
     */
    public boolean estBlanc() {
        return this.getCouleur().equals(Couleur.BLANC);
    }

    public Pion clone() throws CloneNotSupportedException
    {
        return (Pion) super.clone();
    }

}
