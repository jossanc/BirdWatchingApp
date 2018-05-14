package com.jose.birdwatchappv1.Model;


/**
 * Created by jose on 12/05/18.
 */

public class Bird {


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
        return commonName;
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
}
