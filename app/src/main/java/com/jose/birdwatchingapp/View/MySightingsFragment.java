package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.MySightingsAdapter;
import com.jose.birdwatchingapp.Presenter.MySightingsPresenter;
import com.jose.birdwatchingapp.Presenter.SightingsAdapter;
import com.jose.birdwatchingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySightingsFragment extends Fragment {


    private static final String TAG = MySightingsFragment.class.getSimpleName();
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 30;
    private MySightingsPresenter mySightingsPresenter;



    protected RecyclerView mRecyclerView;
    protected SightingsAdapter mAdapter;
    //protected RecyclerView.LayoutManager mLayoutManager;

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

        //mAdapter = new SightingsAdapter(mDataset);
        // Set SightingsAdapter as the adapter for RecyclerView.
        //RecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        mySightingsPresenter = new MySightingsPresenter(this);
        initData();
        return rootView;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initData() {
        mySightingsPresenter.getMySightings();
    }

    public void loadMySightings(String result) {
        //llamar a async task y parsear datos
        mAdapter = new SightingsAdapter(result);
        // Set SightingsAdapter as the adapter for RecyclerView.

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Log.d(TAG,"Adapter cargado en mysightings "+result);
    }

    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }


}
