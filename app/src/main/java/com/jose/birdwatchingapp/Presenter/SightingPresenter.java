package com.jose.birdwatchingapp.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.API;
import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.SightingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 20/05/18.
 */

public class SightingPresenter {
    private String TAG= SightingPresenter.class.getSimpleName();
    private SightingFragment view;
    private API api;
    private List<String> areaList= new ArrayList<>();
    private List<String> birdList= new ArrayList<>();
    private String bird;
    private String area;
    private SharedPreferences prefs;

    public SightingPresenter (SightingFragment v){
        view= v;
        api= new API();
    }

    public void addItemsOnSpinnerArea(){
        //get areasNames  desde el modelo..
        areaList.add("Valladolid");
        areaList.add("Zamora");
        view.addItemsOnSpinnerArea(areaList);
    }

    public void addItemsOnSpinnerBird(){
        //get areasNames  desde el modelo..
        birdList.add("Cotorra Argentina");
        birdList.add("Paloma Común");
        view.addItemsOnSpinnerBird(birdList);
    }

    public void sightingButton(String bird,String area){
        this.bird=bird;
        this.area=area;
        newSighting();
    }

    public String newSighting() {
        String result="ok";
        view.setSightingButton(false);
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        String userName=prefs.getString("username","");
        Log.d(TAG,bird+"  "+area+"  "+userName);


        // TODO: Implement your own signup logic here.
        //TODO: progress dialog, algo parecido
        String url=api.get_url("url_all_sightings");
        String json;
        //verificar aynctask y eso, httpinterface
        json = "{userName:"+userName+",commonBirdName:"+bird+",areaName:"+area+"}";
        Log.d(TAG,json);
        String[] urls = {"","",""};
        urls[0]="post";
        urls[1]=url;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        //view.loadSightings(result);
                        //TODO sighting registered...
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

        onSightingSuccess();
        return  result;
    }
    public void onSightingSuccess(){
        view.setSightingButton(true);
        view.getActivity().finish();

    }
}
