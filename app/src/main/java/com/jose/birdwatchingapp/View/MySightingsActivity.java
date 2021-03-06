package com.jose.birdwatchingapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jose.birdwatchingapp.R;

public class MySightingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sightings);
        // Check if the activity is already created
        if (savedInstanceState == null) {
            // Crea un fragmento
            MySightingsFragment fragment = new MySightingsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
