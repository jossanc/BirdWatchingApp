package com.jose.birdwatchappv1.Model;

/**
 * Created by jose on 19/05/18.
 */

public class  API {
    private String url_all_birds="http://virtual.lab.inf.uva.es:20072/birds/";

    public API() {
    }

    public String getUrl_all_birds() {
        return url_all_birds;
    }
    public String get_url(String choice){
        switch (choice){
            case "url_all_birds":
                return "http://virtual.lab.inf.uva.es:20072/birds/";
            case "url_all_sightings":
                return "http://virtual.lab.inf.uva.es:20072/sightings";
            case "url_all_challenges":
                return "http://virtual.lab.inf.uva.es:20072/challenges/";
            default:
                return null;
        }
    }
}
