package com.jose.birdwatchappv1.View;

import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.jose.birdwatchappv1.Presenter.BirdListFragmentPresenter;
import com.jose.birdwatchappv1.R;
import com.jose.birdwatchappv1.Utilities.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class BirdListFragmentView extends ListFragment {

    private BirdListFragmentPresenter birdListFragmentPresenter;
    // Toast.makeText(BirdOps.this.getActivity(),"Json Data is downloading",Toast.LENGTH_LONG).show();

    private String TAG = BirdListFragmentView.class.getSimpleName();

    private static final String TAG_NAME="commonName";
    private static final String TAG_SNAME="scientificName";
    private static final String TAG_FAMILY="family";
    Context context;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> birdList = new ArrayList<HashMap<String, String>>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("Sin aves...");
        //String a ="a";
        new LoadAllBirds().execute();
        //birdListFragmentPresenter.getBirds(context);
        //birdListFragmentPresenter.new LoadAllBirds().execute();
    }

    /**
     * Background Async Task to Load all birds by making HTTP Request
     * */
    public class LoadAllBirds extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url_all_birds = "http://virtual.lab.inf.uva.es:20072/birds/";
            String jsonStrg = sh.makeServiceCall(url_all_birds);
            if(jsonStrg!=null) {
                try {
                    JSONArray birds = new JSONArray(jsonStrg);

                    Log.e(TAG, "All birds: " + birds);


                        // Checking for SUCCESS TAG
                        JSONObject aux = birds.getJSONObject(0);
                        String success = aux.getString(TAG_NAME);
                        if (success != null) {
                            // birds found
                            // looping through All Birds
                            for (int i = 0; i < birds.length(); i++) {
                                JSONObject c = birds.getJSONObject(i);

                                // Storing each json item in variable
                                String sname = c.getString(TAG_SNAME);
                                String name = c.getString(TAG_NAME);
                                String family = c.getString(TAG_FAMILY);

                                // creating new HashMap
                                HashMap<String, String> map = new HashMap<String, String>();

                                // adding each child node to HashMap key => value
                                map.put(TAG_SNAME, sname);
                                map.put(TAG_NAME, name);
                                map.put(TAG_FAMILY, family);

                                // adding HashList to ArrayList
                                birdList.add(map);
                            }
                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            getActivity(), birdList,
                            R.layout.fragment_bird_list, new String[] { TAG_NAME,
                            TAG_SNAME,TAG_FAMILY},
                            new int[] { R.id.list_item_text_name_bird, R.id.list_item_text_sname, R.id.list_item_text_family });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }


}
