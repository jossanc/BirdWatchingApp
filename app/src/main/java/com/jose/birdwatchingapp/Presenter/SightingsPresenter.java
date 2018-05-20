package com.jose.birdwatchingapp.Presenter;

import android.util.Log;

import com.jose.birdwatchingapp.Model.API;
import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.SightingsFragment;

/**
 * Created by jose on 19/05/18.
 */

public class SightingsPresenter {
    private String TAG = SightingsPresenter.class.getSimpleName();
    private SightingsFragment view;
    private API api;

    public SightingsPresenter(SightingsFragment fragView) {
        Log.d(TAG,"enlazando con el presentador");
        view=fragView;
        api = new API();
        }

    public void getSightings(){
        Log.d(TAG,"Obteniendo los avistamientos");
        // view.showMessage("asda");
        //view.showMessage("as");
        String url=api.get_url("url_all_sightings");
        Log.d(TAG,url);
        String[] urls={"","",""};
        urls[0]="get";
        urls[1]=url;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        view.loadSightings(result);

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
        }).execute(urls);
    }
}
