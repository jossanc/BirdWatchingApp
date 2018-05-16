package com.jose.birdwatchappv1.Model;


import android.util.Log;

import com.jose.birdwatchappv1.Utilities.HttpHandler;

/**
 * Created by jose on 12/05/18.
 */

public class Bird {

    private String TAG= Bird.class.getSimpleName();

    private String commonName;
    private String scientificName;
    private String family;

    public void Bird(){
        this.commonName=null;
        this.scientificName=null;
        this.family=null;
    }
    public void Bird(String nombre,String nombreCientifico,String familia){
        commonName=nombre;
        scientificName=nombreCientifico;
        family=familia;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setScientificName(String scientificName) {

        this.scientificName = scientificName;
    }

    public void setFamily(String family) {

        this.family = family;
    }


    public String getBirds(){
        //TODO: obtener bien los datos, sale exception en httpHandler
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://virtual.lab.inf.uva.es:20072/birds/";
        String birds = sh.makeServiceCall(url);

        Log.d(TAG,"Obteniendo las aves...");
        //Log.d(TAG,birds);
        if (birds == null)
            birds="aves";
        Log.d(TAG,birds);
        return birds;
    }
}
