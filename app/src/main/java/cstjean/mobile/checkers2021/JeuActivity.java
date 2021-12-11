package cstjean.mobile.checkers2021;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

/**
 * Activity pour le jeu.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class JeuActivity extends cstjean.mobile.checkers2021.SingleFragmentActivity {

    /**
     * Clé permettant de récupérer le nom du joueur blanc.
     */
    private static final String EXTRA_NOMJOUEURBLANC = "cstjean.mobile.nomJoueurBlanc";

    /**
     * Clé permettant de récupérer le nom du joueur noir.
     */
    private static final String EXTRA_NOMJOUEURNOIR = "cstjean.mobile.nomJoueurNoir";

    /**
     * Création d'un Intent pour démarrer l'Activity courant.
     *
     * @param packageContext Le contexte lié à l'Activity
     * @param nomJoueurBlanc Le nom du joueur blanc
     * @param nomJoueurNoir  le nom du joueur noir
     * @return L'Intent pour l'ouverture de l'Activity
     */
    public static Intent newIntent(Context packageContext, String nomJoueurBlanc,
                                   String nomJoueurNoir) {
        Intent intent = new Intent(packageContext, JeuActivity.class);
        intent.putExtra(EXTRA_NOMJOUEURBLANC, nomJoueurBlanc);
        intent.putExtra(EXTRA_NOMJOUEURNOIR, nomJoueurNoir);
        return intent;
    }

    /**
     * Création d'un fragment de jeu.
     *
     * @return Un fragment pour le jeu
     */
    @Override
    protected Fragment createFragment() {
        String nomJoueurBlanc = getIntent().getStringExtra(EXTRA_NOMJOUEURBLANC);
        String nomJoueurNoir = getIntent().getStringExtra(EXTRA_NOMJOUEURNOIR);

        return cstjean.mobile.checkers2021.JeuFragment.newInstance(nomJoueurBlanc, nomJoueurNoir);
    }
}
