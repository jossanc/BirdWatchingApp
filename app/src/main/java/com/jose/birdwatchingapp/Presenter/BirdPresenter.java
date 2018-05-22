package com.jose.birdwatchingapp.Presenter;

import com.jose.birdwatchingapp.View.BirdFragment;

/**
 * Created by jose on 22/05/18.
 */

public class BirdPresenter {
    private static final String TAG=BirdsPresenter.class.getSimpleName();
    private BirdFragment view;

    public BirdPresenter(BirdFragment birdFragment){
        view=birdFragment;
    }

    public void initData(String a){
        view.setBirdN(a);
    }

    public void addItemsOnSpinnerSeason() {

    }
    public void addItemsOnSpinnerArea(){

    }
}

