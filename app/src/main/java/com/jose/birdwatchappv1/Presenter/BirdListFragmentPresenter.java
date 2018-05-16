package com.jose.birdwatchappv1.Presenter;

import android.content.Context;
import android.util.Log;

import com.jose.birdwatchappv1.Model.Bird;

/**
 * Created by jose on 12/05/18.
 */

public class BirdListFragmentPresenter{


    private String TAG = BirdListFragmentPresenter.class.getSimpleName();
    private Bird bird ;
    private Context view;


    public BirdListFragmentPresenter(Context fragView) {
        view=fragView;
        bird = new Bird();
    }


    public String getBirds(){
        Log.d(TAG,"Obteniendo las aves");

        return bird.getBirds();
        //new LoadAllBirds().execute();
    }

}
