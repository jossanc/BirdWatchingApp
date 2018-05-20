package com.jose.birdwatchingapp.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.jose.birdwatchingapp.Utilities.HttpInterface;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jose on 20/05/18.
 */

public class ChallengeOps extends AsyncTask<String,Void,Void> {

    private String TAG = ChallengeOps.class.getSimpleName();
    private HttpInterface httpResult;

    public ChallengeOps(HttpInterface httpR) {
        httpResult = httpR;
        Log.d(TAG, "enlazando listener");
    }


    @Override
    protected Void doInBackground(String... urls) {
        Log.d(TAG, "Obteniendo el GET de la url");
        OkHttpClient client = new OkHttpClient();
        String url = urls[0];

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            httpResult.onSuccess(response.body().string());
            //Log.d(TAG,response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            httpResult.onFail("Error al obtener los datos");
        }
        return null;
    }
}