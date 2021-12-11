package cstjean.mobile.checkers2021.code;

import static cstjean.mobile.checkers2021.code.DamierUtilitaire.transformerPositionEnManoury;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Cette classe est le damier qui contient tous les emplacement de pions et les pions.
 *
 * @author Sebastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class Damier implements Cloneable {

    /**
     * Instance du singleton.
     */
    private static Damier m_instance = null;
    /**
     * Liste contenant la notation manoury de chaque mouvement.
     */
    private final List<String> listeManouryMouvement = new LinkedList<>();

    /**
     * Enumération des direction possible par un pion lors d'un déplacement.
     */
    private enum Direction {
        /** Direction -1x -1y.*/
        HAUT_GAUCHE,
        /** Direction +1x -1y.*/
        HAUT_DROITE,
        /** Direction -1x +1y.*/
        BAS_GAUCHE,
        /** Direction +1x +1y.*/
        BAS_DROITE,
    }

    /**
     * Structure contenant les pions et leurs endroits sur le jeu.
     */
    private Map<Tuile, Pion> tuiles = new LinkedHashMap<>();

    /**
     * liste de movement possible sur le damier.
     */
    private LinkedList<LinkedList<Tuile>> listeMove = new LinkedList<>();

    /**
     * Détermine si c'est le tour du joueur blanc.
     */
    private boolean estTourJoueurBlanc = true;

    /**
     * Constructeur du damier.
     */
    private Damier() {
        initialiser();
    }

    /**
     * Permet d'accéder à l'instance du singleton et la crée
     * si elle n'existe pas.
     *
     * @return l'instance du singleton Jeu
     */
    public static Damier getInstance() {
        if (m_instance == null) {
            m_instance = new Damier();
        }
        return m_instance;
    }

    /**
     * Permet d'initialiser le Damier avec les 40 pions et les différents emplacements.
     */
    public void initialiser() {
        for (int y = 0; y < 10; y += 2) {
            for (int x = 0; x < 10; x++) {
                Tuile tuile = new Tuile(x, y);

                if (x % 2 == 0) {
                    ajouterPion(tuile, null);
                } else {
                    if (y < 4) {
                        ajouterPion(tuile, new Pion(Pion.Couleur.NOIR));
                    } else if (y > 5) {
                        ajouterPion(tuile, new Pion(Pion.Couleur.BLANC));
                    } else {
                        ajouterPion(tuile, null);
                    }
                }
            }
        }
        for (int y = 1; y < 10; y += 2) {
            for (int x = 0; x < 10; x++) {
                Tuile tuile = new Tuile(x, y);

                if (x % 2 == 0) {

                    if (y < 4) {
                        ajouterPion(tuile, new Pion(Pion.Couleur.NOIR));
                    } else if (y > 6) {
                        ajouterPion(tuile, new Pion(Pion.Couleur.BLANC));
                    } else {
                        ajouterPion(tuile, null);
                    }
                } else {
                    ajouterPion(tuile, null);
                }
            }
        }
        estTourJoueurBlanc = true;
        Set<Tuile> tuiles = this.tuiles.keySet();

        for (Tuile tuile : tuiles) {
            populerTuile(tuile);
        }
        /*
         *         ajouterPion(new Tuile(4,5), new Pion(Pion.Couleur.BLANC));
         *         ajouterPion(new Tuile(5,4), new Pion(Pion.Couleur.NOIR));
         *         ajouterPion(new Tuile(7,2),new Pion(Pion.Couleur.NOIR));
         *         ajouterPion(new Tuile(3,4), new Pion(Pion.Couleur.NOIR));
         *         ajouterPion(new Tuile(0,9), new Pion(Pion.Couleur.BLANC));
         */
    }

    /**
     * Permet d'enlever tout les pions sur le Damier.
     */
    public void viderBoard() {
        Set<Tuile> tuiles = this.tuiles.keySet();
        for (Tuile tuile : tuiles) {
            supprimerPion(tuile);
        }
    }

    /**
     * Permet de placer toutes les tuiles sur le Damier.
     */
    private void populerTuile(Tuile tuile) {
        try {
            tuile.setTuileBasGauche(getTuile(tuile.getX() - 1, tuile.getY() + 1));
        } catch (NullPointerException e) {
            // TODO
        }
        try {
            tuile.setTuileBasDroite(getTuile(tuile.getX() + 1, tuile.getY() + 1));
        } catch (NullPointerException e) {
            // TODO
        }
        try {
            tuile.setTuileHautGauche(getTuile(tuile.getX() - 1, tuile.getY() - 1));
        } catch (NullPointerException e) {
            // TODO
        }
        try {
            tuile.setTuileHautDroite(getTuile(tuile.getX() + 1, tuile.getY() - 1));
        } catch (NullPointerException e) {
            // TODO
        }
    }

    /**
     * Cette méthode permet d'ajouter un Pion dans le damier.
     *
     * @param pion pion que l'on désire placer
     * @param tuile ou l'on désire placer le pion
     */
    public void ajouterPion(Tuile tuile, Pion pion) {
        tuiles.put(tuile, pion);
    }

    /**
     * Permet d'avoir accès à la Map contenant les tuiles et les pions.
     *
     * @return la Map du jeu de dame
     */
    public Map<Tuile, Pion> getTuiles() {
        return tuiles;
    }

    /**
     * Permet d'obtenir la liste qui contient les mouvements en notation manoury.
     *
     * @return la liste des mouvements en notation manoury
     */
    public List<String> getlisteManouryMouvement() {
        return listeManouryMouvement;
    }

    /**
     * Permet de savoir si c'est le tour du joueur blanc.
     *
     * @return vrai si c'est le tour du joueur blanc faux si c'est le tour du joueur noir
     */
    public boolean getestTourJoueurBlanc() {
        return estTourJoueurBlanc;
    }

    /**
     * Transforme un pion en pion de type Dame.
     *
     * @param tuile tuile qui contient le pion qu'on veut transformer en dame
     */
    public void transformerPion(Tuile tuile) {
        if (tuiles.get(tuile) != null) {
            Dame dame = new Dame(Objects.requireNonNull(tuiles.get(tuile)).getCouleur());
            ajouterPion(tuile, dame);
        }

    }

    /**
     * Cette méthode permet d'avoir le nombre de pion Total dans le damier.
     *
     * @return le nb de pions
     */
    public int getNbPions() {

        int compteurPion = 0;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Tuile tuile = new Tuile(x, y);
                if (tuiles.get(tuile) != null) {
                    compteurPion++;
                }
            }
        }
        return compteurPion;
    }

    /**
     * Permet d'obtenir un pion selon son index sur le damier.
     *
     * @param tuile ou se trouve le ppion cherché
     * @return le pion selon l'index indiqué
     */
    public Pion getPion(Tuile tuile) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (x == tuile.getX() && y == tuile.getY()) {
                    return tuiles.get(tuile);
                }
            }
        }
        return null;
    }

    /**
     * Permet d'obtenir la couleur du pion qui est sur une tuile.
     *
     * @param tuile la tuile d'on on veut regarder la couleur du pion
     * @return la couleur du pion sur une tuile
     */
    public Pion.Couleur getCouleurPionSurTuile(Tuile tuile) {
        if (getPion(tuile) != null) {
            return getPion(tuile).getCouleur();
        }
        return null;
    }

    /**
     * Effectue le déplacement d'un pion : supprime le pion à l'ancien emplacement et ajoute celui
     * reçu en paramètre à la position désirée.
     *
     * @param ancienneTuile sa position initiale
     * @param nouvelleTuile la nouvelle position du pion
     */
    public void deplacerPion(Tuile nouvelleTuile, Tuile ancienneTuile) {
        try {
            Pion pion = getPion(new Tuile(ancienneTuile.getX(), ancienneTuile.getY()));
            tuiles.put(new Tuile(nouvelleTuile.getX(), nouvelleTuile.getY()), pion);
            tuiles.put(new Tuile(ancienneTuile.getX(), ancienneTuile.getY()), null);
            if (pion.estBlanc()) {
                if (nouvelleTuile.getY() == 0) {
                    transformerPion(nouvelleTuile);
                }
            } else {
                if (nouvelleTuile.getY() == 9) {
                    transformerPion(nouvelleTuile);
                }
            }
        } catch (NullPointerException ex) {
            // TODO
        }
    }

    /**
     * Permet d'obtenir une tuile sur le damier selon ses coordonées.
     *
     * @param x la coordonée en X de la tuile
     * @param y la coordonée en Y de la tuile
     * @return la tuile selon les coordonnées données en paramètre
     */
    public Tuile getTuile(int x, int y) {
        Set<Tuile> tuiles = this.tuiles.keySet();
        for (Tuile tuile : tuiles) {
            if (tuile.getX() == x && tuile.getY() == y) {
                return tuile;
            }
        }
        return null;
    }

    /**
     * Permet de vérifier si la tuile ne contient aucun pion.
     *
     * @param tuile la tuile qu'on veut vérifier si elle est vide
     * @return vrai si la tuile est vide faux si la tuile n'est pas vide
     */
    public boolean estVideTuile(Tuile tuile) {
        if (tuile.getX() < 0) {
            return false;
        }
        if (tuile.getY() < 0) {
            return false;
        }
        if (tuile.getX() > 9) {
            return false;
        }
        if (tuile.getY() > 9) {
            return false;
        }
        return (tuiles.get(tuile) == null);
    }

    /**
     * Permet d'obtenir les cases de déplacement disponible pour un pion.
     *
     * @param tuile la tuile d'on on veut obtenir les cases disponibles
     * @return une liste contenant les tuiles où le pion peut se déaplacer
     */
    public LinkedList<Tuile> obtenirCasesDisponibles(Tuile tuile) throws CloneNotSupportedException {
        getToutMouvement();
        Set<Tuile> liste = new HashSet<>();
        for (LinkedList<Tuile> list :
                listeMove) {
            if (list.get(0).getX() == tuile.getX() && list.get(0).getY() == tuile.getY()) {
                liste.add(list.get(1));
            }

        }
        return new LinkedList<>(liste);
    }

    /**
     * Permet de gérer le déplacement lorsque le joueur joue.
     *
     * @param tuile la tuile initiale
     * @param choix la tuile où on veut se déplacer
     */
    public void gererDeplacement(Tuile tuile, Tuile choix) throws CloneNotSupportedException {
        LinkedList<LinkedList<Tuile>> choixExact = new LinkedList<>();
        for (int i = 0; i != listeMove.size(); i++) {
            if (listeMove.get(i).get(0).getX() == tuile.getX() && listeMove.get(i).get(0).getY() == tuile.getY() &&
                    listeMove.get(i).get(1).getX() == choix.getX() && listeMove.get(i).get(1).getY() == choix.getY()) {
                choixExact.add(listeMove.get(i));
            }
        }
        listeMove = choixExact;
        trouverRelationSupprimer(tuile, choix);
        if (listeMove.size() != 0) {
            deplacerPion(choix, tuile);
        }
        if (moveFait.size() == 0) {
            moveFait.add(tuile);
        }
        moveFait.add(choix);
        boolean listePareil = true;
        for (LinkedList<Tuile> liste : listeMove) {
            int i = 0;
            listePareil = true;
            for (Tuile tuileListe : liste) {
                if (moveFait.size() != liste.size()) {
                    listePareil = false;
                    break;
                }
                if (tuileListe.getX() != moveFait.get(i).getX() || tuileListe.getY() != moveFait.get(i).getY()) {
                    listePareil = false;
                }
                if (listePareil && liste.getLast() == tuileListe) {
                    break;
                }
                i++;
            }
            if (listePareil) {
                break;
            }
        }
        if (listePareil) {
            estTourJoueurBlanc = !estTourJoueurBlanc;
        }
        moveFait = new LinkedList<>();

    }

    /**
     * Permet de Faire les deplacement arriere.
     * Cette methode est utiliser lorsque les mouvement sont sur
     *
     * @param tuileOriginal l'ancienne Tuile
     * @param tuileFinal la nouvelle tuile
     */
    public void gererDeplacementPrecedant(Tuile tuileOriginal, Tuile tuileFinal) {
        trouverRelationSupprimer(getTuile(tuileOriginal.getX(), tuileOriginal.getY()),
                getTuile(tuileFinal.getX(), tuileFinal.getY()));
        deplacerPion(tuileFinal, tuileOriginal);
    }

    /**
     * Liste de Tout les moves.
     */
    private LinkedList<Tuile> moveFait = new LinkedList<>();

    /**
     * Permet de gérer la suppression de pion entre une position de départ et sa position de fin.
     *
     * @param tuile la tuile initiale
     * @param choix la tuile où on veut se déplacer
     */
    public void trouverRelationSupprimer(Tuile tuile, Tuile choix) {
        boolean caseTrouver = false;
        LinkedList<Tuile> listeCaseSupprimer = new LinkedList<>();
        Tuile tuileTmp = tuile;

        while (true) {

            try {
                caseTrouver = tuileTmp.getTuileHautGauche() == choix;
                if (caseTrouver) {
                    break;
                }
                tuileTmp = tuileTmp.getTuileHautGauche();
                listeCaseSupprimer.add(tuileTmp);

            } catch (NullPointerException ex) {
                listeCaseSupprimer.clear();
                break;
            }
        }
        tuileTmp = tuile;
        while (!caseTrouver) {
            try {
                caseTrouver = tuileTmp.getTuileHautDroite() == choix;
                if (caseTrouver) {
                    break;
                }
                tuileTmp = tuileTmp.getTuileHautDroite();
                listeCaseSupprimer.add(tuileTmp);

            } catch (NullPointerException ex) {
                listeCaseSupprimer.clear();
                break;
            }
        }
        tuileTmp = tuile;
        while (!caseTrouver) {
            try {
                caseTrouver = tuileTmp.getTuileBasGauche() == choix;
                if (caseTrouver) {
                    break;
                }
                tuileTmp = tuileTmp.getTuileBasGauche();
                listeCaseSupprimer.add(tuileTmp);

            } catch (NullPointerException ex) {
                listeCaseSupprimer.clear();
                break;
            }
        }
        tuileTmp = tuile;
        while (!caseTrouver) {
            try {
                caseTrouver = tuileTmp.getTuileBasDroite() == choix;
                if (caseTrouver) {
                    break;
                }
                tuileTmp = tuileTmp.getTuileBasDroite();
                listeCaseSupprimer.add(tuileTmp);

            } catch (NullPointerException ex) {
                listeCaseSupprimer.clear();
                break;
            }
        }

        if (caseTrouver) {
            for (Tuile tuileInspecte : listeCaseSupprimer) {
                supprimerPion(tuileInspecte);

            }
        }

    }

    /**
     *Méthode permettant de trouver les tuiles que la dame
     *peut mager par rapport à sa liste de mouvvement sur le damier.
     *
     * @return un Set de tout les case Mangable.
     */
    public Set<Tuile> trouverTuileMangerDame() {
        Set<Tuile> list = new HashSet<>();
        boolean hautDroitUnlock = false;
        boolean hautGaucheUnlock = false;
        boolean basDroitUnlock = false;
        boolean basGaucheUnlock = false;
        boolean premierFois = true;
        LinkedList<Tuile> listePrecedante = new LinkedList<>();
        for (LinkedList<Tuile> liste : listeMove) {
            if (getPion(getTuile(liste.get(0).getX(), liste.get(0).getY())) instanceof Dame) {
                // BasGauche
                if (liste.get(0).getX() > liste.get(1).getX() && liste.get(0).getY() < liste.get(1).getY()) {
                    if (basGaucheUnlock) {
                        list.add(liste.get(1));
                    } else {
                        if (premierFois) {
                            if (liste.get(0).getTuileBasGauche().getX() != liste.get(1).getX() && liste.get(0).getTuileBasGauche().getY() != liste.get(1).getY()) {
                                basGaucheUnlock = true;
                                list.add(liste.get(1));

                            }
                            premierFois = false;

                        } else {
                            if (listePrecedante.get(1).getTuileBasGauche() != liste.get(1)) {
                                basGaucheUnlock = true;
                                list.add(liste.get(1));
                            }
                        }
                        listePrecedante = liste;

                    }
                }
            }

        }
        premierFois = true;
        listePrecedante = new LinkedList<>();
        for (LinkedList<Tuile> liste : listeMove) {
            // BasDroite
            if (getPion(getTuile(liste.get(0).getX(), liste.get(0).getY())) instanceof Dame) {
                if (liste.get(0).getX() < liste.get(1).getX() && liste.get(0).getY() < liste.get(1).getY()) {
                    if (basDroitUnlock) {
                        list.add(liste.get(1));
                    } else {
                        if (premierFois) {
                            if (liste.get(0).getTuileBasDroite().getX() != liste.get(1).getX() && liste.get(0).getTuileBasDroite().getY() != liste.get(1).getY()) {
                                basDroitUnlock = true;
                                list.add(liste.get(1));

                            }
                            premierFois = false;

                        } else {
                            if (listePrecedante.get(1).getTuileBasDroite() != liste.get(1)) {
                                basDroitUnlock = true;
                                list.add(liste.get(1));
                            }
                        }
                        listePrecedante = liste;
                    }

                }
            }
        }
        premierFois = true;
        listePrecedante = new LinkedList<>();
        for (LinkedList<Tuile> liste : listeMove) {
            // HautGauche
            if (getPion(getTuile(liste.get(0).getX(), liste.get(0).getY())) instanceof Dame) {
                if (liste.get(0).getX() > liste.get(1).getX() && liste.get(0).getY() > liste.get(1).getY()) {
                    if (hautGaucheUnlock) {
                        list.add(liste.get(1));
                    } else {
                        if (premierFois) {
                            if (liste.get(0).getTuileHautGauche().getX() != liste.get(1).getX() && liste.get(0).getTuileHautGauche().getY() != liste.get(1).getY()) {
                                hautGaucheUnlock = true;
                                list.add(liste.get(1));

                            }
                            premierFois = false;

                        } else {
                            if (listePrecedante.get(1).getTuileHautGauche() != liste.get(1)) {
                                hautGaucheUnlock = true;
                                list.add(liste.get(1));
                            }
                        }
                        listePrecedante = liste;
                    }
                }
            }

        }

        premierFois = true;
        listePrecedante = new LinkedList<>();
        for (LinkedList<Tuile> liste : listeMove) {
            // HautDroite
            if (getPion(getTuile(liste.get(0).getX(), liste.get(0).getY())) instanceof Dame) {
                if (liste.get(0).getX() < liste.get(1).getX() && liste.get(0).getY() > liste.get(1).getY()) {
                    if (hautDroitUnlock) {
                        list.add(liste.get(1));
                    } else {
                        if (premierFois) {
                            if (liste.get(0).getTuileHautDroite().getX() != liste.get(1).getX() && liste.get(0).getTuileHautDroite().getY() != liste.get(1).getY()) {
                                hautDroitUnlock = true;
                                list.add(liste.get(1));
                            }
                            premierFois = false;
                        } else {
                            if (listePrecedante.get(1).getTuileHautDroite() != liste.get(1)) {
                                hautDroitUnlock = true;
                                list.add(liste.get(1));
                            }
                        }
                        listePrecedante = liste;
                    }
                }
            }
        }
        return list;
    }

    /**
     * Permet de déterminer la notation manoury d'un déplacement et de l'ajouter dans la liste.
     *
     * @param tuileActuel   la tuile où le pion est après le déplacement
     * @param derniereTuile la tuile où il est rendu
     * @param nbPion noombre de pion sur le damier.
     */
    public void determinerNotationManoury(Tuile tuileActuel,
                                          Tuile derniereTuile, int nbPion) {
        String manoury;
        char signe = '-';

        if (nbPion != getNbPions()) {
            signe = 'x';
        }
        if (getCouleurPionSurTuile(tuileActuel) == Pion.Couleur.BLANC) {
            manoury = "" +
                    transformerPositionEnManoury(derniereTuile.getX(),
                            derniereTuile.getY()) +
                    signe +
                    transformerPositionEnManoury(tuileActuel.getX(),
                            tuileActuel.getY());
        } else {
            manoury = "(" +
                    transformerPositionEnManoury(derniereTuile.getX(),
                            derniereTuile.getY()) +
                    signe +
                    transformerPositionEnManoury(tuileActuel.getX(),
                            tuileActuel.getY()) + ")";
        }

        listeManouryMouvement.add(manoury);

    }

    /**
     * Effectue la supression d'un pion sur une certaine tuile.
     *
     * @param tuile la tuile où on veut supprimer le pion
     */
    public void supprimerPion(Tuile tuile) {
        tuiles.put(tuile, null);
    }

    /**
     * Permet de vérifier si il reste des pions d'une certaine couleur.
     *
     * @param couleur la couleur des pions qu'on veut regarder si il en reste
     * @return vrai si il ne reste aucun pion de la couleu faux si il reste encore des pions de la couleur
     */
    public boolean verifierResteAucunPion(Pion.Couleur couleur) {
        for (Tuile t : tuiles.keySet()
        ) {
            if (getCouleurPionSurTuile(t) == couleur) {
                //todo
            }

        }
        return false;

    }

    /**
     * Permet de vérifier tout les movement possible sur le damier.
     */
    public void getToutMouvement() throws CloneNotSupportedException {
        listeMove.clear();
        for (Tuile t : tuiles.keySet()) {
            if (t != null) {
                if (getPion(t) != null) {
                    if ((estTourJoueurBlanc && getCouleurPionSurTuile(t) == Pion.Couleur.BLANC) || (!estTourJoueurBlanc && getCouleurPionSurTuile(t) == Pion.Couleur.NOIR)) {
                        if (getPion(t) instanceof Dame) {
                            detecterNbDeplacementDame(getTuile(t.getX(), t.getY()), new LinkedList<>(), this, null, getCouleurPionSurTuile(getTuile(t.getX(),t.getY())));
                        } else {
                            detecterNbMovementPion(t, new LinkedList<>(), null);
                        }
                    }
                }
            }

        }
        filtrerListe();

    }

    /**
     * retourne le nombre de mouvement maximal que peut faire un pion sur une tuile.
     *
     * @param tuile
     * @return
     */
    public void detecterNbMovementPion(Tuile tuile, LinkedList<Tuile> mouvementFait, Direction direction) {

        // Haut gauche
        if (0 == mouvementFait.size()) {
            mouvementFait.add(tuile);
        }
        if (tuile != null) {
            boolean arreteRecherche = true;
            // Haut Gauche
            if (tuile.getTuileHautGauche() != null && tuile.getTuileHautGauche().getTuileHautGauche() != null) {
                if (!estVideTuile(tuile.getTuileHautGauche())) {
                    if (estVideTuile(tuile.getTuileHautGauche().getTuileHautGauche())) {
                        if (getCouleurPionSurTuile(mouvementFait.get(0)) != getCouleurPionSurTuile(tuile.getTuileHautGauche())) {
                            if (direction != Direction.BAS_DROITE) {
                                arreteRecherche = false;
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>(mouvementFait);
                                listtmp.add(tuile.getTuileHautGauche().getTuileHautGauche());
                                detecterNbMovementPion(tuile.getTuileHautGauche().getTuileHautGauche(), listtmp, Direction.HAUT_GAUCHE);
                            }

                        }
                    }
                }
            }

            // Haut Droite
            if (tuile.getTuileHautDroite() != null && tuile.getTuileHautDroite().getTuileHautDroite() != null) {
                if (!estVideTuile(tuile.getTuileHautDroite())) {
                    if (estVideTuile(tuile.getTuileHautDroite().getTuileHautDroite())) {
                        if (getCouleurPionSurTuile(mouvementFait.get(0)) != getCouleurPionSurTuile(tuile.getTuileHautDroite())) {
                            if (direction != Direction.BAS_GAUCHE) {
                                arreteRecherche = false;
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>(mouvementFait);
                                listtmp.add(tuile.getTuileHautDroite().getTuileHautDroite());
                                detecterNbMovementPion(tuile.getTuileHautDroite().getTuileHautDroite(), listtmp, Direction.HAUT_DROITE);
                            }
                        }
                    }
                }
            }

            // Bas Droite
            if (tuile.getTuileBasDroite() != null && tuile.getTuileBasDroite().getTuileBasDroite() != null) {
                if (!estVideTuile(tuile.getTuileBasDroite())) {
                    if (estVideTuile(tuile.getTuileBasDroite().getTuileBasDroite())) {
                        if (getCouleurPionSurTuile(mouvementFait.get(0)) != getCouleurPionSurTuile(tuile.getTuileBasDroite())) {
                            if (direction != Direction.HAUT_GAUCHE) {
                                arreteRecherche = false;
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>(mouvementFait);
                                listtmp.add(tuile.getTuileBasDroite().getTuileBasDroite());
                                detecterNbMovementPion(tuile.getTuileBasDroite().getTuileBasDroite(), listtmp, Direction.BAS_DROITE);
                            }

                        }
                    }
                }
            }

            // Bas gauche
            if (tuile.getTuileBasGauche() != null && tuile.getTuileBasGauche().getTuileBasGauche() != null) {
                if (!estVideTuile(tuile.getTuileBasGauche())) {
                    if (estVideTuile(tuile.getTuileBasGauche().getTuileBasGauche())) {
                        if (getCouleurPionSurTuile(mouvementFait.get(0)) != getCouleurPionSurTuile(tuile.getTuileBasGauche())) {
                            if (direction != Direction.HAUT_DROITE) {
                                arreteRecherche = false;
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>(mouvementFait);
                                listtmp.add(tuile.getTuileBasGauche().getTuileBasGauche());
                                detecterNbMovementPion(tuile.getTuileBasGauche().getTuileBasGauche(), listtmp, Direction.BAS_GAUCHE);
                            }

                        }
                    }
                }
            }
            if (arreteRecherche) {

                if (mouvementFait.size() == 1) {
                    // Haut Droite
                    try {
                        if (getCouleurPionSurTuile(tuile) == Pion.Couleur.BLANC) {
                            if (estVideTuile(tuile.getTuileHautDroite())) {
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>();
                                listtmp.add(tuile);
                                listtmp.add(tuile.getTuileHautDroite());
                                listeMove.add(listtmp);
                            }
                        }
                    } catch (NullPointerException e) {
                        //todo
                    }
                    // Haut Gauche
                    try {
                        if (getCouleurPionSurTuile(tuile) == Pion.Couleur.BLANC) {
                            if (estVideTuile(tuile.getTuileHautGauche())) {
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>();
                                listtmp.add(tuile);
                                listtmp.add(tuile.getTuileHautGauche());
                                listeMove.add(listtmp);
                            }
                        }
                    } catch (NullPointerException e) {
                        //todo
                    }
                    // Bas Droite
                    try {
                        if (getCouleurPionSurTuile(tuile) == Pion.Couleur.NOIR) {
                            if (estVideTuile(tuile.getTuileBasDroite())) {
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>();
                                listtmp.add(tuile);
                                listtmp.add(tuile.getTuileBasDroite());
                                listeMove.add(listtmp);
                            }
                        }
                    } catch (NullPointerException e) {
                        //todo
                    }

                    // Bas Gauche
                    try {
                        if (getCouleurPionSurTuile(tuile) == Pion.Couleur.NOIR) {
                            if (estVideTuile(tuile.getTuileBasGauche())) {
                                LinkedList<Tuile> listtmp = new LinkedList<Tuile>();
                                listtmp.add(tuile);
                                listtmp.add(tuile.getTuileBasGauche());
                                listeMove.add(listtmp);
                            }
                        }
                    } catch (NullPointerException e) {
                        //todo
                    }
                } else {
                    listeMove.add(mouvementFait);
                }
            }

        }

    }

    /** Méthode permettant d'aller trouver la couleur opposé du pion sur une tuilt donné
     *
     * @param tuile sur la qu'elle le pion sera évalué */
    private Pion.Couleur getCouleurOpposer(Tuile tuile) {
        Pion.Couleur couleur = getCouleurPionSurTuile(tuile);
        if (couleur == Pion.Couleur.NOIR) {
            return Pion.Couleur.BLANC;
        }
        if (couleur == Pion.Couleur.BLANC) {
            return Pion.Couleur.NOIR;
        }
        return null;
    }


    /** Méthode permettant de détecter tout les mouvement qu'une dame peut faire.
     *
     * @param tuile sur la qu'elle la dame sera évalué
     * @param mouvementFait liste des mouvements de la dame
     * @param damier sur lequel la dame est évaluer
     * @param direction précédant de la dame */

    public void detecterNbDeplacementDame(Tuile tuile, LinkedList<Tuile> mouvementFait, Damier damier, Direction direction, Pion.Couleur couleur) throws CloneNotSupportedException {
        Damier damierTmp = damier.clone();
        Tuile tuileTmp = damierTmp.getTuile(tuile.getX(), tuile.getY());
        Tuile tuileOriginale = tuileTmp;

        if (mouvementFait.size() == 0) {
            mouvementFait.add(tuileOriginale);
        }

        if (mouvementFait.size() == 3) {
            System.out.println(1);
        }
        boolean mange = false;
        // BAS GAUCHE
        while (true) {
            try {
                LinkedList<Tuile> listTmp = new LinkedList<>();
                listTmp.addAll(mouvementFait);
                if (damierTmp.estVideTuile(tuileTmp.getTuileBasGauche())) {
                    if (listTmp.size() > 1 ) {
                        if (direction == Direction.BAS_GAUCHE) {
                            listTmp.removeLast();
                            listTmp.add(tuileTmp.getTuileBasGauche());
                            listeMove.add(listTmp);
                        }
                    } else {
                        listTmp.add(tuileTmp.getTuileBasGauche());
                        listeMove.add(listTmp);
                    }

                } else if (damierTmp.getCouleurPionSurTuile(tuileTmp.getTuileBasGauche()) != couleur) {
                    if (damierTmp.estVideTuile(tuileTmp.getTuileBasGauche().getTuileBasGauche())) {
                        damierTmp.supprimerPion(tuileTmp.getTuileBasGauche());
                        damierTmp.deplacerPion(tuileOriginale, tuileTmp.getTuileBasGauche().getTuileBasGauche());
                        listTmp.add(tuileTmp.getTuileBasGauche().getTuileBasGauche());
                        listeMove.add(listTmp);
                        detecterNbDeplacementDame(tuileTmp.getTuileBasGauche().getTuileBasGauche(), listTmp, damierTmp, Direction.BAS_GAUCHE, couleur);
                        tuileTmp = tuileTmp.getTuileBasGauche();
                    } else {
                        break;
                    }
                }else {
                    break;
                }
                tuileTmp = tuileTmp.getTuileBasGauche();
            } catch (NullPointerException e) {
                break;
            }
        }

        damierTmp = damier.clone();
        tuileTmp = damierTmp.getTuile(tuile.getX(), tuile.getY());
        // BAS DROITE
        while (true) {
            try {
                LinkedList<Tuile> listTmp = new LinkedList<>();
                listTmp.addAll(mouvementFait);
                if (damierTmp.estVideTuile(tuileTmp.getTuileBasDroite())) {
                    if (listTmp.size() > 1 ) {
                        if (direction == Direction.BAS_DROITE) {
                            listTmp.removeLast();
                            listTmp.add(tuileTmp.getTuileBasDroite());
                            listeMove.add(listTmp);
                        }
                    } else {
                        listTmp.add(tuileTmp.getTuileBasDroite());
                        listeMove.add(listTmp);
                    }
                } else if (damierTmp.getCouleurPionSurTuile(tuileTmp.getTuileBasDroite()) != couleur) {
                    if (damierTmp.estVideTuile(tuileTmp.getTuileBasDroite().getTuileBasDroite())) {
                        damierTmp.supprimerPion(tuileTmp.getTuileBasDroite());
                        damierTmp.deplacerPion(tuileOriginale, tuileTmp.getTuileBasDroite().getTuileBasDroite());
                        listTmp.add(tuileTmp.getTuileBasDroite().getTuileBasDroite());
                        listeMove.add(listTmp);
                        detecterNbDeplacementDame(tuileTmp.getTuileBasDroite().getTuileBasDroite(), listTmp, damierTmp, Direction.BAS_DROITE, couleur);
                        tuileTmp = tuileTmp.getTuileBasDroite();
                    } else {
                        break;
                    }
                }else {
                    break;
                }
                tuileTmp = tuileTmp.getTuileBasDroite();
            } catch (NullPointerException e) {
                break;
            }

        }

        damierTmp = damier.clone();
        tuileTmp = damierTmp.getTuile(tuile.getX(), tuile.getY());

        /* HAUT GAUCHE*/
        while (true) {
            try {
                LinkedList<Tuile> listTmp = new LinkedList<>();
                listTmp.addAll(mouvementFait);
                if (damierTmp.estVideTuile(tuileTmp.getTuileHautGauche())) {
                    if (listTmp.size() > 1 ) {
                        if (direction == Direction.HAUT_GAUCHE) {
                            listTmp.removeLast();
                            listTmp.add(tuileTmp.getTuileHautGauche());
                            listeMove.add(listTmp);
                        }
                    } else {
                        listTmp.add(tuileTmp.getTuileHautGauche());
                        listeMove.add(listTmp);
                    }
                } else if (damierTmp.getCouleurPionSurTuile(tuileTmp.getTuileHautGauche()) != couleur) {
                    if (damierTmp.estVideTuile(tuileTmp.getTuileHautGauche().getTuileHautGauche())) {
                        damierTmp.supprimerPion(tuileTmp.getTuileHautGauche());
                        damierTmp.deplacerPion(tuileOriginale, tuileTmp.getTuileHautGauche().getTuileHautGauche());
                        listTmp.add(tuileTmp.getTuileHautGauche().getTuileHautGauche());
                        listeMove.add(listTmp);
                        detecterNbDeplacementDame(tuileTmp.getTuileHautGauche().getTuileHautGauche(), listTmp, damierTmp, Direction.HAUT_GAUCHE, couleur);
                        tuileTmp = tuileTmp.getTuileHautGauche();
                    } else {
                        break;
                    }
                }else {
                    break;
                }
                tuileTmp = tuileTmp.getTuileHautGauche();
            } catch (NullPointerException e) {
                break;
            }
        }

        damierTmp = damier.clone();
        tuileTmp = damierTmp.getTuile(tuile.getX(), tuile.getY());
        /* HAUT DROITE*/
        while (true) {
            try {
                LinkedList<Tuile> listTmp = new LinkedList<>();
                listTmp.addAll(mouvementFait);
                if (damierTmp.estVideTuile(tuileTmp.getTuileHautDroite())) {
                    if (listTmp.size() > 1 ) {
                        if (direction == Direction.HAUT_DROITE) {
                            listTmp.removeLast();
                            listTmp.add(tuileTmp.getTuileHautDroite());
                            listeMove.add(listTmp);
                        }
                    } else {
                        listTmp.add(tuileTmp.getTuileHautDroite());
                        listeMove.add(listTmp);
                    }
                } else if (damierTmp.getCouleurPionSurTuile(tuileTmp.getTuileHautDroite()) != couleur) {
                    if (damierTmp.estVideTuile(tuileTmp.getTuileHautDroite().getTuileHautDroite())) {
                        damierTmp.supprimerPion(tuileTmp.getTuileHautDroite());
                        damierTmp.deplacerPion(tuileOriginale, tuileTmp.getTuileHautDroite().getTuileHautDroite());
                        listTmp.add(tuileTmp.getTuileHautDroite().getTuileHautDroite());
                        listeMove.add(listTmp);
                        detecterNbDeplacementDame(tuileTmp.getTuileHautDroite().getTuileHautDroite(), listTmp, damierTmp, Direction.HAUT_DROITE, couleur);
                        tuileTmp = tuileTmp.getTuileHautGauche();
                    } else {
                        break;
                    }
                }else {
                    break;
                }
                tuileTmp = tuileTmp.getTuileHautDroite();
            } catch (NullPointerException e) {
                break;
            }
        }
    }


    /** Méthode permettant le filtrage de la liste de move */
    public void filtrerListe() {
        int nbMax = 0;
        LinkedList<LinkedList<Tuile>> listTmp = new LinkedList<>();
        boolean mange = false;
        Set<Tuile> listeMouvementMange = trouverTuileMangerDame();

        // Checker si il mange
        // si oui clear la liste et rentrer dans un cas ou on ajoute seulement s'il mange
        for (LinkedList<Tuile> liste : listeMove) {
            if (liste.size() > nbMax) {
                if (!(getPion(liste.get(0)) instanceof Dame)) {
                    listTmp.clear();
                    listTmp.add(liste);
                    nbMax = liste.size();
                    if ((liste.get(0).getX() + 2 == liste.get(1).getX()) || (liste.get(0).getX() - 2 == liste.get(1).getX())) {
                        mange = true;
                    }
                } else {
                    listTmp.clear();
                    listTmp.add(liste);
                    nbMax = liste.size();
                    if (listeMouvementMange.contains(liste.get(1))) {
                        mange = true;
                    }
                }

            } else if (liste.size() == nbMax) {

                if (getPion(liste.get(0)) instanceof Dame) {
                    if (mange) {
                        if (liste.size() == 2) {
                            if (listeMouvementMange.contains(liste.get(1))) {
                                listTmp.add(liste);
                            }
                        } else {
                            listTmp.add(liste);
                        }
                    } else {
                        if (listeMouvementMange.contains(liste.get(1))) {
                            listTmp.clear();
                            listTmp.add(liste);
                            mange = true;
                        } else {
                            listTmp.add(liste);
                        }

                    }
                } else {

                    if (mange) {

                        if ((liste.get(0).getX() + 2 == liste.get(1).getX()) || (liste.get(0).getX() - 2 == liste.get(1).getX())) {
                            listTmp.add(liste);
                        }
                    } else {

                        if ((liste.get(0).getX() + 2 == liste.get(1).getX()) || (liste.get(0).getX() - 2 == liste.get(1).getX())) {
                            mange = true;
                            listTmp.clear();
                            listTmp.add(liste);
                        } else {
                            listTmp.add(liste);
                        }
                    }
                }
            }
        }
        listeMove = listTmp;
    }

    /**
     * Permet de vérifier si les pions d'une couleur peuvent encore bouger.
     *
     * @param couleur la couleur des pions qu'on veut regarder si ils peuvent encore bouger
     * @return vrai si aucun pion de la couleur donnée en paramètre peuvent bougé
     */
    public boolean verifierPeuxPasBouger(Pion.Couleur couleur) throws CloneNotSupportedException {
        getToutMouvement();
        for (LinkedList<Tuile> liste:listeMove) {
            if (getCouleurPionSurTuile(liste.get(0)) == couleur) {
                return false;
            }
        }
        return true;
    }

    /** Méthode permettant le clonnage de la dame.*/

    @NonNull
    public Damier clone() throws CloneNotSupportedException {
        Damier clone = (Damier) super.clone();
        Map<Tuile, Pion> mapClone = new LinkedHashMap<>();
        clone.tuiles = tuiles;
        for (Map.Entry<Tuile, Pion> entry :
                clone.tuiles.entrySet()) {
            if (entry.getValue() != null) {
                mapClone.put(entry.getKey().clone(), entry.getValue().clone());
            } else {
                mapClone.put(entry.getKey().clone(), null);
            }

        }
        clone.tuiles = mapClone;
        for (Tuile tuile : clone.tuiles.keySet()) {
            clone.populerTuile(tuile);
        }
        return clone;
    }

}

