package com.jose.birdwatchingapp.Presenter;

/**
 * Created by jose on 19/05/18.
 */

public class  API {

    public API() {
    }

    public String get_url(String choice){
        switch (choice){
            case "url_all_birds":
                return "http://virtual.lab.inf.uva.es:20072/birds/birds";
            case "url_all_birdsName":
                return "http://virtual.lab.inf.uva.es:20072/birds/birdsName";
            case "url_all_sightings":
                return "http://virtual.lab.inf.uva.es:20072/sightings/";
            case "url_all_challenges":
                return "http://virtual.lab.inf.uva.es:20072/challenges/";
            case "url_user":
                return "http://virtual.lab.inf.uva.es:20072/users/";
            case "url_register_user":
                return "http://virtual.lab.inf.uva.es:20072/register";
            case "url_login":
                return "http://virtual.lab.inf.uva.es:20072/users/login";
            case "url_areas":
                return "http://virtual.lab.inf.uva.es:20072/areas/areasName";
            default:
                return null;
        }
    }
}
