package cstjean.mobile.checkers2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

/**
 * Fragment pour le nom des joueurs.
 * LINT : On ne peut pas mettre le fragment package-private car le Fragment Manager ne pourra pas le charger
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
public class NomJoueurFragment extends Fragment {


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

        View v = inflater.inflate(R.layout.fragment_nom_joueur, container, false);
        final TextInputEditText inputNomJoueurBlanc = v.findViewById(R.id.input_nomJoueurBlanc);
        final TextInputEditText inputNomJoueurNoir = v.findViewById(R.id.input_nomJoueurNoir);
        Button boutonCommencer = v.findViewById(R.id.bouton_commencer);
        final TextView txtErreur = v.findViewById(R.id.txt_messageErreur);
        boutonCommencer.setOnClickListener(v1 -> {

            final String nomJoueurBlanc =
                    Objects.requireNonNull(inputNomJoueurBlanc.getText()).toString();
            final String nomJoueurNoir =
                    Objects.requireNonNull(inputNomJoueurNoir.getText()).toString();
            if (nomJoueurBlanc.equals("") || nomJoueurNoir.equals("")) {
                txtErreur.setText(R.string.message_erreur_nom);

                return;
            }
            Intent intent = JeuActivity.newIntent(getActivity(), nomJoueurBlanc, nomJoueurNoir);
            startActivity(intent);
        });
        return v;
    }
}