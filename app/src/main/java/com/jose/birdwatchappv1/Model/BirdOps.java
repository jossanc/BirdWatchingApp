package com.jose.birdwatchappv1.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.jose.birdwatchappv1.Utilities.HttpHandler;
import com.jose.birdwatchappv1.Utilities.HttpInterface;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jose on 12/05/18.
 */

public class BirdOps extends AsyncTask<Void,Void,Void>{

    private String TAG= BirdOps.class.getSimpleName();
    private API api = new API();
    private HttpInterface httpResult;
    public BirdOps(HttpInterface httpR){
        httpResult=httpR;
        Log.d(TAG,"enlazando listener");
       // getBirds();
    }

    public String getBirds(){

        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url_all_birds = api.getUrl_all_birds();
        String birds = sh.makeServiceCall(url_all_birds);

        return birds;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG,"Obteniendo el GET de la url");
        OkHttpClient client = new OkHttpClient();
        String url = api.get_url("url_all_birds");

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
        /* private class GetBirds extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://virtual.lab.inf.uva.es:20072/birds/";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);


            return null;
        }


    }
    if(response !=null){
            try{
                JSONArray birds = new JSONArray(response);

                for(int i=0;i<birds.length();i++){
                    JSONObject b = birds.getJSONObject(i);
                    String commonName=b.getString("commonName");
                    String scientificName= b.getString("scientificName");
                    String family = b.getString("family");

                    Bird bird= new Bird();
                    bird.setCommonName(commonName);
                    bird.setScientificName(scientificName);
                    bird.setFamily(family);
                    Birds.add(bird);
                }

            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        }*/
}
