package com.jose.birdwatchappv1.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchappv1.Model.API;
import com.jose.birdwatchappv1.Model.SightingsOps;
import com.jose.birdwatchappv1.Utilities.HttpInterface;
import com.jose.birdwatchappv1.View.SightingsFragment;

/**
 * Created by jose on 19/05/18.
 */

public class SightingsPresenter {
    private String TAG = SightingsPresenter.class.getSimpleName();
    private SightingsOps sightings ;
    private SightingsFragment view;
    private String url;
    private API api;
    private SharedPreferences prefs;

    public SightingsPresenter(SightingsFragment fragView) {
        Log.d(TAG,"enlazando con el presentador");
        view=fragView;
        api = new API();
        }

    public void getSightings(){
        Log.d(TAG,"Obteniendo las aves");
        // view.showMessage("asda");
        //view.showMessage("as");
        url=api.get_url("url_all_sightings");
        Log.d(TAG,url);
        new SightingsOps(new HttpInterface() {
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
        }).execute(url);
    }

    public void getMySightings(){
        Log.d(TAG,"Obteniendo mis avistamientos");
        // view.showMessage("asda");
        //view.showMessage("as");
        url=api.get_url("url_all_sightings");
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        String user=prefs.getString("username","");
        new SightingsOps(new HttpInterface() {
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
        }).execute(url+user);
    }
}
