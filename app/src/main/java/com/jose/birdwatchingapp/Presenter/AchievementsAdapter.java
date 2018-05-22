package com.jose.birdwatchingapp.Presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jose.birdwatchingapp.Model.Achievement;
import com.jose.birdwatchingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 22/05/18.
 */

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {
    private static final String TAG = AchievementsAdapter.class.getSimpleName();

    private List<Achievement> achievementsList = new ArrayList<>();
    private String TAG_ACHIEVEMENTNAME = "achievementName";
    private String TAG_CHALLENGE = "challengeName";


// BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView achievementName;
        private final TextView achievementChallenge;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    //putExtras
                    //v.getContext().startActivity(new Intent(v.getContext(), SightingsActivity.class));
                }
            });
            achievementName = (TextView) v.findViewById(R.id.achievementN);
            achievementChallenge = (TextView) v.findViewById(R.id.achievementCha);
          }

        public TextView getAchievementName() {
            return achievementName;
        }

        public TextView getAchievementChallenge() {
            return achievementChallenge;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param jsonArray String containing the data to populate views to be used by RecyclerView.
     */
    public AchievementsAdapter(String jsonArray) {
        //  parsear aqui
        // la vista no tiene que generar el array de strings
        parserAll(jsonArray);
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public AchievementsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_achievement, viewGroup, false);

        return new AchievementsAdapter.ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from the list sightings at this position and replace the contents of the view
        // with that element

        viewHolder.getAchievementName().setText(achievementsList.get(position).getAchievementName());
        viewHolder.getAchievementChallenge().setText(achievementsList.get(position).getChallengeName());

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your list (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return achievementsList.size();
    }


    public void parserAll(String result) {
        Log.d(TAG, "Resultado del get achievements " + result);
        if (result.contains("<html>"))
            result = null;
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);

                Log.e(TAG, "All achievements: " + result);

                // Checking for SUCCESS TAG
                JSONObject aux = data.getJSONObject(0);
                String success = aux.getString(TAG_ACHIEVEMENTNAME);
                if (success != null) {
                    // data found
                    // looping through All Data
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        // Storing each json item in variable
                        String aname = c.getString(TAG_ACHIEVEMENTNAME);
                        String achallenge = c.getString(TAG_CHALLENGE);
                        // creating new object to put into the List
                        Achievement achievement= new Achievement(aname,achallenge);

                        // adding the object to the List
                        achievementsList.add(achievement);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get " + result);
    }
}

