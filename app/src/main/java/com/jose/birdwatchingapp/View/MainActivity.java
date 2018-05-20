package com.jose.birdwatchingapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jose.birdwatchingapp.Presenter.MainPresenter;
import com.jose.birdwatchingapp.R;
/*
    Actividad principal, accedida después de la actividad Login. Es donde se carga la primera lista de aves y el menú

    Created by José Luis Sánchez Paredes for the Final Degree Project
 */
public class MainActivity extends AppCompatActivity {

    private MainPresenter mainPresenter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter= new MainPresenter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    // según la opción de menu elegida llamamos al presentador para que inicie una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sighting:
                mainPresenter.menu("action_sighting");
                return true;
            case R.id.action_sightings:
                mainPresenter.menu("action_sightings");
                return true;
            case R.id.action_my_sightings:
                mainPresenter.menu("action_my_sightings");
                return true;
            case R.id.action_challenges:
                mainPresenter.menu("action_challenges");
                return true;
            case R.id.action_achievements:
                mainPresenter.menu("action_achievements");
                return true;
            case R.id.action_settings:
                mainPresenter.menu("action_settings");
                return true;
            default:
                return false;
        }
    }


}
