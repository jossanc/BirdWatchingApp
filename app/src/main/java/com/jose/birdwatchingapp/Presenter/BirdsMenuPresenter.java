package com.jose.birdwatchingapp.Presenter;

import android.content.Intent;

import com.jose.birdwatchingapp.View.BirdsActivity;
import com.jose.birdwatchingapp.View.BirdsMenuFragment;

/**
 * Created by jose on 28/05/18.
 */

public class BirdsMenuPresenter {
    private BirdsMenuFragment view;

    public BirdsMenuPresenter(BirdsMenuFragment fragment){
        view=fragment;
    }
    public void menu(String id) {
        Intent intent = new Intent(view.getActivity(), BirdsActivity.class);
        String choice=null;


        //controlamos la entrada de las opciones del menú, e iniciamos la actividad correspondiente
        switch (id) {
            case "action_commonName":
                choice="commonName";
                intent.putExtra("choice", choice);
                view.startActivity(intent);
                return;
            case "action_scientificName":
                choice="scientificName";
                intent.putExtra("choice", choice);
                view.startActivity(intent);
                return;
            case "action_areas":
                choice="areas";
                intent.putExtra("choice", choice);
                view.startActivity(intent);
                return;
            case "action_seasons":
                choice="seasons";
                intent.putExtra("choice", choice);
                view.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
