package com.jose.birdwatchingapp.Presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jose.birdwatchingapp.Model.Bird;
import com.jose.birdwatchingapp.R;
import com.jose.birdwatchingapp.View.BirdActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 22/05/18.
 */

public class BirdsAdapter extends RecyclerView.Adapter<BirdsAdapter.ViewHolder> {
    private static final String TAG = BirdsAdapter.class.getSimpleName();

    //private Bird s1= new Bird("Cotorra Común","10-05-2018");
    //private Bird s2= new Bird("jose","Cotorra Común","11-05-2018");
    private List<Bird> birdsList = new ArrayList<>();
    private String TAG_BIRD="commonName";
    private String TAG_BIRDSN="scientificName";
    private String TAG_FAMILY="family";
    private String TAG_ECOSYSTEM="ecosystemName";


    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView birdN;
        private final TextView birdSN;
        private final TextView family;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    //putExtras
                    Intent intent = new Intent(v.getContext(), BirdActivity.class);
                    String userName = null;
                    intent.putExtra("username", userName);
                    //v.getContext().startActivity(new Intent(v.getContext(), BirdsActivity.class));
                }
            });
            birdN = (TextView) v.findViewById(R.id.birdName);
            birdSN = (TextView) v.findViewById(R.id.birdSN);
            family = (TextView) v.findViewById(R.id.birdF);
        }

        public TextView getBirdN() {
            return birdN;
        }
        public TextView getBirdSN() {
            return birdSN;
        }
        public TextView getFamily() {
            return family;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param jsonArray String containing the data to populate views to be used by RecyclerView.
     */
    public BirdsAdapter(String jsonArray) {
        //  parsear aqui
        // la vista no tiene que generar el array de strings
        parserAll(jsonArray);
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public BirdsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_bird, viewGroup, false);

        return new BirdsAdapter.ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from the list Birds at this position and replace the contents of the view
        // with that element

        viewHolder.getBirdN().setText(birdsList.get(position).getCommonName());
        viewHolder.getBirdSN().setText(birdsList.get(position).getScientificName());
        viewHolder.getFamily().setText(birdsList.get(position).getFamily());

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your list (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return birdsList.size();
    }


    public void parserAll(String result){
        Log.d(TAG, "Resultado del get Birds " + result);
        if (result.contains("<html>"))
            result = null;
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);

                Log.e(TAG, "All Birds: " + result);

                // Checking for SUCCESS TAG
                JSONObject aux = data.getJSONObject(0);
                String success = aux.getString(TAG_BIRD);
                if (success != null) {
                    // data found
                    // looping through All Data
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        // Storing each json item in variable
                        String birdn = c.getString(TAG_BIRD);
                        String birdsn = c.getString(TAG_BIRDSN);
                        String family = c.getString(TAG_FAMILY);
                        String ecosystem = c.getString(TAG_ECOSYSTEM);
                        // creating new object to put into the List
                        Bird bir= new Bird(birdn,birdsn, family, ecosystem);

                          /*  sig.setUser(user);
                            sig.setBird(bird);
                            sig.setDate(date);
                            sig.setAreaName(area);
                            */
                        // adding the object to the List
                        birdsList.add(bir);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get " + result);
    }
}
