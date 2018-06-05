package com.jose.birdwatchingapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jose.birdwatchingapp.R;

public class ChallengesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        // Comprobar si la actividad ya ha sido creada con anterioridad
        if (savedInstanceState == null) {
            // Crear un fragmento
            ChallengesFragment fragment = new ChallengesFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
