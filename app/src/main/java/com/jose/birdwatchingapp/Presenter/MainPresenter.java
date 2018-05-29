package com.jose.birdwatchingapp.Presenter;


import android.content.Intent;

import com.jose.birdwatchingapp.View.AchievementsActivity;
import com.jose.birdwatchingapp.View.BirdsMenuActivity;
import com.jose.birdwatchingapp.View.ChallengesActivity;
import com.jose.birdwatchingapp.View.MainFragment;
import com.jose.birdwatchingapp.View.MySightingsActivity;
import com.jose.birdwatchingapp.View.NewSightingActivity;
import com.jose.birdwatchingapp.View.SightingsActivity;

/*
 Clase java que actúa como Presentador de la actividad MainActivity
 */
public class MainPresenter {

    private MainFragment mainView;


    public MainPresenter(MainFragment ma) {

        //enlazamos el presentador con la vista
        mainView=ma;
    }

    public void menu(String id){

        //controlamos la entrada de las opciones del menú, e iniciamos la actividad correspondiente

        switch (id){
            case "action_sighting":
                mainView.startActivity(new Intent(mainView.getActivity(), NewSightingActivity.class));
                return ;
            case "action_sightings":
                mainView.startActivity(new Intent(mainView.getActivity(), SightingsActivity.class));
                return ;
            case "action_my_sightings":
                mainView.startActivity(new Intent(mainView.getActivity(),MySightingsActivity.class));
                return;
            case "action_challenges":
                mainView.startActivity(new Intent(mainView.getActivity(), ChallengesActivity.class));
                return;
            case "action_achievements":
                mainView.startActivity(new Intent(mainView.getActivity(), AchievementsActivity.class));
                return;
            case "action_settings":
                //mainView.startActivity(new Intent(mainView, SettingsActivity.class));
                return;
            case "action_birds":
                mainView.startActivity(new Intent(mainView.getActivity(), BirdsMenuActivity.class));
                return;
            default:
                return;
        }
    }






}
