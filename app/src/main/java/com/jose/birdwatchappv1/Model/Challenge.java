package com.jose.birdwatchappv1.Model;

/**
 * Created by jose on 19/05/18.
 */

public class Challenge {
    private String challengeName;
    private String description;

    public Challenge(String challengeName, String description) {
        this.challengeName = challengeName;
        this.description = description;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChallengeName() {

        return challengeName;
    }

    public String getDescription() {
        return description;
    }
}
