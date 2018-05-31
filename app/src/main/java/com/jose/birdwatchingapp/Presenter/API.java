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
            case "url_birds_bysn":
                return "http://virtual.lab.inf.uva.es:20072/birds/birdsbysn";
            case "url_all_birdsName":
                return "http://virtual.lab.inf.uva.es:20072/birds/birdsName";
            case "url_get_ecosystem":
                return "http://virtual.lab.inf.uva.es:20072/birds/getecosystem/";
            case "url_all_sightings":
                return "http://virtual.lab.inf.uva.es:20072/sightings/";
            case "url_my_sightings":
                return "http://virtual.lab.inf.uva.es:20072/sightings/byuser/";
            case "url_count_sightings":
                return "http://virtual.lab.inf.uva.es:20072/sightings/byuser/count/";
            case "url_update_sighting":
                return "http://virtual.lab.inf.uva.es:20072/sightings/update/";
            case "url_all_challenges":
                return "http://virtual.lab.inf.uva.es:20072/challenges/";
            case "url_user":
                return "http://virtual.lab.inf.uva.es:20072/users/";
            case "url_register_user":
                return "http://virtual.lab.inf.uva.es:20072/users/register";
            case "url_login":
                return "http://virtual.lab.inf.uva.es:20072/users/login";
            case "url_areas":
                return "http://virtual.lab.inf.uva.es:20072/areas/areasName";
            case "url_area_bybird":
                return  "http://virtual.lab.inf.uva.es:20072/lives/findbybird/";
            case "url_birds_byarea":
                return  "http://virtual.lab.inf.uva.es:20072/lives/findbyarea/";
            case "url_seasons_bybird":
                return "http://virtual.lab.inf.uva.es:20072/seasons/bybird/";
            case "url_birds_byseason":
                return "http://virtual.lab.inf.uva.es:20072/seasons/byseason/";
            case "url_all_achievements":
                return "http://virtual.lab.inf.uva.es:20072/achievements/";
            default:
                return null;
        }
    }
}
