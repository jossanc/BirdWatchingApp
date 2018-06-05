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
    private String TAG_ECOSYSTEM="ecosystemName";
    private String TAG_COUNT="count";
    private NewSightingFragment view;
    private API api;
    private List<String> areaList= new ArrayList<>();
    private List<String> birdsList= new ArrayList<>();
    private String bird;
    private String area;
    private String userName;
    private String ecosystem=null, count;
    private SharedPreferences prefs;

    public NewSightingPresenter(NewSightingFragment v){
        view= v;
        api= new API();
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        userName=prefs.getString("username","");
    }

    public void addItemsOnSpinnerArea(){
        //get areasNames  desde el modelo..
        areaList.add("Elija un área");
        initializeSpinnerArea();
        view.addItemsOnSpinnerArea(areaList);
    }

    public void addItemsOnSpinnerBird(){
        birdsList.add("Elija un ave");
        initializeSpinnerBirds();
        view.addItemsOnSpinnerBird(birdsList);
    }

    public void sightingButton(String bird,String area){
        this.bird=bird;
        this.area=area;
        view.setSightingButton(false);
        newSighting();
        getEcosystem();
        getCountSightings();
        view.setSightingButton(true);
        //checkAchievements();
        //checkNovato();

    }


    public void newSighting() {
        Log.d(TAG,bird+"  "+area+"  "+userName);
        String url=api.get_url("url_all_sightings");
        String json;
        json = "{\"userName\":\""+userName+"\",\"commonBirdName\":\""+bird+"\",\"areaName\":\""+area+"\"}";
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
                        view.showMessage("Nuevo avistamiento añadido correctamente");
                           }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("No se pudo añadir en estos momentos");
                    }
                });
            }
        }).execute(urls);
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
        if (!result.isEmpty()) {
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
        if (!result.isEmpty()) {
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

    public void getEcosystem(){
        String url=api.get_url("url_get_ecosystem");
        String json;
        json = "{\"commonName\":\""+bird+"\"}";
        Log.d(TAG,json);
        String[] urls = {"","",""};
        urls[0]="get";
        urls[1]=url+bird;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                if (!result.contains("<html>")) {
                    try {
                        JSONObject data = new JSONObject(result);
                        String success = data.getString(TAG_ECOSYSTEM);
                        if (success != null) {
                                ecosystem=success;
                        }checkAchievements(ecosystem);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else
                    Log.d(TAG, "error al hacer get ecosystem " + result);
            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("No se pudo añadir en estos momentos");
                    }
                });
            }
        }).execute(urls);
    }

    public void getCountSightings(){
        String url=api.get_url("url_count_sightings");
        String json, data;
        json = "{\"userName\":\""+userName+"\"}";
        Log.d(TAG,json);
        String[] urls = {"","",""};
        urls[0]="get";
        urls[1]=url+userName;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                if (!result.contains("<html>")) {
                    try {
                        JSONArray array = new JSONArray(result);
                        JSONObject data = array.getJSONObject(0);
                        String success = data.getString(TAG_COUNT);
                        if (success != null) {
                            // data found
                            count=success;
                        }checkNovato();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else
                    Log.d(TAG, "error al hacer get count sightings " + result);
            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("No se pudo añadir en estos momentos");
                    }
                });
            }
        }).execute(urls);
    }

    public void checkAchievements(String ecosystem){
        String url=api.get_url("url_all_achievements");
        String json;
        if(ecosystem!=null)
            switch (ecosystem){
            case "Zona urbana":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Urbanita novato\",\"challengeName\":\"Ave de ciudad\"}";
                insertAchievement(url,json);
                return;
            case "Zona arbolada":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"\",\"challengeName\":\"\"}";
                insertAchievement(url,json);
                return;
            case "Zona semiarbolada":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"\",\"challengeName\":\"\"}";
                insertAchievement(url,json);
                return;
            case "Zona de pradera":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Pastor\",\"challengeName\":\"Ave de pradera\"}";
                insertAchievement(url,json);
                return;
            case "Zona boscosa":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Cervatillo\",\"challengeName\":\"Ave de zona boscosa\"}";
                insertAchievement(url,json);
                return;
            case "Zona de montaña":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Montañero novato\",\"challengeName\":\"Ave de montaña\"}";
                insertAchievement(url,json);
                return;
            case "Zona rural":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Campesino novato\",\"challengeName\":\"Ave rural\"}";
                insertAchievement(url,json);
                return;
            case "Brezales":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Amo del monte\",\"challengeName\":\"Ave de brezales\"}";
                insertAchievement(url,json);
                return;
            case "Zona de humedales":
                json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Anfibio\",\"challengeName\":\"Ave de humedales\"}";
                insertAchievement(url,json);
                return;
            default:
                return;
        }
        else
            Log.d(TAG,"ecosystem null");
    }
    public void checkNovato() {
        String url=api.get_url("url_all_achievements");
        String json;
        int c = 0;
        if (count != null){
            c = Integer.parseInt(count);
         }else
            Log.d(TAG,"Count null");
        if(c<3) {
            Log.d(TAG, "Mas de 3 avistamientos");
        }else if(c<10){
            json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Novato\",\"challengeName\":\"Novato\"}";
            insertAchNov(url,json);
        }else{
            json = "{\"userName\":\""+userName+"\",\"achievementName\":\"Amante de las aves\",\"challengeName\":\"Ver 10 aves\"}";
            insertAchNov(url,json);
        }
    }
    public void insertAchievement(String url,String json){
        String[] urls = {"","",""};
        urls[0]="post";
        urls[1]=url;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
            }

            @Override
            public void onFail(final String result) {
            }
        }).execute(urls);

    }

    public void insertAchNov(String url, String json){
            Log.d(TAG,json);
            String[] urls = {"","",""};
            urls[0]="post";
            urls[1]=url;
            urls[2]=json;

            new HttpReq(new HttpInterface() {
                @Override
                public void onSuccess(final String result) {
                }

                @Override
                public void onFail(final String result) {
                }
            }).execute(urls);

            //  onSightingFinish();
        }
    }

