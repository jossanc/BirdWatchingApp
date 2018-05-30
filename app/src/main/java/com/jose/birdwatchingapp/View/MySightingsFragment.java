package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.MySightingsPresenter;
import com.jose.birdwatchingapp.Presenter.SightingsAdapter;
import com.jose.birdwatchingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySightingsFragment extends Fragment {


    private static final String TAG = MySightingsFragment.class.getSimpleName();
    private MySightingsPresenter mySightingsPresenter;
    private TextView title_toolbar;
    protected RecyclerView mRecyclerView;
    protected SightingsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sightings, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewSightings);
        title_toolbar = (TextView) rootView.findViewById(R.id.toolbar_title);
        title_toolbar.setText("Mis avistamientos");
        // END_INCLUDE(initializeRecyclerView)

        setHasOptionsMenu(true);
        mySightingsPresenter = new MySightingsPresenter(this);
        initData();
        return rootView;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would come
     * from a remote server.
     */
    private void initData() {
        mySightingsPresenter.getMySightings();
    }

    public void loadMySightings(String result) {
        //llamar a async task y parsear datos
        mAdapter = new SightingsAdapter(result,1);
        // Set SightingsAdapter as the adapter for RecyclerView.

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Log.d(TAG,"Adapter cargado en mysightings "+result);
    }

    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mysightings, menu);
    }

    // según la opción de menu elegida llamamos al presentador para que inicie una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_sighting:
                mySightingsPresenter.menu("action_new_sighting");
                return true;
            default:
                return false;
        }
    }

}
