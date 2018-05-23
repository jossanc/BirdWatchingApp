package com.jose.birdwatchingapp.Presenter;

import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.BirdFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 22/05/18.
 */

public class BirdPresenter {
    private static final String TAG = BirdsPresenter.class.getSimpleName();
    private static final String TAG_AREA = "areaName";
    private static final String TAG_SEASON = "seasonName";
    private String birdName;
    private BirdFragment view;
    private API api;
    private List<String> areaList = new ArrayList<>();
    private List<String> seasonList = new ArrayList<>();

    public BirdPresenter(BirdFragment birdFragment) {
        view = birdFragment;
        api = new API();
    }

    public void initData(String birdN, String birdSN, String birdF, String birdE) {
        view.setBirdN(birdN);
        birdName = birdN;
        view.setBirdSN(birdSN);
        view.setBirdF(birdF);
        view.setBirdE(birdE);
    }

    public void addItemsOnSpinnerSeason() {
        //get seasonNames  desde el modelo..
        seasonList.add("Temporadas en las que reside");
        initializeSpinnerSeason();
        view.addItemsOnSpinnerSeaason(seasonList);
    }

    public void addItemsOnSpinnerArea() {
        //get areasNames  desde el modelo..
        areaList.add("Áreas donde reside");
        initializeSpinnerArea();
        view.addItemsOnSpinnerArea(areaList);
    }

    public void initializeSpinnerArea() {
        String url = api.get_url("url_area_bybird");
        String[] urls = {"", "", ""};
        urls[0] = "get";
        urls[1] = url + birdName;

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
                    for (int i = 0; i < data.length(); i++) {
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

    public void initializeSpinnerSeason() {
        String url = api.get_url("url_seasons_bybird");
        String[] urls = {"", "", ""};
        urls[0] = "get";
        urls[1] = url + birdName;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        parseSeasons(result);
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

    public void parseSeasons(String result) {
        String season;
        if (result.contains("<html>")) {
            result = null;
        }
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);
                Log.d(TAG, "seasons: " + result);
                // Checking for SUCCESS TAG
                JSONObject a = data.getJSONObject(0);
                String success = a.getString(TAG_SEASON);
                if (success != null) {
                    // data found
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);
                        season = c.getString(TAG_SEASON);
                        seasonList.add(season);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get seasons " + result);
    }
}

