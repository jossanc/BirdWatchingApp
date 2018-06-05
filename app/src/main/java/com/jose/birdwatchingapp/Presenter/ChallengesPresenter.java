package com.jose.birdwatchingapp.Presenter;

import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.ChallengesFragment;

/**
 * Created by jose on 20/05/18.
 */

public class ChallengesPresenter {
    private String TAG = ChallengesPresenter.class.getSimpleName();
    private ChallengesFragment view;
    private API api;

    public ChallengesPresenter(ChallengesFragment fragView) {
        Log.d(TAG,"enlazando con el presentador");
        view=fragView;
        api = new API();
    }

    public void getChallenges(){
        Log.d(TAG,"Obteniendo las aves");
        String url=api.get_url("url_all_challenges");
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
                        view.loadChallenges(result);

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
}
