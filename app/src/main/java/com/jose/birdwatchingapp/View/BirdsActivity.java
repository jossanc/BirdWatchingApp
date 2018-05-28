package com.jose.birdwatchingapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jose.birdwatchingapp.R;

public class BirdsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds);

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.list_toolbar);
  //      setSupportActionBar(myToolbar);
        if (savedInstanceState == null) {
            // Crea un fragmento
            BirdsFragment fragment = new BirdsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
