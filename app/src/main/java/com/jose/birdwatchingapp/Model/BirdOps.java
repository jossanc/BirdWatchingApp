package com.jose.birdwatchingapp.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.jose.birdwatchingapp.Presenter.API;
import com.jose.birdwatchingapp.Utilities.HttpInterface;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jose on 12/05/18.
 */

public class BirdOps extends AsyncTask<String,Void,Void>{

    private String TAG= BirdOps.class.getSimpleName();
    private API api = new API();
    private HttpInterface httpResult;
    public BirdOps(HttpInterface httpR){
        httpResult=httpR;
        Log.d(TAG,"enlazando listener");
       // getBirds();
    }


    @Override
    protected Void doInBackground(String... urls) {
        Log.d(TAG,"Obteniendo el GET de la url");
        OkHttpClient client = new OkHttpClient();
        //String url = api.get_url("url_all_birds");
        String url=urls[0];

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
