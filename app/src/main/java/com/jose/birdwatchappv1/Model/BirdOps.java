package com.jose.birdwatchappv1.Model;

import com.jose.birdwatchappv1.Utilities.HttpHandler;

/**
 * Created by jose on 12/05/18.
 */

public class BirdOps {

    private String TAG= BirdOps.class.getSimpleName();

    public String getBirds(){

        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url_all_birds = "http://virtual.lab.inf.uva.es:20072/birds/";
        String birds = sh.makeServiceCall(url_all_birds);

        return birds;
    }
        /*if(response !=null){
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
