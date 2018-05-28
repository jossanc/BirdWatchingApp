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

import com.jose.birdwatchingapp.Presenter.BirdsAdapter;
import com.jose.birdwatchingapp.Presenter.BirdsPresenter;
import com.jose.birdwatchingapp.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class BirdsFragment extends Fragment {

    private static final String TAG = BirdsFragment.class.getSimpleName();
    private BirdsPresenter presenter;


    protected RecyclerView mRecyclerView;
    protected BirdsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_birds, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewBird);
        TextView toolbar=(TextView) rootView.findViewById(R.id.toolbar_title);
        toolbar.setText("Lista de aves");
        toolbar.setTextSize(30);
    // END_INCLUDE(initializeRecyclerView)

        presenter = new BirdsPresenter(this);
        initData();
        return rootView;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would come
     * from a remote server.
     */
    private void initData() {
        presenter.getBirds();
    }

    public void loadBirds(String result) {
        //llamra a async task y parsear datos
        mAdapter = new BirdsAdapter(result);
        // Set SightingsAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
