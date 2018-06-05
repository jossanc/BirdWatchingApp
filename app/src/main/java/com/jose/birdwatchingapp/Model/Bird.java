package com.jose.birdwatchingapp.Model;


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


}
