package cstjean.mobile.checkers2021;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Classe de test qui vérifie le fonctionnement de l'interface pour entrer le nom des joueurs.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 * @author Hakim-Anis Hamani
 */
@RunWith(AndroidJUnit4.class)
public class TestInterfaceNom {

    /**
     * Règle qui indique d'attendre la fin du chargement de l'activité avant le début des tests.
     */
    @Rule
    public ActivityScenarioRule<NomJoueurActivity> rule =
            new ActivityScenarioRule<>(NomJoueurActivity.class);


    /**
     * Vérifie que le message d'erreur s'affiche si l'utilisateur remplie seulement
     * le champ du nom pour le joueur avec les pions blanc.
     */
    @Test
    public void testVerificationNomNoirVide() {

        final String texte = "Nom joueur blanc";
        final String messageErreur = "Les noms ne peuvent pas être vides";

        onView(withId(R.id.input_nomJoueurBlanc)).perform(typeText(texte), closeSoftKeyboard());
        onView(withId(R.id.bouton_commencer)).perform(click());
        onView(withId(R.id.txt_messageErreur)).check(matches(withText(messageErreur)));
    }

    /**
     * Vérifie que le message d'erreur s'affiche si l'utilisateur remplie seulement
     * le champ du nom pour le joueur avec les pions noir.
     */
    @Test
    public void testVerificationNomBlancVide() {

        final String texte = "Nom joueur noir";
        final String messageErreur = "Les noms ne peuvent pas être vides";

        onView(withId(R.id.input_nomJoueurNoir)).perform(typeText(texte), closeSoftKeyboard());
        onView(withId(R.id.bouton_commencer)).perform(click());
        onView(withId(R.id.txt_messageErreur)).check(matches(withText(messageErreur)));
    }

    /**
     * Vérifie que le message d'erreur s'affiche si l'utilisateur remplie aucun des champs
     * pour le nom des joueurs.
     */
    @Test
    public void testVerificationDeuxNomsVide() {

        final String messageErreur = "Les noms ne peuvent pas être vides";

        onView(withId(R.id.bouton_commencer)).perform(click());
        onView(withId(R.id.txt_messageErreur)).check(matches(withText(messageErreur)));
    }

    /**
     * Vérifie que le changement d'activité se fait après avoir rempli les deux champs pour les noms.
     */
    @Test
    public void testVerificationReussite() {
        Intents.init();
        final String nomjoueurBlanc = "Nom joueur blanc";
        final String nomJoueurNoir = "Nom joueur noir";

        onView(withId(R.id.input_nomJoueurBlanc)).perform(typeText(nomjoueurBlanc), closeSoftKeyboard());
        onView(withId(R.id.input_nomJoueurNoir)).perform(typeText(nomJoueurNoir), closeSoftKeyboard());

        onView(withId(R.id.bouton_commencer)).perform(click());

        // Verifier le changement d'activité
        intended(hasComponent(JeuActivity.class.getName()));

        Intents.release();
    }
}