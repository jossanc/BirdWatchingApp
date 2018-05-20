package com.jose.birdwatchingapp.Presenter;


import android.content.Intent;

import com.jose.birdwatchingapp.View.MainActivity;
import com.jose.birdwatchingapp.View.MySightingsActivity;
import com.jose.birdwatchingapp.View.SightingsActivity;
/*
 Clase java que actúa como Presentador de la actividad MainActivity
 */
public class MainPresenter {

    private MainActivity mainView;


    public MainPresenter(MainActivity ma) {

        //enlazamos el presentador con la vista
        mainView=ma;
    }

    public void menu(String id){

        //controlamos la entrada de las opciones del menú, e iniciamos la actividad correspondiente

        switch (id){
            case "action_sighting":
                //mainView.startActivity(new Intent(this, SightingActivity.class));
            case "action_sightings":
                mainView.startActivity(new Intent(mainView, SightingsActivity.class));
                return ;
            case "action_my_sightings":
                mainView.startActivity(new Intent(mainView,MySightingsActivity.class));
                return;
            case "action_challenges":
                //mainView.startActivity(new Intent(this, ChallengesActivity.class));
                return;
            case "action_achievements":
                //mainView.startActivity(new Intent(this, AchievementsActivity.class));
                return;
            case "action_settings":
                //mainView.startActivity(new Intent(this, SettingsActivity.class));
            default:
                return;
        }
    }




}
