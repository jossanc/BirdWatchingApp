package com.jose.birdwatchingapp.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.jose.birdwatchingapp.Presenter.MainPresenter;
import com.jose.birdwatchingapp.R;

/*
    Actividad principal, accedida después de la actividad LoginActivity. Es donde se carga la primera lista de aves y el menú

    Created by José Luis Sánchez Paredes for the Final Degree Project
 */
public class MainActivity extends AppCompatActivity {

    private MainPresenter mainPresenter;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mainPresenter = new MainPresenter(this);

    }
/*
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
            case R.id.action_orderby:
                orderBy();
                return true;
            default:
                return false;
        }
    }
*/
    public void orderBy() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ordenar aves");
        builder1.setCancelable(true);

        builder1.setNeutralButton(
                "Nombre científico",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //llamar al metodo que ordene
                    }
                });

        builder1.setNegativeButton(
                "Familia",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder1.setPositiveButton(
                "Áreas",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
        });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}



