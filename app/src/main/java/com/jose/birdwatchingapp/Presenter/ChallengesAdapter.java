package com.jose.birdwatchingapp.Presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jose.birdwatchingapp.Model.Challenge;
import com.jose.birdwatchingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 19/05/18.
 */

public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesAdapter.ViewHolder>{

    private static final String TAG = ChallengesAdapter.class.getSimpleName();
    private List<Challenge> challengeList = new ArrayList<>();
    private String TAG_CHALLENGE="challengeName";
    private String TAG_DESCRIPTION="description";

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView challengeName;
        private final TextView challengeDescription;

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
            challengeName = (TextView) v.findViewById(R.id.challengeN);
            challengeDescription = (TextView) v.findViewById(R.id.challengeDesc);
        }

        public TextView getChallengeName() {
            return challengeName;
        }
        public TextView getChallengeDescription() {
            return challengeDescription;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param jsonArray String containing the data to populate views to be used by RecyclerView.
     */
    public ChallengesAdapter(String jsonArray) {
        //  parsear aqui
        parseAll(jsonArray);
        Log.d(TAG,challengeList.get(0).getChallengeName());
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_challenge, viewGroup, false);

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
        viewHolder.getChallengeName().setText(challengeList.get(position).getChallengeName());
        viewHolder.getChallengeDescription().setText(challengeList.get(position).getDescription());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your list (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public void parseAll(String result){
        Log.d(TAG, "Resultado del GET url " + result);
        if (result.contains("<html>"))
            result = null;
        if (!result.isEmpty()) {
            try {
                JSONArray data = new JSONArray(result);

                // Checking for SUCCESS TAG
                JSONObject aux = data.getJSONObject(0);
                String success = aux.getString(TAG_CHALLENGE);
                if (success != null) {
                    // data found
                    // looping through All Data
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject elemento = data.getJSONObject(i);

                        // Storing each json item in variable
                        String name = elemento.getString(TAG_CHALLENGE);
                        String des = elemento.getString(TAG_DESCRIPTION);
                        // creating new object to put into the List
                        Challenge challenge=new Challenge(name,des);

                        challenge.setChallengeName(name);
                        challenge.setDescription(des);

                        // adding the object to the List
                        challengeList.add(challenge);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del getChallenges " + result);
    }
}

