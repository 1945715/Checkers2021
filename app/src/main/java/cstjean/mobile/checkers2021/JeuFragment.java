package cstjean.mobile.checkers2021;

import static cstjean.mobile.checkers2021.code.DamierUtilitaire.transformerCoordonneesEnNombre;
import static cstjean.mobile.checkers2021.code.DamierUtilitaire.transformerManouryEnPosition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cstjean.mobile.checkers2021.code.Dame;
import cstjean.mobile.checkers2021.code.Damier;
import cstjean.mobile.checkers2021.code.Pion;
import cstjean.mobile.checkers2021.code.Tuile;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fragment pour le jeu.
 * LINT : On ne peut pas mettre le fragment package-private car le.
 * Fragment Manager ne pourra pas le charger.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class JeuFragment extends Fragment {

    /**
     * Le RecyclerView des notation manoury.
     */
    private RecyclerView recyclerViewJeu;

    /**
     * Clé permettant de récupérer le nom du joueur blanc envoyé par le fragment NomJoueur.
     */
    private static final String EXTRA_NOMJOUEURBLANC = "cstjean.mobile.nomJoueurBlanc";

    /**
     * Clé permettant de récupérer le nom du joueur noir envoyé par le fragment NomJoueur.
     */
    private static final String EXTRA_NOMJOUEURNOIR = "cstjean.mobile.nomJoueurNoir";

    /**
     * Layout principal contenant le jeu de dame dans le fragment.
     */
    GridLayout layoutJeu;

    /**
     * Le damier contenant les tuiles et les pions avec ses méthodes.
     */
    private final Damier damier = Damier.getInstance();

    /**
     * Liste contenant tout les images boutons présent sur le damier.
     */
    private final ImageButton[] listeButton = new ImageButton[100];

    /**
     * Liste contenant la notation manoury de chaque mouvement.
     */
    private final List<String> listeManouryMouvement = damier.getlisteManouryMouvement();

    /**
     * Text view qui indique à quel joueur c'est le tour.
     */
    private TextView texteTourNom;

    /**
     * Tuile qui prend la valeur de la tuile qu'on a appuiyé précédemment.
     */
    private Tuile derniereTuile = null;

    /**
     * Instanciation de l'interface.
     *
     * @param inflater           Pour instancier l'interface
     * @param container          Le parent qui contiendra notre interface
     * @param savedInstanceState Les données conservées au changement d'état
     * @return La vue instanciée
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_jeu, container, false);

        // Initialisation des boutons et textView
        Button boutonPrecedant = v.findViewById(R.id.bouton_precedant);
        final Button boutonRecommencer = v.findViewById(R.id.bouton_recommencer);

        texteTourNom = v.findViewById(R.id.txt_tour);

        // Récupération des noms des joueurs

        assert getArguments() != null;
        final String nomJoueurBlanc = getArguments().getString(EXTRA_NOMJOUEURBLANC, "");
        final String nomJoueurNoir = getArguments().getString(EXTRA_NOMJOUEURNOIR, "");

        // OnClick du bouton précédant
        boutonPrecedant.setOnClickListener(v13 -> {
            if (listeManouryMouvement.size() >= 1) {
                listeManouryMouvement.remove(listeManouryMouvement.size() - 1);
                updateListeNotationManoury();
                derniereTuile = null;
                if (listeManouryMouvement.size() == 0) {
                    reinitialiserJeu(nomJoueurBlanc, nomJoueurNoir);
                } else {

                    damier.initialiser();
                    Pattern regex = Pattern.compile("\\d+");

                    for (String manoury : listeManouryMouvement) {
                        Matcher matcher = regex.matcher(manoury);

                        boolean premierNombreTrouver = matcher.find();

                        String nombre1 = "";
                        String nombre2 = "";
                        if (premierNombreTrouver) {
                            nombre1 = manoury.substring(matcher.start(), matcher.end());
                        }

                        boolean deuxiemeNombreTrouver = matcher.find();

                        if (deuxiemeNombreTrouver) {
                            nombre2 = manoury.substring(matcher.start(), matcher.end());
                        }
                        int[] coordoneesNombre1 = transformerManouryEnPosition(nombre1);
                        int[] coordoneesNombre2 = transformerManouryEnPosition(nombre2);

                        Tuile tuile1 = new Tuile(coordoneesNombre1[0], coordoneesNombre1[1]);
                        Tuile tuile2 = new Tuile(coordoneesNombre2[0], coordoneesNombre2[1]);
                        damier.gererDeplacementPrecedant(tuile1, tuile2);
                    }

                    rafraichirJeu(nomJoueurBlanc, nomJoueurNoir);
                }
            }

        });

        // Onclick d'une tuile
        final View.OnClickListener clickTuile = v12 -> {
            rafraichirJeu(nomJoueurBlanc, nomJoueurNoir);

            // Détermine les coordonnées du bouton en X et Y
            String indexBoutonString = v12.getTag().toString();
            int indexBouton = Integer.parseInt(indexBoutonString);
            int xbouton = indexBouton % 10;
            int ybouton = (indexBouton - xbouton) / 10;

            boolean abouger = false;
            Tuile tuileActuel = damier.getTuile(xbouton, ybouton);

            // premier click
            if (tuileActuel != null && !damier.estVideTuile(new Tuile(xbouton, ybouton))) {
                List<Tuile> tuileDispo = null;
                try {
                    tuileDispo = damier.obtenirCasesDisponibles(tuileActuel);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                if (!damier.estVideTuile(tuileActuel)) {
                    if (damier.getPion(tuileActuel).getCouleur() == Pion.Couleur.BLANC &&
                            damier.getestTourJoueurBlanc()) {
                        for (Tuile tuile : Objects.requireNonNull(tuileDispo)) {
                            listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                                    tuile.getYcoord())].setImageResource(R.drawable.ic_pion_possible);
                        }
                    } else if (damier.getPion(tuileActuel).getCouleur() ==
                            Pion.Couleur.NOIR && !damier.getestTourJoueurBlanc()) {
                        for (Tuile tuile : Objects.requireNonNull(tuileDispo)) {
                            listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                                    tuile.getYcoord())].setImageResource(R.drawable.ic_pion_possible);
                        }
                    }
                }
                derniereTuile = tuileActuel;
            }
            if (derniereTuile != null) {
                if ((damier.getCouleurPionSurTuile(derniereTuile) == Pion.Couleur.NOIR &&
                        !damier.getestTourJoueurBlanc()) ||
                        (damier.getCouleurPionSurTuile(derniereTuile) == Pion.Couleur.BLANC &&
                                damier.getestTourJoueurBlanc())) {
                    if (!damier.estVideTuile(derniereTuile)) {
                        try {
                            for (Tuile tuile : damier.obtenirCasesDisponibles(derniereTuile)) {
                                if (tuile.equals(tuileActuel)) {
                                    int nbPion = damier.getNbPions();

                                    damier.gererDeplacement(derniereTuile, tuileActuel);
                                    rafraichirJeu(nomJoueurBlanc, nomJoueurNoir);
                                    damier.determinerNotationManoury(tuileActuel,
                                            derniereTuile, nbPion);

                                    updateListeNotationManoury();
                                }
                            }
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (damier.getestTourJoueurBlanc()) {
                    try {
                        if (verifierDefaite(Pion.Couleur.BLANC)) {
                            texteTourNom.setText(R.string.texte_gagnant_noir);
                        }
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        if (verifierDefaite(Pion.Couleur.NOIR)) {
                            texteTourNom.setText(R.string.texte_gagnant_blanc);
                        }
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        // Click du bouton recommencer
        boutonRecommencer.setOnClickListener(v1 -> reinitialiserJeu(nomJoueurBlanc, nomJoueurNoir));

        initialiserJeux(v, clickTuile, nomJoueurBlanc, nomJoueurNoir);

        recyclerViewJeu = v.findViewById(R.id.recycler_view_tour);
        recyclerViewJeu.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateListeNotationManoury();

        return v;
    }

    /**
     * Permet d'initialiser le jeu avec ses boutons.
     *
     * @param view           la vue du layout
     * @param onClickButton  le onClickListener qu'on applique sur tout les boutons initialement
     * @param nomJoueurBlanc Le nom du joueur les pions blanc
     * @param nomJoueurNoir  Le nom du joueur avec les pions noir
     */
    public void initialiserJeux(View view, View.OnClickListener onClickButton,
                                String nomJoueurBlanc, String nomJoueurNoir) {
        layoutJeu = view.findViewById(R.id.layout_jeu);

        // Création des boutons du jeu
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {

                ImageButton bouton = new ImageButton(view.getContext());

                int dizaine = y * 10;

                if (y % 2 == 0) {
                    if (x % 2 == 0) {
                        bouton.setBackgroundColor(getResources().getColor(R.color.couleurCaseBlanche));
                    } else {
                        bouton.setBackgroundColor(getResources().getColor(R.color.couleurCaseBeige));
                    }
                } else {
                    if (x % 2 == 1) {
                        bouton.setBackgroundColor(getResources().getColor(R.color.couleurCaseBlanche));
                    } else {
                        bouton.setBackgroundColor(getResources().getColor(R.color.couleurCaseBeige));
                    }
                }

                bouton.setTag(dizaine + x);
                bouton.setImageResource(R.drawable.ic_tuile_vide);
                bouton.setPadding(15, 15, 15, 15);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                bouton.setLayoutParams(param);
                bouton.setOnClickListener(onClickButton);
                listeButton[dizaine + x] = bouton;

                layoutJeu.addView(bouton);
            }

        }
        rafraichirJeu(nomJoueurBlanc, nomJoueurNoir);
    }

    /**
     * Permet de mettre à jour l'interface du jeu selon les données contenues dans la Map du damier.
     *
     * @param nomJoueurBlanc le nom du joueur avec les pions blanc
     * @param nomJoueurNoir  le nom du joueur avec les pions noir
     */
    public void rafraichirJeu(String nomJoueurBlanc, String nomJoueurNoir) {
        String auTourDe;

        for (Tuile tuile : damier.getTuiles().keySet()) {
            if (damier.estVideTuile(tuile)) {
                listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                        tuile.getYcoord())].setImageResource(R.drawable.ic_tuile_vide);
            } else if (damier.getCouleurPionSurTuile(tuile) == Pion.Couleur.NOIR) {
                if (damier.getPion(tuile) instanceof Dame) {
                    listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                            tuile.getYcoord())].setImageResource(R.drawable.ic_dame_noir);
                } else {
                    listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                            tuile.getYcoord())].setImageResource(R.drawable.ic_pion_noir);
                }
            } else if (damier.getCouleurPionSurTuile(tuile) == Pion.Couleur.BLANC) {
                if (damier.getPion(tuile) instanceof Dame) {
                    listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                            tuile.getYcoord())].setImageResource(R.drawable.ic_dame_blanche);
                } else {
                    listeButton[transformerCoordonneesEnNombre(tuile.getX(),
                            tuile.getYcoord())].setImageResource(R.drawable.ic_pion_blanc);
                }
            }
        }

        if (damier.getestTourJoueurBlanc()) {
            auTourDe = getString(R.string.texte_tour_joueur) + nomJoueurBlanc;
        } else {
            auTourDe = getString(R.string.texte_tour_joueur) + nomJoueurNoir;
        }

        texteTourNom.setText(auTourDe);
    }

    /**
     * Permet de reinitialiser l'interface du jeu de dame.
     *
     * @param nomJoueurBlanc le nom du joueur avec les pions blanc
     * @param nomJoueurNoir  le nom du joueur avec les pions noir
     */
    public void reinitialiserJeu(String nomJoueurBlanc, String nomJoueurNoir) {
        damier.initialiser();
        rafraichirJeu(nomJoueurBlanc, nomJoueurNoir);
        listeManouryMouvement.clear();
        updateListeNotationManoury();
        for (ImageButton button : listeButton) {
            button.setEnabled(true);
        }
    }

    /**
     * Permet de vérifier si une des deux couleurs de pions à gagnés la partie.
     *
     * @param couleur la couleur de l'équipe de pion
     * @return vrai si la couleur de l'équipe qu'on a reçu à perdu la partie, faux si ils n'ont pas perdu
     */
    public boolean verifierDefaite(Pion.Couleur couleur) throws CloneNotSupportedException {
        return (damier.verifierPeuxPasBouger(couleur));
    }

    /**
     * Permet de mettre à jour l'affichage de la liste contenant les notations manoury.
     */
    private void updateListeNotationManoury() {
        JeuAdapter adapterJeu = new JeuAdapter(listeManouryMouvement);
        recyclerViewJeu.setAdapter(adapterJeu);
    }

    /**
     * ViewHolder pour notre RecyclerView de notation manoury.
     *
     * @author Sébastien Fortier
     * @author Yoan Gauthier
     * @author Hakim-Anis Hamani
     */
    private static class JeuHolder extends RecyclerView.ViewHolder {

        /**
         * TextView pour la notation manoury.
         */
        private final TextView manoury;

        /**
         * Constructeur.
         *
         * @param itemView Le layout associé au ViewHolder
         */
        JeuHolder(View itemView) {
            super(itemView);

            manoury = itemView.findViewById(R.id.notation_manoury);
        }

        /**
         * On associe une notation manoury à ce ViewHolder.
         *
         * @param manoury La notation manoury associé
         */
        void bindPositionTour(String manoury) {
            this.manoury.setText(manoury);
        }
    }

    /**
     * Adapter pour notre RecyclerView de notation manoury.
     *
     * @author Sébastien Fortier
     * @author Yoan Gauthier
     */
    private class JeuAdapter extends RecyclerView.Adapter<JeuHolder> {

        /**
         * La liste des notations manoury à afficher.
         */
        private final List<String> listeDeNotationManoury;

        /**
         * Constructeur.
         *
         * @param listeDeNotationManoury La liste des notations manoury à affficher
         */
        JeuAdapter(List<String> listeDeNotationManoury) {
            this.listeDeNotationManoury = listeDeNotationManoury;
        }

        /**
         * Lors de la création des ViewHolder.
         *
         * @param parent   Layout dans lequel la nouvelle vue
         *                 sera ajoutée quand elle sera liée à une position
         * @param viewType Le type de vue de la nouvelle vue
         * @return Un ViewHolder pour notre cellule
         */
        @NonNull
        @Override
        public JeuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_historique_deplacement, parent,
                    false);
            return new JeuHolder(view);
        }

        /**
         * Associe un élément à un ViewHolder.
         *
         * @param holder   Le ViewHolder à utiliser
         * @param position La position dans la liste qu'on souhaite utiliser
         */
        @Override
        public void onBindViewHolder(@NonNull JeuHolder holder, int position) {
            String manoury = listeDeNotationManoury.get(position);
            holder.bindPositionTour(manoury);
        }

        /**
         * Permet de retourner la grandeur de liste de la notation.
         *
         * @return Le nombre d'item total de notre liste
         */
        @Override
        public int getItemCount() {
            return listeDeNotationManoury.size();
        }
    }

    /**
     * Création d'une instance de notre Fragment. Pour contrôler
     * les données nécessaires pour la création.
     *
     * @param nomJoueurBlanc Le nom du joueur avec les pions blancs
     * @param nomJoueurNoir  Le nom du joueur avec les pions noirs
     * @return Le nouveau Fragment
     */
    public static JeuFragment newInstance(String nomJoueurBlanc, String nomJoueurNoir) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NOMJOUEURBLANC, nomJoueurBlanc);
        bundle.putString(EXTRA_NOMJOUEURNOIR, nomJoueurNoir);

        JeuFragment fragment = new JeuFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

}