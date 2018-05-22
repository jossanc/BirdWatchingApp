package com.jose.birdwatchingapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jose.birdwatchingapp.R;

public class BirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);
        if (savedInstanceState == null) {
            // Crea un fragmento
            BirdFragment fragment = new BirdFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
