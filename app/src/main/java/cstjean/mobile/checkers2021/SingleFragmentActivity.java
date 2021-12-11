package cstjean.mobile.checkers2021;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Activity qui contient un seul Fragment.
 *
 * @author Sébastien Fortier
 * @author Yoan Gauthier
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    /**
     * Fragment.
     *
     * @return Fragment.
     */
    protected abstract Fragment createFragment();

    /**
     * Quand l'Activity est initialisé.
     *
     * @param savedInstanceState Les données conservées au changement d'état
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}