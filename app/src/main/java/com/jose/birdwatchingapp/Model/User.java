package com.jose.birdwatchingapp.Model;

/**
 * Created by jose on 20/05/18.
 */

public class User {
    private String userName;
    private String password;
    private String areaName;

    public User(){
        userName="";
        password="";
        areaName="";
    }

    public User(String userName, String password, String areaName) {
        this.userName = userName;
        this.password = password;
        this.areaName = areaName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
