package com.jose.birdwatchingapp.Model;

/**
 * Created by jose on 19/05/18.
 */

public class  API {

    public API() {
    }

    public String get_url(String choice){
        switch (choice){
            case "url_all_birds":
                return "http://virtual.lab.inf.uva.es:20072/birds/";
            case "url_all_sightings":
                return "http://virtual.lab.inf.uva.es:20072/sightings/";
            case "url_all_challenges":
                return "http://virtual.lab.inf.uva.es:20072/challenges/";
            case "url_user":
                return "http://virtual.lab.inf.uva.es:20072/users/";
            case "url_register_user":
                return "http://virtual.lab.inf.uva.es:20072/register/";
            default:
                return null;
        }
    }
}
