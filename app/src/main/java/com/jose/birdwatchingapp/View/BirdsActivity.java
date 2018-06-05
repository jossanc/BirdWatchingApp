package com.jose.birdwatchingapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jose.birdwatchingapp.R;

import static com.jose.birdwatchingapp.R.id.toolbar;

public class BirdsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birds);
       /* getSupportFragmentManager().beginTransaction()
                .replace(R.id.bird_fragment, new BirdsFragment())
                .addToBackStack(null)
                .commit();
*/
        if (savedInstanceState == null) {
            // Crea un fragmento
            BirdsFragment fragment = new BirdsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment,
                            fragment.getClass().getSimpleName())
                    .commit();
        }
        Toolbar myToolbar = (Toolbar) findViewById(toolbar);
        //setSupportActionBar(myToolbar);
        //myToolbar.inflateMenu(R.menu.menu);
    }/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/
}
