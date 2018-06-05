package com.jose.birdwatchingapp.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.AchievementsFragment;

/**
 * Created by jose on 22/05/18.
 */

public class AchievementsPresenter {
    private String TAG = AchievementsPresenter.class.getSimpleName();
    private AchievementsFragment view;
    private API api;
    private SharedPreferences prefs;

    public AchievementsPresenter(AchievementsFragment fragView){
        view=fragView;
        api= new API();
    }
    public void getAchievements(){
        Log.d(TAG,"Obteniendo los logros");
        String url=api.get_url("url_all_achievements");
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
                        view.loadAchievements(result);

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
