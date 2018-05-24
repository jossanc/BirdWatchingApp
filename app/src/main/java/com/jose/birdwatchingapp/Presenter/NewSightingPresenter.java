package com.jose.birdwatchingapp.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.NewSightingFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 20/05/18.
 */

public class NewSightingPresenter {
    private String TAG= NewSightingPresenter.class.getSimpleName();
    private String TAG_AREA="areaName";
    private String TAG_BIRD="commonName";
    private NewSightingFragment view;
    private API api;
    private List<String> areaList= new ArrayList<>();
    private List<String> birdsList= new ArrayList<>();
    private String bird;
    private String area;
    private SharedPreferences prefs;

    public NewSightingPresenter(NewSightingFragment v){
        view= v;
        api= new API();
    }

    public void addItemsOnSpinnerArea(){
        //get areasNames  desde el modelo..
        areaList.add("Eliga un área");
        initializeSpinnerArea();
        view.addItemsOnSpinnerArea(areaList);
    }

    public void addItemsOnSpinnerBird(){
        birdsList.add("Eliga un ave");
        initializeSpinnerBirds();
        view.addItemsOnSpinnerBird(birdsList);
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
