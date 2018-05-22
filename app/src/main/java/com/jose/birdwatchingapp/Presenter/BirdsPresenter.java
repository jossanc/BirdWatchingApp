package com.jose.birdwatchingapp.Presenter;

import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.BirdsFragment;

/**
 * Created by jose on 12/05/18.
 */

public class BirdsPresenter {


    private String TAG = BirdsPresenter.class.getSimpleName();
    private BirdsFragment view;
    private API api;

    public BirdsPresenter(BirdsFragment fragView) {
        Log.d(TAG,"enlazando con el presentador");
        view=fragView;
        api=new API();
    }


    public void getBirds(){
        Log.d(TAG,"Obteniendo las aves");
        String url=api.get_url("url_all_birds");
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
        }).execute(urls);


    }

}
