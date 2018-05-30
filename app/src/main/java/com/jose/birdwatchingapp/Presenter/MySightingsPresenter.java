package com.jose.birdwatchingapp.Presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.MySightingsFragment;
import com.jose.birdwatchingapp.View.NewSightingActivity;

/**
 * Created by jose on 19/05/18.
 */

public class MySightingsPresenter {
    private String TAG = MySightingsPresenter.class.getSimpleName();
    private MySightingsFragment view;
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
        String url=api.get_url("url_my_sightings");
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        String user=prefs.getString("username","");
        Log.d(TAG,url+user);
        String[] urls={"","",""};
        urls[0]="get";
        urls[1]=url+user;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //view.showMessage(result);
                        view.loadMySightings(result);

                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //view.showMessage(result);
                    }
                });
            }
        }).execute(urls);
    }

    public void menu(String option){
        if(option.contains("action_new_sighting")){
            view.startActivity(new Intent(view.getActivity(), NewSightingActivity.class));
            view.getActivity().finish();
        }
    }

}
