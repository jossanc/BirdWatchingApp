package com.jose.birdwatchingapp.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.API;
import com.jose.birdwatchingapp.Model.SightingsOps;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.MySightingsFragment;

/**
 * Created by jose on 19/05/18.
 */

public class MySightingsPresenter {
    private String TAG = MySightingsPresenter.class.getSimpleName();
    private MySightingsFragment view;
    private String url;
    private API api;
    private SharedPreferences prefs;

    public MySightingsPresenter(MySightingsFragment fragView) {
        Log.d(TAG,"enlazando con el presentador");
        view=fragView;
        api = new API();
    }


    public void getMySightings(){
        Log.d(TAG,"Obteniendo mis avistamientos");
        // view.showMessage("asda");
        //view.showMessage("as");
        url=api.get_url("url_all_sightings");
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        String user=prefs.getString("username","");
        Log.d(TAG,url+user);
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
