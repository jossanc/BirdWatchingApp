package com.jose.birdwatchingapp.Presenter;

import android.util.Log;
import android.widget.ImageView;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.BirdFragment;
import com.squareup.picasso.Picasso;

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

    public void initData(String birdN, String birdSN, String birdF, String birdE, ImageView image1, ImageView image2, ImageView image3) {
        view.setBirdN(birdN);
        birdName = birdN;
        view.setBirdSN(birdSN);
        view.setBirdF(birdF);
        view.setBirdE(birdE);
        initializeArea();
        initializeSeason();
        initializeImage(image1,image2,image3);
    }


    public void initializeArea() {
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
                        //view.showMessage(result);
                        parseAreas(result);
                        view.loadArea(areaList);
                        Log.d(TAG,result);
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

    public void initializeSeason() {
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
                        //view.showMessage(result);
                        parseSeasons(result);
                        view.loadSeason(seasonList);
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // view.showMessage(result);
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

    public void initializeImage(ImageView imageView1,ImageView imageView2,ImageView imageView3){
        String url = api.get_url("url_images");
        url=url+birdName;
        Picasso.get().load(url+"1.jpg").into(imageView1);
        Picasso.get().load(url+"2.jpg").into(imageView2);
        Picasso.get().load(url+"3.jpg").into(imageView3);
    }
}

