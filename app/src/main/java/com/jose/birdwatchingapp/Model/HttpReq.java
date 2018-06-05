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

public class HttpReq extends AsyncTask<String,Void,Void> {

    private String TAG = HttpReq.class.getSimpleName();
    private HttpInterface httpResult;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public HttpReq(HttpInterface httpR) {
        httpResult = httpR;
        Log.d(TAG, "enlazando listener");
    }


    @Override
    protected Void doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();
        if (urls[0].contains("post")) {
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
                if(response.isSuccessful())
                    httpResult.onSuccess(response.body().string());
                else httpResult.onFail("Error al obtener los datos");
                //Log.d(TAG,response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                httpResult.onFail("Error al obtener los datos");
            }

        }else if ( urls[0].contains("get")) {
            Log.d(TAG, "Obteniendo el GET de la url "+urls[2]);
            String url = urls[1];
            Request request;
                request = new Request.Builder()
                        .url(url)
                        .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful())
                    httpResult.onSuccess(response.body().string());
                else httpResult.onFail("Error al obtener los datos");
                //Log.d(TAG,response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                httpResult.onFail("Error al obtener los datos");
            }
        }else if (urls[0].contains("del")) {
            Log.d(TAG, "Ejecutando el DELETE a esa url");
            String url = urls[1];
            String json = urls[2];
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .delete(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful())
                    httpResult.onSuccess(response.body().string());
                else httpResult.onFail("Error al eliminar los datos");
                //Log.d(TAG,response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                httpResult.onFail("Error al obtener los datos");
            }

        }else if (urls[0].contains("put")) {
            Log.d(TAG, "Ejecutando el PUT a esa url");
            String url = urls[1];
            String json = urls[2];
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
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
