package com.jose.birdwatchingapp.Presenter;

import android.util.Log;

import com.jose.birdwatchingapp.Model.API;
import com.jose.birdwatchingapp.Model.BirdOps;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.BirdListFragmentView;

/**
 * Created by jose on 12/05/18.
 */

public class BirdsPresenter {


    private String TAG = BirdsPresenter.class.getSimpleName();
    private BirdOps bird ;
    private BirdListFragmentView view;
    private API api;
    private String url;

    public BirdsPresenter(BirdListFragmentView fragView) {
        Log.d(TAG,"enlazando con el presentador");
        view=fragView;
        api=new API();
    }


    public void getBirds(){
        Log.d(TAG,"Obteniendo las aves");
        url=api.get_url("url_all_birds");

        new BirdOps(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        view.loadBirds(result);

                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                    }
                });
            }
        }).execute(url);


    }

}
