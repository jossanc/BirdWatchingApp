package com.jose.birdwatchingapp.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.SightingFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 24/05/18.
 */

public class SightingPresenter {
    private String TAG= SightingPresenter.class.getSimpleName();
    private String TAG_AREA="areaName";
    private String TAG_BIRD="commonName";
    private SightingFragment view;
    private API api;
    private List<String> areaList= new ArrayList<>();
    private List<String> birdsList= new ArrayList<>();
    private String bird, area, date;
    private SharedPreferences prefs;

    public SightingPresenter(SightingFragment v){
        view= v;
        api= new API();
    }



    public void initData(String sigBird, String sigArea, String sigDate){
        addItemsOnSpinnerBird();
        addItemsOnSpinnerArea();
        view.setTextBird(sigBird);
        view.setTextArea(sigArea);
        view.setTextDate(sigDate);
    }

    public void addItemsOnSpinnerArea(){
        //get areasNames  desde el modelo..
        areaList.add("Seleccione un nuevo área");
        initializeSpinnerArea();
        view.addItemsOnSpinnerArea(areaList);
    }

    public void addItemsOnSpinnerBird(){
        birdsList.add("Seleccione un nuevo ave");
        initializeSpinnerBirds();
        view.addItemsOnSpinnerBird(birdsList);
    }
    public void changeVisibility(){
        view.changeVisibility();
    }

    public void updateButton(String bird, String area,String date){
        this.bird=bird;
        this.area=area;
        this.date=date;
        view.setUpdateButton(false);
        view.setDeleteButton(false);
        updateSighting();
        view.setUpdateButton(true);
        view.setDeleteButton(true);
    }
    public void deleteButton(String date){
        this.date=date;
        deleteSighting();
    }
    public void gobackButton(){
        view.getActivity().finish();
    }

    public void deleteSighting(){

    }

    public void updateSighting() {
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        String userName=prefs.getString("username","");
        Log.d(TAG,bird+"  "+area+"  "+userName);

        String url=api.get_url("url_all_sightings");
        String json;
        //verificar aynctask y eso, httpinterface
        json = "{userName:"+userName+",commonBirdName:"+bird+",areaName:"+area+",sightingDate:"+date+"}";
        Log.d(TAG,json);
        String[] urls = {"","",""};
        urls[0]="post";/////////////////////////77  update actualizar API
        urls[1]=url;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        onSightingSuccess();
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("No se pudo actualizar en estos momentos");
                    }
                });
            }
        }).execute(urls);

    }
    public void onSightingSuccess(){
        gobackButton();

    }
    public void initializeSpinnerArea(){
        String url=api.get_url("url_areas");
        String[] urls = {"","",""};
        urls[0]="get";
        urls[1]=url;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        parseAreas(result);
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
    public void initializeSpinnerBirds(){
        String url=api.get_url("url_all_birdsName");
        String[] urls = {"","",""};
        urls[0]="get";
        urls[1]=url;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        parseBirds(result);
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
    public void parseAreas(String result) {
        String area;
        if (result.contains("<html>")) {
            result = null;
        }
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);
                Log.d(TAG, "areas: " + result);
                // Checking for SUCCESS TAG
                JSONObject a = data.getJSONObject(0);
                String success = a.getString(TAG_AREA);
                if (success != null) {
                    // data found
                    for(int i=0;i<data.length();i++) {
                        JSONObject c = data.getJSONObject(i);
                        area = c.getString(TAG_AREA);
                        areaList.add(area);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get areas " + result);
    }
    public void parseBirds(String result) {
        String birdName;
        if (result.contains("<html>")) {
            result = null;
        }
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);
                Log.d(TAG, "birds: " + result);
                // Checking for SUCCESS TAG
                JSONObject a = data.getJSONObject(0);
                String success = a.getString(TAG_BIRD);
                if (success != null) {
                    // data found
                    for(int i=0;i<data.length();i++) {
                        JSONObject c = data.getJSONObject(i);
                        birdName = c.getString(TAG_BIRD);
                        birdsList.add(birdName);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get birds " + result);
    }
}