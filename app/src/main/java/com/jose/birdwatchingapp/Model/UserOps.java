package com.jose.birdwatchingapp.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.jose.birdwatchingapp.Utilities.HttpInterface;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jose on 20/05/18.
 */

public class UserOps extends AsyncTask<String, Void, Void> {

    private String TAG = UserOps.class.getSimpleName();
    private HttpInterface httpResult;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public UserOps(HttpInterface httpR) {
        httpResult = httpR;
        Log.d(TAG, "enlazando listener");
    }


    @Override
    protected Void doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();

        if (urls[0] == "post") {
            Log.d(TAG, "Ejecutando el Post a esa url");
            String url = urls[1];
            String json = urls[2];
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                httpResult.onSuccess(response.body().string());
                //Log.d(TAG,response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                httpResult.onFail("Error al obtener los datos");
            }

        }else if ( urls[0]=="get"){
            String url=urls[1];
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
        }


        return null;
    }
}