package com.jose.birdwatchappv1.Model;

/**
 * Created by jose on 12/05/18.
 */

public class Sighting {
    private String user;
    private String bird;
    private String date;
    private String areaName;

    public Sighting(String user,String bird, String date) {
        this.user = user;
        this.bird=bird;
        this.date=date;
        this.areaName="";
    }
    public Sighting(String user,String bird, String date,String areaName) {
        this.user = user;
        this.bird=bird;
        this.date=date;
        this.areaName=areaName;
    }

    public Sighting(){

    }

    public String getUser() {
        return user;
    }

    public String getBird() {
        return bird;
    }

    public String getDate() {
        return date;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setBird(String bird) {
        this.bird = bird;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
