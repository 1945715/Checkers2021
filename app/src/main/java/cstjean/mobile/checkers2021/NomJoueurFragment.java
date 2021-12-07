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
 * Fragment pour le nom des joueurs
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * <p>
 * LINT : On ne peut pas mettre le fragment package-private car le
 * Fragment Manager ne pourra pas le charger
 */
public class NomJoueurFragment extends Fragment {


    /**
     * Instanciation de l'interface.
     *
     * @param p_inflater           Pour instancier l'interface
     * @param p_container          Le parent qui contiendra notre interface
     * @param p_savedInstanceState Les données conservées au changement d'état
     * @return La vue instanciée
     */
    @Override
    public View onCreateView(LayoutInflater p_inflater, ViewGroup p_container,
                             Bundle p_savedInstanceState) {

        View v = p_inflater.inflate(R.layout.fragment_nom_joueur, p_container, false);


        final TextInputEditText inputNomJoueurBlanc = v.findViewById(R.id.input_nomJoueurBlanc);
        final TextInputEditText inputNomJoueurNoir = v.findViewById(R.id.input_nomJoueurNoir);


        Button boutonCommencer = v.findViewById(R.id.bouton_commencer);
        final TextView txtErreur = v.findViewById(R.id.txt_messageErreur);
        boutonCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });


        return v;
    }
}