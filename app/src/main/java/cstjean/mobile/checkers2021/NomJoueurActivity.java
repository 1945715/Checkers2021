package cstjean.mobile.checkers2021;

import androidx.fragment.app.Fragment;

/**
 * Activity pour le menu des noms des joueurs.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 */
public class NomJoueurActivity extends SingleFragmentActivity {

    /**
     * Création d'un fragment de NomJoueur.
     *
     * @return Un fragment pour le nomJoueur
     */
    @Override
    protected Fragment createFragment() {
        return new NomJoueurFragment();
    }
}
