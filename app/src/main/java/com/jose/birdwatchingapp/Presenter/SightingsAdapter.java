package com.jose.birdwatchingapp.Presenter;

/**
 * Created by jose on 17/05/18.
 */


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jose.birdwatchingapp.Model.Sighting;
import com.jose.birdwatchingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class SightingsAdapter extends RecyclerView.Adapter<SightingsAdapter.ViewHolder> {
    private static final String TAG = SightingsAdapter.class.getSimpleName();

    private Sighting s1= new Sighting("jose","Cotorra Común","10-05-2018");
    private Sighting s2= new Sighting("jose","Cotorra Común","11-05-2018");
    private List<Sighting> sightings = new ArrayList<>();
    private String TAG_USER="userName";
    private String TAG_BIRD="commonBirdName";
    private String TAG_DATE="sightingDate";
    private String TAG_AREA="areaName";

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView sigUser;
        private final TextView sigBird;
        private final TextView sigDate;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    //v.getContext().startActivity(new Intent(v.getContext(), SightingsActivity.class));
                }
            });
            sigUser = (TextView) v.findViewById(R.id.sightingsUser);
            sigBird = (TextView) v.findViewById(R.id.sightingsBird);
            sigDate = (TextView) v.findViewById(R.id.sightingsDate);
        }

        public TextView getSightingUser() {
            return sigUser;
        }
        public TextView getSightingBird() {
            return sigBird;
        }
        public TextView getSightingDate() {
            return sigDate;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param jsonArray String containing the data to populate views to be used by RecyclerView.
     */
    public SightingsAdapter(String jsonArray) {
        //  parsear aqui
        // la vista no tiene que generar el array de strings
        sightings.add(s1);
        sightings.add(s2);
        parserAllSightings(jsonArray);
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sighting, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from the list sightings at this position and replace the contents of the view
        // with that element
            viewHolder.getSightingUser().setText(sightings.get(position).getUser());
            viewHolder.getSightingBird().setText(sightings.get(position).getBird());
            viewHolder.getSightingDate().setText(sightings.get(position).getDate());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your list (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return sightings.size();
    }

    public void parserAllSightings(String result){
            Log.d(TAG, "Resultado del getBirds " + result);
            if (result.contains("<html>"))
                result = null;
            if (result != null) {
                try {
                    JSONArray data = new JSONArray(result);

                    Log.e(TAG, "All sightings: " + result);


                    // Checking for SUCCESS TAG
                    JSONObject aux = data.getJSONObject(0);
                    String success = aux.getString(TAG_USER);
                    if (success != null) {
                        // data found
                        // looping through All Data
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject c = data.getJSONObject(i);

                            // Storing each json item in variable
                            String bird = c.getString(TAG_BIRD);
                            String user = c.getString(TAG_USER);
                            String date = c.getString(TAG_DATE);
                            String area = c.getString(TAG_AREA);
                            // creating new object to put into the List
                            Sighting sig=new Sighting(user,bird,date);

                            sig.setUser(user);
                            sig.setBird(bird);
                            sig.setDate(date);
                            sig.setAreaName(area);

                            // adding the object to the List
                            sightings.add(sig);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else
                Log.d(TAG, "Resultado del getSigtings " + result);
    }
}
