package com.jose.birdwatchingapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jose.birdwatchingapp.R;

public class BirdsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds_menu);
        // Comprobar si la actividad ya ha sido creada con anterioridad
        if (savedInstanceState == null) {
            // Crear un fragment
            BirdsMenuFragment fragment = new BirdsMenuFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
