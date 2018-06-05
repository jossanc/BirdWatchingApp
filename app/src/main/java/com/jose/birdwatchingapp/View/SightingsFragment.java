package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.SightingsAdapter;
import com.jose.birdwatchingapp.Presenter.SightingsPresenter;
import com.jose.birdwatchingapp.R;


public class SightingsFragment extends Fragment {


    private static final String TAG = SightingsFragment.class.getSimpleName();
    private SightingsPresenter sightingsPresenter;

    private TextView title_toolbar;
    protected RecyclerView mRecyclerView;
    protected SightingsAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sightings, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewSightings);
        title_toolbar = (TextView) rootView.findViewById(R.id.toolbar_title);
        title_toolbar.setText("Avistamientos de todos los usuarios");

        // END_INCLUDE(initializeRecyclerView)

        sightingsPresenter = new SightingsPresenter(this);
        initDataset();
        return rootView;
    }


    /**
     * Generates Strings for RecyclerView's adapter. This data would come
     * from a remote server.
     */
    private void initDataset() {
        sightingsPresenter.getSightings();
    }

    public void loadSightings(String result) {
        //llamra a async task y parsear datos
        mAdapter = new SightingsAdapter(result,0);
        // Set SightingsAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }


}
