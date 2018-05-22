package com.jose.birdwatchingapp.Model;


import android.util.Log;

import com.jose.birdwatchingapp.Utilities.HttpHandler;

/**
 * Created by jose on 12/05/18.
 */

public class Bird {

    private String TAG= Bird.class.getSimpleName();

    private String commonName;
    private String scientificName;
    private String family;
    private String ecosystem;

    public Bird(){
        this.commonName=null;
        this.scientificName=null;
        this.family=null;
        this.ecosystem=null;
    }
    public Bird(String nombre,String nombreCientifico, String familia, String eco){
        commonName=nombre;
        scientificName=nombreCientifico;
        family=familia;
        ecosystem=eco;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getFamily() {
        return family;
    }
    public String getEcosystem(){
        return ecosystem;
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


    public String getBirds2(){
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
