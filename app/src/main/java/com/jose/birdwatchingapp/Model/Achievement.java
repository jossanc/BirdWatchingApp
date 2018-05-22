package com.jose.birdwatchingapp.Model;

/**
 * Created by jose on 22/05/18.
 */

public class Achievement {
    private String achievementName;
    private String challengeName;
    private String userName;

    public Achievement(String name,String challenge){
        achievementName=name;
        challengeName=challenge;
    }

    public Achievement(String achievementName, String challengeName, String userName) {
        this.achievementName = achievementName;
        this.challengeName = challengeName;
        this.userName = userName;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
